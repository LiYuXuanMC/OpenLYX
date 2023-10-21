package al.logger.client.utils.misc;


import al.logger.client.wrapper.LoggerMC.Minecraft;

import java.security.SecureRandom;
import java.util.Random;

public class MathHelper {
    public static final float deg2Rad = roundToFloat(0.017453292519943295D);
    public static final float SQRT_2 = sqrt(2.0F);
    private static final float[] SIN_TABLE = new float[65536];
    private static final int[] MULTIPLY_DE_BRUIJN_BIT_POSITION;
    private static final double FRAC_BIAS;
    private static final double[] ASINE_TAB;
    private static final double[] COS_TAB;
    public static final float PI2 = roundToFloat((Math.PI * 2D));

    private static final Random random = new Random();

    public static Random getRandom() {
        return random;
    }
    public static Double interpolate(double oldValue, double newValue, double interpolationValue){
        return (oldValue + (newValue - oldValue) * interpolationValue);
    }

    public static int interpolateInt(int oldValue, int newValue, double interpolationValue){
        return al.logger.client.utils.misc.MathHelper.interpolate(oldValue, newValue, (float) interpolationValue).intValue();
    }

    public static float interpolateFloat(float oldValue, float newValue, double interpolationValue) {
        return interpolate(oldValue, newValue, (float) interpolationValue).floatValue();
    }

    public static double easeInQuint(Double x) {
        return x * x * x * x * x;
    }

    public static float roundToFloat(double d) {
        return (float) ((double) Math.round(d * 1.0E8D) / 1.0E8D);
    }

    public static double clamp_double(double num, double min, double max) {
        return num < min ? min : (num > max ? max : num);
    }
    public static float clamp_float(float num, float min, float max)
    {
        return num < min ? min : (num > max ? max : num);
    }
    public static double[] yawPos(double value) {
        return yawPos(Minecraft.getMinecraft().getThePlayer().getRotationYaw() * MathHelper.deg2Rad, value);
    }

    public static double[] yawPos(float yaw, double value) {
        return new double[]{-MathHelper.sin(yaw) * value, MathHelper.cos(yaw) * value};
    }
    public static float getRandomFloat(float max, float min) {
        SecureRandom random = new SecureRandom();
        return random.nextFloat() * (max - min) + min;
    }
    public static double getIncremental(double val, double inc) {
        double one = 1.0 / inc;
        return (double)Math.round(val * one) / one;
    }

    public static double atan2(double p_181159_0_, double p_181159_2_) {
        double d0 = p_181159_2_ * p_181159_2_ + p_181159_0_ * p_181159_0_;

        if (Double.isNaN(d0)) {
            return Double.NaN;
        } else {
            boolean flag = p_181159_0_ < 0.0D;

            if (flag) {
                p_181159_0_ = -p_181159_0_;
            }

            boolean flag1 = p_181159_2_ < 0.0D;

            if (flag1) {
                p_181159_2_ = -p_181159_2_;
            }

            boolean flag2 = p_181159_0_ > p_181159_2_;

            if (flag2) {
                double d1 = p_181159_2_;
                p_181159_2_ = p_181159_0_;
                p_181159_0_ = d1;
            }

            double d9 = func_181161_i(d0);
            p_181159_2_ = p_181159_2_ * d9;
            p_181159_0_ = p_181159_0_ * d9;
            double d2 = FRAC_BIAS + p_181159_0_;
            int i = (int) Double.doubleToRawLongBits(d2);
            double d3 = ASINE_TAB[i];
            double d4 = COS_TAB[i];
            double d5 = d2 - FRAC_BIAS;
            double d6 = p_181159_0_ * d4 - p_181159_2_ * d5;
            double d7 = (6.0D + d6 * d6) * d6 * 0.16666666666666666D;
            double d8 = d3 + d7;

            if (flag2) {
                d8 = (Math.PI / 2D) - d8;
            }

            if (flag1) {
                d8 = Math.PI - d8;
            }

            if (flag) {
                d8 = -d8;
            }

            return d8;
        }
    }

    public static double func_181161_i(double p_181161_0_) {
        double d0 = 0.5D * p_181161_0_;
        long i = Double.doubleToRawLongBits(p_181161_0_);
        i = 6910469410427058090L - (i >> 1);
        p_181161_0_ = Double.longBitsToDouble(i);
        p_181161_0_ = p_181161_0_ * (1.5D - d0 * p_181161_0_ * p_181161_0_);
        return p_181161_0_;
    }

    public static float getRandomInRange(float min, float max) {
        SecureRandom random = new SecureRandom();
        return random.nextFloat() * (max - min) + min;
    }

    public static double getRandomInRange(double min, double max) {
        SecureRandom random = new SecureRandom();
        return random.nextDouble() * (max - min) + min;
    }


    public static double easeInOutQuad(double x, int step) {
        return (x < 0.5) ? 2 * x * x : 1 - Math.pow((-2 * x + 2), step) / 2;
    }

    public static int floor_double(double value) {
        int i = (int) value;
        return value < (double) i ? i - 1 : i;
    }

    /**
     * the angle is reduced to an angle between -180 and +180 by mod, and a 360 check
     */
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
    public static int clamp_int(int num, int min, int max)
    {
        return num < min ? min : (num > max ? max : num);
    }
    public static float sqrt_double(double value)
    {
        return (float)Math.sqrt(value);
    }
    public static float sqrt_float(float value)
    {
        return (float) Math.sqrt(value);
    }
    public static float sin(float value)
    {
        return SIN_TABLE[(int)(value * 10430.378F) & 65535];
    }

    public static float cos(float value)
    {
        return SIN_TABLE[(int)(value * 10430.378F + 16384.0F) & 65535];
    }

    public static float sqrt(float value)
    {
        return (float) Math.sqrt(value);
    }

    public static float sqrt(double value)
    {
        return (float)Math.sqrt(value);
    }

    public static int floor(float value)
    {
        int i = (int)value;
        return value < (float)i ? i - 1 : i;
    }

    static
    {
        for (int i = 0; i < 65536; ++i)
        {
            SIN_TABLE[i] = (float)Math.sin((double)i * Math.PI * 2.0D / 65536.0D);
        }

        MULTIPLY_DE_BRUIJN_BIT_POSITION = new int[] {0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9};
        FRAC_BIAS = Double.longBitsToDouble(4805340802404319232L);
        ASINE_TAB = new double[257];
        COS_TAB = new double[257];

        for (int j = 0; j < 257; ++j)
        {
            double d0 = (double)j / 256.0D;
            double d1 = Math.asin(d0);
            COS_TAB[j] = Math.cos(d1);
            ASINE_TAB[j] = d1;
        }
    }
    public static double getRandom(double min, double max) {
        double range = max - min;

        double scaled = random.nextDouble() * range;

        if (scaled > max) scaled = max;

        double shifted = scaled + min;

        if (shifted > max) shifted = max;

        return shifted;
    }
    public static double getRandomDoubleInRange(Random p_getRandomDoubleInRange_0_, double p_getRandomDoubleInRange_1_, double p_getRandomDoubleInRange_3_) {
        return p_getRandomDoubleInRange_1_ >= p_getRandomDoubleInRange_3_ ? p_getRandomDoubleInRange_1_ : p_getRandomDoubleInRange_0_.nextDouble() * (p_getRandomDoubleInRange_3_ - p_getRandomDoubleInRange_1_) + p_getRandomDoubleInRange_1_;
    }
    public static float abs(float p_abs_0_) {
        return p_abs_0_ >= 0.0F ? p_abs_0_ : -p_abs_0_;
    }
    public static float clampValue(final float value, final float floor, final float cap) {
        if (value < floor) {
            return floor;
        }
        return Math.min(value, cap);
    }

    public static int clampValue(final int value, final int floor, final int cap) {
        if (value < floor) {
            return floor;
        }
        return Math.min(value, cap);
    }

    public static double nextDouble(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public static double wrapDegrees(double degrees) {
        degrees = degrees % 360.0F;
        if (degrees >= 180.0F)
        {
            degrees -= 360.0F;
        }
        if (degrees < -180.0F)
        {
            degrees += 360.0F;
        }
        return degrees;
    }



}
