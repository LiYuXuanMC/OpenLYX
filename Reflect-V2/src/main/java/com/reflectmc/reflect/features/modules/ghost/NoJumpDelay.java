package com.reflectmc.reflect.features.modules.ghost;

import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.player.EventPreUpdate;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;

public class NoJumpDelay extends Module {
    public NoJumpDelay() {
        super("NoJumpDelay", Category.Ghost);
    }

    @EventTarget
    public void onPlayerPreUpdate(EventPreUpdate update) {
        mc.getThePlayer().setJumpTicks(0);
    }
}
