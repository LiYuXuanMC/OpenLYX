package al.nya.reflect.features.modules.Movement.speeds.hypixel;

import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.features.modules.Movement.Speed;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;

public class HypixelStrafe extends SpeedModules {
    public static int stage;
    public double movementSpeed;
    boolean collided = false;
    boolean lessSlow;
    double less;
    private double AiSpeed = 0;
    private static final double distance = 0;
    public HypixelStrafe() {
        super(Speeds.HypixelStrafe);
    }
    @Override
    public void onDisable() {
        ClientUtil.resetTimer();
    }

    @Override
    public void onEnable() {
        boolean player = mc.getThePlayer().isNull();
        collided = player ? false : mc.getThePlayer().isCollidedHorizontally();
        lessSlow = false;
        less = 0.0;
        stage = 2;
        ClientUtil.resetTimer();
    }
    @Override
    public void onPre(EventPreUpdate event) {
        EntityLivingBase thePlayer = mc.getThePlayer();
        strafe();
        if (MovementUtils.isMoving()) {
            if (thePlayer.isOnGround()) {
                thePlayer.setMotionY(0.42F);
                AiSpeed = 0.3623;
                if (thePlayer.isPotionActive(Potion.moveSpeed)) {
                    AiSpeed = 0.545023;
                }
                mc.getTimer().setTimerSpeed(Speed.timerSpeed.getValue().floatValue());
                AiSpeed = AiSpeed - 0.006;
                MovementUtils.strafe(AiSpeed);
            }
        }
    }
    public static void strafe(final double d) {
        EntityLivingBase thePlayer = Minecraft.getMinecraft().getThePlayer();
        if (!MovementUtils.isMoving())
            return;
        final double yaw = MovementUtils.getDirection();
        thePlayer.setMotionX(-Math.sin(yaw) * d);
        thePlayer.setMotionZ(Math.cos(yaw) * d);
    }

    public static void strafe() {
        strafe(MovementUtils.getSpeed());
    }
}
