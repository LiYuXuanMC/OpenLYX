package al.logger.client.features.modules.impls.World;

import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.DoubleValue;

public class Timer extends Module {
    public DoubleValue speed = new DoubleValue("Speed",3,1,1.1,0.01){
        public void onValueChange(Double old,Double newV){
            if (isEnable()){
                onDisable();
                onEnable();
            }
        }
    };

    public Timer() {
        super("Timer", Category.World);
        addValues(speed);
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
