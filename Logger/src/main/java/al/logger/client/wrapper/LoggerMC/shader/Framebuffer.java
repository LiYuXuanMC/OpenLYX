package al.logger.client.wrapper.LoggerMC.shader;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.*;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.shader.Framebuffer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.shader.Framebuffer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
//ToDp: Use MethodHandle
public class Framebuffer extends IWrapper {
    @ClassInstance
    public static Class<?> FramebufferClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = {int.class, int.class, boolean.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = {int.class, int.class, boolean.class})
    public static Constructor<?> Constructor_int_int_boolean;

    @WrapMethod(mcpName = "deleteFramebuffer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "deleteFramebuffer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method deleteFramebuffer;

    @WrapMethod(mcpName = "framebufferClear", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "framebufferClear", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method framebufferClear;

    @WrapMethod(mcpName = "unbindFramebuffer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "unbindFramebuffer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method unbindFramebuffer;

    @WrapField(mcpName = "framebufferTextureWidth", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "framebufferTextureWidth", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field framebufferTextureWidth;
    @WrapField(mcpName = "framebufferTextureHeight", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "framebufferTextureHeight", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field framebufferTextureHeight;
    @WrapField(mcpName = "framebufferWidth", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "framebufferWidth", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field framebufferWidth;
    @WrapField(mcpName = "framebufferHeight", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "framebufferHeight", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field framebufferHeight;
    @WrapField(mcpName = "useDepth", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "useDepth", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field useDepth;
    @WrapField(mcpName = "framebufferObject", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "framebufferObject", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field framebufferObject;
    @WrapField(mcpName = "framebufferTexture", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "framebufferTexture", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field framebufferTexture;
    @WrapField(mcpName = "depthBuffer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "depthBuffer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field depthBuffer;
    @WrapField(mcpName = "framebufferColor", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "framebufferColor", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field framebufferColor;
    @WrapField(mcpName = "framebufferFilter", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "framebufferFilter", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field framebufferFilter;
    @WrapMethod(mcpName = "bindFramebuffer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "bindFramebuffer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
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
