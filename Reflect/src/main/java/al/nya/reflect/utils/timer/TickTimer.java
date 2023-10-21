package al.nya.reflect.utils.timer;

public class TickTimer {
    private int tick = 0;

    public void update() {
        tick++;
    }

    public void reset() {
        tick = 0;
    }

    public boolean hasTimePassed(int ticks) {
        return tick >= ticks;
    }
}
