package al.logger.client.features.modules.impls.Movement;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Hazard;
import al.logger.client.features.modules.Module;

public class NoJumpDelay extends Module {
    public NoJumpDelay() {
        super("NoJumpDelay", Category.Movement);
        setHazard(Hazard.NONE);
    }

    @Listener
    public void onPlayerPreUpdate(EventPreUpdate update) {
        mc.getThePlayer().setJumpTicks(0);
    }
}
