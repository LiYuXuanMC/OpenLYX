package com.reflectmc.verify.packet;

public interface Packet {
    void process(PacketBuffer buffer);
    PacketBuffer create();
}
