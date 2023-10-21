package com.reflectmc.loader.packet;

import java.util.Arrays;

public interface PacketProcessor {
    void process(PacketBuffer buffer);
    void onConnect();
    void onDisconnect();

    default PacketBuffer createPacket(PacketBuffer buffer){
        PacketBuffer newPacket = new PacketBuffer();
        newPacket.writeInt(buffer.getLength());
        newPacket.appendBuffer(buffer);
        buffer.closeBuffer();
        return newPacket;
    }

    default Packet processPacket(PacketBuffer buffer,PacketType type) {
        byte[] seq = buffer.read(2);
        for (PacketID value : PacketID.values()) {
            if (Arrays.equals(value.getSeq(), seq) && value.getType() == type){
                try {
                    Packet packet = value.getTarget().newInstance();
                    packet.process(buffer);
                    buffer.closeBuffer();
                    return packet;
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
