package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


@WrapperClass(mcpName = "net.minecraft.client.renderer.OpenGlHelper",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.OpenGlHelper",targetMap = Maps.Srg1_12_2)
public class OpenGlHelper extends IWrapper {
    @WrapMethod(mcpName = "glBlendFunc", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "glBlendFunc", targetMap = Maps.Srg1_12_2)
    public static Method glBlendFunc;
    @WrapField(mcpName = "lightmapTexUnit", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "lightmapTexUnit", targetMap = Maps.Srg1_8_9)
    public static Field lightmapTexUnit;
    @WrapField(mcpName = "defaultTexUnit", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "defaultTexUnit", targetMap = Maps.Srg1_8_9)
    public static Field defaultTexUnit;
    @WrapField(mcpName = "shadersSupported", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "shadersSupported", targetMap = Maps.Srg1_8_9)
    public static Field shadersSupported;

    public OpenGlHelper(Object obj) {
        super(obj);
    }

    public static boolean getShadersSupported() {
        return (boolean) ReflectUtil.getField(shadersSupported, null);
    }

    public static int defaultTexUnit() {
        return (int) ReflectUtil.getField(defaultTexUnit, null);
    }

    public static int lightmapTexUnit() {
        return (int) ReflectUtil.getField(lightmapTexUnit, null);
    }

    public static void glBlendFunc(int i1, int i2, int i3, int i4) {
        ReflectUtil.invoke(glBlendFunc, null, i1, i2, i3, i4);
    }
}
