package com.reflectmc.reflect.utils.render.font;

import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;

import java.awt.*;

public class FontRenderer {
    private StringCache stringCache = null;
    private int stringHeight = 0;

    public FontRenderer(String fontname, int fontsize, boolean antiAlias) {
        stringCache = new StringCache(Minecraft.getMinecraft().getFontRenderer().getColorCode());
        stringCache.setDefaultFont(fontname, fontsize, antiAlias);
        stringCache.cacheHeight();
        stringHeight = stringCache.getHeight();
    }

    public FontRenderer(Font font, int fontsize, boolean antiAlias) {
        stringCache = new StringCache(Minecraft.getMinecraft().getFontRenderer().getColorCode());
        stringCache.setDefaultFont(font, fontsize, antiAlias);
        stringCache.cacheHeight();
        stringHeight = stringCache.getHeight();
        System.out.println("height:"+stringHeight);
    }

    public float drawString(String string, float x, float y, int color) {
        return stringCache.renderString(string, x, y, color, false);
    }

    public float drawString(String string, float x, float y, int color, boolean dark) {
        return stringCache.renderString(string, x, y, dark ? (color & 0xFCFCFC) >> 2 | color & 0xFF000000 : color, dark);
    }

    public int getStringHeight() {
        return stringHeight;
    }

    public float drawCenteredString(String text, float x, float y, int color) {
        return this.drawString(text, x - (float) (this.getStringWidth(text) / 2), y, color);
    }

    public float drawStringWithShadow(String text, double x, double y, int color) {
        return drawStringWithShadow(text, (float) x, (float) y, color);
    }

    public float drawCenteredStringWithShadow(String text, float x, float y, int color) {
        return this.drawStringWithShadow(text, x - (float) (this.getStringWidth(text) / 2), y, color);
    }

    public float drawCenteredStringWithShadow(String text, double x, double y, int color) {
        return this.drawStringWithShadow(text, x - (double) (this.getStringWidth(text) / 2), y, color);
    }

    public int getStringHeight(String str) {
        return stringHeight;
    }

    public float drawStringWithShadow(String string, float x, float y, int color) {
        float i = stringCache.renderString(string, x + 1, y + 1, (color & 0xFCFCFC) >> 2 | color & 0xFF000000, true);
        i = Math.max(i, stringCache.renderString(string, x, y, color, false));
        return i;
    }

    public float getMiddleOfBox(float boxHeight) {
        return boxHeight / 2f - getHeight() / 2f;
    }

    public int getStringWidth(String str) {
        return stringCache.getStringWidth(str);
    }

    public int getHeight() {
        return stringHeight;
    }
}
