package com.reflectmc.loader.packet.irc;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketID;

public class S00Hello implements Packet {
    @Override
    public void process(PacketBuffer buffer) {

    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buf = new PacketBuffer();
        buf.writeByte(PacketID.PacketS00Hello.getSeq());
        return buf;
    }
}
