package al.nya.reflect.events.events;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.EventType;
import al.nya.reflect.events.annotation.Realized;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;
import al.nya.reflect.wrapper.wraps.wrapper.network.S08.S08PacketPlayerPosLook;
@Realized
public class EventPacket extends Event {
    private final EventType eventType;
    public Packet packet;

    public EventPacket(EventType eventType, Packet packet) {
        this.eventType = eventType;
        this.packet = packet;
        if (this.packet.isPacket(S08PacketPlayerPosLook.S08PacketPlayerPosLookClass)) {
            Reflect.Instance.eventBus.callEvent(new EventPullback());
        }
    }

    public EventType getEventType() {
        return eventType;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }
}


