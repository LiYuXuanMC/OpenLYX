package al.nya.reflect.utils.math;

import al.nya.reflect.utils.MathHelper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MathUtil {

    private static final Random random = new Random();

    public static Random getRandom() {
        return random;
    }

    public static float[] calcAngle(Vec3 from, Vec3 to) {
        final double difX = to.getXCoord() - from.getXCoord();
        final double difY = (to.getYCoord() - from.getYCoord()) * -1.0F;
        final double difZ = to.getZCoord() - from.getZCoord();

        final double dist = MathHelper.sqrt(difX * difX + difZ * difZ);

        return new float[]{(float) MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0f), (float) MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difY, dist)))};
    }

    public static float roundToFloat(double d) {
        return (float) ((double) Math.round(d * 1.0E8D) / 1.0E8D);
    }

    public static Double interpolate(double oldValue, double newValue, double interpolationValue) {
        return (oldValue + (newValue - oldValue) * interpolationValue);
    }

    public static double map(double value, double a, double b, double c, double d) {
        // first map value from (a..b) to (0..1)
        value = (value - a) / (b - a);
        // then map it from (0..1) to (c..d) and return it
        return c + value * (d - c);
    }

    public static int getRandom(final int min, final int max) {
        if (max < min) return min;
        return min + random.nextInt((max - min) + 1);
    }

    public static double getRandomByThreadLocalRandom(double min, double max) {
        if (min == max) {
            return min;
        } else if (min > max) {
            final double d = min;
            min = max;
            max = d;
        }
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static double getRandom(double min, double max) {
        double range = max - min;

        double scaled = random.nextDouble() * range;

        if (scaled > max) scaled = max;

        double shifted = scaled + min;

        if (shifted > max) shifted = max;

        return shifted;
    }

    public static float processFPS(float fps, float defF, float defV) {
        return defV / (fps / defF);
    }

    public static int randomInRange(int min, int max) {
        if (min > max) {
            System.err.println("The minimal value cannot be higher than the max value");
            return min;
        }
        max -= min;
        return (int) Math.round(Math.random() * (max)) + min;
    }

    public static double randomInRange(double min, double max) {
        if (min > max) {
            System.err.println("The minimal value cannot be higher than the max value");
            return min;
        }
        max -= min;
        return (Math.random() * (max)) + min;
    }

    public static float calculateGaussianValue(float x, float sigma) {
        double PI = 3.141592653;
        double output = 1.0 / Math.sqrt(2.0 * PI * (sigma * sigma));
        return (float) (output * Math.exp(-(x * x) / (2.0 * (sigma * sigma))));
    }
}
