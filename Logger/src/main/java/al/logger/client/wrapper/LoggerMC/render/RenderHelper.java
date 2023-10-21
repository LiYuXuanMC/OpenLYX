package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.RenderHelper", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.RenderHelper", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class RenderHelper extends IWrapper {
@ClassInstance    
public static Class<?> RenderHelperClass;
    @WrapMethod(mcpName = "enableStandardItemLighting", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableStandardItemLighting", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enableStandardItemLighting;
    @WrapMethod(mcpName = "disableStandardItemLighting", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableStandardItemLighting", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableStandardItemLighting;
    @WrapMethod(mcpName = "enableGUIStandardItemLighting", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableGUIStandardItemLighting", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
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
