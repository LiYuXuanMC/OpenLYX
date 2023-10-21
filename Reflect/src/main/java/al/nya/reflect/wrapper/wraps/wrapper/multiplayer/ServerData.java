package al.nya.reflect.wrapper.wraps.wrapper.multiplayer;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.multiplayer.ServerData",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.multiplayer.ServerData",targetMap = Maps.Srg1_12_2)
public class ServerData extends IWrapper {
    @WrapField(mcpName = "serverIP",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "serverIP",targetMap = Maps.Srg1_12_2)
    public static Field serverIP;
    public ServerData(Object obj) {
        super(obj);
    }
    public String getServerIP(){
        return (String) getField(serverIP);
    }
}
