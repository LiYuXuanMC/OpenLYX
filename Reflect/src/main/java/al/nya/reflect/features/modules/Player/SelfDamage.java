package al.nya.reflect.features.modules.Player;

import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.entity.PlayerUtil;
import al.nya.reflect.value.ModeValue;

public class SelfDamage extends Module {
    private ModeValue mode = new ModeValue("Mode",Mode.Fall,Mode.values());
    public SelfDamage() {
        super("SelfDamage","自伤",ModuleType.Player);
        addValues(mode);
    }
    @Override
    public void onEnable() {
        if (mode.getValue() == Mode.Fall){
            PlayerUtil.selfDamage();
        }
    }
    private enum Mode {
        Fall
    }
}
