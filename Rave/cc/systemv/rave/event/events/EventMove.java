package cc.systemv.rave.event.events;

import cc.systemv.rave.event.CancelableEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventMove extends CancelableEvent {
    private double x,y,z;
}
