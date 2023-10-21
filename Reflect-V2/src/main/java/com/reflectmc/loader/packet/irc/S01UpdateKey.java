package com.reflectmc.loader.packet.irc;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketID;
import lombok.Getter;

public class S01UpdateKey implements Packet {
    @Getter
    private String key;
    public S01UpdateKey(){

    }
    public S01UpdateKey(String key){
        this.key = key;
    }
    @Override
    public void process(PacketBuffer buffer) {
        key = buffer.readString();
    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(PacketID.PacketS01UpdateKey.getSeq());
        buffer.writeString(key);
        return buffer;
    }
}
