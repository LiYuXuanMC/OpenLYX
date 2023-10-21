package com.reflectmc.reflect.transform.transformers;

import com.reflectmc.libraries.asm.ClassReader;
import com.reflectmc.libraries.asm.ClassWriter;
import com.reflectmc.libraries.asm.Type;
import com.reflectmc.libraries.asm.tree.*;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.event.EventBus;
import com.reflectmc.reflect.event.events.game.EventTick;
import com.reflectmc.reflect.event.events.player.EventRotationAnimation;
import com.reflectmc.reflect.obfuscate.ObfuscateHelper;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityLivingBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.entity.RenderLivingEntity;

public class RendererLivingEntityTransformer extends ClassTransformer{
    @Override
    public Class<?> getTargetClass() {
        return RenderLivingEntity.RenderLivingEntityClass;
    }

    @Override
    public byte[] transform(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        for (MethodNode method : cn.methods) {
            if (method.name.equals(RenderLivingEntity.doRender.getName()) && method.desc.equals(Type.getMethodDescriptor(RenderLivingEntity.doRender))){
                int f = 0;
                int f1 = 0;
                int f2 = 0;
                int f7 = 0;
                AbstractInsnNode interpolateRotation = findMethodCall(RenderLivingEntity.interpolateRotation,method.instructions);
                if (interpolateRotation != null){
                    VarInsnNode fOP = (VarInsnNode) findVarOperate(interpolateRotation);
                    f = fOP.var;
                    interpolateRotation = findMethodCall(RenderLivingEntity.interpolateRotation,interpolateRotation);
                    VarInsnNode f1OP = (VarInsnNode) findVarOperate(interpolateRotation);
                    f1 = f1OP.var;
                    AbstractInsnNode FSUB = findFSUB(interpolateRotation);
                    VarInsnNode f2OP = (VarInsnNode) findVarOperate(FSUB);
                    f2 = f2OP.var;
                    AbstractInsnNode renderAt = findMethodCall(RenderLivingEntity.renderLivingAt,interpolateRotation);
                    AbstractInsnNode FADD = backFindFADD(renderAt);
                    VarInsnNode f7OP = (VarInsnNode) findVarOperate(FADD);
                    f7 = f7OP.var;

                    InsnList hook = new InsnList();
                    LocalVariableNode rot = new LocalVariableNode("rot",Type.getDescriptor(EventRotationAnimation.class),null,new LabelNode(),new LabelNode(), method.maxLocals);
                    method.localVariables.add(rot);

                    hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventRotationAnimation.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new TypeInsnNode(NEW,Type.getInternalName(EntityLivingBase.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new VarInsnNode(ALOAD,1));
                    hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EntityLivingBase.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                    hook.add(new VarInsnNode(FLOAD,f));
                    hook.add(new VarInsnNode(FLOAD,f1));
                    hook.add(new VarInsnNode(FLOAD,f2));
                    hook.add(new VarInsnNode(FLOAD,f7));
                    hook.add(new VarInsnNode(FLOAD,9));
                    hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventRotationAnimation.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(EntityLivingBase.class),Type.FLOAT_TYPE,Type.FLOAT_TYPE,Type.FLOAT_TYPE,Type.FLOAT_TYPE,Type.FLOAT_TYPE)));
                    hook.add(new VarInsnNode(ASTORE,rot.index));

                    hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new VarInsnNode(ALOAD,rot.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));


                    //              f = event.getRenderYawOffset();
                    hook.add(new VarInsnNode(ALOAD,rot.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventRotationAnimation.class)
                            , ObfuscateHelper.findObfMethod(EventRotationAnimation.class,"Rot$getRenderYawOffset").getName(),"()F"));
                    hook.add(new VarInsnNode(FSTORE,f));
                    //				f1 = event.getRotationYawHead();
                    hook.add(new VarInsnNode(ALOAD,rot.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventRotationAnimation.class)
                            , ObfuscateHelper.findObfMethod(EventRotationAnimation.class,"Rot$getRotationYawHead").getName(),"()F"));
                    hook.add(new VarInsnNode(FSTORE,f1));
                    //				f2 = event.getRenderHeadYaw();
                    hook.add(new VarInsnNode(ALOAD,rot.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventRotationAnimation.class)
                            , ObfuscateHelper.findObfMethod(EventRotationAnimation.class,"Rot$getRenderHeadYaw").getName(),"()F"));
                    hook.add(new VarInsnNode(FSTORE,f2));
                    //				f7 = event.getRenderHeadPitch();
                    hook.add(new VarInsnNode(ALOAD,rot.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventRotationAnimation.class)
                            , ObfuscateHelper.findObfMethod(EventRotationAnimation.class,"Rot$getRenderHeadPitch").getName(),"()F"));
                    hook.add(new VarInsnNode(FSTORE,f7));
                    method.instructions.insert(f7OP,hook);
                }else {
                    System.out.println("Find target fail");
                }
            }
        }
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        return cw.toByteArray();
    }
}
