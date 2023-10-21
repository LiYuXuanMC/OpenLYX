package pub.ensemble.hillo.messages.components;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import pub.ensemble.hillo.EACCore;
import pub.ensemble.hillo.enums.EAC_STATUS;
import pub.ensemble.hillo.messages.IMessage;
import pub.ensemble.hillo.objects.WrapEntity;
import pub.ensemble.hillo.utils.VerifyUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class CPacketHandShake implements IMessage {

    @Getter
    @Setter
    private String readHash;

    @Getter
    @Setter
    private byte[] authenticationBody;

    @Override
    public IMessage onMessage(WrapEntity player) {
        try {
            VerifyUtil.verifyAuthenticationBody(this.authenticationBody, player.getPlayer().getName(), player.getHandShakeKey());
            if (this.readHash.equals(player.getEncryptHandShakeKey())) {
                player.setStatus(EAC_STATUS.AUTHENTICATED);
                player.getPlayer().sendMessage("§a§lEAC §7» §f您的客户端已通过EAC认证.");
                EACCore.INSTANCE.getEacHandler().sendPacket(player.getPlayer(), new ConvertPacket(2, ByteBuffer.allocate(4).putInt(256), player));
            } else {
                player.getPlayer().kickPlayer("未通过EAC认证");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void read(ByteBuf buf, WrapEntity player) throws IOException {
        int packetSize = buf.readInt();
        byte[] saltBytes = new byte[packetSize];
        buf.readBytes(saltBytes);
        this.readHash = new String(saltBytes, StandardCharsets.UTF_8);
        int bodySize = buf.readInt();
        this.authenticationBody = new byte[bodySize];
        buf.readBytes(this.authenticationBody);
        this.authenticationBody = VerifyUtil.decryptBody(this.authenticationBody);
    }

    @Override
    public void write(ByteBuf buf) throws IOException {
    }
}
