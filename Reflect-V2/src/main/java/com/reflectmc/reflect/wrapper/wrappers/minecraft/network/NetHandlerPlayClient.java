package com.reflectmc.reflect.wrapper.wrappers.minecraft.network;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WrapperClass(deobfName = "net.minecraft.client.network.NetHandlerPlayClient", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.network.NetHandlerPlayClient", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class NetHandlerPlayClient extends INetHandlerPlayClient {
    @WrapField(deobfName = "netManager", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "netManager", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field netManager;
    @WrapMethod(deobfName = "getPlayerInfoMap", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getPlayerInfoMap", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getPlayerInfoMap;
    @WrapField(deobfName = "doneLoadingTerrain", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "doneLoadingTerrain", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field doneLoadingTerrain;

    public NetHandlerPlayClient(Object obj) {
        super(obj);
    }

    public void addToSendQueue(Packet packet) {
        getNetworkManager().sendPacket(packet);
    }
    public NetworkManager getNetworkManager() {
        return new NetworkManager(getField(netManager));
    }
    public List<NetworkPlayerInfo> getPlayerInfoMap() {
        Collection<Object> objects = (Collection<Object>) invokeMethod(getPlayerInfoMap);
        List<NetworkPlayerInfo> infos = new ArrayList<NetworkPlayerInfo>();
        for (Object object : objects) {
            infos.add(new NetworkPlayerInfo(object));
        }
        return infos;
    }
    public boolean isDoneLoadingTerrain() {
        return (boolean) getField(doneLoadingTerrain);
    }
}
