package al.logger.client.wrapper.LoggerMC.network.server;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S27PacketExplosion",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketExplosion",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class S27PacketExplosion extends Packet {
@ClassInstance    
public static Class S27PacketExplosionClass;
    public S27PacketExplosion(Object obj) {
        super(obj);
    }
    public static boolean isS27PacketExplosion(Packet p){
        return S27PacketExplosionClass.isAssignableFrom(p.getWrappedObject().getClass());
    }
}
