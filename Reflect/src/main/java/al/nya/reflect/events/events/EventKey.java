package al.nya.reflect.events.events;

import al.nya.reflect.events.annotation.Realized;
import al.nya.reflect.utils.LWJGLKeyBoard;

@Realized
public class EventKey extends Event {
    private final int key;

    public EventKey() {
        this.key = LWJGLKeyBoard.getEventKey() == 0 ? LWJGLKeyBoard.getEventCharacter() + 256 : LWJGLKeyBoard.getEventKey();
    }

    public int getKey() {
        return key;
    }
}
