package al.logger.client.ui.panels;

import al.logger.client.Logger;
import al.logger.client.bridge.bridges.GuiScreenBridge;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;

import java.awt.*;
import java.io.IOException;

public class EditTemplateScreen extends GuiScreenBridge {


    @Override
    public void initGui() {
        super.initGui();
        for (ComponentBase value : Logger.getInstance().getGuiManager().GuiElementMap.values()) {
            value.setVisibility(false);
        }
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        for (ComponentBase value : Logger.getInstance().getGuiManager().GuiElementMap.values()) {
            value.setVisibility(true);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (String name : Logger.getInstance().getGuiManager().GuiElementMap.keySet()) {
            ComponentBase value = Logger.getInstance().getGuiManager().GuiElementMap.get(name);
            value.setEdit(true);
            value.update();
            RenderUtil.drawRound(value.getPosition().getX() - 4f, value.getPosition().getY() - 4f, value.getPosition().getWidth() + 8f, value.getPosition().getHeight() + 8f, 2f, new Color(255, 255, 255, 30));
            value.drawComponent(mouseX, mouseY, true);
            if (value.isMouseDown()) {
                RenderUtil.drawRound(value.getPosition().getX() - 4f, value.getPosition().getY() - 4f, value.getPosition().getWidth() + 8f, value.getPosition().getHeight() + 8f, 2f, new Color(255, 255, 255, 80));
                FontLoadersWithChinese.hongMengBlod17.drawString(value.getElementName(), value.getPosition().getX(), value.getPosition().getY() - 8f, new Color(255, 255, 255).getRGB());
            }
        }
    }

    @Override
    public void mouseClicked(int p_mouseClicked_1_, int p_mouseClicked_2_, int p_mouseClicked_3_) throws IOException {
        super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_2_, p_mouseClicked_3_);
        for (ComponentBase value : Logger.getInstance().getGuiManager().GuiElementMap.values()) {
            if (value.mouseClicked(p_mouseClicked_1_, p_mouseClicked_2_, p_mouseClicked_3_)) {
                return;
            }
        }
    }

    @Override
    public void mouseReleased(int p_mouseReleased_1_, int p_mouseReleased_2_, int p_mouseReleased_3_) {
        super.mouseReleased(p_mouseReleased_1_, p_mouseReleased_2_, p_mouseReleased_3_);
        for (ComponentBase value : Logger.getInstance().getGuiManager().GuiElementMap.values()) {
            value.mouseReleased(p_mouseReleased_1_, p_mouseReleased_2_, p_mouseReleased_3_);
        }
    }
}
