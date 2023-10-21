package al.nya.proxy.network.packets.login.client;

import al.nya.proxy.Logger;
import al.nya.proxy.network.EnumConnectionState;
import al.nya.proxy.network.EnumPacketDirection;
import al.nya.proxy.network.ProxyClient;
import al.nya.proxy.network.packets.Packet;
import al.nya.proxy.network.packets.annotations.PacketField;
import al.nya.proxy.utils.EnumDataType;
import lombok.Getter;
import lombok.Setter;

public class C00PacketLoginStart extends Packet {

    @Getter
    @Setter
    @PacketField(seq = 0, type = EnumDataType.String)
    private String profile;

    @Override
    public EnumPacketDirection getDirection(ProxyClient client) {
        return EnumPacketDirection.SERVERBOUND;
    }

    @Override
    public Packet processPacket(ProxyClient client) {
        client.setState(EnumConnectionState.Play);

        Logger.info("Login to minecraft success (" + this.profile + ")");
        return this;
    }
}
