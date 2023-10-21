package com.reflectmc.reflect.transform.transformers;

import com.reflectmc.libraries.asm.ClassReader;
import com.reflectmc.libraries.asm.ClassWriter;
import com.reflectmc.libraries.asm.Type;
import com.reflectmc.libraries.asm.tree.*;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.event.EventBus;
import com.reflectmc.reflect.event.events.render.EventRender2D;
import com.reflectmc.reflect.obfuscate.ObfuscateHelper;
import com.reflectmc.reflect.wrapper.Wrapper;
import com.reflectmc.reflect.wrapper.mapper.Mapper;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.GuiIngame;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.ScaledResolution;

public class GuiIngameForgeTransformer extends ClassTransformer{
    @Override
    public Class<?> getTargetClass() {
        return Mapper.getGuiIngameForge();
    }

    @Override
    public byte[] transform(byte[] classBytes) {
        ClassNode cn = new ClassNode();
        ClassReader cr = new ClassReader(classBytes);
        cr.accept(cn,0);
        for (MethodNode method : cn.methods) {
            if (method.name.equals(GuiIngame.renderTooltip.getName()) &&
                    method.desc.equals("(L" + Type.getInternalName(ScaledResolution.ScaledResolutionClass) + ";F)V")){
                InsnList hook = new InsnList();
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new TypeInsnNode(NEW, Type.getInternalName(EventRender2D.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(FLOAD,2));
                hook.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventRender2D.class), "<init>", "(F)V"));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(), "(L"+Type.getInternalName(Event.class)+";)V"));
                method.instructions.insert(hook);
            }
        }
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        return cw.toByteArray();
    }
}
