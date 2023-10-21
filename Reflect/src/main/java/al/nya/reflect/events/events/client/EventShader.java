package al.nya.reflect.events.events.client;

import al.nya.reflect.events.events.Event;
import lombok.Getter;

public class EventShader extends Event {
    @Getter
    public final boolean bloom;

    public EventShader(boolean bloom) {
        this.bloom = bloom;
    }
}
