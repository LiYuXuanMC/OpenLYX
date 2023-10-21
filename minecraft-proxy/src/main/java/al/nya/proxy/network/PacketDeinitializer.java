package al.nya.proxy.network;

import al.nya.proxy.utils.DataUtils;
import al.nya.proxy.utils.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class PacketDeinitializer extends MessageToByteEncoder<PacketBuffer> {
    @Override
    protected void encode(ChannelHandlerContext ctx, PacketBuffer msg, ByteBuf out) throws Exception {
        DataUtils.putVarInt(out,msg.array().length);
        out.writeBytes(msg.array());
    }
}
