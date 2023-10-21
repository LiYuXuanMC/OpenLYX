package al.nya.reflect.features.modules.Movement.speeds.other;

import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.features.modules.Combat.KillAura;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.Movement.Speed;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class Edit extends SpeedModules {
    private DoubleValue motionY = new DoubleValue("MotionY", 1.0, 0.01, 0.42, "0.00") {
        @Override
        public boolean show() {
            return ModuleManager.getModule(Speed.class).mode.getValue() == Speeds.Edit;
        }
    };
    private OptionValue forwardBoost = new OptionValue("ForwardBoost", true) {
        @Override
        public boolean show() {
            return ModuleManager.getModule(Speed.class).mode.getValue() == Speeds.Edit;
        }
    };
    private DoubleValue speed = new DoubleValue("Speed", 2, 0.001, 0.5, "0.000") {
        @Override
        public boolean show() {
            return ModuleManager.getModule(Speed.class).mode.getValue() == Speeds.Edit;
        }
    };

    public Edit() {
        super(Speeds.Edit);
        addValue(motionY);
        addValue(forwardBoost);
        addValue(speed);
    }

    @Override
    public void onPre(EventPreUpdate e) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isOnGround()) {
            if (MovementUtils.isMoving()) {
                thePlayer.setMotionY(motionY.getValue());
            }
        }
        if (MovementUtils.isMoving()) {
            if (thePlayer.getMoveForward() > 0.0f && forwardBoost.getValue()) {
                MovementUtils.strafe(MovementUtils.getBaseMoveSpeed() * 1.6 + speed.getValue());
            } else {
                MovementUtils.strafe(MovementUtils.getBaseMoveSpeed() * 1 + speed.getValue());
            }
        }
        if (MovementUtils.isMoving()) {
            mc.getTimer().setTimerSpeed(Speed.timerSpeed.getValue().floatValue());
        } else {
            ClientUtil.resetTimer();
        }
    }

    @Override
    public void onDisable() {
        ClientUtil.resetTimer();
    }
}
