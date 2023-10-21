package al.logger.client.wrapper.LoggerMC.network.server;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S18PacketEntityTeleport", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketEntityTeleport", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class S18PacketEntityTeleport extends Packet {
@ClassInstance
public static Class S18PacketEntityTeleportClass;
    @WrapField(mcpName = "entityId",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "entityId",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field entityID;
    public S18PacketEntityTeleport(Object obj) {
        super(obj);
    }

    public static boolean isS18PacketEntityTeleport(Packet e) {
        return S18PacketEntityTeleportClass.isInstance(e.getWrappedObject());
    }

    public int getEntityID(){
        return (int) ReflectUtil.getField(entityID,getWrappedObject());
    }
}
