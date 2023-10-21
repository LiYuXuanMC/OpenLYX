package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.item.Item", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.Item", targetMap = Maps.Srg1_12_2)
public class Item extends IWrapper {
    @WrapMethod(mcpName = "getUnlocalizedName", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getUnlocalizedName", targetMap = Maps.Srg1_12_2, signature = "()Ljava/lang/String;")
    public static Method getUnlocalizedName;
    @WrapField(mcpName = "maxDamage", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "maxDamage", targetMap = Maps.Srg1_12_2)
    public static Field maxDamage;
    @WrapMethod(mcpName = "getIdFromItem", targetMap = Maps.Srg1_8_9)
    public static Method getIdFromItem;

    public Item(Object obj) {
        super(obj);
    }

    public String getUnlocalizedName() {
        return (String) invoke(getUnlocalizedName);
    }

    public int getMaxDamage() {
        return (int) getField(maxDamage);
    }

    public static int getIdFromItem(Item item) {
        return (int) ReflectUtil.invoke(getIdFromItem, null, item.getWrapObject());
    }
}
