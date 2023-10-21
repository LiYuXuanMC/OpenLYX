package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;

public class AirJump extends Module {
    public AirJump() {
        super("AirJump",ModuleType.Movement);
    }
    @EventTarget
    public void onUpdate(EventUpdate update){
        mc.getThePlayer().setOnGround(true);
    }
}
