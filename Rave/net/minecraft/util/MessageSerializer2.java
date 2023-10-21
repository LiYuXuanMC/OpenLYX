package net.minecraft.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.minecraft.network.PacketBuffer;

public class MessageSerializer2 extends MessageToByteEncoder<ByteBuf>
{
    protected void encode(ChannelHandlerContext p_encode_1_, ByteBuf p_encode_2_, ByteBuf p_encode_3_) throws Exception
    {
        int i = p_encode_2_.readableBytes();
        int j = PacketBuffer.getVarIntSize(i);

        if (j > 3)
        {
            throw new IllegalArgumentException("unable to fit " + i + " into " + 3);
        }
        else
        {
            PacketBuffer packetbuffer = new PacketBuffer(p_encode_3_);
            packetbuffer.ensureWritable(j + i);
            packetbuffer.writeVarIntToBuffer(i);
            packetbuffer.writeBytes(p_encode_2_, p_encode_2_.readerIndex(), i);
        }
    }
}
