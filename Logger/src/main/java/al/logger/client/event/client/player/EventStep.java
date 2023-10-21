package al.logger.client.event.client.player;


import al.logger.client.event.Event;
import al.logger.client.event.EventType;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;

public class EventStep implements Event {
    private float height;
    private final EventType eventType;

    public EventStep(EventType eventType, float height) {
        this.eventType = eventType;
        this.height = height;
    }
    @ExportObfuscate(name = "getHeight")
    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public EventType getEventType() {
        return eventType;
    }
}