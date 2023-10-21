package com.reflectmc.loader.packet.irc;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketID;
import lombok.Getter;

public class S03Ping implements Packet {
    @Getter
    private long timestamp;
    public S03Ping(){

    }
    public S03Ping(long timestamp){
        this.timestamp = timestamp;
    }
    @Override
    public void process(PacketBuffer buffer) {
        timestamp = buffer.readLong();
    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(PacketID.PacketS03Ping.getSeq());
        buffer.writeLong(timestamp);
        return null;
    }
}
