package cc.systemv.rave.utils.math.impls;

import cc.systemv.rave.utils.math.MathProvider;

public class JavaMath extends MathProvider {
    public float sin(float radians) {
        return (float)Math.sin(radians);
    }

    public float cos(float radians) {
        return (float)Math.cos(radians);
    }
}
