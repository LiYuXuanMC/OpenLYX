package com.reflectmc.loader.packet.local;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketID;

public class PacketI00Hello implements Packet {
    @Override
    public void process(PacketBuffer buffer) {

    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(PacketID.I00Hello.getSeq());
        return buffer;
    }
}
