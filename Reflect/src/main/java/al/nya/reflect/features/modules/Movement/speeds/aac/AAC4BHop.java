package al.nya.reflect.features.modules.Movement.speeds.aac;

import al.nya.reflect.events.events.EventTick;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class AAC4BHop extends SpeedModules {
    public AAC4BHop() {
        super(Speeds.AAC4BHop);
    }
    private boolean legitHop = false;

    @Override
    public void onEnable() {
        legitHop = true;
    }

    @Override
    public void onDisable() {
        mc.getThePlayer().setSpeedInAir(0.02f);
    }

    @Override
    public void onTick(EventTick tick) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (MovementUtils.isMoving()) {
            if (legitHop) {
                if (thePlayer.isOnGround()) {
                    thePlayer.jump();
                    thePlayer.setOnGround(false);
                    legitHop = false;
                }
                return;
            }
            if (thePlayer.isOnGround()) {
                thePlayer.setOnGround(false);
                MovementUtils.strafe(0.375f);
                thePlayer.jump();
                thePlayer.setMotionY(0.41);
            } else thePlayer.setSpeedInAir(0.0211f);
        } else {
            thePlayer.setMotionX(0.0);
            thePlayer.setMotionZ(0.0);
            legitHop = true;
        }
    }
}
