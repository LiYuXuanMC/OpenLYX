package com.reflectmc.reflect.features.modules.player;

import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.player.EventLivingUpdate;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.multiplayer.PlayerControllerMP;

public class SpeedMine extends Module {
    public SpeedMine() {
        super("SpeedMine", Category.Player);
    }
    @EventTarget
    public void onUpdate(EventLivingUpdate event) {
        PlayerControllerMP playerController = mc.getPlayerController();
        playerController.setBlockHitDelay(0);
        if (playerController.getCurBlockDamageMP() >= 0.75F) playerController.setCurBlockDamageMP(1F);
    }
}
