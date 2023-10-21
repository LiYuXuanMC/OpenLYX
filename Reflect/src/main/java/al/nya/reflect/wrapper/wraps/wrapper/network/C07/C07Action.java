package al.nya.reflect.wrapper.wraps.wrapper.network.C07;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
@WrapperClass(mcpName = "net.minecraft.network.play.client.C07PacketPlayerDigging$Action",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketPlayerDigging$Action",targetMap = Maps.Srg1_12_2)
public class C07Action extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.network.play.client.C07PacketPlayerDigging$Action",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketPlayerDigging$Action",targetMap = Maps.Srg1_12_2)
    public static Class ActionClass;
    @WrapEnum(mcpName = "START_DESTROY_BLOCK",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "START_DESTROY_BLOCK",targetMap = Maps.Srg1_12_2)
    public static Enum START_DESTROY_BLOCK;
    @WrapEnum(mcpName = "ABORT_DESTROY_BLOCK",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "ABORT_DESTROY_BLOCK",targetMap = Maps.Srg1_12_2)
    public static Enum ABORT_DESTROY_BLOCK;
    @WrapEnum(mcpName = "STOP_DESTROY_BLOCK",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "STOP_DESTROY_BLOCK",targetMap = Maps.Srg1_12_2)
    public static Enum STOP_DESTROY_BLOCK;
    @WrapEnum(mcpName = "DROP_ALL_ITEMS",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "DROP_ALL_ITEMS",targetMap = Maps.Srg1_12_2)
    public static Enum DROP_ALL_ITEMS;
    @WrapEnum(mcpName = "DROP_ITEM",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "DROP_ITEM",targetMap = Maps.Srg1_12_2)
    public static Enum DROP_ITEM;
    @WrapEnum(mcpName = "RELEASE_USE_ITEM",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "RELEASE_USE_ITEM",targetMap = Maps.Srg1_12_2)
    public static Enum RELEASE_USE_ITEM;
    public C07Action(Object obj) {
        super(obj);
    }
}
