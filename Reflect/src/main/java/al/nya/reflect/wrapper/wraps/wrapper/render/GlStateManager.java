package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.utils.client.EnvDetector;
import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Method;
import java.nio.FloatBuffer;

@WrapperClass(mcpName = "net.minecraft.client.renderer.GlStateManager",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.GlStateManager",targetMap = Maps.Srg1_12_2)
public class GlStateManager extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.renderer.GlStateManager", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.renderer.GlStateManager", targetMap = Maps.Srg1_12_2)
    public static Class GlStateManagerClass;
    @WrapMethod(mcpName = "alphaFunc", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "alphaFunc", targetMap = Maps.Srg1_12_2)
    public static Method alphaFunc;
    @WrapMethod(mcpName = "bindTexture", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "bindTexture", targetMap = Maps.Srg1_12_2)
    public static Method bindTexture;
    @WrapMethod(mcpName = "blendFunc", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "blendFunc", targetMap = Maps.Srg1_12_2)
    public static Method blendFunc;
    @WrapMethod(mcpName = "callList", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "callList", targetMap = Maps.Srg1_12_2)
    public static Method callList;
    @WrapMethod(mcpName = "clear", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "clear", targetMap = Maps.Srg1_12_2)
    public static Method clear;
    @WrapMethod(mcpName = "clearColor", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "clearColor", targetMap = Maps.Srg1_12_2)
    public static Method clearColor;
    @WrapMethod(mcpName = "clearDepth", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "clearDepth", targetMap = Maps.Srg1_12_2)
    public static Method clearDepth;
    @WrapMethod(mcpName = "color", targetMap = Maps.Srg1_8_9, signature = "(FFF)V")
    @WrapMethod(mcpName = "color", targetMap = Maps.Srg1_12_2, signature = "(FFF)V")
    public static Method color_FFF;
    @WrapMethod(mcpName = "color", targetMap = Maps.Srg1_8_9, signature = "(FFFF)V")
    @WrapMethod(mcpName = "color", targetMap = Maps.Srg1_12_2, signature = "(FFFF)V")
    public static Method color_FFFF;
    @WrapMethod(mcpName = "colorLogicOp", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "colorLogicOp", targetMap = Maps.Srg1_12_2)
    public static Method colorLogicOp;
    @WrapMethod(mcpName = "colorMask", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "colorMask", targetMap = Maps.Srg1_12_2)
    public static Method colorMask;
    @WrapMethod(mcpName = "colorMaterial", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "colorMaterial", targetMap = Maps.Srg1_12_2)
    public static Method colorMaterial;
    @WrapMethod(mcpName = "cullFace", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "cullFace", targetMap = Maps.Srg1_12_2)
    public static Method cullFace;
    @WrapMethod(mcpName = "deleteTexture", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "deleteTexture", targetMap = Maps.Srg1_12_2)
    public static Method deleteTexture;
    @WrapMethod(mcpName = "depthFunc", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "depthFunc", targetMap = Maps.Srg1_12_2)
    public static Method depthFunc;
    @WrapMethod(mcpName = "depthMask", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "depthMask", targetMap = Maps.Srg1_12_2)
    public static Method depthMask;
    @WrapMethod(mcpName = "disableAlpha", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableAlpha", targetMap = Maps.Srg1_12_2)
    public static Method disableAlpha;
    @WrapMethod(mcpName = "disableBlend", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableBlend", targetMap = Maps.Srg1_12_2)
    public static Method disableBlend;
    @WrapMethod(mcpName = "disableColorLogic", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableColorLogic", targetMap = Maps.Srg1_12_2)
    public static Method disableColorLogic;
    @WrapMethod(mcpName = "disableColorMaterial", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableColorMaterial", targetMap = Maps.Srg1_12_2)
    public static Method disableColorMaterial;
    @WrapMethod(mcpName = "disableCull", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableCull", targetMap = Maps.Srg1_12_2)
    public static Method disableCull;
    @WrapMethod(mcpName = "disableDepth", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableDepth", targetMap = Maps.Srg1_12_2)
    public static Method disableDepth;
    @WrapMethod(mcpName = "disableFog", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableFog", targetMap = Maps.Srg1_12_2)
    public static Method disableFog;
    @WrapMethod(mcpName = "disableLight", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableLight", targetMap = Maps.Srg1_12_2)
    public static Method disableLight;
    @WrapMethod(mcpName = "disableLighting", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableLighting", targetMap = Maps.Srg1_12_2)
    public static Method disableLighting;
    @WrapMethod(mcpName = "disableNormalize", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableNormalize", targetMap = Maps.Srg1_12_2)
    public static Method disableNormalize;
    @WrapMethod(mcpName = "disablePolygonOffset", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disablePolygonOffset", targetMap = Maps.Srg1_12_2)
    public static Method disablePolygonOffset;
    @WrapMethod(mcpName = "disableRescaleNormal", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableRescaleNormal", targetMap = Maps.Srg1_12_2)
    public static Method disableRescaleNormal;
    @WrapMethod(mcpName = "disableTexGenCoord", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableTexGenCoord", targetMap = Maps.Srg1_12_2)
    public static Method disableTexGenCoord;
    @WrapMethod(mcpName = "disableTexture2D", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "disableTexture2D", targetMap = Maps.Srg1_12_2)
    public static Method disableTexture2D;
    @WrapMethod(mcpName = "doPolygonOffset", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "doPolygonOffset", targetMap = Maps.Srg1_12_2)
    public static Method doPolygonOffset;
    @WrapMethod(mcpName = "enableAlpha", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableAlpha", targetMap = Maps.Srg1_12_2)
    public static Method enableAlpha;
    @WrapMethod(mcpName = "enableBlend", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableBlend", targetMap = Maps.Srg1_12_2)
    public static Method enableBlend;
    @WrapMethod(mcpName = "enableColorLogic", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableColorLogic", targetMap = Maps.Srg1_12_2)
    public static Method enableColorLogic;
    @WrapMethod(mcpName = "enableColorMaterial", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableColorMaterial", targetMap = Maps.Srg1_12_2)
    public static Method enableColorMaterial;
    @WrapMethod(mcpName = "enableCull", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableCull", targetMap = Maps.Srg1_12_2)
    public static Method enableCull;
    @WrapMethod(mcpName = "enableDepth", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableDepth", targetMap = Maps.Srg1_12_2)
    public static Method enableDepth;
    @WrapMethod(mcpName = "enableFog", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableFog", targetMap = Maps.Srg1_12_2)
    public static Method enableFog;
    @WrapMethod(mcpName = "enableLight", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableLight", targetMap = Maps.Srg1_12_2)
    public static Method enableLight;
    @WrapMethod(mcpName = "enableLighting", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableLighting", targetMap = Maps.Srg1_12_2)
    public static Method enableLighting;
    @WrapMethod(mcpName = "enableNormalize", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableNormalize", targetMap = Maps.Srg1_12_2)
    public static Method enableNormalize;
    @WrapMethod(mcpName = "enablePolygonOffset", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enablePolygonOffset", targetMap = Maps.Srg1_12_2)
    public static Method enablePolygonOffset;
    @WrapMethod(mcpName = "enableRescaleNormal", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableRescaleNormal", targetMap = Maps.Srg1_12_2)
    public static Method enableRescaleNormal;
    @WrapMethod(mcpName = "enableTexGenCoord", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableTexGenCoord", targetMap = Maps.Srg1_12_2)
    public static Method enableTexGenCoord;
    @WrapMethod(mcpName = "enableTexture2D", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "enableTexture2D", targetMap = Maps.Srg1_12_2)
    public static Method enableTexture2D;
    @WrapMethod(mcpName = "generateTexture", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "generateTexture", targetMap = Maps.Srg1_12_2)
    public static Method generateTexture;
    @WrapMethod(mcpName = "getFloat", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getFloat", targetMap = Maps.Srg1_12_2)
    public static Method getFloat;
    @WrapMethod(mcpName = "loadIdentity", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "loadIdentity", targetMap = Maps.Srg1_12_2)
    public static Method loadIdentity;
    @WrapMethod(mcpName = "matrixMode", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "matrixMode", targetMap = Maps.Srg1_12_2)
    public static Method matrixMode;
    @WrapMethod(mcpName = "multMatrix", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "multMatrix", targetMap = Maps.Srg1_12_2)
    public static Method multMatrix;
    @WrapMethod(mcpName = "ortho", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "ortho", targetMap = Maps.Srg1_12_2)
    public static Method ortho;
    @WrapMethod(mcpName = "popAttrib", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "popAttrib", targetMap = Maps.Srg1_12_2)
    public static Method popAttrib;
    @WrapMethod(mcpName = "popMatrix", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "popMatrix", targetMap = Maps.Srg1_12_2)
    public static Method popMatrix;
    @WrapMethod(mcpName = "pushAttrib", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "pushAttrib", targetMap = Maps.Srg1_12_2)
    public static Method pushAttrib;
    @WrapMethod(mcpName = "pushMatrix", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "pushMatrix", targetMap = Maps.Srg1_12_2)
    public static Method pushMatrix;
    @WrapMethod(mcpName = "resetColor", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "resetColor", targetMap = Maps.Srg1_12_2)
    public static Method resetColor;
    @WrapMethod(mcpName = "rotate", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "rotate", targetMap = Maps.Srg1_12_2)
    public static Method rotate;
    @WrapMethod(mcpName = "scale", targetMap = Maps.Srg1_8_9, signature = "(DDD)V")
    @WrapMethod(mcpName = "scale", targetMap = Maps.Srg1_12_2, signature = "(DDD)V")
    public static Method scale_DDD;
    @WrapMethod(mcpName = "scale", targetMap = Maps.Srg1_8_9, signature = "(FFF)V")
    @WrapMethod(mcpName = "scale", targetMap = Maps.Srg1_12_2, signature = "(FFF)V")
    public static Method scale_FFF;
    @WrapMethod(mcpName = "setActiveTexture", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setActiveTexture", targetMap = Maps.Srg1_12_2)
    public static Method setActiveTexture;
    @WrapMethod(mcpName = "setFog", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setFog", targetMap = Maps.Srg1_12_2)
    public static Method setFog;
    @WrapMethod(mcpName = "setFogDensity", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setFogDensity", targetMap = Maps.Srg1_12_2)
    public static Method setFogDensity;
    @WrapMethod(mcpName = "setFogEnd", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setFogEnd", targetMap = Maps.Srg1_12_2)
    public static Method setFogEnd;
    @WrapMethod(mcpName = "setFogStart", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setFogStart", targetMap = Maps.Srg1_12_2)
    public static Method setFogStart;
    @WrapMethod(mcpName = "shadeModel", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "shadeModel", targetMap = Maps.Srg1_12_2)
    public static Method shadeModel;
    @WrapMethod(mcpName = "texGen", targetMap = Maps.Srg1_8_9, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;ILjava/nio/FloatBuffer;)V")
    @WrapMethod(mcpName = "texGen", targetMap = Maps.Srg1_12_2, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;ILjava/nio/FloatBuffer;)V")
    public static Method texGen_NJ;
    @WrapMethod(mcpName = "texGen", targetMap = Maps.Srg1_8_9, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;I)V")
    @WrapMethod(mcpName = "texGen", targetMap = Maps.Srg1_12_2, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;I)V")
    public static Method texGen_NI;
    @WrapMethod(mcpName = "texGenCoord", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "texGenCoord", targetMap = Maps.Srg1_12_2)
    public static Method texGenCoord;
    @WrapMethod(mcpName = "translate", targetMap = Maps.Srg1_8_9, signature = "(FFF)V")
    @WrapMethod(mcpName = "translate", targetMap = Maps.Srg1_12_2, signature = "(FFF)V")
    public static Method translate_FFF;
    @WrapMethod(mcpName = "translate", targetMap = Maps.Srg1_8_9, signature = "(DDD)V")
    @WrapMethod(mcpName = "translate", targetMap = Maps.Srg1_12_2, signature = "(DDD)V")
    public static Method translate_DDD;
    @WrapMethod(mcpName = "tryBlendFuncSeparate", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "tryBlendFuncSeparate", targetMap = Maps.Srg1_12_2)
    public static Method tryBlendFuncSeparate;
    @WrapMethod(mcpName = "viewport", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "viewport", targetMap = Maps.Srg1_12_2)
    public static Method viewport;
    @WrapMethod(mcpName = "glTexEnvf",targetMap = Maps.Srg1_12_2)
    public static Method glTexEnvf;
    @WrapMethod(mcpName = "glTexEnvi",targetMap = Maps.Srg1_12_2)
    public static Method glTexEnvi;

    public GlStateManager(Object obj) {
        super(obj);
    }

    public static void glTexEnvi(int glTextureEnv, int glTextureEnvMode, int glModulate) {
        if (EnvDetector.is1122Srg())
            ReflectUtil.invoke(glTexEnvi,null,glTextureEnv,glTextureEnvMode,glModulate);
        else
            GL11.glTexEnvi(glTextureEnv, glTextureEnvMode, glModulate);
    }

    public static void enableCull() {
        ReflectUtil.invoke(enableCull,null);
    }

    public static void enableColorMaterial() {
        ReflectUtil.invoke(enableColorMaterial, null);
    }

    public static void disableRescaleNormal() {
        ReflectUtil.invoke(disableRescaleNormal, null);
    }

    public static void color(float f1, float f2, float f3, float f4) {
        ReflectUtil.invoke(color_FFFF, null, f1, f2, f3, f4);
    }

    public static void disableCull() {
        ReflectUtil.invoke(disableCull, null);
    }

    public static void disableDepth() {
        ReflectUtil.invoke(disableDepth, null);
    }

    public static void disableLighting() {
        ReflectUtil.invoke(disableLighting, null);
    }

    public static void enableAlpha() {
        ReflectUtil.invoke(enableAlpha, null);
    }

    public static void enableLighting() {
        ReflectUtil.invoke(enableLighting, null);
    }

    public static void disableAlpha() {
        ReflectUtil.invoke(disableAlpha, null);
    }

    public static void disableBlend() {
        ReflectUtil.invoke(disableBlend, null);
    }

    public static void enableBlend() {
        ReflectUtil.invoke(enableBlend, null);
    }

    public static void bindTexture(int texture) {
        ReflectUtil.invoke(bindTexture, null, texture);
    }

    public static void blendFunc(int srcFactor, int dstFactor) {
        ReflectUtil.invoke(blendFunc, null, srcFactor, dstFactor);
    }

    public static void enableDepth() {
        ReflectUtil.invoke(enableDepth, null);
    }

    public static void clear(int mask) {
        ReflectUtil.invoke(clear, null, mask);
    }

    public static void pushMatrix() {
        ReflectUtil.invoke(pushMatrix, null);
    }

    public static void popMatrix() {
        ReflectUtil.invoke(popMatrix, null);
    }

    public static void rotate(float angle, float x, float y, float z) {
        ReflectUtil.invoke(rotate, null, angle, x, y, z);
    }

    public static void setActiveTexture(int texture) {
        ReflectUtil.invoke(setActiveTexture, null, texture);
    }

    public static void scale(double x, double y, double z) {
        ReflectUtil.invoke(scale_DDD, null, x, y, z);
    }

    public static void translate(float x, float y, float z) {
        ReflectUtil.invoke(translate_FFF, null, x, y, z);
    }

    public static void translate(double x, double y, double z) {
        ReflectUtil.invoke(translate_DDD, null, x, y, z);
    }

    public static void multMatrix(FloatBuffer matrix) {
        ReflectUtil.invoke(multMatrix, null, matrix);
    }

    public static void enableTexture2D() {
        ReflectUtil.invoke(enableTexture2D, null);
    }

    public static void disableTexture2D() {
        ReflectUtil.invoke(disableTexture2D, null);
    }

    public static void resetColor() {
        ReflectUtil.invoke(resetColor, null);
    }

    public static void tryBlendFuncSeparate(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {
        ReflectUtil.invoke(tryBlendFuncSeparate, null, srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
    }

    public static void shadeModel(int mode) {
        ReflectUtil.invoke(shadeModel, null, mode);
    }

    public static void disableLight(int light) {
        ReflectUtil.invoke(disableLight, null, light);
    }

    public static void disableColorMaterial() {
        ReflectUtil.invoke(disableColorMaterial, null);
    }

    public static void color(float i, float i1, float i2) {
        ReflectUtil.invoke(color_FFF, null, i, i1, i2);
    }

    public static void matrixMode(int i) {
        ReflectUtil.invoke(matrixMode, null, i);
    }

    public static void loadIdentity() {
        ReflectUtil.invoke(loadIdentity, null);
    }

    public static void alphaFunc(int i, float v) {
        ReflectUtil.invoke(alphaFunc, null, i, v);
    }
}