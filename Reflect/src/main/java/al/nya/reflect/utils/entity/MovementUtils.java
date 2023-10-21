package al.nya.reflect.utils.entity;

import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetHandlerPlayClient;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;
import al.nya.reflect.wrapper.wraps.wrapper.potion.PotionEffect;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public class MovementUtils {
    public static Minecraft mc;
    public static final double SPRINTING_MOD = 1.0 / 1.3F;
    public static final double SNEAK_MOD = 0.3F;
    public static final double ICE_MOD = 2.5F;
    public static final double WALK_SPEED = 0.221;
    public static final double SWIM_MOD = 0.115F / WALK_SPEED;
    public static final double[] DEPTH_STRIDER_VALUES = {
            1.0F,
            0.1645F / SWIM_MOD / WALK_SPEED,
            0.1995F / SWIM_MOD / WALK_SPEED,
            1.0F / SWIM_MOD,
    };
    public static final double[] SPEEDS = new double[3];
    public static final double MIN_DIST = 1.0E-3;
    public static final double AIR_FRICTION = 0.98F;
    public static final double WATER_FRICTION = 0.89F;
    public static final double LAVA_FRICTION = 0.535F;
    public static final double BUNNY_DIV_FRICTION = 160.0 - 1.0E-3;

    static {
        mc = Minecraft.getMinecraft();
    }

    public static boolean isMoving() {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        return Objects.nonNull(thePlayer.getWrapObject())
                && (thePlayer.getMovementInput().getMoveForward() != 0F || thePlayer.getMovementInput().getMoveStrafe() != 0F);
    }

    public static void strafe() {
        strafe((double) getSpeed());
    }

    public static void strafe(final Double double1) {
        if (!isMoving())
            return;
        final double yaw = getDirection();
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        thePlayer.setMotionX(-Math.sin(yaw) * double1);
        thePlayer.setMotionZ(Math.cos(yaw) * double1);
    }

    public static boolean isOnGround(double height) {
        Minecraft mc = Minecraft.getMinecraft();
        WorldClient theWorld = mc.getTheWorld();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        return !theWorld.getCollidingBoundingBoxes(thePlayer, thePlayer.getEntityBoundingBox().offset(0.0D, -height, 0.0D)).isEmpty();
    }

    public static double getBaseMoveSpeed(double speed, double v) {
        double baseSpeed = speed;
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        if (thePlayer.isPotionActive(Potion.moveSpeed)) {
            int amplifier = thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier();
            baseSpeed *= 1.0 + v * (amplifier + 1);
        }
        return baseSpeed;
    }

    public static double getJumpHeight(final EntityPlayerSP player) {
        final double base = 0.42F;
        final PotionEffect effect = player.getActivePotionEffect(Potion.jump);
        return effect.isNull() ? base : base + ((effect.getAmplifier() + 1) * 0.1F);
    }

    public static double applyNCPFriction(final EntityPlayerSP player,
                                          final double moveSpeed,
                                          final double lastDist,
                                          final double baseMoveSpeedRef) {
        SPEEDS[0] = lastDist - (lastDist / BUNNY_DIV_FRICTION);
        SPEEDS[1] = lastDist - ((moveSpeed - lastDist) / 33.3D);
        double materialFriction = player.isInWater() ? WATER_FRICTION :
                player.isInLava() ? LAVA_FRICTION :
                        AIR_FRICTION;
        SPEEDS[2] = lastDist - (baseMoveSpeedRef * (1.0D - materialFriction));

        Arrays.sort(SPEEDS);

        return SPEEDS[0];
    }

    public static void strafe(final float speed) {
        if (!isMoving())
            return;
        final double yaw = getDirection();
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        thePlayer.setMotionX(-Math.sin(yaw) * speed);
        thePlayer.setMotionZ(Math.cos(yaw) * speed);
    }

    public static double round(final double value, final double inc) {
        if (inc == 0.0) return value;
        else if (inc == 1.0) return Math.round(value);
        else {
            final double halfOfInc = inc / 2.0;
            final double floored = Math.floor(value / inc) * inc;

            if (value >= floored + halfOfInc)
                return new BigDecimal(Math.ceil(value / inc) * inc)
                        .doubleValue();
            else return new BigDecimal(floored)
                    .doubleValue();
        }
    }

    public static double getDirection() {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        float rotationYaw = thePlayer.getRotationYaw();
        float moveForward = thePlayer.getMoveForward();
        float moveStrafing = thePlayer.getMoveStrafing();
        if (moveForward < 0F)
            rotationYaw += 180F;
        float forward = 1F;
        if (moveForward < 0F)
            forward = -0.5F;
        else if (moveForward > 0F)
            forward = 0.5F;
        if (moveStrafing > 0F)
            rotationYaw -= 90F * forward;
        if (moveStrafing < 0F)
            rotationYaw += 90F * forward;
        return Math.toRadians(rotationYaw);
    }

    public static double defaultSpeed() {
        double baseSpeed = 0.2873;
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isPotionActive(Potion.moveSpeed)) {
            final int amplifier = thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier();
            baseSpeed *= 1.0 + 0.2 * (amplifier + 1);
        }
        return baseSpeed;
    }

    public static double getBaseMoveSpeed() {
        Potion moveSlowdown = Potion.moveSlowdown;
        Potion moveSpeed = Potion.moveSpeed;
        EntityPlayerSP thePlayer = mc.getThePlayer();
        double baseSpeed = thePlayer.getCapabilities().getWalkSpeed() * 2.873;
        if (thePlayer.isPotionActive(moveSlowdown)) {
            baseSpeed /= 1.0 + 0.2 * (thePlayer.getActivePotionEffect(moveSlowdown).getAmplifier() + 1);
        }
        if (thePlayer.isPotionActive(moveSpeed)) {
            baseSpeed *= 1.0 + 0.2 * (thePlayer.getActivePotionEffect(moveSpeed).getAmplifier() + 1);
        }
        return baseSpeed;
    }

    public static float getSpeed() {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        double motionX = thePlayer.getMotionX();
        double motionZ = thePlayer.getMotionZ();
        return (float) Math
                .sqrt(motionX * motionX + motionZ * motionZ);
    }

    public static double calculateGround() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        AxisAlignedBB playerBoundingBox = thePlayer.getEntityBoundingBox();
        double blockHeight = 1.0;
        double ground = thePlayer.getPosY();
        while (ground > 0.0) {
            AxisAlignedBB customBox = new AxisAlignedBB(playerBoundingBox.getMaxX(), ground + blockHeight, playerBoundingBox.getMaxZ(), playerBoundingBox.getMinX(), ground, playerBoundingBox.getMinZ());
            if (mc.getTheWorld().checkBlockCollision(customBox)) {
                if (blockHeight <= 0.05) return ground + blockHeight;
                ground += blockHeight;
                blockHeight = 0.05;
            }
            ground -= blockHeight;
        }
        return 0.0;
    }

    public static void handleVanillaKickBypass() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        NetHandlerPlayClient netHandler = mc.getNetHandler();
        double posX = thePlayer.getPosX();
        double posZ = thePlayer.getPosZ();
        double ground = calculateGround();
        {
            double posY = thePlayer.getPosY();
            while (posY > ground) {
                netHandler.addToSendQueue(new C04PacketPlayerPosition(posX, posY, posZ, true));
                if (posY - 8.0 < ground) break; // Prevent next step
                posY -= 8.0;
            }
        }
        netHandler.addToSendQueue(new C04PacketPlayerPosition(posX, ground, posZ, true));
        double posY = ground;
        while (posY < thePlayer.getPosY()) {
            netHandler.addToSendQueue(new C04PacketPlayerPosition(posX, posY, posZ, true));
            if (posY + 8.0 > thePlayer.getPosY()) break; // Prevent next step
            posY += 8.0;
        }
        netHandler.addToSendQueue(new C04PacketPlayerPosition(posX, thePlayer.getPosY(), posZ, true));
    }
}
