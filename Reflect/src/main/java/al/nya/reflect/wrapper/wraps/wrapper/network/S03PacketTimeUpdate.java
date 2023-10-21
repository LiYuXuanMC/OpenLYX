package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S03PacketTimeUpdate", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketTimeUpdate", targetMap = Maps.Srg1_12_2)
public class S03PacketTimeUpdate extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.server.S03PacketTimeUpdate", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.server.SPacketTimeUpdate", targetMap = Maps.Srg1_12_2)
    public static Class S03PacketTimeUpdateClass;

    public S03PacketTimeUpdate(Object obj) {
        super(obj);
    }

    public static boolean isPacketTimeUpdate(Packet packet) {
        return S03PacketTimeUpdateClass.isInstance(packet.getWrapObject());
    }
}
