package com.reflectmc.injector.packet.local;

import com.reflectmc.injector.Main;
import com.reflectmc.injector.crypt.AES_CFB8_NoPadding;
import com.reflectmc.injector.packet.Packet;
import com.reflectmc.injector.packet.PacketBuffer;
import com.reflectmc.injector.packet.PacketProcessor;
import com.reflectmc.injector.packet.PacketType;
import org.java_websocket.WebSocket;

public class InjectorPacketProcessor implements PacketProcessor {
    private WebSocket target;
    private AES_CFB8_NoPadding crypt;
    private byte[] vi = new byte[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
    public InjectorPacketProcessor(WebSocket target){
        this.target = target;
    }

    @Override
    public void process(PacketBuffer buffer) {
        PacketBuffer decrypt = new PacketBuffer();
        decrypt.writeByte(crypt.decrypt(buffer.getData()));
        buffer.closeBuffer();
        int packetLength = decrypt.readInt();
        System.out.println("Packet data size " + packetLength);
        PacketBuffer packet = new PacketBuffer();
        packet.writeByte(decrypt.read(packetLength));
        Packet packetInstance = processPacket(packet, PacketType.Game);
        if (packetInstance != null){
            System.out.println(packetInstance.getClass().getSimpleName());
            if (packetInstance instanceof PacketG00Hello){
                System.out.println("Game send Hello");
                sendPacket(createPacket(new PacketI00Hello().create()));
            }
            if (packetInstance instanceof PacketG01RequestMetadata) {
                System.out.println("Game request Metadata");
                sendPacket(createPacket(new PacketI01Metadata(Main.debugUsername,Main.debugPassword,Main.debugChannel).create()));
            }
            if (packetInstance instanceof PacketG02InjectProgress){
                System.out.println("Update progress");
                System.out.println(((PacketG02InjectProgress) packetInstance).getType());
                System.out.println(((PacketG02InjectProgress) packetInstance).getProgress());
                System.out.println("Update Progress "+ ((PacketG02InjectProgress) packetInstance).getType()
                        +" "+((PacketG02InjectProgress) packetInstance).getProgress()*100+"%");
            }
        }else {
            System.out.println("Packet cannot be process");
        }
    }
    public void sendPacket(PacketBuffer buffer){
        sendPacket(buffer.getData());
        buffer.closeBuffer();
    }
    public void sendPacket(byte[] bytes){
        target.send(crypt.encrypt(bytes));
    }
    @Override
    public void onConnect() {
        crypt = new AES_CFB8_NoPadding("5ca37899z7b0f22e",vi);
    }
}
