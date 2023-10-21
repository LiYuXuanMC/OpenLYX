package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.util.FoodStats",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.FoodStats",targetMap = Maps.Srg1_12_2)
public class FoodStats extends IWrapper {
    @WrapField(mcpName = "foodLevel",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "foodLevel",targetMap = Maps.Srg1_12_2)
    public static Field foodLevel;
    public FoodStats(Object obj) {
        super(obj);
    }
    public int getFoodLevel(){
        return (int) ReflectUtil.getField(foodLevel,getWrapObject());
    }
}
