package al.nya.proxy.network.packets;

import al.nya.proxy.network.EnumConnectionState;
import al.nya.proxy.network.packets.handshake.C00Handshaking;
import al.nya.proxy.network.packets.handshake.S00PacketServerInfo;
import al.nya.proxy.network.packets.login.client.C00PacketLoginStart;

import java.util.HashMap;
import java.util.Map;

public class PacketMap {
    public final static Map<Integer, Map<Integer, Class<? extends Packet>[]>> refMap = new HashMap<>();

    static {
        Map<Integer, Class<? extends Packet>[]> map0 = new HashMap<>();
        //0 -> Status
        //We use 0 to represent the status packet
        map0.put(0x00, new Class[]{C00Handshaking.class, S00PacketServerInfo.class});
        refMap.put(0, map0);
        Map<Integer, Class<? extends Packet>[]> map47 = new HashMap<>();
        //47 -> 1.8.x
        //map47.put(0x00, new Class[]{C00Handshaking.class, S00Status.class});
        map47.put(0x00, new Class[]{C00PacketLoginStart.class});
        refMap.put(47, map47);

        Map<Integer, Class<? extends Packet>[]> mapPlay = new HashMap<>();
        refMap.put(EnumConnectionState.Play.i, mapPlay);

    }
}
