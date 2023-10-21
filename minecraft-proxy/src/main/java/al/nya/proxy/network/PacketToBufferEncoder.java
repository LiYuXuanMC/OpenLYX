package al.nya.proxy.network;

import al.nya.proxy.network.packets.Packet;
import al.nya.proxy.network.packets.PacketBuilder;
import al.nya.proxy.utils.ChannelAttribute;
import al.nya.proxy.utils.PacketBuffer;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class PacketToBufferEncoder extends MessageToMessageEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        ProxyClient client = (ProxyClient) ctx.channel().attr(ChannelAttribute.CLIENT_BINDING).get();
        PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
        buffer.writeVarIntToBuffer(PacketBuilder.getIdByPacket(client.getProtocolVersion(),msg));
        PacketBuilder.writeToBuffer(buffer,msg);
        out.add(buffer);
    }
}
