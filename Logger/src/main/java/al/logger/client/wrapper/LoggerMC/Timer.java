package al.logger.client.wrapper.LoggerMC;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.util.Timer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.Timer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Timer extends IWrapper {
    @WrapField(mcpName = "timerSpeed",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "tickLength",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field timerSpeed;
    @WrapField(mcpName = "renderPartialTicks",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "renderPartialTicks",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
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
