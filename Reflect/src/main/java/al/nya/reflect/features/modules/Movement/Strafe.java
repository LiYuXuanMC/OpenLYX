package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.World.disablers.HypixelDisabler;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.wrapper.wraps.wrapper.GameSettings;

public class Strafe extends Module {
    public Strafe() {
        super("Strafe", ModuleType.Movement);
    }
    @EventTarget
    private void onPre(EventPreUpdate event) {
        MovementUtils.strafe();
    }
}
