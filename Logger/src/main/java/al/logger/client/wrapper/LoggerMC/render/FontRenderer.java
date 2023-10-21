package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.gui.FontRenderer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.gui.FontRenderer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class FontRenderer extends IWrapper {
@ClassInstance    
public static Class<?> FontRendererClass;
    @WrapMethod(mcpName = "getStringWidth",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getStringWidth",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getStringWidth;
    @WrapField(mcpName = "FONT_HEIGHT",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "FONT_HEIGHT",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field FONT_HEIGHT;
    @WrapMethod(mcpName = "drawString",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(Ljava/lang/String;III)I")
    @WrapMethod(mcpName = "drawString",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(Ljava/lang/String;III)I")
    public static Method drawString_III;
    @WrapMethod(mcpName = "drawString",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(Ljava/lang/String;FFIZ)I")
    @WrapMethod(mcpName = "drawString", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(Ljava/lang/String;FFIZ)I")
    public static Method drawString_FFIZ;
    @WrapMethod(mcpName = "drawStringWithShadow",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "drawStringWithShadow",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method drawStringWithShadow;
    @WrapMethod(mcpName = "setUnicodeFlag",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "setUnicodeFlag",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method setUnicodeFlag;
    @WrapField(mcpName = "colorCode",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapField(mcpName = "colorCode",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field colorCode;
    @WrapMethod(mcpName = "renderString",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "renderString",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method renderString;//String text, float x, float y, int color, boolean dropShadow

    public FontRenderer(Object obj) {
        super(obj);
    }

    public int[] getColorCodeField() {
        return (int[]) getField(colorCode);
    }

    public void setColorCodeField(int[] ints) {
        setField(colorCode, ints);
    }

    public int getStringWidth(String s) {
        return (int) invoke(getStringWidth, s);
    }

    public int getFontHeight() {
        return (int) getField(FONT_HEIGHT);
    }

    public int drawString(String p_drawString_1_, float p_drawString_2_, float p_drawString_3_, int p_drawString_4_, boolean p_drawString_5_) {
        return (int) invoke(drawString_FFIZ, p_drawString_1_, p_drawString_2_, p_drawString_3_, p_drawString_4_, p_drawString_5_);
    }

    public int drawString(String text, int x, int y, int color) {
        return (int) invoke(drawString_III, text, x, y, color);
    }

    public int drawStringWithShadow(String text, float x, float y, int color) {
        return (int) invoke(drawStringWithShadow, text, x, y, color);
    }

    public void setUnicodeFlag(boolean b) {
        invoke(setUnicodeFlag,b);
    }
}
