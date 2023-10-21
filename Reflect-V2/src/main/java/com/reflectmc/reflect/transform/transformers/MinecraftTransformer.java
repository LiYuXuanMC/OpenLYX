package com.reflectmc.reflect.transform.transformers;

import com.reflectmc.libraries.asm.ClassReader;
import com.reflectmc.libraries.asm.ClassWriter;
import com.reflectmc.libraries.asm.Type;
import com.reflectmc.libraries.asm.tree.*;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.event.EventBus;
import com.reflectmc.reflect.event.events.game.EventKey;
import com.reflectmc.reflect.event.events.game.EventLoop;
import com.reflectmc.reflect.event.events.game.EventTick;
import com.reflectmc.reflect.event.events.render.EventRender2D;
import com.reflectmc.reflect.obfuscate.ObfuscateHelper;
import com.reflectmc.reflect.utils.FileUtil;
import com.reflectmc.reflect.wrapper.wrappers.lwjgl.KeyBoard;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;

import java.io.File;

public class MinecraftTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return Minecraft.MinecraftClass;
    }

    @Override
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(Minecraft.runTick.getName()) && method.desc.equals("()V")){
                //OnUpdate
                InsnList hook = new InsnList();
                hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventTick.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventTick.class), "<init>", "()V"));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                method.instructions.insert(hook);
            }
            if (method.name.equals(Minecraft.dispatchKeypresses.getName())&& method.desc.equals("()V")){
                InsnList hook = new InsnList();
                LabelNode L1 = new LabelNode();
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(KeyBoard.class),ObfuscateHelper.findObfMethod(KeyBoard.class,"getEventKeyState").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventKey.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventKey.class), "<init>", "()V"));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(L1);
                method.instructions.insert(hook);
            }
            if(method.name.equals(Minecraft.runGameLoop.getName())&& method.desc.equals("()V")){
                InsnList hook = new InsnList();
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventLoop.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventLoop.class), "<init>", "()V"));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                method.instructions.insert(hook);
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_FRAMES);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
