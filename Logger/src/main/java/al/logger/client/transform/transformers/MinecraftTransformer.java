package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.client.EventKey;
import al.logger.client.event.client.EventLoop;
import al.logger.client.event.client.EventMouse;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.render.EventScreen;
import al.logger.client.event.client.world.EventLoadWorld;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.LoggerMC.KeyBinding;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.gui.GuiScreen;
import al.logger.client.wrapper.LoggerMC.world.WorldClient;
import al.logger.client.wrapper.lwjgl.KeyBoard;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;

@Native
public class MinecraftTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return Minecraft.MinecraftClass;
    }

    @Override
    //////@VM
    public void transformClass(ClassNode cn) {
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
            if (method.name.equals(Minecraft.runTick.getName()) && method.desc.equals("()V")){
                {
                    InsnList hook = new InsnList();
                    hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventTick.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventTick.class), "<init>", "()V"));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Event.class))));
                    method.instructions.insert(hook);
                }
                {
                    InsnList hook = new InsnList();
                    hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                    hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventMouse.class)));
                    hook.add(new InsnNode(DUP));
                    hook.add(new VarInsnNode(ILOAD,1));
                    hook.add(new MethodInsnNode(INVOKESTATIC,"org/lwjgl/input/Mouse","getEventButtonState","()Z"));
                    hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventMouse.class), "<init>", "(IZ)V"));
                    hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Event.class))));
                    AbstractInsnNode insnNode = findMethodCall(KeyBinding.setKeyBindState,method.instructions);
                    hook.insert(insnNode,hook);
                }
            }
            if(method.name.equals(Minecraft.runGameLoop.getName())&& method.desc.equals("()V")){
                InsnList hook = new InsnList();
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventLoop.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventLoop.class), "<init>", "()V"));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                method.instructions.insert(hook);
            }
            if (method.name.equals(Minecraft.dispatchKeypresses.getName())&& method.desc.equals("()V")){
                InsnList hook = new InsnList();
                LabelNode L1 = new LabelNode();
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(KeyBoard.class),ObfuscateHelper.targetObfuscatedMethod(KeyBoard.class,"getEventKeyState").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventKey.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventKey.class), "<init>", "()V"));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(L1);
                method.instructions.insert(hook);
            }
            //loadWorld
            if (method.name.equals(Minecraft.loadWorld.getName()) && method.desc.equals(Type.getMethodDescriptor(Minecraft.loadWorld))){
                InsnList hook = new InsnList();
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventLoadWorld.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(WorldClient.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(ALOAD,1));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(WorldClient.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventLoadWorld.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(WorldClient.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                method.instructions.insert(hook);
            }

            //displayGuiScreen
            if (method.name.equals(Minecraft.displayGuiScreen.getName()) && method.desc.equals(Type.getMethodDescriptor(Minecraft.displayGuiScreen))){
                InsnList hook = new InsnList();
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventScreen.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(GuiScreen.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(ALOAD,1));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(GuiScreen.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventScreen.class), "<init>", Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(GuiScreen.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                method.instructions.insert(hook);
            }
        }
    }
}
