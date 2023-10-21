package al.logger.client.utils.animation.logger;

public class Easing {

    public static double easeInQuad(double t, double b, double c, double d) {
        t /= d;
        return c * t * t + b;
    }

    public static double easeOutQuad(double t, double b, double c, double d) {
        t /= d;
        return -c * t * (t - 2) + b;
    }

    public static double easeInOutQuad(double t, double b, double c, double d) {
        t /= d / 2;
        if (t < 1) {
            return c / 2 * t * t + b;
        }
        t--;
        return -c / 2 * (t * (t - 2) - 1) + b;
    }

    public static double bounce(double t, double b, double c, double d) {
        t /= d;
        if (t < 1 / 2.75) {
            return c * (7.5625 * t * t) + b;
        } else if (t < 2 / 2.75) {
            t -= 1.5 / 2.75;
            return c * (7.5625 * t * t + 0.75) + b;
        } else if (t < 2.5 / 2.75) {
            t -= 2.25 / 2.75;
            return c * (7.5625 * t * t + 0.9375) + b;
        } else {
            t -= 2.625 / 2.75;
            return c * (7.5625 * t * t + 0.984375) + b;
        }
    }

    public static double elastic(double t, double b, double c, double d) {
        double p = d * 0.3;
        double s = p / 4;
        return c * Math.pow(2, -10 * t) * Math.sin((t * d - s) * (2 * Math.PI) / p) + c + b;
    }

    public static double easeInBack(double t, double b, double c, double d) {
        double s = 1.70158;
        double postFix = t /= d;
        return c * (postFix) * t * ((s + 1) * t - s) + b;
    }

    public static double easeOutBack(double t, double b, double c, double d) {
        double s = 1.70158;
        return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
    }

    public static double easeInOutBack(double t, double b, double c, double d) {
        double s = 1.70158;
        double postFix = t /= d / 2;
        if (postFix < 1) return c / 2 * (postFix * postFix * (((s *= (1.525)) + 1) * postFix - s)) + b;
        double postFix2 = t - 2;
        return c / 2 * ((postFix2) * postFix2 * (((s *= (1.525)) + 1) * postFix2 + s) + 2) + b;
    }

    public static double easeInCirc(double t, double b, double c, double d) {
        return -c * (Math.sqrt(1 - (t /= d) * t) - 1) + b;
    }

    public static double easeOutCirc(double t, double b, double c, double d) {
        return c * Math.sqrt(1 - (t = t / d - 1) * t) + b;
    }

    public static double easeInOutCirc(double t, double b, double c, double d) {
        double postFix = t /= d / 2;
        if (postFix < 1) return -c / 2 * (Math.sqrt(1 - postFix * postFix) - 1) + b;
        double postFix2 = t -= 2;
        return c / 2 * (Math.sqrt(1 - postFix2 * postFix2) + 1) + b;
    }

    public static double easeInSine(double t, double b, double c, double d) {
        return -c * Math.cos(t / d * (Math.PI / 2)) + c + b;
    }

    public static double easeOutSine(double t, double b, double c, double d) {
        return c * Math.sin(t / d * (Math.PI / 2)) + b;
    }

    public static double easeInOutSine(double t, double b, double c, double d) {
        return -c / 2 * (Math.cos(Math.PI * t / d) - 1) + b;
    }

    public static double easeInQuint(double t, double b, double c, double d) {
        double postFix = t / d;
        return c * postFix * postFix * postFix * postFix * postFix + b;
    }

    public static double easeOutQuint(double t, double b, double c, double d) {
        double postFix = (t / d) - 1;
        return c * (postFix * postFix * postFix * postFix * postFix + 1) + b;
    }

    public static double easeInOutQuint(double t, double b, double c, double d) {
        double postFix = t / (d / 2);
        if (postFix < 1) return c / 2 * postFix * postFix * postFix * postFix * postFix + b;
        double postFix2 = (t - 2) / d;
        return c / 2 * (postFix2 * postFix2 * postFix2 * postFix2 * postFix2 + 2) + b;
    }

    public static double ease(EasingType easingType, double t, double startValue, double change, double duration) {
        switch (easingType) {
            case LINEAR:
                return change * t / duration + startValue;
            case QUAD_IN:
                return easeInQuad(t, startValue, change, duration);
            case QUAD_OUT:
                return easeOutQuad(t, startValue, change, duration);
            case QUAD_IN_OUT:
                return easeInOutQuad(t, startValue, change, duration);
            case BOUNCE:
                return bounce(t, startValue, change, duration);
            case ELASTIC:
                return elastic(t, startValue, change, duration);
            case BACK_IN:
                return easeInBack(t, startValue, change, duration);
            case BACK_OUT:
                return easeOutBack(t, startValue, change, duration);
            case BACK_IN_OUT:
                return easeInOutBack(t, startValue, change, duration);
            case CIRC_IN:
                return easeInCirc(t, startValue, change, duration);
            case CIRC_OUT:
                return easeOutCirc(t, startValue, change, duration);
            case CIRC_IN_OUT:
                return easeInOutCirc(t, startValue, change, duration);
            case SINE_IN:
                return easeInSine(t, startValue, change, duration);
            case SINE_OUT:
                return easeOutSine(t, startValue, change, duration);
            case SINE_IN_OUT:
                return easeInOutSine(t, startValue, change, duration);
            case QUINT_IN:
                return easeInQuint(t, startValue, change, duration);
            case QUINT_OUT:
                return easeOutQuint(t, startValue, change, duration);
            case QUINT_IN_OUT:
                return easeInOutQuint(t, startValue, change, duration);
            // Add other easing functions here
            default:
                throw new IllegalArgumentException("Invalid easing type");
        }
    }

    public enum EasingType {
        LINEAR,
        QUAD_IN,
        QUAD_OUT,
        QUAD_IN_OUT,
        BOUNCE,
        ELASTIC,
        BACK_IN,
        BACK_OUT,
        BACK_IN_OUT,
        CIRC_IN,
        CIRC_OUT,
        CIRC_IN_OUT,
        SINE_IN,
        SINE_OUT,
        SINE_IN_OUT,
        QUINT_IN,
        QUINT_OUT,
        QUINT_IN_OUT
        // Add other easing types here
    }

}
