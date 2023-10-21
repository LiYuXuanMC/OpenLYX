package pub.ensemble.hillo.messages;

import by.radioegor146.nativeobfuscator.Native;
import by.radioegor146.nativeobfuscator.VM;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import pub.ensemble.hillo.CoreMod;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

/**
 * @author JKot.
 * @date 2023/10/3 14:13
 */
@Native
public class ConvertPacket implements IMessage {
    private int packetId;
    private ByteBuffer buffer;

    public ConvertPacket() {
    }

    public ConvertPacket(int packetId, ByteBuffer buffer) {
        this.packetId = packetId;
        this.buffer = buffer;
    }

    public int getPacketId() {
        return packetId;
    }

    public void setPacketId(int packetId) {
        this.packetId = packetId;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    @VM
    public void fromBytes(ByteBuf buf) {
        int length = buf.readUnsignedShort();
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        byte[] crc32r = new byte[4];
        buffer.get(crc32r);
        byte[] packetContent = new byte[length - 4];
        buffer.get(packetContent);
        CRC32 crc32 = new CRC32();
        crc32.update(packetContent);
        byte[] crc32l = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt((int) crc32.getValue()).array();
        for (int i = 0; i < 4; i++) {
            if (crc32l[i] != crc32r[i]) {
                throw new RuntimeException("Error Handshake Packet!");
            }
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(CoreMod.instance.coreOffset16.getBytes(StandardCharsets.UTF_8), "AES"));
            packetContent = cipher.doFinal(packetContent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ByteBuffer result = ByteBuffer.wrap(packetContent);
        this.packetId = result.getInt();
        this.buffer = result;
    }

    @Override
    @VM
    public void toBytes(ByteBuf buf) {
        byte[] packetContent = this.buffer.array();
        packetContent = ByteBuffer.allocate(4 + packetContent.length).putInt(this.packetId).put(packetContent).array();
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(CoreMod.instance.coreName16.getBytes(StandardCharsets.UTF_8), "AES"));
            packetContent = cipher.doFinal(packetContent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        CRC32 crc32 = new CRC32();
        crc32.update(packetContent);
        byte[] crc32l = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt((int) crc32.getValue()).array();
        byte[] bytes = new byte[packetContent.length + 4];
        System.arraycopy(crc32l, 0, bytes, 0, 4);
        System.arraycopy(packetContent, 0, bytes, 4, packetContent.length);
        buf.writeShort(bytes.length);
        buf.writeBytes(bytes);
    }
}
