package al.nya.reflect.wrapper.wraps.wrapper.network;


import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S00PacketKeepAlive", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketKeepAlive", targetMap = Maps.Srg1_12_2)
public class S00PacketKeepAlive extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.server.S00PacketKeepAlive", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.server.SPacketKeepAlive", targetMap = Maps.Srg1_12_2)
    public static Class<?> S00PacketKeepAliveClass;

    public S00PacketKeepAlive(Object obj) {
        super(obj);
    }

    public static boolean isS00PacketKeepAlive(Packet packet) {
        return S00PacketKeepAliveClass.isInstance(packet.getWrapObject());
    }


}
