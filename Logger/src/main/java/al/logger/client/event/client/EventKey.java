package al.logger.client.event.client;


import al.logger.client.event.Event;
import org.lwjgl.input.Keyboard;

public class EventKey implements Event {
    private final int key;

    public EventKey() {
        this.key = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
    }

    public int getKey() {
        return key;
    }
}
