package al.logger.client.ui.screens.logger.Components.Items.ColorView;

import al.logger.client.Logger;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.screens.logger.Components.Items.ListItem;
import al.logger.client.utils.ScreenUtil;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.misc.ColorUtil;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.value.impls.ColorValue;
import al.logger.client.wrapper.lwjgl.Mouse;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class ColorEdit extends ComponentBase {

    private ColorValue value;
    private ListItem parent;
    private boolean isEditColor = false;
    private boolean isEditHue = false;
    @Getter
    @Setter
    private boolean isMiniView = false;
    private final Animation pickerYAnimation = new Animation();
    private final Animation pickerXAnimation = new Animation();
    float gradientX = 0;
    float gradientY = 0;
    float gradientWidth = 0;
    float gradientHeight = 0;

    public ColorEdit(ColorValue value, ListItem parent) {
        super("ColorEdit");
        this.value = value;
        this.parent = parent;
    }

    @Override
    protected void _initElements() {
        this.position.setHeight(94);
    }

    @Override
    protected void _drawElement() {
        //RenderUtil.drawRound(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), 0, new Color(255, 255, 255, 100));
        if (isMiniView) {
            RenderUtil.drawRound(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), 4, this.value.getValue());
        } else {
            FontLoadersWithChinese.hongMengBlod17.drawString(this.value.getName(), this.position.getX(), this.position.getY() + 4, 0xFFFFFFFF);
            float titleFontWidth = FontLoadersWithChinese.hongMengBlod17.getStringWidth(this.value.getName());
            if (titleFontWidth > this.position.getWidth()) {
                this.position.setWidth(titleFontWidth);
            }
            float[] hsb = {value.getHue(), value.getSaturation(), value.getBrightness()};
            Color hsbZeroOneOne = ColorUtil.applyOpacity(Color.getHSBColor(hsb[0], 1, 1), 1);
            Color hsbZeroZeroOne = ColorUtil.applyOpacity(Color.getHSBColor(hsb[0], 0, 1), 1);
            Color hsbZeroOneZero = ColorUtil.applyOpacity(Color.getHSBColor(hsb[0], 1, 0), 1);
            gradientX = this.position.getX();
            gradientY = this.position.getY() + 18;
            gradientWidth = 64;
            gradientHeight = 64;
            if (isEditColor && Mouse.isButtonDown(0)) {
                value.setBrightness(Math.min(1, Math.max(0, 1 - (mouseY - gradientY) / gradientHeight)));
                value.setSaturation(Math.min(1, Math.max(0, (mouseX - gradientX) / gradientWidth)));
            } else if (isEditHue && Mouse.isButtonDown(0)) {
                value.setHue(Math.min(1, Math.max(0, (mouseX - gradientX) / gradientWidth)));
            }
            RenderUtil.drawRoundedRectGradientRect(gradientX, gradientY, gradientWidth, gradientHeight, 4, hsbZeroZeroOne, hsbZeroOneOne, false);
            RenderUtil.drawRoundedRectGradientRect(gradientX, gradientY, gradientWidth, gradientHeight, 4, ColorUtil.applyOpacity(Color.getHSBColor(hsb[0], 1, 0), 0), hsbZeroOneZero, true);
            float pickerY = Math.max(Math.min(gradientHeight - 2, (gradientHeight * (1 - hsb[2]))), 0);
            float pickerX = Math.max(Math.min(gradientWidth - 2, (gradientWidth * hsb[1] - 1)), 0);
            pickerXAnimation.start(pickerXAnimation.getValue(), pickerX - 0.5, 0.03f, Type.EASE_IN_OUT_QUAD);
            pickerYAnimation.start(pickerYAnimation.getValue(), pickerY - 1, 0.03f, Type.EASE_IN_OUT_QUAD);
            pickerXAnimation.update();
            pickerYAnimation.update();
            RenderUtil.drawOutline(gradientX + (float) pickerXAnimation.getValue(), gradientY + (float) pickerYAnimation.getValue(), 4, 4, 1, 1, new Color(255, 255, 255), false);
            if (ScreenUtil.isHovered(gradientX, gradientY, gradientWidth, gradientHeight, mouseX, mouseY) || isEditColor) {
                String text = String.format("R: %s G: %s B: %s", value.getValue().getRed(), value.getValue().getGreen(), value.getValue().getBlue());
                float textWidth = FontLoadersWithChinese.hongMengBold14S.getStringWidth(text);
                float textHeight = FontLoadersWithChinese.hongMengBold14S.getStringHeight(text) + 6;
                float textX = (float) Math.min(Math.max(gradientX + pickerXAnimation.getValue() + 10, gradientX), gradientX + gradientWidth);
                float textY = (float) Math.min(Math.max(gradientY + pickerYAnimation.getValue() - 10, gradientY), gradientY + gradientHeight);
                float boxWidth = textWidth + 4;
                if (textX + boxWidth - gradientX > this.position.getWidth()) {
                    this.position.setWidth(textX + boxWidth - gradientX);
                }
                RenderUtil.drawRound(textX, textY, boxWidth, textHeight, 2, new Color(23, 23, 29, 255));
                RenderUtil.drawOutline(textX - 1, textY - 1, boxWidth + 2f, textHeight + 2f, 2, 1, new Color(58, 58, 64, 255), false);
                FontLoadersWithChinese.hongMengBold14S.drawString(text, textX + 2, textY + 3, new Color(255, 255, 255, 160).getRGB());
            }
            RenderUtil.setColor(new Color(255, 255, 255, 255).getRGB());
            RenderUtil.drawFullImage(Logger.getInstance().getResourceManager().getResourceLocation("clickgui/hue.png"), gradientX + 1, gradientY + gradientHeight + 5, gradientWidth - 2, 7, 2);
            float hueX = gradientX + (gradientWidth * hsb[0]) - 3.5f;
            if (hueX + 9 > gradientX + gradientWidth) hueX = gradientX + gradientWidth - 9;
            if (hueX < gradientX) hueX = gradientX;
            RenderUtil.drawOutline(hueX, gradientY + gradientHeight + 4, 9, 9, 4, 1, new Color(255, 255, 255), false);
        }
    }

    @Override
    protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (ScreenUtil.isHovered(gradientX, gradientY, gradientWidth, gradientHeight, mouseX, mouseY) && !isMiniView) {
            isEditColor = true;
            return true;
        } else if (ScreenUtil.isHovered(gradientX + 1, gradientY + gradientHeight + 5, gradientWidth - 2, 7, mouseX, mouseY) && !isMiniView) {
            isEditHue = true;
            return true;
        }
        return false;
    }

    @Override
    protected boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        if (isEditColor) {
            isEditColor = false;
        }
        if (isEditHue) {
            isEditHue = false;
        }
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