package al.logger.client.ui.screens.logger;

import al.logger.client.Logger;
import al.logger.client.ui.bases.PanelBase;
import al.logger.client.ui.bases.Position;
import al.logger.client.ui.screens.logger.Container.TabControl;
import al.logger.client.utils.ScreenUtil;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.wrapper.LoggerMC.gui.GuiScreen;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;

public class LoggerScreen extends PanelBase {

    public boolean isMouseDown = false;
    public float moveX = 0f;
    public float moveY = 0f;
    public TabControl tabControl = new TabControl(this);
    public Animation scaleAnimation = new Animation();

    public boolean InExit = false;
    public boolean IsNotOutline = false;

    @Override
    public void initVariables() {
        this.position = new Position(0, 0, 625, 350);
        this.titleHeight = 40;
    }

    @Override
    public void initGui() {
        super.initGui();
        scaleAnimation.reset();
        tabControl.update();
        InExit = false;
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.mouseDWheel(mouseX, mouseY, Mouse.getDWheel());

        scaleAnimation.start(scaleAnimation.getValue(), InExit ? 0.0 : 0.2, 0.35f, InExit ? Type.EASE_IN_BACK : Type.EASE_OUT_BACK);
        scaleAnimation.update();
        if (InExit && scaleAnimation.getValue() == 0.0) {
            mc.displayGuiScreen(new GuiScreen(null));
            return;
        } else {
            IsNotOutline = 1.2 - scaleAnimation.getValue() != 1;
        }

        RenderUtil.doScale(this.position.getX() + this.position.getWidth() / 2, this.position.getY() + this.position.getHeight() / 2, (float) (1.2 - scaleAnimation.getValue()), () -> {

            if (this.isMouseDown) {
                if (moveX == 0 && moveY == 0) {
                    moveX = mouseX - this.position.getX() - 300;
                    moveY = mouseY - this.position.getY() - 300;
                } else {
                    this.position.setX((mouseX - moveX - 300));
                    this.position.setY((mouseY - moveY - 300));
                }
            } else if (moveX != 0f || moveY != 0f) {
                moveX = 0f;
                moveY = 0f;
            }

            RenderUtil.drawFullImage(Logger.getInstance().getResourceManager().getResourceLocation("clickgui/background.png"), this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), 255);
            RenderUtil.drawFullImage(Logger.getInstance().getResourceManager().getResourceLocation("logger.png"), this.position.getX() + 40, this.position.getY() + 35, 61, 15, 255);
            tabControl.drawComponent(this.position.getX() + 20.5f, this.position.getY() + 68f, mouseX, mouseY, false);
            if (Logger.getInstance().getLoggerUser().getPower() == 255){
                FontLoadersWithChinese.hongMengSR14.drawString("Current License:Dev", this.position.getX() + 24f, this.position.getY() + this.position.getHeight() - 48f, new Color(150, 150, 150, 255).getRGB());
            }else {
                FontLoadersWithChinese.hongMengSR14.drawString("Current License:User", this.position.getX() + 24f, this.position.getY() + this.position.getHeight() - 48f, new Color(150, 150, 150, 255).getRGB());
            }
            FontLoadersWithChinese.hongMengSR14.drawString("Version:" + Logger.getInstance().currentVer, this.position.getX() + 24f, this.position.getY() + this.position.getHeight() - 38f, new Color(150, 150, 150, 255).getRGB());
            FontLoadersWithChinese.hongMengSR14.drawString("Client:"+ EnvironmentDetector.getMinecraft().getVersionName()+" "+EnvironmentDetector.getModifier().getVersionName(), this.position.getX() + 24f, this.position.getY() + this.position.getHeight() - 28f, new Color(150, 150, 150, 255).getRGB());
            FontLoadersWithChinese.hongMengBlod22.drawString(tabControl.seletedCategory.name(), this.position.getX() + 148, this.position.getY() + 25.5f, new Color(255, 255, 255, 255).getRGB());
            FontLoadersWithChinese.hongMengSR14.drawString(tabControl.seletedCategory.getHazard().value, this.position.getX() + 148, this.position.getY() + 38f, new Color(150, 150, 150, 255).getRGB());

        });
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        tabControl.mouseClicked(mouseX, mouseY, mouseButton);
        if (ScreenUtil.isHovered(this.position.getX(), this.position.getY(), this.position.getWidth(), this.titleHeight, mouseX, mouseY)) {
            if (mouseButton == 0 && !this.isMouseDown) {
                this.isMouseDown = true;
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        tabControl.mouseReleased(mouseX, mouseY, state);
        if (this.isMouseDown) {
            this.isMouseDown = false;
        }
    }

    @Override
    public boolean keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) throws IOException {
        this.tabControl.keyTyped(p_keyTyped_1_, p_keyTyped_2_);
        if (p_keyTyped_2_ == 1) {
            this.InExit = true;
        }
        return false;
    }

    public void mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {
        tabControl._mouseDWheel(mouseX, mouseY, mouseDWheel);
    }

}
