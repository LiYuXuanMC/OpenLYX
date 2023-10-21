package al.nya.reflect.events.events;


import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import lombok.Getter;
import lombok.Setter;

public class EventNameTag extends Event {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Entity entity;

    public EventNameTag(Entity entity, String name) {
        this.name = name;
        this.entity = entity;
    }
}
