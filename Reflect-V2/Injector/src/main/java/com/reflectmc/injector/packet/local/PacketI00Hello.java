package com.reflectmc.injector.packet.local;

import com.reflectmc.injector.packet.Packet;
import com.reflectmc.injector.packet.PacketBuffer;
import com.reflectmc.injector.packet.PacketID;

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
