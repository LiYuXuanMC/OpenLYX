package al.logger.client.utils.animation.scalefactor.animations;

import al.logger.client.utils.animation.scalefactor.bases.AbstractAnimation;

public class BackInOut extends AbstractAnimation {
    @Override
    public float update(float t, float b, float c, float d) {
        float s = 1.70158f;
        t /= d / 2;
        if (t < 1) {
            s *= 1.525f;
            return c / 2 * (t * t * ((s + 1) * t - s)) + b;
        }
        t -= 2;
        s *= 1.525f;
        return c / 2 * (t * t * ((s + 1) * t + s) + 2) + b;
    }
}
