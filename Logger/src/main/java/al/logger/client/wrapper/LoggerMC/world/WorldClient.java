package al.logger.client.wrapper.LoggerMC.world;

import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.multiplayer.WorldClient",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.multiplayer.WorldClient",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class WorldClient extends World {

    @ClassInstance
    public static Class WorldClientClass;
    @WrapMethod(mcpName = "getEntityByID", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getEntityByID", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getEntityByID;
    @WrapMethod(mcpName = "sendQuittingDisconnectingPacket", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "sendQuittingDisconnectingPacket", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method sendQuittingDisconnectingPacket;
    @WrapMethod(mcpName = "addEntityToWorld", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "addEntityToWorld", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method addEntityToWorld;
    @WrapMethod(mcpName = "removeEntityFromWorld", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "removeEntityFromWorld", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method removeEntityFromWorld;
    public WorldClient(Object obj) {
        super(obj);
    }
    public Entity getEntityByID(int id) {
        return new Entity(invoke(getEntityByID, id));
    }

    public void sendQuittingDisconnectingPacket() {
        invoke(sendQuittingDisconnectingPacket);
    }

    public void addEntityToWorld(int p_73027_1_, Entity p_73027_2_) {
        invoke(addEntityToWorld, p_73027_1_, p_73027_2_.getWrappedObject());
    }

    public Entity removeEntityFromWorld(int p_73028_1_) {
        return new Entity(invoke(removeEntityFromWorld, p_73028_1_));
    }

}
