package al.logger.client.event.client;

import al.logger.client.event.CancelableEvent;
import lombok.Getter;

@Deprecated
public class EventSendMessage extends CancelableEvent {
    @Getter
    private String message;
    public EventSendMessage(String message){
        this.message = message;
    }
}
