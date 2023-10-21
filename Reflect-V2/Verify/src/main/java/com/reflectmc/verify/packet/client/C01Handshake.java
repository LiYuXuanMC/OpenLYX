package com.reflectmc.verify.packet.client;

import com.reflectmc.verify.packet.Packet;
import com.reflectmc.verify.packet.PacketBuffer;
import com.reflectmc.verify.packet.PacketID;

public class C01Handshake implements Packet {
    @Override
    public void process(PacketBuffer buffer) {

    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(PacketID.PacketC01Handshake.getSeq());
        return buffer;
    }
}
