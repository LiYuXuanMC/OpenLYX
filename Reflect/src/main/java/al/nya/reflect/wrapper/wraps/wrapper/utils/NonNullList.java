package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.util.NonNullList", targetMap = Maps.Srg1_12_2)
public class NonNullList extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.util.NonNullList", targetMap = Maps.Srg1_12_2)
    public static Class NonNullListClass;
    @WrapConstructor(targetMap = Maps.Srg1_12_2)
    public static Constructor NonNullListConstructor;

    public NonNullList(Object obj) {
        super(obj);
    }

    public NonNullList() {
        super(ReflectUtil.construction(NonNullListConstructor));
    }
}
