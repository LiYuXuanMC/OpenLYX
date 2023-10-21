package al.nya.reflect.wrapper.wraps.wrapper.network.C03;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C03PacketPlayer$C05PacketPlayerLook",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketPlayer$Rotation",targetMap = Maps.Srg1_12_2)
public class C05PacketPlayerLook extends C03PacketPlayer{
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {float.class,float.class,boolean.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2,signature = {float.class,float.class,boolean.class})
    public static Constructor C04PacketPlayerPosition_FFZ;
    public C05PacketPlayerLook(Object obj) {
        super(obj);
    }
    @WrapClass(mcpName = "net.minecraft.network.play.client.C03PacketPlayer$C05PacketPlayerLook",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketPlayer$Rotation",targetMap = Maps.Srg1_12_2)
    public static Class<?> C05PacketPlayerLookClass;
    public static boolean isC05PacketPlayerLook(Packet packet){
        return C05PacketPlayerLookClass.isInstance(packet.getWrapObject());
    }
    public C05PacketPlayerLook(float playerYaw, float playerPitch, boolean isOnGround)
    {
        super(ReflectUtil.construction(C04PacketPlayerPosition_FFZ,playerYaw,playerPitch,isOnGround));
    }
}
