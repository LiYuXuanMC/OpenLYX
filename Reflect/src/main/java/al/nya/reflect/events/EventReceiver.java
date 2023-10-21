package al.nya.reflect.events;

import al.nya.reflect.events.events.Event;

public interface EventReceiver {
    public void receive(Event event);
}
