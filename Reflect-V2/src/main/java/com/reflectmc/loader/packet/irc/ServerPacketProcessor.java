package com.reflectmc.loader.packet.irc;

import com.google.gson.Gson;
import com.reflectmc.libraries.websocket.client.WebSocketClient;
import com.reflectmc.loader.Loader;
import com.reflectmc.loader.agent.ReflectNative;
import com.reflectmc.loader.data.UserData;
import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketProcessor;
import com.reflectmc.loader.packet.PacketType;
import com.reflectmc.loader.packet.local.LocalPacketProcessor;
import com.reflectmc.loader.socket.ServerSocket;
import com.reflectmc.loader.utils.EncryptUtil;

public class ServerPacketProcessor implements PacketProcessor {
    private ServerSocket socket;
    public ServerPacketProcessor(ServerSocket client){
        socket = client;
    }

    @Override
    public void process(PacketBuffer buffer) {
        int packetLength = buffer.readInt();
        System.out.println("Packet data size " + packetLength);
        PacketBuffer packet = new PacketBuffer();
        packet.writeByte(buffer.read(packetLength));
        Packet packetInstance = processPacket(packet, PacketType.Server);
        if (packetInstance != null){
            System.out.println(packetInstance.getClass().getSimpleName());
            if (packetInstance instanceof S00Hello){
                send(new C01Handshake());
            }
            if (packetInstance instanceof S01UpdateKey){
                Loader.getINSTANCE().getServerSocket().updateKey(((S01UpdateKey) packetInstance).getKey());
                send(new C02Login(LocalPacketProcessor.getMetadata().getUsername(),
                        EncryptUtil.generateMD5(LocalPacketProcessor.getMetadata().getPasswd())));
            }
            if (packetInstance instanceof S02LoginResponse){
                S02LoginResponse loginResponse = (S02LoginResponse) packetInstance;
                if (loginResponse.getStatus() == 200){
                    System.out.println("Login success");
                    Loader.getINSTANCE().setUserData(new Gson().fromJson(loginResponse.getExtraData(), UserData.class));
                    ReflectNative.startInject(this.getClass().getClassLoader());
                }else {
                    //do crash
                }
            }
            if (packetInstance instanceof S03Ping){
                //HeartBeat
                send(new C03Pong(((S03Ping) packetInstance).getTimestamp()));
            }
        }else {
            System.out.println("Packet cannot be process");
        }
    }

    @Override
    public void onConnect() {
        send(new C00Hello());
    }

    @Override
    public void onDisconnect() {

    }
    public void send(Packet packet){
        socket.sendPacket(createPacket(packet.create()));
    }
}
