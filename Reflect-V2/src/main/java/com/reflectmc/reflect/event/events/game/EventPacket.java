package com.reflectmc.reflect.event.events.game;

import com.reflectmc.reflect.event.EventType;
import com.reflectmc.reflect.event.events.EventCancelable;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.Packet;
import lombok.Getter;

public class EventPacket extends EventCancelable {
    @Getter
    private Packet packet;
    @Getter
    private EventType type;
    public EventPacket(Packet packet, EventType type){
        this.packet = packet;
        this.type = type;
    }

}
