package al.logger.client.event.client.mac;

import al.logger.client.event.CancelableEvent;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import lombok.Getter;

public class EventMACProcessPacket extends CancelableEvent {
    @Getter
    private final Packet packet;
    public EventMACProcessPacket(Packet packet){
        this.packet = packet;
    }
}
