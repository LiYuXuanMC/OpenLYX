package com.reflectmc.verify.packet.client;

import com.reflectmc.verify.packet.Packet;
import com.reflectmc.verify.packet.PacketBuffer;
import com.reflectmc.verify.packet.PacketID;
import lombok.Getter;

public class C02Login implements Packet {
    @Getter
    private String username;
    @Getter
    private String passwd;
    public C02Login(){

    }
    public C02Login(String username,String passwd){
        this.username = username;
        this.passwd = passwd;
    }

    @Override
    public void process(PacketBuffer buffer) {
        username = buffer.readString();
        passwd = buffer.readString();
    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(PacketID.PacketC02Login.getSeq());
        buffer.writeString(username);
        buffer.writeString(passwd);
        return buffer;
    }
}
