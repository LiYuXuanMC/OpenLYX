package pub.ensemble.hillo.handlers;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import pub.ensemble.hillo.CoreMod;
import pub.ensemble.hillo.EACMain;
import pub.ensemble.hillo.core.EACSMgr;
import pub.ensemble.hillo.messages.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ScheduledExecutorService;

public class EACCoreHandler implements IMessageHandler<IMessage, IMessage> {

    @Override
    public IMessage onMessage(IMessage message, MessageContext ctx) {
        if (!(System.getSecurityManager() instanceof EACSMgr)) {
            Runtime.getRuntime().halt(0);
            Minecraft.getMinecraft().shutdown();
            Minecraft.getMinecraft().shutdownMinecraftApplet();
        }
        if (!Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
            Minecraft.getMinecraft().addScheduledTask(() -> onMessage(message, ctx));
            return null;
        }
        if (message instanceof SPacketHandShake) {
            try {
                CoreMod.instance.coreName = ((SPacketHandShake) message).sendHash;
                CoreMod.instance.coreOffset = getSha256(CoreMod.instance.coreName + "JtX2Z3Y4OmR5U6aV7bW8cX9dY0eA1fB2");
                CoreMod.instance.coreName16 = CoreMod.instance.coreName.substring(0, 16);
                CoreMod.instance.coreOffset16 = CoreMod.instance.coreOffset.substring(0, 16);
                CoreMod.instance.networkChannel.sendToServer(new CPacketHandShake(CoreMod.instance.coreOffset, CoreMod.eac.getAuthenticationBody(Minecraft.getMinecraft().thePlayer.getName(), CoreMod.instance.coreName)));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        } else if (message instanceof SPacketPing) {
            CoreMod.instance.networkChannel.sendToServer(new CPacketPing());
        } else if (message instanceof ConvertPacket) {
            ConvertPacket convertPacket = (ConvertPacket) message;
            String str = "Hello,World!";
            ByteBuffer byteBuffer = ByteBuffer.allocate(4 + str.length()).putInt(str.length()).put(str.getBytes(StandardCharsets.UTF_8));
            CoreMod.instance.networkChannel.sendToServer(new ConvertPacket(9, byteBuffer));
        }
        return null;
    }

    public static String getSha256(String str) throws NoSuchAlgorithmException {
        MessageDigest object = MessageDigest.getInstance("SHA-256");
        byte[] hash = object.digest(str.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
