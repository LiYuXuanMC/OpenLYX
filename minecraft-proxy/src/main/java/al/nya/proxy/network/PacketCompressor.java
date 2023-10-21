package al.nya.proxy.network;

import al.nya.proxy.utils.ChannelAttribute;
import al.nya.proxy.utils.PacketBuffer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class PacketCompressor extends MessageToMessageEncoder<PacketBuffer> {
    @Override
    protected void encode(ChannelHandlerContext ctx, PacketBuffer msg, List<Object> out) throws Exception {
        if (ctx.channel().hasAttr(ChannelAttribute.COMPRESSION) && (boolean)(ctx.channel().attr(ChannelAttribute.COMPRESSION).get())){
        } else {
            out.add(msg);
        }
    }
}
