package al.nya.reflect.features.modules.Ghost;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.World.Scaffold;
import al.nya.reflect.utils.entity.PlayerUtil;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class Sprint extends Module {
    private OptionValue omni = new OptionValue("Omni", true);
    public Sprint() {
        super("Sprint", ModuleType.Ghost);
        addValues(omni);
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
        EntityPlayerSP player = mc.getThePlayer();
        if ((this.omni.getValue() ? PlayerUtil.moving(player) : player.getMoveForward() > 0.0f)) {
            player.setSprinting(true);
        }
        if ((Scaffold.sprint.getValue() ? false : ModuleManager.getModule(Scaffold.class).isEnable())) {
            player.setSprinting(false);
        }
    }
}
