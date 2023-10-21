package al.logger.client.ui.screens.toluene;

import al.logger.client.ui.bases.PanelBase;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.wrapper.LoggerMC.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;

public class TolueneScreen extends PanelBase {

    private float gridSize = 25f;

    @Override
    public void initGui() {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.mouseDWheel(mouseX, mouseY, Mouse.getDWheel());
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        RenderUtil.drawRect(0, 0, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), new Color(29, 29, 29, 255).getRGB());
        for (int i = 0; i < scaledResolution.getScaledWidth() / gridSize; i++) {
            RenderUtil.drawLine(i * gridSize, 0, i * gridSize, scaledResolution.getScaledHeight(), 0.5f, new Color(80, 80, 80, 255));
        }
        for (int i = 0; i < scaledResolution.getScaledHeight() / gridSize; i++) {
            RenderUtil.drawLine(0, i * gridSize, scaledResolution.getScaledWidth(), i * gridSize, 0.5f, new Color(80, 80, 80, 255));
        }

    }

    @Override
    public void onGuiClosed() {

    }

    @Override
    public void mouseClicked(int p_mouseClicked_1_, int p_mouseClicked_2_, int p_mouseClicked_3_) throws IOException {

    }

    @Override
    public void mouseReleased(int p_mouseReleased_1_, int p_mouseReleased_2_, int p_mouseReleased_3_) {

    }

    @Override
    public boolean keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) throws IOException {
        return super.keyTyped(p_keyTyped_1_, p_keyTyped_2_);
    }

    public void mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

}
