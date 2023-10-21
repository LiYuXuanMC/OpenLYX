package al.nya.reflect.gui;

import al.nya.reflect.key.EnumKey;
import al.nya.reflect.utils.render.font.reflect.FontRenderer;

public class KeyBindBox {
    public float x;
    public float y;
    public float width;
    public float height;
    public EnumKey key;
    public float stringPosX;
    public float stringPosY;
    public float centerX;

    public KeyBindBox(float x, float y, float width, float height, EnumKey key, FontRenderer fontRenderer) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.key = key;
        stringPosX = x + (width / 2) - (fontRenderer.getStringWidth(key.getDisplayName()) / 2);
        stringPosY = y + (height / 2) - (fontRenderer.getStringHeight(key.getDisplayName()) / 2);
        centerX = x + (width / 2);
    }
}
