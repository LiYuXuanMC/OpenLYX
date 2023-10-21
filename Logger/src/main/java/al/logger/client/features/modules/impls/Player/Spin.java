package al.logger.client.features.modules.impls.Player;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Hazard;
import al.logger.client.features.modules.Module;

public class Spin extends Module {
    private float yaw = 0;
    public Spin() {
        super("Spin",Category.Player);
        setHazard(Hazard.HACK);
    }
    @Listener
    public void eventPreUpdate(EventPreUpdate preUpdate) {
        yaw+=10;
        preUpdate.setRotation(yaw,90f);
    }
}
