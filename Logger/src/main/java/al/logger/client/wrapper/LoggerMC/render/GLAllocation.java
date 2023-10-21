package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Method;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

@WrapperClass(mcpName = "net.minecraft.client.renderer.GLAllocation", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.GLAllocation", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class GLAllocation extends IWrapper {
    @WrapMethod(mcpName = "createDirectIntBuffer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "createDirectIntBuffer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method createDirectIntBuffer;
    @WrapMethod(mcpName = "createDirectFloatBuffer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "createDirectFloatBuffer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method createDirectFloatBuffer;

    public GLAllocation(Object obj) {
        super(obj);
    }

    public static IntBuffer createDirectIntBuffer(int i) {
        return (IntBuffer) invokeStatic(createDirectIntBuffer, i);
    }

    public static FloatBuffer createDirectFloatBuffer(int i) {
        return (FloatBuffer) invokeStatic(createDirectFloatBuffer, i);
    }

}
