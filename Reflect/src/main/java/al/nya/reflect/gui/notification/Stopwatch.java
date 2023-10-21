package al.nya.reflect.gui.notification;

public final class Stopwatch {
    private long ms = this.getCurrentMS();

    private long getCurrentMS() {
        return System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return this.getCurrentMS() - this.ms;
    }

    public boolean elapsed(long milliseconds) {
        return this.getCurrentMS() - this.ms > milliseconds;
    }

    public void reset() {
        this.ms = this.getCurrentMS();
    }
}
