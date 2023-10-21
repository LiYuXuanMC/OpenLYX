package al.logger.client.event.client;

import al.logger.client.event.CancelableEvent;
import al.logger.client.event.EventType;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import lombok.Getter;

public class EventPacket extends CancelableEvent {
    @Getter
    private Packet packet;
    @Getter
    private EventType type;
    public EventPacket(Packet packet, EventType type){
        this.packet = packet;
        this.type = type;
    }

}
