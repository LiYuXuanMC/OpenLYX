package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class NoWeb extends Module {
    public ModeValue mode = new ModeValue("Mode", Mode.Vanilla, Mode.values());
    public NoWeb() {
        super("NoWeb", ModuleType.Player);
        addValue(mode);
    }

    @EventTarget
    public void onUpdate(EventUpdate update){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (!thePlayer.getIsInWeb()) return;
        if (mode.getValue() == Mode.Vanilla) {
            thePlayer.setIsInWeb(false);
        } else if (mode.getValue() == Mode.AAC5) {
            thePlayer.setJumpMovementFactor(0.42f);
            if (thePlayer.isOnGround()) {
                thePlayer.jump();
            }
        }
    }

    public enum Mode{
        Vanilla,
        AAC5
    }
}
