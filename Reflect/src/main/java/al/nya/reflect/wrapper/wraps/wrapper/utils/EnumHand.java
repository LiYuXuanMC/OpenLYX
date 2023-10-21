package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.EnumWrapper;
@WrapperClass(mcpName = "net.minecraft.util.EnumHand",targetMap = Maps.Srg1_12_2)
public class EnumHand extends EnumWrapper {
    @WrapEnum(mcpName = "MAIN_HAND",targetMap = Maps.Srg1_12_2)
    public static Enum MAIN_HAND;
    @WrapEnum(mcpName = "MAIN_HAND",targetMap = Maps.Srg1_12_2)
    public static Enum OFF_HAND;
    public EnumHand(Object obj) {
        super(obj);
    }
}
