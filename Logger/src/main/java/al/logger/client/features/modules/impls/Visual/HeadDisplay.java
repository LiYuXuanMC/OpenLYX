package al.logger.client.features.modules.impls.Visual;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.templates.TextDisplayTemplate;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;

public class HeadDisplay extends Module {

    public HeadDisplay() {
        super("HeadDisplay", "Show Your HeadInfo", Category.Visual);
    }

    @Listener
    public void onRender2D(EventRender2D eventRender2D) {

        TextDisplayTemplate textDisplayTemplate = Logger.getInstance().getGuiManager().headDisplay;
        textDisplayTemplate.setParameter("Yaw:" + (int) mc.getThePlayer().getRotationYaw() + " Pitch:" + (int) mc.getThePlayer().getRotationPitch());
        textDisplayTemplate.drawComponent(0, 0, false);

    }
}
