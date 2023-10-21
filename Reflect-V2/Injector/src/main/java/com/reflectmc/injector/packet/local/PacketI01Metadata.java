package com.reflectmc.injector.packet.local;

import com.reflectmc.injector.packet.Packet;
import com.reflectmc.injector.packet.PacketBuffer;
import com.reflectmc.injector.packet.PacketID;

public class PacketI01Metadata implements Packet {
    private String username;
    private String passwd;
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
