package pub.ensemble.hillo.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import pub.ensemble.hillo.EACCore;
import pub.ensemble.hillo.managers.EntityManager;
import pub.ensemble.hillo.messages.IMessage;
import pub.ensemble.hillo.messages.components.*;
import pub.ensemble.hillo.objects.WrapEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EACHandler implements PluginMessageListener {

    @Getter
    @Setter
    private List<Class<? extends IMessage>> messages = new ArrayList<Class<? extends IMessage>>();

    public EACHandler() {
        //Server
        messages.add(SPacketHandShake.class);
        messages.add(SPacketPing.class);
        messages.add(ConvertPacket.class);

        //Client
        messages.add(CPacketHandShake.class);
        messages.add(CPacketPing.class);
        messages.add(ConvertPacket.class);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!EACCore.CHANNEL.equals(channel) || message.length == 0) return;
        ByteBuf buffer = Unpooled.wrappedBuffer(message);
        try {
            int id = buffer.readByte();
            IMessage packet = messages.get(id).newInstance();
            WrapEntity playerHandle = EntityManager.getWrapEntity(player);
            if (playerHandle != null) {
                packet.read(buffer, playerHandle);
                IMessage retPacket = packet.onMessage(playerHandle);
                if (retPacket != null) sendPacket(player, retPacket);
            }
        } catch (Exception e) {
            player.kickPlayer(e.getMessage());
        } finally {
            buffer.release();
        }
    }

    public void sendPacket(Player player, IMessage packet) {
        ByteBuf buffer = Unpooled.buffer();
        //EACCore.INSTANCE.getLogger().info("Send packet: " + packet.getClass().getSimpleName());
        try {
            buffer.writeByte(messages.indexOf(packet.getClass()));
            packet.write(buffer);
            player.sendPluginMessage(EACCore.INSTANCE, EACCore.CHANNEL, buffer.array());
        } catch (IOException e) {
            player.kickPlayer("发送包出现错误");
        } finally {
            buffer.release();
        }
    }
}
