package com.reflectmc.injector.packet;

public interface Packet {
    void process(PacketBuffer buffer);
    PacketBuffer create();
}
