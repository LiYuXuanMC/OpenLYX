package al.nya.reflect.utils.math;

public class TimerUtil {

    private long ms = getCurrentMS();

    private long getCurrentMS() {
        return System.currentTimeMillis();
    }

    public final long getPassedTime() {
        return getCurrentMS() - ms;
    }

    public final boolean hasPassed(long milliseconds) {
        return getCurrentMS() - ms >= milliseconds;
    }

    public final void reset() {
        ms = getCurrentMS();
    }

}
