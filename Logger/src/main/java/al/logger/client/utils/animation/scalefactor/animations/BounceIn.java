package al.logger.client.utils.animation.scalefactor.animations;

import al.logger.client.utils.animation.scalefactor.bases.AbstractAnimation;

public class BounceIn extends AbstractAnimation {

    private final AbstractAnimation bounceOut = new BounceOut();

    @Override
    public float update(float t, float b, float c, float d) {
        return c - bounceOut.update(d - t, 0, c, d) + b;
    }
}
