package com.reflectmc.loader.packet;

public interface Packet {
    void process(PacketBuffer buffer);
    PacketBuffer create();
}
