package al.nya.reflect.utils.render.font.reflect;

import al.nya.reflect.utils.render.font.render.CFontRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;

import java.awt.*;

public class FontRenderer {
    private final boolean isSpecial;
    private CFontRenderer fontRenderer = null;
    private StringCache stringCache = null;
    private int stringHeight = 0;

    public FontRenderer(String fontname, int fontsize, boolean antiAlias) {
        isSpecial = false;
        stringHeight = fontsize;
        stringCache = new StringCache(Minecraft.getMinecraft().getFontRenderer().getColorCodeField());
        stringCache.setDefaultFont(fontname, fontsize, antiAlias);
    }

    public FontRenderer(Font font, int fontsize, boolean antiAlias) {
        isSpecial = false;
        stringHeight = fontsize;
        stringCache = new StringCache(Minecraft.getMinecraft().getFontRenderer().getColorCodeField());
        stringCache.setDefaultFont(font, fontsize, antiAlias);
    }

    public FontRenderer(Font fontFromTTF, boolean b, boolean b1) {
        isSpecial = true;
        fontRenderer = new CFontRenderer(fontFromTTF, b, b1);
    }

    public float drawString(String string, float x, float y, int color) {
        if (isSpecial)
            return fontRenderer.drawString(string, x, y, color);
        return stringCache.renderString(string, x, y, color, false);
    }

    public float drawString(String string, float x, float y, int color, boolean dark) {
        if (isSpecial)
            return fontRenderer.drawString(x, y, color, string, dark);
        return stringCache.renderString(string, x, y, dark ? (color & 0xFCFCFC) >> 2 | color & 0xFF000000 : color, dark);
    }

    public int getStringHeight() {
        if (isSpecial)
            return fontRenderer.getStringHeight("Reflect");
        return stringHeight;
    }

    public float drawCenteredString(String text, float x, float y, int color) {
        if (isSpecial)
            return fontRenderer.drawCenteredString(text, x, y, color);
        return this.drawString(text, x - (float) (this.getStringWidth(text) / 2), y, color);
    }

    public float drawStringWithShadow(String text, double x, double y, int color) {
        if (isSpecial)
            return fontRenderer.drawStringWithShadow(text, x, y, color);
        return drawStringWithShadow(text, (float) x, (float) y, color);
    }

    public float drawCenteredStringWithShadow(String text, float x, float y, int color) {
        if (isSpecial)
            return fontRenderer.drawCenteredStringWithShadow(text, x, y, color);
        return this.drawStringWithShadow(text, x - (float) (this.getStringWidth(text) / 2), y, color);
    }

    public float drawCenteredStringWithShadow(String text, double x, double y, int color) {
        if (isSpecial)
            return fontRenderer.drawCenteredStringWithShadow(text, x, y, color);
        return this.drawStringWithShadow(text, x - (double) (this.getStringWidth(text) / 2), y, color);
    }

    public int getStringHeight(String str) {
        if (isSpecial)
            return fontRenderer.getStringHeight(str);
        return stringHeight;
    }

    public float drawStringWithShadow(String string, float x, float y, int color) {
        if (isSpecial)
            return fontRenderer.drawStringWithShadow(string, x, y, color);
        float i = stringCache.renderString(string, x + 1, y + 1, (color & 0xFCFCFC) >> 2 | color & 0xFF000000, true);
        i = Math.max(i, stringCache.renderString(string, x, y, color, false));
        return i;
    }

    public float getMiddleOfBox(float boxHeight) {
        if (isSpecial)
            return fontRenderer.getMiddleOfBox(boxHeight);
        return boxHeight / 2f - getHeight() / 2f;
    }

    public int getStringWidth(String str) {
        if (isSpecial)
            return fontRenderer.getStringWidth(str);
        return stringCache.getStringWidth(str);
    }

    public int getHeight() {
        if (isSpecial)
            return fontRenderer.getHeight();
        return stringHeight;
    }
}
