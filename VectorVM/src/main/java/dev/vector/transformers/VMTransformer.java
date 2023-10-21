package dev.vector.transformers;

import dev.vector.Configuration;
import dev.vector.Obfuscator;
import dev.vector.annotation.VectorVM;
import dev.vector.utils.InstructionMapping;
import dev.vector.utils.InstructionUtil;
import dev.vector.utils.wrappers.ClassWrapper;
import dev.vector.vm.VMClass;
import dev.vector.vm.VMInstruction;
import dev.vector.vm.VMMethod;
import dev.vector.vm.VMPool;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.lang.annotation.Annotation;
import java.util.*;

public class VMTransformer extends ClassTransformer{
    @Override
    public void transform(Obfuscator deobfuscator, List<ClassWrapper> classes) {
        List<ClassWrapper> classesNeedVM = new ArrayList<>();
        classes.forEach((cw) -> {
            ClassNode cn = cw.getClassNode();
            if (deobfuscator.getConfiguration().isAnnotation()){
                if (cn.visibleAnnotations != null){
                    for (AnnotationNode visibleAnnotation : cn.visibleAnnotations) {
                        if (visibleAnnotation.desc.equals(Type.getDescriptor(VectorVM.class))){
                            classesNeedVM.add(cw);
                        }
                    }
                    cn.visibleAnnotations.removeIf(annotationNode -> annotationNode.desc.equals(Type.getDescriptor(VectorVM.class)));
                }
            }else {
                classesNeedVM.add(cw);
            }
        });
        for (ClassWrapper classWrapper : classesNeedVM) {
            buildVM(classWrapper,deobfuscator.getConfiguration());
        }
    }
    public void buildVM(ClassWrapper classWrapper, Configuration configuration){
        ClassNode cn = classWrapper.getClassNode();
        if (cn.interfaces.stream().anyMatch(s -> s.equals(Type.getInternalName(Annotation.class)))){
            return;
        }
        if ((cn.access & ACC_INTERFACE) != 0){
            return;
        }
        System.out.println("Building VM for "+classWrapper.getClassNode().name);
        MethodNode clinit;
        if (cn.methods.stream().noneMatch(methodNode -> methodNode.name.equals("<clinit>"))){
            clinit = new MethodNode(ACC_STATIC,"<clinit>","()V",null,null);
            clinit.instructions.add(new InsnNode(RETURN));
            cn.methods.add(clinit);
        }else {
            clinit = cn.methods.stream().filter(methodNode -> methodNode.name.equals("<clinit>")).findFirst().get();
        }
        FieldNode vmField = new FieldNode(ACC_PRIVATE + ACC_STATIC + ACC_FINAL,"vm",Type.getDescriptor(VMClass.class),null,null);
        cn.fields.add(vmField);
        InsnList createVMField = new InsnList();
        createVMField.add(new TypeInsnNode(NEW, Type.getInternalName(VMClass.class)));
        createVMField.add(new InsnNode(DUP));
        createVMField.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(VMClass.class),"<init>","()V",false));
        createVMField.add(new FieldInsnNode(PUTSTATIC,cn.name,vmField.name,vmField.desc));
        clinit.instructions.insert(createVMField);

        for (MethodNode method : cn.methods) {
            int vmMethodIndex = -1;
            int vmPoolIndex = -1;
            int varIndex = (method.access & Opcodes.ACC_STATIC) != 0 ? 0 : 1;
            int vmStart = -1;
            int defaultVarRange = varIndex + (Type.getArgumentsAndReturnSizes(method.desc) >> 2) - 1;
            if (method.name.equals("<clinit>")){
                vmStart = 4;
                continue;
            }
            if (method.name.equals("<init>")){
                MethodInsnNode superInsn = Arrays.stream(method.instructions.toArray())
                        .filter(abstractInsnNode -> abstractInsnNode instanceof MethodInsnNode)
                        .map(abstractInsnNode -> (MethodInsnNode) abstractInsnNode)
                        .filter(methodInsnNode -> methodInsnNode.getOpcode() == INVOKESPECIAL)
                        .filter(methodInsnNode -> methodInsnNode.name.equals("<init>"))
                        .filter(methodInsnNode -> methodInsnNode.owner.equals(cn.superName) || methodInsnNode.owner.equals(cn.name))
                        .findFirst().get();
                vmStart = method.instructions.indexOf(superInsn);
            }
            InsnList vmMethod = new InsnList();
            System.out.println("Building VM for "+method.name);
            InsnList createVMMethod = new InsnList();
            createVMMethod.add(new FieldInsnNode(GETSTATIC,cn.name,vmField.name,vmField.desc));
            createVMMethod.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(VMClass.class),"allocateMethod",Type.getMethodDescriptor(Type.getType(VMMethod.class)),false));
            vmMethodIndex = method.maxLocals;
            method.maxLocals ++;
            createVMMethod.add(new VarInsnNode(ASTORE,vmMethodIndex));
            createVMMethod.add(new VarInsnNode(ALOAD,vmMethodIndex));
            createVMMethod.add(new LdcInsnNode(method.maxLocals - 1));
            createVMMethod.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(VMMethod.class),"allocatePool",Type.getMethodDescriptor(Type.getType(VMPool.class),Type.INT_TYPE),false));
            vmPoolIndex = method.maxLocals;
            method.maxLocals ++;
            createVMMethod.add(new VarInsnNode(ASTORE,vmPoolIndex));
            int index = -1;
            for (AbstractInsnNode instruction : method.instructions) {
                index++;
                if (index == vmStart+1){
                    vmMethod.add(createVMMethod);
                }
                if (index > vmStart){
                    //build
                    boolean skip = false;
                    switch (instruction.getOpcode()){
                        case ISTORE:
                        case LSTORE:
                        case FSTORE:
                        case DSTORE:
                        case ASTORE:
                        {
                            if (!configuration.isStackVirtualize()){
                                break;
                            }
                            VarInsnNode varInsnNode = (VarInsnNode) instruction;
                            if (varInsnNode.var < defaultVarRange) {
                                break;
                            }
                            vmMethod.add(buildSTORE(instruction,vmMethodIndex,vmPoolIndex));
                            skip = true;
                            break;
                        }
                        case ILOAD:
                        case LLOAD:
                        case FLOAD:
                        case DLOAD:
                        case ALOAD:
                        {
                            if (!configuration.isStackVirtualize()){
                                break;
                            }
                            VarInsnNode varInsnNode = (VarInsnNode) instruction;
                            if (varInsnNode.var < defaultVarRange) {
                                break;
                            }
                            vmMethod.add(buildLOAD(instruction,vmMethodIndex,vmPoolIndex));
                            skip = true;
                            break;
                        }
                        case IINC:
                        {
                            if (!configuration.isStackVirtualize()){
                                break;
                            }
                            IincInsnNode iincInsnNode = (IincInsnNode) instruction;
                            if (iincInsnNode.var < defaultVarRange) {
                                break;
                            }
                            vmMethod.add(buildIINC(iincInsnNode,vmMethodIndex,vmPoolIndex));
                            skip = true;
                            break;
                        }
                        case INVOKESPECIAL:
                        case INVOKEVIRTUAL:
                        case INVOKESTATIC:
                        case INVOKEINTERFACE:
                        {
                            MethodInsnNode methodInsnNode = (MethodInsnNode) instruction;
                            if (configuration.isInvokeVirtualize()){

                            }else if (configuration.isStackVirtualize()){
                                vmMethod.add(fixType(methodInsnNode.desc,methodInsnNode.owner,methodInsnNode.getOpcode(),vmPoolIndex));
                                break;
                            }
                        }
                        case INVOKEDYNAMIC:
                        {
                            InvokeDynamicInsnNode invokeDynamicInsnNode = (InvokeDynamicInsnNode) instruction;
                            if (configuration.isInvokeVirtualize()){

                            }else if (configuration.isStackVirtualize()){
                                vmMethod.add(fixType(invokeDynamicInsnNode.desc,null,invokeDynamicInsnNode.getOpcode(),vmPoolIndex));
                                break;
                            }
                        }
                    }
                    if (skip){
                        continue;
                    }
                }
                vmMethod.add(method.instructions.get(index));
            }


            method.instructions = vmMethod;
        }
    }
    private InsnList fixType(String desc,String owner,int opcode,int vmPoolIndex){
        InsnList stackInsnList = new InsnList();
        InsnList castInsnList = new InsnList();
        List<Type> types = new ArrayList<>();
        if (opcode != INVOKESTATIC && opcode != INVOKESPECIAL && opcode != INVOKEDYNAMIC){
            types.add(Type.getObjectType(owner));
        }
        for (Type argumentType : Type.getArgumentTypes(desc)) {
            types.add(argumentType);
        }
        Collections.reverse(types);
        for (Type type : types) {
            stackInsnList.add(new VarInsnNode(ALOAD,vmPoolIndex));
            if (isPrimitive(type)){
                switch (type.getSort()){
                    case Type.BOOLEAN:
                    case Type.BYTE:
                    case Type.CHAR:
                    case Type.SHORT:
                    case Type.INT:
                        stackInsnList.add(new InsnNode(SWAP));
                        stackInsnList.add(putInt());
                        break;
                    case Type.LONG:
                        stackInsnList.add(new InsnNode(DUP_X2));
                        stackInsnList.add(new InsnNode(POP));
                        stackInsnList.add(putLong());
                        break;
                    case Type.FLOAT:
                        stackInsnList.add(new InsnNode(SWAP));
                        stackInsnList.add(putFloat());
                        break;
                    case Type.DOUBLE:
                        stackInsnList.add(new InsnNode(DUP_X2));
                        stackInsnList.add(new InsnNode(POP));
                        stackInsnList.add(putDouble());
                        break;
                }
            }else{
                stackInsnList.add(new InsnNode(SWAP));
            }
            stackInsnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(VMPool.class),"push",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class)),false));
        }
        Collections.reverse(types);
        for (Type type : types) {
            castInsnList.add(new VarInsnNode(ALOAD,vmPoolIndex));
            castInsnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(VMPool.class),"pop",Type.getMethodDescriptor(Type.getType(Object.class)),false));
            if (isPrimitive(type)){
                switch (type.getSort()){
                    case Type.BOOLEAN:
                    case Type.BYTE:
                    case Type.CHAR:
                    case Type.SHORT:
                    case Type.INT:
                        castInsnList.add(getInt());
                        break;
                    case Type.LONG:
                        castInsnList.add(getLong());
                        break;
                    case Type.FLOAT:
                        castInsnList.add(getFloat());
                        break;
                    case Type.DOUBLE:
                        castInsnList.add(getDouble());
                        break;
                }
            }else{
                castInsnList.add(new TypeInsnNode(CHECKCAST,type.getInternalName()));
            }
        }
        InsnList insnList = new InsnList();
        insnList.add(stackInsnList);
        insnList.add(castInsnList);
        return insnList;
    }
    private boolean isPrimitive(Type type){
        return type.getSort() < Type.ARRAY;
    }
    private InsnList buildIINC(IincInsnNode iincInsnNode,int vmMethodIndex,int vmPoolIndex){
        InsnList insnList = new InsnList();
        insnList.add(buildLOAD(new VarInsnNode(ILOAD,iincInsnNode.var),vmMethodIndex,vmPoolIndex));
        insnList.add(new LdcInsnNode(iincInsnNode.incr));
        insnList.add(new InsnNode(IADD));
        insnList.add(buildSTORE(new VarInsnNode(ISTORE,iincInsnNode.var),vmMethodIndex,vmPoolIndex));
        return insnList;
    }
    private InsnList getInstruction(int vmMethodIndex,int vmPoolIndex){
        InsnList insnList = new InsnList();
        insnList.add(new VarInsnNode(ALOAD,vmMethodIndex));
        insnList.add(new VarInsnNode(ALOAD,vmPoolIndex));
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(VMMethod.class),"allocateInstruction",Type.getMethodDescriptor(Type.getType(VMInstruction.class),Type.getType(VMPool.class)),false));
        return insnList;
    }
    private InsnList pushInstruction(int instruction){
        InsnList insnList = new InsnList();
        insnList.add(new LdcInsnNode(instruction));
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(VMInstruction.class),"putInstruction",Type.getMethodDescriptor(Type.getType(VMInstruction.class),Type.INT_TYPE),false));
        return insnList;
    }
    private InsnList putInt(){
        InsnList insnList = new InsnList();
        insnList.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Integer.class),"valueOf",Type.getMethodDescriptor(Type.getType(Integer.class),Type.INT_TYPE),false));
        return insnList;
    }
    private InsnList putLong(){
        InsnList insnList = new InsnList();
        insnList.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Long.class),"valueOf",Type.getMethodDescriptor(Type.getType(Long.class),Type.LONG_TYPE),false));
        return insnList;
    }
    private InsnList putFloat(){
        InsnList insnList = new InsnList();
        insnList.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Float.class),"valueOf",Type.getMethodDescriptor(Type.getType(Float.class),Type.FLOAT_TYPE),false));
        return insnList;
    }
    private InsnList putDouble(){
        InsnList insnList = new InsnList();
        insnList.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Double.class),"valueOf",Type.getMethodDescriptor(Type.getType(Double.class),Type.DOUBLE_TYPE),false));
        return insnList;
    }
    private InsnList buildSTORE(AbstractInsnNode abstractInsnNode,int vmMethodIndex,int vmPoolIndex){
        VarInsnNode varInsnNode = (VarInsnNode) abstractInsnNode;
        InsnList insnList = new InsnList();
        insnList.add(getInstruction(vmMethodIndex,vmPoolIndex));
        insnList.add(pushInstruction(InstructionMapping.STORE));
        switch (abstractInsnNode.getOpcode()){
            case ISTORE:
            {
                insnList.add(new InsnNode(SWAP));
                insnList.add(putInt());
                break;
            }
            case LSTORE:
            {
                insnList.add(new InsnNode(DUP_X2));
                insnList.add(new InsnNode(POP));
                insnList.add(putLong());
                break;
            }
            case FSTORE:
            {
                insnList.add(new InsnNode(SWAP));
                insnList.add(putFloat());
                break;
            }
            case DSTORE:
            {
                insnList.add(new InsnNode(DUP_X2));
                insnList.add(new InsnNode(POP));
                insnList.add(putDouble());
                break;
            }
            case ASTORE:
            {
                insnList.add(new InsnNode(SWAP));
                break;
            }
        }
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(VMInstruction.class),"putObject",Type.getMethodDescriptor(Type.getType(VMInstruction.class),Type.getType(Object.class)),false));
        insnList.add(new LdcInsnNode(varInsnNode.var));
        insnList.add(putInt());
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(VMInstruction.class),"putObject",Type.getMethodDescriptor(Type.getType(VMInstruction.class),Type.getType(Object.class)),false));
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(VMInstruction.class),"execute",Type.getMethodDescriptor(Type.getType(VMInstruction.class)),false));
        insnList.add(new InsnNode(POP));
        return insnList;
    }
    private InsnList getInt(){
        InsnList insnList = new InsnList();
        insnList.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(Integer.class)));
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Integer.class),"intValue",Type.getMethodDescriptor(Type.INT_TYPE),false));
        return insnList;
    }
    private InsnList getLong(){
        InsnList insnList = new InsnList();
        insnList.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(Long.class)));
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Long.class),"longValue",Type.getMethodDescriptor(Type.LONG_TYPE),false));
        return insnList;
    }
    private InsnList getFloat(){
        InsnList insnList = new InsnList();
        insnList.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(Float.class)));
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Float.class),"floatValue",Type.getMethodDescriptor(Type.FLOAT_TYPE),false));
        return insnList;
    }
    private InsnList getDouble(){
        InsnList insnList = new InsnList();
        insnList.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(Double.class)));
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Double.class),"doubleValue",Type.getMethodDescriptor(Type.DOUBLE_TYPE),false));
        return insnList;
    }

    private InsnList buildLOAD(AbstractInsnNode abstractInsnNode,int vmMethodIndex,int vmPoolIndex){
        VarInsnNode varInsnNode = (VarInsnNode) abstractInsnNode;
        InsnList insnList = new InsnList();
        insnList.add(getInstruction(vmMethodIndex,vmPoolIndex));
        insnList.add(pushInstruction(InstructionMapping.LOAD));
        insnList.add(new LdcInsnNode(varInsnNode.var));
        insnList.add(putInt());
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(VMInstruction.class),"putObject",Type.getMethodDescriptor(Type.getType(VMInstruction.class),Type.getType(Object.class)),false));
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(VMInstruction.class),"execute",Type.getMethodDescriptor(Type.getType(VMInstruction.class)),false));
        insnList.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(VMInstruction.class),"getResult",Type.getMethodDescriptor(Type.getType(Object.class)),false));
        if (abstractInsnNode.getOpcode() == ILOAD){
            insnList.add(getInt());
        }
        else if (abstractInsnNode.getOpcode() == LLOAD){
            insnList.add(getLong());
        }
        else if (abstractInsnNode.getOpcode() == FLOAD){
            insnList.add(getFloat());
        }
        else if (abstractInsnNode.getOpcode() == DLOAD){
            insnList.add(getDouble());
        }
        return insnList;
    }
}
