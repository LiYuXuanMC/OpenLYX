package cc.systemv.rave.feature.module.modules.movement;

import cc.systemv.rave.event.annotation.Listener;
import cc.systemv.rave.event.events.EventPreUpdate;
import cc.systemv.rave.feature.module.Category;
import cc.systemv.rave.feature.module.Module;
import cc.systemv.rave.utils.InstanceAccess;

public class NoJumpDelay extends Module {
    public NoJumpDelay() {
        super("NoJumpDelay", Category.Movement);
    }

    @Listener
    public void onPlayerPreUpdate(EventPreUpdate update) {
        InstanceAccess.mc.thePlayer.setJumpTicks(0);
    }
}
