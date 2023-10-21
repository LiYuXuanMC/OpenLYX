package al.logger.client.wrapper.LoggerMC.network.server;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S07PacketRespawn", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketRespawn", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class S07PacketRespawn extends Packet {
@ClassInstance
public static Class<?> S07PacketRespawnClass;

    public S07PacketRespawn(Object obj) {
        super(obj);
    }

    public static boolean isS07PacketRespawn(Packet packet) {
        return S07PacketRespawnClass.isInstance(packet.getWrappedObject());
    }

}
