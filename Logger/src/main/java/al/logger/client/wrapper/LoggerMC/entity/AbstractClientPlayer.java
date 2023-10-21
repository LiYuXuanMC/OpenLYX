package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.entity.AbstractClientPlayer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.entity.AbstractClientPlayer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class AbstractClientPlayer extends EntityPlayer {
    @ClassInstance
    public static Class<?> AbstractClientPlayerClass;
    @WrapMethod(mcpName = "getLocationSkin", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "()Lnet/minecraft/util/ResourceLocation;")
    @WrapMethod(mcpName = "getLocationSkin", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "()Lnet/minecraft/util/ResourceLocation;")
    public static Method getLocationSkin;
    @WrapMethod(mcpName = "hasPlayerInfo", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "()Z")
    public static Method hasPlayerInfo;
    @WrapMethod(mcpName = "getLocationCape", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getLocationCape;
    public AbstractClientPlayer(Object obj) {
        super(obj);
    }
    public ResourceLocation getLocationSkin() {
        return new ResourceLocation(ReflectUtil.invoke(getLocationSkin, getWrappedObject()));
    }

    public boolean hasPlayerInfo() {
        return (boolean) invoke(hasPlayerInfo);
    }
}
