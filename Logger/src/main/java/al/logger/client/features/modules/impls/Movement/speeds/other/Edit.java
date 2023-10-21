package al.logger.client.features.modules.impls.Movement.speeds.other;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventMove;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.event.client.player.EventUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.impls.Movement.Speed;
import al.logger.client.utils.player.ClientUtils;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;

public class Edit extends Module {
    private DoubleValue motionY = new DoubleValue("MotionY", 1.0, 0.01, 0.42, 0.01);
    private OptionValue forwardBoost = new OptionValue("ForwardBoost", true);
    private DoubleValue speed = new DoubleValue("Speed", 2, 0.001, 0.5, 0.001);

    public Edit() {
        super("Edit",null);
        addValues(motionY, forwardBoost, speed);
    }

    @Listener
    public void onMove(EventMove e) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (MovementUtils.isMoving()) {
            if (thePlayer.getMoveForward() > 0.0f && forwardBoost.getValue()) {
                MovementUtils.strafe((float) (MovementUtils.getBaseMoveSpeed() * 1.6 + speed.getValue()));
            } else {
                MovementUtils.strafe((float) (MovementUtils.getBaseMoveSpeed() * 1 + speed.getValue()));
            }
        }
        if (MovementUtils.isMoving()) {
            mc.getTimer().setTimerSpeed(((Speed)Logger.getInstance().getModuleManager().getModule(Speed.class)).timerSpeed.getValue().floatValue());
        } else {
            ClientUtils.resetTimer();
        }
    }
    @Listener
    public void onUpdate(EventUpdate e){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isOnGround()) {
            if (MovementUtils.isMoving()) {
                thePlayer.setMotionY(motionY.getValue());
            }
        }
    }

    @Override
    public void onDisable() {
        ClientUtils.resetTimer();
    }
}
