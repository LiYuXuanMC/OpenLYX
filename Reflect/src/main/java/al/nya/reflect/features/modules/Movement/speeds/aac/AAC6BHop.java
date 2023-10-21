package al.nya.reflect.features.modules.Movement.speeds.aac;

import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class AAC6BHop extends SpeedModules {
    public AAC6BHop() {
        super(Speeds.AAC6BHop);
    }
    private boolean legitJump = false;

    @Override
    public void onUpdate(EventUpdate update) {
        ClientUtil.resetTimer();;
        EntityPlayerSP thePlayer = mc.getThePlayer();
        
        if (thePlayer.isInWater()) return;

        if (MovementUtils.isMoving()) {
            if (thePlayer.isOnGround()) {
                if (legitJump) {
                    thePlayer.setMotionY(0.4);
                    MovementUtils.strafe(0.15f);
                    thePlayer.setOnGround(false);
                    legitJump = false;
                    return;
                }
                thePlayer.setMotionY(0.41);
                MovementUtils.strafe(0.47458485f);
            }

            if (thePlayer.getMotionY() < 0 && thePlayer.getMotionY() > -0.2) mc.getTimer().setTimerSpeed((float) (1.2 + thePlayer.getMotionY()));

            thePlayer.setSpeedInAir(0.022151f);
        } else {
            legitJump = true;
            thePlayer.setMotionX(0.0);
            thePlayer.setMotionZ(0.0);
        }
    }
}
