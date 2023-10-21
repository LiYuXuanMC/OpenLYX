package al.logger.client.ui.screens.logger.Components.normal;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.fontRender.ChineseFontRenderer;
import al.logger.client.utils.misc.ColorUtil;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class RightMenuButton extends ComponentBase {

    private final Runnable mouseClickedEvent;
    private final Color normalColor;
    private final Color hoverColor;
    private final Color fontColor;
    private final ChineseFontRenderer fontRenderer;
    private final Animation colorAnimation = new Animation();
    @Getter
    @Setter
    private ResourceLocation iconLocation = null;
    @Getter
    @Setter
    private float iconWidth = 8f;
    @Getter
    @Setter
    private float iconHeight = 8f;

    public RightMenuButton(String displayText, ChineseFontRenderer fontRenderer, Runnable mouseClickedEvent) {
        this(displayText, fontRenderer, new Color(20, 20, 20), new Color(61, 119, 193), new Color(255, 255, 255,200), fontRenderer.getStringWidth(displayText) + 14f, fontRenderer.getStringHeight(displayText) + 12f, mouseClickedEvent);
    }

    public RightMenuButton(String displayText, ChineseFontRenderer fontRenderer, Color normalColor, Color hoverColor, Color fontColor, float width, float height, Runnable mouseClickedEvent) {
        super(displayText);
        this.position.setWidth(width);
        this.position.setHeight(height);
        this.mouseClickedEvent = mouseClickedEvent;
        this.normalColor = normalColor;
        this.hoverColor = hoverColor;
        this.fontColor = fontColor;
        this.fontRenderer = fontRenderer;
        this.widthBase = this.position.getWidth();
    }

    @Override
    public void _initElements() {
    }

    @Override
    public void _drawElement() {
        float textHeight = this.fontRenderer.getStringHeight(this.elementName);
        float textY = this.position.getY() + (this.position.getHeight() - textHeight) / 2;
        colorAnimation.start(colorAnimation.getValue(), isMouseHover ? 1.0 : 0.0, 0.1f, Type.EASE_IN_OUT_QUAD);
        colorAnimation.update();
        Color background = ColorUtil.interpolateColorC(normalColor, hoverColor, (float) colorAnimation.getValue());
        RenderUtil.drawRound(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), 0f, background);
        float remain = 0f;
        float poxX = this.position.getX() + 6f;
        if (this.iconLocation != null) {
            remain += 14f;
            float iconY = this.position.getY() + (this.position.getHeight() - iconHeight) / 2;
            RenderUtil.drawFullImage(this.iconLocation, poxX, iconY, iconWidth, iconHeight, 255);
        }
        this.fontRenderer.drawString(this.elementName, poxX + remain, textY, this.fontColor.getRGB());
        this.position.setWidth(this.widthBase + remain);
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseHover) {
            mouseClickedEvent.run();
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

    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

    }
}
