package al.logger.client.features.modules.impls.Movement.speeds.other;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;

public class AutoJump extends Module {
    public AutoJump() {
        super("AutoJump", Category.Movement);
    }
    @Listener
    public void onTick(EventTick e) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isOnGround()) {
            if (MovementUtils.isMoving()) {
                thePlayer.setMotionY(0.42D);
            }
        }
    }
}
