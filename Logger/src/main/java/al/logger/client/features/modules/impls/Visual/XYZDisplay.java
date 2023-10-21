package al.logger.client.features.modules.impls.Visual;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.templates.TextDisplayTemplate;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;

public class XYZDisplay extends Module {
    public XYZDisplay() {
        super("XYZDisplay", "Show Your XYZ", Category.Visual);
    }

    @Listener
    public void onRender2D(EventRender2D eventRender2D) {
        TextDisplayTemplate textDisplayTemplate = Logger.getInstance().getGuiManager().xyzDisplay;
        textDisplayTemplate.setParameter("X:" + (int) mc.getThePlayer().getPosX() + " Y:" + (int) mc.getThePlayer().getPosY() + " Z:" + (int) mc.getThePlayer().getPosZ());
        textDisplayTemplate.drawComponent(0, 0, false);
    }
}
