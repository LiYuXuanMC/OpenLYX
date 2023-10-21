package al.logger.client.features.modules.impls.Visual;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.templates.RadarTemplate;
import al.logger.client.value.impls.DoubleValue;

public class Radar extends Module {

    public DoubleValue radarSize = new DoubleValue("RadarSize", 100, 50, 80, 1);

    public Radar() {
        super("Rader", "Show entity position", Category.Visual);
        this.addValues(radarSize);
    }

    @Listener
    public void onRender2D(EventRender2D eventRender2D) {
        RadarTemplate radarTemplate = Logger.getInstance().getGuiManager().radarTemplate;
        radarTemplate.setRadarSize(radarSize.getValue().intValue());
        radarTemplate.drawComponent(0, 0, false);
    }
}
