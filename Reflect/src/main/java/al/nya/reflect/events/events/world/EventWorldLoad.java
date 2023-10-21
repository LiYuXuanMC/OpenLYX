package al.nya.reflect.events.events.world;

import al.nya.reflect.events.annotation.Realized;
import al.nya.reflect.wrapper.wraps.wrapper.world.World;
@Realized
public class EventWorldLoad extends EventWorld{
    private World world;
    public EventWorldLoad(World world){
        this.world = world;
    }

    public World getWorld() {
        return world;
    }
}
