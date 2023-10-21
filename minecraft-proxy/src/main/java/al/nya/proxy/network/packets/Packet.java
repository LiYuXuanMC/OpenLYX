package al.nya.proxy.network.packets;

import al.nya.proxy.network.EnumPacketDirection;
import al.nya.proxy.network.ProxyClient;

public abstract class Packet {

    public EnumPacketDirection getDirection(ProxyClient client){
        return EnumPacketDirection.CLIENTBOUND;
    }

    public abstract Packet processPacket(ProxyClient client);

}
