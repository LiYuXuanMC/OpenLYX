package al.nya.reflect.transform.transformers;

import al.nya.reflect.events.EventBus;
import al.nya.reflect.libraries.reflectasm.ClassReader;
import al.nya.reflect.libraries.reflectasm.ClassWriter;
import al.nya.reflect.libraries.reflectasm.Opcodes;
import al.nya.reflect.libraries.reflectasm.Type;
import al.nya.reflect.libraries.reflectasm.tree.*;
import al.nya.reflect.transform.ClassTransformer;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetworkManager;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;
import by.radioegor146.nativeobfuscator.Native;

public class NetworkManagerTransformer extends ClassTransformer {
    @Override
    public Class<?> getTargetClass() {
        return NetworkManager.NetworkManagerClass;
    }

    @Override
    @Native
    public byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassNode classNode = new ClassNode();
        cr.accept(classNode,0);
        for (MethodNode method : classNode.methods) {
            if ((method.name.equals("handler$send$zze000") || method.name.equals("handler$send$zza000")) && MargeleAntiCheatDetector.isMAC()){
                InsnList hookInsn = new InsnList();
                LabelNode L0 = new LabelNode();
                hookInsn.add(new VarInsnNode(ALOAD,1));
                hookInsn.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(EventBus.class),"macProcessSendPacket", "(Ljava/lang/Object;)Z"));
                hookInsn.add(new JumpInsnNode(IFEQ,L0));
                hookInsn.add(new InsnNode(RETURN));
                hookInsn.add(L0);
                method.instructions.insert(hookInsn);
            }
            if ((method.name.equals("handler$read$zze000") || method.name.equals("handler$read$zza000")) && MargeleAntiCheatDetector.isMAC()){
                InsnList hookInsn = new InsnList();
                LabelNode L0 = new LabelNode();
                hookInsn.add(new VarInsnNode(ALOAD,1));
                hookInsn.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(EventBus.class),"macProcessRevPacket", "(Ljava/lang/Object;)Z"));
                hookInsn.add(new JumpInsnNode(IFEQ,L0));
                hookInsn.add(new InsnNode(RETURN));
                hookInsn.add(L0);
                method.instructions.insert(hookInsn);
            }
            if (method.name.equals(NetworkManager.closeChannel.getName()) && MargeleAntiCheatDetector.isMAC()){
                InsnList hookInsn = new InsnList();
                LabelNode L0 = new LabelNode();
                hookInsn.add(new VarInsnNode(ALOAD,1));
                hookInsn.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(EventBus.class),"onCloseChannel", "(Ljava/lang/Object;)Z"));
                hookInsn.add(new JumpInsnNode(IFEQ,L0));
                hookInsn.add(new InsnNode(RETURN));
                hookInsn.add(L0);
                method.instructions.insert(hookInsn);
            }
            if (method.name.equals(NetworkManager.channelRead0.getName()) && method.desc.equals("(Lio/netty/channel/ChannelHandlerContext;L" + Type.getInternalName(Packet.PacketClass) + ";)V")){
                InsnList insnList = method.instructions;;
                InsnList recievePacket = new InsnList();
                //ALOAD 2
                //INVOKESTATIC al/nya/reflect/events/EventBus.recievePacketEvent (Ljava/lang/Object;)Z
                //IFEQ L0
                //RETURN
                //L0
                LabelNode L0 = new LabelNode();
                recievePacket.add(new VarInsnNode(Opcodes.ALOAD,2));
                recievePacket.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),"recievePacketEvent","(Ljava/lang/Object;)Z"));
                recievePacket.add(new JumpInsnNode(Opcodes.IFEQ,L0));
                recievePacket.add(new InsnNode(Opcodes.RETURN));
                recievePacket.add(L0);
                //recievePacket.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
                insnList.insert(recievePacket);
                method.instructions = insnList;
                method.maxLocals++;
            }
            if (method.name.equals(NetworkManager.sendPacket1.getName()) && method.desc.equals("(L"+Type.getInternalName(Packet.PacketClass)+";)V")){
                InsnList insnList = method.instructions;;
                InsnList sendPacket = new InsnList();
                //ALOAD 1
                //INVOKESTATIC al/nya/reflect/events/EventBus.sendPacketEvent (Ljava/lang/Object;)Z
                //IFEQ L0
                //RETURN
                //L0
                LabelNode L0 = new LabelNode();
                sendPacket.add(new VarInsnNode(Opcodes.ALOAD,1));
                sendPacket.add(new MethodInsnNode(Opcodes.INVOKESTATIC,Type.getInternalName(EventBus.class),"sendPacketEvent","(Ljava/lang/Object;)Z"));
                sendPacket.add(new JumpInsnNode(Opcodes.IFEQ,L0));
                sendPacket.add(new InsnNode(Opcodes.RETURN));
                sendPacket.add(L0);
                //sendPacket.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
                insnList.insert(sendPacket);
                method.instructions = insnList;
                method.maxLocals++;
            }
        }
        ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
        classNode.accept(cw);
        return cw.toByteArray();
    }
}
