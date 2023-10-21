package al.nya.reflect.wrapper.wraps.wrapper;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.util.Timer",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.Timer",targetMap = Maps.Srg1_12_2)
public class Timer extends IWrapper {
    @WrapField(mcpName = "timerSpeed",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "tickLength",targetMap = Maps.Srg1_12_2)
    public static Field timerSpeed;
    @WrapField(mcpName = "renderPartialTicks",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "renderPartialTicks",targetMap = Maps.Srg1_12_2)
    public static Field renderPartialTicks;

    public Timer(Object obj) {
        super(obj);
    }

    public void setTimerSpeed(float timerSpeedV) {
        ReflectUtil.setField(timerSpeed,timerSpeedV,getWrapObject());
    }
    public float getTimerSpeed(){
        return (float) ReflectUtil.getField(timerSpeed,getWrapObject());
    }

    public float getRenderPartialTicks() {
        return (float) ReflectUtil.getField(renderPartialTicks,getWrapObject());
    }
}
