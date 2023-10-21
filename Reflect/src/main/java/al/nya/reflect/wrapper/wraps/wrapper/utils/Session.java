package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.GameProfile;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.Session",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.Session",targetMap = Maps.Srg1_12_2)
public class Session extends IWrapper {
    @WrapMethod(mcpName = "getProfile",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getProfile",targetMap = Maps.Srg1_12_2)
    public static Method getProfile;
    public Session(Object obj) {
        super(obj);
    }
    public GameProfile getProfile(){
        return new GameProfile(ReflectUtil.invoke(getProfile,getWrapObject()));
    }
}
