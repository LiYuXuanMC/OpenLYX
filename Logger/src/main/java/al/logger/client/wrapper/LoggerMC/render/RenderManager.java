package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.RenderManager",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.RenderManager",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class RenderManager extends IWrapper {
    @WrapField(mcpName = "renderPosX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "renderPosX",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field renderPosX;
    @WrapField(mcpName = "renderPosY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "renderPosY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field renderPosY;
    @WrapField(mcpName = "renderPosZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "renderPosZ",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field renderPosZ;
    @WrapField(mcpName = "viewerPosX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "viewerPosX",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field viewerPosX;
    @WrapField(mcpName = "viewerPosY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "viewerPosY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field viewerPosY;
    @WrapField(mcpName = "viewerPosZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "viewerPosZ",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field viewerPosZ;
    @WrapField(mcpName = "playerViewX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "playerViewX",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field playerViewX;
    @WrapField(mcpName = "playerViewY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "playerViewY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field playerViewY;
    @WrapMethod(mcpName = "setPlayerViewY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "setPlayerViewY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method setPlayerViewY;
    @WrapMethod(mcpName = "setRenderShadow",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "setRenderShadow",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method setRenderShadow;
    @WrapMethod(mcpName = "renderEntity",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "renderEntityWithPosYaw",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method renderEntity;

    public RenderManager(Object obj) {
        super(obj);
    }
    public double getRenderPosX(){
        return (double) getField(renderPosX);
    }
    public double getRenderPosY(){
        return (double) getField(renderPosY);
    }
    public double getRenderPosZ(){
        return (double) getField(renderPosZ);
    }
    public double getViewerPosX(){
        return (double) getField(viewerPosX);
    }
    public double getViewerPosY(){
        return (double) getField(viewerPosY);
    }
    public double getViewerPosZ(){
        return (double) getField(viewerPosZ);
    }
    public float getPlayerViewX(){
        return (float) getField(playerViewX);
    }
    public float getPlayerViewY(){
        return (float) getField(playerViewY);
    }
    public void setPlayerViewY(float y) {
        invoke(setPlayerViewY, y);
    }
    public void setRenderShadow(boolean shadow) {
        invoke(setRenderShadow, shadow);
    }

    public boolean renderEntity(EntityLivingBase entity, double v, double v1, double v2, float v3, float v4) {
        return (boolean) invoke(renderEntity, entity.getWrappedObject(), v, v1, v2, v3, v4);
    }
}

