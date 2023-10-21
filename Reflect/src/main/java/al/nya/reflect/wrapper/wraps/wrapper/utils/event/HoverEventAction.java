package al.nya.reflect.wrapper.wraps.wrapper.utils.event;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.EnumWrapper;

@WrapperClass(mcpName = "net.minecraft.event.HoverEvent$Action", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.text.event.HoverEvent$Action", targetMap = Maps.Srg1_12_2)
public class HoverEventAction extends EnumWrapper {
    @WrapClass(mcpName = "net.minecraft.event.HoverEvent$Action", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.util.text.event.HoverEvent$Action", targetMap = Maps.Srg1_12_2)
    public static Class<?> HoverEventActionClass;
    @WrapEnum(mcpName = "SHOW_TEXT", targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "SHOW_TEXT", targetMap = Maps.Srg1_12_2)
    public static Enum SHOW_TEXT;
    @WrapEnum(mcpName = "SHOW_ACHIEVEMENT", targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "SHOW_ACHIEVEMENT", targetMap = Maps.Srg1_12_2)
    public static Enum SHOW_ACHIEVEMENT;
    @WrapEnum(mcpName = "SHOW_ITEM", targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "SHOW_ITEM", targetMap = Maps.Srg1_12_2)
    public static Enum SHOW_ITEM;
    @WrapEnum(mcpName = "SHOW_ENTITY", targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "SHOW_ENTITY", targetMap = Maps.Srg1_12_2)
    public static Enum SHOW_ENTITY;

    public HoverEventAction(Object obj) {
        super(obj);
    }
}
