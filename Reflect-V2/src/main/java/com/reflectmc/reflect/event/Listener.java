package com.reflectmc.reflect.event;

import lombok.Getter;

import java.util.function.Consumer;

public class Listener{
    @Getter
    private Priority priority;
    @Getter
    private Consumer<Event> consumer;
    public Listener(Priority priority,Consumer<Event> consumer){
        this.priority = priority;
        this.consumer = consumer;
    }
}
