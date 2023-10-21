package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.annotations.CactusWrapping;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Method;
import java.nio.FloatBuffer;

@WrapperClass(mcpName = "net.minecraft.client.renderer.GlStateManager",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.GlStateManager",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class GlStateManager extends IWrapper {
    @ClassInstance
    public static Class GlStateManagerClass;
    @WrapMethod(mcpName = "alphaFunc", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(IF)V")
    @WrapMethod(mcpName = "alphaFunc", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping
    public static Method alphaFunc;
    @WrapMethod(mcpName = "bindTexture", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(I)V")
    @WrapMethod(mcpName = "bindTexture", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(I)V")
    @CactusWrapping
    public static Method bindTexture;
    @WrapMethod(mcpName = "blendFunc", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(II)V")
    @WrapMethod(mcpName = "blendFunc", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(II)V")
    @CactusWrapping
    public static Method blendFunc;
    @WrapMethod(mcpName = "callList", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "callList", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method callList;
    @WrapMethod(mcpName = "clear", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "clear", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method clear;
    @WrapMethod(mcpName = "clearColor", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "clearColor", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method clearColor;
    @WrapMethod(mcpName = "clearDepth", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "clearDepth", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method clearDepth;
    @WrapMethod(mcpName = "color", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "(FFF)V")
    @WrapMethod(mcpName = "color", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "(FFF)V")
    @CactusWrapping
    public static Method color_FFF;
    @WrapMethod(mcpName = "color", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "(FFFF)V")
    @WrapMethod(mcpName = "color", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "(FFFF)V")
    @CactusWrapping
    public static Method color_FFFF;
    @WrapMethod(mcpName = "colorLogicOp", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "colorLogicOp", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method colorLogicOp;
    @WrapMethod(mcpName = "colorMask", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "colorMask", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method colorMask;
    @WrapMethod(mcpName = "colorMaterial", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "colorMaterial", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method colorMaterial;
    @WrapMethod(mcpName = "cullFace", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "cullFace", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method cullFace;
    @WrapMethod(mcpName = "deleteTexture", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "deleteTexture", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method deleteTexture;
    @WrapMethod(mcpName = "depthFunc", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "depthFunc", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method depthFunc;
    @WrapMethod(mcpName = "depthMask", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "depthMask", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method depthMask;
    @WrapMethod(mcpName = "disableAlpha", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableAlpha", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableAlpha;
    @WrapMethod(mcpName = "disableBlend", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "()V")
    @WrapMethod(mcpName = "disableBlend", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping
    public static Method disableBlend;
    @WrapMethod(mcpName = "disableColorLogic", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableColorLogic", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableColorLogic;
    @WrapMethod(mcpName = "disableColorMaterial", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableColorMaterial", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableColorMaterial;
    @WrapMethod(mcpName = "disableCull", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableCull", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableCull;
    @WrapMethod(mcpName = "disableDepth", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableDepth", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableDepth;
    @WrapMethod(mcpName = "disableFog", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableFog", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableFog;
    @WrapMethod(mcpName = "disableLight", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableLight", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableLight;
    @WrapMethod(mcpName = "disableLighting", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableLighting", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableLighting;
    @WrapMethod(mcpName = "disableNormalize", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableNormalize", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableNormalize;
    @WrapMethod(mcpName = "disablePolygonOffset", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disablePolygonOffset", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disablePolygonOffset;
    @WrapMethod(mcpName = "disableRescaleNormal", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableRescaleNormal", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableRescaleNormal;
    @WrapMethod(mcpName = "disableTexGenCoord", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableTexGenCoord", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableTexGenCoord;
    @WrapMethod(mcpName = "disableTexture2D", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "disableTexture2D", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method disableTexture2D;
    @WrapMethod(mcpName = "doPolygonOffset", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "doPolygonOffset", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method doPolygonOffset;
    @WrapMethod(mcpName = "enableAlpha", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableAlpha", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enableAlpha;
    @WrapMethod(mcpName = "enableBlend", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "()V")
    @WrapMethod(mcpName = "enableBlend", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping
    public static Method enableBlend;
    @WrapMethod(mcpName = "enableColorLogic", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableColorLogic", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enableColorLogic;
    @WrapMethod(mcpName = "enableColorMaterial", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableColorMaterial", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enableColorMaterial;
    @WrapMethod(mcpName = "enableCull", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableCull", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enableCull;
    @WrapMethod(mcpName = "enableDepth", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableDepth", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enableDepth;
    @WrapMethod(mcpName = "enableFog", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableFog", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enableFog;
    @WrapMethod(mcpName = "enableLight", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableLight", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enableLight;
    @WrapMethod(mcpName = "enableLighting", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableLighting", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enableLighting;
    @WrapMethod(mcpName = "enableNormalize", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableNormalize", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enableNormalize;
    @WrapMethod(mcpName = "enablePolygonOffset", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enablePolygonOffset", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enablePolygonOffset;
    @WrapMethod(mcpName = "enableRescaleNormal", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableRescaleNormal", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enableRescaleNormal;
    @WrapMethod(mcpName = "enableTexGenCoord", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "enableTexGenCoord", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method enableTexGenCoord;
    @WrapMethod(mcpName = "enableTexture2D", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "()V")
    @WrapMethod(mcpName = "enableTexture2D", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping
    public static Method enableTexture2D;
    @WrapMethod(mcpName = "generateTexture", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "generateTexture", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method generateTexture;
    @WrapMethod(mcpName = "getFloat", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getFloat", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getFloat;
    @WrapMethod(mcpName = "loadIdentity", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "loadIdentity", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method loadIdentity;
    @WrapMethod(mcpName = "matrixMode", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(I)V")
    @WrapMethod(mcpName = "matrixMode", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping
    public static Method matrixMode;
    @WrapMethod(mcpName = "multMatrix", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "multMatrix", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method multMatrix;
    @WrapMethod(mcpName = "ortho", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "ortho", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method ortho;
    @WrapMethod(mcpName = "popAttrib", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "popAttrib", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method popAttrib;
    @WrapMethod(mcpName = "popMatrix", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "()V")
    @WrapMethod(mcpName = "popMatrix", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping()
    public static Method popMatrix;
    @WrapMethod(mcpName = "pushAttrib", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "pushAttrib", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method pushAttrib;
    @WrapMethod(mcpName = "pushMatrix", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "()V")
    @WrapMethod(mcpName = "pushMatrix", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping()
    public static Method pushMatrix;
    @WrapMethod(mcpName = "resetColor", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "resetColor", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method resetColor;
    @WrapMethod(mcpName = "rotate", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "rotate", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method rotate;
    @WrapMethod(mcpName = "scale", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "(DDD)V")
    @WrapMethod(mcpName = "scale", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "(DDD)V")
    @CactusWrapping
    public static Method scale_DDD;
    @WrapMethod(mcpName = "scale", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "(FFF)V")
    @WrapMethod(mcpName = "scale", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "(FFF)V")
    public static Method scale_FFF;
    @WrapMethod(mcpName = "setActiveTexture", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setActiveTexture", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setActiveTexture;
    @WrapMethod(mcpName = "setFog", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setFog", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setFog;
    @WrapMethod(mcpName = "setFogDensity", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setFogDensity", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setFogDensity;
    @WrapMethod(mcpName = "setFogEnd", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setFogEnd", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setFogEnd;
    @WrapMethod(mcpName = "setFogStart", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setFogStart", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setFogStart;
    @WrapMethod(mcpName = "shadeModel", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "shadeModel", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method shadeModel;
    @WrapMethod(mcpName = "texGen", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;ILjava/nio/FloatBuffer;)V")
    @WrapMethod(mcpName = "texGen", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;ILjava/nio/FloatBuffer;)V")
    public static Method texGen_NJ;
    @WrapMethod(mcpName = "texGen", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;I)V")
    @WrapMethod(mcpName = "texGen", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "(Lnet/minecraft/client/renderer/GlStateManager$TexGen;I)V")
    public static Method texGen_NI;
    @WrapMethod(mcpName = "texGenCoord", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "texGenCoord", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method texGenCoord;
    @WrapMethod(mcpName = "translate", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "(FFF)V")
    @WrapMethod(mcpName = "translate", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "(FFF)V")
    @CactusWrapping
    public static Method translate_FFF;
    @WrapMethod(mcpName = "translate", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "(DDD)V")
    @WrapMethod(mcpName = "translate", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "(DDD)V")
    public static Method translate_DDD;
    @WrapMethod(mcpName = "tryBlendFuncSeparate", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "tryBlendFuncSeparate", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method tryBlendFuncSeparate;
    @WrapMethod(mcpName = "viewport", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "viewport", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method viewport;
    @WrapMethod(mcpName = "glTexEnvf",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method glTexEnvf;
    @WrapMethod(mcpName = "glTexEnvi",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method glTexEnvi;

    public GlStateManager(Object obj) {
        super(obj);
    }

    public static void glTexEnvi(int glTextureEnv, int glTextureEnvMode, int glModulate) {
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

    public static void colorMask(boolean p_colorMask_0_, boolean p_colorMask_1_, boolean p_colorMask_2_, boolean p_colorMask_3_) {
        invokeStatic(colorMask, p_colorMask_0_, p_colorMask_1_, p_colorMask_2_, p_colorMask_3_);
    }
}