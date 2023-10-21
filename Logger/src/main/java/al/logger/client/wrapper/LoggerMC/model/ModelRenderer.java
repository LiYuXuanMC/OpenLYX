package al.logger.client.wrapper.LoggerMC.model;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.model.ModelRenderer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.model.ModelRenderer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class ModelRenderer extends IWrapper {
@ClassInstance    
public static Class<?> ModelRendererClass;
    @WrapField(mcpName = "offsetX", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "offsetX", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field offsetX;
    @WrapField(mcpName = "offsetY", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "offsetY", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field offsetY;
    @WrapField(mcpName = "offsetZ", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "offsetZ", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field offsetZ;
    @WrapField(mcpName = "rotationPointX", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "rotationPointX", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field rotationPointX;
    @WrapField(mcpName = "rotationPointY", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "rotationPointY", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field rotationPointY;
    @WrapField(mcpName = "rotationPointZ", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "rotationPointZ", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field rotationPointZ;
    @WrapField(mcpName = "rotateAngleX", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "rotateAngleX", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field rotateAngleX;
    @WrapField(mcpName = "rotateAngleY", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "rotateAngleY", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field rotateAngleY;
    @WrapField(mcpName = "rotateAngleZ", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "rotateAngleZ", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
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
