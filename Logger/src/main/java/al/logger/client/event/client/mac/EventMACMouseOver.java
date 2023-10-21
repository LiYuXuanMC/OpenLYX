package al.logger.client.event.client.mac;

import al.logger.client.event.CancelableEvent;
import lombok.Getter;

public class EventMACMouseOver extends CancelableEvent {
    @Getter
    private final float partialTicks;
    public EventMACMouseOver(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}
