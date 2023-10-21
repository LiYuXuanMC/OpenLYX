package com.reflectmc.loader.packet.local;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketID;
import lombok.Getter;

public class PacketI01Metadata implements Packet {
    @Getter
    private String username;
    @Getter
    private String passwd;
    @Getter
    private int serverChannel;
    public PacketI01Metadata(){

    }
    public PacketI01Metadata(String username,String passwd,int serverChannel){
        this.username = username;
        this.passwd = passwd;
        this.serverChannel = serverChannel;
    }
    @Override
    public void process(PacketBuffer buffer) {
        username = buffer.readString();
        passwd = buffer.readString();
        serverChannel = buffer.readInt();
    }
    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(PacketID.I01Metadata.getSeq()); //Packet SEQ
        buffer.writeString(username);
        buffer.writeString(passwd);
        buffer.writeInt(serverChannel);
        return buffer;
    }
}
