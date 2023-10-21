package al.logger.client.ui.screens.logger.Components.Items.ComboBox;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.ScreenUtil;
import al.logger.client.utils.animation.logger.Smoother;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.misc.ColorUtil;
import al.logger.client.utils.render.RenderUtil;

import java.awt.*;

public class ComboBoxItem extends ComponentBase {

    public ComboBox parent;
    public Enum value;
    public Smoother alphaAnimation = new Smoother(0, 200);

    public ComboBoxItem(Enum value, ComboBox parent) {
        super("ComboBoxItem");
        this.value = value;
        this.parent = parent;
    }

    @Override
    public void _initElements() {
        this.position.setHeight(15);
    }

    @Override
    public void _drawElement() {
        float textWidth = FontLoadersWithChinese.hongMengBlod17.getStringWidth(this.value.name());
        boolean isMouseHoverContainer = ScreenUtil.isHovered(this.getPosition().getX(), this.getPosition().getY(), textWidth + 6, this.getPosition().getHeight(), this.mouseX, this.mouseY);
        alphaAnimation.setTimeConstant(isMouseHoverContainer ? 50 : 200);
        alphaAnimation.update(isMouseHoverContainer ? 1 : 0, System.currentTimeMillis());
        if (!parent.openness) {
            alphaAnimation.setValue(0);
        }
        RenderUtil.drawRound(this.getPosition().getX(), this.getPosition().getY(), textWidth + 6, this.getPosition().getHeight(), 2, new Color(255, 255, 255, (int) (80 * alphaAnimation.get())));
        FontLoadersWithChinese.hongMengBlod17.drawString(this.value.name(), this.position.getX() + 3, this.position.getY() + 5, ColorUtil.interpolateColorC(new Color(255, 255, 255, 128), new Color(255, 255, 255, 255), (float) alphaAnimation.get()).getRGB());
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseHover) {
            if (mouseButton == 0) {
                parent.value.setValue(value);
                parent.openness = false;
                return true;
            }
        }
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
