package pub.ensemble.hillo.messages;

import by.radioegor146.nativeobfuscator.Native;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Native
public class CPacketHandShake implements IMessage {

    public String readHash;
    public byte[] authenticationBody;

    public CPacketHandShake() {
    }

    public CPacketHandShake(String readHash,byte[] authenticationBody) {
        this.readHash = readHash;
        this.authenticationBody = authenticationBody;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(readHash.length());
        buf.writeBytes(readHash.getBytes(StandardCharsets.UTF_8));
        buf.writeInt(authenticationBody.length);
        buf.writeBytes(authenticationBody);
    }
}
