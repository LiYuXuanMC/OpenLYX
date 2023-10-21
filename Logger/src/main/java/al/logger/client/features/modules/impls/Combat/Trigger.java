package al.logger.client.features.modules.impls.Combat;


import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventRender3D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ValidUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.wrapper.LoggerMC.KeyBinding;
import lombok.val;

public class Trigger extends Module {
    private DoubleValue maxCPS = new DoubleValue("MaxCPS", 20D, 1D, 8D,1D) {
        public void onValueChange(Double old,Double newV) {
            double i = minCPS.getValue();
            if (i > newV) setValue(old);
            delay = randomClickDelay(minCPS.getValue().intValue(),getValue().intValue());
        }
    };
    private DoubleValue minCPS = new DoubleValue("MinCPS", 20D,1D,5D,1D) {
        public void onValueChange(Double old,Double newV) {
            val i = maxCPS.getValue();
            if (i < newV) setValue(old);
            delay = randomClickDelay(this.getValue().intValue(), maxCPS.getValue().intValue());
        }
    };
    private long delay = randomClickDelay(minCPS.getValue().intValue(), maxCPS.getValue().intValue());
    private long lastSwing = 0L;
    public long randomClickDelay(final int minCPS, final int maxCPS) {
        return (long) ((Math.random() * (1000 / minCPS - 1000 / maxCPS + 1)) + 1000 / maxCPS);
    }
    public Trigger() {
        super("Trigger", Category.Combat);
        addValues(maxCPS,minCPS);
    }
    @Listener
    public void onRender(EventRender3D render3D) {
        val objectMouseOver = mc.getObjectMouseOver();

        if (!objectMouseOver.isNull() && System.currentTimeMillis() - lastSwing >= delay &&
                ValidUtils.isValid(objectMouseOver.getEntityHit())) {
            KeyBinding.onTick(mc.getGameSettings().getKeyBindAttack().getKeyCode()); // Minecraft Click handling
            lastSwing = System.currentTimeMillis();
            delay = randomClickDelay(minCPS.getValue().intValue(), maxCPS.getValue().intValue());
        }
    }
}
