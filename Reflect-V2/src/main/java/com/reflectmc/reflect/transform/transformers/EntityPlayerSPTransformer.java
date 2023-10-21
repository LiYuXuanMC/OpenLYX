package com.reflectmc.reflect.transform.transformers;

import com.reflectmc.libraries.asm.ClassReader;
import com.reflectmc.libraries.asm.ClassWriter;
import com.reflectmc.libraries.asm.Type;
import com.reflectmc.libraries.asm.tree.*;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.event.EventBus;
import com.reflectmc.reflect.event.events.EventCancelable;
import com.reflectmc.reflect.event.events.game.EventPacket;
import com.reflectmc.reflect.event.events.game.EventSendMessage;
import com.reflectmc.reflect.event.events.player.EventLivingUpdate;
import com.reflectmc.reflect.event.events.player.EventPostUpdate;
import com.reflectmc.reflect.event.events.player.EventPreUpdate;
import com.reflectmc.reflect.features.ModuleManager;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.modules.ghost.Reach;
import com.reflectmc.reflect.features.modules.movement.NoSlow;
import com.reflectmc.reflect.obfuscate.ObfuscateHelper;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;

public class EntityPlayerSPTransformer extends ClassTransformer{
    @Override
    public Class<?> getTargetClass() {
        return EntityPlayerSP.EntityPlayerSPClass;
    }

    @Override
    public byte[] transform(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        for (MethodNode method : cn.methods) {
            if (method.name.equals(EntityPlayerSP.onLivingUpdate.getName()) && method.desc.equals("()V")){
                InsnList hook = new InsnList();
                hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventLivingUpdate.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventLivingUpdate.class), "<init>", "()V"));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(), "(L"+Type.getInternalName(Event.class)+";)V"));
                method.instructions.insert(hook);
            }
            if (method.name.equals(EntityPlayerSP.onLivingUpdate.getName()) && method.desc.equals("()V")){
                //OnUpdate
                InsnList insnList = method.instructions;
                InsnList hook = new InsnList();
                for (AbstractInsnNode abstractInsnNode : insnList) {
                    //INVOKEVIRTUAL net/minecraft/client/entity/EntityPlayerSP.func_71039_bw ()Z
                    if (abstractInsnNode instanceof MethodInsnNode){
                        if (abstractInsnNode.getOpcode() == INVOKEVIRTUAL &&
                                ((MethodInsnNode) abstractInsnNode).owner.equals(Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass)) &&
                                ((MethodInsnNode) abstractInsnNode).name.equals(Entity.isRiding.getName())&&
                                ((MethodInsnNode) abstractInsnNode).desc.equals("()Z")){
                            AbstractInsnNode L12 = abstractInsnNode.getNext();
                            if (L12 instanceof JumpInsnNode){
                                LocalVariableNode noslow = new LocalVariableNode("noslow", Type.getDescriptor(NoSlow.class),null,new LabelNode(),new LabelNode(),method.maxLocals);
                                method.localVariables.add(noslow);
                                hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getModuleManager").getName(),Type.getMethodDescriptor(Type.getType(ModuleManager.class))));
                                hook.add(new LdcInsnNode(Type.getType(NoSlow.class)));
                                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(ModuleManager.class),ObfuscateHelper.findObfMethod(ModuleManager.class,"getModule").getName(),Type.getMethodDescriptor(Type.getType(Module.class),Type.getType(Class.class))));
                                hook.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(NoSlow.class)));
                                hook.add(new VarInsnNode(ASTORE,noslow.index));
                                hook.add(new VarInsnNode(ALOAD,noslow.index));
                                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(NoSlow.class),ObfuscateHelper.findObfMethod(Module.class,"isEnable").getName(),"()Z"));
                                hook.add(new JumpInsnNode(IFNE, ((JumpInsnNode) L12).label));
                                insnList.insert(L12.getNext(),hook);
                            }
                            break;
                        }
                    }
                }
                method.instructions = insnList;
            }
            if (method.name.equals(EntityPlayerSP.onUpdate.getName()) && method.desc.equals("()V")) {
                InsnList pre = new InsnList();
                InsnList post = new InsnList();
                LabelNode L1 = new LabelNode();
                LabelNode L2 = new LabelNode();
                LocalVariableNode preUpdate = new LocalVariableNode("eventPreUpdate",Type.getDescriptor(EventPreUpdate.class),null,new LabelNode(),new LabelNode(),method.maxLocals);
                method.localVariables.add(preUpdate);
                //pre.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                //pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                pre.add(new TypeInsnNode(NEW, Type.getInternalName(EventPreUpdate.class)));
                pre.add(new InsnNode(DUP));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.rotationYaw.getName(),Type.getDescriptor(float.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.rotationPitch.getName(),Type.getDescriptor(float.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.posX.getName(),Type.getDescriptor(double.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.posY.getName(),Type.getDescriptor(double.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.posZ.getName(),Type.getDescriptor(double.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.onGround.getName(),Type.getDescriptor(boolean.class)));
                pre.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventPreUpdate.class), "<init>", "(FFDDDZ)V"));
                pre.add(new VarInsnNode(ASTORE,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(), "(L"+Type.getInternalName(Event.class)+";)V"));

                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.findObfMethod(EventPreUpdate.class,"Pre$getYaw").getName(),"()F"));
                pre.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.rotationYaw.getName(),Type.getDescriptor(float.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.findObfMethod(EventPreUpdate.class,"Pre$getPitch").getName(),"()F"));
                pre.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.rotationPitch.getName(),Type.getDescriptor(float.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.findObfMethod(EventPreUpdate.class,"Pre$getX").getName(),"()D"));
                pre.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.posX.getName(),Type.getDescriptor(double.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.findObfMethod(EventPreUpdate.class,"Pre$getY").getName(),"()D"));
                pre.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.posY.getName(),Type.getDescriptor(double.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.findObfMethod(EventPreUpdate.class,"Pre$getZ").getName(),"()D"));
                pre.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.posZ.getName(),Type.getDescriptor(double.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.findObfMethod(EventPreUpdate.class,"Pre$isOnGround").getName(),"()Z"));
                pre.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.onGround.getName(),Type.getDescriptor(boolean.class)));


                /*pre.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getModuleManager").getName(),Type.getMethodDescriptor(Type.getType(ModuleManager.class))));
                pre.add(new LdcInsnNode(Type.getType(RotationAnimation.class)));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(ModuleManager.class),ObfuscateHelper.findObfMethod(ModuleManager.class,"getModule").getName(),Type.getMethodDescriptor(Type.getType(Module.class),Type.getType(Class.class))));
                pre.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(RotationAnimation.class)));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(RotationAnimation.class),ObfuscateHelper.findObfMethod(Module.class,"isEnable").getName(),"()Z"));
                pre.add(new JumpInsnNode(IFEQ,L1));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.findObfMethod(EventPreUpdate.class,"getYaw").getName(),"()F"));
                pre.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(EntityLivingBase.EntityLivingBaseClass),EntityLivingBase.rotationYawHead.getName(),Type.getDescriptor(float.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.findObfMethod(EventPreUpdate.class,"getYaw").getName(),"()F"));
                pre.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(EntityLivingBase.EntityLivingBaseClass),EntityLivingBase.renderYawOffset.getName(),Type.getDescriptor(float.class)));
                pre.add(L1);

                 */


                post.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                post.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                post.add(new TypeInsnNode(NEW, Type.getInternalName(EventPostUpdate.class)));
                post.add(new InsnNode(DUP));
                post.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventPostUpdate.class), "<init>", "()V"));
                post.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(), "(L"+Type.getInternalName(Event.class)+";)V"));

                post.add(new VarInsnNode(ALOAD,preUpdate.index));
                post.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.findObfMethod(EventPreUpdate.class,"Pre$isValueChanged").getName(),"()Z"));
                post.add(new JumpInsnNode(IFEQ,L2));
                post.add(new VarInsnNode(ALOAD,0));
                post.add(new VarInsnNode(ALOAD,preUpdate.index));
                post.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.findObfMethod(EventPreUpdate.class,"Pre$getYawOld").getName(),"()F"));
                post.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.rotationYaw.getName(),Type.getDescriptor(float.class)));
                post.add(new VarInsnNode(ALOAD,0));
                post.add(new VarInsnNode(ALOAD,preUpdate.index));
                post.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.findObfMethod(EventPreUpdate.class,"Pre$getPitchOld").getName(),"()F"));
                post.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.rotationPitch.getName(),Type.getDescriptor(float.class)));
                post.add(L2);

                for (AbstractInsnNode abstractInsnNode : method.instructions) {
                    if (abstractInsnNode instanceof MethodInsnNode){
                        if (abstractInsnNode.getOpcode() == INVOKEVIRTUAL
                                && ((MethodInsnNode) abstractInsnNode).owner.equals(Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass))
                                && ((MethodInsnNode) abstractInsnNode).name.equals(EntityPlayerSP.onUpdateWalkingPlayer.getName())
                                && ((MethodInsnNode) abstractInsnNode).desc.equals("()V")){
                            AbstractInsnNode aload = abstractInsnNode.getPrevious();
                            while (aload.getOpcode() != ALOAD){
                                aload = aload.getPrevious();
                            }
                            method.instructions.insertBefore(aload,pre);
                            method.instructions.insert(abstractInsnNode,post);
                        }
                    }
                }
            }
            if (method.name.equals(EntityPlayerSP.sendChatMessage.getName()) && method.desc.equals("(Ljava/lang/String;)V")){
                InsnList hook = new InsnList();
                LabelNode L1 = new LabelNode();
                LocalVariableNode msg = new LocalVariableNode("msg",Type.getDescriptor(EventSendMessage.class),null,new LabelNode(),new LabelNode(),method.maxLocals);
                method.localVariables.add(msg);
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventSendMessage.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(ALOAD,1));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventSendMessage.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(String.class))));
                hook.add(new VarInsnNode(ASTORE,msg.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,msg.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(new VarInsnNode(ALOAD,msg.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventSendMessage.class),ObfuscateHelper.findObfMethod(EventCancelable.class,"isCancel").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new InsnNode(RETURN));
                hook.add(L1);
                method.instructions.insert(hook);
            }
        }
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        return cw.toByteArray();
    }
}
