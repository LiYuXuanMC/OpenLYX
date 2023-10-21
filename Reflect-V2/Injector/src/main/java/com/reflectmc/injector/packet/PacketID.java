package com.reflectmc.injector.packet;

import com.reflectmc.injector.packet.local.*;
import lombok.Getter;

public enum PacketID {
    G00Hello(PacketType.Game,new byte[]{0x00,0x00}, PacketG00Hello.class),
    I00Hello(PacketType.Injector,new byte[]{0x00,0x00}, PacketI00Hello.class),
    G01RequestMetadata(PacketType.Game,new byte[]{0x00,0x01}, PacketG01RequestMetadata.class),
    I01Metadata(PacketType.Injector,new byte[]{0x00,0x01}, PacketI01Metadata.class),
    G02InjectProgress(PacketType.Game,new byte[]{0x00,0x02}, PacketG02InjectProgress.class),
    ;
    @Getter
    private byte[] seq;
    @Getter
    private Class<? extends Packet> target;
    @Getter
    private PacketType type;
    PacketID(PacketType type,byte[] seq,Class<? extends Packet> target){
        this.type = type;
        this.seq = seq;
        this.target = target;
    }
}
