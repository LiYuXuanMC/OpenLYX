package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S24PacketBlockAction", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.server.SPacketBlockAction", targetMap = Maps.Srg1_12_2)
public class S24PacketBlockAction extends Packet {
    @WrapClass(mcpName = "net.minecraft.network.play.server.S24PacketBlockAction", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.server.SPacketBlockAction", targetMap = Maps.Srg1_12_2)
    public static Class<?> S24PacketBlockActionClass;
    @WrapMethod(mcpName = "getBlockPosition", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getBlockPosition", targetMap = Maps.Srg1_12_2)
    public static Method getBlockPosition;
    @WrapMethod(mcpName = "getData1", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getData1", targetMap = Maps.Srg1_12_2)
    public static Method getData1;
    @WrapMethod(mcpName = "getData2", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getData2", targetMap = Maps.Srg1_12_2)
    public static Method getData2;

    public S24PacketBlockAction(Object obj) {
        super(obj);
    }

    public static boolean isS24PacketBlockAction(Packet e) {
        return S24PacketBlockActionClass.isInstance(e.getWrapObject());
    }

    public BlockPos getBlockPosition() {
        return new BlockPos(invoke(getBlockPosition));
    }

    public int getData1() {
        return (int) invoke(getData1);
    }

    public int getData2() {
        return (int) invoke(getData2);
    }
}
