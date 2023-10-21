package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.RenderHelper", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.RenderHelper", targetMap = Maps.Srg1_12_2)
public class RenderHelper extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.renderer.RenderHelper", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.renderer.RenderHelper", targetMap = Maps.Srg1_12_2)
    public static Class<?> RenderHelperClass;
    @WrapMethod(mcpName = "enableStandardItemLighting", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableStandardItemLighting", targetMap = Maps.Srg1_12_2)
    public static Method enableStandardItemLighting;
    @WrapMethod(mcpName = "disableStandardItemLighting", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableStandardItemLighting", targetMap = Maps.Srg1_12_2)
    public static Method disableStandardItemLighting;
    @WrapMethod(mcpName = "enableGUIStandardItemLighting", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableGUIStandardItemLighting", targetMap = Maps.Srg1_12_2)
    public static Method enableGUIStandardItemLighting;

    public RenderHelper(Object obj) {
        super(obj);
    }

    public static void enableGUIStandardItemLighting() {
        ReflectUtil.invoke(enableGUIStandardItemLighting, null);
    }

    public static void disableStandardItemLighting() {
        ReflectUtil.invoke(disableStandardItemLighting, null);
    }

    public static void enableStandardItemLighting() {
        ReflectUtil.invoke(enableStandardItemLighting, null);
    }
}
