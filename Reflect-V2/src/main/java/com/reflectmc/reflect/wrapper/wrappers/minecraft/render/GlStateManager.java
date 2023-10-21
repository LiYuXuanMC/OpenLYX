package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.Wrapper;
import com.reflectmc.reflect.wrapper.annotation.CactusWrapping;
import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Method;
import java.nio.FloatBuffer;

@WrapperClass(deobfName = "net.minecraft.client.renderer.GlStateManager",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.GlStateManager",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class GlStateManager extends WrapperBase {
    @ClassInstance
    public static Class GlStateManagerClass;
    @WrapMethod(deobfName = "alphaFunc", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "alphaFunc", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method alphaFunc;
    @WrapMethod(deobfName = "bindTexture", targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(I)V")
    @WrapMethod(deobfName = "bindTexture", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(I)V")
    @CactusWrapping
    public static Method bindTexture;
    @WrapMethod(deobfName = "blendFunc", targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(II)V")
    @WrapMethod(deobfName = "blendFunc", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(II)V")
    @CactusWrapping
    public static Method blendFunc;
    @WrapMethod(deobfName = "callList", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "callList", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method callList;
    @WrapMethod(deobfName = "clear", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "clear", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method clear;
    @WrapMethod(deobfName = "clearColor", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "clearColor", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method clearColor;
    @WrapMethod(deobfName = "clearDepth", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "clearDepth", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method clearDepth;
    @WrapMethod(deobfName = "color", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "(FFF)V")
    @WrapMethod(deobfName = "color", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = "(FFF)V")
    @CactusWrapping
    public static Method color_FFF;
    @WrapMethod(deobfName = "color", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "(FFFF)V")
    @WrapMethod(deobfName = "color", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = "(FFFF)V")
    @CactusWrapping
    public static Method color_FFFF;
    @WrapMethod(deobfName = "colorLogicOp", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "colorLogicOp", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method colorLogicOp;
    @WrapMethod(deobfName = "colorMask", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "colorMask", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method colorMask;
    @WrapMethod(deobfName = "colorMaterial", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "colorMaterial", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method colorMaterial;
    @WrapMethod(deobfName = "cullFace", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "cullFace", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method cullFace;
    @WrapMethod(deobfName = "deleteTexture", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "deleteTexture", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method deleteTexture;
    @WrapMethod(deobfName = "depthFunc", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "depthFunc", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method depthFunc;
    @WrapMethod(deobfName = "depthMask", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "depthMask", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method depthMask;
    @WrapMethod(deobfName = "disableAlpha", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableAlpha", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableAlpha;
    @WrapMethod(deobfName = "disableBlend", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableBlend", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableBlend;
    @WrapMethod(deobfName = "disableColorLogic", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableColorLogic", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableColorLogic;
    @WrapMethod(deobfName = "disableColorMaterial", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableColorMaterial", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableColorMaterial;
    @WrapMethod(deobfName = "disableCull", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableCull", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableCull;
    @WrapMethod(deobfName = "disableDepth", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableDepth", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableDepth;
    @WrapMethod(deobfName = "disableFog", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableFog", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableFog;
    @WrapMethod(deobfName = "disableLight", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableLight", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableLight;
    @WrapMethod(deobfName = "disableLighting", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableLighting", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableLighting;
    @WrapMethod(deobfName = "disableNormalize", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableNormalize", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableNormalize;
    @WrapMethod(deobfName = "disablePolygonOffset", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disablePolygonOffset", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disablePolygonOffset;
    @WrapMethod(deobfName = "disableRescaleNormal", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableRescaleNormal", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableRescaleNormal;
    @WrapMethod(deobfName = "disableTexGenCoord", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableTexGenCoord", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableTexGenCoord;
    @WrapMethod(deobfName = "disableTexture2D", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableTexture2D", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableTexture2D;
    @WrapMethod(deobfName = "doPolygonOffset", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "doPolygonOffset", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method doPolygonOffset;
    @WrapMethod(deobfName = "enableAlpha", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableAlpha", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableAlpha;
    @WrapMethod(deobfName = "enableBlend", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableBlend", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableBlend;
    @WrapMethod(deobfName = "enableColorLogic", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableColorLogic", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableColorLogic;
    @WrapMethod(deobfName = "enableColorMaterial", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableColorMaterial", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableColorMaterial;
    @WrapMethod(deobfName = "enableCull", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableCull", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableCull;
    @WrapMethod(deobfName = "enableDepth", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableDepth", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableDepth;
    @WrapMethod(deobfName = "enableFog", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableFog", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableFog;
    @WrapMethod(deobfName = "enableLight", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableLight", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableLight;
    @WrapMethod(deobfName = "enableLighting", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableLighting", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableLighting;
    @WrapMethod(deobfName = "enableNormalize", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableNormalize", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableNormalize;
    @WrapMethod(deobfName = "enablePolygonOffset", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enablePolygonOffset", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enablePolygonOffset;
    @WrapMethod(deobfName = "enableRescaleNormal", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableRescaleNormal", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableRescaleNormal;
    @WrapMethod(deobfName = "enableTexGenCoord", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableTexGenCoord", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableTexGenCoord;
    @WrapMethod(deobfName = "enableTexture2D", targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "()V")
    @WrapMethod(deobfName = "enableTexture2D", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "()V")
    @CactusWrapping
    public static Method enableTexture2D;
    @WrapMethod(deobfName = "generateTexture", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "generateTexture", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method generateTexture;
    @WrapMethod(deobfName = "getFloat", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getFloat", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getFloat;
    @WrapMethod(deobfName = "loadIdentity", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "loadIdentity", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method loadIdentity;
    @WrapMethod(deobfName = "matrixMode", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "matrixMode", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method matrixMode;
    @WrapMethod(deobfName = "multMatrix", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "multMatrix", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method multMatrix;
    @WrapMethod(deobfName = "ortho", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "ortho", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method ortho;
    @WrapMethod(deobfName = "popAttrib", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "popAttrib", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method popAttrib;
    @WrapMethod(deobfName = "popMatrix", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "popMatrix", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method popMatrix;
    @WrapMethod(deobfName = "pushAttrib", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "pushAttrib", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method pushAttrib;
    @WrapMethod(deobfName = "pushMatrix", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "pushMatrix", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method pushMatrix;
    @WrapMethod(deobfName = "resetColor", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "resetColor", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method resetColor;
    @WrapMethod(deobfName = "rotate", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "rotate", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method rotate;
    @WrapMethod(deobfName = "scale", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "(DDD)V")
    @WrapMethod(deobfName = "scale", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = "(DDD)V")
    public static Method scale_DDD;
    @WrapMethod(deobfName = "scale", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "(FFF)V")
    @WrapMethod(deobfName = "scale", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = "(FFF)V")
    public static Method scale_FFF;
    @WrapMethod(deobfName = "setActiveTexture", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setActiveTexture", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setActiveTexture;
    @WrapMethod(deobfName = "setFog", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setFog", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setFog;
    @WrapMethod(deobfName = "setFogDensity", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setFogDensity", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setFogDensity;
    @WrapMethod(deobfName = "setFogEnd", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setFogEnd", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setFogEnd;
    @WrapMethod(deobfName = "setFogStart", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setFogStart", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setFogStart;
    @WrapMethod(deobfName = "shadeModel", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "shadeModel", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method shadeModel;
    @WrapMethod(deobfName = "texGen", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;ILjava/nio/FloatBuffer;)V")
    @WrapMethod(deobfName = "texGen", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;ILjava/nio/FloatBuffer;)V")
    public static Method texGen_NJ;
    @WrapMethod(deobfName = "texGen", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;I)V")
    @WrapMethod(deobfName = "texGen", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;I)V")
    public static Method texGen_NI;
    @WrapMethod(deobfName = "texGenCoord", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "texGenCoord", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method texGenCoord;
    @WrapMethod(deobfName = "translate", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "(FFF)V")
    @WrapMethod(deobfName = "translate", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = "(FFF)V")
    public static Method translate_FFF;
    @WrapMethod(deobfName = "translate", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "(DDD)V")
    @WrapMethod(deobfName = "translate", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = "(DDD)V")
    public static Method translate_DDD;
    @WrapMethod(deobfName = "tryBlendFuncSeparate", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "tryBlendFuncSeparate", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method tryBlendFuncSeparate;
    @WrapMethod(deobfName = "viewport", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "viewport", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method viewport;
    @WrapMethod(deobfName = "glTexEnvf",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method glTexEnvf;
    @WrapMethod(deobfName = "glTexEnvi",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method glTexEnvi;

    public GlStateManager(Object obj) {
        super(obj);
    }

    public static void glTexEnvi(int glTextureEnv, int glTextureEnvMode, int glModulate) {
        if (Wrapper.getMapper().isVersionMatch(Environment.Forge1122,Environment.Vanilla1122))
            invokeStaticMethod(glTexEnvi,null,glTextureEnv,glTextureEnvMode,glModulate);
        else
            GL11.glTexEnvi(glTextureEnv, glTextureEnvMode, glModulate);
    }

    public static void enableCull() {
        invokeStaticMethod(enableCull);
    }

    public static void enableColorMaterial() {
        invokeStaticMethod(enableColorMaterial);
    }

    public static void disableRescaleNormal() {
        invokeStaticMethod(disableRescaleNormal);
    }

    public static void color(float f1, float f2, float f3, float f4) {
        invokeStaticMethod(color_FFFF, f1, f2, f3, f4);
    }

    public static void disableCull() {
        invokeStaticMethod(disableCull);
    }

    public static void disableDepth() {
        invokeStaticMethod(disableDepth);
    }

    public static void disableLighting() {
        invokeStaticMethod(disableLighting);
    }

    public static void enableAlpha() {
        invokeStaticMethod(enableAlpha);
    }

    public static void enableLighting() {
        invokeStaticMethod(enableLighting);
    }

    public static void disableAlpha() {
        invokeStaticMethod(disableAlpha);
    }

    public static void disableBlend() {
        invokeStaticMethod(disableBlend);
    }

    public static void enableBlend() {
        invokeStaticMethod(enableBlend);
    }

    public static void bindTexture(int texture) {
        invokeStaticMethod(bindTexture, texture);
    }

    public static void blendFunc(int srcFactor, int dstFactor) {
        invokeStaticMethod(blendFunc, srcFactor, dstFactor);
    }

    public static void enableDepth() {
        invokeStaticMethod(enableDepth);
    }

    public static void clear(int mask) {
        invokeStaticMethod(clear, mask);
    }

    public static void pushMatrix() {
        invokeStaticMethod(pushMatrix);
    }

    public static void popMatrix() {
        invokeStaticMethod(popMatrix);
    }

    public static void rotate(float angle, float x, float y, float z) {
        invokeStaticMethod(rotate, angle, x, y, z);
    }

    public static void setActiveTexture(int texture) {
        invokeStaticMethod(setActiveTexture, texture);
    }

    public static void scale(double x, double y, double z) {
        invokeStaticMethod(scale_DDD, x, y, z);
    }

    public static void translate(float x, float y, float z) {
        invokeStaticMethod(translate_FFF, x, y, z);
    }

    public static void translate(double x, double y, double z) {
        invokeStaticMethod(translate_DDD, x, y, z);
    }

    public static void multMatrix(FloatBuffer matrix) {
        invokeStaticMethod(multMatrix, matrix);
    }

    public static void enableTexture2D() {
        invokeStaticMethod(enableTexture2D);
    }

    public static void disableTexture2D() {
        invokeStaticMethod(disableTexture2D);
    }

    public static void resetColor() {
        invokeStaticMethod(resetColor);
    }

    public static void tryBlendFuncSeparate(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {
        invokeStaticMethod(tryBlendFuncSeparate, srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
    }

    public static void shadeModel(int mode) {
        invokeStaticMethod(shadeModel, mode);
    }

    public static void disableLight(int light) {
        invokeStaticMethod(disableLight, light);
    }

    public static void disableColorMaterial() {
        invokeStaticMethod(disableColorMaterial);
    }

    public static void color(float i, float i1, float i2) {
        invokeStaticMethod(color_FFF, i, i1, i2);
    }

    public static void matrixMode(int i) {
        invokeStaticMethod(matrixMode, i);
    }

    public static void loadIdentity() {
        invokeStaticMethod(loadIdentity);
    }

    public static void alphaFunc(int i, float v) {
        invokeStaticMethod(alphaFunc, i, v);
    }
}