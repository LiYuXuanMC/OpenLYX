package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityLivingBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.renderer.entity.RenderManager",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.entity.RenderManager",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class RenderManager extends WrapperBase {
    @WrapField(deobfName = "renderPosX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "renderPosX",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field renderPosX;
    @WrapField(deobfName = "renderPosY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "renderPosY",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field renderPosY;
    @WrapField(deobfName = "renderPosZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "renderPosZ",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field renderPosZ;
    @WrapField(deobfName = "viewerPosX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "viewerPosX",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field viewerPosX;
    @WrapField(deobfName = "viewerPosY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "viewerPosY",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field viewerPosY;
    @WrapField(deobfName = "viewerPosZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "viewerPosZ",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field viewerPosZ;
    @WrapField(deobfName = "playerViewX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "playerViewX",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field playerViewX;
    @WrapField(deobfName = "playerViewY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "playerViewY",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field playerViewY;
    @WrapMethod(deobfName = "setPlayerViewY",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapMethod(deobfName = "setPlayerViewY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method setPlayerViewY;
    @WrapMethod(deobfName = "setRenderShadow",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapMethod(deobfName = "setRenderShadow",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method setRenderShadow;
    @WrapMethod(deobfName = "renderEntity",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapMethod(deobfName = "renderEntityWithPosYaw",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
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
        invokeMethod(setPlayerViewY, y);
    }
    public void setRenderShadow(boolean shadow) {
        invokeMethod(setRenderShadow, shadow);
    }
    public boolean renderEntity(EntityLivingBase entity, double v, double v1, double v2, float v3, float v4) {
        return (boolean) invokeMethod(renderEntity, entity.getWrappedObject(), v, v1, v2, v3, v4);
    }
}

