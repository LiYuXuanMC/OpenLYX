package al.logger.client.ui.screens.music.Components.TextButton;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.fontRender.ChineseFontRenderer;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class TextButton extends ComponentBase {

    @Getter
    @Setter
    private Runnable onClick;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private ChineseFontRenderer renderer;

    @Getter
    @Setter
    private Color color;

    public TextButton(ChineseFontRenderer renderer, String content, float x, float y, Color color, Runnable onClick) {
        super("TextButton", x, y);
        this.onClick = onClick;
        this.renderer = renderer;
        this.content = content;
        this.color = color;
        float width = renderer.getStringWidth(content);
        float height = renderer.getHeight();
        this.getPosition().setWidth(width);
        this.getPosition().setHeight(height);
    }

    @Override
    protected void _initElements() {
    }

    @Override
    protected void _drawElement() {
        renderer.drawString(content, this.getPosition().getX(), this.getPosition().getY(), this.color.getRGB());
    }

    @Override
    protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseHover) {
            onClick.run();
            return true;
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
