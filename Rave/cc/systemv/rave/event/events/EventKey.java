package cc.systemv.rave.event.events;

import cc.systemv.rave.event.Event;
import org.lwjgl.input.Keyboard;

public class EventKey extends Event {
    private final int key;

    public EventKey() {
        this.key = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
    }

    public int getKey() {
        return key;
    }
}
