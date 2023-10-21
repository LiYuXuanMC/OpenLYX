package al.nya.reflect.gui.notification;

import al.nya.reflect.utils.render.Translate;
import al.nya.reflect.utils.render.font.reflect.FontRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;

public final class Notification {
    private final String title;
    private final String content;
    private final int time;
    private final NotificationType type;
    private final Stopwatch timer;
    private final Translate translate;
    private final FontRenderer fontRenderer;
    public float scissorBoxWidth;

    public Notification(String title, String content, NotificationType type, int time, FontRenderer fontRenderer) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.type = type;
        this.timer = new Stopwatch();
        this.fontRenderer = fontRenderer;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        this.translate = new Translate(sr.getScaledWidth() - this.getWidth(), sr.getScaledHeight() - 30);

    }


    public int getWidth() {
        return Math.max(100, Math.max(this.fontRenderer.getStringWidth(this.title), this.fontRenderer.getStringWidth(this.content)) + 10);
    }

    public FontRenderer getFontRenderer() {
        return this.fontRenderer;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public int getTime() {
        return this.time;
    }

    public NotificationType getType() {
        return this.type;
    }

    public Stopwatch getTimer() {
        return this.timer;
    }

    public Translate getTranslate() {
        return this.translate;
    }
}