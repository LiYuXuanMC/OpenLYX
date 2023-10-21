package al.nya.reflect.socket.handlers;

import al.nya.reflect.socket.packets.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventHandler implements IHandler {
    private List<Consumer<? super Packet>> listeners = new ArrayList<>();

    @Override
    public void processPacket(Packet packet) {
        synchronized (listeners) {
            for (Consumer<? super Packet> listener : listeners) {
                listener.accept(packet);
            }
        }
    }

    public void addListener(Consumer<? super Packet> listener) {
        listeners.add(listener);
    }

    @Override
    public Class<? extends Packet> getPacketByName(String name) {
        return null;
    }

    @Override
    public String getNameByPacket(Class<? extends Packet> packet) {
        return null;
    }
}
