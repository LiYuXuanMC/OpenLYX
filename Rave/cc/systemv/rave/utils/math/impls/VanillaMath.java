package cc.systemv.rave.utils.math.impls;

import cc.systemv.rave.utils.math.MathProvider;
import net.minecraft.util.MathHelper;
import net.optifine.util.MathUtils;

public class VanillaMath extends MathProvider {
    private static final float[] SIN_TABLE_FAST = new float[4096];
    private static final float[] SIN_TABLE = new float[65536];
    private static final float radToIndex = MathUtils.roundToFloat(651.8986469044033D);
    @Override
    public float sin(float rad) {
        return MathHelper.fastMath ? SIN_TABLE_FAST[(int)(rad * radToIndex) & 4095] : SIN_TABLE[(int)(rad * 10430.378F) & 65535];
    }

    @Override
    public float cos(float rad) {
        return MathHelper.fastMath ? SIN_TABLE_FAST[(int)(rad * radToIndex + 1024.0F) & 4095] : SIN_TABLE[(int)(rad * 10430.378F + 16384.0F) & 65535];
    }
    static
    {
        for (int i = 0; i < 65536; ++i)
        {
            SIN_TABLE[i] = (float)Math.sin((double)i * Math.PI * 2.0D / 65536.0D);
        }

        for (int j = 0; j < SIN_TABLE_FAST.length; ++j)
        {
            SIN_TABLE_FAST[j] = MathUtils.roundToFloat(Math.sin((double)j * Math.PI * 2.0D / 4096.0D));
        }

    }
}
