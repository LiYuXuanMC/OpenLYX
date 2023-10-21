package al.logger.client.utils.animation.scalefactor.animations;

import al.logger.client.utils.animation.scalefactor.bases.AbstractAnimation;

public class EaseInOut extends AbstractAnimation {
    @Override
    public float update(float t, float b, float c, float d) {
        t /= d / 2;
        if (t < 1) return c / 2 * (float)Math.pow(t, 2) + b;
        t--;
        return -c / 2 * (t * (t - 2) - 1) + b;
    }
}
