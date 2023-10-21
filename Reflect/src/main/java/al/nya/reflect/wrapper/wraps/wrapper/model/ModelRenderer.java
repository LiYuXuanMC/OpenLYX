package al.nya.reflect.wrapper.wraps.wrapper.model;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.model.ModelRenderer", targetMap = Maps.Srg1_12_2)
@WrapperClass(mcpName = "net.minecraft.client.model.ModelRenderer", targetMap = Maps.Srg1_8_9)
public class ModelRenderer extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.model.ModelRenderer", targetMap = Maps.Srg1_12_2)
    @WrapClass(mcpName = "net.minecraft.client.model.ModelRenderer", targetMap = Maps.Srg1_8_9)
    public static Class<?> ModelRendererClass;
    @WrapField(mcpName = "offsetX", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "offsetX", targetMap = Maps.Srg1_8_9)
    public static Field offsetX;
    @WrapField(mcpName = "offsetY", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "offsetY", targetMap = Maps.Srg1_8_9)
    public static Field offsetY;
    @WrapField(mcpName = "offsetZ", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "offsetZ", targetMap = Maps.Srg1_8_9)
    public static Field offsetZ;
    @WrapField(mcpName = "rotationPointX", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "rotationPointX", targetMap = Maps.Srg1_8_9)
    public static Field rotationPointX;
    @WrapField(mcpName = "rotationPointY", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "rotationPointY", targetMap = Maps.Srg1_8_9)
    public static Field rotationPointY;
    @WrapField(mcpName = "rotationPointZ", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "rotationPointZ", targetMap = Maps.Srg1_8_9)
    public static Field rotationPointZ;
    @WrapField(mcpName = "rotateAngleX", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "rotateAngleX", targetMap = Maps.Srg1_8_9)
    public static Field rotateAngleX;
    @WrapField(mcpName = "rotateAngleY", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "rotateAngleY", targetMap = Maps.Srg1_8_9)
    public static Field rotateAngleY;
    @WrapField(mcpName = "rotateAngleZ", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "rotateAngleZ", targetMap = Maps.Srg1_8_9)
    public static Field rotateAngleZ;

    public float getRotateAngleX() {
        return (float) getField(rotateAngleX);
    }
    public float getRotateAngleY() {
        return (float) getField(rotateAngleY);
    }
    public float getRotateAngleZ() {
        return (float) getField(rotateAngleZ);
    }
    public void setRotateAngleX(float angle) {
        setField(rotateAngleX, angle);
    }
    public void setRotateAngleY(float angle) {
        setField(rotateAngleY, angle);
    }
    public void setRotateAngleZ(float angle) {
        setField(rotateAngleZ, angle);
    }

    public ModelRenderer(Object obj) {
        super(obj);
    }
    public float getRotationPointX() {
        return (float) getField(rotationPointX);
    }
    public float getRotationPointY() {
        return (float) getField(rotationPointY);
    }
    public float getRotationPointZ() {
        return (float) getField(rotationPointZ);
    }
    public void setRotationPointX(float x) {
        setField(rotationPointX, x);
    }
    public void setRotationPointY(float y) {
        setField(rotationPointY, y);
    }
    public void setRotationPointZ(float z) {
        setField(rotationPointZ, z);
    }
    public float getOffsetX() {
        return (float) getField(offsetX);
    }
    public float getOffsetY() {
        return (float) getField(offsetY);
    }
    public float getOffsetZ() {
        return (float) getField(offsetZ);
    }
    public void setOffsetX(float x) {
        setField(offsetX, x);
    }
    public void setOffsetY(float y) {
        setField(offsetY, y);
    }
    public void setOffsetZ(float z) {
        setField(offsetZ, z);
    }
}
