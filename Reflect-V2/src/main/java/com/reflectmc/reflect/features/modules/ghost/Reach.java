package com.reflectmc.reflect.features.modules.ghost;

import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.DoubleValue;
import com.reflectmc.reflect.obfuscate.ExportObfuscate;

public class Reach extends Module {
    private DoubleValue buildRange = new DoubleValue("BuildRange",7.0,3.0,5,"0.0");
    private DoubleValue attackRange = new DoubleValue("AttackRange",7.0,3.0,3.5,"0.0");
    public Reach() {
        super("Reach", Category.Ghost);
        addValues(buildRange,attackRange);
    }

    @Override
    public void onEnable(){
    }
    @ExportObfuscate(name = "getAttackRange")
    public float getAttackRange(){
        return isEnable() ? attackRange.getValue().floatValue() : 3.0f;
    }
    @ExportObfuscate(name = "getBlockRange")
    public float getBlockRange(){
        return isEnable() ? buildRange.getValue().floatValue() : 4.5f;
    }
    @ExportObfuscate(name = "getMaxRange")
    public float getMaxRange(){
        return Math.max(getAttackRange(),getBlockRange());
    }
}
