package pub.ensemble.hillo.messages;

import io.netty.buffer.ByteBuf;
import pub.ensemble.hillo.objects.WrapEntity;

import java.io.IOException;

public interface IMessage {
    IMessage onMessage(WrapEntity player);

    void read(ByteBuf buf, WrapEntity player) throws IOException;

    void write(ByteBuf buf) throws IOException;
}
