package com.reflectmc.reflect.socket.irc;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketType;
import com.reflectmc.loader.packet.irc.*;
import com.reflectmc.loader.socket.ServerSocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class IServerPacketProcessor extends ServerPacketProcessor {
    public IServerPacketProcessor(ServerSocket client) {
        super(client);
    }
    private final Map<Class<? extends Packet>,List<Consumer<Packet>>> packetHook = new HashMap<>();
    @Override
    public void process(PacketBuffer buffer) {
        int packetLength = buffer.readInt();
        System.out.println("Packet data size " + packetLength);
        PacketBuffer packet = new PacketBuffer();
        packet.writeByte(buffer.read(packetLength));
        Packet packetInstance = processPacket(packet, PacketType.Server);
        if (packetInstance != null){
            System.out.println(packetInstance.getClass().getSimpleName());
            packetHook.forEach((p,h) -> {
                if (p.isInstance(packetInstance)){
                    h.forEach(c -> c.accept(packetInstance));
                }
            });

            PacketBuffer superBuffer = new PacketBuffer();
            superBuffer.writeByte(buffer.getData());
            super.process(superBuffer);
        }else {
            System.out.println("Packet cannot be process");
        }
    }
    public void addPacketHook(Consumer<Packet> hook,Class<? extends Packet> targetPacket){
        if (packetHook.containsKey(targetPacket)){
            List<Consumer<Packet>> hooks = packetHook.get(targetPacket);
            hooks.add(hook);
        }else {
            List<Consumer<Packet>> hooks = new ArrayList<>();
            hooks.add(hook);
            packetHook.put(targetPacket,hooks);
        }
    }
}
