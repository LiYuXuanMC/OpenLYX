package al.logger.client.event.client;

import al.logger.client.event.Event;
import lombok.Getter;
import lombok.Setter;

public class EventMouse implements Event {

    @Getter
    @Setter
    private int button;
    @Getter
    @Setter
    private boolean down;

    public EventMouse(int button, boolean down) {
        this.button = button;
        this.down = down;
    }

}
