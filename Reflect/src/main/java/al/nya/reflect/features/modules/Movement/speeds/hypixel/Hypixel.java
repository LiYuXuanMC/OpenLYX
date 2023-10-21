package al.nya.reflect.features.modules.Movement.speeds.hypixel;

import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.features.modules.Combat.KillAura;
import al.nya.reflect.features.modules.Movement.Speed;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class Hypixel extends SpeedModules {
    public Hypixel() {
        super(Speeds.Hypixel);
    }
    public void onDisable(){
        ClientUtil.resetTimer();
    }
    @Override
    public void onPre(EventPreUpdate e) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isOnGround() && !mc.getGameSettings().getKeyBindJump().isKeyDown()) {
            if (MovementUtils.isMoving()) {
                if (thePlayer.getMoveForward() > 0.0f) {
                    MovementUtils.strafe(MovementUtils.getBaseMoveSpeed() * 1.6 + Math.random() / 100);
                }
                else {
                    MovementUtils.strafe(MovementUtils.getBaseMoveSpeed() * 1 + Math.random() / 100);
                }
                thePlayer.setMotionY(0.418);
            }
        }
        if (MovementUtils.isMoving()) {
            if (KillAura.curTarget == null) {
                mc.getTimer().setTimerSpeed(Speed.timerSpeed.getValue().floatValue());
            }
            else {
                ClientUtil.resetTimer();
            }
        }
        MovementUtils.strafe();
    }
}
