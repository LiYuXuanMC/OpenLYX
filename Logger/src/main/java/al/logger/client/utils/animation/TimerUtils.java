package al.logger.client.utils.animation;

import lombok.Getter;

public class TimerUtils {

    private long basetime = -1L;
    @Getter
    private long lastMS = 0L;
    @Getter
    private long prevMS = 0L;

    public boolean isDelay(long delay) {
        if(System.currentTimeMillis() - lastMS >= delay) {
            return true;
        }
        return false;
    }

    public long getCurrentMS(){
        return System.nanoTime() / 1000000L;
    }

    public void setLastMS(long lastMS) {
        this.lastMS = lastMS;
    }

    public void setLastMS() {
        this.lastMS = System.currentTimeMillis();
    }

    public int convertToMS(int d) {
        return 1000 /d;
    }

    public boolean hasReached(float f){
        return (float) (getCurrentMS() - this.lastMS) >= f;
    }
    public boolean hasReach(double f){
        return (float) (getCurrentMS() - this.lastMS) >= f;
    }

    public void reset(){
        this.lastMS = getCurrentMS();
        this.prevMS = getCurrentMS();
    }

    public boolean delay(float milliSec){
        return (float)(getTime() - this.prevMS) >= milliSec;
    }

    public long getTime(){
        return System.nanoTime() / 1000000L;
    }

    public boolean check(float milliseconds)
    {
        return getTime() >= milliseconds;
    }

    public boolean isDelayComplete(Double delay) {
        return System.currentTimeMillis() - this.lastMS > delay;
    }

    public boolean hasTimeElapsed(long time) {
        return System.currentTimeMillis() - lastMS > time;
    }

    public boolean hasTimePassed(long time){
        return System.currentTimeMillis() >= basetime + time;
    }
    public boolean hasTimeElapsed(long time, boolean reset) {
        if(time < 150) {
            if (((double)getTime()) >= ((double)time) / 1.63d) {
                if (reset) {
                    reset();
                }
                return true;
            }
        }
        else {
            if (getTime() >= time) {
                if (reset) {
                    reset();
                }
                return true;
            }
        }

        return false;
    }
    public boolean hasTimeElapsed(float time, boolean reset) {
        if(time < 150) {
            if (((double)getTime()) >= ((double)time) / 1.63f) {
                if (reset) {
                    reset();
                }
                return true;
            }
        }
        else {
            if (getTime() >= time) {
                if (reset) {
                    reset();
                }
                return true;
            }
        }

        return false;
    }
}


