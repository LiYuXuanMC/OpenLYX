package al.nya.reflect.wrapper.wraps.wrapper.network.C03;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C03PacketPlayer$C06PacketPlayerPosLook", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketPlayer$Rotation", targetMap = Maps.Srg1_12_2)
public class C06PacketPlayerPositionLook extends C03PacketPlayer {
    @WrapClass(mcpName = "net.minecraft.network.play.client.C03PacketPlayer$C06PacketPlayerPosLook", targetMap = Maps.Srg1_8_9)
    public static Class<?> C06PacketPlayerPositionLookClass;
    @WrapConstructor(targetMap = Maps.Srg1_12_2, signature = {double.class, double.class, double.class, float.class, float.class, boolean.class})
    @WrapConstructor(targetMap = Maps.Srg1_8_9, signature = {double.class, double.class, double.class, float.class, float.class, boolean.class})
    public static Constructor<?> C06PacketPlayerPositionLookConstructor;

    public C06PacketPlayerPositionLook(Object obj) {
        super(obj);
    }

    public C06PacketPlayerPositionLook(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        super(ReflectUtil.construction(C06PacketPlayerPositionLookConstructor, x, y, z, yaw, pitch, onGround));
    }

    public static boolean isPacketPlayerPositionLook(Packet packet) {
        return C06PacketPlayerPositionLookClass.isInstance(packet.getWrapObject());
    }
}
