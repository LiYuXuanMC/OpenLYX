package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.MathHelper",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.math.MathHelper",targetMap = Maps.Srg1_12_2)
public class MathHelper extends IWrapper {
    @WrapMethod(mcpName = "sin",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "sin",targetMap = Maps.Srg1_12_2)
    public static Method sin;
    @WrapMethod(mcpName = "cos",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "cos",targetMap = Maps.Srg1_12_2)
    public static Method cos;
    public MathHelper(Object obj) {
        super(obj);
    }
    public static float sin(float f){
        return (float) ReflectUtil.invoke(sin,null,f);
    }
    public static float cos(float f){
        return (float) ReflectUtil.invoke(cos,null,f);
    }
}
