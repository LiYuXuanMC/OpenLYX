package al.logger.client.wrapper.LoggerMC.gui;

import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.Minecraft;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.gui.ScaledResolution",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.gui.ScaledResolution",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ScaledResolution extends IWrapper {
    @ClassInstance
    public static Class<?> ScaledResolutionClass;
    @WrapField(mcpName = "scaledWidthD", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "scaledWidthD", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field scaledWidthD;
    @WrapField(mcpName = "scaledHeightD",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "scaledHeightD",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field scaledHeightD;
    @WrapField(mcpName = "scaledWidth",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "scaledWidth",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field scaledWidth;
    @WrapField(mcpName = "scaledHeight",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "scaledHeight",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field scaledHeight;
    @WrapField(mcpName = "scaleFactor",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "scaleFactor",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field scaleFactor;
    @WrapConstructor(signature = {Minecraft.class},targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapConstructor(signature = {Minecraft.class},targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Constructor ScaledResolution_Minecraft;

    public ScaledResolution(Object obj) {
        super(obj);
    }
    public ScaledResolution(Minecraft mc){
        super(construction(ScaledResolution_Minecraft,mc.getWrappedObject()));
    }
    public int getScaledWidth()
    {
        return (int) ReflectUtil.getField(scaledWidth,getWrappedObject());
    }

    public int getScaledHeight()
    {
        return (int) ReflectUtil.getField(scaledHeight,getWrappedObject());
    }

    public double getScaledWidth_double()
    {
        return (double) ReflectUtil.getField(scaledWidthD,getWrappedObject());
    }

    public double getScaledHeight_double()
    {
        return (double) ReflectUtil.getField(scaledHeightD,getWrappedObject());
    }

    public int getScaleFactor()
    {
        return (int) ReflectUtil.getField(scaleFactor,getWrappedObject());
    }
}
