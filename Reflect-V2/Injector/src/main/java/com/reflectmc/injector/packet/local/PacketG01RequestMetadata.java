package com.reflectmc.injector.packet.local;

import com.reflectmc.injector.packet.Packet;
import com.reflectmc.injector.packet.PacketBuffer;
import com.reflectmc.injector.packet.PacketID;

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
