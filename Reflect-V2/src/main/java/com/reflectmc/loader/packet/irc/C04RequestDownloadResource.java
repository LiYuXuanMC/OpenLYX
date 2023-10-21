package com.reflectmc.loader.packet.irc;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketID;
import lombok.Getter;

public class C04RequestDownloadResource implements Packet {
    @Getter
    private String resource;
    public C04RequestDownloadResource(){

    }
    public C04RequestDownloadResource(String resource){
        this.resource = resource;
    }
    @Override
    public void process(PacketBuffer buffer) {
        resource = buffer.readString();
    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(PacketID.PacketC04RequestDownloadResource.getSeq());
        buffer.writeString(resource);
        return buffer;
    }
}
