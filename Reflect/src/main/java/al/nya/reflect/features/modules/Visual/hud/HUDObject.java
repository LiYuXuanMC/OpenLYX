package al.nya.reflect.features.modules.Visual.hud;

import al.nya.reflect.events.events.client.EventShader;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.Visual.HUD;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.utils.render.Translate;
import al.nya.reflect.utils.render.font.FontManager;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiChat;
import lombok.Getter;
import lombok.Setter;
import org.lwjgl.input.Mouse;

import java.awt.*;

public class HUDObject {
    public Minecraft mc = Minecraft.getMinecraft();
    public Translate translate;
    public OptionValue enable;
    @Getter
    @Setter
    int PosX = 0;
    @Getter
    @Setter
    int PosY = 0;
    @Getter
    @Setter
    int width;
    @Getter
    @Setter
    String name;
    private float dragX, dragY;
    private boolean drag = false;
    @Getter
    @Setter
    private int color;
    @Getter
    @Setter
    public String title = "";
    @Getter
    @Setter
    public boolean isSpecial = false;

    public HUDObject(int width, String name) {
        this.width = width;
        this.name = name;
        translate = new Translate(0, 0);
        enable = new OptionValue(name, true);
        ModuleManager.getModule(HUD.class).addValue(enable);
    }

    public HUDObject(int PosX, int PosY, int width, String name) {
        this.PosX = PosX;
        this.PosY = PosY;
        this.width = width;
        this.name = name;
        translate = new Translate(PosX, PosY);
        enable = new OptionValue(name, true);
        ModuleManager.getModule(HUD.class).addValue(enable);
    }

    public static boolean isHovered(float x, float y, float x2, float y2, int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
    }

    public boolean doDraw() {
        return enable.getValue();
    }

    public void drawHUD(int x, int y, float partialTicks, boolean isEditing) {
    }

    public void onBlur(EventShader event) {
    }

    public void doDrag(int mouseX, int mouseY) {
        if (this.drag) {
            if (!Mouse.isButtonDown(0)) {
                this.drag = false;
            }
            this.PosX = (int) (mouseX - this.dragX);
            this.PosY = (int) (mouseY - this.dragY);
        }
    }

    public void mouseClick(int mouseX, int mouseY, int mouseButton) {
        if (mouseX > this.PosX - 2 && mouseX < this.PosX + 92 && mouseY > this.PosY - 2 && mouseY < this.PosY + 17) {
            if (mouseButton == 1) {
                enable.setValue(!enable.getValue());
            }
            if (mouseButton == 0) {
                this.drag = true;
                this.dragX = mouseX - this.PosX;
                this.dragY = mouseY - this.PosY;
            }
        }
    }

    public void draw(float partialTicks) {
        if (!doDraw())
            return;
        boolean flag = GuiChat.isGuiChat(mc.getCurrentScreen());
//        color = flag ? new Color(0x01B97A).getRGB() : new Color(0x3F51B5).getRGB();


        if (enable.getValue()) {
//            if (flag)
//                RenderUtil.drawRect(PosX, PosY, PosX + width, PosY + 10, color);
            if (flag) {
                FontManager.Verdana16.drawString(getName(), PosX + 3, PosY + 4, -1);
                RenderUtil.drawGradientRect2(PosX, PosY + 15, width, 5, ColorUtils.applyOpacity(Color.BLACK, .2f).getRGB(), ColorUtils.applyOpacity(Color.BLACK, 0).getRGB());
            } else if (isSpecial) {
                FontManager.Verdana16.drawString(getName(), PosX + 3, PosY + 4, -1);
                RenderUtil.drawGradientRect2(PosX, PosY + 15, width, 5, ColorUtils.applyOpacity(Color.BLACK, .2f).getRGB(), ColorUtils.applyOpacity(Color.BLACK, 0).getRGB());
            }
            drawHUD(PosX, PosY + 18, partialTicks, flag);
        }
    }

    public boolean isDrag() {
        return drag;
    }
}
