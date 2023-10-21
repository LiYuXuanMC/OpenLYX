package al.nya.proxy.network;

import al.nya.proxy.Logger;
import al.nya.proxy.utils.DataUtils;
import al.nya.proxy.utils.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

public class PacketInitializer extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
        msg.markReaderIndex();
        byte[] lvt_4_1_ = new byte[3];

        for (int lvt_5_1_ = 0; lvt_5_1_ < lvt_4_1_.length; ++lvt_5_1_) {
            if (!msg.isReadable()) {
                msg.resetReaderIndex();
                return;
            }

            lvt_4_1_[lvt_5_1_] = msg.readByte();
            if (lvt_4_1_[lvt_5_1_] >= 0) {
                PacketBuffer lvt_6_1_ = new PacketBuffer(Unpooled.wrappedBuffer(lvt_4_1_));

                try {
                    int lvt_7_1_ = lvt_6_1_.readVarIntFromBuffer();
                    //System.out.println(lvt_7_1_);
                    if (msg.readableBytes() >= lvt_7_1_) {
                        //System.out.println("PacketInitializer: " + lvt_7_1_ + " bytes");
                        out.add(new PacketBuffer(msg.readBytes(lvt_7_1_)));
                        return;
                    }

                    msg.resetReaderIndex();
                } finally {
                    lvt_6_1_.release();
                }

                return;
            }
        }

        throw new CorruptedFrameException("length wider than 21-bit");
    }
}
