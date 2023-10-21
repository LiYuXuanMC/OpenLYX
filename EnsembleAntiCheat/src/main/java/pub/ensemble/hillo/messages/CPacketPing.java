package pub.ensemble.hillo.messages;

import by.radioegor146.nativeobfuscator.Native;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Native
public class CPacketPing implements IMessage {

    public UUID readUUID;
    public long timestamp;

    public CPacketPing() {
        this.timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        long mostSignificantBits = timestamp >>> 4;
        long leastSignificantBits = date.getTime() << 32;
        this.readUUID = new UUID(mostSignificantBits, leastSignificantBits);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
        String uuid = readUUID.toString();
        buf.writeInt(uuid.length());
        buf.writeBytes(uuid.getBytes(StandardCharsets.UTF_8));
        buf.writeLong(timestamp);
    }
}