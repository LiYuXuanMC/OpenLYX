package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.*;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.renderer.EntityRenderer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.EntityRenderer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class EntityRenderer extends WrapperBase {
    @ClassInstance
    public static Class<?> EntityRendererClass;
    @WrapMethod(deobfName = "renderWorldPass",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(IFJ)V")
    @WrapMethod(deobfName = "renderWorldPass",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @CactusWrapping
    public static Method renderWorldPass;
    @WrapMethod(deobfName = "getMouseOver",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getMouseOver",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getMouseOver;
    @WrapField(deobfName = "pointedEntity", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "pointedEntity", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field pointedEntity;
    @WrapMethod(deobfName = "disableLightmap", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableLightmap", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableLightmap;
    @WrapMethod(deobfName = "enableLightmap", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableLightmap", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableLightmap;
    @WrapMethod(deobfName = "setupCameraTransform", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setupCameraTransform", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setupCameraTransform;
    @WrapMethod(deobfName = "setupOverlayRendering", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapMethod(deobfName = "setupOverlayRendering", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method setupOverlayRendering;
    @WrapField(deobfName = "mc", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "mc", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field mc;
    @WrapField(deobfName = "itemRenderer", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "itemRenderer", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field itemRenderer;

    public EntityRenderer(Object obj) {
        super(obj);
    }

    public void enableLightmap() {
        invokeMethod(enableLightmap);
    }
    public void disableLightmap() {
        invokeMethod(disableLightmap);
    }
    public void setupCameraTransform(float partialTicks, int pass) {
        invokeMethod(setupCameraTransform, partialTicks, pass);
    }
    public Entity getPointedEntity() {
        return new Entity(getField(pointedEntity));
    }
    public void setPointedEntity(Entity entity) {
        setField(pointedEntity, entity.getWrappedObject());
    }
    public void setupOverlayRendering() {
        invokeMethod(setupOverlayRendering);
    }
    public ItemRenderer getItemRenderer() {
        return new ItemRenderer(getField(itemRenderer));
    }
}
