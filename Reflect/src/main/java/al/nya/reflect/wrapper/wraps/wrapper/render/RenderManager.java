package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.RenderManager",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.RenderManager",targetMap = Maps.Srg1_12_2)
public class RenderManager extends IWrapper {
    @WrapField(mcpName = "renderPosX",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "renderPosX",targetMap = Maps.Srg1_12_2)
    public static Field renderPosX;
    @WrapField(mcpName = "renderPosY",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "renderPosY",targetMap = Maps.Srg1_12_2)
    public static Field renderPosY;
    @WrapField(mcpName = "renderPosZ",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "renderPosZ",targetMap = Maps.Srg1_12_2)
    public static Field renderPosZ;
    @WrapField(mcpName = "viewerPosX",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "viewerPosX",targetMap = Maps.Srg1_12_2)
    public static Field viewerPosX;
    @WrapField(mcpName = "viewerPosY",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "viewerPosY",targetMap = Maps.Srg1_12_2)
    public static Field viewerPosY;
    @WrapField(mcpName = "viewerPosZ",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "viewerPosZ",targetMap = Maps.Srg1_12_2)
    public static Field viewerPosZ;
    @WrapField(mcpName = "playerViewX",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "playerViewX",targetMap = Maps.Srg1_12_2)
    public static Field playerViewX;
    @WrapField(mcpName = "playerViewY",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "playerViewY",targetMap = Maps.Srg1_12_2)
    public static Field playerViewY;
    @WrapMethod(mcpName = "setPlayerViewY",targetMap = Maps.Srg1_12_2)
    @WrapMethod(mcpName = "setPlayerViewY",targetMap = Maps.Srg1_8_9)
    public static Method setPlayerViewY;
    @WrapMethod(mcpName = "setRenderShadow",targetMap = Maps.Srg1_12_2)
    @WrapMethod(mcpName = "setRenderShadow",targetMap = Maps.Srg1_8_9)
    public static Method setRenderShadow;
    @WrapMethod(mcpName = "renderEntity",targetMap = Maps.Srg1_12_2)
    @WrapMethod(mcpName = "renderEntityWithPosYaw",targetMap = Maps.Srg1_8_9)
    public static Method renderEntity;

    public RenderManager(Object obj) {
        super(obj);
    }
    public double getRenderPosX(){
        return (double) ReflectUtil.getField(renderPosX,getWrapObject());
    }
    public double getRenderPosY(){
        return (double) ReflectUtil.getField(renderPosY,getWrapObject());
    }
    public double getRenderPosZ(){
        return (double) ReflectUtil.getField(renderPosZ,getWrapObject());
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
        return (boolean) invoke(renderEntity, entity.getWrapObject(), v, v1, v2, v3, v4);
    }
}

