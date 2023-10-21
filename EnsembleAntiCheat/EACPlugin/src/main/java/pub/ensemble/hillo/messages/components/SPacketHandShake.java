package pub.ensemble.hillo.messages.components;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import pub.ensemble.hillo.messages.IMessage;
import pub.ensemble.hillo.objects.WrapEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SPacketHandShake implements IMessage {

    @Getter
    private final String sendHash;

    public SPacketHandShake(String sendHash) {
        this.sendHash = sendHash;
    }

    @Override
    public IMessage onMessage(WrapEntity player) {
        return null;
    }

    @Override
    public void read(ByteBuf buf,WrapEntity player) throws IOException {

    }

    @Override
    public void write(ByteBuf buf) throws IOException {
        buf.writeInt(sendHash.length());
        buf.writeBytes(sendHash.getBytes(StandardCharsets.UTF_8));
    }
}
