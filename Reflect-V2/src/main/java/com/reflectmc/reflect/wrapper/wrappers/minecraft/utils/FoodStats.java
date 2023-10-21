package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.util.FoodStats",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.FoodStats",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class FoodStats extends WrapperBase {
    @WrapField(deobfName = "foodLevel",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "foodLevel",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field foodLevel;
    public FoodStats(Object obj) {
        super(obj);
    }
    public int getFoodLevel(){
        return (int) getField(foodLevel);
    }
}
