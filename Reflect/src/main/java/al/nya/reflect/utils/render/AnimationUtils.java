package al.nya.reflect.utils.render;

public final class AnimationUtils {
    public static float animate(float target, float current, float speed) {
        boolean larger;
        boolean bl = larger = target > current;
        if (speed < 0.0) {
            speed = 0.0f;
        } else if (speed > 1.0) {
            speed = 1.0f;
        }
        float dif = Math.max(target, current) - Math.min(target, current);
        float factor = dif * speed;
        if (factor < 0.1) {
            factor = 0.1f;
        }
        current = larger ? (current += factor) : (current -= factor);
        return current;
    }
    // target hud
    public static double delta;

    public static float getAnimationState(float animation, float finalState, float speed) {
        final float add = (float) (delta * (speed / 1000f));
        if (animation < finalState) {
            if (animation + add < finalState) {
                animation += add;
            } else {
                animation = finalState;
            }
        } else if (animation - add > finalState) {
            animation -= add;
        } else {
            animation = finalState;
        }
        return animation;
    }
}

