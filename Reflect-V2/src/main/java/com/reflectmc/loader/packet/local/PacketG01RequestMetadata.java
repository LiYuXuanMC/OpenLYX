package com.reflectmc.loader.packet.local;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketID;

public class PacketG01RequestMetadata implements Packet {
    @Override
    public void process(PacketBuffer buffer) {

    }

    @Override
    public PacketBuffer create() {
        PacketBuffer packetBuffer = new PacketBuffer();
        packetBuffer.writeByte(PacketID.G01RequestMetadata.getSeq());
        return packetBuffer;
    }
}
