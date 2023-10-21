package al.nya.reflect.features.modules.Movement.speeds.vulcan;

import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.wrapper.wraps.wrapper.GameSettings;
import al.nya.reflect.wrapper.wraps.wrapper.Timer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

/*
你们是不是一天天吃太饱了 天天写反作弊
 */
public class VulcanHop extends SpeedModules {
    private boolean wasTimer = false;

    public VulcanHop() {
        super(Speeds.VulcanHop);
    }

    public void onUpdate(EventUpdate update) {
        Timer timer = mc.getTimer();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        GameSettings gameSettings = mc.getGameSettings();
        if (wasTimer) {
            timer.setTimerSpeed(1.00f);
            wasTimer = false;
        }
        if (Math.abs(thePlayer.getMovementInput().getMoveStrafe()) < 0.1f) {
            thePlayer.setJumpMovementFactor(0.026499f);
        } else {
            thePlayer.setJumpMovementFactor(0.0244f);
        }
        gameSettings.getKeyBindJump().setPressed(gameSettings.getKeyBindJump().isKeyDown());

        if (MovementUtils.getSpeed() < 0.215f && !thePlayer.isOnGround()) {
            MovementUtils.strafe(0.215f);
        }
        if (thePlayer.isOnGround() && MovementUtils.isMoving()) {
            gameSettings.getKeyBindJump().setPressed(false);
            thePlayer.jump();
            if (!thePlayer.isAirBorne()) {
                return;//Prevent flag with Fly
            }
            timer.setTimerSpeed(1.25f);
            wasTimer = true;
            MovementUtils.strafe();
            if (MovementUtils.getSpeed() < 0.5f) {
                MovementUtils.strafe(0.4849f);
            }
        } else if (!MovementUtils.isMoving()) {
            timer.setTimerSpeed(1.00f);
            thePlayer.setMotionX(0);
            thePlayer.setMotionZ(0);
        }
    }
}
