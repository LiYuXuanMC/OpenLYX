package pub.ensemble.hillo.messages;


import by.radioegor146.nativeobfuscator.Native;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import java.nio.charset.StandardCharsets;

@Native
public class SPacketHandShake implements IMessage {

    public String sendHash;

    @Override
    public void fromBytes(ByteBuf buf) {
        int packetSize = buf.readInt();
        byte[] saltBytes = new byte[packetSize];
        buf.readBytes(saltBytes);
        this.sendHash = new String(saltBytes, StandardCharsets.UTF_8);
    }

    @Override
    public void toBytes(ByteBuf buf) {

    }
}
