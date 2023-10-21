package al.nya.reflect.utils;

import al.nya.reflect.features.modules.Combat.FastBow;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;

import java.util.Random;

public class RotationUtils {
    public static Rotation targetRotation;
    private static Random random = new Random();
    private static Minecraft mc = Minecraft.getMinecraft();
    private static double x = random.nextDouble();
    private static double y = random.nextDouble();
    private static double z = random.nextDouble();
    private static int keepLength;
    public static Rotation serverRotation = new Rotation(0F, 0F);
    public static float[] getRotations(Entity ent) {
        double posX = ent.getPosX();
        double posY = ent.getPosY();
        double posZ = ent.getPosZ();
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        double x = posX + (posX - ent.getLastTickPosX()) - thePlayer.getPosX();
        double z = posZ + (posZ - ent.getLastTickPosZ()) - thePlayer.getPosZ();
        double y = posY + (posY - ent.getLastTickPosY()) - thePlayer.getPosY() - 1.0257F;
        double dist = MathHelper.sqrt_double(x * x + z * z);
        float yaw = (float) (Math.atan2(z, x) * 180.0D / 3.141592653589793D) - 90.0F;
        float pitch = (float) (-Math.atan2(y, dist) * 180.0D / 3.141592653589793D);
        return new float[] { yaw, pitch };
    }
    public static Rotation toRotation(final Vec3 vec, final boolean predict) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        final Vec3 eyesPos = new Vec3(thePlayer.getPosX(), thePlayer.getEntityBoundingBox().getMinY() +
                thePlayer.getEyeHeight(), thePlayer.getPosZ());

        if (predict)
             eyesPos.addVector(thePlayer.getMotionX(), thePlayer.getMotionY(), thePlayer.getMotionZ());

        final double diffX = vec.getXCoord() - eyesPos.getXCoord();
        final double diffY = vec.getYCoord() - eyesPos.getYCoord();
        final double diffZ = vec.getZCoord() - eyesPos.getZCoord();

        return new Rotation(MathHelper.wrapAngleTo180_float(
                (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90F
        ), MathHelper.wrapAngleTo180_float(
                (float) (-Math.toDegrees(Math.atan2(diffY, Math.sqrt(diffX * diffX + diffZ * diffZ))))
        ));
    }

    public static Rotation getRotationFromEyeHasPrev(EntityLivingBase target) {
        final double x = (target.getPrevPosX() + (target.getPosX() - target.getPrevPosX()));
        final double y = (target.getPrevPosY() + (target.getPosY() - target.getPrevPosY()));
        final double z = (target.getPrevPosZ() + (target.getPosZ() - target.getPrevPosZ()));
        float[] floats = getRotationFromEyeHasPrev(x, y, z);
        return new Rotation(floats[0],floats[1]);
    }
    public static Rotation limitAngleChange(final Rotation currentRotation, final Rotation targetRotation, final float turnSpeed) {
        final float yawDifference = getAngleDifference(targetRotation.getYaw(), currentRotation.getYaw());
        final float pitchDifference = getAngleDifference(targetRotation.getPitch(), currentRotation.getPitch());

        return new Rotation(
                currentRotation.getYaw() + (yawDifference > turnSpeed ? turnSpeed : Math.max(yawDifference, -turnSpeed)),
                currentRotation.getPitch() + (pitchDifference > turnSpeed ? turnSpeed : Math.max(pitchDifference, -turnSpeed)
                ));
    }
    public static float getAngleDifference(final float a, final float b) {
        return ((((a - b) % 360F) + 540F) % 360F) - 180F;
    }
    public static Vec3 getCenter(final AxisAlignedBB bb) {
        return new Vec3(bb.getMinX() + (bb.getMaxX() - bb.getMinX()) * 0.5, bb.getMinY() + (bb.getMaxY() - bb.getMinY()) * 0.5, bb.getMinZ() + (bb.getMaxZ() - bb.getMinZ()) * 0.5);
    }
    public static double getRotationDifference(final Entity entity) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        final Rotation rotation = toRotation(getCenter(entity.getEntityBoundingBox()), true);

        return getRotationDifference(rotation, new Rotation(thePlayer.getRotationYaw(), thePlayer.getRotationPitch()));
    }
    public static double getRotationDifference(final Rotation a, final Rotation b) {
        return Math.hypot(getAngleDifference(a.getYaw(), b.getYaw()), a.getPitch() - b.getPitch());
    }
    public static float getRotationDifference(float current, float target) {
        return MathHelper.wrapAngleTo180_float(target - current);
    }
    public static float[] getHypixelRotations(EntityLivingBase curTarget) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        double diffX = curTarget.getPosX() - thePlayer.getPosX();
        double diffZ = curTarget.getPosZ() - thePlayer.getPosZ();
        double diffY = curTarget.getPosY() + 1F - (thePlayer.getPosY() + (double)thePlayer.getEyeHeight());
        double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float)((- Math.atan2(diffY, dist)) * 180.0 / Math.PI);
        return new float[]{yaw, pitch};
    }
    public static float getYawDifference(float currentYaw, double targetX, double targetY, double targetZ) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        double deltaX = targetX - thePlayer.getPosX();
        double deltaY = targetY - thePlayer.getPosY();
        double deltaZ = targetZ - thePlayer.getPosZ();
        double yawToEntity = 0;
        double degrees = Math.toDegrees(Math.atan(deltaZ / deltaX));
        if ((deltaZ < 0.0D) && (deltaX < 0.0D)) {
            if (deltaX != 0) yawToEntity = 90.0D + degrees;
        } else if ((deltaZ < 0.0D) && (deltaX > 0.0D)) {
            if (deltaX != 0) yawToEntity = -90.0D + degrees;
        } else {
            if (deltaZ != 0) yawToEntity = Math.toDegrees(-Math.atan(deltaX / deltaZ));
        }
        return MathHelper.wrapAngleTo180_float(-(currentYaw - (float) yawToEntity));
    }

    public static float getPitchDifference(float currentPitch, double targetX, double targetY, double targetZ) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        double deltaX = targetX - thePlayer.getPosX();
        double deltaY = targetY - thePlayer.getPosY();
        double deltaZ = targetZ - thePlayer.getPosZ();
        double distanceXZ = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ);
        double pitchToEntity = -Math.toDegrees(Math.atan(deltaY / distanceXZ));
        return -MathHelper.wrapAngleTo180_float(currentPitch - (float) pitchToEntity) - 2.5F;
    }
    public static Vec3 getVectorForRotation(float[] rotation) {
        final double f = Math.cos(Math.toRadians(-rotation[0]) - Math.PI);
        final double f1 = Math.sin(Math.toRadians(-rotation[0]) - Math.PI);
        final double f2 = -Math.cos(Math.toRadians(-rotation[1]));
        final double f3 = Math.sin(Math.toRadians(-rotation[1]));
        return new Vec3(f1 * f2, f3, f * f2);
    }
    public static float[] getRotationFromEyeHasPrev(double x, double y, double z) {
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().getThePlayer();
        double prevPosX = entityPlayerSP.getPrevPosX();
        double prevPosY = entityPlayerSP.getPrevPosY();
        double prevPosZ = entityPlayerSP.getPrevPosZ();
        double posX = entityPlayerSP.getPosX();
        double posY = entityPlayerSP.getPosY();
        double posZ = entityPlayerSP.getPosZ();
        AxisAlignedBB aabb = entityPlayerSP.getEntityBoundingBox();
        double xDiff = x - (prevPosX + (posX - prevPosX));
        double yDiff = y - ((prevPosY + (posY - prevPosY)) + (aabb.getMaxX() - aabb.getMinY()));
        double zDiff = z - (prevPosZ + (posZ - prevPosZ));
        final double dist = MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
        return new float[] {(float) ((Math.atan2(zDiff, xDiff) * 180D / Math.PI) - 90F), (float) -(Math.atan2(yDiff, dist) * 180D / Math.PI)};
    }
    public static double getRotationDifference(final Rotation rotation) {
        return serverRotation == null ? 0D : getRotationDifference(rotation, serverRotation);
    }
    public static Rotation faceBow(final Entity target, final boolean silent, final boolean predict, final float predictSize) {
        final EntityPlayerSP player = Minecraft.getMinecraft().getThePlayer();
        double targetPosX = target.getPosX();
        double targetPosZ = target.getPosZ();
        double playerPosX = player.getPosX();
        double playerPosZ = player.getPosZ();
        final double posX = targetPosX + (predict ? (targetPosX - target.getPrevPosX()) * predictSize : 0) - (playerPosX + (predict ? (playerPosX - player.getPrevPosX()) : 0));
        final double posY = target.getEntityBoundingBox().getMinY() + (predict ? (target.getEntityBoundingBox().getMinY() - target.getPrevPosY()) * predictSize : 0) + target.getEyeHeight() - 0.15 - (player.getEntityBoundingBox().getMinY() + (predict ? (player.getPosY() - player.getPrevPosY()) : 0)) - player.getEyeHeight();
        final double posZ = targetPosZ + (predict ? (targetPosZ - target.getPrevPosZ()) * predictSize : 0) - (playerPosZ + (predict ? (playerPosZ - player.getPrevPosZ()) : 0));
        final double posSqrt = Math.sqrt(posX * posX + posZ * posZ);
        float velocity = ModuleManager.getModule(FastBow.class).isEnable() ? 1F : player.getItemInUseDuration() / 20F;
        velocity = (velocity * velocity + velocity * 2) / 3;

        if (velocity > 1) velocity = 1;
        final Rotation rotation = new Rotation(
                (float) (Math.atan2(posZ, posX) * 180 / Math.PI) - 90,
                (float) -Math.toDegrees(Math.atan((velocity * velocity - Math.sqrt(velocity * velocity * velocity * velocity - 0.006F * (0.006F * (posSqrt * posSqrt) + 2 * posY * (velocity * velocity)))) / (0.006F * posSqrt)))
        );
        targetRotation = rotation;
        return rotation;
        /*if (silent)
            setTargetRotation(rotation);
        else
            limitAngleChange(new Rotation(player.getRotationYaw(), player.getRotationPitch()), rotation, 10 +
                    new Random().nextInt(6)).toPlayer(player);
         */
    }
    public static void setTargetRotation(final Rotation rotation, final int keepLength) {
        if (Double.isNaN(rotation.getYaw()) || Double.isNaN(rotation.getPitch())
                || rotation.getPitch() > 90 || rotation.getPitch() < -90)
            return;

        rotation.fixedSensitivity(Minecraft.getMinecraft().getGameSettings().getMouseSensitivity());
        targetRotation = rotation;
        RotationUtils.keepLength = keepLength;
    }


    public static void setTargetRotationReverse(final Rotation rotation, final int keepLength, final int revTick) {
        if(Double.isNaN(rotation.getYaw()) || Double.isNaN(rotation.getPitch())
                || rotation.getPitch() > 90 || rotation.getPitch() < -90)
            return;

        rotation.fixedSensitivity(mc.getGameSettings().getMouseSensitivity());
        targetRotation = rotation;
        RotationUtils.keepLength = keepLength;
//        RotationUtils.revTick = revTick+1;
    }

    /**
     * Set your target rotation
     *
     * @param rotation your target rotation
     */
    public static void setTargetRotation(final Rotation rotation) {
        setTargetRotation(rotation, 0);
    }

    public static boolean isValidRangeForNCP(final Entity entity, double range) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (entity.isNull() || thePlayer.isNull())
            return false;

        final Location dRef = new Location(entity.getPosX(), entity.getPosY(), entity.getPosZ());
        final Location pLoc = new Location(thePlayer.getPosX(), thePlayer.getPosY(), thePlayer.getPosZ());

        final double height = EntityLivingBase.isEntityLivingBase(entity) ? entity.getEyeHeight() : 1.75;

        // Refine y position.

        final double pY = pLoc.getY() + thePlayer.getEyeHeight();
        final double dY = dRef.getY();
        if (pY <= dY) ; // Keep the foot level y.
        else if (pY >= dY + height) dRef.setY(dY + height); // Highest ref y.
        else dRef.setY(pY); // Level with damaged.

        Vec3 temp = new Vec3(pLoc.getX(), pY, pLoc.getZ());
        final Vec3 pRel = dRef.toVector().subtract(temp); //

        // Distance is calculated from eye location to center of targeted. If the player is further away from their target
        // than allowed, the difference will be assigned to "distance".
        final double lenpRel = pRel.lengthVector();

        double violation = lenpRel - range;

        if (violation > 0) {
            return false;
        }

        return true;
    }

}
