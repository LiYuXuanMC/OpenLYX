package al.logger.client.wrapper.LoggerMC.network.server;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S0CPacketSpawnPlayer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketSpawnPlayer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class S0CPacketSpawnPlayer extends Packet {
@ClassInstance
public static Class S0CPacketSpawnPlayerClass;

    @WrapField(mcpName = "entityId",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "entityId",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field entityID;
    @WrapField(mcpName = "x",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "x",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    private static Field x;
    @WrapField(mcpName = "y",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "y",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    private static Field y;
    @WrapField(mcpName = "z",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "z",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    private static Field z;
    public S0CPacketSpawnPlayer(Object obj) {
        super(obj);
    }

    public static boolean isS0CPacketSpawnPlayer(Packet packet) {
        return S0CPacketSpawnPlayerClass.isInstance(packet.getWrappedObject());
    }

    public int getEntityID(){
        return (int) ReflectUtil.getField(entityID,getWrappedObject());
    }

    public int getX(){
        return (int) ReflectUtil.getField(x,getWrappedObject());
    }
    public int getY(){
        return (int) ReflectUtil.getField(y,getWrappedObject());
    }
    public int getZ(){
        return (int) ReflectUtil.getField(z,getWrappedObject());
    }

}
