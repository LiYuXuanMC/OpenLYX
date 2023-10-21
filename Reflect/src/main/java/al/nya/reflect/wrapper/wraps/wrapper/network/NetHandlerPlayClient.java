package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.client.network.NetHandlerPlayClient", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.network.NetHandlerPlayClient", targetMap = Maps.Srg1_12_2)
public class NetHandlerPlayClient extends INetHandlerPlayClient {
    @WrapField(mcpName = "netManager", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "netManager", targetMap = Maps.Srg1_12_2)
    public static Field netManager;
    @WrapMethod(mcpName = "getPlayerInfoMap", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getPlayerInfoMap", targetMap = Maps.Srg1_12_2)
    public static Method getPlayerInfoMap;
    @WrapField(mcpName = "doneLoadingTerrain", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "doneLoadingTerrain", targetMap = Maps.Srg1_12_2)
    public static Field doneLoadingTerrain;

    public NetHandlerPlayClient(Object obj) {
        super(obj);
    }

    public void addToSendQueue(Packet packet) {
        getNetworkManager().sendPacket(packet);
    }

    public NetworkManager getNetworkManager() {
        return new NetworkManager(ReflectUtil.getField(netManager, getWrapObject()));
    }

    public List<NetworkPlayerInfo> getPlayerInfoMap() {
        Collection<Object> objects = (Collection<Object>) ReflectUtil.invoke(getPlayerInfoMap, getWrapObject());
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
