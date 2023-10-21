package cc.systemv.rave.event.events;

import cc.systemv.rave.event.Event;
import lombok.Getter;
import lombok.Setter;

public class EventNoSlow extends Event {
    @Getter
    @Setter
    private boolean slow = true;
}
