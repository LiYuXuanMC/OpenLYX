package dev.qingwan.crater.builder;

import dev.qingwan.crater.Log;
import dev.qingwan.crater.utils.DynamicByteBuffer;
import dev.qingwan.crater.vm.VirtualMachine;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class VMBuilder {
    private byte[] classBytes;
    private List<String> invokeMap = new LinkedList<>();
    private String args;
    private ProcessProvider processProvider;
    private Map<VMCodes,Byte> codes = new HashMap<>();
    private Map<String,Integer> methods = new HashMap<>();
    private String vmName = null;
    private byte[] vmCodes = null;
    private String staticAccessorName = null;
    private String nonstaticAccessorName = null;
    private String invokeVirtualMachineName = null;
    private String codeMap = null;
    private boolean fieldVM = false;
    public VMBuilder(byte[] classBytes,String args,ProcessProvider processProvider){
        this.classBytes = classBytes;
        this.args = args;
        this.processProvider = processProvider;
        if (args.contains("+FieldVirtualize")){
            fieldVM = true;
        }
    }
    public byte[] buildVM(){
        preProcessOriginClass();
        build();
        postProcessOriginClass();
        return classBytes;
    }
    private void preProcessOriginClass(){
        //Process invokeMap
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        for (MethodNode method : cn.methods) {
            for (AbstractInsnNode instruction : method.instructions) {
                if (instruction instanceof MethodInsnNode){
                    invokeMap.add(((MethodInsnNode) instruction).owner+"]"+((MethodInsnNode) instruction).name+"]"+((MethodInsnNode) instruction).desc);
                }
                if (instruction instanceof FieldInsnNode){
                    invokeMap.add(((FieldInsnNode) instruction).owner+"]"+((FieldInsnNode) instruction).name);
                }
                if (instruction instanceof TypeInsnNode){
                    invokeMap.add(((TypeInsnNode) instruction).desc);
                }
            }
            invokeMap = invokeMap.stream().distinct().collect(Collectors.toList());
        }
        for (String s : invokeMap) {
            Log.debug(s);
        }
        vmCodes = toVMCode(cn);
    }
    private byte[] toVMCode(ClassNode classNode){
        DynamicByteBuffer buffer = new DynamicByteBuffer(0);
        for (MethodNode method : classNode.methods) {
            methods.put(method.name+method.desc,buffer.position());
            Log.debug("Method "+method.name+method.desc+" Offset "+buffer.position());
            for (AbstractInsnNode instruction : method.instructions) {
                if (instruction instanceof VarInsnNode){
                    switch (instruction.getOpcode()){
                        case Opcodes.ILOAD:
                        case Opcodes.LLOAD:
                        case Opcodes.FLOAD:
                        case Opcodes.DLOAD:
                        case Opcodes.ALOAD:
                            buffer.put(getVMCode(VMCodes.LOAD));
                            buffer.putShort((short) ((VarInsnNode) instruction).var);
                            break;
                    }
                }
                if (instruction instanceof InsnNode){
                    switch (instruction.getOpcode()){
                        case Opcodes.IRETURN:
                        case Opcodes.LRETURN:
                        case Opcodes.DRETURN:
                        case Opcodes.ARETURN:
                        case Opcodes.RETURN:
                            buffer.put(getVMCode(VMCodes.RETURN));
                            break;
                        case Opcodes.IADD:
                            buffer.put(getVMCode(VMCodes.ADD_I));
                            break;
                        case Opcodes.POP:
                            buffer.put(getVMCode(VMCodes.POP));
                            break;
                    }
                }
            }
        }
        return buffer.array();
    }
    private byte getVMCode(VMCodes codes){
        if (this.codes.containsKey(codes)){
            return this.codes.get(codes);
        }
        int code = new Random().nextInt(256);
        while (this.codes.containsValue((byte) code)){
            code = new Random().nextInt(256);
        }
        this.codes.put(codes,(byte) code);
        return (byte) code;
    }
    private void build(){
        DynamicByteBuffer vmDataBuffer = new DynamicByteBuffer(0);
        DynamicByteBuffer invokeMapBuffer = new DynamicByteBuffer(0);
        for (String s : invokeMap) {
            byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
            invokeMapBuffer.putInt(bytes.length);
            invokeMapBuffer.put(bytes);
        }
        byte[] invokeMapBytes = invokeMapBuffer.array();
        vmDataBuffer.putInt(invokeMapBytes.length);
        vmDataBuffer.put(invokeMapBytes);
        vmDataBuffer.put(vmCodes);
        byte[] vmData = vmDataBuffer.array();

        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);

        List<String> staticField = new ArrayList<>();
        List<String> nonstaticField = new ArrayList<>();

        if (cn.fields != null){
            for (FieldNode field : cn.fields) {
                if ((field.access & Opcodes.ACC_STATIC) != 0){
                    staticField.add(field.name);
                }else {
                    nonstaticField.add(field.name);
                }
            }
        }

        vmName = "dev/qingwan/crater/vms/"+createRandomString(20);
        String staticPool = createRandomString(20);
        String nonstaticPool = createRandomString(20);
        String invokePool = createRandomString(20);
        codeMap = createRandomString(20);

        String getInternalNameName = createRandomString(15);
        String appendDescriptorName = createRandomString(15);
        String getMethodDescriptorName = createRandomString(15);
        staticAccessorName = createRandomString(15);
        nonstaticAccessorName = createRandomString(15);
        invokeVirtualMachineName = createRandomString(15);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,vmName,null, Type.getInternalName(VirtualMachine.class),new String[]{});

        cw.visitField(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC,staticPool,"[Ljava/lang/Object;",null,null);
        cw.visitField(Opcodes.ACC_PRIVATE,nonstaticPool,"[Ljava/lang/Object;",null,null);
        cw.visitField(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC,invokePool,"Ljava/util/List;",null,null);
        cw.visitField(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC,codeMap,"[B",null,null);

        {
            MethodVisitor clinit = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,"<clinit>","()V",null,new String[]{});
            if (fieldVM){
                clinit.visitLdcInsn(staticField.size());
                clinit.visitTypeInsn(Opcodes.ANEWARRAY,Type.getInternalName(Object.class));
                for (int i = 0; i < staticField.size(); i++) {
                    clinit.visitInsn(Opcodes.DUP);
                    clinit.visitLdcInsn(i);
                    clinit.visitInsn(Opcodes.ACONST_NULL);
                    clinit.visitInsn(Opcodes.AASTORE);
                }
                clinit.visitFieldInsn(Opcodes.PUTSTATIC,vmName,staticPool,"[Ljava/lang/Object;");
            }
            {
                //  NEW java/util/ArrayList
                //    DUP
                //    INVOKESPECIAL java/util/ArrayList.<init> ()
                clinit.visitTypeInsn(Opcodes.NEW,Type.getInternalName(ArrayList.class));
                clinit.visitInsn(Opcodes.DUP);
                clinit.visitMethodInsn(Opcodes.INVOKESPECIAL,Type.getInternalName(ArrayList.class),"<init>","()V");
                clinit.visitFieldInsn(Opcodes.PUTSTATIC,vmName,invokePool,"Ljava/util/List;");
            }
            {
                clinit.visitLdcInsn(vmData.length);
                clinit.visitIntInsn(Opcodes.NEWARRAY,Opcodes.T_BYTE);
                for (int i = 0; i < vmData.length; i++) {
                    clinit.visitInsn(Opcodes.DUP);
                    clinit.visitLdcInsn(i);
                    clinit.visitLdcInsn(vmData[i]);
                    clinit.visitInsn(Opcodes.BASTORE);
                }
                clinit.visitVarInsn(Opcodes.ASTORE,0);
            }
            {
                Label L0 = new Label();
                Label L1 = new Label();
                Label L2 = new Label();
                Label L3 = new Label();
                Label L4 = new Label();
                Label L5 = new Label();
                Label L6 = new Label();
                Label L7 = new Label();
                Label L8 = new Label();
                Label L9 = new Label();
                Label L10 = new Label();
                Label L11 = new Label();
                //    ALOAD 0
                //    INVOKESTATIC java/nio/ByteBuffer.wrap ([B)Ljava/nio/ByteBuffer;
                //    ASTORE 1
                //    ALOAD 1
                //    INVOKEVIRTUAL java/nio/ByteBuffer.getInt ()I
                //    ISTORE 2
                //    ILOAD 2
                //    NEWARRAY T_BYTE
                //    ASTORE 3
                //    ALOAD 1
                //    ALOAD 3
                //    INVOKEVIRTUAL java/nio/ByteBuffer.get ([B)Ljava/nio/ByteBuffer;
                //    POP
                //    ALOAD 3
                //    INVOKESTATIC java/nio/ByteBuffer.wrap ([B)Ljava/nio/ByteBuffer;
                //    ASTORE 4
                //   L0
                clinit.visitVarInsn(Opcodes.ALOAD,0);
                clinit.visitMethodInsn(Opcodes.INVOKESTATIC,Type.getInternalName(ByteBuffer.class),"wrap","([B)Ljava/nio/ByteBuffer;");
                clinit.visitVarInsn(Opcodes.ASTORE,1);
                clinit.visitVarInsn(Opcodes.ALOAD,1);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(ByteBuffer.class),"getInt","()I");
                clinit.visitVarInsn(Opcodes.ISTORE,2);
                clinit.visitVarInsn(Opcodes.ILOAD,2);
                clinit.visitIntInsn(Opcodes.NEWARRAY,Opcodes.T_BYTE);
                clinit.visitVarInsn(Opcodes.ASTORE,3);
                clinit.visitVarInsn(Opcodes.ALOAD,1);
                clinit.visitVarInsn(Opcodes.ALOAD,3);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(ByteBuffer.class),"get","([B)Ljava/nio/ByteBuffer;");
                clinit.visitInsn(Opcodes.POP);
                clinit.visitVarInsn(Opcodes.ALOAD,3);
                clinit.visitMethodInsn(Opcodes.INVOKESTATIC,Type.getInternalName(ByteBuffer.class),"wrap","([B)Ljava/nio/ByteBuffer;");
                clinit.visitVarInsn(Opcodes.ASTORE,4);
                clinit.visitLabel(L0);
                //    ALOAD 4
                //    INVOKEVIRTUAL java/nio/ByteBuffer.hasRemaining ()Z
                //    IFEQ L1
                //    ALOAD 4
                //    INVOKEVIRTUAL java/nio/ByteBuffer.getInt ()I
                //    ISTORE 5
                //    ILOAD 5
                //    NEWARRAY T_BYTE
                //    ASTORE 6
                //    ALOAD 4
                //    ALOAD 6
                //    INVOKEVIRTUAL java/nio/ByteBuffer.get ([B)Ljava/nio/ByteBuffer;
                //    POP
                //    NEW java/lang/String
                //    DUP
                //    ALOAD 6
                //    INVOKESPECIAL java/lang/String.<init> ([B)V
                //    ASTORE 7
                //    ALOAD 7
                //    LDC "]"
                clinit.visitVarInsn(Opcodes.ALOAD,4);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(ByteBuffer.class),"hasRemaining","()Z");
                clinit.visitJumpInsn(Opcodes.IFEQ,L1);
                clinit.visitVarInsn(Opcodes.ALOAD,4);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(ByteBuffer.class),"getInt","()I");
                clinit.visitVarInsn(Opcodes.ISTORE,5);
                clinit.visitVarInsn(Opcodes.ILOAD,5);
                clinit.visitIntInsn(Opcodes.NEWARRAY,Opcodes.T_BYTE);
                clinit.visitVarInsn(Opcodes.ASTORE,6);
                clinit.visitVarInsn(Opcodes.ALOAD,4);
                clinit.visitVarInsn(Opcodes.ALOAD,6);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(ByteBuffer.class),"get","([B)Ljava/nio/ByteBuffer;");
                clinit.visitInsn(Opcodes.POP);
                clinit.visitTypeInsn(Opcodes.NEW,Type.getInternalName(String.class));
                clinit.visitInsn(Opcodes.DUP);
                clinit.visitVarInsn(Opcodes.ALOAD,6);
                clinit.visitMethodInsn(Opcodes.INVOKESPECIAL,Type.getInternalName(String.class),"<init>","([B)V");
                clinit.visitVarInsn(Opcodes.ASTORE,7);
                clinit.visitVarInsn(Opcodes.ALOAD,7);
                clinit.visitLdcInsn("]");
                //    INVOKEVIRTUAL java/lang/String.split (Ljava/lang/String;)[Ljava/lang/String;
                //    ASTORE 8
                //    ALOAD 8
                //    ARRAYLENGTH
                //    ICONST_3
                //    IF_ICMPNE L2
                //    ALOAD 8
                //    ICONST_0
                //    AALOAD
                //    INVOKESTATIC java/lang/Class.forName (Ljava/lang/String;)Ljava/lang/Class;
                //    ASTORE 9
                //    ALOAD 9
                //    INVOKEVIRTUAL java/lang/Class.getDeclaredMethods ()[Ljava/lang/reflect/Method;
                //    ASTORE 10
                //    ALOAD 10
                //    ARRAYLENGTH
                //    ISTORE 11
                //    ICONST_0
                //    ISTORE 12
                //   L3
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(String.class),"split","(Ljava/lang/String;)[Ljava/lang/String;");
                clinit.visitVarInsn(Opcodes.ASTORE,8);
                clinit.visitVarInsn(Opcodes.ALOAD,8);
                clinit.visitInsn(Opcodes.ARRAYLENGTH);
                clinit.visitInsn(Opcodes.ICONST_3);
                clinit.visitJumpInsn(Opcodes.IF_ICMPNE,L2);
                clinit.visitVarInsn(Opcodes.ALOAD,8);
                clinit.visitInsn(Opcodes.ICONST_0);
                clinit.visitInsn(Opcodes.AALOAD);
                clinit.visitMethodInsn(Opcodes.INVOKESTATIC,Type.getInternalName(Class.class),"forName","(Ljava/lang/String;)Ljava/lang/Class;");
                clinit.visitVarInsn(Opcodes.ASTORE,9);
                clinit.visitVarInsn(Opcodes.ALOAD,9);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(Class.class),"getDeclaredMethods","()[Ljava/lang/reflect/Method;");
                clinit.visitVarInsn(Opcodes.ASTORE,10);
                clinit.visitVarInsn(Opcodes.ALOAD,10);
                clinit.visitInsn(Opcodes.ARRAYLENGTH);
                clinit.visitVarInsn(Opcodes.ISTORE,11);
                clinit.visitInsn(Opcodes.ICONST_0);
                clinit.visitVarInsn(Opcodes.ISTORE,12);
                clinit.visitLabel(L3);
                //    ILOAD 12
                //    ILOAD 11
                //    IF_ICMPGE L4
                //    ALOAD 10
                //    ILOAD 12
                //    AALOAD
                //    ASTORE 13
                //    ALOAD 13
                //    INVOKEVIRTUAL java/lang/reflect/Method.getName ()Ljava/lang/String;
                //    ALOAD 8
                //    ICONST_1
                //    AALOAD
                //    INVOKEVIRTUAL java/lang/String.equals (Ljava/lang/Object;)Z
                //    IFEQ L5
                //    ALOAD 13
                //    INVOKESTATIC dev/qingwan/crater/Crater.getMethodDescriptor (Ljava/lang/reflect/Method;)Ljava/lang/String;
                //    ALOAD 8
                //    ICONST_2
                //    AALOAD
                //    INVOKEVIRTUAL java/lang/String.equals (Ljava/lang/Object;)Z
                //    IFEQ L5
                //    ALOAD 13
                //    INVOKEVIRTUAL java/lang/reflect/Method.isAccessible ()Z
                //    IFNE L6
                //    ALOAD 13
                //    ICONST_1
                //    INVOKEVIRTUAL java/lang/reflect/Method.setAccessible (Z)V
                //   L6
                clinit.visitVarInsn(Opcodes.ILOAD,12);
                clinit.visitVarInsn(Opcodes.ILOAD,11);
                clinit.visitJumpInsn(Opcodes.IF_ICMPGE,L4);
                clinit.visitVarInsn(Opcodes.ALOAD,10);
                clinit.visitVarInsn(Opcodes.ILOAD,12);
                clinit.visitInsn(Opcodes.AALOAD);
                clinit.visitVarInsn(Opcodes.ASTORE,13);
                clinit.visitVarInsn(Opcodes.ALOAD,13);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(Method.class),"getName","()Ljava/lang/String;");
                clinit.visitVarInsn(Opcodes.ALOAD,8);
                clinit.visitInsn(Opcodes.ICONST_1);
                clinit.visitInsn(Opcodes.AALOAD);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(String.class),"equals","(Ljava/lang/Object;)Z");
                clinit.visitJumpInsn(Opcodes.IFEQ,L5);
                clinit.visitVarInsn(Opcodes.ALOAD,13);
                clinit.visitMethodInsn(Opcodes.INVOKESTATIC,vmName,getMethodDescriptorName,"(Ljava/lang/reflect/Method;)Ljava/lang/String;");
                clinit.visitVarInsn(Opcodes.ALOAD,8);
                clinit.visitInsn(Opcodes.ICONST_2);
                clinit.visitInsn(Opcodes.AALOAD);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(String.class),"equals","(Ljava/lang/Object;)Z");
                clinit.visitJumpInsn(Opcodes.IFEQ,L5);
                clinit.visitVarInsn(Opcodes.ALOAD,13);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(Method.class),"isAccessible","()Z");
                clinit.visitJumpInsn(Opcodes.IFNE,L6);
                clinit.visitVarInsn(Opcodes.ALOAD,13);
                clinit.visitInsn(Opcodes.ICONST_1);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(Method.class),"setAccessible","(Z)V");
                clinit.visitLabel(L6);
                //    GETSTATIC dev/qingwan/crater/Crater.invokeMap : Ljava/util/List;
                //    ALOAD 13
                //    INVOKEINTERFACE java/util/List.add (Ljava/lang/Object;)Z (itf)
                //    POP
                //   L5
                //    IINC 12 1
                //    GOTO L3
                //   L4
                //    GOTO L7
                //   L2
                //    ALOAD 8
                //    ARRAYLENGTH
                //    ICONST_2
                //    IF_ICMPNE L8
                //    ALOAD 8
                //    ICONST_0
                clinit.visitFieldInsn(Opcodes.GETSTATIC,vmName,invokePool,Type.getDescriptor(List.class));
                clinit.visitVarInsn(Opcodes.ALOAD,13);
                clinit.visitMethodInsn(Opcodes.INVOKEINTERFACE,Type.getInternalName(List.class),"add","(Ljava/lang/Object;)Z");
                clinit.visitInsn(Opcodes.POP);
                clinit.visitLabel(L5);
                clinit.visitIincInsn(12,1);
                clinit.visitJumpInsn(Opcodes.GOTO,L3);
                clinit.visitLabel(L4);
                clinit.visitJumpInsn(Opcodes.GOTO,L7);
                clinit.visitLabel(L2);
                clinit.visitVarInsn(Opcodes.ALOAD,8);
                clinit.visitInsn(Opcodes.ARRAYLENGTH);
                clinit.visitInsn(Opcodes.ICONST_2);
                clinit.visitJumpInsn(Opcodes.IF_ICMPNE,L8);
                clinit.visitVarInsn(Opcodes.ALOAD,8);
                clinit.visitInsn(Opcodes.ICONST_0);
                //    AALOAD
                //    INVOKESTATIC java/lang/Class.forName (Ljava/lang/String;)Ljava/lang/Class;
                //    ASTORE 9
                //    ALOAD 9
                //    INVOKEVIRTUAL java/lang/Class.getDeclaredFields ()[Ljava/lang/reflect/Field;
                //    ASTORE 10
                //    ALOAD 10
                //    ARRAYLENGTH
                //    ISTORE 11
                //    ICONST_0
                //    ISTORE 12
                //   L9
                clinit.visitInsn(Opcodes.AALOAD);
                clinit.visitMethodInsn(Opcodes.INVOKESTATIC,Type.getInternalName(Class.class),"forName","(Ljava/lang/String;)Ljava/lang/Class;");
                clinit.visitVarInsn(Opcodes.ASTORE,9);
                clinit.visitVarInsn(Opcodes.ALOAD,9);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(Class.class),"getDeclaredFields","()[Ljava/lang/reflect/Field;");
                clinit.visitVarInsn(Opcodes.ASTORE,10);
                clinit.visitVarInsn(Opcodes.ALOAD,10);
                clinit.visitInsn(Opcodes.ARRAYLENGTH);
                clinit.visitVarInsn(Opcodes.ISTORE,11);
                clinit.visitInsn(Opcodes.ICONST_0);
                clinit.visitVarInsn(Opcodes.ISTORE,12);
                clinit.visitLabel(L9);
                //    ILOAD 12
                //    ILOAD 11
                //    IF_ICMPGE L10
                //    ALOAD 10
                //    ILOAD 12
                //    AALOAD
                //    ASTORE 13
                //    ALOAD 13
                //    INVOKEVIRTUAL java/lang/reflect/Field.getName ()Ljava/lang/String;
                //    ALOAD 8
                //    ICONST_1
                //    AALOAD
                //    INVOKEVIRTUAL java/lang/String.equals (Ljava/lang/Object;)Z
                //    IFEQ L11
                //    GETSTATIC dev/qingwan/crater/Crater.invokeMap : Ljava/util/List;
                //    ALOAD 13
                //    INVOKEINTERFACE java/util/List.add (Ljava/lang/Object;)Z (itf)
                //    POP
                //   L11
                clinit.visitVarInsn(Opcodes.ILOAD,12);
                clinit.visitVarInsn(Opcodes.ILOAD,11);
                clinit.visitJumpInsn(Opcodes.IF_ICMPGE,L10);
                clinit.visitVarInsn(Opcodes.ALOAD,10);
                clinit.visitVarInsn(Opcodes.ILOAD,12);
                clinit.visitInsn(Opcodes.AALOAD);
                clinit.visitVarInsn(Opcodes.ASTORE,13);
                clinit.visitVarInsn(Opcodes.ALOAD,13);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(Field.class),"getName","()Ljava/lang/String;");
                clinit.visitVarInsn(Opcodes.ALOAD,8);
                clinit.visitInsn(Opcodes.ICONST_1);
                clinit.visitInsn(Opcodes.AALOAD);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(String.class),"equals","(Ljava/lang/Object;)Z");
                clinit.visitJumpInsn(Opcodes.IFEQ,L11);
                clinit.visitFieldInsn(Opcodes.GETSTATIC,vmName,invokePool,Type.getDescriptor(List.class));
                clinit.visitVarInsn(Opcodes.ALOAD,13);
                clinit.visitMethodInsn(Opcodes.INVOKEINTERFACE,Type.getInternalName(List.class),"add","(Ljava/lang/Object;)Z");
                clinit.visitInsn(Opcodes.POP);
                clinit.visitLabel(L11);
                //    IINC 12 1
                //    GOTO L9
                //   L10
                //    GOTO L7
                //   L8
                //    ALOAD 8
                //    ARRAYLENGTH
                //    ICONST_3
                //    IF_ICMPNE L7
                //    ALOAD 8
                //    ICONST_0
                //    AALOAD
                //    INVOKESTATIC java/lang/Class.forName (Ljava/lang/String;)Ljava/lang/Class;
                //    ASTORE 9
                //    GETSTATIC dev/qingwan/crater/Crater.invokeMap : Ljava/util/List;
                //    ALOAD 9
                //    INVOKEINTERFACE java/util/List.add (Ljava/lang/Object;)Z (itf)
                //    POP
                //   L7
                clinit.visitIincInsn(12,1);
                clinit.visitJumpInsn(Opcodes.GOTO,L9);
                clinit.visitLabel(L10);
                clinit.visitJumpInsn(Opcodes.GOTO,L7);
                clinit.visitLabel(L8);
                clinit.visitVarInsn(Opcodes.ALOAD,8);
                clinit.visitInsn(Opcodes.ARRAYLENGTH);
                clinit.visitInsn(Opcodes.ICONST_3);
                clinit.visitJumpInsn(Opcodes.IF_ICMPNE,L7);
                clinit.visitVarInsn(Opcodes.ALOAD,8);
                clinit.visitInsn(Opcodes.ICONST_0);
                clinit.visitInsn(Opcodes.AALOAD);
                clinit.visitMethodInsn(Opcodes.INVOKESTATIC,Type.getInternalName(Class.class),"forName","(Ljava/lang/String;)Ljava/lang/Class;");
                clinit.visitVarInsn(Opcodes.ASTORE,9);
                clinit.visitFieldInsn(Opcodes.GETSTATIC,vmName,invokePool,Type.getDescriptor(List.class));
                clinit.visitVarInsn(Opcodes.ALOAD,9);
                clinit.visitMethodInsn(Opcodes.INVOKEINTERFACE,Type.getInternalName(List.class),"add","(Ljava/lang/Object;)Z");
                clinit.visitInsn(Opcodes.POP);
                clinit.visitLabel(L7);
                //    GOTO L0
                //   L1
                //   FRAME SAME
                //    ALOAD 1
                //    INVOKEVIRTUAL java/nio/ByteBuffer.remaining ()I
                //    ISTORE 2
                //    ILOAD 2
                //    NEWARRAY T_BYTE
                //    ASTORE 3
                //    ALOAD 1
                //    ALOAD 3
                //    INVOKEVIRTUAL java/nio/ByteBuffer.get ([B)Ljava/nio/ByteBuffer;
                //    POP
                //    ALOAD 3
                //    PUTSTATIC dev/qingwan/crater/Crater.codeMap : [B
                clinit.visitJumpInsn(Opcodes.GOTO,L0);
                clinit.visitLabel(L1);
                clinit.visitVarInsn(Opcodes.ALOAD,1);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(ByteBuffer.class),"remaining","()I");
                clinit.visitVarInsn(Opcodes.ISTORE,2);
                clinit.visitVarInsn(Opcodes.ILOAD,2);
                clinit.visitIntInsn(Opcodes.NEWARRAY,Opcodes.T_BYTE);
                clinit.visitVarInsn(Opcodes.ASTORE,3);
                clinit.visitVarInsn(Opcodes.ALOAD,1);
                clinit.visitVarInsn(Opcodes.ALOAD,3);
                clinit.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(ByteBuffer.class),"get","([B)Ljava/nio/ByteBuffer;");
                clinit.visitInsn(Opcodes.POP);
                clinit.visitVarInsn(Opcodes.ALOAD,3);
                clinit.visitFieldInsn(Opcodes.PUTSTATIC,vmName,codeMap,"[B");
            }
            clinit.visitInsn(Opcodes.RETURN);
        }
        if (fieldVM) {
            MethodVisitor staticAccessor = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,staticAccessorName,"(I)Ljava/lang/Object;",null,new String[]{});

            //    GETSTATIC dev/qingwan/crater/Crater.staticPool : [Ljava/lang/Object;
            //    ILOAD 0
            //    AALOAD
            //    ARETURN
            staticAccessor.visitFieldInsn(Opcodes.GETSTATIC,vmName,staticPool,Type.getDescriptor(Object[].class));
            staticAccessor.visitVarInsn(Opcodes.ILOAD,0);
            staticAccessor.visitInsn(Opcodes.AALOAD);
            staticAccessor.visitInsn(Opcodes.ARETURN);
        }
        if (fieldVM) {
            MethodVisitor nonstaticAccessor = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,nonstaticAccessorName,"(I)Ljava/lang/Object;",null,new String[]{});

            //    ALOAD 0
            //    GETFIELD dev/qingwan/crater/Crater.nonstaticPool : [Ljava/lang/Object;
            //    ILOAD 1
            //    AALOAD
            //    ARETURN
            nonstaticAccessor.visitVarInsn(Opcodes.ALOAD,0);
            nonstaticAccessor.visitFieldInsn(Opcodes.GETFIELD,vmName,nonstaticPool,Type.getDescriptor(Object[].class));
            nonstaticAccessor.visitVarInsn(Opcodes.ILOAD,1);
            nonstaticAccessor.visitInsn(Opcodes.AALOAD);
            nonstaticAccessor.visitInsn(Opcodes.ARETURN);
        }
        {
            MethodVisitor init = cw.visitMethod(Opcodes.ACC_PUBLIC,"<init>","()V",null,new String[]{});
            if (fieldVM) {
                init.visitVarInsn(Opcodes.ALOAD,0);
                {
                    init.visitLdcInsn(nonstaticField.size());
                    init.visitTypeInsn(Opcodes.ANEWARRAY,Type.getInternalName(Object.class));
                    for (int i = 0; i < nonstaticField.size(); i++) {
                        init.visitInsn(Opcodes.DUP);
                        init.visitLdcInsn(i);
                        init.visitInsn(Opcodes.ACONST_NULL);
                        init.visitInsn(Opcodes.AASTORE);
                    }
                    init.visitFieldInsn(Opcodes.PUTFIELD,vmName,nonstaticPool,"[Ljava/lang/Object;");
                }
            }
            init.visitInsn(Opcodes.RETURN);
        }
        {
            MethodVisitor getMethodDescriptor = cw.visitMethod(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, getMethodDescriptorName, "(Ljava/lang/reflect/Method;)Ljava/lang/String;", null, new String[]{});
            Label L0 = new Label();
            Label L1 = new Label();

            getMethodDescriptor.visitTypeInsn(Opcodes.NEW,Type.getInternalName(StringBuilder.class));
            getMethodDescriptor.visitInsn(Opcodes.DUP);
            getMethodDescriptor.visitMethodInsn(Opcodes.INVOKESPECIAL,Type.getInternalName(StringBuilder.class),"<init>","()V");
            getMethodDescriptor.visitVarInsn(Opcodes.ASTORE,1);
            getMethodDescriptor.visitVarInsn(Opcodes.ALOAD,1);
            getMethodDescriptor.visitIntInsn(Opcodes.BIPUSH,40);
            getMethodDescriptor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(StringBuilder.class),"append","(C)Ljava/lang/StringBuilder;");
            getMethodDescriptor.visitInsn(Opcodes.POP);
            getMethodDescriptor.visitVarInsn(Opcodes.ALOAD,0);
            getMethodDescriptor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(Method.class),"getParameterTypes","()[Ljava/lang/Class;");
            getMethodDescriptor.visitVarInsn(Opcodes.ASTORE,2);
            getMethodDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            getMethodDescriptor.visitVarInsn(Opcodes.ASTORE,3);
            getMethodDescriptor.visitVarInsn(Opcodes.ALOAD,3);
            getMethodDescriptor.visitInsn(Opcodes.ARRAYLENGTH);
            getMethodDescriptor.visitVarInsn(Opcodes.ISTORE,4);
            getMethodDescriptor.visitInsn(Opcodes.ICONST_0);
            getMethodDescriptor.visitVarInsn(Opcodes.ISTORE,5);
            getMethodDescriptor.visitLabel(L0);
            getMethodDescriptor.visitVarInsn(Opcodes.ILOAD,5);
            getMethodDescriptor.visitVarInsn(Opcodes.ILOAD,4);
            getMethodDescriptor.visitJumpInsn(Opcodes.IF_ICMPGE,L1);
            getMethodDescriptor.visitVarInsn(Opcodes.ALOAD,3);
            getMethodDescriptor.visitVarInsn(Opcodes.ILOAD,5);
            getMethodDescriptor.visitInsn(Opcodes.AALOAD);
            getMethodDescriptor.visitVarInsn(Opcodes.ASTORE,6);
            getMethodDescriptor.visitVarInsn(Opcodes.ALOAD,6);
            getMethodDescriptor.visitVarInsn(Opcodes.ALOAD,1);
            getMethodDescriptor.visitMethodInsn(Opcodes.INVOKESTATIC,vmName,appendDescriptorName,"(Ljava/lang/Class;Ljava/lang/StringBuilder;)V");
            getMethodDescriptor.visitIincInsn(5,1);
            getMethodDescriptor.visitJumpInsn(Opcodes.GOTO,L0);
            getMethodDescriptor.visitLabel(L1);
            getMethodDescriptor.visitVarInsn(Opcodes.ALOAD,1);
            getMethodDescriptor.visitIntInsn(Opcodes.BIPUSH,41);
            getMethodDescriptor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(StringBuilder.class),"append","(C)Ljava/lang/StringBuilder;");
            getMethodDescriptor.visitInsn(Opcodes.POP);
            getMethodDescriptor.visitVarInsn(Opcodes.ALOAD,0);
            getMethodDescriptor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(Method.class),"getReturnType","()Ljava/lang/Class;");
            getMethodDescriptor.visitVarInsn(Opcodes.ALOAD,1);
            getMethodDescriptor.visitMethodInsn(Opcodes.INVOKESTATIC,vmName,appendDescriptorName,"(Ljava/lang/Class;Ljava/lang/StringBuilder;)V");
            getMethodDescriptor.visitVarInsn(Opcodes.ALOAD,1);
            getMethodDescriptor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(StringBuilder.class),"toString","()Ljava/lang/String;");
            getMethodDescriptor.visitInsn(Opcodes.ARETURN);

        }
        {
            MethodVisitor appendDescriptor = cw.visitMethod(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, appendDescriptorName, "(Ljava/lang/Class;Ljava/lang/StringBuilder;)V", null, new String[]{});
            Label L0 = new Label();
            Label L1 = new Label();
            Label L2 = new Label();
            Label L3 = new Label();
            Label L4 = new Label();
            Label L5 = new Label();
            Label L6 = new Label();
            Label L7 = new Label();
            Label L8 = new Label();
            Label L9 = new Label();
            Label L10 = new Label();
            Label L11 = new Label();
            Label L12 = new Label();
            Label L13 = new Label();

            appendDescriptor.visitVarInsn(Opcodes.ALOAD,0);
            appendDescriptor.visitVarInsn(Opcodes.ASTORE,2);
            appendDescriptor.visitLabel(L0);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            appendDescriptor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(Class.class),"isArray","()Z");
            appendDescriptor.visitJumpInsn(Opcodes.IFEQ,L1);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,1);
            appendDescriptor.visitIntInsn(Opcodes.BIPUSH,91);
            appendDescriptor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(StringBuilder.class),"append","(C)Ljava/lang/StringBuilder;");
            appendDescriptor.visitInsn(Opcodes.POP);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            appendDescriptor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(Class.class),"getComponentType","()Ljava/lang/Class;");
            appendDescriptor.visitVarInsn(Opcodes.ASTORE,2);
            appendDescriptor.visitJumpInsn(Opcodes.GOTO,L0);
            appendDescriptor.visitLabel(L1);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            appendDescriptor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(Class.class),"isPrimitive","()Z");
            appendDescriptor.visitJumpInsn(Opcodes.IFEQ,L2);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            appendDescriptor.visitFieldInsn(Opcodes.GETSTATIC,Type.getInternalName(Integer.class),"TYPE","Ljava/lang/Class;");
            appendDescriptor.visitJumpInsn(Opcodes.IF_ACMPNE,L3);
            appendDescriptor.visitIntInsn(Opcodes.BIPUSH,73);
            appendDescriptor.visitVarInsn(Opcodes.ISTORE,3);
            appendDescriptor.visitJumpInsn(Opcodes.GOTO,L4);
            appendDescriptor.visitLabel(L3);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            appendDescriptor.visitFieldInsn(Opcodes.GETSTATIC,Type.getInternalName(Void.class),"TYPE","Ljava/lang/Class;");
            appendDescriptor.visitJumpInsn(Opcodes.IF_ACMPNE,L5);
            appendDescriptor.visitIntInsn(Opcodes.BIPUSH,86);
            appendDescriptor.visitVarInsn(Opcodes.ISTORE,3);
            appendDescriptor.visitJumpInsn(Opcodes.GOTO,L4);
            appendDescriptor.visitLabel(L5);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            appendDescriptor.visitFieldInsn(Opcodes.GETSTATIC,Type.getInternalName(Boolean.class),"TYPE","Ljava/lang/Class;");
            appendDescriptor.visitJumpInsn(Opcodes.IF_ACMPNE,L6);
            appendDescriptor.visitIntInsn(Opcodes.BIPUSH,90);
            appendDescriptor.visitVarInsn(Opcodes.ISTORE,3);
            appendDescriptor.visitJumpInsn(Opcodes.GOTO,L4);
            appendDescriptor.visitLabel(L6);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            appendDescriptor.visitFieldInsn(Opcodes.GETSTATIC,Type.getInternalName(Character.class),"TYPE","Ljava/lang/Class;");
            appendDescriptor.visitJumpInsn(Opcodes.IF_ACMPNE,L7);
            appendDescriptor.visitIntInsn(Opcodes.BIPUSH,67);
            appendDescriptor.visitVarInsn(Opcodes.ISTORE,3);
            appendDescriptor.visitJumpInsn(Opcodes.GOTO,L4);
            appendDescriptor.visitLabel(L7);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            appendDescriptor.visitFieldInsn(Opcodes.GETSTATIC,Type.getInternalName(Byte.class),"TYPE","Ljava/lang/Class;");
            appendDescriptor.visitJumpInsn(Opcodes.IF_ACMPNE,L8);
            appendDescriptor.visitIntInsn(Opcodes.BIPUSH,66);
            appendDescriptor.visitVarInsn(Opcodes.ISTORE,3);
            appendDescriptor.visitJumpInsn(Opcodes.GOTO,L4);
            appendDescriptor.visitLabel(L8);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            appendDescriptor.visitFieldInsn(Opcodes.GETSTATIC,Type.getInternalName(Short.class),"TYPE","Ljava/lang/Class;");
            appendDescriptor.visitJumpInsn(Opcodes.IF_ACMPNE,L9);
            appendDescriptor.visitIntInsn(Opcodes.BIPUSH,83);
            appendDescriptor.visitVarInsn(Opcodes.ISTORE,3);
            appendDescriptor.visitJumpInsn(Opcodes.GOTO,L4);
            appendDescriptor.visitLabel(L9);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            appendDescriptor.visitFieldInsn(Opcodes.GETSTATIC,Type.getInternalName(Double.class),"TYPE","Ljava/lang/Class;");
            appendDescriptor.visitJumpInsn(Opcodes.IF_ACMPNE,L10);
            appendDescriptor.visitIntInsn(Opcodes.BIPUSH,68);
            appendDescriptor.visitVarInsn(Opcodes.ISTORE,3);
            appendDescriptor.visitJumpInsn(Opcodes.GOTO,L4);
            appendDescriptor.visitLabel(L10);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            appendDescriptor.visitFieldInsn(Opcodes.GETSTATIC,Type.getInternalName(Float.class),"TYPE","Ljava/lang/Class;");
            appendDescriptor.visitJumpInsn(Opcodes.IF_ACMPNE,L11);
            appendDescriptor.visitIntInsn(Opcodes.BIPUSH,70);
            appendDescriptor.visitVarInsn(Opcodes.ISTORE,3);
            appendDescriptor.visitJumpInsn(Opcodes.GOTO,L4);
            appendDescriptor.visitLabel(L11);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            appendDescriptor.visitFieldInsn(Opcodes.GETSTATIC,Type.getInternalName(Long.class),"TYPE","Ljava/lang/Class;");
            appendDescriptor.visitJumpInsn(Opcodes.IF_ACMPNE,L12);
            appendDescriptor.visitIntInsn(Opcodes.BIPUSH,74);
            appendDescriptor.visitVarInsn(Opcodes.ISTORE,3);
            appendDescriptor.visitJumpInsn(Opcodes.GOTO,L4);
            appendDescriptor.visitLabel(L12);
            appendDescriptor.visitTypeInsn(Opcodes.NEW,Type.getInternalName(AssertionError.class));
            appendDescriptor.visitInsn(Opcodes.DUP);
            appendDescriptor.visitMethodInsn(Opcodes.INVOKESPECIAL,Type.getInternalName(AssertionError.class),"<init>","()V");
            appendDescriptor.visitInsn(Opcodes.ATHROW);
            appendDescriptor.visitLabel(L4);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,1);
            appendDescriptor.visitVarInsn(Opcodes.ILOAD,3);
            appendDescriptor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"java/lang/StringBuilder","append","(C)Ljava/lang/StringBuilder;");
            appendDescriptor.visitInsn(Opcodes.POP);
            appendDescriptor.visitJumpInsn(Opcodes.GOTO,L13);
            appendDescriptor.visitLabel(L2);
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,1);
            appendDescriptor.visitIntInsn(Opcodes.BIPUSH,76);
            appendDescriptor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"java/lang/StringBuilder","append","(C)Ljava/lang/StringBuilder;");
            appendDescriptor.visitVarInsn(Opcodes.ALOAD,2);
            appendDescriptor.visitMethodInsn(Opcodes.INVOKESTATIC,vmName,getInternalNameName,"(Ljava/lang/Class;)Ljava/lang/String;");
            appendDescriptor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"java/lang/StringBuilder","append","(Ljava/lang/String;)Ljava/lang/StringBuilder;");
            appendDescriptor.visitIntInsn(Opcodes.BIPUSH,59);
            appendDescriptor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"java/lang/StringBuilder","append","(C)Ljava/lang/StringBuilder;");
            appendDescriptor.visitInsn(Opcodes.POP);
            appendDescriptor.visitLabel(L13);
            appendDescriptor.visitInsn(Opcodes.RETURN);

        }
        {
            MethodVisitor getInternalName = cw.visitMethod(Opcodes.ACC_PRIVATE+Opcodes.ACC_STATIC,getInternalNameName,"(Ljava/lang/Class;)Ljava/lang/String;",null,new String[]{});
            getInternalName.visitVarInsn(Opcodes.ALOAD,0);
            getInternalName.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"java/lang/Class","getName","()Ljava/lang/String;");
            getInternalName.visitLdcInsn(46);
            getInternalName.visitLdcInsn(47);
            getInternalName.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"java/lang/String","replace","(CC)Ljava/lang/String;");
            getInternalName.visitInsn(Opcodes.ARETURN);
        }
        {
            MethodVisitor invokeVirtualMachine = cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC+Opcodes.ACC_VARARGS,invokeVirtualMachineName,"(I[Ljava/lang/Object;)Ljava/lang/Object;",null,new String[]{});

            Label L0 = new Label();
            Label L1 = new Label();
            Label L2 = new Label();
            //    ILOAD 0
            //    ISTORE 2
            //    NEW java/util/concurrent/ConcurrentLinkedQueue
            //    DUP
            //    INVOKESPECIAL java/util/concurrent/ConcurrentLinkedQueue.<init> ()V
            //    ASTORE 3
            //    NEW java/util/HashMap
            //    DUP
            //    INVOKESPECIAL java/util/HashMap.<init> ()V
            //    ASTORE 4
            //    ICONST_0
            //    ISTORE 5
            //   L0
            invokeVirtualMachine.visitVarInsn(Opcodes.ILOAD,0);
            invokeVirtualMachine.visitVarInsn(Opcodes.ISTORE,2);
            invokeVirtualMachine.visitTypeInsn(Opcodes.NEW,Type.getInternalName(ConcurrentLinkedQueue.class));
            invokeVirtualMachine.visitInsn(Opcodes.DUP);
            invokeVirtualMachine.visitMethodInsn(Opcodes.INVOKESPECIAL,Type.getInternalName(ConcurrentLinkedQueue.class),"<init>","()V");
            invokeVirtualMachine.visitVarInsn(Opcodes.ASTORE,3);
            invokeVirtualMachine.visitTypeInsn(Opcodes.NEW,Type.getInternalName(HashMap.class));
            invokeVirtualMachine.visitInsn(Opcodes.DUP);
            invokeVirtualMachine.visitMethodInsn(Opcodes.INVOKESPECIAL,Type.getInternalName(HashMap.class),"<init>","()V");
            invokeVirtualMachine.visitVarInsn(Opcodes.ASTORE,4);
            invokeVirtualMachine.visitInsn(Opcodes.ICONST_0);
            invokeVirtualMachine.visitVarInsn(Opcodes.ISTORE,5);
            invokeVirtualMachine.visitLabel(L0);
            //    ILOAD 5
            //    ALOAD 1
            //    ARRAYLENGTH
            //    IF_ICMPGE L1
            //    ALOAD 4
            //    ILOAD 5
            //    INVOKESTATIC java/lang/Integer.valueOf (I)Ljava/lang/Integer;
            //    ALOAD 1
            //    ILOAD 5
            //    AALOAD
            //    INVOKEINTERFACE java/util/Map.put (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; (itf)
            //    POP
            //    IINC 5 1
            //    GOTO L0
            //   L1
            invokeVirtualMachine.visitVarInsn(Opcodes.ILOAD,5);
            invokeVirtualMachine.visitVarInsn(Opcodes.ALOAD,1);
            invokeVirtualMachine.visitInsn(Opcodes.ARRAYLENGTH);
            invokeVirtualMachine.visitJumpInsn(Opcodes.IF_ICMPGE,L1);
            invokeVirtualMachine.visitVarInsn(Opcodes.ALOAD,4);
            invokeVirtualMachine.visitVarInsn(Opcodes.ILOAD,5);
            invokeVirtualMachine.visitMethodInsn(Opcodes.INVOKESTATIC,"java/lang/Integer","valueOf","(I)Ljava/lang/Integer;");
            invokeVirtualMachine.visitVarInsn(Opcodes.ALOAD,1);
            invokeVirtualMachine.visitVarInsn(Opcodes.ILOAD,5);
            invokeVirtualMachine.visitInsn(Opcodes.AALOAD);
            invokeVirtualMachine.visitMethodInsn(Opcodes.INVOKEINTERFACE,"java/util/Map","put","(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
            invokeVirtualMachine.visitInsn(Opcodes.POP);
            invokeVirtualMachine.visitIincInsn(5,1);
            invokeVirtualMachine.visitJumpInsn(Opcodes.GOTO,L0);
            invokeVirtualMachine.visitLabel(L1);
            //    ILOAD 2
            //    ICONST_M1
            //    IF_ICMPEQ L2
            //    ILOAD 2
            //    ALOAD 3
            //    ALOAD 4
            //    INVOKESTATIC dev/qingwan/crater/Crater.ADD_I (ILjava/util/concurrent/ConcurrentLinkedQueue;Ljava/util/Map;)I
            //    ISTORE 2
            //    ILOAD 2
            //    BIPUSH -2
            //    IF_ICMPNE L1
            //    NEW java/lang/RuntimeException
            //    DUP
            //    LDC "Op code cannot be process"
            //    INVOKESPECIAL java/lang/RuntimeException.<init> (Ljava/lang/String;)V
            //    ATHROW
            //   L2
            //   FRAME SAME
            //    ALOAD 3
            //    INVOKEVIRTUAL java/util/concurrent/ConcurrentLinkedQueue.poll ()Ljava/lang/Object;
            //    ARETURN
            invokeVirtualMachine.visitVarInsn(Opcodes.ILOAD,2);
            invokeVirtualMachine.visitInsn(Opcodes.ICONST_M1);
            invokeVirtualMachine.visitJumpInsn(Opcodes.IF_ICMPEQ,L2);
            invokeVirtualMachine.visitVarInsn(Opcodes.ILOAD,2);
            invokeVirtualMachine.visitVarInsn(Opcodes.ALOAD,3);
            invokeVirtualMachine.visitVarInsn(Opcodes.ALOAD,4);
            invokeVirtualMachine.visitMethodInsn(Opcodes.INVOKESTATIC,vmName,generateProcessors(cw),"(ILjava/util/concurrent/ConcurrentLinkedQueue;Ljava/util/Map;)I");
            invokeVirtualMachine.visitVarInsn(Opcodes.ISTORE,2);
            invokeVirtualMachine.visitVarInsn(Opcodes.ILOAD,2);
            invokeVirtualMachine.visitIntInsn(Opcodes.BIPUSH,-2);
            invokeVirtualMachine.visitJumpInsn(Opcodes.IF_ICMPNE,L1);
            invokeVirtualMachine.visitTypeInsn(Opcodes.NEW,Type.getInternalName(RuntimeException.class));
            invokeVirtualMachine.visitInsn(Opcodes.DUP);
            invokeVirtualMachine.visitLdcInsn("Op code cannot be process");
            invokeVirtualMachine.visitMethodInsn(Opcodes.INVOKESPECIAL,Type.getInternalName(RuntimeException.class),"<init>","(Ljava/lang/String;)V");
            invokeVirtualMachine.visitInsn(Opcodes.ATHROW);
            invokeVirtualMachine.visitLabel(L2);
            invokeVirtualMachine.visitVarInsn(Opcodes.ALOAD,3);
            invokeVirtualMachine.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(ConcurrentLinkedQueue.class),"poll","()Ljava/lang/Object;");
            invokeVirtualMachine.visitInsn(Opcodes.ARETURN);

        }

        ClassReader cr2 = new ClassReader(cw.toByteArray());
        ClassWriter cw2 = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cr2.accept(cw2,0);


        processProvider.addClass(vmName,cw.toByteArray());
    }

    /**
     * Generate instruction processors
     * @param cw Target virtual machine
     * @return The name of the first generated processor
     */
    private String generateProcessors(ClassWriter cw){
        List<Map.Entry<VMCodes,Byte>> shuffledEntries = codes.entrySet().stream().collect(Collectors.toList());
        Collections.shuffle(shuffledEntries);
        Queue<Map.Entry<VMCodes,Byte>> queue = new ConcurrentLinkedQueue<>(shuffledEntries);
        String lastProcessor = null;
        while (!queue.isEmpty()){
             lastProcessor = generateProcessor(cw,queue.poll(),lastProcessor);
        }
        return lastProcessor;
    }
    private String generateProcessor(ClassWriter cw,Map.Entry entry,String lastProcessor){
        String processorName = createRandomString(15);
        MethodVisitor processor = cw.visitMethod(Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC,processorName,"(ILjava/util/concurrent/ConcurrentLinkedQueue;Ljava/util/Map;)I",null,null);
        Label L0 = new Label();
        //    GETSTATIC dev/qingwan/crater/Crater.codeMap : [B
        //    ILOAD 0
        //    BALOAD
        //    ICONST_1
        //    IF_ICMPNE L0
        processor.visitFieldInsn(Opcodes.GETSTATIC,vmName,codeMap,"[B");
        processor.visitVarInsn(Opcodes.ILOAD,0);
        processor.visitInsn(Opcodes.BALOAD);
        processor.visitIntInsn(Opcodes.BIPUSH,(Byte)(entry.getValue()));
        processor.visitJumpInsn(Opcodes.IF_ICMPNE,L0);
        switch ((VMCodes) entry.getKey()){
            case ADD_I:

            default:
                break;
        }
        processor.visitLabel(L0);
        if (lastProcessor == null){
            processor.visitIntInsn(Opcodes.BIPUSH,-2);
            processor.visitInsn(Opcodes.IRETURN);
        }else {
            //    ILOAD 0
            //    ALOAD 1
            //    ALOAD 2
            //    INVOKESTATIC dev/qingwan/crater/Crater.otherParser (ILjava/util/concurrent/ConcurrentLinkedQueue;Ljava/util/Map;)I
            //    IRETURN
            processor.visitVarInsn(Opcodes.ILOAD,0);
            processor.visitVarInsn(Opcodes.ALOAD,1);
            processor.visitVarInsn(Opcodes.ALOAD,2);
            processor.visitMethodInsn(Opcodes.INVOKESTATIC,vmName,lastProcessor,"(ILjava/util/concurrent/ConcurrentLinkedQueue;Ljava/util/Map;)I");
            processor.visitInsn(Opcodes.IRETURN);
        }
        return processorName;
    }
    private void postProcessOriginClass(){
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        FieldNode vmField = new FieldNode(Opcodes.ACC_PRIVATE,createRandomString(30),"L"+vmName+";",null,null);
        cn.fields.add(vmField);
        if (!hasMethod(cn,"<clinit>")){
            MethodNode clinit = new MethodNode(Opcodes.ACC_STATIC,"<clinit>","()V",null,null);
            clinit.instructions.add(new InsnNode(Opcodes.RETURN));
            cn.methods.add(clinit);
        }
        if (!hasMethod(cn,"<init>")){
            MethodNode init = new MethodNode(Opcodes.ACC_PUBLIC,"<init>","()V",null,null);
            init.instructions.add(new InsnNode(Opcodes.RETURN));
            cn.methods.add(init);
        }
        for (MethodNode method : cn.methods) {
            if (method.name.equals("<clinit>")){
                //ToDo: Virtualize <clinit>
                InsnList newInsn = new InsnList();
                for (AbstractInsnNode instruction : method.instructions) {
                    if (instruction.getOpcode() == Opcodes.RETURN){
                        //    NEW dev/qingwan/crater/vm/ExampleVirtualMachine
                        //    DUP
                        //    INVOKESPECIAL dev/qingwan/crater/vm/ExampleVirtualMachine.<init> ()V
                        //    PUTFIELD dev/qingwan/crater/test/CraterTest.vm : Ldev/qingwan/crater/vm/ExampleVirtualMachine;
                        newInsn.add(new TypeInsnNode(Opcodes.NEW,vmName));
                        newInsn.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,vmName,"<init>","()V"));
                    }
                    newInsn.add(instruction);
                }
                method.instructions = newInsn;
            }
            if (method.name.equals("<init>")){
                //ToDo: Virtualize <init>
                //ToDo: Support multi <init>
                InsnList newInsn = new InsnList();
                for (AbstractInsnNode instruction : method.instructions) {
                    if (instruction.getOpcode() == Opcodes.RETURN){
                        //    NEW dev/qingwan/crater/vm/ExampleVirtualMachine
                        //    DUP
                        //    INVOKESPECIAL dev/qingwan/crater/vm/ExampleVirtualMachine.<init> ()V
                        //    PUTFIELD dev/qingwan/crater/test/CraterTest.vm : Ldev/qingwan/crater/vm/ExampleVirtualMachine;
                        newInsn.add(new VarInsnNode(Opcodes.ALOAD,0));
                        newInsn.add(new TypeInsnNode(Opcodes.NEW,vmName));
                        newInsn.add(new InsnNode(Opcodes.DUP));
                        newInsn.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,vmName,"<init>","()V"));
                        newInsn.add(new FieldInsnNode(Opcodes.PUTFIELD,cn.name,vmField.name,"L"+vmName+";"));
                    }
                    newInsn.add(instruction);
                }
                method.instructions = newInsn;
            }

            if (!method.name.equals("<clinit>") && !method.name.equals("<init>")){
                InsnList overwrite = new InsnList();
                overwrite.add(new InsnNode(Opcodes.RETURN));
            }
        }
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        classBytes = cw.toByteArray();
    }
    private static boolean hasMethod(ClassNode cn,String name,String desc){
        for (MethodNode method : cn.methods) {
            if (method.name.equals(name) && method.desc.equals(desc)){
                return true;
            }
        }
        return false;
    }
    private static boolean hasMethod(ClassNode cn,String name){
        for (MethodNode method : cn.methods) {
            if (method.name.equals(name)){
                return true;
            }
        }
        return false;
    }

    private static String createRandomString(int length){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        //str = "MWN";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }
}
