package al.nya.reflect.socket.handlers;

import al.nya.reflect.socket.packets.Packet;

import java.util.Map;

public interface IHandler {
    void processPacket(Packet packet);

    Class<? extends Packet> getPacketByName(String name);

    String getNameByPacket(Class<? extends Packet> packet);
}
