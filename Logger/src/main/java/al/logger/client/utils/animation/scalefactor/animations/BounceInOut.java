package al.logger.client.utils.animation.scalefactor.animations;

import al.logger.client.utils.animation.scalefactor.bases.AbstractAnimation;


public class BounceInOut extends AbstractAnimation {

    private final AbstractAnimation bounceOut = new BounceOut();
    private final AbstractAnimation bounceIn = new BounceIn();

    @Override
    public float update(float t, float b, float c, float d) {
        if (t < d / 2) return bounceIn.update(t * 2, 0, c, d) * 0.5f + b;
        else return bounceOut.update(t * 2 - d, 0, c, d) * 0.5f + c * 0.5f + b;
    }
}
