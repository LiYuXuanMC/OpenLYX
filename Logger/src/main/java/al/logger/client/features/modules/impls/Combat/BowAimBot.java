package al.logger.client.features.modules.impls.Combat;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.EntityValid;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.math.FormulaHelper;
import al.logger.client.utils.rotation.RotationUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.block.BlockLiquid;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.item.Item;
import al.logger.client.wrapper.LoggerMC.item.ItemBow;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.MathHelper;
import al.logger.client.wrapper.LoggerMC.utils.Vec3;
import al.logger.client.wrapper.LoggerMC.world.WorldClient;

import java.util.Comparator;
import java.util.stream.Stream;

public class BowAimBot extends Module {
    private final DoubleValue range = new DoubleValue("Range", 120, 40, 50 , 1);
    private final DoubleValue fov = new DoubleValue("FOV", 360f, 0f, 360f , 1f);
    private final OptionValue slient = new OptionValue("Slient", true);
    private final OptionValue auto = new OptionValue("Auto Release", true);
    private final OptionValue moveFix = new OptionValue("Move Fix", false);
    private final OptionValue clamp = new OptionValue("Clamp", false);
    private final ModeValue mode = new ModeValue("Priority", Mode.FOV,Mode.values());
    private final OptionValue prediction = new OptionValue("Prediction", false);
    private final EntityValid valid = new EntityValid(true, true, false, false, true);

    private Entity curEntity = new Entity(null);
    private float yaw, pitch;
    private final TimerUtils timeHelper = new TimerUtils();
    public BowAimBot() {
        super("BowAimBot",Category.Combat);
        addValues(range,fov,slient,auto,moveFix,clamp,mode,prediction);
        addValues(valid.getValues());
    }
    @Listener
    public void onPreUpdate(EventPreUpdate preUpdate){
        if (slient.getValue() && allowAiming(mc.getThePlayer())) {
            if (!Float.isNaN(pitch) && !Float.isNaN(yaw)) {
                preUpdate.setRotation(yaw,pitch);
            }
        }
    }
    @Listener
    public void onUpdate(EventLivingUpdate update){
        Entity entity = getClosestEntity();
        curEntity = entity;
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (isUsing(thePlayer)) {
            if (!entity.isNull() && !EntityPlayerSP.isEntityPlayerSP(entity)) {
                final double deltaX = entity.getPosX() - thePlayer.getPosX();
                double deltaY = (entity.getPosY() + entity.getEyeHeight()) - (thePlayer.getPosY() + thePlayer.getEyeHeight());
                final double deltaZ = entity.getPosZ() - thePlayer.getPosZ();

                if (!(EntityPlayer.isEntityPlayer(entity)))
                    deltaY = (entity.getPosY() + entity.getEyeHeight()) - (thePlayer.getPosY() + thePlayer.getEyeHeight());

                final double x = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ); //distance
                final double v = getVelocity();
                final double g = getGravity();

                float pitch = FormulaHelper.getProjectileMotion(v, g, x, deltaY);
                float[] rotations = RotationUtils.facePlayer(entity, false, false, false, prediction.getValue(), true, false, 0, clamp.getValue(), 180, 6, false, 0);
                pitch = MathHelper.clamp_float(pitch, -90, 90);

                this.yaw = rotations[0];
                this.pitch = pitch;

                if (v == 1F && auto.getValue()) {
                    if (timeHelper.hasReached(200))
                        mc.getPlayerController().onStoppedUsingItem(thePlayer);
                } else if (auto.getValue()) {
                    timeHelper.reset();
                }

            }
        }
        if (!slient.getValue() && allowAiming(thePlayer)) {
            if (!Float.isNaN(pitch)) {
                thePlayer.setRotationPitch(pitch);
            }
            if (!Float.isNaN(yaw)){
                thePlayer.setRotationYaw(yaw);
            }
        }
    }
    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }
    private boolean canAttack(EntityLivingBase target) {
        if (!valid.check(target, mc.getThePlayer())) return false;
        if (!isInFOV(target, fov.getValue())) return false;

        return  mc.getThePlayer().getDistanceToEntity(target) <= range.getValue().floatValue();
    }

    private boolean isInFOV(EntityLivingBase entity, double angle) {
        angle *= .5D;
        double angleDiff = getAngleDifference(mc.getThePlayer().getRotationYaw(), getRotations(entity.getPosX(), entity.getPosY(), entity.getPosZ())[0]);
        return (angleDiff > 0 && angleDiff < angle) || (-angle < angleDiff && angleDiff < 0);
    }

    private static float getAngleDifference(float dir, float yaw) {
        float f = Math.abs(yaw - dir) % 360F;
        return f > 180F ? 360F - f : f;
    }

    private float[] getRotations(double x, double y, double z) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        double diffX = x + .5D - thePlayer.getPosX();
        double diffY = (y + .5D) / 2D - (thePlayer.getPosY() + thePlayer.getEyeHeight());
        double diffZ = z + .5D - thePlayer.getPosZ();

        double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float) (Math.atan2(diffZ, diffX) * 180D / Math.PI) - 90F;
        float pitch = (float) -(Math.atan2(diffY, dist) * 180D / Math.PI);

        return new float[]{yaw, pitch};
    }

    private float[] getRotations(final Entity entity) {
        if (entity.isNull()) {
            return null;
        }
        EntityPlayerSP thePlayer = mc.getThePlayer();
        final double diffX = entity.getPosX() - thePlayer.getPosX();
        final double diffZ = entity.getPosZ() - thePlayer.getPosZ();
        double diffY;
        if (EntityLivingBase.isEntityLivingBase(entity)) {
            final EntityLivingBase elb = new EntityLivingBase(entity.getWrappedObject());
            diffY = elb.getPosY() + (elb.getEyeHeight()) - (thePlayer.getPosY() + thePlayer.getEyeHeight());
        } else {
            AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
            diffY = (entityBoundingBox.getMinY() + entityBoundingBox.getMaxY()) / 2.0 - (thePlayer.getPosY() + thePlayer.getEyeHeight());
        }
        final double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0 / Math.PI) - 90.0f;
        final float pitch = (float) (-(Math.atan2(diffY, dist) * 180.0 / Math.PI));
        return new float[]{yaw, pitch};
    }

    private static float getDistanceBetweenAngles(float angle1, float angle2) {
        float angle3 = Math.abs((angle1 - angle2)) % 360.0f;
        if (angle3 > 180.0f) {
            angle3 = 0.0f;
        }
        return angle3;
    }

    private double getVelocity() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        int i = thePlayer.getCurrentEquippedItem().getMaxItemUseDuration() - thePlayer.getItemInUseCount();

        float f = (float) i / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    private boolean allowAiming(EntityPlayer player) {
        return isUsing(player) && !curEntity.isNull();
    }

    private boolean isUsing(EntityPlayer player) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        ItemStack item = thePlayer.getCurrentEquippedItem();
        return player.isUsingItem() && !item.isNull() && ItemBow.isItemBow(item.getItem());
    }

    private double getGravity() {
        return 0.006;
    }

    private float simulateArrow(Entity entity) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        WorldClient theWorld = mc.getTheWorld();
        float ticksInAir = 0;
        final Vec3 eyePosition = thePlayer.getPositionEyes(1F);
        float motionX, motionY, motionZ, posX = (float) eyePosition.getXCoord(), posY = (float) eyePosition.getYCoord(), posZ = (float) eyePosition.getZCoord();
        posX -= (double) (MathHelper.cos(yaw / 180.0F * (float) Math.PI) * 0.16F);
        posY -= 0.10000000149011612D;
        posZ -= (double) (MathHelper.sin(yaw / 180.0F * (float) Math.PI) * 0.16F);
        motionX = (-MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI));
        motionZ = (MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI));
        motionY = -MathHelper.sin(pitch / 180.0F * (float) Math.PI);

        do {
            ticksInAir++;
            float f4 = 0.99F;
            final float f6 = 0.05F;
            final Block block = theWorld.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
            if (BlockLiquid.isBlockLiquid(block)) {
                f4 = 0.6F;
            }
            posX += motionX;
            posY += motionY;
            posZ += motionZ;

            motionX *= (double) f4;
            motionY *= (double) f4;
            motionZ *= (double) f4;
            motionY -= (double) f6;

            if (entity.getDistance(posX, posY, posZ) <= 1)
                break;

        } while (thePlayer.getDistance(posX, posY, posZ) <= thePlayer.getDistanceToEntity(entity));
        return ticksInAir;
    }

    //取实体
    private EntityLivingBase getClosestEntity() {
        Stream<EntityLivingBase> stream = mc.getTheWorld().getLoadedEntityList().stream()
                .filter(EntityLivingBase::isEntityLivingBase)
                .map(entity -> new EntityLivingBase(entity.getWrappedObject()))
                .filter(this::canAttack);
        EntityPlayerSP thePlayer = mc.getThePlayer();
        float rotationYaw = thePlayer.getRotationYaw();
        float rotationPitch = thePlayer.getRotationPitch();
        if (mode.getValue() == Mode.Armor) {
            stream = stream.sorted(Comparator.comparingInt(o -> ((EntityPlayer.isEntityPlayer(o) ? new EntityPlayer(o.getWrappedObject()).getInventory().getTotalArmorValue() : (int) o.getHealth()))));
        } else if (mode.getValue() == Mode.Range) {
            stream = stream.sorted((o1, o2) -> (int) (o1.getDistanceToEntity(thePlayer) - o2.getDistanceToEntity(thePlayer)));
        } else if (mode.getValue() == Mode.FOV) {
            stream = stream.sorted(Comparator.comparingDouble(o -> getDistanceBetweenAngles(rotationPitch, getRotations(o)[0])));
        } else if (mode.getValue() == Mode.Angle) {
            stream = stream.sorted((o1, o2) -> {
                float[] rot1 = getRotations(o1);
                float[] rot2 = getRotations(o2);
                return (int) (rotationYaw - rot1[1] - (rotationYaw - rot2[1]));
            });
        } else if (mode.getValue() == Mode.Health) {
            stream = stream.sorted((o1, o2) -> (int) (o1.getHealth() - o2.getHealth()));
        } else if (mode.getValue() == Mode.HurtTime) {
            stream = stream.sorted(Comparator.comparingInt(o -> (20 - o.getHurtResistantTime())));
        }

        return stream.findFirst().orElse(new EntityLivingBase(null));
    }

    enum Mode{
        Angle,
        Armor,
        Range,
        FOV,
        Health,
        HurtTime
    }
}
