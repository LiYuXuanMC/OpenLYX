package com.reflectmc.loader.packet.irc;

import com.reflectmc.loader.packet.Packet;
import com.reflectmc.loader.packet.PacketBuffer;
import com.reflectmc.loader.packet.PacketID;
import lombok.Getter;

public class C02Login implements Packet {
    @Getter
    private String username;
    @Getter
    private String passwd;
    @Getter
    private Role role;
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
        int roleInt = buffer.readInt();
        for (Role value : Role.values()) {
            if (value.ordinal() == roleInt){
                role = value;
            }
        }
    }

    @Override
    public PacketBuffer create() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeByte(PacketID.PacketC02Login.getSeq());
        buffer.writeString(username);
        buffer.writeString(passwd);
        buffer.writeInt(Role.Game.ordinal());
        return buffer;
    }
    public enum Role {
        Injector,
        Game,
    }
}
