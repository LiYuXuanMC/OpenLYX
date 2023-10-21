package al.logger.client.utils.animation.scalefactor.animations;

import al.logger.client.utils.animation.scalefactor.bases.AbstractAnimation;

public class EaseIn extends AbstractAnimation {
    @Override
    public float update(float t, float b, float c, float d) {
        return c * (float)Math.pow(t / d, 2) + b;
    }
}
