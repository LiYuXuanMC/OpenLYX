package al.logger.client.features.modules.impls.Combat;


import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Hazard;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.*;
import al.logger.client.wrapper.LoggerMC.KeyBinding;

public class AutoClicker extends Module {
    private OptionValue left = new OptionValue("LeftClick",true);
    private DoubleValue maxleftCPS = new DoubleValue("MaxLeftCPS",40D,0D,8D,1D);
    private DoubleValue minleftCPS = new DoubleValue("MinLeftCPS",40D,0D,8D,1D);
    private OptionValue right = new OptionValue("RightClick",true);
    private DoubleValue maxrightCPS = new DoubleValue("MaxRightCPS",40D,0D,8D,1D);
    private DoubleValue minrightCPS = new DoubleValue("MinRightCPS",40D,0D,8D,1D);
    private long rightLastSwing = 0L;
    private long leftLastSwing = 0L;
    private long rightDelay = 0;
    private long leftDelay = 0;

    public AutoClicker() {
        super("AutoClicker", Category.Combat);
        this.setHazard(Hazard.HACK);
        addValues(left,maxleftCPS,minleftCPS,right,maxrightCPS,minrightCPS);
    }

    @Listener
    public void onUpdate(EventLivingUpdate update){
        // Left Click
        if (mc.getGameSettings().getKeyBindAttack().isKeyDown() && left.getValue() &&
                System.currentTimeMillis() - leftLastSwing >= leftDelay && mc.getPlayerController().getCurBlockDamageMP() == 0F) {
            mc.setLeftClickCounter(0);
            KeyBinding.onTick(mc.getGameSettings().getKeyBindAttack().getKeyCode()); // Minecraft Click Handling
            leftLastSwing = System.currentTimeMillis();
            leftDelay = randomClickDelay(minleftCPS.getValue().intValue(),maxleftCPS.getValue().intValue());;
        }
        // Right Click
        if (mc.getGameSettings().getKeyBindUseItem().isKeyDown() && !mc.getThePlayer().isUsingItem() && right.getValue() &&
                System.currentTimeMillis() - rightLastSwing >= rightDelay) {
            KeyBinding.onTick(mc.getGameSettings().getKeyBindUseItem().getKeyCode()); // Minecraft Click Handling
            rightLastSwing = System.currentTimeMillis();
            rightDelay = randomClickDelay(minrightCPS.getValue().intValue(),maxrightCPS.getValue().intValue());
        }
    }
    public long randomClickDelay(final int minCPS, final int maxCPS) {
        return (long) ((Math.random() * (1000 / minCPS - 1000 / maxCPS + 1)) + 1000 / maxCPS);
    }
}
