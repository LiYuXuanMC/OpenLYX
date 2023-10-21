package cc.systemv.rave.event.events;

import cc.systemv.rave.event.CancelableEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class EventPreUpdate extends CancelableEvent {
    @Getter
    @Setter
    private boolean sprinting;
    @Getter
    @Setter
    private boolean sneaking;
}
