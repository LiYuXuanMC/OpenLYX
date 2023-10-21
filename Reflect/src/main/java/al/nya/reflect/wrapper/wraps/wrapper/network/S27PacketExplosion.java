package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S27PacketExplosion",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketExplosion",targetMap = Maps.Srg1_12_2)
public class S27PacketExplosion extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.server.S27PacketExplosion",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.server.SPacketExplosion",targetMap = Maps.Srg1_12_2)
    public static Class S27PacketExplosionClass;
    public S27PacketExplosion(Object obj) {
        super(obj);
    }
    public static boolean isS27PacketExplosion(Packet p){
        return S27PacketExplosionClass.isAssignableFrom(p.getWrapObject().getClass());
    }
}
