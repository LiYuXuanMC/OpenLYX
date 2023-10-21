package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Method;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

@WrapperClass(mcpName = "net.minecraft.client.renderer.GLAllocation", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.GLAllocation", targetMap = Maps.Srg1_12_2)
public class GLAllocation extends IWrapper {
    @WrapMethod(mcpName = "createDirectIntBuffer", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "createDirectIntBuffer", targetMap = Maps.Srg1_12_2)
    public static Method createDirectIntBuffer;
    @WrapMethod(mcpName = "createDirectFloatBuffer", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "createDirectFloatBuffer", targetMap = Maps.Srg1_12_2)
    public static Method createDirectFloatBuffer;

    public GLAllocation(Object obj) {
        super(obj);
    }

    public static IntBuffer createDirectIntBuffer(int i) {
        return (IntBuffer) ReflectUtil.invoke(createDirectIntBuffer, null, i);
    }

    public static FloatBuffer createDirectFloatBuffer(int i) {
        return (FloatBuffer) ReflectUtil.invoke(createDirectFloatBuffer, null, i);
    }

}
