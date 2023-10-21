package al.nya.reflect.wrapper.wraps.wrapper.network.C0B;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
@WrapperClass(mcpName = "net.minecraft.network.play.client.C0BPacketEntityAction$Action",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketEntityAction$Action",targetMap = Maps.Srg1_12_2)
public class C0BAction extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.network.play.client.C0BPacketEntityAction$Action",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketEntityAction$Action",targetMap = Maps.Srg1_12_2)
    public static Class C08ActionClass;
    @WrapEnum(mcpName = "START_SPRINTING", targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "START_SPRINTING", targetMap = Maps.Srg1_12_2)
    public static Enum START_SPRINTING;
    @WrapEnum(mcpName = "STOP_SPRINTING", targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "STOP_SPRINTING", targetMap = Maps.Srg1_12_2)
    public static Enum STOP_SPRINTING;
    @WrapEnum(mcpName = "STOP_SNEAKING", targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "STOP_SNEAKING", targetMap = Maps.Srg1_12_2)
    public static Enum STOP_SNEAKING;
    @WrapEnum(mcpName = "OPEN_INVENTORY", targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "OPEN_INVENTORY", targetMap = Maps.Srg1_12_2)
    public static Enum OPEN_INVENTORY;

    public C0BAction(Object obj) {
        super(obj);
    }
}
