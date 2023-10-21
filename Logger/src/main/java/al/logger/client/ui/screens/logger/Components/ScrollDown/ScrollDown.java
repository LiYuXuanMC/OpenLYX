package al.logger.client.ui.screens.logger.Components.ScrollDown;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.screens.logger.Container.ListBox;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;

import java.awt.*;

public class ScrollDown extends ComponentBase {

    public ListBox parent;

    public ScrollDown(ListBox parent) {
        super("ScrollDown");
        this.parent = parent;
    }

    @Override
    protected void _initElements() {

    }

    @Override
    protected void _drawElement() {
        float textX = this.getPosition().getX() + (this.getPosition().getWidth() - FontLoadersWithChinese.hongMengSR15.getStringWidth("Down")) / 2;
        float textY = this.getPosition().getY() + (this.getPosition().getHeight() - FontLoadersWithChinese.hongMengSR15.getStringHeight("Down")) / 2;
        RenderUtil.drawRound(this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getWidth(), this.getPosition().getHeight(), (this.getPosition().getHeight() / 2) - 0.5f, new Color(0, 0, 0, 180));
        FontLoadersWithChinese.hongMengSR15.drawString("Down", textX, textY, new Color(255, 255, 255, 200).getRGB());
    }

    @Override
    protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (this.isMouseHover) {
            if (mouseButton == 0) {
                this.parent.isScrolling = true;
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        return false;
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

    }
}
