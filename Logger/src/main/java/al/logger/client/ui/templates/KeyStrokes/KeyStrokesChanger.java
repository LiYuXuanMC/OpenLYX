package al.logger.client.ui.templates.KeyStrokes;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.render.RenderUtil;

import java.awt.*;

public class KeyStrokesChanger extends ComponentBase {

    private ComponentBase parent;
    private Animation sizeAnimation = new Animation();
    public Animation alphaAnimation = new Animation();

    public KeyStrokesChanger(ComponentBase parent) {
        super("KeyStrokesChanger");
        this.parent = parent;
        this.alphaAnimation.setEnd(1.0);
        this.alphaAnimation.setValue(1.0);
    }

    @Override
    public void _initElements() {

    }

    @Override
    public void _drawElement() {
        sizeAnimation.fstart(sizeAnimation.getValue(), this.parent.getPosition().getWidth() + 35, 0.15f, Type.LINEAR);
        sizeAnimation.update();
        this.position.setWidth((float) sizeAnimation.getValue());
        this.position.setHeight((float) sizeAnimation.getValue());
        this.position.setX(this.parent.getPosition().getX() + (this.parent.getPosition().getWidth() - this.position.getWidth()) / 2);
        this.position.setY(this.parent.getPosition().getY() + (this.parent.getPosition().getHeight() - this.position.getHeight()) / 2);
        RenderUtil.drawRound(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), this.position.getWidth() / 2, new Color(255, 255, 255, (int) (40 * alphaAnimation.getValue())));
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        return false;
    }

    @Override
    public boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
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
