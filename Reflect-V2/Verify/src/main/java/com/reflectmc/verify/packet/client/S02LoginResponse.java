package com.reflectmc.verify.packet.client;

import com.reflectmc.verify.packet.Packet;
import com.reflectmc.verify.packet.PacketBuffer;
import com.reflectmc.verify.packet.PacketID;
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
        buffer.writeByte(PacketID.PacketS02LoginResponse.getSeq());
        buffer.writeInt(status);
        if (extraData != null){
            buffer.writeString(extraData);
        }
        return buffer;
    }
}
