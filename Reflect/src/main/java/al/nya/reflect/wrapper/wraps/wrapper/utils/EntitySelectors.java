package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.util.EntitySelectors",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.EntitySelectors",targetMap = Maps.Srg1_12_2)
public class EntitySelectors extends IWrapper {
    @WrapField(mcpName = "NOT_SPECTATING", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "NOT_SPECTATING", targetMap = Maps.Srg1_12_2)
    public static Field NOT_SPECTATING;
    public EntitySelectors(Object obj) {
        super(obj);
    }
    public static Object getNOT_SPECTATING() {
        return ReflectUtil.getField(NOT_SPECTATING,null);
    }
}
