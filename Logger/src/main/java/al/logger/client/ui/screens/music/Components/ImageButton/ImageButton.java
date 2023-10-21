package al.logger.client.ui.screens.music.Components.ImageButton;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import lombok.Getter;
import lombok.Setter;

public class ImageButton extends ComponentBase {


    @Getter
    @Setter
    private ResourceLocation image;

    private Runnable onClick;

    public ImageButton(ResourceLocation image, float x, float y, float width, float height, Runnable onClick) {
        super("ImageButton", x, y, width, height);
        this.image = image;
        this.onClick = onClick;
    }

    @Override
    protected void _initElements() {

    }

    @Override
    protected void _drawElement() {
        RenderUtil.drawFullImage(image, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getWidth(), this.getPosition().getHeight());
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
