package al.logger.client.ui.screens.logger.Components.Tabs;

import al.logger.client.Logger;
import al.logger.client.features.modules.Category;
import al.logger.client.ui.screens.logger.Container.TabControl;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;

import java.awt.*;

public class Tab extends ComponentBase {

    public Category category;
    public TabControl parent;
    public Animation alphaAnimation = new Animation();
    public boolean isMe = false;

    public Tab(Category category, TabControl parent) {
        super("Tab");
        this.category = category;
        this.parent = parent;
    }

    @Override
    public void _initElements() {
        this.position.setWidth(103.5f);
        this.position.setHeight(20f);
    }

    @Override
    public void _drawElement() {
        isMe = !this.isMouseDown && parent.seletedCategory == this.category;

        //Animations
        alphaAnimation.start(alphaAnimation.getValue(), isMe ? 1 : 0, 0.35f, Type.LINEAR);
        alphaAnimation.update();

        float value = (float) alphaAnimation.getValue();
        int alpha = (int) (255 * value);

        if (isMe || isMouseDown) {
            RenderUtil.drawRound(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), 4, new Color(60, 60, 60, alpha));
        }
        RenderUtil.drawFullImage(Logger.getInstance().resourceManager.getResourceLocation("clickgui/" + this.category.getIcon() + ".png"),
                this.position.getX() + 6, this.position.getY() + 6, 9, 9, 255);
        String text = this.category.name();
        float textY = this.position.getY() + (this.position.getHeight() / 2f) - (FontLoadersWithChinese.intersemibold15.getStringHeight(text) / 2f);
        FontLoadersWithChinese.intersemibold15.drawString(text, this.position.getX() + 22, textY,
                isMe ? new Color(255, 255, 255, 255).getRGB() :
                        new Color(153, 153, 153, 255).getRGB());
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseHover && mouseButton == 0) {
            this.isMouseDown = true;
            this.parent.seletedCategory = this.category;
            this.parent.update();
            return true;
        }
        return false;
    }

    @Override
    public boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        this.isMouseDown = false;
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {


    }
}
