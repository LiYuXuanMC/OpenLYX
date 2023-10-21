package com.reflectmc.reflect.features.modules.movement;

import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.player.EventLivingUpdate;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.OptionValue;
import com.reflectmc.reflect.utils.player.PlayerUtil;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;

public class Sprint extends Module {
    private OptionValue omni = new OptionValue("Omni", true);
    public Sprint() {
        super("Sprint", Category.Movement);
        addValues(omni);
    }
    @EventTarget
    public void onUpdate(EventLivingUpdate event) {
        EntityPlayerSP player = mc.getThePlayer();
        if ((this.omni.getValue() ? PlayerUtil.moving(player) : player.getMoveForward() > 0.0f)) {
            player.setSprinting(true);
        }
    }
}
