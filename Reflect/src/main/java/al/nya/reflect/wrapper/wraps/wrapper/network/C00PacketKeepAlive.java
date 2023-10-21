package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketKeepAlive", targetMap = Maps.Srg1_12_2)
@WrapperClass(mcpName = "net.minecraft.network.play.client.C00PacketKeepAlive", targetMap = Maps.Srg1_8_9)
public class C00PacketKeepAlive extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketKeepAlive", targetMap = Maps.Srg1_12_2)
    @WrapClass(mcpName = "net.minecraft.network.play.client.C00PacketKeepAlive", targetMap = Maps.Srg1_8_9)
    public static Class<?> C00PacketKeepAliveClass;
    @WrapConstructor(targetMap = Maps.Srg1_8_9)
    public static Constructor C00Con;

    public C00PacketKeepAlive(Object obj) {
        super(obj);
    }

    public C00PacketKeepAlive() {
        super(ReflectUtil.construction(C00Con));
    }

    public static boolean isC00PacketKeepAlive(Packet packet) {
        return C00PacketKeepAliveClass.isInstance(packet.getWrapObject());
    }
}
