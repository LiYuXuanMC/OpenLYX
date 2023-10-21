package al.logger.client.ui.templates;

import al.logger.client.features.modules.impls.Visual.Hud;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.wrapper.LoggerMC.render.GlStateManager;

import java.awt.*;

public class InventoryHUD extends ComponentBase {

    public InventoryHUD(){super("InventoryHUD");}
    @Override
    protected void _initElements() {

    }

    @Override
    protected void _drawElement() {
        if (!Hud.invhud.getValue()){
            return;
        }
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
