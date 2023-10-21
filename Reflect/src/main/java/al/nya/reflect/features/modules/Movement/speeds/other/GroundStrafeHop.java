package al.nya.reflect.features.modules.Movement.speeds.other;

import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class GroundStrafeHop extends SpeedModules {
    public GroundStrafeHop() {
        super(Speeds.GroundStrafeHop);
    }
    public void onPre(EventPreUpdate e) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (MovementUtils.isMoving()) {
            if (thePlayer.isOnGround()) {
                thePlayer.jump();
                MovementUtils.strafe();
            }
        } else {
            thePlayer.setMotionX(0);
            thePlayer.setMotionZ(0);
        }
    }
}
