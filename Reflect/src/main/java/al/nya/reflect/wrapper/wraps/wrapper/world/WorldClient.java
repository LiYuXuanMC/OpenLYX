package al.nya.reflect.wrapper.wraps.wrapper.world;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.multiplayer.WorldClient",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.multiplayer.WorldClient",targetMap = Maps.Srg1_12_2)
public class WorldClient extends World {
    @WrapClass(mcpName = "net.minecraft.client.multiplayer.WorldClient", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.multiplayer.WorldClient", targetMap = Maps.Srg1_12_2)
    public static Class WorldClientClass;
    @WrapMethod(mcpName = "getEntityByID", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getEntityByID", targetMap = Maps.Srg1_12_2)
    public static Method getEntityByID;
    @WrapMethod(mcpName = "sendQuittingDisconnectingPacket", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "sendQuittingDisconnectingPacket", targetMap = Maps.Srg1_12_2)
    public static Method sendQuittingDisconnectingPacket;
    @WrapMethod(mcpName = "addEntityToWorld", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "addEntityToWorld", targetMap = Maps.Srg1_12_2)
    public static Method addEntityToWorld;
    @WrapMethod(mcpName = "removeEntityFromWorld", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "removeEntityFromWorld", targetMap = Maps.Srg1_12_2)
    public static Method removeEntityFromWorld;

    public WorldClient(Object obj) {
        super(obj);
    }

    public Entity getEntityByID(int id) {
        return new Entity(ReflectUtil.invoke(getEntityByID, getWrapObject(), id));
    }

    public void sendQuittingDisconnectingPacket() {
        invoke(sendQuittingDisconnectingPacket);
    }

    public void addEntityToWorld(int p_73027_1_, Entity p_73027_2_) {
        invoke(addEntityToWorld, p_73027_1_, p_73027_2_.getWrapObject());
    }

    public Entity removeEntityFromWorld(int p_73028_1_) {
        return new Entity(invoke(removeEntityFromWorld, p_73028_1_));
    }
}
