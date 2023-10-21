package al.logger.client.event.client;

import al.logger.client.event.CancelableEvent;
import al.logger.client.wrapper.LoggerMC.utils.text.IChatComponent;
import lombok.Getter;

public class EventCloseChannel extends CancelableEvent {
    @Getter
    private final IChatComponent reason;
    public EventCloseChannel(IChatComponent reason){
        this.reason = reason;
    }
}
