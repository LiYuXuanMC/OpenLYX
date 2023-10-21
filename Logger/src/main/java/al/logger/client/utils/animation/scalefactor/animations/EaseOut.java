package al.logger.client.utils.animation.scalefactor.animations;

import al.logger.client.utils.animation.scalefactor.bases.AbstractAnimation;

public class EaseOut extends AbstractAnimation {
    @Override
    public float update(float t, float b, float c, float d) {
        t /= d;
        return -c * t * (t - 2) + b;
    }
}
