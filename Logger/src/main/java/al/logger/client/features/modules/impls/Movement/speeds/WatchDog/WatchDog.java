package al.logger.client.features.modules.impls.Movement.speeds.WatchDog;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventTick;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.misc.MathHelper;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.utils.player.PlayerUtils;
import al.logger.client.value.impls.ModeValue;

public class WatchDog extends Module {
    ModeValue mode = new ModeValue("Mode", Mode.GroundStrafe,Mode.values());
    public WatchDog(){
        super("WatchDog", "", Category.Movement);
        addValues(mode);
    }

    @Listener
    public void onTick(EventTick e){
        if (PlayerUtils.isInLiquid()) {
            return;
        }

        if (mode.getValue() == Mode.GroundStrafe) {
            if (MovementUtils.isMoving()) {
                if (mc.getThePlayer().isOnGround()) {
                    MovementUtils.jump();
                }
                float speed = 1.48f;
                speed = mc.getThePlayer().getMoveStrafing() != 0.0f || mc.getThePlayer().getMoveForward() < 0.0f ? (speed -= 0.2f) : (float) ((double) speed - MathHelper.getIncremental(0.00411, 0.0465123));
                if (mc.getThePlayer().isOnGround()) {
                    PlayerUtils.setSpeed(PlayerUtils.getBaseMoveSpeed() * (double) speed);
                }
            } else {
                mc.getThePlayer().setMotionZ(0.0);
                mc.getThePlayer().setMotionX(0.0);
            }
        }
    }

    enum Mode{
        GroundStrafe
    }
}
