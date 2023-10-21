package al.logger.client.utils.network;

import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.network.Packet;

public class PacketUtil {

    public static void sendPacket(Packet packet){
        Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacket(packet);
    }

    public static void sendPacketNoEvent(Packet packet){
        Minecraft.getMinecraft().getNetHandler().getNetworkManager().sendPacketNoEvent(packet);
    }
}
