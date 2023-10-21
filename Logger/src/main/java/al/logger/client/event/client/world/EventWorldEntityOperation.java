package al.logger.client.event.client.world;

import al.logger.client.event.Event;
import al.logger.client.event.EventType;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import lombok.Getter;

public class EventWorldEntityOperation implements Event {
    @Getter
    private final EventType type;
    @Getter
    private final int entityId;
    public EventWorldEntityOperation(EventType type, int entityId) {
        this.type = type;
        this.entityId = entityId;
    }
}
