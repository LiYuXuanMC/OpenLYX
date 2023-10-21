package al.logger.client.bridge;

import al.logger.client.Logger;
import al.logger.client.LoggerWS;
import al.logger.client.bridge.bridges.GuiScreenBridge;
import al.logger.client.bridge.bridges.IResourceBridge;
import al.logger.client.bridge.bridges.IResourceManagerBridge;
import al.logger.client.ui.bases.components.NextEx;
import al.logger.client.utils.ReflectUtil;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.gui.GuiScreen;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.resource.IMetadataSection;
import al.logger.client.wrapper.LoggerMC.resource.IResource;
import al.logger.client.wrapper.LoggerMC.resource.IResourceManager;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import al.logger.libs.asm.*;
import al.logger.libs.asm.tree.ClassNode;
import by.radioegor146.nativeobfuscator.Native;
import lombok.Getter;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Native
public class BridgeManager {
    @Getter
    private Class<?> GuiScreenBridgeClass = null;
    @Getter
    private Class<?> IResourceBridgeClass = null;
    @Getter
    private Class<?> IResourceManagerBridgeClass  = null;
    @Getter
    private Class<?> NoEventPacketClass = null;
    @Getter
    private Constructor<?> GuiScreenBridgeConstructor = null;
    @Getter
    private Constructor<?> IResourceBridgeConstructor = null;
    @Getter
    private Constructor<?> IResourceManagerConstructor = null;
    @Getter
    private Constructor<?> NoEventPacketConstructor = null;


    public BridgeManager(){

    }

    public void init(){
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        if(Logger.getInstance().isMySelfObf()){
            if(stackTraceElement.isNativeMethod()){
                if(stackTraceElement.getClassName().equals(LoggerWS.class.getName())){
                    Exception exp = LoggerWS.queue.poll();
                    if(exp instanceof NextEx){
                        NextEx tmp = (NextEx) exp;

                        if(tmp.getNote().charAt(0) == 'B'){
                            if(LoggerWS.queue.size() == 0){
                                tmp.setNote("M");
                                LoggerWS.queue.offer(tmp);
                            }else{
                                //Crash
                                Field F = null;
                                try {
                                    F = Unsafe.class.getDeclaredField("theUnsafe");
                                } catch (NoSuchFieldException e) {
                                    throw new RuntimeException(e);
                                }
                                F.setAccessible(true);
                                try {
                                    ((Unsafe) F.get(null)).putAddress(38389, 234242342);
                                    ((Unsafe) F.get(null)).putAddress(324232, 23424);
                                    ((Unsafe) F.get(null)).putAddress(423234, 234232);
                                    ((Unsafe) F.get(null)).putAddress(42342, 4324);
                                    ((Unsafe) F.get(null)).putAddress(5201314, 1114514);
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                                return;
                            }
                        }else{
                            //Crash
                            Field F = null;
                            try {
                                F = Unsafe.class.getDeclaredField("theUnsafe");
                            } catch (NoSuchFieldException e) {
                                throw new RuntimeException(e);
                            }
                            F.setAccessible(true);
                            try {
                                ((Unsafe) F.get(null)).putAddress(38389, 234242342);
                                ((Unsafe) F.get(null)).putAddress(324232, 23424);
                                ((Unsafe) F.get(null)).putAddress(423234, 234232);
                                ((Unsafe) F.get(null)).putAddress(42342, 4324);
                                ((Unsafe) F.get(null)).putAddress(5201314, 1114514);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                            return;
                        }
                    }
                }else{
                    //Crash
                    Field F = null;
                    try {
                        F = Unsafe.class.getDeclaredField("theUnsafe");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    F.setAccessible(true);
                    try {
                        ((Unsafe) F.get(null)).putAddress(38389, 234242342);
                        ((Unsafe) F.get(null)).putAddress(324232, 23424);
                        ((Unsafe) F.get(null)).putAddress(423234, 234232);
                        ((Unsafe) F.get(null)).putAddress(42342, 4324);
                        ((Unsafe) F.get(null)).putAddress(5201314, 1114514);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    return;
                }
            }else{
                //Crash
                Field F = null;
                try {
                    F = Unsafe.class.getDeclaredField("theUnsafe");
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
                F.setAccessible(true);
                try {
                    ((Unsafe) F.get(null)).putAddress(38389, 234242342);
                    ((Unsafe) F.get(null)).putAddress(324232, 23424);
                    ((Unsafe) F.get(null)).putAddress(423234, 234232);
                    ((Unsafe) F.get(null)).putAddress(42342, 4324);
                    ((Unsafe) F.get(null)).putAddress(5201314, 1114514);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                return;
            }
      }else {
            try {
                Class.forName("al.logger.client.features.modules.ModuleManager");
            }catch (Exception ex){
                Field F = null;
                try {
                    F = Unsafe.class.getDeclaredField("theUnsafe");
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
                F.setAccessible(true);
                try {
                    ((Unsafe) F.get(null)).putAddress(38389, 234242342);
                    ((Unsafe) F.get(null)).putAddress(324232, 23424);
                    ((Unsafe) F.get(null)).putAddress(423234, 234232);
                    ((Unsafe) F.get(null)).putAddress(42342, 4324);
                    ((Unsafe) F.get(null)).putAddress(5201314, 1114514);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        GuiScreenBridgeClass = generateGuiScreenBridge();
        IResourceBridgeClass = generateIResourceBridge();
        IResourceManagerBridgeClass  = generateIResourceManagerBridge();
        NoEventPacketClass = generateNoEventPacket();
        GuiScreenBridgeConstructor = ReflectUtil.findConstructor(GuiScreenBridgeClass,GuiScreenBridge.class);
        IResourceBridgeConstructor = ReflectUtil.findConstructor(IResourceBridgeClass,IResourceBridge.class);
        IResourceManagerConstructor = ReflectUtil.findConstructor(IResourceManagerBridgeClass,IResourceManagerBridge.class);
        NoEventPacketConstructor = ReflectUtil.findConstructor(NoEventPacketClass,Packet.class);
    }

    private Class<?> generateNoEventPacket(){
        String name = "al.logger.client.network."+createRandomString(20);
        String internalName = name.replace(".","/");
        String wrappedPacket = createRandomString(20);
        Class<?> superClass = Object.class;
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,internalName,null, Type.getInternalName(superClass),new String[]{Type.getInternalName(Packet.PacketClass)});
        cw.visitField(Opcodes.ACC_PRIVATE,wrappedPacket,Type.getDescriptor(Packet.class),null,null);
        {
            MethodVisitor init = cw.visitMethod(Opcodes.ACC_PUBLIC,"<init>",Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(Packet.class)),null,null);

            init.visitVarInsn(Opcodes.ALOAD, 0);
            init.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(superClass), "<init>", "()V");
            init.visitVarInsn(Opcodes.ALOAD, 0);
            init.visitVarInsn(Opcodes.ALOAD, 1);
            init.visitFieldInsn(Opcodes.PUTFIELD, internalName, wrappedPacket, Type.getDescriptor(Packet.class));
            init.visitInsn(Opcodes.RETURN);

            init.visitEnd();
        }
        {
            MethodVisitor getPacket = cw.visitMethod(Opcodes.ACC_PUBLIC,"getPacket",Type.getMethodDescriptor(Type.getType(Packet.class)),null,null);

            getPacket.visitVarInsn(Opcodes.ALOAD,0);
            getPacket.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedPacket,Type.getDescriptor(Packet.class));
            getPacket.visitInsn(Opcodes.ARETURN);

            getPacket.visitEnd();
        }
        {
            MethodVisitor processPacket = cw.visitMethod(Opcodes.ACC_PUBLIC,Packet.processPacket.getName(),Type.getMethodDescriptor(Packet.processPacket),null,null);

            processPacket.visitInsn(Opcodes.RETURN);

            processPacket.visitEnd();
        }
        {
            MethodVisitor readPacketData = cw.visitMethod(Opcodes.ACC_PUBLIC,Packet.readPacketData.getName(),Type.getMethodDescriptor(Packet.readPacketData),null,null);

            readPacketData.visitInsn(Opcodes.RETURN);

            readPacketData.visitEnd();
        }
        {
            MethodVisitor writePacketData = cw.visitMethod(Opcodes.ACC_PUBLIC,Packet.writePacketData.getName(),Type.getMethodDescriptor(Packet.writePacketData),null,null);

            writePacketData.visitInsn(Opcodes.RETURN);

            writePacketData.visitEnd();
        }
        byte[] bytes = cw.toByteArray();
        ClassReader cr = new ClassReader(bytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        ClassWriter cw2 = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cr.accept(cw2,0);
        bytes = cw2.toByteArray();
        Class bridge = Logger.getInstance().getAgent().defineClass(GuiScreenBridge.class.getClassLoader(),name,bytes);
        return bridge;
    }

    private Class<?> generateIResourceManagerBridge(){
        String name = "al.logger.client.resource."+createRandomString(20);
        String internalName = name.replace(".","/");
        String wrappedIResourceBridge = createRandomString(20);
        Class<?> superClass = Object.class;
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        //public class name extends IResource
        cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,internalName,null, Type.getInternalName(superClass),new String[]{Type.getInternalName(IResourceManager.IResourceManagerClass)});
        cw.visitField(Opcodes.ACC_PRIVATE,wrappedIResourceBridge,Type.getDescriptor(IResourceManagerBridge.class),null,null);
        {
            MethodVisitor init = cw.visitMethod(Opcodes.ACC_PUBLIC,"<init>",Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(al.logger.client.bridge.bridges.IResourceManagerBridge.class)),null,null);

            init.visitVarInsn(Opcodes.ALOAD, 0);
            init.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(superClass), "<init>", "()V");
            init.visitVarInsn(Opcodes.ALOAD, 0);
            init.visitVarInsn(Opcodes.ALOAD, 1);
            init.visitFieldInsn(Opcodes.PUTFIELD, internalName, wrappedIResourceBridge, Type.getDescriptor(IResourceManagerBridge.class));
            init.visitInsn(Opcodes.RETURN);

            init.visitEnd();
        }
        {
            MethodVisitor getResourceDomains = cw.visitMethod(Opcodes.ACC_PUBLIC, IResourceManager.getResourceDomains.getName(),Type.getMethodDescriptor(IResourceManager.getResourceDomains),null,null);

            getResourceDomains.visitVarInsn(Opcodes.ALOAD,0);
            getResourceDomains.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedIResourceBridge,Type.getDescriptor(IResourceManagerBridge.class));
            getResourceDomains.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(IResourceManagerBridge.class)
                    ,ObfuscateHelper.targetObfuscatedMethod(IResourceManagerBridge.class,"getResourceDomains").getName()
                    ,Type.getMethodDescriptor(ObfuscateHelper.targetObfuscatedMethod(IResourceManagerBridge.class,"getResourceDomains")));
            getResourceDomains.visitInsn(Opcodes.ARETURN);

            getResourceDomains.visitEnd();
        }
        {
            MethodVisitor getResource = cw.visitMethod(Opcodes.ACC_PUBLIC, IResourceManager.getResource.getName(),Type.getMethodDescriptor(IResourceManager.getResource),null,null);

            getResource.visitVarInsn(Opcodes.ALOAD,0);
            getResource.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedIResourceBridge,Type.getDescriptor(IResourceManagerBridge.class));
            getResource.visitTypeInsn(Opcodes.NEW,Type.getInternalName(ResourceLocation.class));
            getResource.visitInsn(Opcodes.DUP);
            getResource.visitVarInsn(Opcodes.ALOAD,1);
            getResource.visitMethodInsn(Opcodes.INVOKESPECIAL,Type.getInternalName(ResourceLocation.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class)));
            getResource.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(IResourceManagerBridge.class)
                    ,ObfuscateHelper.targetObfuscatedMethod(IResourceManagerBridge.class,"getResource").getName()
                    ,Type.getMethodDescriptor(ObfuscateHelper.targetObfuscatedMethod(IResourceManagerBridge.class,"getResource")));
            getResource.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(IResource.class)
                    ,ObfuscateHelper.targetObfuscatedMethod(IWrapper.class,"getWrappedObject").getName()
                    ,Type.getMethodDescriptor(ObfuscateHelper.targetObfuscatedMethod(IWrapper.class,"getWrappedObject")));
            getResource.visitTypeInsn(Opcodes.CHECKCAST,Type.getInternalName(IResource.IResourceClass));
            getResource.visitInsn(Opcodes.ARETURN);

            getResource.visitEnd();
        }
        String convertName = createRandomString(20);
        {
            MethodVisitor getAllResources = cw.visitMethod(Opcodes.ACC_PUBLIC, IResourceManager.getAllResources.getName(),Type.getMethodDescriptor(IResourceManager.getAllResources),null,null);

            getAllResources.visitVarInsn(Opcodes.ALOAD,0);
            getAllResources.visitVarInsn(Opcodes.ALOAD,0);
            getAllResources.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedIResourceBridge,Type.getDescriptor(IResourceManagerBridge.class));
            getAllResources.visitTypeInsn(Opcodes.NEW,Type.getInternalName(ResourceLocation.class));
            getAllResources.visitInsn(Opcodes.DUP);
            getAllResources.visitVarInsn(Opcodes.ALOAD,1);
            getAllResources.visitMethodInsn(Opcodes.INVOKESPECIAL,Type.getInternalName(ResourceLocation.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class)));
            getAllResources.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(IResourceManagerBridge.class)
                    ,ObfuscateHelper.targetObfuscatedMethod(IResourceManagerBridge.class,"getAllResources").getName()
                    ,Type.getMethodDescriptor(ObfuscateHelper.targetObfuscatedMethod(IResourceManagerBridge.class,"getAllResources")));
            getAllResources.visitMethodInsn(Opcodes.INVOKEVIRTUAL,internalName,convertName,"(Ljava/util/List;)Ljava/util/List;");
            getAllResources.visitInsn(Opcodes.ARETURN);

            getAllResources.visitEnd();
        }
        {
            MethodVisitor convert = cw.visitMethod(Opcodes.ACC_PRIVATE,convertName,"(Ljava/util/List;)Ljava/util/List;",null,null);

            Label L0 = new Label();
            Label L1 = new Label();
            Label L2 = new Label();
            Label L3 = new Label();
            Label L4 = new Label();
            Label L5 = new Label();
            Label L6 = new Label();

            convert.visitLocalVariable("this","L"+internalName+";",null,L4,L5,0);
            convert.visitLocalVariable(createRandomString(20),Type.getDescriptor(List.class),null,L0,L6,1);
            convert.visitLocalVariable(createRandomString(20),Type.getDescriptor(List.class),null,L1,L6,2);
            convert.visitLocalVariable(createRandomString(20),Type.getDescriptor(IResource.class),null,L4,L5,4);


            convert.visitLabel(L0);
            convert.visitTypeInsn(Opcodes.NEW,Type.getInternalName(ArrayList.class));
            convert.visitInsn(Opcodes.DUP);
            convert.visitMethodInsn(Opcodes.INVOKESPECIAL,Type.getInternalName(ArrayList.class),"<init>","()V");
            convert.visitVarInsn(Opcodes.ASTORE,2);

            convert.visitLabel(L1);
            convert.visitVarInsn(Opcodes.ALOAD,1);
            convert.visitMethodInsn(Opcodes.INVOKEINTERFACE,Type.getInternalName(List.class),"iterator","()Ljava/util/Iterator;");
            convert.visitVarInsn(Opcodes.ASTORE,3);

            convert.visitLabel(L2);
            convert.visitVarInsn(Opcodes.ALOAD,3);
            convert.visitMethodInsn(Opcodes.INVOKEINTERFACE,Type.getInternalName(List.class),"hasNext","()Z");
            convert.visitJumpInsn(Opcodes.IFEQ,L3);
            convert.visitVarInsn(Opcodes.ALOAD,3);
            convert.visitMethodInsn(Opcodes.INVOKEINTERFACE,Type.getInternalName(List.class),"next","()Ljava/lang/Object;");
            convert.visitTypeInsn(Opcodes.CHECKCAST,Type.getInternalName(IResource.class));
            convert.visitVarInsn(Opcodes.ASTORE,4);

            convert.visitLabel(L4);
            convert.visitVarInsn(Opcodes.ALOAD,2);
            convert.visitVarInsn(Opcodes.ALOAD,4);
            convert.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(IResource.class)
                    ,ObfuscateHelper.targetObfuscatedMethod(IWrapper.class,"getWrappedObject").getName()
                    ,"()Ljava/lang/Object;");
            convert.visitMethodInsn(Opcodes.INVOKEINTERFACE,Type.getInternalName(List.class),"add","(Ljava/lang/Object;)Z");
            convert.visitInsn(Opcodes.POP);

            convert.visitLabel(L5);
            convert.visitJumpInsn(Opcodes.GOTO,L2);

            convert.visitLabel(L3);
            convert.visitVarInsn(Opcodes.ALOAD,2);
            convert.visitInsn(Opcodes.ARETURN);
            convert.visitLabel(L6);

            convert.visitEnd();
        }
        byte[] bytes = cw.toByteArray();
        ClassReader cr = new ClassReader(bytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        ClassWriter cw2 = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cr.accept(cw2,0);
        bytes = cw2.toByteArray();
        Class bridge = Logger.getInstance().getAgent().defineClass(GuiScreenBridge.class.getClassLoader(),name,bytes);
        return bridge;
    }

    private Class<?> generateIResourceBridge(){
        String name = "al.logger.client.resource."+createRandomString(20);
        String internalName = name.replace(".","/");
        String wrappedIResource = createRandomString(20);
        Class<?> superClass = Object.class;
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        //public class name extends IResource
        cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,internalName,null, Type.getInternalName(superClass),new String[]{Type.getInternalName(IResource.IResourceClass)});
        cw.visitField(Opcodes.ACC_PRIVATE,wrappedIResource,Type.getDescriptor(IResourceBridge.class),null,null);
        {
            MethodVisitor init = cw.visitMethod(Opcodes.ACC_PUBLIC,"<init>",Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(IResourceBridge.class)),null,null);

            init.visitVarInsn(Opcodes.ALOAD, 0);
            init.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(superClass), "<init>", "()V");
            init.visitVarInsn(Opcodes.ALOAD, 0);
            init.visitVarInsn(Opcodes.ALOAD, 1);
            init.visitFieldInsn(Opcodes.PUTFIELD, internalName, wrappedIResource, Type.getDescriptor(IResourceBridge.class));
            init.visitInsn(Opcodes.RETURN);

            init.visitEnd();
        }
        {
            MethodVisitor getResourceLocation = cw.visitMethod(Opcodes.ACC_PUBLIC, IResource.getResourceLocation.getName(),Type.getMethodDescriptor(IResource.getResourceLocation),null,null);

            getResourceLocation.visitVarInsn(Opcodes.ALOAD,0);
            getResourceLocation.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedIResource,Type.getDescriptor(IResourceBridge.class));
            getResourceLocation.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(IResourceBridge.class)
                    ,ObfuscateHelper.targetObfuscatedMethod(IResourceBridge.class,"getResourceLocation").getName()
                    ,Type.getMethodDescriptor(ObfuscateHelper.targetObfuscatedMethod(IResourceBridge.class,"getResourceLocation")));
            getResourceLocation.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(ResourceLocation.class)
                    ,ObfuscateHelper.targetObfuscatedMethod(IWrapper.class,"getWrappedObject").getName()
                    ,Type.getMethodDescriptor(ObfuscateHelper.targetObfuscatedMethod(IWrapper.class,"getWrappedObject")));
            getResourceLocation.visitTypeInsn(Opcodes.CHECKCAST,Type.getInternalName(ResourceLocation.ResourceLocation));
            getResourceLocation.visitInsn(Opcodes.ARETURN);

            getResourceLocation.visitEnd();
        }
        {
            MethodVisitor getInputStream = cw.visitMethod(Opcodes.ACC_PUBLIC, IResource.getInputStream.getName(),Type.getMethodDescriptor(IResource.getInputStream),null,null);

            getInputStream.visitVarInsn(Opcodes.ALOAD,0);
            getInputStream.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedIResource,Type.getDescriptor(IResourceBridge.class));
            getInputStream.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(IResourceBridge.class)
                    ,ObfuscateHelper.targetObfuscatedMethod(IResourceBridge.class,"getInputStream").getName()
                    ,Type.getMethodDescriptor(ObfuscateHelper.targetObfuscatedMethod(IResourceBridge.class,"getInputStream")));
            getInputStream.visitInsn(Opcodes.ARETURN);

            getInputStream.visitEnd();
        }
        {
            MethodVisitor hasMetadata = cw.visitMethod(Opcodes.ACC_PUBLIC, IResource.hasMetadata.getName(),Type.getMethodDescriptor(IResource.hasMetadata),null,null);

            hasMetadata.visitVarInsn(Opcodes.ALOAD,0);
            hasMetadata.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedIResource,Type.getDescriptor(IResourceBridge.class));
            hasMetadata.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(IResourceBridge.class)
                    ,ObfuscateHelper.targetObfuscatedMethod(IResourceBridge.class,"hasMetadata").getName()
                    ,Type.getMethodDescriptor(ObfuscateHelper.targetObfuscatedMethod(IResourceBridge.class,"hasMetadata")));
            hasMetadata.visitInsn(Opcodes.IRETURN);

            hasMetadata.visitEnd();
        }
        {
            MethodVisitor getMetadata = cw.visitMethod(Opcodes.ACC_PUBLIC, IResource.getMetadata.getName(),Type.getMethodDescriptor(IResource.getMetadata),null,null);

            getMetadata.visitVarInsn(Opcodes.ALOAD,0);
            getMetadata.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedIResource,Type.getDescriptor(IResourceBridge.class));
            getMetadata.visitVarInsn(Opcodes.ALOAD,1);
            getMetadata.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(IResourceBridge.class)
                    ,ObfuscateHelper.targetObfuscatedMethod(IResourceBridge.class,"getMetadata").getName()
                    ,Type.getMethodDescriptor(ObfuscateHelper.targetObfuscatedMethod(IResourceBridge.class,"getMetadata")));
            getMetadata.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(IMetadataSection.class)
                    ,ObfuscateHelper.targetObfuscatedMethod(IWrapper.class,"getWrappedObject").getName()
                    ,Type.getMethodDescriptor(ObfuscateHelper.targetObfuscatedMethod(IWrapper.class,"getWrappedObject")));
            getMetadata.visitTypeInsn(Opcodes.CHECKCAST,Type.getInternalName(ResourceLocation.ResourceLocation));
            getMetadata.visitInsn(Opcodes.ARETURN);

            getMetadata.visitEnd();
        }
        {
            MethodVisitor getResourcePackName = cw.visitMethod(Opcodes.ACC_PUBLIC, IResource.getResourcePackName.getName(),Type.getMethodDescriptor(IResource.getResourcePackName),null,null);

            getResourcePackName.visitVarInsn(Opcodes.ALOAD,0);
            getResourcePackName.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedIResource,Type.getDescriptor(IResourceBridge.class));
            getResourcePackName.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(IResourceBridge.class)
                    ,ObfuscateHelper.targetObfuscatedMethod(IResourceBridge.class,"getResourcePackName").getName()
                    ,Type.getMethodDescriptor(ObfuscateHelper.targetObfuscatedMethod(IResourceBridge.class,"getResourcePackName")));
            getResourcePackName.visitInsn(Opcodes.ARETURN);

            getResourcePackName.visitEnd();
        }
        byte[] bytes = cw.toByteArray();
        ClassReader cr = new ClassReader(bytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        ClassWriter cw2 = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cr.accept(cw2,0);
        bytes = cw2.toByteArray();
        Class bridge = Logger.getInstance().getAgent().defineClass(GuiScreenBridge.class.getClassLoader(),name,bytes);
        return bridge;
    }

    private Class<?> generateGuiScreenBridge(){
        String name = "al.logger.client.gui."+createRandomString(20);
        String internalName = name.replace(".","/");
        String wrappedScreen = createRandomString(20);
        Class<?> superClass = GuiScreen.GuiScreenClass;
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        //public class name extends GuiScreen
        cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,internalName,null, Type.getInternalName(superClass),new String[]{});
        cw.visitField(Opcodes.ACC_PRIVATE,wrappedScreen,Type.getDescriptor(GuiScreenBridge.class),null,null);
        {
            MethodVisitor init = cw.visitMethod(Opcodes.ACC_PUBLIC,"<init>",Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(GuiScreenBridge.class)),null,null);

            init.visitVarInsn(Opcodes.ALOAD, 0);
            init.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(superClass), "<init>", "()V");
            init.visitVarInsn(Opcodes.ALOAD, 0);
            init.visitVarInsn(Opcodes.ALOAD, 1);
            init.visitFieldInsn(Opcodes.PUTFIELD, internalName, wrappedScreen, Type.getDescriptor(GuiScreenBridge.class));
            init.visitInsn(Opcodes.RETURN);

            init.visitEnd();
        }
        {
            MethodVisitor drawScreen = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.drawScreen.getName(), "(IIF)V", null, null);

            drawScreen.visitVarInsn(Opcodes.ALOAD,0);
            drawScreen.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedScreen,Type.getDescriptor(GuiScreenBridge.class));
            drawScreen.visitVarInsn(Opcodes.ILOAD,1);
            drawScreen.visitVarInsn(Opcodes.ILOAD,2);
            drawScreen.visitVarInsn(Opcodes.FLOAD,3);
            drawScreen.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenBridge.class),ObfuscateHelper.targetObfuscatedMethod(GuiScreenBridge.class,"drawScreen").getName(),"(IIF)V");
            drawScreen.visitInsn(Opcodes.RETURN);

            drawScreen.visitEnd();
        }
        {
            MethodVisitor initGui = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.initGui.getName(),"()V",null,null);

            initGui.visitVarInsn(Opcodes.ALOAD,0);
            initGui.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedScreen,Type.getDescriptor(GuiScreenBridge.class));
            initGui.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenBridge.class),ObfuscateHelper.targetObfuscatedMethod(GuiScreenBridge.class,"initGui").getName(),"()V");
            initGui.visitInsn(Opcodes.RETURN);

            initGui.visitEnd();
        }
        {
            MethodVisitor onGuiClosed = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.onGuiClosed.getName(),"()V",null,null);

            onGuiClosed.visitVarInsn(Opcodes.ALOAD,0);
            onGuiClosed.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedScreen,Type.getDescriptor(GuiScreenBridge.class));
            onGuiClosed.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenBridge.class),ObfuscateHelper.targetObfuscatedMethod(GuiScreenBridge.class,"onGuiClosed").getName(),"()V");
            onGuiClosed.visitInsn(Opcodes.RETURN);

            onGuiClosed.visitEnd();
        }
        {
            MethodVisitor updateScreen = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.updateScreen.getName(),"()V",null,null);

            updateScreen.visitVarInsn(Opcodes.ALOAD,0);
            updateScreen.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedScreen,Type.getDescriptor(GuiScreenBridge.class));
            updateScreen.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenBridge.class),ObfuscateHelper.targetObfuscatedMethod(GuiScreenBridge.class,"updateScreen").getName(),"()V");
            updateScreen.visitInsn(Opcodes.RETURN);

            updateScreen.visitEnd();
        }
        {
            MethodVisitor mouseReleased = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.mouseReleased.getName(), "(III)V", null, null);

            mouseReleased.visitVarInsn(Opcodes.ALOAD,0);
            mouseReleased.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedScreen,Type.getDescriptor(GuiScreenBridge.class));
            mouseReleased.visitVarInsn(Opcodes.ILOAD,1);
            mouseReleased.visitVarInsn(Opcodes.ILOAD,2);
            mouseReleased.visitVarInsn(Opcodes.ILOAD,3);
            mouseReleased.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenBridge.class),ObfuscateHelper.targetObfuscatedMethod(GuiScreenBridge.class,"mouseReleased").getName(),"(III)V");
            mouseReleased.visitInsn(Opcodes.RETURN);

            mouseReleased.visitEnd();
        }
        {
            MethodVisitor keyTyped = cw.visitMethod(Opcodes.ACC_PROTECTED,GuiScreen.keyTyped.getName(),"(CI)V",null,null);

            Label L0 = new Label();
            keyTyped.visitVarInsn(Opcodes.ALOAD,0);
            keyTyped.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedScreen,Type.getDescriptor(GuiScreenBridge.class));
            keyTyped.visitVarInsn(Opcodes.ILOAD,1);
            keyTyped.visitVarInsn(Opcodes.ILOAD,2);
            keyTyped.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenBridge.class),ObfuscateHelper.targetObfuscatedMethod(GuiScreenBridge.class,"keyTyped").getName(),"(CI)Z");
            keyTyped.visitJumpInsn(Opcodes.IFEQ,L0);
            keyTyped.visitVarInsn(Opcodes.ALOAD,0);
            keyTyped.visitVarInsn(Opcodes.ILOAD,1);
            keyTyped.visitVarInsn(Opcodes.ILOAD,2);
            keyTyped.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(superClass),GuiScreen.keyTyped.getName(),"(CI)V");
            keyTyped.visitLabel(L0);

            keyTyped.visitInsn(Opcodes.RETURN);
        }
        {
            MethodVisitor mouseClicked = cw.visitMethod(Opcodes.ACC_PUBLIC, GuiScreen.mouseClicked.getName(), "(III)V", null, null);

            mouseClicked.visitVarInsn(Opcodes.ALOAD,0);
            mouseClicked.visitFieldInsn(Opcodes.GETFIELD,internalName,wrappedScreen,Type.getDescriptor(GuiScreenBridge.class));
            mouseClicked.visitVarInsn(Opcodes.ILOAD,1);
            mouseClicked.visitVarInsn(Opcodes.ILOAD,2);
            mouseClicked.visitVarInsn(Opcodes.ILOAD,3);
            mouseClicked.visitMethodInsn(Opcodes.INVOKEVIRTUAL,Type.getInternalName(GuiScreenBridge.class),ObfuscateHelper.targetObfuscatedMethod(GuiScreenBridge.class,"mouseClicked").getName(),"(III)V");
            mouseClicked.visitInsn(Opcodes.RETURN);

            mouseClicked.visitEnd();
        }
        byte[] bytes = cw.toByteArray();
        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw2 = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cr.accept(cw2,0);
        bytes = cw2.toByteArray();
        Class bridge = Logger.getInstance().getAgent().defineClass(GuiScreenBridge.class.getClassLoader(),name,bytes);
        return bridge;
    }

    public static String createRandomString(int length){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }
}
