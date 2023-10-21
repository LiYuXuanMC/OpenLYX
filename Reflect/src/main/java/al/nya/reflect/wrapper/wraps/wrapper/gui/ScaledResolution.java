package al.nya.reflect.wrapper.wraps.wrapper.gui;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.gui.ScaledResolution",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.gui.ScaledResolution",targetMap = Maps.Srg1_12_2)
public class ScaledResolution extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.gui.ScaledResolution", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.gui.ScaledResolution", targetMap = Maps.Srg1_12_2)
    public static Class<?> ScaledResolution;
    @WrapField(mcpName = "scaledWidthD", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "scaledWidthD", targetMap = Maps.Srg1_12_2)
    public static Field scaledWidthD;
    @WrapField(mcpName = "scaledHeightD",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "scaledHeightD",targetMap = Maps.Srg1_12_2)
    public static Field scaledHeightD;
    @WrapField(mcpName = "scaledWidth",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "scaledWidth",targetMap = Maps.Srg1_12_2)
    public static Field scaledWidth;
    @WrapField(mcpName = "scaledHeight",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "scaledHeight",targetMap = Maps.Srg1_12_2)
    public static Field scaledHeight;
    @WrapField(mcpName = "scaleFactor",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "scaleFactor",targetMap = Maps.Srg1_12_2)
    public static Field scaleFactor;

    public ScaledResolution(Object obj) {
        super(obj);
    }
    public ScaledResolution(Minecraft mc){
        super(ReflectUtil.construction(ScaledResolution,mc.getWrapObject()));
    }
    public int getScaledWidth()
    {
        return (int) ReflectUtil.getField(scaledWidth,getWrapObject());
    }

    public int getScaledHeight()
    {
        return (int) ReflectUtil.getField(scaledHeight,getWrapObject());
    }

    public double getScaledWidth_double()
    {
        return (double) ReflectUtil.getField(scaledWidthD,getWrapObject());
    }

    public double getScaledHeight_double()
    {
        return (double) ReflectUtil.getField(scaledHeightD,getWrapObject());
    }

    public int getScaleFactor()
    {
        return (int) ReflectUtil.getField(scaleFactor,getWrapObject());
    }
}
