package al.nya.reflect.wrapper.wraps.wrapper.network.C03;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C03PacketPlayer$C04PacketPlayerPosition",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketPlayer$Position",targetMap = Maps.Srg1_12_2)
public class C04PacketPlayerPosition extends C03PacketPlayer{
    @WrapClass(mcpName = "net.minecraft.network.play.client.C03PacketPlayer$C04PacketPlayerPosition",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketPlayer$Position",targetMap = Maps.Srg1_12_2)
    public static Class C04PacketPlayerPositionClass;
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {double.class,double.class,double.class,boolean.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2,signature = {double.class,double.class,double.class,boolean.class})
    public static Constructor C04PacketPlayerPosition_DDDZ;
    public C04PacketPlayerPosition(Object obj) {
        super(obj);
    }
    public static boolean isPacketPlayerPosition(Packet packet){
        return C04PacketPlayerPositionClass.isInstance(packet.getWrapObject());
    }
    public C04PacketPlayerPosition(double posX, double posY, double posZ, boolean isOnGround)
    {
        super(ReflectUtil.construction(C04PacketPlayerPosition_DDDZ,posX,posY,posZ,isOnGround));
    }
}
