package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S07PacketRespawn", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketRespawn", targetMap = Maps.Srg1_12_2)
public class S07PacketRespawn extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.server.S07PacketRespawn", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.server.SPacketRespawn", targetMap = Maps.Srg1_12_2)
    public static Class<?> S07PacketRespawnClass;

    public S07PacketRespawn(Object obj) {
        super(obj);
    }

    public static boolean isS07PacketRespawn(Packet packet) {
        return S07PacketRespawnClass.isInstance(packet.getWrapObject());
    }

}
