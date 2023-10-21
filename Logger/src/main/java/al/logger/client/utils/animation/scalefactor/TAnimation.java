package al.logger.client.utils.animation.scalefactor;

import al.logger.client.utils.animation.scalefactor.animations.Linear;
import al.logger.client.utils.animation.scalefactor.bases.AbstractAnimation;

public class TAnimation {

    private float begin;
    private float end;
    private float duration;
    private AbstractAnimation ease;
    private float time;
    private float value;
    private long lastUpdateTime;

    public TAnimation(float begin, float end, float duration, Class<?> ease) {
        this(begin, end, duration, ease, 0, 0);
    }

    public TAnimation(float begin, float end, float duration, Class<?> ease, float time, float value) {
        this.begin = begin;
        this.end = end;
        this.duration = duration;
        try {
            this.ease = (AbstractAnimation) ease.newInstance();
        } catch (Exception e) {
            this.ease = new Linear();
        }
        this.time = time;
        this.value = value;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public void update(boolean forward ) {
        long now = System.currentTimeMillis();
        float deltaTime = (now - lastUpdateTime) / 1000f;
        lastUpdateTime = now;

        if(forward){
            time += deltaTime;
            if (time > duration) {
                time = duration;
            }
        }else{
            time -= deltaTime;
            if (time < 0) {
                time = 0;
            }
        }

        value = ease.update(time, begin, end - begin, duration);
    }

    public void setTarget(float target) {
        begin = value;
        end = target;
        time = 0;
    }

    public float getValue() {
        return value;
    }

    public void reset() {
        time = 0;
    }

    public void reset(float value) {
        time = 0;
        this.value = value;
    }

    public void resetInit(float value) {
        this.value = value;
        this.begin = value;
        this.end = value;
        time = 0;
    }

    public void setBegin(float begin) {
        this.begin = begin;
    }

    public float getBegin() {
        return begin;
    }

    public float getEnd() {
        return end;
    }

    public void setEnd(float end) {
        this.end = end;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isFinished() {
        return time >= duration;
    }
}
