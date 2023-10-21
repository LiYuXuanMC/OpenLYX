package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.events.events.EventMove;
import al.nya.reflect.events.events.EventText;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.wrapper.wraps.wrapper.Profiler;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import by.radioegor146.nativeobfuscator.Native;

public class EntityTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return Entity.EntityClass;
    }
    @Override
    @Native
    public byte[] transform(byte[] bytes){
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(Entity.moveEntity.getName())&&method.desc.equals("(DDD)V")){
                LocalVariableNode moveVar = new LocalVariableNode("move","L"+Type.getInternalName(EventMove.class)+";",null
                        ,new LabelNode(),new LabelNode(),method.maxLocals);
                method.localVariables.add(moveVar);
                //OnUpdate
                InsnList insnList = method.instructions;
                InsnList move = new InsnList();
                //ALOAD 0
                //INVOKESTATIC al/nya/reflect/events/EventBus.move (Ljava/lang/Object;)V
                LabelNode L0 = new LabelNode();
                move.add(new VarInsnNode(Opcodes.ALOAD,0));
                move.add(new VarInsnNode(Opcodes.DLOAD,1));
                move.add(new VarInsnNode(Opcodes.DLOAD,3));
                move.add(new VarInsnNode(Opcodes.DLOAD,5));
                move.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class),"move"
                        ,"(Ljava/lang/Object;DDD)L"+Type.getInternalName(EventMove.class)+";"));
                move.add(new VarInsnNode(Opcodes.ASTORE,moveVar.index));
                move.add(new VarInsnNode(Opcodes.ALOAD,moveVar.index));
                move.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventMove.class),
                        "isCancel","()Z"));
                move.add(new JumpInsnNode(Opcodes.IFEQ,L0));
                move.add(new InsnNode(Opcodes.RETURN));
                move.add(L0);
                move.add(new VarInsnNode(Opcodes.ALOAD,moveVar.index));
                move.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventMove.class),"getX","()D"));
                move.add(new VarInsnNode(Opcodes.DSTORE,1));
                move.add(new VarInsnNode(Opcodes.ALOAD,moveVar.index));
                move.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventMove.class),"getY","()D"));
                move.add(new VarInsnNode(Opcodes.DSTORE,3));
                move.add(new VarInsnNode(Opcodes.ALOAD,moveVar.index));
                move.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(EventMove.class),"getZ","()D"));
                move.add(new VarInsnNode(Opcodes.DSTORE,5));
                insnList.insert(move);
                method.instructions = insnList;
            }
            if (method.name.equals(Entity.moveEntity.getName())&&method.desc.equals("(DDD)V")){
                //OnUpdate
                InsnList insnList = method.instructions;
                InsnList step = new InsnList();
                LabelNode Lnew = new LabelNode();
                step.add(Lnew);
                step.add(new VarInsnNode(Opcodes.ALOAD,0));
                step.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class),"postStep","(Ljava/lang/Object;)V"));
                for (AbstractInsnNode abstractInsnNode : insnList) {
                    if (abstractInsnNode instanceof MethodInsnNode){
                        if (((MethodInsnNode) abstractInsnNode).owner.equals(Type.getInternalName(Profiler.ProfilerClass)) &&
                                ((MethodInsnNode) abstractInsnNode).name.equals(Profiler.endSection.getName()) &&
                                ((MethodInsnNode) abstractInsnNode).desc.equals("()V")){
                            AbstractInsnNode node = abstractInsnNode.getPrevious();
                            while (!(node instanceof LabelNode)){
                                node = node.getPrevious();
                            }
                            LabelNode L33 = (LabelNode) node;
                            step.insert(new JumpInsnNode(Opcodes.GOTO,L33));
                            insnList.insertBefore(L33,step);
                            while (!(node instanceof JumpInsnNode)){
                                node = node.getPrevious();
                            }
                            node = node.getPrevious();
                            while (!(node instanceof JumpInsnNode)){
                                node = node.getPrevious();
                            }
                            JumpInsnNode jumpL33 = (JumpInsnNode) node;
                            jumpL33.label = Lnew;
                            break;
                        }
                    }
                }
                method.instructions = insnList;
            }
            if (method.name.equals(Entity.moveEntity.getName())&&method.desc.equals("(DDD)V")){
                //OnUpdate
                InsnList insnList = method.instructions;
                InsnList step = new InsnList();
                step.add(new VarInsnNode(Opcodes.ALOAD,0));
                step.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EventBus.class),"preStep","(Ljava/lang/Object;)V"));
                int index = 0;
                for (AbstractInsnNode abstractInsnNode : insnList) {
                    if (abstractInsnNode instanceof FieldInsnNode){
                        if (((FieldInsnNode) abstractInsnNode).name.equals(Entity.stepHeight.getName()) &&
                                ((FieldInsnNode) abstractInsnNode).owner.equals(Type.getInternalName(Entity.EntityClass))&&
                                ((FieldInsnNode) abstractInsnNode).desc.equals("F")){
                            index ++;
                            if (index == 2){
                                insnList.insertBefore(abstractInsnNode,step);
                            }
                        }
                    }
                }
                method.instructions = insnList;
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
