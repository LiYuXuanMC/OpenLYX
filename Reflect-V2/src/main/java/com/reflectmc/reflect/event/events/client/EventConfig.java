package com.reflectmc.reflect.event.events.client;

import com.google.gson.JsonObject;
import com.reflectmc.reflect.event.Event;
import lombok.Getter;

import java.util.Map;

public class EventConfig extends Event {
    @Getter
    private Map<String,JsonObject> objectMap;
    public EventConfig(Map<String, JsonObject> objectMap){
        this.objectMap = objectMap;
    }
}
