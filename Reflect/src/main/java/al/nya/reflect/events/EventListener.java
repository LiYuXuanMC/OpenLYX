package al.nya.reflect.events;

import al.nya.reflect.events.events.Event;
import al.nya.reflect.features.modules.Module;

public class EventListener {
    private Class<? extends Event> requireEvent;
    private EventReceiver receive;
    private Module from;
    public EventListener(Module from, Class<? extends Event> requireEvent, EventReceiver receive){
        this.requireEvent = requireEvent;
        this.receive = receive;
        this.from = from;
    }

    public Class<? extends Event> getRequireEvent() {
        return requireEvent;
    }

    public EventReceiver getReceive() {
        return receive;
    }

    public Module getFrom() {
        return from;
    }
}
