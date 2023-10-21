package com.reflectmc.loader.packet.irc;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketID;

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
