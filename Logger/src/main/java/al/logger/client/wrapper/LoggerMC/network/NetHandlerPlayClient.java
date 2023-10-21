package al.logger.client.wrapper.LoggerMC.network;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.client.network.NetHandlerPlayClient", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.network.NetHandlerPlayClient", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class NetHandlerPlayClient extends INetHandlerPlayClient {
    @WrapField(mcpName = "netManager", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "netManager", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field netManager;
    @WrapMethod(mcpName = "getPlayerInfoMap", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getPlayerInfoMap", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getPlayerInfoMap;
    @WrapField(mcpName = "doneLoadingTerrain", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "doneLoadingTerrain", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field doneLoadingTerrain;



    public NetHandlerPlayClient(Object obj) {
        super(obj);
    }

    public void addToSendQueue(Packet packet) {
        getNetworkManager().sendPacket(packet);
    }

    public NetworkManager getNetworkManager() {
        return new NetworkManager(ReflectUtil.getField(netManager, getWrappedObject()));
    }
    public List<NetworkPlayerInfo> getPlayerInfoMap() {
        Collection<Object> objects = (Collection<Object>) ReflectUtil.invoke(getPlayerInfoMap, getWrappedObject());
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
