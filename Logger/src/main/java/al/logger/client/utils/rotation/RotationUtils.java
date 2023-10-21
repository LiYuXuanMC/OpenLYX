package al.logger.client.utils.rotation;

import al.logger.client.utils.math.RandomUtil;
import al.logger.client.utils.misc.MathHelper;
import al.logger.client.utils.player.RayCastUtil;
import al.logger.client.utils.player.Vector2f;
import al.logger.client.utils.player.Vector3d;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.utils.*;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectType;

import java.util.List;
import java.util.Random;


public class RotationUtils {
    private static Minecraft mc = Minecraft.getMinecraft();
    public static final float PI = (float) Math.PI;
    public static final float TO_DEGREES = 180.0F / PI;

    public static Rotation serverRotation = new Rotation(0F, 0F);
    public static float[] getRotations(BlockPos block, Enum face) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        final double x = block.getX() + 0.5 - thePlayer.getPosX() + EnumFacing.getFrontOffsetX(face) / 2.0;
        final double z = block.getZ() + 0.5 - thePlayer.getPosZ() + EnumFacing.getFrontOffsetX(face) / 2.0;
        double y = block.getY() + 0.5;
        final double d1 = y + thePlayer.getEyeHeight() - y;
        final double d2 = MathHelper.sqrt_double(x * x + z * z);
        float yaw = (float) (Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float) (Math.atan2(d1, d2) * 180.0 / 3.141592653589793);
        if (yaw < 0.0f) {
            yaw += 360.0f;
        }
        return new float[]{yaw, pitch};
    }
    public static float getGCD() {
        return (float) (Math.pow(mc.getGameSettings().getMouseSensitivity() * 0.6 + 0.2, 3) * 1.2);
    }

    public static Rotation getRotationFromEyeHasPrev(EntityLivingBase target) {
        final double x = (target.getPrevPosX() + (target.getPosX() - target.getPrevPosX()));
        final double y = (target.getPrevPosY() + (target.getPosY() - target.getPrevPosY()));
        final double z = (target.getPrevPosZ() + (target.getPosZ() - target.getPrevPosZ()));
        float[] floats = getRotationFromEyeHasPrev(x, y, z);
        return new Rotation(floats[0],floats[1]);
    }

    public static float[] getRotationsToPosition(double x, double y, double z, double targetX, double targetY, double targetZ) {
        double dx = targetX - x;
        double dy = targetY - y;
        double dz = targetZ - z;

        double horizontalDistance = Math.sqrt(dx * dx + dz * dz);

        float yaw = (float) Math.toDegrees(-Math.atan2(dx, dz));
        float pitch = (float) Math.toDegrees(-Math.atan2(dy, horizontalDistance));

        return new float[] {yaw, pitch};
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

    public static Vec3 getVectorForRotation(float[] rotation) {
        final double f = Math.cos(Math.toRadians(-rotation[0]) - Math.PI);
        final double f1 = Math.sin(Math.toRadians(-rotation[0]) - Math.PI);
        final double f2 = -Math.cos(Math.toRadians(-rotation[1]));
        final double f3 = Math.sin(Math.toRadians(-rotation[1]));
        return new Vec3(f1 * f2, f3, f * f2);
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
    public static float getSensitivityMultiplier() {
        float SENSITIVITY = mc.getGameSettings().getMouseSensitivity() * 0.6F + 0.2F;
        return (SENSITIVITY * SENSITIVITY * SENSITIVITY * 8.0F) * 0.15F;
    }
    public static float[] facePlayer(Entity e, boolean a3, boolean heuristics, boolean smooth, boolean prediction, boolean mouseFix, boolean bestVector, double inaccuracy, boolean clampYaw, float rotationSpeed, double range , boolean custom , int customRange) {
        final RandomUtil randomUtil = RandomUtil.getInstance();

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        AxisAlignedBB playerBoundingBox = thePlayer.getEntityBoundingBox();
        AxisAlignedBB entityBoundingBox = e.getEntityBoundingBox();

        final double eyeX = (playerBoundingBox.getMinX() + playerBoundingBox.getMaxX()) / 2;
        final double eyeY = playerBoundingBox.getMinY() + thePlayer.getEyeHeight();
        final double eyeZ = (playerBoundingBox.getMinZ() + playerBoundingBox.getMaxZ()) / 2;

        double x = e.getPosX() - eyeX;
        double y = e.getPosY() + e.getEyeHeight() - eyeY;
        double z = e.getPosZ() - eyeZ;

        if (bestVector) {
            final Vec3 bestVec = getBestVector(thePlayer.getPositionEyes(mc.getTimer().getRenderPartialTicks()), e.getEntityBoundingBox()).addVector(-inaccuracy / 10, -inaccuracy / 10, -inaccuracy / 10);
            x = bestVec.getXCoord() - eyeX;
            y = bestVec.getYCoord() - eyeY;
            z = bestVec.getZCoord() - eyeZ;
        }

        if (!(EntityLivingBase.isEntityLivingBase(e))) {
            y = (entityBoundingBox.getMinY() + entityBoundingBox.getMaxY()) / 2.0D - (entityBoundingBox.getMinY() + (double) thePlayer.getEyeHeight());
        }

        if (custom)
            y *= customRange * 0.02;

        if (heuristics) {
            final float randomPitch = (float) MathHelper.getRandomDoubleInRange(new Random(), 0.015, 0.018);
            float randomizedPitch = (float) MathHelper.getRandomDoubleInRange(new Random(), 0.010, randomPitch);
            float randomizedYaw = (float) MathHelper.getRandomDoubleInRange(new Random(), -0.1, -0.3);
            x += randomUtil.getRandomDouble(-randomizedPitch, randomizedPitch);
            z += randomUtil.getRandomDouble(-randomizedPitch, randomizedPitch);
            y += randomUtil.getRandomDouble(randomizedYaw, -0.01);
        }

        if (prediction) {
            boolean sprinting = e.isSprinting();
            boolean sprintingPlayer = thePlayer.isSprinting();

            float walkingSpeed = 0.10000000149011612f; //https://minecraft.fandom.com/wiki/Sprinting

            float sprintMultiplication = sprinting ? 1.25f : walkingSpeed;
            float sprintMultiplicationPlayer = sprintingPlayer ? 1.25f : walkingSpeed;

            float xMultiplication = (float) ((e.getPosX() - e.getPrevPosX()) * sprintMultiplication);
            float zMultiplication = (float) ((e.getPosZ() - e.getPrevPosZ()) * sprintMultiplication);

            float xMultiplicationPlayer = (float) ((thePlayer.getPosX() - thePlayer.getPrevPosX()) * sprintMultiplicationPlayer);
            float zMultiplicationPlayer = (float) ((thePlayer.getPosZ() - thePlayer.getPrevPosZ()) * sprintMultiplicationPlayer);


            if (xMultiplication != 0.0f && zMultiplication != 0.0f || xMultiplicationPlayer != 0.0f && zMultiplicationPlayer != 0.0f) {
                x += xMultiplication + xMultiplicationPlayer;
                z += zMultiplication + zMultiplicationPlayer;
            }
        }

        double angle = MathHelper.sqrt_double(x * x + z * z);

        float yawAngle = (float) (MathHelper.atan2(z, x) * 180.0D / Math.PI) - 90.0F;
        float pitchAngle = (float) (-(MathHelper.atan2(y, angle) * 180.0D / Math.PI));

        double rangeToEntity = thePlayer.getDistanceToEntity(e);
        double rangeSetting = range + 1;

        float playerYaw = thePlayer.getRotationYaw();
        float playerPitch = thePlayer.getRotationPitch();

        double rotationDelta = Math.hypot(playerYaw - yawAngle, playerPitch - pitchAngle);
        double speed = rotationDelta * ((rangeSetting - rangeToEntity) / rangeSetting);

        float yaw = clampYaw ? yawAngle : updateRotation(playerYaw, yawAngle, smooth ? MathHelper.abs((float) speed) : rotationSpeed);
        float pitch = updateRotation(playerPitch, pitchAngle, smooth ? MathHelper.abs((float) speed) : rotationSpeed);


        if (!mouseFix)
            return new float[]{yaw, clampPitch(pitch)};
        final float[] mouseSensitivity = applyMouseSensitivity(yaw, pitch, a3);

        return new float[]{mouseSensitivity[0], clampPitch(mouseSensitivity[1])};
    }
    public static Vec3 getBestVector(Vec3 look, AxisAlignedBB axisAlignedBB) {
        return new Vec3(MathHelper.clamp_double(look.getXCoord(), axisAlignedBB.getMinX(), axisAlignedBB.getMaxX()), MathHelper.clamp_double(look.getYCoord(), axisAlignedBB.getMinY(), axisAlignedBB.getMaxY()), MathHelper.clamp_double(look.getZCoord(), axisAlignedBB.getMinZ(), axisAlignedBB.getMaxZ()));
    }
    public static float updateRotation(float currentRotation, float nextRotation, float rotationSpeed) {
        float f = MathHelper.wrapAngleTo180_float(nextRotation - currentRotation);
        if (f > rotationSpeed) {
            f = rotationSpeed;
        }
        if (f < -rotationSpeed) {
            f = -rotationSpeed;
        }
        return currentRotation + f;
    }

    public static void setTargetRotation(final Rotation rotation) {
        setTargetRotation(rotation, 0);
    }
    public static Rotation getRotationsNonLivingEntity(Entity entity) {
        return RotationUtils.getRotations(entity.getPosX(), entity.getPosY() + (entity.getEntityBoundingBox().getMaxY() -entity.getEntityBoundingBox().getMinY())*0.5, entity.getPosZ());
    }
    public static Rotation getRotations(double posX, double posY, double posZ) {
        EntityPlayerSP player = mc.getThePlayer();
        double x = posX - player.getPosX();
        double y = posY - (player.getPosY() + (double)player.getEyeHeight());
        double z = posZ - player.getPosZ();
        double dist = MathHelper.sqrt_double(x * x + z * z);
        float yaw = (float)(Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f;
        float pitch = (float)(-(Math.atan2(y, dist) * 180.0 / 3.141592653589793));
        return new Rotation(yaw,pitch);
    }

    public static void setTargetRotation(final Rotation rotation, final int keepLength) {
        if(Double.isNaN(rotation.getYaw()) || Double.isNaN(rotation.getPitch())
                || rotation.getPitch() > 90 || rotation.getPitch() < -90)
            return;

        rotation.fixedSensitivity(mc.getGameSettings().getMouseSensitivity());
    }
    public static float[] applyMouseSensitivity(float yaw, float pitch, boolean a3) {
        float sensitivity = Minecraft.getMinecraft().getGameSettings().getMouseSensitivity();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (sensitivity == 0) {
            sensitivity = 0.0070422534F; //1% Sensitivity <- to fix 0.0 sensitivity
        }
        sensitivity = Math.max(0.1F, sensitivity);
        float playerYaw = thePlayer.getRotationYaw();
        float playerPitch = thePlayer.getRotationPitch();
        int deltaYaw = (int) ((yaw - playerYaw) / (sensitivity / 2));
        int deltaPitch = (int) ((pitch - playerPitch) / (sensitivity / 2)) * -1;

        if (a3) {
            deltaYaw -= deltaYaw % 0.5 + 0.25;
            deltaPitch -= deltaPitch % 0.5 + 0.25;
        }
        float f = sensitivity * 0.6F + 0.2F;
        float f1 = (float) (Math.pow(f, 3.0) * 8F);
        float f2 = (float) deltaYaw * f1;
        float f3 = (float) deltaPitch * f1;

        float endYaw = (float) ((double) playerYaw + (double) f2 * 0.15);
        float endPitch = (float) ((double) playerPitch - (double) f3 * 0.15);
        return new float[]{endYaw, endPitch};
    }
    public static float clampPitch(float pitch) {
        return MathHelper.clamp_float(pitch, -90, 90);
    }

    public static float smoothRotation(float from, float to, float speed) {
        float f = MathHelper.wrapAngleTo180_float(to - from);

        if (f > speed) {
            f = speed;
        }

        if (f < -speed) {
            f = -speed;
        }

        return from + f;
    }
    public static float[] getAngles(EntityLivingBase entity) {
        if (entity == null) return null;
        final EntityPlayerSP player = mc.getThePlayer();

        final double diffX = entity.getPosX() - player.getPosX(),
                diffY = entity.getPosY() + entity.getEyeHeight() * 0.9 - (player.getPosY() + player.getEyeHeight()),
                diffZ = entity.getPosZ() - player.getPosZ(), dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ); // @on

        final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F,
                pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);

        return new float[]{player.getRotationYaw() + MathHelper.wrapAngleTo180_float(
                yaw - player.getRotationYaw()), player.getRotationPitch() + MathHelper.wrapAngleTo180_float(pitch - player.getRotationPitch())};
    }

    public static float[] getAngles(BlockPos pos) {
        double posX = pos.getX(), posY = pos.getY(), posZ = pos.getZ();
        final EntityPlayerSP player = mc.getThePlayer();

        final double diffX = posX - player.getPosX(),
                diffY = posY - (player.getPosY() + player.getEyeHeight()),
                diffZ = posZ - player.getPosZ(), dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ); // @on

        final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F,
                pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);

        return new float[]{player.getRotationYaw() + MathHelper.wrapAngleTo180_float(
                yaw - player.getRotationYaw()), player.getRotationPitch() + MathHelper.wrapAngleTo180_float(pitch - player.getRotationPitch())};
    }

    public static float[] getAngles(Vec3 vector) {
        double posX = vector.getXCoord(), posY = vector.getYCoord(), posZ = vector.getZCoord();
        final EntityPlayerSP player = mc.getThePlayer();

        final double diffX = posX - player.getPosX(),
                diffY = posY - (player.getPosY() + player.getEyeHeight()),
                diffZ = posZ - player.getPosZ(), dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ); // @on

        final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F,
                pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);

        return new float[]{player.getRotationYaw() + MathHelper.wrapAngleTo180_float(
                yaw - player.getRotationYaw()), player.getRotationPitch() + MathHelper.wrapAngleTo180_float(pitch - player.getRotationPitch())};
    }
    /**
     * 获取实体的旋转
     *
     * @param entity
     * @return
     */
    public static Float[] getEntityHeadPosition(EntityLivingBase entity) {
        return getEntityHeadPosition(entity, Minecraft.getMinecraft().getThePlayer().getRotationYaw(), Minecraft.getMinecraft().getThePlayer().getRotationPitch());
    }

    /**
     * 获取实体的旋转
     *
     * @param entity
     * @return
     */
    public static Float[] getEntityHeadPosition(EntityLivingBase entity, double Yaw,double Pitch) {
        float HeadPosX = (float) (entity.getPosX() - Minecraft.getMinecraft().getThePlayer().getPosX());
        float HeadPosZ = (float) (entity.getPosZ() - Minecraft.getMinecraft().getThePlayer().getPosZ());
        float HeadPosY = (float) (entity.getPosY() + entity.getEyeHeight() - (Minecraft.getMinecraft().getThePlayer().getEntityBoundingBox().getMinY() + Minecraft.getMinecraft().getThePlayer().getEyeHeight()));
        double distance = Math.sqrt(HeadPosX * HeadPosX + HeadPosZ * HeadPosZ);
        float yaw = (float) (Math.atan2(HeadPosZ, HeadPosX) * 180.0D / Math.PI) - 90.0F;
        float pitch = (float) -(Math.atan2(HeadPosY, distance) * 180.0D / Math.PI);
        float f = (float) (Yaw + MathHelper.wrapDegrees(yaw - Yaw));
        float f1 = (float) (Pitch + MathHelper.wrapDegrees(pitch - Pitch));
        return new Float[]{f, f1};
    }


    /**
     * 获取视角差
     *
     * @param yaw
     * @param yaw2
     * @return
     */
    public static float getFovParallax(float yaw, float yaw2) {
        //因为yaw叠加和叠减所以要取绝对值
        float f = Math.abs(yaw % 360.0F - yaw2 % 360.0F);
        //如果大于180度就取360度减去这个值
        if (f > 180.0F) {
            f = 360.0F - f;
        }
        //最终得到的值就是视角差
        return f;
    }
    public static Vector2f calculate(final Entity entity) {
        return calculate(entity.getCustomPositionVector().add(0, Math.max(0, Math.min(mc.getThePlayer().getPosY() - entity.getPosY() +
                mc.getThePlayer().getEyeHeight(), (entity.getEntityBoundingBox().getMaxY() - entity.getEntityBoundingBox().getMinY()) * 0.9)), 0));
    }
    public static Vector2f calculate(final Vec3 to, final Enum enumFacing) {
        return calculate(new Vector3d(to.getXCoord(), to.getYCoord(), to.getZCoord()), enumFacing);
    }
    public static Vector2f calculate(final Entity entity, final boolean adaptive, final double range) {
        Vector2f normalRotations = calculate(entity);
        if (!adaptive || RayCastUtil.rayCast(normalRotations, range).getTypeOfHit() == MovingObjectType.ENTITY) {
            return normalRotations;
        }

        for (double yPercent = 1; yPercent >= 0; yPercent -= 0.25) {
            for (double xPercent = 1; xPercent >= -0.5; xPercent -= 0.5) {
                for (double zPercent = 1; zPercent >= -0.5; zPercent -= 0.5) {
                    Vector2f adaptiveRotations = calculate(entity.getCustomPositionVector().add(
                            (entity.getEntityBoundingBox().getMaxX() - entity.getEntityBoundingBox().getMinX()) * xPercent,
                            (entity.getEntityBoundingBox().getMaxY() - entity.getEntityBoundingBox().getMinY()) * yPercent,
                            (entity.getEntityBoundingBox().getMaxZ() - entity.getEntityBoundingBox().getMinZ()) * zPercent));

                    if (RayCastUtil.rayCast(adaptiveRotations, range).getTypeOfHit() == MovingObjectType.ENTITY) {
                        return adaptiveRotations;
                    }
                }
            }
        }

        return normalRotations;
    }

    public static Vector2f calculate(final Vec3 to) {
        return calculate(mc.getThePlayer().getCustomPositionVector().add(0, mc.getThePlayer().getEyeHeight(), 0), new Vector3d(to.getXCoord(), to.getYCoord(), to.getZCoord()));
    }

    public static Vector2f calculate(final Vector3d to) {
        return calculate(mc.getThePlayer().getCustomPositionVector().add(0, mc.getThePlayer().getEyeHeight(), 0), to);
    }

    public static Vector2f calculate(final Vector3d position, final Enum enumFacing) {
        double x = position.getX() + 0.5D;
        double y = position.getY() + 0.5D;
        double z = position.getZ() + 0.5D;

        x += (double) EnumFacing.getDirectionVec(enumFacing).getX() * 0.5D;
        y += (double) EnumFacing.getDirectionVec(enumFacing).getY() * 0.5D;
        z += (double) EnumFacing.getDirectionVec(enumFacing).getZ() * 0.5D;
        return calculate(new Vector3d(x, y, z));
    }
    public static Vector2f calculate(final Vector3d from, final Vector3d to) {
        final Vector3d diff = to.subtract(from);
        final double distance = Math.hypot(diff.getX(), diff.getZ());
        final float yaw = (float) (MathHelper.atan2(diff.getZ(), diff.getX()) * TO_DEGREES) - 90.0F;
        final float pitch = (float) (-(MathHelper.atan2(diff.getY(), distance) * TO_DEGREES));
        return new Vector2f(yaw, pitch);
    }
}
