package al.logger.client.features.modules.impls.Combat;


import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventPostUpdate;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.event.client.render.EventRender3D;
import al.logger.client.event.client.world.EventLoadWorld;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Hazard;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.lock.Locks;
import al.logger.client.ui.templates.TargetHud;
import al.logger.client.utils.EntityValid;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.misc.MathHelper;
import al.logger.client.utils.network.PacketUtil;
import al.logger.client.utils.player.PositionConverter;
import al.logger.client.utils.player.RayCastUtil;
import al.logger.client.utils.rotation.RotationUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.*;
import al.logger.client.wrapper.LoggerMC.item.ItemSword;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02Action;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02PacketUseEntity;
import al.logger.client.wrapper.LoggerMC.network.client.C07.C07Action;
import al.logger.client.wrapper.LoggerMC.network.client.C07.C07PacketPlayerDigging;
import al.logger.client.wrapper.LoggerMC.network.client.C08PacketPlayerBlockPlacement;
import al.logger.client.wrapper.LoggerMC.network.client.C09PacketHeldItemChange;
import al.logger.client.wrapper.LoggerMC.utils.*;

import java.util.*;
import java.util.List;

import static al.logger.client.wrapper.LoggerMC.utils.MathHelper.wrapAngleTo180_float;

public class KillAura extends Module {
    private final ModeValue targetsetting = new ModeValue("Target", KillAura.Target.Single, KillAura.Target.values());
    private final ModeValue rotationsSetting = new ModeValue("Rotation", Rotations.Smooth, Rotations.values());

    private final ModeValue blockingTiem = new ModeValue("Blocking Time", BlockingType.Post, BlockingType.values());
    private final ModeValue autoblockmode = new ModeValue("AutoBlock Mode", BlockMode.Fake, BlockMode.values());
    private final ModeValue sortMode = new ModeValue("Sort Mode", SortMode.Distance, SortMode.values());
    private final DoubleValue switchDelay = new DoubleValue("Switch Delay", 1000, 100, 100, 1);
    private final DoubleValue reach = new DoubleValue("Reach", 6, 3, 4, 0.1);

    public final DoubleValue mincps = new DoubleValue("MinCPS", 20, 0, 6, 1);
    public final DoubleValue maxcps = new DoubleValue("MaxCPS", 20, 0, 6, 1);
    public DoubleValue fov = new DoubleValue("Fov", 360, 0, 360, 1);
    public DoubleValue maxTargets = new DoubleValue("Max Targets", 100, 1, 2, 1);
    private DoubleValue turnSpeed = new DoubleValue("LockViewTurnSpeed", 100D, 50D, 50D, 1);
    private final OptionValue autoblock = new OptionValue("AutoBlock", false);
    private final OptionValue autoDisable = new OptionValue("AutoDisable", true);
    private final OptionValue matrix = new OptionValue("Matrix", false);
    private final OptionValue raycast = new OptionValue("RayCast", false);
    private final OptionValue targetHUD = new OptionValue("TargetHUD", true);
    public static final OptionValue TargetHudPTEntity = new OptionValue("TargetHUD Follow Entity", false);
    private final EntityValid entitySelect = new EntityValid(true, false, false, false, false);

    public List<EntityLivingBase> targets = new ArrayList<>();

    public static EntityLivingBase target;
    public static boolean isBlocking = false;

    boolean test;
    public static boolean attacking;
    public TimerUtils timer = new TimerUtils(), swtichTimer = new TimerUtils();
    private double[] renderPositions = new double[]{0, 0, 0, 0};

    public KillAura() {
        super("KillAura", Category.Combat);
        setDescription("KillAura");
        setHazard(Hazard.HACK);
        addValues(targetsetting, sortMode, rotationsSetting, autoblock, autoblockmode, blockingTiem, matrix, autoDisable, reach, mincps, maxcps, fov, maxTargets, turnSpeed, targetHUD, TargetHudPTEntity, raycast);
        addValues(entitySelect.getValues());
    }

    @Listener
    public void onRender3D(EventRender3D eventRender3D) {
        if (TargetHudPTEntity.getValue()) {
            if(target != null){
                renderPositions = PositionConverter.convertEntityPosition(target);
            }else{
                renderPositions = PositionConverter.convertEntityPosition(Logger.getInstance().getGuiManager().targetHud.renderTarget);
            }
        }
    }

    @Listener
    public void onRender2D(EventRender2D eventRender2D) {
        if (targetHUD.getValue()) {
            TargetHud targetHud = Logger.getInstance().getGuiManager().targetHud;
            targetHud.target = target;
            targetHud.isTargetPT = TargetHudPTEntity.getValue();
            targetHud.renderPositions = renderPositions;
            if (targetHud.getPosition().getX() < 0 || targetHud.getPosition().getY() < 0) {
                targetHud.getPosition().setX(0);
                targetHud.getPosition().setY(0);
            }
            targetHud.drawComponent(0, 0, false);
        }
    }

    @Listener
    public void onPost(EventPostUpdate e) {
        if (!targets.isEmpty()) {
            final float[] rot = getLockView(target);
            if (rot != null) {
                final float yaw = rot[0];
                final float pitch = rot[1] + 4.0f;

                mc.getThePlayer().setRotationYaw(yaw);
                mc.getThePlayer().setRotationPitch(pitch);
            }

            if (blockingTiem.getValue() == BlockingType.Post) {
                if (!isBlocking && autoblock.getValue() && ItemSword.isItemSword(mc.getThePlayer().getHeldItem().getItem())) {
                    this.block(BlockingType.Post);
                }
            }

            if (isBlocking && !attacking) {
                this.unblock();
            }

        } else {
            if (isBlocking) {
                this.unblock();
            }
        }
    }

    @Listener
    public void onLoadWorld(EventLoadWorld loadWorld) {
        if (autoDisable.getValue()) {
            toggle();

        }
    }

    @Listener(priority = 600)
    public void onPre(EventPreUpdate e) {
        if (!logger.getGlobalConfiguration().getKillauraAttackScaffold().getValue() && logger.getLockManager().getLock(Locks.Scaffolding).isLocked()){
            return;
        }
        sortTargets();
        if (!targets.isEmpty()) {
            if (targetsetting.getValue() == Target.Switch && swtichTimer.hasTimeElapsed(switchDelay.getValue().longValue())) {
                swtichTimer.reset();
            }
        }
        if (!targets.isEmpty()) {
            target = targets.get(0);
            float[] rotations = getRotationsToEnt(target);

            if (rotationsSetting.getValue() == Rotations.Dynamic) {
                rotations[0] += MathHelper.getRandomInRange(1, 5);
                rotations[1] += MathHelper.getRandomInRange(1, 5);
            }
            if (rotationsSetting.getValue() == Rotations.Prediction) {
                rotations[0] = (float) (rotations[0] + ((Math.abs(target.getPosX() - target.getLastTickPosX()) - Math.abs(target.getPosZ() - target.getLastTickPosZ())) * (2 / 3)) * 2);
                rotations[1] = (float) (rotations[1] + ((Math.abs(target.getPosY() - target.getLastTickPosY()) - Math.abs(target.getEntityBoundingBox().getMinX() - target.getLastTickPosY())) * (2 / 3)) * 2);
            }

            if (rotationsSetting.getValue() == Rotations.Resolver) {
                if (target.getPosY() < 0) {
                    rotations[1] = 1;
                } else if (target.getPosY() > 255) {
                    rotations[1] = 90;
                }

                if (Math.abs(target.getPosX() - target.getLastTickPosX()) > 0.50 || Math.abs(target.getPosZ() - target.getLastTickPosZ()) > 0.50) {
                    target.setEntityBoundingBox(new AxisAlignedBB(target.getPosX(), target.getPosY(), target.getPosZ(), target.getLastTickPosX(), target.getLastTickPosY(), target.getLastTickPosZ()));
                }
            }

            if (rotationsSetting.getValue() == Rotations.Smooth) {
                float sens = RotationUtils.getSensitivityMultiplier();

                rotations[0] = RotationUtils.smoothRotation(mc.getThePlayer().getRotationYaw(), rotations[0], 360);
                rotations[1] = RotationUtils.smoothRotation(mc.getThePlayer().getRotationPitch(), rotations[1], 90);

                rotations[0] = Math.round(rotations[0] / sens) * sens;
                rotations[1] = Math.round(rotations[1] / sens) * sens;
            }

            if (matrix.getValue()) {
                rotations[0] = rotations[0] + MathHelper.getRandomFloat(1.98f, -1.98f);
            }

            if (rotationsSetting.getValue() == Rotations.LockView) {

            } else {
                e.setRotation(rotations[0], rotations[1]);
            }

            if(raycast.getValue() && RayCastUtil.isMouseOver(e.getYaw(), e.getPitch(), target, reach.getValue().floatValue())){
                return;
            }


            if (target != null && timer.delay((float) MathHelper.nextDouble(1000 / mincps.getValue(), 1000 / maxcps.getValue()))) {
                timer.reset();
                doAttack();
                attacking = true;
            }

            if (blockingTiem.getValue() == BlockingType.Pre) {
                if (!isBlocking && autoblock.getValue() && ItemSword.isItemSword(mc.getThePlayer().getHeldItem().getItem())) {
                    this.block(BlockingType.Pre);
                }
            }

            if (targets.isEmpty()) {
                attacking = false;
            }
            logger.getLockManager().getLock(Locks.KillAuraAttacking).setLocked(attacking);

            if (isBlocking && target == null) {
                this.unblock();
            }

        } else {
            target = null;
        }
    }




    private void doAttack(){
        mc.getThePlayer().swingItem();
        mc.getThePlayer().getSendQueue().addToSendQueue(new C02PacketUseEntity(target, C02Action.ATTACK));
    }
    private void block(BlockingType type) {
        if (attacking && target != null) {

            if (autoblockmode.getValue() == BlockMode.Blink) {
                PacketUtil.sendPacket(new C08PacketPlayerBlockPlacement(mc.getThePlayer().getHeldItem()));
                mc.getThePlayer().setItemInUseCount(mc.getThePlayer().getInventory().getCurrentItem().getMaxItemUseDuration());
            }

            if (autoblockmode.getValue() == BlockMode.Test) {
                PacketUtil.sendPacketNoEvent(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, mc.getThePlayer().getInventory().getCurrentItem(), 0.0f, 0.0f, 0.0f));
            }

            if (autoblockmode.getValue() == BlockMode.Hypixel) {
                    int slot = mc.getThePlayer().getInventory().currentItem();
                    PacketUtil.sendPacketNoEvent(new C09PacketHeldItemChange(slot < 8 ? slot + 1 : 0));
                    PacketUtil.sendPacketNoEvent(new C09PacketHeldItemChange(slot));
                    this.blockhypixel(true, true);

            }

            mc.getThePlayer().setItemInUseCount(mc.getThePlayer().getInventory().getCurrentItem().getMaxItemUseDuration());
            isBlocking = true;
        }
    }
    //

    public void blockhypixel(boolean noevent, boolean bl2) {
        C08PacketPlayerBlockPlacement lUK;
        C08PacketPlayerBlockPlacement lUK2 = lUK = bl2 ? new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, Minecraft.getMinecraft().getThePlayer().getHeldItem(), 0.0f, 0.0f, 0.0f) : new C08PacketPlayerBlockPlacement(Minecraft.getMinecraft().getThePlayer().getHeldItem());
        if (noevent) {
            PacketUtil.sendPacketNoEvent(lUK);
        } else {
            PacketUtil.sendPacket(lUK);
        }
    }


    private void unblock() {
        if (isBlocking) {


            if (autoblockmode.getValue() == BlockMode.Fake) {
                mc.getThePlayer().setItemInUseCount(0);
                isBlocking = false;
                return;
            } else {
                if (autoblockmode.getValue() == BlockMode.Blink) {
                    mc.getThePlayer().setItemInUseCount(0);
                    PacketUtil.sendPacket(new C07PacketPlayerDigging(C07Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                    mc.getGameSettings().getKeyBindUseItem().setPressed(false);
                }

                if (isBlocking && autoblockmode.getValue() == BlockMode.Hypixel) {
                    int slot = mc.getThePlayer().getInventory().currentItem();
                    PacketUtil.sendPacketNoEvent(new C09PacketHeldItemChange((slot + 1) % 8));
                    PacketUtil.sendPacketNoEvent(new C09PacketHeldItemChange(slot));
                    mc.getThePlayer().setItemInUseCount(0);
                    mc.getGameSettings().getKeyBindUseItem().setPressed(false);
                }
            }

            isBlocking = false;

        }
    }


    @Override
    public void onEnable() {
        if (!mc.getThePlayer().isBlocking()) {
            isBlocking = false;

        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        targets.clear();
        timer.reset();
        attacking = false;
        target = null;

        this.unblock();
        isBlocking = false;

        super.onDisable();
    }


    public static float[] getLockView(final Entity q) {
        if (q == null) {
            return null;
        }
        final double diffX = q.getPosX() - Minecraft.getMinecraft().getThePlayer().getPosX();
        double diffY;
        if (q instanceof EntityLivingBase) {
            final EntityLivingBase en = (EntityLivingBase) q;
            diffY = en.getPosY() + en.getEyeHeight() * 0.9 - (Minecraft.getMinecraft().getThePlayer().getPosY() + Minecraft.getMinecraft().getThePlayer().getEyeHeight());
        } else {
            diffY = (q.getEntityBoundingBox().getMinY() + q.getEntityBoundingBox().getMaxY()) / 2.0 - (Minecraft.getMinecraft().getThePlayer().getPosY() + Minecraft.getMinecraft().getThePlayer().getEyeHeight());
        }
        final double diffZ = q.getPosZ() - Minecraft.getMinecraft().getThePlayer().getPosZ();
        final double dist = MathHelper.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        final float pitch = (float) (-(Math.atan2(diffY, dist) * 180.0 / 3.141592653589793));
        return new float[]{Minecraft.getMinecraft().getThePlayer().getRotationYaw() + wrapAngleTo180_float(yaw - Minecraft.getMinecraft().getThePlayer().getRotationYaw()), Minecraft.getMinecraft().getThePlayer().getRotationPitch() + wrapAngleTo180_float(pitch - Minecraft.getMinecraft().getThePlayer().getRotationPitch())};
    }

    public boolean isValid(EntityLivingBase entLiving) {
        return entitySelect.check(entLiving, mc.getThePlayer());
    }

    public void sortTargets() {
        targets.clear();
        for (Entity entity : mc.getTheWorld().getLoadedEntityList()) {
            if (EntityLivingBase.isEntityLivingBase(entity)) {
                EntityLivingBase livingBase = new EntityLivingBase(entity.getWrappedObject());
                if (isValid(livingBase, reach.getValue().doubleValue())) {
                    targets.add(livingBase);
                }
            }
        }
        if (sortMode.getValue().equals(SortMode.Distance)) {
            targets.sort(Comparator.comparingDouble(mc.getThePlayer()::getDistanceToEntity));
        } else if (sortMode.getValue().equals(SortMode.Health)) {
            targets.sort(Comparator.comparingDouble(EntityLivingBase::getHealth));
        } else if (sortMode.getValue().equals(SortMode.Armor)) {
            targets.sort(Comparator.comparingInt(EntityLivingBase::getTotalArmorValue));
        } else if (sortMode.getValue().equals(SortMode.HurtTime)) {
            targets.sort(Comparator.comparingInt(EntityLivingBase::getHurtTime));
        }

    }

    private boolean isValid(EntityLivingBase entity, double range) {
        EntityPlayerSP thePlayer = mc.getThePlayer();

        if (!isVisibleFOV(entity, (float) fov.getValue().floatValue())) {
            return false;
        }

        if (thePlayer.getDistanceToEntity(entity) > range) {
            return false;
        }

        return entitySelect.check(entity, thePlayer);
    }

    public static boolean isVisibleFOV(final Entity e, final float fov) {
        return ((Math.abs(getRotations(e)[0] - Minecraft.getMinecraft().getThePlayer().getRotationYaw())
                % 360.0f > 180.0f) ? (360.0f
                - Math.abs(getRotations(e)[0] - Minecraft.getMinecraft().getThePlayer().getRotationYaw())
                % 360.0f)
                : (Math.abs(getRotations(e)[0] - Minecraft.getMinecraft().getThePlayer().getRotationYaw())
                % 360.0f)) <= fov;
    }


    public static float[] getRotations(final Entity entity) {
        if (entity == null) {
            return null;
        }
        final double diffX = entity.getPosX() - Minecraft.getMinecraft().getThePlayer().getPosX();
        final double diffZ = entity.getPosZ() - Minecraft.getMinecraft().getThePlayer().getPosZ();
        double diffY;
        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase elb = (EntityLivingBase) entity;
            diffY = elb.getPosY() + (elb.getEyeHeight())
                    - (Minecraft.getMinecraft().getThePlayer().getPosY() + Minecraft.getMinecraft().getThePlayer().getEyeHeight());
        } else {
            diffY = (entity.getEntityBoundingBox().getMinY() + entity.getEntityBoundingBox().getMaxY()) / 2.0
                    - (Minecraft.getMinecraft().getThePlayer().getPosY() + Minecraft.getMinecraft().getThePlayer().getEyeHeight());
        }
        final double dist = MathHelper.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0 / Math.PI) - 90.0f;
        final float pitch = (float) (-(Math.atan2(diffY, dist) * 180.0 / Math.PI));
        return new float[]{yaw, pitch};
    }

    private float[] getRotationsToEnt(Entity ent) {
        final double differenceX = ent.getPosX() - mc.getThePlayer().getPosX();
        final double differenceY = (ent.getPosY() + ent.getHeight()) - (mc.getThePlayer().getPosY() + mc.getThePlayer().getHeight()) - 0.5;
        final double differenceZ = ent.getPosZ() - mc.getThePlayer().getPosZ();
        final float rotationYaw = (float) (Math.atan2(differenceZ, differenceX) * 180.0D / Math.PI) - 90.0f;
        final float rotationPitch = (float) (Math.atan2(differenceY, mc.getThePlayer().getDistanceToEntity(ent)) * 180.0D
                / Math.PI);
        final float finishedYaw = mc.getThePlayer().getRotationYaw()
                + MathHelper.wrapAngleTo180_float(rotationYaw - mc.getThePlayer().getRotationYaw());
        final float finishedPitch = mc.getThePlayer().getRotationPitch()
                + MathHelper.wrapAngleTo180_float(rotationPitch - mc.getThePlayer().getRotationPitch());
        return new float[]{finishedYaw, -MathHelper.clamp_float(finishedPitch, -90, 90)};
    }


    public enum Target {
        Single,
        Switch
    }

    public enum Rotations {
        Dynamic,
        Prediction,
        Resolver,
        Smooth,
        LockView

    }

    public enum BlockMode {
        Fake,
        Blink,
        Test,
        Hypixel
    }

    public enum SortMode {
        Distance,
        Health,
        Armor,
        HurtTime
    }

    public enum BlockingType {
        Pre,
        Post
    }
}
