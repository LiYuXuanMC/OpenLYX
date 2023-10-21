package al.nya.reflect.features.modules.Ghost;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;

public class NoJumpDelay extends Module {
    public NoJumpDelay() {
        super("NoJumpDelay", "无跳跃间隔", ModuleType.Ghost);
    }

    @EventTarget
    public void onPlayerPreUpdate(EventPreUpdate update) {
        mc.getThePlayer().setJumpTicks(0);
    }
}
