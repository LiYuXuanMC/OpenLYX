package al.nya.reflect.features.modules.Movement.speeds.other;

import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class CSGO extends SpeedModules {
    int counter;

    public CSGO() {
        super(Speeds.CSGO);
    }
    public void onPre(EventPreUpdate e) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isOnGround() && MovementUtils.isMoving()) {
            counter++;
            thePlayer.setMotionY(0.41999999999999999999999);
            MovementUtils.strafe(MovementUtils.defaultSpeed() + counter * 0.05);
        }
        if (!MovementUtils.isMoving() || thePlayer.isCollidedVertically()) {
            counter = 0;
        }
        MovementUtils.strafe();
    }
}
