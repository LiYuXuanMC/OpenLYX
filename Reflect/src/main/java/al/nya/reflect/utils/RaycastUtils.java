package al.nya.reflect.utils;

import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import lombok.val;

public class RaycastUtils {
    public static Entity raycastEntity(double range,EntityFilter entityFilter){
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        return raycastEntity(range,thePlayer.getRotationYaw(),thePlayer.getRotationPitch(),entityFilter);
    }
    private static Entity raycastEntity(double range, float yaw, float pitch,EntityFilter entityFilter){
        Minecraft mc = Minecraft.getMinecraft();
        Entity renderViewEntity = mc.getRenderViewEntity();

        if (!renderViewEntity.isNull() && !mc.getTheWorld().isNull()) {
            double blockReachDistance = range;
            val eyePosition = renderViewEntity.getPositionEyes(1f);

            val yawCos = Math.cos(-yaw * 0.017453292f - (float) Math.PI);
            val yawSin = Math.sin(-yaw * 0.017453292f - (float) Math.PI);
            val pitchCos = (-Math.cos(-pitch * 0.017453292f));
            val pitchSin = Math.sin(-pitch * 0.017453292f);

            val entityLook = new Vec3((yawSin * pitchCos), pitchSin, (yawCos * pitchCos));
            val vector = eyePosition.addVector(entityLook.getXCoord() * blockReachDistance, entityLook.getYCoord() * blockReachDistance, entityLook.getZCoord() * blockReachDistance);
            val entityList = mc.getTheWorld().getEntitiesInAABBexcluding(renderViewEntity, renderViewEntity.getEntityBoundingBox().addCoord(entityLook.getXCoord() * blockReachDistance, entityLook.getYCoord() * blockReachDistance, entityLook.getZCoord() * blockReachDistance).expand(1.0, 1.0, 1.0),null);
            entityList.removeIf(entity -> {
                return entity.isNull() || (EntityPlayer.isEntityPlayer(entity) && new EntityPlayer(entity.getWrapObject()).isSpectator()) || !entity.canBeCollidedWith();
            });
            Entity pointedEntity = new Entity(null);

            for (Entity entity : entityList) {
                if (!entityFilter.canRaycast(entity))
                    continue;
                double collisionBorderSize = entity.getCollisionBorderSize();
                val axisAlignedBB = entity.getEntityBoundingBox().expand(collisionBorderSize, collisionBorderSize, collisionBorderSize);

                val movingObjectPosition = axisAlignedBB.calculateIntercept(eyePosition, vector);

                if (axisAlignedBB.isVecInside(eyePosition)) {
                    if (blockReachDistance >= 0.0) {
                        pointedEntity = entity;
                        blockReachDistance = 0.0;
                    }
                } else if (!movingObjectPosition.isNull()) {
                    val eyeDistance = eyePosition.distanceTo(movingObjectPosition.getHitVec());

                    if (eyeDistance < blockReachDistance || blockReachDistance == 0.0) {
                        if (entity.equals(renderViewEntity.getRidingEntity()) && !renderViewEntity.canRiderInteract()) {
                            if (blockReachDistance == 0.0)
                                pointedEntity = entity;
                        } else {
                            pointedEntity = entity;
                            blockReachDistance = eyeDistance;
                        }
                    }
                }
            }

            return pointedEntity;
        }

        return new Entity(null);
    }
    public interface EntityFilter {
        boolean canRaycast(Entity entity);
    }
}
