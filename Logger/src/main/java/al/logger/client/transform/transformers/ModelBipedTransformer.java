package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.player.EmotePostEvent;
import al.logger.client.event.client.player.EmotePreEvent;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.model.ModelBase;
import al.logger.client.wrapper.LoggerMC.model.ModelBiped;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;

public class ModelBipedTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return ModelBiped.ModelBipedClass;
    }

    @Override
    public void transformClass(ClassNode cn) {
        boolean hook1 = false;
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if(method.name.equals(ModelBiped.setRotationAngles.getName())){
                for (AbstractInsnNode abstractInsnNode : method.instructions) {
                    if (abstractInsnNode instanceof FieldInsnNode){
                        FieldInsnNode fieldInsnNode = (FieldInsnNode) abstractInsnNode;
                        if (fieldInsnNode.name.equals(ModelBiped.heldItemRight.getName()) && !hook1){
                            InsnList hook = new InsnList();
                            hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                            hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                            hook.add(new TypeInsnNode(NEW, Type.getInternalName(EmotePreEvent.class)));
                            hook.add(new InsnNode(DUP));
                            hook.add(new TypeInsnNode(NEW, Type.getInternalName(ModelBiped.class)));
                            hook.add(new InsnNode(DUP));
                            hook.add(new VarInsnNode(ALOAD,0));
                            //INVOKESPECIAL al/logger/client/wrapper/LoggerMC/model/ModelBiped.<init> (Ljava/lang/Object;)V
                            hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(ModelBiped.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                            //NEW al/logger/client/wrapper/LoggerMC/entity/Entity
                            hook.add(new TypeInsnNode(NEW, Type.getInternalName(Entity.class)));
                            hook.add(new InsnNode(DUP));
                            hook.add(new VarInsnNode(ALOAD,7));
                            //INVOKESPECIAL al/logger/client/wrapper/LoggerMC/entity/Entity.<init> (Ljava/lang/Object;)V
                            hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(Entity.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                            // INVOKESPECIAL al/logger/client/event/client/player/EmotePreEvent.<init> (Lal/logger/client/wrapper/LoggerMC/model/ModelBiped;Lal/logger/client/wrapper/LoggerMC/entity/Entity;)V
                            hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EmotePreEvent.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(ModelBiped.class),Type.getType(Entity.class))));
                            // INVOKEVIRTUAL al/logger/client/event/EventBus.callEvent (Lal/logger/client/event/Event;)V
                            hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Event.class))));
                            method.instructions.insertBefore(abstractInsnNode,hook);
                            hook1 = true;
                        }
                    }
                    if(abstractInsnNode instanceof MethodInsnNode){
                        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
                        if (methodInsnNode.name.equals(ModelBase.copyModelAngles.getName())){
                            InsnList hook = new InsnList();
                            hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                            hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                            hook.add(new TypeInsnNode(NEW, Type.getInternalName(EmotePostEvent.class)));
                            hook.add(new InsnNode(DUP));
                            hook.add(new TypeInsnNode(NEW, Type.getInternalName(ModelBiped.class)));
                            hook.add(new InsnNode(DUP));
                            hook.add(new VarInsnNode(ALOAD,0));
                            //INVOKESPECIAL al/logger/client/wrapper/LoggerMC/model/ModelBiped.<init> (Ljava/lang/Object;)V
                            hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(ModelBiped.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                            //NEW al/logger/client/wrapper/LoggerMC/entity/Entity
                            hook.add(new TypeInsnNode(NEW, Type.getInternalName(Entity.class)));
                            hook.add(new InsnNode(DUP));
                            hook.add(new VarInsnNode(ALOAD,7));
                            //INVOKESPECIAL al/logger/client/wrapper/LoggerMC/entity/Entity.<init> (Ljava/lang/Object;)V
                            hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(Entity.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                            // INVOKESPECIAL al/logger/client/event/client/player/EmotePostEvent.<init> (Lal/logger/client/wrapper/LoggerMC/model/ModelBiped;Lal/logger/client/wrapper/LoggerMC/entity/Entity;)V
                            hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EmotePostEvent.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(ModelBiped.class),Type.getType(Entity.class))));
                            // INVOKEVIRTUAL al/logger/client/event/EventBus.callEvent (Lal/logger/client/event/Event;)V
                            hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Event.class))));
                            method.instructions.insertBefore(abstractInsnNode,hook);
                        }
                    }
                }

            }
        }
    }




}
