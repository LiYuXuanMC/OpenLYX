package com.reflectmc.reflect.event.events.game;

import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.wrapper.wrappers.lwjgl.KeyBoard;

public class EventKey extends Event {
    private final int key;

    public EventKey() {
        this.key = KeyBoard.getEventKey() == 0 ? KeyBoard.getEventCharacter() + 256 : KeyBoard.getEventKey();
    }

    public int getKey() {
        return key;
    }
}
