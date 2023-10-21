package al.nya.reflect.features.modules.Ghost;

import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.DoubleValue;

public class Reach extends Module {
    public static DoubleValue buildRange = new DoubleValue("BuildRange",7.0,3.0,5,"0.0");
    public static DoubleValue attackRange = new DoubleValue("AttackRange",7.0,3.0,3.5,"0.0");
    public Reach() {
        super("Reach",ModuleType.Ghost);
        addValues(buildRange,attackRange);
    }

    @Override
    public void onEnable(){
    }

    public static float getRange(){
        return ModuleManager.getModule(Reach.class).isEnable() ? getAttackRange() : 4.5f;
    }
    public static float getAttackRange(){
        return ModuleManager.getModule(Reach.class).isEnable() ? attackRange.getValue().floatValue() : 3.0f;
    }
    public static float getBlockRange(){
        return ModuleManager.getModule(Reach.class).isEnable() ? buildRange.getValue().floatValue() : 4.5f;
    }
}
