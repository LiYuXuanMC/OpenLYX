package al.nya.reflect.features.modules.World;

import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.DoubleValue;

public class Timer extends Module {
    public DoubleValue speed = new DoubleValue("Speed",3,1,1.1,"0.00"){
        public void onValueChange(){
            if (isEnable()){
                onDisable();
                onEnable();
            }
        }
    };
    public Timer() {
        super("Timer",ModuleType.World);
        addValue(speed);
    }

    @Override
    public void onDisable() {
        mc.getTimer().setTimerSpeed(1F);
    }

    @Override
    public void onEnable() {
        mc.getTimer().setTimerSpeed(speed.getValue().floatValue());
    }
}
