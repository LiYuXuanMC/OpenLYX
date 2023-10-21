package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.CancelableEvent;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.mac.EventMACProcessUpdate;
import al.logger.client.event.client.player.*;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.ModuleManager;
import al.logger.client.features.modules.impls.Movement.NoSlow;
import al.logger.client.features.modules.impls.World.CommandHandler;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import al.logger.libs.asm.Opcodes;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;
import by.radioegor146.nativeobfuscator.VM;

@Native
public class EntityPlayerSPTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return EntityPlayerSP.EntityPlayerSPClass;
    }

    @Override
    //////@VM
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(EntityPlayerSP.onLivingUpdate.getName()) && method.desc.equals("()V")){
                InsnList hook = new InsnList();
                hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventLivingUpdate.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventLivingUpdate.class), "<init>", "()V"));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(), "(L"+Type.getInternalName(Event.class)+";)V"));
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
                                hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getModuleManager").getName(),Type.getMethodDescriptor(Type.getType(ModuleManager.class))));
                                hook.add(new LdcInsnNode(Type.getType(NoSlow.class)));
                                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(ModuleManager.class),ObfuscateHelper.targetObfuscatedMethod(ModuleManager.class,"getModule").getName(),Type.getMethodDescriptor(Type.getType(Module.class),Type.getType(Class.class))));
                                hook.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(NoSlow.class)));
                                hook.add(new VarInsnNode(ASTORE,noslow.index));
                                hook.add(new VarInsnNode(ALOAD,noslow.index));
                                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(NoSlow.class),ObfuscateHelper.targetObfuscatedMethod(Module.class,"isEnable").getName(),"()Z"));
                                hook.add(new JumpInsnNode(IFNE, ((JumpInsnNode) L12).label));
                                insnList.insert(L12.getNext(),hook);
                            }
                            break;
                        }
                    }
                }
                method.instructions = insnList;
            }
            if (method.name.equals(EntityPlayerSP.sendChatMessage.getName()) && method.desc.equals("(Ljava/lang/String;)V")){
                InsnList insnListOld = method.instructions;
                InsnList insnList = new InsnList();
                InsnList chat = new InsnList();
                LabelNode L0 = new LabelNode();
                chat.add(new VarInsnNode(Opcodes.ALOAD,1));
                chat.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(EntityPlayerSPTransformer.class),ObfuscateHelper.targetObfuscatedMethod(EntityPlayerSPTransformer.class,"onMessage").getName(),"(Ljava/lang/String;)Z"));
                chat.add(new JumpInsnNode(Opcodes.IFEQ,L0));
                insnList.add(chat);
                insnList.add(insnListOld);
                insnList.add(L0);
                insnList.add(new InsnNode(Opcodes.RETURN));
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
                pre.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.posX.getName(),Type.getDescriptor(double.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.posY.getName(),Type.getDescriptor(double.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.posZ.getName(),Type.getDescriptor(double.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.rotationYaw.getName(),Type.getDescriptor(float.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.rotationPitch.getName(),Type.getDescriptor(float.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new FieldInsnNode(GETFIELD,Type.getInternalName(Entity.EntityClass),Entity.onGround.getName(),Type.getDescriptor(boolean.class)));
                pre.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventPreUpdate.class), "<init>", "(DDDFFZ)V"));
                pre.add(new VarInsnNode(ASTORE,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(), "(L"+Type.getInternalName(Event.class)+";)V"));

                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.targetObfuscatedMethod(EventPreUpdate.class,"getYaw").getName(),"()F"));
                pre.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.rotationYaw.getName(),Type.getDescriptor(float.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.targetObfuscatedMethod(EventPreUpdate.class,"getPitch").getName(),"()F"));
                pre.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.rotationPitch.getName(),Type.getDescriptor(float.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.targetObfuscatedMethod(EventPreUpdate.class,"getX").getName(),"()D"));
                pre.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.posX.getName(),Type.getDescriptor(double.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.targetObfuscatedMethod(EventPreUpdate.class,"getY").getName(),"()D"));
                pre.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.posY.getName(),Type.getDescriptor(double.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.targetObfuscatedMethod(EventPreUpdate.class,"getZ").getName(),"()D"));
                pre.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.posZ.getName(),Type.getDescriptor(double.class)));
                pre.add(new VarInsnNode(ALOAD,0));
                pre.add(new VarInsnNode(ALOAD,preUpdate.index));
                pre.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.targetObfuscatedMethod(EventPreUpdate.class,"isOnGround").getName(),"()Z"));
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


                post.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                post.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                post.add(new TypeInsnNode(NEW, Type.getInternalName(EventPostUpdate.class)));
                post.add(new InsnNode(DUP));
                post.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventPostUpdate.class), "<init>", "()V"));
                post.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(), "(L"+Type.getInternalName(Event.class)+";)V"));

                post.add(new VarInsnNode(ALOAD,preUpdate.index));
                post.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.targetObfuscatedMethod(EventPreUpdate.class,"isValueChanged").getName(),"()Z"));
                post.add(new JumpInsnNode(IFEQ,L2));
                post.add(new VarInsnNode(ALOAD,0));
                post.add(new VarInsnNode(ALOAD,preUpdate.index));
                post.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.targetObfuscatedMethod(EventPreUpdate.class,"getYawOld").getName(),"()F"));
                post.add(new FieldInsnNode(PUTFIELD,Type.getInternalName(Entity.EntityClass),Entity.rotationYaw.getName(),Type.getDescriptor(float.class)));
                post.add(new VarInsnNode(ALOAD,0));
                post.add(new VarInsnNode(ALOAD,preUpdate.index));
                post.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPreUpdate.class),ObfuscateHelper.targetObfuscatedMethod(EventPreUpdate.class,"getPitchOld").getName(),"()F"));
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
                InsnList hook = new InsnList();
                hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventUpdate.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventUpdate.class), "<init>", "()V"));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(), "(L"+Type.getInternalName(Event.class)+";)V"));
                method.instructions.insert(hook);
            }
            if (method.name.contains("handler$eventUpdate") && EnvironmentDetector.hasAntiCheat(Environment.MargelesAntiCheat)) {
                LocalVariableNode eventUpdate = new LocalVariableNode("eventUpdate",Type.getDescriptor(EventMACProcessUpdate.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(eventUpdate);
                InsnList hook = new InsnList();
                LabelNode L1 = new LabelNode();
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventMACProcessUpdate.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventMACProcessUpdate.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE)));
                hook.add(new VarInsnNode(ASTORE,eventUpdate.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,eventUpdate.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(new VarInsnNode(ALOAD,eventUpdate.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventMACProcessUpdate.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new InsnNode(RETURN));

                hook.add(L1);
                method.instructions.insert(hook);
            }
            if (method.name.equals(EntityPlayerSP.onUpdateWalkingPlayer.getName()) && method.desc.equals(Type.getMethodDescriptor(EntityPlayerSP.onUpdateWalkingPlayer))){
                LocalVariableNode eventState = new LocalVariableNode("eventState",Type.getDescriptor(EventPlayerState.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(eventState);

                MethodInsnNode isSprinting = (MethodInsnNode) findMethodCall(Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass),Entity.isSprinting.getName(),Type.getMethodDescriptor(Entity.isSprinting),method.instructions);
                VarInsnNode istore = (VarInsnNode) findVarOperate(isSprinting);
                if (istore != null){
                    InsnList hookUpdateSprintState = new InsnList();
                    hookUpdateSprintState.add(new VarInsnNode(ALOAD,eventState.index));
                    hookUpdateSprintState.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPlayerState.class),ObfuscateHelper.targetObfuscatedMethod(EventPlayerState.class,"isSprint").getName(),"()Z"));
                    hookUpdateSprintState.add(new VarInsnNode(ISTORE,istore.var));
                    method.instructions.insert(istore,hookUpdateSprintState);
                }
                MethodInsnNode isSneaking = (MethodInsnNode) findMethodCall(Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass),Entity.isSneaking.getName(),Type.getMethodDescriptor(Entity.isSprinting),method.instructions);
                istore = (VarInsnNode) findVarOperate(isSneaking);
                if (istore != null){
                    InsnList hookUpdateSneakState = new InsnList();
                    hookUpdateSneakState.add(new VarInsnNode(ALOAD,eventState.index));
                    hookUpdateSneakState.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPlayerState.class),ObfuscateHelper.targetObfuscatedMethod(EventPlayerState.class,"isSnake").getName(),"()Z"));
                    hookUpdateSneakState.add(new VarInsnNode(ISTORE,istore.var));
                    method.instructions.insert(istore,hookUpdateSneakState);
                }
                InsnList hookRequestState = new InsnList();
                hookRequestState.add(new TypeInsnNode(NEW,Type.getInternalName(EventPlayerState.class)));
                hookRequestState.add(new InsnNode(DUP));
                hookRequestState.add(new VarInsnNode(ALOAD,0));
                hookRequestState.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass),Entity.isSprinting.getName(),"()Z"));
                hookRequestState.add(new VarInsnNode(ALOAD,0));
                hookRequestState.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass),Entity.isSneaking.getName(),"()Z"));
                hookRequestState.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventPlayerState.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.BOOLEAN_TYPE,Type.BOOLEAN_TYPE)));
                hookRequestState.add(new VarInsnNode(ASTORE,eventState.index));
                hookRequestState.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hookRequestState.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hookRequestState.add(new VarInsnNode(ALOAD,eventState.index));
                hookRequestState.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                method.instructions.insert(hookRequestState);
            }
        }
    }

    @ExportObfuscate(name = "onMessage")
    public static boolean onMessage(String s){
        if (s.startsWith(".") && Logger.getInstance().moduleManager.getModule(CommandHandler.class).isEnable()){
            Logger.getInstance().commandManager.runCommand(s);
            return false;
        }
        return true;
    }
}
