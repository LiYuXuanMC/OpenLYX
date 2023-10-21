package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import al.logger.client.wrapper.annotations.*;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.entity.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.EntityRenderer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.EntityRenderer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EntityRenderer extends IWrapper {
    @ClassInstance
    public static Class EntityRendererClass;
    @WrapMethod(mcpName = "renderWorldPass",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(IFJ)V")
    @WrapMethod(mcpName = "renderWorldPass",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping
    public static Method renderWorldPass;
    @WrapMethod(mcpName = "getMouseOver",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getMouseOver",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getMouseOver;
    @WrapField(mcpName = "pointedEntity", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "pointedEntity", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field pointedEntity;
    @WrapMethod(mcpName = "disableLightmap", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableLightmap", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableLightmap;
    @WrapMethod(mcpName = "enableLightmap", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableLightmap", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enableLightmap;
    @WrapMethod(mcpName = "setupCameraTransform", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setupCameraTransform", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setupCameraTransform;
    @WrapMethod(mcpName = "setupOverlayRendering", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "setupOverlayRendering", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method setupOverlayRendering;

    @WrapField(mcpName = "mc", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "mc", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field mc;
    @WrapField(mcpName = "itemRenderer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "itemRenderer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field itemRenderer;
    @WrapMethod(mcpName = "loadShader", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "loadShader", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method loadShader;
    @WrapMethod(mcpName = "isShaderActive", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "isShaderActive", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method isShaderActive;
    @WrapMethod(mcpName = "stopUseShader", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "stopUseShader", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method stopUseShader;
    @WrapMethod(mcpName = "updateCameraAndRender", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "updateCameraAndRender", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method updateCameraAndRender;
    @WrapMethod(mcpName = "hurtCameraEffect", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "hurtCameraEffect", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method hurtCameraEffect;
    @WrapMethod(mcpName = "orientCamera", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "orientCamera", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method orientCamera;
    @WrapField(mcpName = "cloudFog", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field cloudFog;
    @WrapField(mcpName = "thirdPersonDistance", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field thirdPersonDistanceTemp;
    @WrapField(mcpName = "thirdPersonDistance", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field thirdPersonDistance;

    public EntityRenderer(Object obj) {
        super(obj);
    }

    public void enableLightmap() {
        invoke(enableLightmap);
    }

    public void disableLightmap() {
        invoke(disableLightmap);
    }

    public void setupCameraTransform(float partialTicks, int pass) {
        invoke(setupCameraTransform, partialTicks, pass);
    }

    public Entity getPointedEntity() {
        return new Entity(getField(pointedEntity));
    }

    public void setPointedEntity(Entity entity) {
        setField(pointedEntity, entity.getWrappedObject());
    }

    public void setupOverlayRendering() {
        invoke(setupOverlayRendering);
    }

    public ItemRenderer getItemRenderer() {
        return new ItemRenderer(getField(itemRenderer));
    }

    public void loadShader(ResourceLocation p_loadShader_1_){
        invoke(loadShader,p_loadShader_1_.getWrappedObject());
    }

    public boolean isShaderActive(){
        return (boolean) invoke(isShaderActive);
    }

    public void stopUseShader(){
        invoke(stopUseShader);
    }
    public void setCloudFog(boolean b){
        setField(cloudFog,b);
    }
    public float getThirdPersonDistanceTemp(){
        return (float) getField(thirdPersonDistanceTemp);
    }
    public void setThirdPersonDistance(float f){
        setField(thirdPersonDistance,f);
    }
    public float getThirdPersonDistance(){
        return (float) getField(thirdPersonDistance);
    }
}
