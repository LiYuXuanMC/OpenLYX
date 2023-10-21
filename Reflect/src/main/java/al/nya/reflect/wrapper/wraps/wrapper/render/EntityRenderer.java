package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.EntityRenderer",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.EntityRenderer",targetMap = Maps.Srg1_12_2)
public class EntityRenderer extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.renderer.EntityRenderer",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.renderer.EntityRenderer",targetMap = Maps.Srg1_12_2)
    public static Class EntityRendererClass;
    @WrapMethod(mcpName = "renderWorldPass",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "renderWorldPass",targetMap = Maps.Srg1_12_2)
    public static Method renderWorldPass;
    @WrapMethod(mcpName = "getMouseOver",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getMouseOver",targetMap = Maps.Srg1_12_2)
    public static Method getMouseOver;
    @WrapField(mcpName = "pointedEntity", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "pointedEntity", targetMap = Maps.Srg1_12_2)
    public static Field pointedEntity;
    @WrapMethod(mcpName = "disableLightmap", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableLightmap", targetMap = Maps.Srg1_12_2)
    public static Method disableLightmap;
    @WrapMethod(mcpName = "enableLightmap", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableLightmap", targetMap = Maps.Srg1_12_2)
    public static Method enableLightmap;
    @WrapMethod(mcpName = "setupCameraTransform", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setupCameraTransform", targetMap = Maps.Srg1_12_2)
    public static Method setupCameraTransform;
    @WrapMethod(mcpName = "setupOverlayRendering", targetMap = Maps.Srg1_12_2)
    @WrapMethod(mcpName = "setupOverlayRendering", targetMap = Maps.Srg1_8_9)
    public static Method setupOverlayRendering;
    @WrapField(mcpName = "mc", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "mc", targetMap = Maps.Srg1_12_2)
    public static Field mc;
    @WrapField(mcpName = "itemRenderer", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "itemRenderer", targetMap = Maps.Srg1_12_2)
    public static Field itemRenderer;

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
        setField(pointedEntity, entity.getWrapObject());
    }

    public void setupOverlayRendering() {
        invoke(setupOverlayRendering);
    }

    public ItemRenderer getItemRenderer() {
        return new ItemRenderer(getField(itemRenderer));
    }
}
