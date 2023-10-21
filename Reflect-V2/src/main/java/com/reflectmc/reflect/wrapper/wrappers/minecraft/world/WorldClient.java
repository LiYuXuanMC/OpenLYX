package com.reflectmc.reflect.wrapper.wrappers.minecraft.world;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.multiplayer.WorldClient",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.multiplayer.WorldClient",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class WorldClient extends World {
    @ClassInstance
    public static Class WorldClientClass;
    @WrapMethod(deobfName = "getEntityByID", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getEntityByID", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getEntityByID;
    @WrapMethod(deobfName = "sendQuittingDisconnectingPacket", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "sendQuittingDisconnectingPacket", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method sendQuittingDisconnectingPacket;
    @WrapMethod(deobfName = "addEntityToWorld", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "addEntityToWorld", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method addEntityToWorld;
    @WrapMethod(deobfName = "removeEntityFromWorld", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "removeEntityFromWorld", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method removeEntityFromWorld;

    public WorldClient(Object obj) {
        super(obj);
    }

    public Entity getEntityByID(int id) {
        return new Entity(invokeMethod(getEntityByID,id));
    }
    public void sendQuittingDisconnectingPacket() {
        invokeMethod(sendQuittingDisconnectingPacket);
    }
    public void addEntityToWorld(int p_73027_1_, Entity p_73027_2_) {
        invokeMethod(addEntityToWorld, p_73027_1_, p_73027_2_.getWrappedObject());
    }
    public Entity removeEntityFromWorld(int p_73028_1_) {
        return new Entity(invokeMethod(removeEntityFromWorld, p_73028_1_));
    }
}
