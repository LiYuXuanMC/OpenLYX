package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.render.EventCape;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.entity.AbstractClientPlayer;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;

public class AbstractClientPlayerTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return AbstractClientPlayer.AbstractClientPlayerClass;
    }

    @Override
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if (method.name.equals(AbstractClientPlayer.getLocationCape.getName()) && method.desc.equals(Type.getMethodDescriptor(AbstractClientPlayer.getLocationCape))) {
                AbstractInsnNode aReturn = null;
                for (int i = 0; i < method.instructions.size(); i++) {
                    AbstractInsnNode node = method.instructions.get(i);
                    if (node instanceof InsnNode) {
                        if (node.getOpcode() == ARETURN) {
                            aReturn = node;
                        }
                    }
                }
                if (aReturn != null){
                    LocalVariableNode capeVar = new LocalVariableNode("cape",Type.getDescriptor(EventCape.class),null,new LabelNode(),new LabelNode(),method.maxLocals);
                    method.localVariables.add(capeVar);

                    InsnList hook = new InsnList();

                    hook.add(new TypeInsnNode(NEW, Type.getInternalName(ResourceLocation.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new InsnNode(DUP2_X1));
                    hook.add(new InsnNode(POP2));
                    hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(ResourceLocation.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(Object.class))));
                    //Cape -> WrappedCape

                    hook.add(new TypeInsnNode(NEW, Type.getInternalName(EntityPlayer.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new VarInsnNode(ALOAD,0));
                    hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EntityPlayer.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(Object.class))));
                    //WrappedCape -> WrappedCape,EntityPlayer

                    hook.add(new InsnNode(SWAP));
                    //WrappedCape,EntityPlayer -> EntityPlayer,WrappedCape

                    hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventCape.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new InsnNode(DUP2_X2));
                    hook.add(new InsnNode(POP2));
                    hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventCape.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(EntityPlayer.class), Type.getType(ResourceLocation.class))));
                    hook.add(new VarInsnNode(ASTORE, capeVar.index));
                    //EntityPlayer,WrappedCape -> EventCape

                    hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class), ObfuscateHelper.targetObfuscatedMethod(Logger.class, "getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new VarInsnNode(ALOAD,capeVar.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));

                    hook.add(new VarInsnNode(ALOAD,capeVar.index));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventCape.class),ObfuscateHelper.targetObfuscatedMethod(EventCape.class,"getCapeLocation").getName(),Type.getMethodDescriptor(Type.getType(ResourceLocation.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(ResourceLocation.class),ObfuscateHelper.targetObfuscatedMethod(IWrapper.class,"getWrappedObject").getName(),Type.getMethodDescriptor(Type.getType(Object.class))));
                    hook.add(new TypeInsnNode(CHECKCAST, Type.getInternalName(ResourceLocation.ResourceLocation)));

                    method.instructions.insertBefore(aReturn,hook);
                }
            }
        }
    }
}
