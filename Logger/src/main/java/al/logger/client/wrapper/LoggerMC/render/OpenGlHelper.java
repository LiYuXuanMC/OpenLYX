package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


@WrapperClass(mcpName = "net.minecraft.client.renderer.OpenGlHelper",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.OpenGlHelper",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class OpenGlHelper extends IWrapper {
    @WrapMethod(mcpName = "glBlendFunc", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "glBlendFunc", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method glBlendFunc;
    @WrapField(mcpName = "lightmapTexUnit", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "lightmapTexUnit", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field lightmapTexUnit;
    @WrapField(mcpName = "defaultTexUnit", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "defaultTexUnit", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field defaultTexUnit;
    @WrapField(mcpName = "shadersSupported", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "shadersSupported", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field shadersSupported;

    public OpenGlHelper(Object obj) {
        super(obj);
    }

    public static boolean getShadersSupported() {
        return (boolean) getStatic(shadersSupported);
    }

    public static int defaultTexUnit() {
        return (int) getStatic(defaultTexUnit);
    }

    public static int lightmapTexUnit() {
        return (int) getStatic(lightmapTexUnit);
    }

    public static void glBlendFunc(int i1, int i2, int i3, int i4) {
        ReflectUtil.invoke(glBlendFunc, null, i1, i2, i3, i4);
    }
}
