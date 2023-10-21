package cc.systemv.rave.event.events;


import cc.systemv.rave.event.Event;
import lombok.Getter;

@Getter
public class EventRender extends Event {
    private final float partialTicks;
    public EventRender(float partialTicks){
        this.partialTicks = partialTicks;
    }
}
