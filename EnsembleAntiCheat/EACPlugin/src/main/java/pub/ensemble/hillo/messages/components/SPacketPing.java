package pub.ensemble.hillo.messages.components;

import io.netty.buffer.ByteBuf;
import pub.ensemble.hillo.messages.IMessage;
import pub.ensemble.hillo.objects.WrapEntity;

import java.io.IOException;

public class SPacketPing implements IMessage {

    public SPacketPing() {
    }

    @Override
    public IMessage onMessage(WrapEntity player) {
        return null;
    }

    @Override
    public void read(ByteBuf buf, WrapEntity player) throws IOException {

    }

    @Override
    public void write(ByteBuf buf) throws IOException {
    }
}
