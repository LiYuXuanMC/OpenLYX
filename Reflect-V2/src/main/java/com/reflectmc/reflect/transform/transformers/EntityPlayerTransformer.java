package com.reflectmc.reflect.transform.transformers;

import com.reflectmc.libraries.asm.ClassReader;
import com.reflectmc.libraries.asm.ClassWriter;
import com.reflectmc.libraries.asm.Type;
import com.reflectmc.libraries.asm.tree.*;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.features.ModuleManager;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.modules.ghost.Reach;
import com.reflectmc.reflect.features.modules.movement.KeepSprint;
import com.reflectmc.reflect.obfuscate.ObfuscateHelper;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayer;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;

public class EntityPlayerTransformer extends ClassTransformer{
    @Override
    public Class<?> getTargetClass() {
        return EntityPlayer.EntityPlayerClass;
    }

    @Override
    public byte[] transform(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        for (MethodNode method : cn.methods) {
            if (method.name.equals(EntityPlayer.attackTargetEntityWithCurrentItem.getName()) && method.desc.equals(Type.getMethodDescriptor(EntityPlayer.attackTargetEntityWithCurrentItem))) {
                LabelNode L1 = new LabelNode();
                InsnList ks = new InsnList();

                ks.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Reflect.class), ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                ks.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getModuleManager").getName(),Type.getMethodDescriptor(Type.getType(ModuleManager.class))));
                ks.add(new LdcInsnNode(Type.getType(KeepSprint.class)));
                ks.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(ModuleManager.class),ObfuscateHelper.findObfMethod(ModuleManager.class,"getModule").getName(),Type.getMethodDescriptor(Type.getType(Module.class),Type.getType(Class.class))));
                ks.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(KeepSprint.class)));
                ks.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(KeepSprint.class),ObfuscateHelper.findObfMethod(Module.class,"isEnable").getName(),"()Z"));
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
        }
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        return cw.toByteArray();
    }
}
