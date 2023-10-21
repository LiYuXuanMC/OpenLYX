package al.logger.client.event.client.world;

import al.logger.client.event.Event;
import al.logger.client.wrapper.LoggerMC.world.WorldClient;

public class EventLoadWorld implements Event {
    private WorldClient newWorld;
    public EventLoadWorld(WorldClient newWorld) {
        this.newWorld = newWorld;
    }
    public WorldClient getNewWorld() {
        return newWorld;
    }
}
