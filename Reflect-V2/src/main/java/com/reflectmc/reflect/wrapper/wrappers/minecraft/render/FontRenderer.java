package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.gui.FontRenderer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.gui.FontRenderer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class FontRenderer extends WrapperBase {
    @ClassInstance
    public static Class<?> FontRendererClass;
    @WrapMethod(deobfName = "getStringWidth",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getStringWidth",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getStringWidth;
    @WrapField(deobfName = "FONT_HEIGHT",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "FONT_HEIGHT",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field FONT_HEIGHT;
    @WrapMethod(deobfName = "drawString",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Ljava/lang/String;III)I")
    @WrapMethod(deobfName = "drawString",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(Ljava/lang/String;III)I")
    public static Method drawString_III;
    @WrapMethod(deobfName = "drawString",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(Ljava/lang/String;FFIZ)I")
    @WrapMethod(deobfName = "drawString", targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Ljava/lang/String;FFIZ)I")
    public static Method drawString_FFIZ;
    @WrapMethod(deobfName = "drawStringWithShadow",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "drawStringWithShadow",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method drawStringWithShadow;
    @WrapMethod(deobfName = "setUnicodeFlag",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapMethod(deobfName = "setUnicodeFlag",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method setUnicodeFlag;
    @WrapField(deobfName = "colorCode",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapField(deobfName = "colorCode",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field colorCode;
    @WrapMethod(deobfName = "renderString",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "renderString",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method renderString;//String text, float x, float y, int color, boolean dropShadow

    public FontRenderer(Object obj) {
        super(obj);
    }

    public int[] getColorCode() {
        return (int[]) getField(colorCode);
    }
    public void setColorCodeField(int[] ints) {
        setField(colorCode, ints);
    }
    public int getStringWidth(String s) {
        return (int) invokeMethod(getStringWidth, s);
    }
    public int getFontHeight() {
        return (int) getField(FONT_HEIGHT);
    }
    public int drawString(String p_drawString_1_, float p_drawString_2_, float p_drawString_3_, int p_drawString_4_, boolean p_drawString_5_) {
        return (int) invokeMethod(drawString_FFIZ, p_drawString_1_, p_drawString_2_, p_drawString_3_, p_drawString_4_, p_drawString_5_);
    }
    public int drawString(String text, int x, int y, int color) {
        return (int) invokeMethod(drawString_III, text, x, y, color);
    }
    public int drawStringWithShadow(String text, float x, float y, int color) {
        return (int) invokeMethod(drawStringWithShadow, text, x, y, color);
    }
    public void setUnicodeFlag(boolean b) {
        invokeMethod(setUnicodeFlag,b);
    }
}
