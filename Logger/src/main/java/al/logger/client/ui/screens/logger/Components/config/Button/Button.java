package al.logger.client.ui.screens.logger.Components.config.Button;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.misc.ColorUtil;
import al.logger.client.utils.render.RenderUtil;

import java.awt.*;

public abstract class Button extends ComponentBase {

    private final Animation colorAnimation = new Animation();

    public Button(String title, float width, float height) {
        super(title);
        this.position.setWidth(width);
        this.position.setHeight(height);
    }

    @Override
    public void _initElements() {
    }

    @Override
    public void _drawElement() {
        float textWidth = FontLoadersWithChinese.hongMengBold14S.getStringWidth(this.elementName);
        float textHeight = FontLoadersWithChinese.hongMengBold14S.getStringHeight(this.elementName);
        float textX = this.position.getX() + (this.position.getWidth() - textWidth) / 2;
        float textY = this.position.getY() + (this.position.getHeight() - textHeight) / 2;
        colorAnimation.start(colorAnimation.getValue(), isMouseHover ? 1.0 : 0.0, 0.2f, Type.EASE_IN_OUT_QUAD);
        colorAnimation.update();
        Color backgroundColor = ColorUtil.interpolateColorC(new Color(61, 119, 193), new Color(70, 130, 255), (float) colorAnimation.getValue());
        RenderUtil.drawRound(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), 4f, backgroundColor);
        FontLoadersWithChinese.hongMengBold14S.drawString(this.elementName, textX, textY, new Color(255, 255, 255).getRGB());
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseHover) {
            this.__mouseClicked(mouseX, mouseY, mouseButton);
            return true;
        }
        return false;
    }

    public abstract void __mouseClicked(int mouseX, int mouseY, int mouseButton);

    @Override
    protected boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        if(isMouseHover){
            this.__mouseReleased(mouseX, mouseY, mouseStatus);
            return true;
        }
        return false;
    }

    public abstract void __mouseReleased(int mouseX, int mouseY, int mouseStatus);

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
