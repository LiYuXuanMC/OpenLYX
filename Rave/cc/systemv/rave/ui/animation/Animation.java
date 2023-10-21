package cc.systemv.rave.ui.animation;


import net.minecraft.client.Minecraft;

public class Animation {
    private long duration = 0;
    private long startTime = 0;
    private double start = 0.0;
    private double value = 0.0;
    private double end = 0.0;
    private Type type = Type.LINEAR;
    private boolean started = false;

    public void start(double start, double end, float duration, Type type) {
        if (!started) {
            if (start != this.start || end != this.end || (duration * 1000) != this.duration || type != this.type) {
                this.duration = (long) (duration * 1000);
                this.start = start;
                this.startTime = System.currentTimeMillis();
                value = start;
                this.end = end;
                this.type = type;
                started = true;
            }
        }
    }

    public void update() {
        switch (type) {
            case LINEAR:
                value = linear(startTime, duration, start, end);
                break;
            case EASE_IN_QUAD:
                value = easeInQuad(startTime, duration, start, end);
                break;
            case EASE_OUT_QUAD:
                value = easeOutQuad(startTime, duration, start, end);
                break;
            case EASE_IN_OUT_QUAD:
                value = easeInOutQuad(startTime, duration, start, end);
                break;
            case EASE_IN_ELASTIC:
                value = easeInElastic((System.currentTimeMillis() - startTime), start, end - start, duration);
                break;
            case EASE_OUT_ELASTIC:
                value = easeOutElastic((System.currentTimeMillis() - startTime), start, end - start, duration);
                break;
            case EASE_IN_OUT_ELASTIC:
                value = easeInOutElastic((System.currentTimeMillis() - startTime), start, end - start, duration);
                break;
            case EASE_IN_BACK:
                value = easeInBack((System.currentTimeMillis() - startTime), start, end - start, duration);
                break;
            case EASE_OUT_BACK:
                value = easeOutBack((System.currentTimeMillis() - startTime), start, end - start, duration);
                break;
        }
        if ((System.currentTimeMillis() - startTime) > duration) {
            started = false;
            value = end;
        }
    }

    public void reset() {
        value = 0.0;
        start = 0.0;
        end = 0.0;
        startTime = System.currentTimeMillis();
        started = false;
    }

    public void fstart(double start, double end, float duration, Type type) {
        started = false;
        start(start, end, duration, type);
    }

    public static double base(double current, double target, double speed) {
        return current + (target - current) * speed / ((double) Minecraft.getDebugFPS() / 60);
    }

    public static double linear(long startTime, long duration, double start, double end) {
        return (end - start) * ((System.currentTimeMillis() - startTime) / (float) duration) + start;
    }

    public static double easeInQuad(long startTime, long duration, double start, double end) {
        return (end - start) * Math.pow(
                (System.currentTimeMillis() - startTime) / (double) duration,
                2.0
        ) + start;
    }

    public static double easeOutQuad(long startTime, long duration, double start, double end) {
        double x = (System.currentTimeMillis() - startTime) / (float) duration;
        double y = -x * x + 2 * x;
        return start + (end - start) * y;
    }

    public static double easeInOutQuad(long startTime, long duration, double start, double end) {
        double t = (System.currentTimeMillis() - startTime) / (float) duration;
        t *= 2;
        if (t < 1) return (end - start) / 2 * t * t + start;
        t--;
        return -(end - start) / 2 * (t * (t - 2) - 1) + start;
    }

    public static double easeInElastic(double t, double b, double c, double d) {
        double s = 1.70158;
        double p = 0.0;
        double a = c;
        if (t == 0.0) return b;
        t /= d;
        if (t == 1.0) return b + c;
        p = d * 0.3;
        if (a < Math.abs(c)) {
            a = c;
            s = p / 4.0;
        } else {
            s = p / (2 * Math.PI) * Math.asin(c / a);
        }
        t--;
        return -(a * Math.pow(2.0, (10 * t)) * Math.sin((t * d - s) * (2 * Math.PI) / p)) + b;
    }

    public static double easeOutElastic(double t, double b, double c, double d) {
        double s = 1.70158;
        double p = 0.0;
        double a = c;
        if (t == 0.0) return b;
        t /= d;
        if (t == 1.0) return b + c;
        p = d * 0.3;
        if (a < Math.abs(c)) {
            a = c;
            s = p / 4.0;
        } else {
            s = p / (2 * Math.PI) * Math.asin(c / a);
        }
        return a * Math.pow(2.0, (-10 * t)) * Math.sin((t * d - s) * (2 * Math.PI) / p) + c + b;
    }

    public static double easeInOutElastic(double t, double b, double c, double d) {
        double s = 1.70158;
        double p = 0.0;
        double a = c;
        if (t == 0.0) return b;
        t /= d / 2;
        if (t == 2.0) return b + c;
        p = d * (0.3 * 1.5);
        if (a < Math.abs(c)) {
            a = c;
            s = p / 4.0;
        } else {
            s = p / (2 * Math.PI) * Math.asin(c / a);
        }
        if (t < 1) {
            t--;
            return -0.5 * (a * Math.pow(2.0, (10 * t)) * Math.sin((t * d - s) * (2 * Math.PI) / p)) + b;
        }
        t--;
        return a * Math.pow(2.0, (-10 * t)) * Math.sin((t * d - s) * (2 * Math.PI) / p) * 0.5 + c + b;
    }

    public static double easeInBack(double t, double b, double c, double d) {
        double s = 1.70158;
        t /= d;
        return c * t * t * ((s + 1) * t - s) + b;
    }

    public static double easeOutBack(double t, double b, double c, double d) {
        double s = 1.70158;
        t = t / d - 1;
        return c * (t * t * ((s + 1) * t + s) + 1) + b;
    }

    public double getValue() {
        return value;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public float getDuration() {
        return duration;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getStart() {
        return start;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public double getEnd() {
        return end;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setValue(double value) {
        this.value = value;
    }
}