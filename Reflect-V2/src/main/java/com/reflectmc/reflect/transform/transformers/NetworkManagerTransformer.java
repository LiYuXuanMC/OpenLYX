package com.reflectmc.reflect.transform.transformers;

import com.reflectmc.libraries.asm.ClassReader;
import com.reflectmc.libraries.asm.ClassWriter;
import com.reflectmc.libraries.asm.Type;
import com.reflectmc.libraries.asm.tree.*;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.event.EventBus;
import com.reflectmc.reflect.event.EventType;
import com.reflectmc.reflect.event.events.EventCancelable;
import com.reflectmc.reflect.event.events.game.EventPacket;
import com.reflectmc.reflect.features.ModuleManager;
import com.reflectmc.reflect.obfuscate.ObfuscateHelper;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.NetworkManager;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;

public class NetworkManagerTransformer extends ClassTransformer{
    @Override
    public Class<?> getTargetClass() {
        return NetworkManager.NetworkManagerClass;
    }

    @Override
    public byte[] transform(byte[] classBytes) {
        ClassReader cr = new ClassReader(classBytes);
        ClassNode cn = new ClassNode();
        cr.accept(cn,0);
        for (MethodNode method : cn.methods) {
            if (method.name.equals(NetworkManager.channelRead0.getName()) && method.desc.equals("(Lio/netty/channel/ChannelHandlerContext;L" + Type.getInternalName(Packet.PacketClass) + ";)V")) {
                LocalVariableNode eventPacket = new LocalVariableNode("eventPacket",Type.getDescriptor(EventPacket.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(eventPacket);
                LabelNode L1 = new LabelNode();
                InsnList hook = new InsnList();
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventPacket.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(Packet.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(ALOAD,2));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(Packet.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                hook.add(new FieldInsnNode(GETSTATIC,Type.getInternalName(EventType.class),"Send",Type.getDescriptor(EventType.class)));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventPacket.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Packet.class),Type.getType(EventType.class))));
                hook.add(new VarInsnNode(ASTORE,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(new VarInsnNode(ALOAD,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPacket.class),ObfuscateHelper.findObfMethod(EventCancelable.class,"isCancel").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new InsnNode(RETURN));
                hook.add(L1);
                method.instructions.insert(hook);
            }
            if (method.name.equals(NetworkManager.sendPacket1.getName()) && method.desc.equals("(L"+Type.getInternalName(Packet.PacketClass)+";)V")){
                LocalVariableNode eventPacket = new LocalVariableNode("eventPacket",Type.getDescriptor(EventPacket.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(eventPacket);
                LabelNode L1 = new LabelNode();
                InsnList hook = new InsnList();
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventPacket.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(Packet.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(ALOAD,1));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(Packet.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                hook.add(new FieldInsnNode(GETSTATIC,Type.getInternalName(EventType.class),"Receive",Type.getDescriptor(EventType.class)));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventPacket.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Packet.class),Type.getType(EventType.class))));
                hook.add(new VarInsnNode(ASTORE,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getINSTANCE").getName(),Type.getMethodDescriptor(Type.getType(Reflect.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Reflect.class),ObfuscateHelper.findObfMethod(Reflect.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.findObfMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(new VarInsnNode(ALOAD,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPacket.class),ObfuscateHelper.findObfMethod(EventCancelable.class,"isCancel").getName(),"()Z"));
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
