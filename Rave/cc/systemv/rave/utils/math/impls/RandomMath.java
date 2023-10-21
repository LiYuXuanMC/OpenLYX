package cc.systemv.rave.utils.math.impls;

import cc.systemv.rave.utils.math.MathProvider;

public class RandomMath extends MathProvider {
    @Override
    public float sin(float rad) {
        return (float)Math.random() * rad;
    }

    @Override
    public float cos(float rad) {
        return (float)Math.random() * rad;
    }
}
