package com.reflectmc.verify.packet.client;

import com.reflectmc.verify.packet.Packet;
import com.reflectmc.verify.packet.PacketBuffer;
import com.reflectmc.verify.packet.PacketID;
import lombok.Getter;

public class C03Pong implements Packet {
    @Getter
    private long timestamp;
    public C03Pong(){

    }
    public C03Pong(long timestamp){
        this.timestamp = timestamp;
    }
    @Override
    public void process(PacketBuffer buffer) {
        timestamp = buffer.readLong();
    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(PacketID.PacketC03Pong.getSeq());
        buffer.writeLong(timestamp);
        return buffer;
    }
}
