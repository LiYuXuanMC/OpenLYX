package com.reflectmc.loader.packet.local;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketID;
import lombok.Getter;

public class PacketG02InjectProgress implements Packet {
    @Getter
    private String type;
    @Getter
    private int progress;
    @Getter
    private int max;
    public PacketG02InjectProgress(String type,int progress,int max){
        this.type = type;
        this.progress = progress;
        this.max = max;
    }
    public PacketG02InjectProgress(){

    }
    @Override
    public void process(PacketBuffer buffer) {
        type = buffer.readString();
        progress = buffer.readInt();
        max = buffer.readInt();
    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(PacketID.G02InjectProgress.getSeq());
        buffer.writeString(type);
        buffer.writeInt(progress);
        buffer.writeInt(max);
        return buffer;
    }
}
