package al.logger.client.event.client.render;

import al.logger.client.event.CancelableEvent;
import al.logger.client.event.Event;
import lombok.Getter;

public class EventRender extends CancelableEvent {
    @Getter
    private final float partialTicks;
    public EventRender(float partialTicks){
        this.partialTicks = partialTicks;
    }
}
