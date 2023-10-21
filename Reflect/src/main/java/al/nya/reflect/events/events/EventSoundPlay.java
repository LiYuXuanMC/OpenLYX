package al.nya.reflect.events.events;

import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;

public class EventSoundPlay extends Event {
    private Entity entity;
    private String name;
    public EventSoundPlay(Entity entity, String name){
        this.entity = entity;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Entity getEntity() {
        return entity;
    }
}
