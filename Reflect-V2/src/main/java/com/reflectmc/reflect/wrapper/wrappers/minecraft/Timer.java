package com.reflectmc.reflect.wrapper.wrappers.minecraft;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.util.Timer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.Timer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Timer extends WrapperBase {
    @WrapField(deobfName = "timerSpeed",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "tickLength",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field timerSpeed;
    @WrapField(deobfName = "renderPartialTicks",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "renderPartialTicks",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field renderPartialTicks;

    public Timer(Object obj) {
        super(obj);
    }

    public void setTimerSpeed(float timerSpeedV) {
        setField(timerSpeed,timerSpeedV);
    }
    public float getTimerSpeed(){
        return (float) getField(timerSpeed);
    }
    public float getRenderPartialTicks() {
        return (float) getField(renderPartialTicks);
    }
}
