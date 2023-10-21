package al.nya.reflect.features.modules.Movement.speeds.hypixel;

import al.nya.reflect.events.events.EventMove;
import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.utils.entity.PlayerUtil;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;


public class HypixelBunny extends SpeedModules {
    double distance;
    int stage;
    double speed, less;
    boolean wasOnGround;
    float yaw;
    int failTimes;
    public final double[] LOW_HOP_Y_POSITIONS = {
            MovementUtils.round(0.4, 0.001),
            MovementUtils.round(0.71, 0.001),
            MovementUtils.round(0.75, 0.001),
            MovementUtils.round(0.55, 0.001),
            MovementUtils.round(0.41, 0.001)
    };

    public HypixelBunny() {
        super(Speeds.HypixelBunny);
    }

    @Override
    public void onDisable() {
        if (!mc.getThePlayer().isNull() && !mc.getTheWorld().isNull()) {
            ClientUtil.resetTimer();
            mc.getThePlayer().setSpeedInAir(0.02F);
            stage = 0;
            speed = 0;
            less = 0;
            distance = 0;
        }
    }

    @Override
    public void onMove(EventMove event) {
        if (MovementUtils.isMoving()) {
            double baseMoveSpeed = MovementUtils.getBaseMoveSpeed(0.2871, 0.2);
            EntityPlayerSP thePlayer = mc.getThePlayer();
            boolean shouldLowhop = !mc.getGameSettings().getKeyBindJump().isKeyDown() &&
                    !thePlayer.isPotionActive(Potion.jump) && !thePlayer.isCollidedHorizontally();

            if (!thePlayer.isOnGround() && shouldLowhop && thePlayer.getFallDistance() < 0.54)
                thePlayer.setMotionY(lowHopYModification(thePlayer.getMotionY(), MovementUtils.round(thePlayer.getPosY() - (int) thePlayer.getPosY(), 0.001)));
            event.setY(thePlayer.getMotionY());

            if (thePlayer.isOnGround() && thePlayer.isCollidedVertically() && !wasOnGround) {
                speed = baseMoveSpeed * 1.7;
                thePlayer.setMotionY(shouldLowhop ? 0.4F : MovementUtils.getJumpHeight(thePlayer));
                event.setY(thePlayer.getMotionY());
                wasOnGround = true;
            } else if (wasOnGround) {
                wasOnGround = false;
                final double bunnySlope = 0.66 * (distance - baseMoveSpeed);
                speed = distance - bunnySlope;
            } else {
                speed = MovementUtils.applyNCPFriction(thePlayer, speed, distance, baseMoveSpeed);
            }

            speed = Math.max(speed, baseMoveSpeed);

            if (failTimes > 0 && failTimes % 2 == 0) speed = speed * 0.9;

            PlayerUtil.setSpeed(event, speed);
        }

    }

    @Override
    public void onPre(EventPreUpdate preUpdate) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        double xDist = thePlayer.getPosX() - thePlayer.getLastTickPosX();
        double zDist = thePlayer.getPosZ() - thePlayer.getLastTickPosZ();
        distance = Math.sqrt(xDist * xDist + zDist * zDist);
        if (failTimes > 0) failTimes--;
        float yaw = (float) (Math.toDegrees(Math.atan2(zDist, xDist)) - 90.0);
        if ((Math.abs(this.yaw - yaw)) >= 45) failTimes = 5;
        this.yaw = yaw;

    }

    private double lowHopYModification(final double baseMotionY,
                                       final double yDistFromGround) {
        if (yDistFromGround == LOW_HOP_Y_POSITIONS[0]) {
            return 0.31;
        } else if (yDistFromGround == LOW_HOP_Y_POSITIONS[1]) {
            return 0.04;
        } else if (yDistFromGround == LOW_HOP_Y_POSITIONS[2]) {
            return -0.2;
        } else if (yDistFromGround == LOW_HOP_Y_POSITIONS[3]) {
            return -0.14;
        } else if (yDistFromGround == LOW_HOP_Y_POSITIONS[4]) {
            return -0.2;
        }

        return baseMotionY;
    }

}
