package al.nya.reflect.features.modules.Movement.speeds.aac;

import al.nya.reflect.events.events.EventTick;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.wrapper.wraps.wrapper.Timer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class AAC3BHop extends SpeedModules {
    public AAC3BHop() {
        super(Speeds.AAC3BHop);
    }
    private boolean legitJump = false;

    @Override
    public void onTick(EventTick tick) {
        ClientUtil.resetTimer();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        Timer timer = mc.getTimer();
        if (thePlayer.isInWater()) return;
        if (MovementUtils.isMoving()) {

                if (thePlayer.isOnGround())  {
                    if (legitJump) {
                        thePlayer.jump();
                        legitJump = false;
                        return;
                    }
                    thePlayer.setMotionY(0.3852);
                    thePlayer.setOnGround(false);
                    MovementUtils.strafe(0.374f);
                }

                if (thePlayer.getMotionY() < 0.0) {
                    thePlayer.setSpeedInAir(0.0201f);
                    timer.setTimerSpeed(1.02f);
                } else { timer.setTimerSpeed(1.01f); }

        } else {
            legitJump = true;
            thePlayer.setMotionX(0.0);
            thePlayer.setMotionZ(0.0);
        }
    }
}
