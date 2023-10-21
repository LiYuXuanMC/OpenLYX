package al.logger.client.wrapper.LoggerMC.utils;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import lombok.SneakyThrows;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.MathHelper",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.math.MathHelper",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class MathHelper extends IWrapper {
    @WrapMethod(mcpName = "sin",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "sin",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static MethodHandle sin;
    @WrapMethod(mcpName = "cos",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "cos",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static MethodHandle cos;
    public MathHelper(Object obj) {
        super(obj);
    }
    @SneakyThrows
    public static float sin(float f){
        return (float) sin.invoke(f);
    }
    @SneakyThrows
    public static float cos(float f){
        return (float) cos.invoke(f);
    }
    public static float wrapAngleTo180_float(float value)
    {
        value = value % 360.0F;

        if (value >= 180.0F)
        {
            value -= 360.0F;
        }

        if (value < -180.0F)
        {
            value += 360.0F;
        }

        return value;
    }

    public static float sqrt_float(float value)
    {
        return (float) Math.sqrt(value);
    }
    public static float sqrt_double(double p_sqrt_double_0_) {
        return (float)Math.sqrt(p_sqrt_double_0_);
    }
    public static float clamp_float(float p_clamp_float_0_, float p_clamp_float_1_, float p_clamp_float_2_) {
        if (p_clamp_float_0_ < p_clamp_float_1_) {
            return p_clamp_float_1_;
        } else {
            return p_clamp_float_0_ > p_clamp_float_2_ ? p_clamp_float_2_ : p_clamp_float_0_;
        }
    }
}
