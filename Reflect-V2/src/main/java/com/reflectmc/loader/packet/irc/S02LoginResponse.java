package com.reflectmc.loader.packet.irc;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketID;
import lombok.Getter;

public class S02LoginResponse implements Packet {
    @Getter
    private int status;
    @Getter
    private String extraData;
    public S02LoginResponse(){

    }
    public S02LoginResponse(int status,String extraData){
        this.status = status;
        this.extraData = extraData;
    }
    @Override
    public void process(PacketBuffer buffer) {
        status = buffer.readInt();
        if (!buffer.end()){
            extraData = buffer.readString();
        }else {
            extraData = null;
        }
    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeInt(status);
        if (extraData != null){
            buffer.writeString(extraData);
        }
        return buffer;
    }
}
