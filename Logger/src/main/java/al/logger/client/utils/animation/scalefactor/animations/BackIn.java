package al.logger.client.utils.animation.scalefactor.animations;

import al.logger.client.utils.animation.scalefactor.bases.AbstractAnimation;

public class BackIn extends AbstractAnimation {
    @Override
    public float update(float t, float b, float c, float d) {
        float s = 1.70158f;
        t /= d;
        return c * t * t * ((s + 1) * t - s) + b;
    }
}
