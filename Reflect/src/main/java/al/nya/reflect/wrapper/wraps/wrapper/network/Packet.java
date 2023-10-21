package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.network.Packet",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.Packet",targetMap = Maps.Srg1_12_2)
public class Packet extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.network.Packet", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.Packet", targetMap = Maps.Srg1_12_2)
    public static Class<?> PacketClass;
    @WrapMethod(mcpName = "processPacket", targetMap = Maps.Srg1_8_9)
    public static Method processPacket;
    public Packet(Object obj) {
        super(obj);
    }
    public boolean isPacket(Class c){
        return c.isInstance(getWrapObject());
    }
    public void processPacket(INetHandler handler){
        invoke(processPacket, handler.getWrapObject());
    }
}
