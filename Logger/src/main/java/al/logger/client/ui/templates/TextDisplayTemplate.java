package al.logger.client.ui.templates;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.fontRender.ChineseFontRenderer;
import al.logger.client.utils.render.RenderUtil;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class TextDisplayTemplate extends ComponentBase {

    @Getter
    private String parameter = "";

    @Getter
    @Setter
    private ChineseFontRenderer fontRenderer;

    public void setParameter(String value) {
        this.parameter = value;
        float textWidth = this.fontRenderer.getStringWidth(this.parameter);
        float textHeight = this.fontRenderer.getStringHeight(this.parameter);
        this.position.setWidth(textWidth + 16f);
        this.position.setHeight(textHeight + 16f);
    }

    public TextDisplayTemplate(String elementName) {
        super(elementName);
    }

    public TextDisplayTemplate(String elementName, ChineseFontRenderer fontRenderer) {
        super(elementName);
        this.fontRenderer = fontRenderer;
    }

    @Override
    public void _initElements() {
    }

    @Override
    public void _drawElement() {
        RenderUtil.drawRound(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), 0f, new Color(29, 29, 29, 80));
        if (fontRenderer != null) {
            this.fontRenderer.drawString(this.parameter, this.position.getX() + 8, this.position.getY() + 8, new Color(225, 225, 225).getRGB());
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
        if (isEdit) {
            this.setParameter(this.elementName);
        }
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

    }
}
