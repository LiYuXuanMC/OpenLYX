package al.nya.reflect.wrapper.wraps.wrapper.shader;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.*;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.shader.Framebuffer", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.shader.Framebuffer", targetMap = Maps.Srg1_12_2)
public class Framebuffer extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.shader.Framebuffer", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.shader.Framebuffer", targetMap = Maps.Srg1_12_2)
    public static Class<?> FramebufferClass;
    @WrapConstructor(targetMap = Maps.Srg1_8_9, signature = {int.class, int.class, boolean.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2, signature = {int.class, int.class, boolean.class})
    public static Constructor<?> Constructor_int_int_boolean;

    @WrapMethod(mcpName = "deleteFramebuffer", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "deleteFramebuffer", targetMap = Maps.Srg1_12_2)
    public static Method deleteFramebuffer;

    @WrapMethod(mcpName = "framebufferClear", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "framebufferClear", targetMap = Maps.Srg1_12_2)
    public static Method framebufferClear;

    @WrapMethod(mcpName = "unbindFramebuffer", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "unbindFramebuffer", targetMap = Maps.Srg1_12_2)
    public static Method unbindFramebuffer;

    @WrapField(mcpName = "framebufferTextureWidth", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "framebufferTextureWidth", targetMap = Maps.Srg1_12_2)
    public static Field framebufferTextureWidth;
    @WrapField(mcpName = "framebufferTextureHeight", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "framebufferTextureHeight", targetMap = Maps.Srg1_12_2)
    public static Field framebufferTextureHeight;
    @WrapField(mcpName = "framebufferWidth", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "framebufferWidth", targetMap = Maps.Srg1_12_2)
    public static Field framebufferWidth;
    @WrapField(mcpName = "framebufferHeight", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "framebufferHeight", targetMap = Maps.Srg1_12_2)
    public static Field framebufferHeight;
    @WrapField(mcpName = "useDepth", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "useDepth", targetMap = Maps.Srg1_12_2)
    public static Field useDepth;
    @WrapField(mcpName = "framebufferObject", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "framebufferObject", targetMap = Maps.Srg1_12_2)
    public static Field framebufferObject;
    @WrapField(mcpName = "framebufferTexture", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "framebufferTexture", targetMap = Maps.Srg1_12_2)
    public static Field framebufferTexture;
    @WrapField(mcpName = "depthBuffer", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "depthBuffer", targetMap = Maps.Srg1_12_2)
    public static Field depthBuffer;
    @WrapField(mcpName = "framebufferColor", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "framebufferColor", targetMap = Maps.Srg1_12_2)
    public static Field framebufferColor;
    @WrapField(mcpName = "framebufferFilter", targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "framebufferFilter", targetMap = Maps.Srg1_12_2)
    public static Field framebufferFilter;
    @WrapMethod(mcpName = "bindFramebuffer", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "bindFramebuffer", targetMap = Maps.Srg1_12_2)
    public static Method bindFramebuffer;

    public Framebuffer(int n1, int n2, boolean b1) {
        super(ReflectUtil.construction(Constructor_int_int_boolean, n1, n2, b1));
    }

    public Framebuffer(Object obj) {
        super(obj);
    }

    public static boolean isFramebuffer(Object obj) {
        return FramebufferClass.isInstance(obj);
    }

    public int getFramebufferTextureWidth() {
        return (int) getField(framebufferTextureWidth);
    }

    public void setFramebufferTextureWidth(int i1) {
        setField(framebufferTextureWidth, i1);
    }

    public int getFramebufferTextureHeight() {
        return (int) getField(framebufferTextureHeight);
    }

    public void setFramebufferTextureHeight(int i1) {
        setField(framebufferTextureHeight, i1);
    }

    public int getFramebufferWidth() {
        return (int) getField(framebufferWidth);
    }

    public void setFramebufferWidth(int i1) {
        setField(framebufferWidth, i1);
    }

    public int getFramebufferHeight() {
        return (int) getField(framebufferHeight);
    }

    public void setFramebufferHeight(int i1) {
        setField(framebufferHeight, i1);
    }

    public boolean getUseDepth() {
        return (boolean) getField(useDepth);
    }

    public void setUseDepth(boolean b1) {
        setField(useDepth, b1);
    }

    public int getFramebufferObject() {
        return (int) getField(framebufferObject);
    }

    public void setFramebufferObject(int i1) {
        setField(framebufferObject, i1);
    }

    public int getFramebufferTexture() {
        return (int) getField(framebufferTexture);
    }

    public void setFramebufferTexture(int i1) {
        setField(framebufferTexture, i1);
    }

    public int getDepthBuffer() {
        return (int) getField(depthBuffer);
    }

    public void setDepthBuffer(int i1) {
        setField(depthBuffer, i1);
    }

    public float[] getFramebufferColor() {
        return (float[]) getField(framebufferColor);
    }

    public void setFramebufferColor(float[] af1) {
        setField(framebufferColor, af1);
    }

    public int getFramebufferFilter() {
        return (int) getField(framebufferFilter);
    }

    public void setFramebufferFilter(int i1) {
        setField(framebufferFilter, i1);
    }

    public void deleteFramebuffer() {
        invoke(deleteFramebuffer);
    }

    public void framebufferClear() {
        invoke(framebufferClear);
    }

    public void bindFramebuffer(boolean b) {
        invoke(bindFramebuffer, b);
    }

    public void unbindFramebuffer() {
        invoke(unbindFramebuffer);
    }
}
