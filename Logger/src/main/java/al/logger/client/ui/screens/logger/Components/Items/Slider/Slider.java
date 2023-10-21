package al.logger.client.ui.screens.logger.Components.Items.Slider;

import al.logger.client.ui.screens.logger.Components.Items.ListItem;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.animation.logger.Smoother;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.value.impls.DoubleValue;

import java.awt.*;

public class Slider extends ComponentBase {

    public ListItem parent;
    public DoubleValue value;
    public Smoother positionAnimation = new Smoother(0, 100);
    public Smoother sliderAnimation = new Smoother(0, 100);

    public Slider(DoubleValue doubleValue, ListItem parent) {
        super("Slider");
        this.value = doubleValue;
        this.parent = parent;
    }

    @Override
    public void _initElements() {
        this.position.setHeight(13);
    }

    @Override
    public void _drawElement() {


        positionAnimation.update(isMouseHover || isMouseDown ? 0.0 : 1.0, System.currentTimeMillis());

        double textWidth = FontLoadersWithChinese.hongMengBlod17.getStringWidth(this.value.getName()) + 8f;
        FontLoadersWithChinese.hongMengBlod17.drawString(this.value.getName(), this.position.getX(), this.position.getY() + 3, new Color(255, 255, 255, (int) (255 * positionAnimation.get())).getRGB());
        double sliderWidth = (float) (textWidth * positionAnimation.get());
        double remain = this.position.getWidth() - sliderWidth;
        double value = this.value.getValue().floatValue();
        double min = this.value.getMin().floatValue();
        double max = this.value.getMax().floatValue();
        double range = (max - min);
        double percent = (value - min) / range;

        sliderAnimation.update(remain * percent, System.currentTimeMillis());

        float sliderHeight = 8;
        RenderUtil.drawRound((float) (this.position.getX() + sliderWidth), this.position.getY() + 2, (float) remain, sliderHeight, 4, new Color(60, 60, 60, 255));
        RenderUtil.drawRound((float) (this.position.getX() + sliderWidth), this.position.getY() + 2, (float) sliderAnimation.get(), sliderHeight, 4, new Color(61, 119, 193, 255));
        if (isMouseHover || isMouseDown && positionAnimation.get() != 1.0) {
            FontLoadersWithChinese.hongMengBlod17.drawString(this.value.getValue().toString(), this.position.getX() + sliderAnimation.get() + 2f, this.position.getY() + 3, new Color(255, 255, 255, (int) (255 * (1 - positionAnimation.get()))).getRGB());
        }

        if (isMouseDown) {
            double inc = this.value.inc;
            double valAbs = mouseX - this.position.getX() - sliderWidth;
            double valPer = Math.min(Math.max(0, valAbs / remain), 1);
            double valRel = valPer * range;
            double predict = min + valRel;
            double valNew = Math.round(predict * (1.0 / inc)) / (1.0 / inc);
            this.value.setValue(valNew);
        }

    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseHover) {
            this.isMouseDown = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        return false;
    }

    @Override
    public void update() {
        this.visibility = this.value.callBack();
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

    }
}
