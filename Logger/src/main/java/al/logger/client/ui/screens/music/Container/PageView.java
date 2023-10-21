package al.logger.client.ui.screens.music.Container;

import al.logger.client.ui.bases.ComponentBase;
import lombok.Getter;
import lombok.Setter;

public class PageView extends ComponentBase {

    @Getter
    @Setter
    private String viewName;


    public PageView(String viewName) {
        super("PageView");
        this.setViewName(viewName);
    }

    @Override
    protected void _initElements() {
    }

    @Override
    protected void _drawElement() {
    }

    @Override
    protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
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
