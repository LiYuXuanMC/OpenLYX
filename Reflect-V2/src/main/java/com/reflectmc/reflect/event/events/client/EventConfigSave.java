package com.reflectmc.reflect.event.events.client;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class EventConfigSave extends EventConfig{

    public EventConfigSave() {
        super(new HashMap<>());
    }
    public void putCustomConfig(String property,JsonObject object){
        getObjectMap().put(property,object);
    }
}
