package al.nya.reflect.features.modules.Movement.speeds.aac;

import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.MathHelper;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.wrapper.wraps.wrapper.Timer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class AACBHop extends SpeedModules {
    public AACBHop() {
        super(Speeds.AACBHop);
    }

    @Override
    public void onPre(EventPreUpdate pre) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        Timer timer = mc.getTimer();
        if (thePlayer.isInWater()) return;

        if (MovementUtils.isMoving()) {
            timer.setTimerSpeed(1.08f);
            if (thePlayer.isOnGround()) {
                thePlayer.setMotionY(0.399);
                float f = thePlayer.getRotationYaw() * 0.017453292f;
                thePlayer.setMotionX(thePlayer.getMotionX()-(MathHelper.sin(f) * 0.2f));
                thePlayer.setMotionZ(thePlayer.getMotionZ()+(MathHelper.cos(f) * 0.2f));
                timer.setTimerSpeed(2f);
            } else {
                thePlayer.setMotionY(thePlayer.getMotionY()*0.97);
                thePlayer.setMotionX(thePlayer.getMotionX()*1.008);
                thePlayer.setMotionZ(thePlayer.getMotionZ()*1.008);
            }
        } else {
            thePlayer.setMotionX(0.0);
            thePlayer.setMotionZ(0.0);
            timer.setTimerSpeed(1f);
        }
    }
}
