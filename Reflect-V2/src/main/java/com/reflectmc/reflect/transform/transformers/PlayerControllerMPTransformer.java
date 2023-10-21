package com.reflectmc.reflect.transform.transformers;

import com.reflectmc.libraries.asm.ClassReader;
import com.reflectmc.libraries.asm.ClassWriter;
import com.reflectmc.libraries.asm.Type;
import com.reflectmc.libraries.asm.tree.*;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.event.EventBus;
import com.reflectmc.reflect.event.EventType;
import com.reflectmc.reflect.event.events.game.EventPacket;
import com.reflectmc.reflect.event.events.player.EventAttack;
import com.reflectmc.reflect.features.ModuleManager;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.modules.ghost.Reach;
import com.reflectmc.reflect.obfuscate.ObfuscateHelper;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayer;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.multiplayer.PlayerControllerMP;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;

public class PlayerControllerMPTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return PlayerControllerMP.PlayerControllerMPClass;
    }

    @Override
    public byte[] transform(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        for (MethodNode method : cn.methods) {
            if (method.name.equals(PlayerControllerMP.getBlockReachDistance.getName()) && method.desc.equals("()F")){
                InsnList hook = new InsnList();
                LocalVariableNode reach = new LocalVariableNode("reach", Type.getDescriptor(Reach.class),null,new LabelNode(),new LabelNode(),method.maxLocals);
                method.localVariables.add(reach);
                LabelNode L1 = new LabelNode();
                hook.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getModuleManager").getName(),Type.getMethodDescriptor(Type.getType(ModuleManager.class))));
                hook.add(new LdcInsnNode(Type.getType(Reach.class)));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(ModuleManager.class),ObfuscateHelper.findObfMethod(ModuleManager.class,"getModule").getName(),Type.getMethodDescriptor(Type.getType(Module.class),Type.getType(Class.class))));
                hook.add(new TypeInsnNode(CHECKCAST,Type.getInternalName(Reach.class)));
                hook.add(new VarInsnNode(ASTORE,reach.index));
                hook.add(new VarInsnNode(ALOAD,reach.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class), ObfuscateHelper.findObfMethod(Module.class,"isEnable").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new VarInsnNode(ALOAD,reach.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reach.class),ObfuscateHelper.findObfMethod(Reach.class,"getMaxRange").getName(),"()F"));
                hook.add(new InsnNode(FRETURN));
                hook.add(L1);
                method.instructions.insert(hook);
            }
            if (method.name.equals(PlayerControllerMP.attackEntity.getName()) && method.desc.equals(Type.getMethodDescriptor(PlayerControllerMP.attackEntity))){
                InsnList insnList = method.instructions;
                InsnList attack = new InsnList();
                //    ALOAD 1
                //    ALOAD 2
                //    INVOKESTATIC al/nya/reflect/events/EventBus.attack (Lal/nya/reflect/wrapper/wraps/wrapper/entity/EntityPlayer;Lal/nya/reflect/wrapper/wraps/wrapper/entity/Entity;)V

                LabelNode L1 = new LabelNode();
                attack.add(new VarInsnNode(ALOAD,1));
                attack.add(new TypeInsnNode(INSTANCEOF,Type.getInternalName(EntityPlayerSP.EntityPlayerSPClass)));
                attack.add(new JumpInsnNode(IFEQ,L1));

                attack.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                attack.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                attack.add(new TypeInsnNode(NEW,Type.getInternalName(EventAttack.class)));
                attack.add(new InsnNode(DUP));
                attack.add(new TypeInsnNode(NEW,Type.getInternalName(EntityPlayer.class)));
                attack.add(new InsnNode(DUP));
                attack.add(new VarInsnNode(ALOAD,1));
                attack.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EntityPlayer.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                attack.add(new TypeInsnNode(NEW,Type.getInternalName(Entity.class)));
                attack.add(new InsnNode(DUP));
                attack.add(new VarInsnNode(ALOAD,2));
                attack.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(Entity.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                attack.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventAttack.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(EntityPlayer.class),Type.getType(Entity.class))));
                attack.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));

                attack.add(L1);
                insnList.insert(attack);
                method.instructions = insnList;
            }
        }
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        return cw.toByteArray();
    }
}
