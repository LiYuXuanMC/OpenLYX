package al.nya.proxy.network.packets.handshake;

import al.nya.proxy.Logger;
import al.nya.proxy.network.EnumConnectionState;
import al.nya.proxy.network.EnumPacketDirection;
import al.nya.proxy.network.ProxyClient;
import al.nya.proxy.network.packets.Packet;
import al.nya.proxy.network.packets.annotations.PacketField;
import al.nya.proxy.utils.EnumDataType;
import lombok.Getter;

public class C00Handshaking extends Packet {
    @PacketField(seq = 0, type = EnumDataType.VarInt)
    @Getter
    private int protocolVersion;
    @PacketField(seq = 1, type = EnumDataType.String)
    @Getter
    private String serverAddress;
    @PacketField(seq = 2, type = EnumDataType.UnsignedShort)
    @Getter
    private short serverPort;
    @PacketField(seq = 3, type = EnumDataType.Enum, enumClass = EnumConnectionState.class)
    @Getter
    private EnumConnectionState nextState;

    @Override
    public EnumPacketDirection getDirection(ProxyClient client) {
        return EnumPacketDirection.SERVERBOUND;
    }

    @Override
    public Packet processPacket(ProxyClient client) {
        client.setState(this.getNextState());
        return this;
    }
}
