package al.nya.reflect.features.modules.Movement.speeds.aac;

import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class AAC2BHop extends SpeedModules {
    public AAC2BHop() {
        super(Speeds.AAC2BHop);
    }

    @Override
    public void onPre(EventPreUpdate pre) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isInWater()) return;

        if (MovementUtils.isMoving()) {
            if (thePlayer.isOnGround()) {
                thePlayer.jump();
                thePlayer.setMotionX(thePlayer.getMotionX()*1.02);
                thePlayer.setMotionZ(thePlayer.getMotionZ()*1.02);
            } else if (thePlayer.getMotionY() > -0.2) {
                thePlayer.setJumpMovementFactor(0.08f);
                thePlayer.setMotionY(thePlayer.getMotionY()*0.0143099999999999999999999999999);
                thePlayer.setJumpMovementFactor(0.07f);
            }
        } else {
            thePlayer.setMotionX(0.0);
            thePlayer.setMotionZ(0.0);
        }
    }
}
