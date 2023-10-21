package al.logger.client.utils.player;

import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.utils.rotation.RotationUtils;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityItemFrame;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.utils.*;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectType;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;


import java.util.List;

public final class RayCastUtil {
    public static boolean inView(final Entity entity) {
        Minecraft mc = Minecraft.getMinecraft();
        int renderDistance = 16 * mc.getGameSettings().getRenderDistanceChunks();
        double threadDistance = mc.getThePlayer().getDistanceSqToEntity(entity);
        if (threadDistance > 100 || !(EntityPlayer.isEntityPlayer(entity))) {
            Vector2f rotations = RotationUtils.calculate(entity);

            if (Math.abs(MathHelper.wrapAngleTo180_float(rotations.x) - MathHelper.wrapAngleTo180_float(mc.getThePlayer().getRotationYaw())) > mc.getGameSettings().getFovSetting()) {
                return false;
            }

            MovingObjectPosition movingObjectPosition = RayCastUtil.rayCast(rotations, renderDistance, 0.2f);
            return movingObjectPosition != null && movingObjectPosition.getTypeOfHit() == MovingObjectType.ENTITY;
        } else {
            for (double yPercent = 1; yPercent >= -1; yPercent -= 0.5) {
                for (double xPercent = 1; xPercent >= -1; xPercent -= 1) {
                    for (double zPercent = 1; zPercent >= -1; zPercent -= 1) {
                        Vector2f subRotations = RotationUtils.calculate(entity.getCustomPositionVector().add(
                                (entity.getEntityBoundingBox().getMaxX() - entity.getEntityBoundingBox().getMinX()) * xPercent,
                                (entity.getEntityBoundingBox().getMaxY() - entity.getEntityBoundingBox().getMinY()) * yPercent,
                                (entity.getEntityBoundingBox().getMaxZ() - entity.getEntityBoundingBox().getMinZ()) * zPercent));

                        MovingObjectPosition movingObjectPosition = RayCastUtil.rayCast(subRotations, renderDistance, 0.2f);
                        if (movingObjectPosition != null && movingObjectPosition.getTypeOfHit() == MovingObjectType.ENTITY) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
    public static MovingObjectPosition rayCast(final Vector2f rotation, final double range) {
        return rayCast(rotation, range, 0);
    }

    public static MovingObjectPosition rayCast(final Vector2f rotation, final double range, final float expand) {
        Minecraft mc = Minecraft.getMinecraft();
        return rayCast(rotation, range, expand, mc.getThePlayer());
    }

    public static MovingObjectPosition rayCast(final Vector2f rotation, final double range, final float expand, Entity entity) {
        Minecraft mc = Minecraft.getMinecraft();
        final float partialTicks = mc.getTimer().getRenderPartialTicks();
        MovingObjectPosition objectMouseOver;

        if (!entity.isNull() && !mc.getTheWorld().isNull()) {
            objectMouseOver = entity.rayTraceCustom(range, rotation.x, rotation.y);
            double d1 = range;
            final Vec3 vec3 = entity.getPositionEyes(partialTicks);

            if (!objectMouseOver.isNull()) {
                d1 = objectMouseOver.getHitVec().distanceTo(vec3);
            }

            final Vec3 vec31 = mc.getThePlayer().getVectorForRotation(rotation.y, rotation.x);
            final Vec3 vec32 = vec3.addVector(vec31.getXCoord() * range, vec31.getYCoord() * range, vec31.getZCoord() * range);
            Entity pointedEntity = null;
            Vec3 vec33 = null;
            final float f = 1.0F;
            final List<Entity> list = mc.getTheWorld().getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().addCoord(vec31.getXCoord() * range, vec31.getYCoord() * range, vec31.getZCoord() * range).expand(f, f, f), EntitySelectors.getNOT_SPECTATING());
            list.removeIf(entity1 -> !entity1.canBeCollidedWith());
            double d2 = d1;

            for (final Entity entity1 : list) {
                final float f1 = entity1.getCollisionBorderSize() + expand;
                final AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f1, f1, f1);
                final MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);

                if (axisalignedbb.isVecInside(vec3)) {
                    if (d2 >= 0.0D) {
                        pointedEntity = entity1;
                        vec33 = movingobjectposition.isNull() ? vec3 : movingobjectposition.getHitVec();
                        d2 = 0.0D;
                    }
                } else if (!movingobjectposition.isNull()) {
                    final double d3 = vec3.distanceTo(movingobjectposition.getHitVec());

                    if (d3 < d2 || d2 == 0.0D) {
                        pointedEntity = entity1;
                        vec33 = movingobjectposition.getHitVec();
                        d2 = d3;
                    }
                }
            }

            if (pointedEntity != null && (d2 < d1 || objectMouseOver.isNull())) {
                objectMouseOver = new MovingObjectPosition(pointedEntity, vec33);
            }

            return objectMouseOver;
        }

        return null;
    }
    public static boolean overBlock(final EventPreUpdate rotation, final Enum enumFacing, final BlockPos pos, final boolean strict) {
        Minecraft mc = Minecraft.getMinecraft();
        final MovingObjectPosition movingObjectPosition = mc.getThePlayer().rayTraceCustom(4.5f, rotation.getYaw(), rotation.getPitch());

        if (movingObjectPosition.isNull()) return false;

        final Vec3 hitVec = movingObjectPosition.getHitVec();
        if (hitVec.isNull()) return false;

        return movingObjectPosition.getBlockPos().equals(pos) && (!strict || movingObjectPosition.getSideHit().getWrappedObject() == enumFacing);
    }

    public static boolean overBlock(final Vector2f rotation, final Enum enumFacing, final BlockPos pos, final boolean strict) {
        Minecraft mc = Minecraft.getMinecraft();
        final MovingObjectPosition movingObjectPosition = mc.getThePlayer().rayTraceCustom(4.5f, rotation.x, rotation.y);

        if (movingObjectPosition.isNull()) return false;

        final Vec3 hitVec = movingObjectPosition.getHitVec();
        if (hitVec.isNull()) return false;

        return movingObjectPosition.getBlockPos().equals(pos) && (!strict || movingObjectPosition.getSideHit().getWrappedObject() == enumFacing);
    }

    public static boolean overBlock(final Enum enumFacing, final BlockPos pos, final boolean strict) {
        Minecraft mc = Minecraft.getMinecraft();
        final MovingObjectPosition movingObjectPosition = mc.getObjectMouseOver();

        if (movingObjectPosition.isNull()) return false;

        final Vec3 hitVec = movingObjectPosition.getHitVec();
        if (hitVec.isNull()) return false;

        return movingObjectPosition.getBlockPos().equals(pos) && (!strict || movingObjectPosition.getSideHit().getWrappedObject() == enumFacing);
    }

    public static Boolean overBlock(final Vector2f rotation, final BlockPos pos) {
        return overBlock(rotation, EnumFacing.UP, pos, false);
    }

    public static Boolean overBlock(final Vector2f rotation, final BlockPos pos, final Enum enumFacing) {
        return overBlock(rotation, enumFacing, pos, true);
    }

    public static boolean isMouseOver(final float yaw, final float pitch, final Entity target, final float range){
    Minecraft mc = Minecraft.getMinecraft();
    final float partialTicks = mc.getTimer().getRenderPartialTicks();
        final Entity entity = mc.getRenderViewEntity();
        MovingObjectPosition objectMouseOver;
        Entity mcPointedEntity = null;

        if (entity != null && mc.getTheWorld() != null) {

            mc.getMcProfiler().startSection("pick");
            final double d0 = mc.getPlayerController().getBlockReachDistance();
            objectMouseOver = entity.rayTrace(d0, partialTicks);
            double d1 = d0;
            final Vec3 vec3 = entity.getPositionEyes(partialTicks);
            final boolean flag = d0 > (double) range;

            if (objectMouseOver != null) {
                d1 = objectMouseOver.getHitVec().distanceTo(vec3);
            }

            final Vec3 vec31 = mc.getThePlayer().getVectorForRotation(pitch, yaw);
            final Vec3 vec32 = vec3.addVector(vec31.getXCoord() * d0, vec31.getYCoord() * d0, vec31.getZCoord() * d0);
            Entity pointedEntity = null;
            Vec3 vec33 = null;
            final float f = 1.0F;
            List<Entity> list;
            list = mc.getTheWorld().getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().addCoord(vec31.getXCoord() * d0, vec31.getYCoord() * d0, vec31.getZCoord() * d0).expand(f, f, f),null);
            list.removeIf(entity1 -> !entity1.canBeCollidedWith());
            double d2 = d1;

            for (final Entity entity1 : list) {
                final float f1 = entity1.getCollisionBorderSize();
                final AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f1, f1, f1);
                final MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);

                if (axisalignedbb.isVecInside(vec3)) {
                    if (d2 >= 0.0D) {
                        pointedEntity = entity1;
                        vec33 = movingobjectposition == null ? vec3 : movingobjectposition.getHitVec();
                        d2 = 0.0D;
                    }
                } else if (movingobjectposition != null) {
                    final double d3 = vec3.distanceTo(movingobjectposition.getHitVec());

                    if (d3 < d2 || d2 == 0.0D) {
                        pointedEntity = entity1;
                        vec33 = movingobjectposition.getHitVec();
                        d2 = d3;
                    }
                }
            }

            if (pointedEntity != null && flag && vec3.distanceTo(vec33) > (double) range) {
                pointedEntity = null;
                objectMouseOver = new MovingObjectPosition(MovingObjectType.MISS, vec33, null, new BlockPos(vec33));
            }

            if (pointedEntity != null && (d2 < d1 || objectMouseOver == null)) {
                if (EntityLivingBase.isEntityLivingBase(pointedEntity) || EntityItemFrame.isEntityItemFrame(pointedEntity)) {
                    mcPointedEntity = pointedEntity;
                }
            }

            mc.getMcProfiler().endSection();

            return mcPointedEntity == target;
        }

        return false;
    }
}