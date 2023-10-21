package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.gui.FontRenderer",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.gui.FontRenderer",targetMap = Maps.Srg1_12_2)
public class FontRenderer extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.gui.FontRenderer",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.gui.FontRenderer",targetMap = Maps.Srg1_12_2)
    public static Class<?> FontRendererClass;
    @WrapMethod(mcpName = "getStringWidth",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getStringWidth",targetMap = Maps.Srg1_12_2)
    public static Method getStringWidth;
    @WrapField(mcpName = "FONT_HEIGHT",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "FONT_HEIGHT",targetMap = Maps.Srg1_12_2)
    public static Field FONT_HEIGHT;
    @WrapMethod(mcpName = "drawString",targetMap = Maps.Srg1_8_9,signature = "(Ljava/lang/String;III)I")
    @WrapMethod(mcpName = "drawString",targetMap = Maps.Srg1_12_2,signature = "(Ljava/lang/String;III)I")
    public static Method drawString_III;
    @WrapMethod(mcpName = "drawString",targetMap = Maps.Srg1_12_2,signature = "(Ljava/lang/String;FFIZ)I")
    @WrapMethod(mcpName = "drawString", targetMap = Maps.Srg1_8_9,signature = "(Ljava/lang/String;FFIZ)I")
    public static Method drawString_FFIZ;
    @WrapMethod(mcpName = "drawStringWithShadow",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "drawStringWithShadow",targetMap = Maps.Srg1_12_2)
    public static Method drawStringWithShadow;
    @WrapMethod(mcpName = "setUnicodeFlag",targetMap = Maps.Srg1_12_2)
    @WrapMethod(mcpName = "setUnicodeFlag",targetMap = Maps.Srg1_8_9)
    public static Method setUnicodeFlag;
    @WrapField(mcpName = "colorCode",targetMap = Maps.Srg1_12_2)
    @WrapField(mcpName = "colorCode",targetMap = Maps.Srg1_8_9)
    public static Field colorCode;
    @WrapMethod(mcpName = "renderString",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "renderString",targetMap = Maps.Srg1_12_2)
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
