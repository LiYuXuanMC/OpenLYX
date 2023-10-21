package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.PlayerControllerMP;

public class SpeedMine extends Module {
    public SpeedMine() {
        super("SpeedMine",ModuleType.Player);
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
        PlayerControllerMP playerController = mc.getPlayerController();
        playerController.setBlockHitDelay(0);
        if (playerController.getCurBlockDamageMP() >= 0.75F) playerController.setCurBlockDamageMP(1F);
    }
}
