package al.nya.reflect.utils.timer;

public final class MSTimer
{
    private long time;

    public MSTimer() {
        this.time = -1L;
    }

    public boolean hasTimePassed(final long MS) {
        return System.currentTimeMillis() >= this.time + MS;
    }

    public long hasTimeLeft(final long MS) {
        return MS + this.time - System.currentTimeMillis();
    }

    public void reset() {
        this.time = System.currentTimeMillis();
    }
}