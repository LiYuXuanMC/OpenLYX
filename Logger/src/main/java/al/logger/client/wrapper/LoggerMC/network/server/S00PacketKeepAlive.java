package al.logger.client.wrapper.LoggerMC.network.server;


import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S00PacketKeepAlive", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketKeepAlive", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class S00PacketKeepAlive extends Packet {
@ClassInstance    
public static Class<?> S00PacketKeepAliveClass;

    public S00PacketKeepAlive(Object obj) {
        super(obj);
    }

    public static boolean isS00PacketKeepAlive(Packet packet) {
        return S00PacketKeepAliveClass.isInstance(packet.getWrappedObject());
    }


}
