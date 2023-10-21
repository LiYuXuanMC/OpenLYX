package al.nya.reflect.wrapper.wraps.wrapper.network.C07;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C07PacketPlayerDigging", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketPlayerDigging", targetMap = Maps.Srg1_12_2)
public class C07PacketPlayerDigging extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.client.C07PacketPlayerDigging", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketPlayerDigging", targetMap = Maps.Srg1_12_2)
    public static Class C07PacketPlayerDiggingClass;
    @WrapConstructor(targetMap = Maps.Srg1_8_9, signature = {C07Action.class, BlockPos.class, EnumFacing.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2, signature = {C07Action.class, BlockPos.class, EnumFacing.class})
    public static Constructor C07PacketPlayerDigging_Action_BlockPos_EnumFacing;
    @WrapField(mcpName = "status", targetMap = Maps.Srg1_8_9)
    public static Field status;

    public C07PacketPlayerDigging(Object obj) {
        super(obj);
    }

    public C07PacketPlayerDigging(Enum statusIn, BlockPos posIn, Enum facingIn) {
        this(ReflectUtil.construction(C07PacketPlayerDigging_Action_BlockPos_EnumFacing, statusIn, posIn.getWrapObject(), facingIn));
    }

    public static boolean isCPacketPlayerDigging(Packet packet) {
        return C07PacketPlayerDiggingClass.isInstance(packet.getWrapObject());
    }

    public Enum getStatus() {
        return (Enum) getField(status);
    }
}
