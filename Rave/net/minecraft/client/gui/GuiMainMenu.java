package net.minecraft.client.gui;

import cc.systemv.rave.ui.ITransparencyButton;
import cc.systemv.rave.ui.animation.Type;
import cc.systemv.rave.utils.render.ColorUtil;
import cc.systemv.rave.utils.render.GradientUtil;
import cc.systemv.rave.utils.render.RenderUtil;
import cc.systemv.rave.utils.render.RoundedUtil;
import net.minecraft.client.resources.I18n;

import java.awt.*;
import java.io.IOException;

public class GuiMainMenu extends GuiScreen {
    private final static Color pink = new Color(255, 153, 204);
    private final static Color blue = new Color(102, 178, 255);
    public GuiMainMenu()
    {

    }

    public void updateScreen()
    {

    }


    public boolean doesGuiPauseGame()
    {
        return false;
    }


    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
    }


    public void initGui()
    {
        int yOffset = 24;
        this.buttonList.add(new ITransparencyButton(1, this.width / 2 - 100, this.height / 2 - 78 + yOffset, I18n.format("menu.singleplayer", new Object[0])));
        this.buttonList.add(new ITransparencyButton(2, this.width / 2 - 100, this.height / 2 - 52 + yOffset, I18n.format("menu.multiplayer", new Object[0])));
        this.buttonList.add(new ITransparencyButton(3, this.width / 2 - 100, this.height / 2 - 26 + yOffset, "Alt Manager"));
        this.buttonList.add(new ITransparencyButton(4, this.width / 2 - 100, (this.height / 2)  + yOffset,I18n.format("menu.options", new Object[0])));
        this.buttonList.add(new ITransparencyButton(5, this.width / 2 - 100, (this.height / 2) + 26 + yOffset,I18n.format("menu.quit", new Object[0])));

    }




    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 1)
        {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        }
        if (button.id == 2)
        {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }
        if (button.id == 3)
        {
            //this.mc.displayGuiScreen(new AltManager());
        }
        if (button.id == 4)
        {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }
        if (button.id == 5)
        {
            this.mc.shutdown();
        }
    }


    public void confirmClicked(boolean result, int id)
    {

    }


    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        mc.guiAnimation.fstart(mc.guiAnimation.getValue(),200,0.1f, Type.LINEAR);
        mc.guiAnimation.update();
        Color bottomLeftColor = ColorUtil.interpolateColorsBackAndForth(10,0,pink,blue,false);
        Color topLeftColor = ColorUtil.interpolateColorsBackAndForth(10,100,pink,blue,false);
        Color bottomRightColor = ColorUtil.interpolateColorsBackAndForth(10,200,pink,blue,true);
        Color topRightColor = ColorUtil.interpolateColorsBackAndForth(10,300,pink,blue,true);
        GradientUtil.drawGradient(0,0,width,height,255, bottomLeftColor, topLeftColor,bottomRightColor,topRightColor);

        float containerHeight = (float) mc.guiAnimation.getValue();

        RoundedUtil.drawGradientRound(this.width / 2 - 125,this.height / 2 - (containerHeight / 2),250,containerHeight,10, bottomLeftColor, topLeftColor,bottomRightColor,topRightColor);
        RenderUtil.doStencil(this.width / 2 - 125,this.height / 2 - (containerHeight / 2),250,containerHeight,10,() -> {
            super.drawScreen(mouseX, mouseY, partialTicks);
        });
        RenderUtil.upShadow(this.width / 2 - 125,this.height / 2 - (containerHeight / 2),250,containerHeight,10,1);
    }


    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void onGuiClosed()
    {
    }
}
