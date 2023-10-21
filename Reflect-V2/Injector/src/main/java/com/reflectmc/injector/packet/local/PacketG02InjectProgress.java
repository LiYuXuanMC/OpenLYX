package com.reflectmc.injector.packet.local;

import com.reflectmc.injector.packet.Packet;
import com.reflectmc.injector.packet.PacketBuffer;
import lombok.Getter;

public class PacketG02InjectProgress implements Packet {
    @Getter
    private String type;
    @Getter
    private double progress;
    public PacketG02InjectProgress(String type, double progress){
        this.type = type;
        this.progress = progress;
    }
    public PacketG02InjectProgress(){

    }
    @Override
    public void process(PacketBuffer buffer) {
        type = buffer.readString();
        progress = buffer.readDouble();
    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeString(type);
        buffer.writeDouble(progress);
        return buffer;
    }
}
