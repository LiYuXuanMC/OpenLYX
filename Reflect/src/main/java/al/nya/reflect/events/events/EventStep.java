package al.nya.reflect.events.events;


import al.nya.reflect.events.EventType;

public class EventStep extends Event {
    /**
     * The step height.
     */
    private float height;
    private final EventType eventType;

    public EventStep(EventType eventType, float height) {
        this.eventType = eventType;
        this.height = height;
    }

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