package com.reflectmc.verify.packet.client;

import com.reflectmc.verify.packet.Packet;
import com.reflectmc.verify.packet.PacketBuffer;
import com.reflectmc.verify.packet.PacketID;
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
        return buffer;
    }
}
