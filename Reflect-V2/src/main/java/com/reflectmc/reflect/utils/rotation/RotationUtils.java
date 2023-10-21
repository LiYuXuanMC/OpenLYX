package com.reflectmc.reflect.utils.rotation;

import com.reflectmc.reflect.utils.MathHelper;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityLivingBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.AxisAlignedBB;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.Vec3;

import java.util.Random;

public class RotationUtils {
    private static Random random = new Random();
    private static double x = random.nextDouble();
    private static double y = random.nextDouble();
    private static double z = random.nextDouble();
    public static double getRotationDifference(final Entity entity) {
        final Rotation rotation = toRotation(getCenter(entity.getEntityBoundingBox()), true);
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        return getRotationDifference(rotation, new Rotation(thePlayer.getRotationYaw(), thePlayer.getRotationPitch()));
    }
    public static double getRotationDifference(final Rotation a, final Rotation b) {
        return Math.hypot(getAngleDifference(a.getYaw(), b.getYaw()), a.getPitch() - b.getPitch());
    }
    public static Vec3 getCenter(final AxisAlignedBB bb) {
        return new Vec3(bb.getMinX() + (bb.getMaxX() - bb.getMinX()) * 0.5, bb.getMinY() + (bb.getMaxY() - bb.getMinY()) * 0.5, bb.getMinZ() + (bb.getMaxZ() - bb.getMinZ()) * 0.5);
    }
    public static Rotation toRotation(final Vec3 vec, final boolean predict) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        final Vec3 eyesPos = new Vec3(thePlayer.getPosX(), thePlayer.getEntityBoundingBox().getMinY() +
                thePlayer.getEyeHeight(), thePlayer.getPosZ());

        if(predict) {
            if(thePlayer.isOnGround()) {
                eyesPos.addVector(thePlayer.getMotionX(), 0.0, thePlayer.getMotionZ());
            }else eyesPos.addVector(thePlayer.getMotionX(), thePlayer.getMotionY(), thePlayer.getMotionZ());
        }

        final double diffX = vec.getXCoord() - eyesPos.getXCoord();
        final double diffY = vec.getYCoord() - eyesPos.getYCoord();
        final double diffZ = vec.getZCoord() - eyesPos.getZCoord();

        return new Rotation(MathHelper.wrapAngleTo180_float(
                (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90F
        ), MathHelper.wrapAngleTo180_float(
                (float) (-Math.toDegrees(Math.atan2(diffY, Math.sqrt(diffX * diffX + diffZ * diffZ))))
        ));
    }
    public static float getAngleDifference(final float a, final float b) {
        return ((((a - b) % 360F) + 540F) % 360F) - 180F;
    }
    public static Rotation limitAngleChange(final Rotation currentRotation, final Rotation targetRotation, final float turnSpeed) {
        final float yawDifference = getAngleDifference(targetRotation.getYaw(), currentRotation.getYaw());
        final float pitchDifference = getAngleDifference(targetRotation.getPitch(), currentRotation.getPitch());

        return new Rotation(
                currentRotation.getYaw() + (yawDifference > turnSpeed ? turnSpeed : Math.max(yawDifference, -turnSpeed)),
                currentRotation.getPitch() + (pitchDifference > turnSpeed ? turnSpeed : Math.max(pitchDifference, -turnSpeed)
                ));
    }
    public static VecRotation searchCenter(final AxisAlignedBB bb, final boolean outborder, final boolean random, final boolean predict, final boolean throughWalls) {
        if(outborder) {
            final Vec3 vec3 = new Vec3(bb.getMinX() + (bb.getMaxX() - bb.getMinX()) * (x * 0.3 + 1.0), bb.getMinY() + (bb.getMaxY() - bb.getMinY()) * (y * 0.3 + 1.0), bb.getMinZ() + (bb.getMaxZ() - bb.getMinZ()) * (z * 0.3 + 1.0));
            return new VecRotation(vec3, toRotation(vec3, predict));
        }

        final Vec3 randomVec = new Vec3(bb.getMinX() + (bb.getMaxX() - bb.getMinX()) *(x * 0.8+0.2), bb.getMinY() + (bb.getMaxY() - bb.getMinY()) * (y * 0.8+0.2), bb.getMinZ() + (bb.getMaxZ() - bb.getMinZ()) * (z * 0.8+0.2));
        final Rotation randomRotation = toRotation(randomVec, predict);

        VecRotation vecRotation = null;

        for(double xSearch = 0.15D; xSearch < 0.85D; xSearch += 0.1D) {
            for (double ySearch = 0.15D; ySearch < 1D; ySearch += 0.1D) {
                for (double zSearch = 0.15D; zSearch < 0.85D; zSearch += 0.1D) {
                    final Vec3 vec3 = new Vec3(bb.getMinX() + (bb.getMaxX() - bb.getMinX()) * xSearch, bb.getMinY() + (bb.getMaxY() - bb.getMinY()) * ySearch, bb.getMinZ() + (bb.getMaxZ() - bb.getMinZ()) * zSearch);
                    final Rotation rotation = toRotation(vec3, predict);

                    if(throughWalls || isVisible(vec3)) {
                        final VecRotation currentVec = new VecRotation(vec3, rotation);

                        if (vecRotation == null || (getRotationDifference(currentVec.getRotation(), randomRotation) < getRotationDifference(vecRotation.getRotation(), randomRotation)))
                            vecRotation = currentVec;
                    }
                }
            }
        }

        return vecRotation;
    }
    public static boolean isVisible(final Vec3 vec3) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        final Vec3 eyesPos = new Vec3(thePlayer.getPosX(), thePlayer.getEntityBoundingBox().getMinY() + thePlayer.getEyeHeight(), thePlayer.getPosZ());

        return Minecraft.getMinecraft().getTheWorld().rayTraceBlocks(eyesPos, vec3).isNull();
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
}
