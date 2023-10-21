package al.logger.client.features.modules.impls.Combat;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventMouse;
import al.logger.client.event.client.player.EventPostUpdate;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Hazard;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ValidUtils;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.animation.logger.Smoother;
import al.logger.client.utils.misc.MathHelper;
import al.logger.client.utils.rotation.RotationUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.value.impls.TrackValue;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.item.ItemBlock;
import al.logger.client.wrapper.LoggerMC.item.ItemSword;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02Action;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02PacketUseEntity;
import al.logger.client.wrapper.LoggerMC.network.client.C07.C07Action;
import al.logger.client.wrapper.LoggerMC.network.client.C07.C07PacketPlayerDigging;
import al.logger.client.wrapper.LoggerMC.network.client.C08PacketPlayerBlockPlacement;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.EnumFacing;

import java.util.ArrayList;

public class LegitAura extends Module {

    public OptionValue antiButtTouch = new OptionValue("Anti ButtTouch", true);
    public OptionValue rotationEnd = new OptionValue("Only Rotation End", true);
    public OptionValue attachBlock = new OptionValue("AttachBlock", false);
    public OptionValue norot = new OptionValue("NoRotation", false);
    public OptionValue mousedown = new OptionValue("on Mouse Down", true);
    public OptionValue sword = new OptionValue("Only Sword", true);

    public DoubleValue maxTargets = new DoubleValue("Max Targets", 100, 1, 2, 1);
    public TrackValue CPS = new TrackValue("CPS", 7, 30, 7, 12, 1);
    public DoubleValue range = new DoubleValue("Range", 7.0, 3.0, 3.0, 0.1);
    public DoubleValue blockRange = new DoubleValue("Block Range", 10.0, 3.0, 5.0, 0.1);
    public DoubleValue fov = new DoubleValue("Fov", 360, 0, 360, 1);
    public DoubleValue rotationFov = new DoubleValue("Rotation Fov", 40, 0, 20, 1);
    public DoubleValue rotationSpeed = new DoubleValue("Rotation Speed", 1000, 0, 300, 1);
    public boolean blocked = false;
    public TimerUtils cpsTimer = new TimerUtils();
    public static ArrayList<EntityLivingBase> targets = new ArrayList<>();
    public EntityLivingBase target;
    public Smoother YawSmoother = new Smoother(0, rotationSpeed.getValue());
    public Smoother PitchSmoother = new Smoother(0, rotationSpeed.getValue());

    public LegitAura() {
        super("LegitAura", "Legit, Safe", Category.Combat);
        this.setHazard(Hazard.HIGH);
        this.addValues(attachBlock, rotationEnd, norot, mousedown, sword, maxTargets, rotationSpeed, range, fov, rotationFov);
    }

    @Listener
    public void eventPreUpdate(EventPreUpdate preUpdate) {
        this.targets.clear();
        for (Entity entity : mc.getTheWorld().getLoadedEntityList()) {
            if (EntityLivingBase.isEntityLivingBase(entity)) {
                EntityLivingBase entityLivingBase = new EntityLivingBase(entity.getWrappedObject());
                if (!entityLivingBase.equals(mc.getThePlayer()) && entityLivingBase.isEntityAlive() && ValidUtils.isValid(entityLivingBase) && targets.size() <= maxTargets.getValue()) {
                    if (entityLivingBase.getDistanceToEntity(mc.getThePlayer()) <= range.getValue()) {
                        if (RotationUtils.getFovParallax(RotationUtils.getEntityHeadPosition(entityLivingBase)[0], mc.getThePlayer().getRotationYaw()) <= fov.getValue()) {
                            targets.add(entityLivingBase);
                        }
                    }
                }
            }
        }
        if (targets.size() > 0) {
            target = targets.get(0);
            if (!norot.getValue()) {
                Float[] rotations = RotationUtils.getEntityHeadPosition(target, YawSmoother.get(), PitchSmoother.get());
                YawSmoother.update(rotations[0], System.currentTimeMillis());
                PitchSmoother.update(rotations[1], System.currentTimeMillis());
            }
        } else {
            YawSmoother.setValue(mc.getThePlayer().getRotationYaw());
            PitchSmoother.setValue(mc.getThePlayer().getRotationPitch());
            target = null;
            if (this.blocked) {
                this.unBlock();
            }
        }
        preUpdate.setRotation((float) YawSmoother.get(), (float) PitchSmoother.get());
        if (!mousedown.getValue() && target != null && cpsTimer.delay((float) MathHelper.nextDouble(1000 / CPS.getMinValue(), 1000 / CPS.getMaxValue()))) {
            cpsTimer.reset();
            if (sword.getValue()) {
                if (ItemSword.isItemSword(mc.getThePlayer().getHeldItem().getItem())) {
                    doAttack();
                }
            } else {
                doAttack();
            }
        }
    }

    public void doAttack() {
        Float[] rotations = RotationUtils.getEntityHeadPosition(target, YawSmoother.get(), PitchSmoother.get());
        if (rotationEnd.getValue()) {
            if ((int) YawSmoother.get() > rotations[0].intValue() + rotationFov.getValue() || (int) YawSmoother.get() < rotations[0].intValue() - rotationFov.getValue()
                    || (int) PitchSmoother.get() > rotations[1].intValue() + rotationFov.getValue() || (int) PitchSmoother.get() < rotations[1].intValue() - rotationFov.getValue()) {
                return;
            }
        }
        mc.getThePlayer().swingItem();
        mc.getThePlayer().getSendQueue().addToSendQueue(new C02PacketUseEntity(target, C02Action.ATTACK));
        //mc.getPlayerController().attackEntity(mc.getThePlayer(), target);
    }

    public void doBlock() {
        if (this.target != null && !this.blocked) {
            if (target.getDistanceToEntity(mc.getThePlayer()) <= blockRange.getValue()) {
                if (mc.getThePlayer().getHeldItem() != null && ItemSword.isItemSword(mc.getThePlayer().getHeldItem().getItem())
                        && (attachBlock.getValue() || mc.getGameSettings().getKeyBindUseItem().isPressed())) {
                    this.blocked = true;
                    //KeyBinding.onTick(mc.getGameSettings().getKeyBindUseItem().getKeyCode());
                    mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(
                            new BlockPos(-1, -1, -1),
                            255,
                            mc.getThePlayer().getHeldItem(),
                            0,
                            0,
                            0
                    ));
                }
            }
        }
    }

    public void unBlock() {
        if (this.blocked && mc.getThePlayer().getHeldItem() != null && ItemBlock.isItemBlock(mc.getThePlayer().getHeldItem().getItem())
                && (attachBlock.getValue() || mc.getGameSettings().getKeyBindUseItem().isPressed())) {
            this.blocked = false;
            mc.getThePlayer().getSendQueue().addToSendQueue(new C07PacketPlayerDigging(
                    C07Action.RELEASE_USE_ITEM,
                    BlockPos.ORIGIN,
                    EnumFacing.DOWN
            ));
        }
    }

    @Listener
    public void onMouse(EventMouse eventMouse) {
        if (mousedown.getValue()) {
            if (eventMouse.getButton() == 0 && eventMouse.isDown() && target != null) {
                this.unBlock();
                if (sword.getValue()) {
                    if (mc.getThePlayer().getHeldItem().getItem() instanceof ItemSword) {
                        doAttack();
                    }
                } else {
                    doAttack();
                }
            }
        }
    }

    @Listener
    public void eventPostUpdate(EventPostUpdate postUpdate) {
        if (this.YawSmoother.getTimeConstant() != rotationSpeed.getValue()) {
            this.YawSmoother.setTimeConstant(rotationSpeed.getValue());
        }
        if (this.PitchSmoother.getTimeConstant() != rotationSpeed.getValue()) {
            this.PitchSmoother.setTimeConstant(rotationSpeed.getValue());
        }
        if (this.target != null) {
            if (target.getDistanceToEntity(mc.getThePlayer()) <= blockRange.getValue()) {
                this.doBlock();
            }
        }
    }
}

