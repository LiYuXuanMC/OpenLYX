package com.reflectmc.loader.packet.local;

import com.reflectmc.libraries.websocket.client.WebSocketClient;
import com.reflectmc.loader.Loader;
import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketProcessor;
import com.reflectmc.loader.packet.PacketType;
import com.reflectmc.loader.socket.InjectorSocket;
import com.reflectmc.loader.socket.ServerSocket;
import lombok.Getter;

public class LocalPacketProcessor implements PacketProcessor {
    private InjectorSocket connection;
    @Getter
    private static PacketI01Metadata metadata;
    public LocalPacketProcessor(InjectorSocket webSocketClient){
        this.connection = webSocketClient;
    }
    @Override
    public void process(PacketBuffer buffer) {
        int packetLength = buffer.readInt();
        System.out.println("Packet data size " + packetLength);
        PacketBuffer packet = new PacketBuffer();
        packet.writeByte(buffer.read(packetLength));
        Packet packetInstance = processPacket(packet, PacketType.Injector);
        if (packetInstance != null){
            System.out.println(packetInstance.getClass().getSimpleName());
            if (packetInstance instanceof PacketI00Hello){
                System.out.println("Injector send Hello");
                send(new PacketG01RequestMetadata());
            }
            if (packetInstance instanceof PacketI01Metadata){
                System.out.println("Metadata got");
                PacketI01Metadata metadata = (PacketI01Metadata) packetInstance;
                LocalPacketProcessor.metadata = metadata;
                connection.updateProgress("Verifying...",0,1);
                Loader.getINSTANCE().setServerSocket(new ServerSocket(metadata.getServerChannel()));
                Loader.getINSTANCE().getServerSocket().connect();
            }
        }else {
            System.out.println("Packet cannot be process");
        }
    }

    @Override
    public void onConnect() {
        PacketG00Hello g00 = new PacketG00Hello();
        send(g00);
    }

    @Override
    public void onDisconnect() {
        System.out.println("Injector disconnect");
    }

    public void send(Packet packet){
        connection.sendPacket(createPacket(packet.create()));
    }
}
