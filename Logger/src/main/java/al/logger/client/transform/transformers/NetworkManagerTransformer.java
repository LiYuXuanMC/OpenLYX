package al.logger.client.transform.transformers;

import al.logger.client.Logger;
import al.logger.client.event.CancelableEvent;
import al.logger.client.event.Event;
import al.logger.client.event.EventBus;
import al.logger.client.event.EventType;
import al.logger.client.event.client.EventCloseChannel;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.mac.EventMACProcessPacket;
import al.logger.client.transform.ClassTransformer;
import al.logger.client.utils.obfuscate.ObfuscateHelper;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.network.NetworkManager;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.utils.text.IChatComponent;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import al.logger.libs.asm.Opcodes;
import al.logger.libs.asm.Type;
import al.logger.libs.asm.tree.*;
import by.radioegor146.nativeobfuscator.Native;
import by.radioegor146.nativeobfuscator.VM;

@Native
public class NetworkManagerTransformer extends ClassTransformer{
    @Override
    public Class<?> getTargetClass() {
        return NetworkManager.NetworkManagerClass;
    }


    @Override
    //@VM
    public void transformClass(ClassNode cn) {
        if (NetworkManager.channelRead0 == null){
            System.out.println("channelRead0");
        }
        for (MethodNode method : cn.methods) {
            if(method == null)continue;
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
                hook.add(new FieldInsnNode(GETSTATIC,Type.getInternalName(EventType.class),ObfuscateHelper.targetObfuscatedEnum(EventType.Receive).getName(),Type.getDescriptor(EventType.class)));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventPacket.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Packet.class),Type.getType(EventType.class))));
                hook.add(new VarInsnNode(ASTORE,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class), ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(new VarInsnNode(ALOAD,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPacket.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new InsnNode(RETURN));
                hook.add(L1);
                method.instructions.insert(hook);
            }
            if (method.name.equals(NetworkManager.sendPacket1.getName()) && method.desc.equals("(L"+Type.getInternalName(Packet.PacketClass)+";)V")){
                LocalVariableNode eventPacket = new LocalVariableNode("eventPacket",Type.getDescriptor(EventPacket.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(eventPacket);
                LabelNode L1 = new LabelNode();
                LabelNode L2 = new LabelNode();
                InsnList hook = new InsnList();
                //SendPacketNoEvent
                hook.add(new VarInsnNode(ALOAD,1));
                hook.add(new TypeInsnNode(INSTANCEOF,Type.getInternalName(Logger.getInstance().getBridgeManager().getNoEventPacketClass())));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new VarInsnNode(ALOAD,1));
                hook.add(new TypeInsnNode(Opcodes.CHECKCAST,Type.getInternalName(Logger.getInstance().getBridgeManager().getNoEventPacketClass())));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.getInstance().getBridgeManager().getNoEventPacketClass()),"getPacket",Type.getMethodDescriptor(Type.getType(Packet.class))));
                hook.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,Type.getInternalName(Packet.class),ObfuscateHelper.targetObfuscatedMethod(IWrapper.class,"getWrappedObject").getName(),Type.getMethodDescriptor(ObfuscateHelper.targetObfuscatedMethod(IWrapper.class,"getWrappedObject"))));
                hook.add(new TypeInsnNode(Opcodes.CHECKCAST,Type.getInternalName(Packet.PacketClass)));
                hook.add(new VarInsnNode(ASTORE,1));
                hook.add(new JumpInsnNode(GOTO,L2));
                hook.add(L1);
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventPacket.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(Packet.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(ALOAD,1));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(Packet.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                hook.add(new FieldInsnNode(GETSTATIC,Type.getInternalName(EventType.class),ObfuscateHelper.targetObfuscatedEnum(EventType.Send).getName(),Type.getDescriptor(EventType.class)));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventPacket.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Packet.class),Type.getType(EventType.class))));
                hook.add(new VarInsnNode(ASTORE,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(new VarInsnNode(ALOAD,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventPacket.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L2));
                hook.add(new InsnNode(RETURN));

                hook.add(L2);
                method.instructions.insert(hook);
            }
            if ((method.name.startsWith("handler$send$")) && EnvironmentDetector.hasAntiCheat(Environment.MargelesAntiCheat)){
                LocalVariableNode eventPacket = new LocalVariableNode("eventPacket",Type.getDescriptor(EventMACProcessPacket.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(eventPacket);
                InsnList hook = new InsnList();
                LabelNode L1 = new LabelNode();
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventMACProcessPacket.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(Packet.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(ALOAD,1));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(Packet.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventMACProcessPacket.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Packet.class))));
                hook.add(new VarInsnNode(ASTORE,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(new VarInsnNode(ALOAD,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventMACProcessPacket.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new InsnNode(RETURN));

                hook.add(L1);
                method.instructions.insert(hook);
            }
            if ((method.name.startsWith("handler$read$")) && EnvironmentDetector.hasAntiCheat(Environment.MargelesAntiCheat)){
                LocalVariableNode eventPacket = new LocalVariableNode("eventPacket",Type.getDescriptor(EventMACProcessPacket.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(eventPacket);
                InsnList hook = new InsnList();
                LabelNode L1 = new LabelNode();
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventMACProcessPacket.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(Packet.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(ALOAD,1));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(Packet.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventMACProcessPacket.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Packet.class))));
                hook.add(new VarInsnNode(ASTORE,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(new VarInsnNode(ALOAD,eventPacket.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventMACProcessPacket.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new InsnNode(RETURN));

                hook.add(L1);
                method.instructions.insert(hook);
            }
            if (method.name.equals(NetworkManager.closeChannel.getName()) && EnvironmentDetector.hasAntiCheat(Environment.MargelesAntiCheat)){
                LocalVariableNode closeChannel = new LocalVariableNode("closeChannel",Type.getDescriptor(EventCloseChannel.class),null, new LabelNode(), new LabelNode(), method.maxLocals);
                method.localVariables.add(closeChannel);
                InsnList hook = new InsnList();
                LabelNode L1 = new LabelNode();
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(EventCloseChannel.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new TypeInsnNode(NEW,Type.getInternalName(IChatComponent.class)));
                hook.add(new InsnNode(DUP));
                hook.add(new VarInsnNode(ALOAD,1));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(IChatComponent.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(Object.class))));
                hook.add(new MethodInsnNode(INVOKESPECIAL,Type.getInternalName(EventCloseChannel.class),"<init>",Type.getMethodDescriptor(Type.VOID_TYPE,Type.getType(IChatComponent.class))));
                hook.add(new VarInsnNode(ASTORE,closeChannel.index));
                hook.add(new MethodInsnNode(INVOKESTATIC,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getInstance").getName(),Type.getMethodDescriptor(Type.getType(Logger.class))));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(Logger.class),ObfuscateHelper.targetObfuscatedMethod(Logger.class,"getEventBus").getName(),Type.getMethodDescriptor(Type.getType(EventBus.class))));
                hook.add(new VarInsnNode(ALOAD,closeChannel.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventBus.class),ObfuscateHelper.targetObfuscatedMethod(EventBus.class,"callEvent").getName(),"(L"+Type.getInternalName(Event.class)+";)V"));
                hook.add(new VarInsnNode(ALOAD,closeChannel.index));
                hook.add(new MethodInsnNode(INVOKEVIRTUAL,Type.getInternalName(EventCloseChannel.class),ObfuscateHelper.targetObfuscatedMethod(CancelableEvent.class,"isCanceled").getName(),"()Z"));
                hook.add(new JumpInsnNode(IFEQ,L1));
                hook.add(new InsnNode(RETURN));

                hook.add(L1);
                method.instructions.insert(hook);
            }
        }
    }
}
