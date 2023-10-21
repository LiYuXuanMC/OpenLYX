package com.reflectmc.reflect.features.modules.world;

import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.DoubleValue;

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
        super("Timer", Category.World);
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
