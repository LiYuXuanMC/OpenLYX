package al.logger.client.utils.animation.scalefactor.animations;

import al.logger.client.utils.animation.scalefactor.bases.AbstractAnimation;

public class BounceOut extends AbstractAnimation {
    @Override
    public float update(float t, float b, float c, float d) {
        if ((t /= d) < (1 / 2.75f)) {
            return c * (7.5625f * t * t) + b;
        } else if (t < (2 / 2.75f)) {
            float postFix = t -= (1.5f / 2.75f);
            return c * (7.5625f * (postFix) * t + 0.75f) + b;
        } else if (t < (2.5 / 2.75)) {
            float postFix = t -= (2.25f / 2.75f);
            return c * (7.5625f * (postFix) * t + 0.9375f) + b;
        } else {
            float postFix = t -= (2.625f / 2.75f);
            return c * (7.5625f * (postFix) * t + 0.984375f) + b;
        }
    }
}
