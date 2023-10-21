package al.logger.client.features.modules.impls.Combat;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.event.client.render.EventRender3D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.EntityValid;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityArmorStand;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.render.RenderManager;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.MathHelper;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;
import al.logger.client.wrapper.lwjgl.Mouse;

import java.awt.*;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AimAssist extends Module {

    private DoubleValue fov = new DoubleValue("FOV", 360.0, 0.0, 90.0, 1.0);
    private DoubleValue attachFov = new DoubleValue("AttachFov", 360, 0.0, 35.0, 1.0);
    private DoubleValue distance = new DoubleValue("Distance", 30.0, 15.0, 15.0, 1.0);
    private DoubleValue attachDistance = new DoubleValue("AttachDistance", 6.0, 3.0, 3.0, 0.1);
    private DoubleValue speed = new DoubleValue("Speed", 0.5, 0.001, 0.1, 0.001);
    private OptionValue onlyMouse = new OptionValue("OnlyMouse", true);
    private OptionValue clickAttack = new OptionValue("ClickAttack", false);
    private OptionValue jitter = new OptionValue("Jitter", true);
    private DoubleValue jitterMin = new DoubleValue("JitterMin", 0.2, -0.2, -0.1, 0.1);
    private DoubleValue jitterMax = new DoubleValue("JitterMax", 0.2, -0.2, 0.1, 0.1);

    private EntityValid entitySelect = new EntityValid(true, false, false, true,false);

    EntityLivingBase entity  = new EntityLivingBase(null);
    EntityLivingBase seletedEntity = new EntityLivingBase(null);
    TimerUtils timerUtil = new TimerUtils();
    public AimAssist() {
        super("AimAssist", Category.Combat);
        addValues(fov, attachFov, distance, attachDistance, speed, onlyMouse, clickAttack, jitter, jitterMin, jitterMax);
        addValues(entitySelect.getValues());
    }

    @Listener
    public void onRenderWorldLast(EventRender3D render3D) {
        if (mc.getThePlayer().isNull() || mc.getTheWorld().isNull()) return;
        EntityLivingBase entity = seletedEntity;
        RenderManager renderManager = mc.getRenderManager();
        if (entity.isNull())
            return;
        double lastTickPosX = entity.getLastTickPosX();
        double lastTickPosY = entity.getLastTickPosY();
        double lastTickPosZ = entity.getLastTickPosZ();
        double x = (lastTickPosX + (entity.getPosX() - lastTickPosX) * render3D.getPartialTicks() - renderManager.getViewerPosX());
        double y = (lastTickPosY + (entity.getPosY() - lastTickPosY) * render3D.getPartialTicks() - renderManager.getViewerPosY());
        double z = (lastTickPosZ + (entity.getPosZ() - lastTickPosZ) * render3D.getPartialTicks() - renderManager.getViewerPosZ());
        AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();

        double width = entityBoundingBox.getMaxX() - entityBoundingBox.getMinX() - 0.1;
        double height = entityBoundingBox.getMaxY() - entityBoundingBox.getMinY() + 0.1;
        Color color = new Color(155, 155, 200, 50);
        if (entity.getHurtTime() != 0) {
            color = new Color(255, 0, 0, 30);
        } else {
            color = new  Color(60, 100, 255, 30);
        }
        RenderUtil.drawEntityBoxESP(x, y, z, width * 0.7, height, color.getRGB(), 60 / 255f, 100 / 255f, 1f, 0.0f, 1f);
    }
    @Listener
    public void onClientTick(EventLivingUpdate event) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isNull() || mc.getTheWorld().isNull())
            return;
        seletedEntity = getTarget();
        if (seletedEntity.isNull()) {
            return;
        }
        float[] rotations = getRotationsNeeded(seletedEntity,thePlayer);
        if (rotations != null && mc.getCurrentScreen().isNull()) {
            if (clickAttack.getValue() && Mouse.isButtonDown(0) && attachDistance.getValue()
                    .floatValue() >= thePlayer.getDistanceToEntity(seletedEntity) && attachFov.getValue()
                    .floatValue() / 2 >= getDistanceMousePitch(seletedEntity) && timerUtil.delay(100f)){
                mc.getPlayerController().attackEntity(thePlayer, seletedEntity);
                thePlayer.swingItem();
                timerUtil.reset();
            }
            if (!mc.getObjectMouseOver().getEntityHit().equals(seletedEntity) && (!onlyMouse.getValue() || Mouse.isButtonDown(0))){
                thePlayer.setRotationYaw((float) smooth(rotations[0], thePlayer.getRotationYaw() +
                                (jitter.getValue() ? nextDouble(jitterMin.getValue(), jitterMax.getValue()) : 0.0), speed.getValue()));
                thePlayer.setRotationPitch((float) smooth(rotations[1], thePlayer.getRotationPitch() +
                                (jitter.getValue() ? nextDouble(jitterMin.getValue(), jitterMax.getValue()) : 0.0), speed.getValue()));
            }
        }
    }
    public double smooth(double endPoint,double current,double speed){
        boolean shouldContinueAnimation = endPoint > current;
        double dif = Math.max(endPoint, current) - Math.min(endPoint, current);
        double factor = dif * speed;
        return current + (shouldContinueAnimation ? factor : -factor);
    }
    public EntityLivingBase getTarget(){
        MovingObjectPosition objectMouseOver = mc.getObjectMouseOver();
        Entity entityHit = objectMouseOver.getEntityHit();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (!entityHit.isNull() && EntityLivingBase.isEntityLivingBase(entityHit)){
            entity = new EntityLivingBase(entityHit.getWrappedObject());
        }
        if (!entity.isNull()) {
            if (entityRule(entity, false)) {
                return entity;
            }
        }
        List<Entity> entities = mc.getTheWorld().getLoadedEntityList().stream().filter(EntityLivingBase::isEntityLivingBase).collect(Collectors.toList());
        entities.sort(Comparator.comparingDouble(pEntity -> pEntity.getDistanceSqToEntity(thePlayer)));
        for (Entity pEntitty : entities) {
            if (entityRule(new EntityLivingBase(pEntitty.getWrappedObject()), true)) {
                return new EntityLivingBase(pEntitty.getWrappedObject());
            }
        }
        return new EntityLivingBase(null);
    }

    public boolean entityRule(EntityLivingBase entity,boolean isDistance){
        if (entity.getDistanceSqToEntity(mc.getThePlayer()) > distance.getValue()){
            return false;
        }
        if (!entitySelect.check(entity,mc.getThePlayer())){
            return false;
        }
        if (isDistance){
            if (getDistanceMousePitch(entity) > fov.getValue() / 2) {
                return false;
            }
        }

        return true;
    }
    private double getDistanceMousePitch(EntityLivingBase entity){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        float[] neededRotations = getRotationsNeeded(entity,thePlayer);
        if (neededRotations != null) {
            float neededYaw = thePlayer.getRotationYaw() - neededRotations[0];
            float neededPitch = thePlayer.getRotationPitch() - neededRotations[1];
            float distanceFromMouse = MathHelper.sqrt_float(neededYaw * neededYaw + neededPitch * neededPitch * 2.0f);
            return distanceFromMouse;
        }
        return -1D;
    }
    private float[] getRotationsNeeded(Entity entity,EntityPlayerSP thePlayer){
        if (entity.isNull()) {
            return null;
        }
        double diffX = entity.getPosX() - thePlayer.getPosX();
        double diffZ = entity.getPosZ() - thePlayer.getPosZ();
        double diffY;
        if (EntityLivingBase.isEntityLivingBase(entity)) {
            EntityLivingBase entityLivingBase = new EntityLivingBase(entity.getWrappedObject());
            diffY = entityLivingBase.getPosY() + entityLivingBase.getEyeHeight() - (thePlayer.getPosY() + thePlayer.getEyeHeight());
        } else {
            AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
            diffY = (entityBoundingBox.getMinY() + entityBoundingBox.getMaxY()) / 2.0 - (thePlayer.getPosY() + thePlayer.getEyeHeight());
        }
        float dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float) ((Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793)) - 90.0f;
        float pitch = (float) -(Math.atan2(diffY, dist) * 180.0 / 3.141592653589793);
        return new float[]{
                thePlayer.getRotationYaw() + MathHelper.wrapAngleTo180_float(yaw - thePlayer.getRotationYaw()),
                thePlayer.getRotationPitch() + MathHelper.wrapAngleTo180_float(pitch - thePlayer.getRotationPitch())
        };
    }
    public double nextDouble(double min,double max){
        return max + (min - max) * new SecureRandom().nextDouble();
    }

}
