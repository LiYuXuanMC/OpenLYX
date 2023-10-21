package al.logger.client.features.modules.impls.Visual;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.templates.TextDisplayTemplate;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.wrapper.LoggerMC.Minecraft;

public class FPSDisplay extends Module {
    public FPSDisplay() {
        super("FPSDisplay", "Show Your Fps", Category.Visual);
    }


    @Listener
    public void onRender2D(EventRender2D eventRender2D) {
        TextDisplayTemplate textDisplayTemplate = Logger.getInstance().getGuiManager().fpsDisplay;
        textDisplayTemplate.setParameter("FPS:" + Minecraft.getDebugFPS());
        textDisplayTemplate.drawComponent(0, 0, false);
    }
}
