package al.nya.reflect.features.modules.Movement.speeds.hypixel;

import al.nya.reflect.events.events.EventMove;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.features.modules.World.Scaffold;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.utils.entity.PlayerUtil;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class HypixelLowHop extends SpeedModules {
    public HypixelLowHop() {
        super(Speeds.HypixelLowHop);
    }
    @Override
    public void onUpdate(EventUpdate e) {
        if (PlayerUtil.MovementInput()) {
            EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
            if (MovementUtils.isOnGround(0.01) && thePlayer.isCollidedVertically()) {
                thePlayer.setMotionY(0.095);
            }
            if (ModuleManager.getModule(Scaffold.class).isEnable()) {
                setMotion(null, MovementUtils.defaultSpeed() * 2);
            } else {
                setMotion(null, Math.max(0.285, MovementUtils.defaultSpeed() * 0.8));
            }

        }
    }

    public static void setMotion(EventMove em, double speed) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        double forward = thePlayer.getMovementInput().getMoveForward();
        double strafe = thePlayer.getMovementInput().getMoveStrafe();
        float yaw = thePlayer.getRotationYaw();
        if ((forward == 0.0D) && (strafe == 0.0D)) {
            thePlayer.setMotionX(0);
            thePlayer.setMotionZ(0);
            if (em != null) {
                em.setX(0.0D);
                em.setZ(0.0D);
            }
        } else {
            if (forward != 0.0D) {
                if (strafe > 0.0D) {
                    yaw += (forward > 0.0D ? -45 : 45);
                } else if (strafe < 0.0D) {
                    yaw += (forward > 0.0D ? 45 : -45);
                }
                strafe = 0.0D;
                if (forward > 0.0D) {
                    forward = 1;
                } else if (forward < 0.0D) {
                    forward = -1;
                }
            }
            double cos = Math.cos(Math.toRadians(yaw + 90));
            double sin = Math.sin(Math.toRadians(yaw + 90));
            thePlayer.setMotionX(forward * speed * cos + strafe * speed * sin);
            thePlayer.setMotionZ(forward * speed * sin - strafe * speed * cos);
            if (em != null) {
                em.setX(thePlayer.getMotionX());
                em.setZ(thePlayer.getMotionZ());
            }
        }
    }

}
