package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.GameProfile;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.network.NetworkPlayerInfo",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.network.NetworkPlayerInfo",targetMap = Maps.Srg1_12_2)
public class NetworkPlayerInfo extends IWrapper {
    @WrapField(mcpName = "gameProfile",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "gameProfile",targetMap = Maps.Srg1_12_2)
    public static Field gameProfile;
    public NetworkPlayerInfo(Object obj) {
        super(obj);
    }
    public GameProfile getGameProfile(){
        return new GameProfile(ReflectUtil.getField(gameProfile,getWrapObject()));
    }
}
