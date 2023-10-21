package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.EnumWrapper;
@WrapperClass(mcpName = "net.minecraft.util.EnumActionResult",targetMap = Maps.Srg1_12_2)
public class EnumActionResult extends EnumWrapper {
    @WrapEnum(mcpName = "SUCCESS",targetMap = Maps.Srg1_12_2)
    public static Enum SUCCESS;
    @WrapEnum(mcpName = "PASS",targetMap = Maps.Srg1_12_2)
    public static Enum PASS;
    @WrapEnum(mcpName = "FAIL",targetMap = Maps.Srg1_12_2)
    public static Enum FAIL;
    public EnumActionResult(Object obj) {
        super(obj);
    }
}
