package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;
import al.nya.reflect.wrapper.wraps.wrapper.potion.PotionEffect;

public class FullBright extends Module {
    private ModeValue mode = new ModeValue("Mode",Mode.Gamma,Mode.values());
    public FullBright() {
        super("FullBright",ModuleType.Visual);
        addValue(mode);
    }
    @Override
    public void onEnable(){
        if (mode.getValue() == Mode.Gamma)
        mc.getGameSettings().setGammaSetting(1000F);
    }
    @EventTarget
    public void onUpdate(EventUpdate update){
        if (mode.getValue() == Mode.Potion){
            mc.getThePlayer().addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 100, 1));
        }
    }
    @Override
    public void onDisable(){
        mc.getGameSettings().setGammaSetting(0F);
    }
    public enum Mode {
        Gamma,
        Potion
    }
}
