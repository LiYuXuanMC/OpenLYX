package al.nya.proxy.network.packets;

import al.nya.proxy.network.packets.handshake.C00Handshaking;
import al.nya.proxy.network.packets.handshake.S00PacketServerInfo;
import al.nya.proxy.network.packets.login.client.C00PacketLoginStart;

import java.util.ArrayList;
import java.util.List;

public class PacketAccess {

    public static List<Class<?>> packetList = new ArrayList<>();

    static {
        packetList.add(C00Handshaking.class);
        packetList.add(S00PacketServerInfo.class);
        packetList.add(C00PacketLoginStart.class);
    }

}
