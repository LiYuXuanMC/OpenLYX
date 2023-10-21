package cc.systemv.rave.ui;

import cc.systemv.rave.ui.animation.Animation;
import cc.systemv.rave.ui.animation.Type;
import cc.systemv.rave.ui.font.FontManager;
import cc.systemv.rave.utils.render.RoundedUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.awt.*;

public class ITransparencyButton extends GuiButton {
    private Animation opacityAnimation = new Animation();
    public ITransparencyButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
    }
    public ITransparencyButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText){
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

        opacityAnimation.fstart(opacityAnimation.getValue(),hovered ? 0.2 : 0,0.2f, Type.LINEAR);
        opacityAnimation.update();
        Color background = new Color(0,0,0, (int)(opacityAnimation.getValue()*255));
        if (this.visible){
            RoundedUtil.drawRoundOutline(this.xPosition,this.yPosition,this.width,this.height,5,0.5f, background,Color.white);
            FontManager.HarmonyOSbo20.drawString(displayString, xPosition + (float) (width/2 - (FontManager.HarmonyOSbo20.getStringWidth(displayString) / 2)), yPosition + (float) (height/2 - (FontManager.HarmonyOSbo20.getStringHeight(displayString) / 2)),Color.white.getRGB());
        }
    }
}
