package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.player.EventMove;
import al.logger.client.event.client.render.EventCape;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.ModuleManager;
import al.logger.client.features.modules.impls.Movement.KeepSprint;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;
import by.radioegor146.nativeobfuscator.VM;

@Native
public class EntityPlayerTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return EntityPlayer.EntityPlayerClass;
    }

    @Override
    //////@VM
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(EntityPlayer.attackTargetEntityWithCurrentItem.getName()) && method.desc.equals(Type.getMethodDescriptor(EntityPlayer.attackTargetEntityWithCurrentItem))) {
                LabelNode L1 = new LabelNode();
                InsnList ks = new InsnList();

                ks.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                ks.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getModuleManager").getName(),Type.getMethodDescriptor(Type.getType(ModuleManager.class))));
                ks.add(new LdcInsnNode(Type.getType(KeepSprint.class)));
                ks.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(ModuleManager.class),ObfuscateHelper.targetObfuscatedMethod(ModuleManager.class,"getModule").getName(),Type.getMethodDescriptor(Type.getType(Module.class),Type.getType(Class.class))));
                ks.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(KeepSprint.class)));
                ks.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(KeepSprint.class),ObfuscateHelper.targetObfuscatedMethod(Module.class,"isEnable").getName(),"()Z"));
                ks.add(new JumpInsnNode(IFNE, L1));
                AbstractInsnNode start = null;
                AbstractInsnNode end = null;

                for (int i = 0; i < method.instructions.size(); i++) {
                    AbstractInsnNode node = method.instructions.get(i);
                    if (node instanceof MethodInsnNode) {
                        String name2 = ((MethodInsnNode) node).name;
                        String desc = ((MethodInsnNode) node).desc;
                        if (name2.equals(Entity.addVelocity.getName()) && desc.equals("(DDD)V")){
                            start = node;
                        }
                        if ((name2.equals(EntityPlayerSP.setSprinting.getName())) && desc.equals("(Z)V")) {
                            end = node;
                        }
                    }
                }
                if (start != null && end != null){
                    method.instructions.insert(start,ks);
                    method.instructions.insert(end,L1);
                }
            }
            if (method.name.equals(EntityPlayer.isWearing.getName()) && method.desc.equals(Type.getMethodDescriptor(EntityPlayer.isWearing))){
                AbstractInsnNode iReturn = null;
                for (int i = 0; i < method.instructions.size(); i++) {
                    AbstractInsnNode node = method.instructions.get(i);
                    if (node instanceof InsnNode){
                        if (node.getOpcode() == IRETURN){
                            iReturn = node;
                        }
                    }
                }
                if (iReturn != null){
                    LocalVariableNode capeVar = new LocalVariableNode("cape",Type.getDescriptor(EventCape.class),null,new LabelNode(),new LabelNode(),method.maxLocals);
                    method.localVariables.add(capeVar);

                    InsnList hook = new InsnList();

                    hook.add(new TypeInsnNode(NEW, Type.getInternalName(EntityPlayer.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new VarInsnNode(ALOAD,0));
                    hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EntityPlayer.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(Object.class))));

                    hook.add(new InsnNode(SWAP));

                    hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventCape.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new InsnNode(DUP2_X2));
                    hook.add(new InsnNode(POP2));
                    hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventCape.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(EntityPlayer.class),Type.BOOLEAN_TYPE)));
                    hook.add(new VarInsnNode(ASTORE,capeVar.index));

                    hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new VarInsnNode(ALOAD,capeVar.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));

                    hook.add(new VarInsnNode(ALOAD,capeVar.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventCape.class),ObfuscateHelper.targetObfuscatedMethod(EventCape.class,"hasCape").getName(),"()Z"));

                    method.instructions.insertBefore(iReturn,hook);
                }
            }
        }
    }
}
