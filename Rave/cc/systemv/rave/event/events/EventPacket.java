package cc.systemv.rave.event.events;

import cc.systemv.rave.event.CancelableEvent;
import cc.systemv.rave.event.EventType;
import net.minecraft.network.Packet;

public class EventPacket extends CancelableEvent {
    private Packet packet;
    private EventType type;
    public EventPacket(Packet packet, EventType type){
        this.packet = packet;
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public Packet getPacket() {
        return packet;
    }
}
