package com.reflectmc.reflect.event.events;

import com.reflectmc.reflect.event.Event;
import lombok.Getter;

public class EventRender extends Event {
    @Getter
    private final float partialTicks;
    public EventRender(float partialTicks){
        this.partialTicks = partialTicks;
    }
}
