package com.reflectmc.reflect.event.events.game;

import com.reflectmc.reflect.event.events.EventCancelable;
import lombok.Getter;

public class EventSendMessage extends EventCancelable {
    @Getter
    private String message;
    public EventSendMessage(String message){
        this.message = message;
    }
}
