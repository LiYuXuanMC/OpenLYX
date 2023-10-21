package com.reflectmc.verify.packet;

import com.reflectmc.verify.packet.client.*;
import lombok.Getter;

public enum PacketID {
    PacketC00Hello(PacketType.Client,new byte[]{0x00,0x00}, C00Hello.class),
    PacketS00Hello(PacketType.Server,new byte[]{0x00,0x00}, S00Hello.class),
    PacketC01Handshake(PacketType.Client,new byte[]{0x00,0x01}, C01Handshake.class),
    PacketS01UpdateKey(PacketType.Server,new byte[]{0x00,0x01}, S01UpdateKey.class),
    PacketC02Login(PacketType.Client,new byte[]{0x00,0x02}, C02Login.class),
    PacketS02LoginResponse(PacketType.Server,new byte[]{0x00,0x02}, S02LoginResponse.class),
    PacketC03Pong(PacketType.Client,new byte[]{0x00,0x03}, C03Pong.class),
    PacketS03Ping(PacketType.Server,new byte[]{0x00,0x03}, S03Ping.class),
    PacketC04RequestDownloadResource(PacketType.Client,new byte[]{0x00,0x04}, C04RequestDownloadResource.class),
    PacketS04ServerData(PacketType.Server,new byte[]{0x00,0x04}, S04ServerData.class),
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
