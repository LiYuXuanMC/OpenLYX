package al.nya.reflect.features.modules.Movement.speeds.aac;

import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class AAC7BHop extends SpeedModules {
    public AAC7BHop() {
        super(Speeds.AAC7BHop);
    }

    @Override
    public void onUpdate(EventUpdate update) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (!MovementUtils.isMoving() || !thePlayer.getRidingEntity().isNull() || thePlayer.getHurtTime() > 0) return;

        if (thePlayer.isOnGround()) {
            thePlayer.jump();
            thePlayer.setMotionY(0.405);
            thePlayer.setMotionX(thePlayer.getMotionX()*1.004);
            thePlayer.setMotionZ(thePlayer.getMotionZ()*1.004);
            return;
        }

        double speed = MovementUtils.getSpeed() * 1.0072;
        double yaw = Math.toRadians(thePlayer.getRotationYaw());
        thePlayer.setMotionX(-Math.sin(yaw) * speed);
        thePlayer.setMotionZ(Math.cos(yaw) * speed);
    }
}
