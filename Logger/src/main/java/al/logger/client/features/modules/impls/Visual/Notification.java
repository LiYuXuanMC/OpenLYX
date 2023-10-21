package al.logger.client.features.modules.impls.Visual;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;

public class Notification extends Module {
    public Notification() {
        super("Notification", "not description", Category.Visual);
    }

    @Listener
    public void onRender2D(EventRender2D eventRender2D){
        Logger.getInstance().notificationManager.drawNotifications();
    }
}
