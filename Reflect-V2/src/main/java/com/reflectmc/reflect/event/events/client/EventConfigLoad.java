package com.reflectmc.reflect.event.events.client;

import com.google.gson.JsonObject;

import java.util.Map;

public class EventConfigLoad extends EventConfig{
    public EventConfigLoad(Map<String, JsonObject> objectMap) {
        super(objectMap);
    }
}
