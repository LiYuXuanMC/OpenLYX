package al.logger.client.ui.screens.logger.Components.Items.Switch;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.value.impls.OptionValue;

import java.awt.*;

public class SwitchButton extends ComponentBase {


    public OptionValue value;
    public Switch parent;
    public Animation positionAnimation = new Animation();

    public SwitchButton(OptionValue optionValue, Switch parent) {
        super("SwitchButton");
        this.value = optionValue;
        this.parent = parent;
    }

    @Override
    public void _initElements() {
        this.position.setWidth(26f);
        this.position.setHeight(13f);
    }

    @Override
    public void _drawElement() {
        positionAnimation.fstart(positionAnimation.getValue(), this.value.getValue() ? 14f : 3f, 0.1f, Type.EASE_OUT_QUAD);
        positionAnimation.update();

        RenderUtil.drawRound(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), this.position.getHeight() / 2, this.value.getValue() ? new Color(61, 119, 193) : new Color(51, 51, 51));
        RenderUtil.drawRound(this.position.getX() + (float) positionAnimation.getValue(), this.position.getY() + 2.5f, 8f, 8f, 4f, this.value.getValue() ? new Color(255, 255, 255) : new Color(91, 91, 91));
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseHover) {
            this.value.setValue(!this.value.getValue());
            return true;
        }
        return false;
    }

    @Override
    public boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        if (isMouseHover) {
            return true;
        }
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
