package al.nya.reflect.features.modules.Combat;


import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.Event;
import al.nya.reflect.events.events.EventPostUpdate;
import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.RotationUtils;
import al.nya.reflect.utils.timer.MSTimer;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

import java.util.ArrayList;

/**
 * 操你妈矩阵你妈死了网骗我250不会写就不会写你妈是不是上辈子被人操死你气急败坏要报复社会窝囊废整天阴阳怪气连个人的样子都活不出来傻逼你妈死了不会活就别活了整天网骗别人妈的弱智
 * 你妈死了下辈子别当人了社会的败类
 */
public class MagicKillaura extends Module {

    /**
     * OPTIONS
     */

    // CPS - Attack speed
    private final DoubleValue maxCpsValue = new DoubleValue("MaxCPS", 20, 1, 8, "0.0");
    private final DoubleValue minCpsValue = new DoubleValue("MinCPS", 20, 1, 5, "0.0");
    private final DoubleValue hurtTimeValue = new DoubleValue("HurtTime", 10, 0, 10, "0.0");
    private final OptionValue combatDelayValue = new OptionValue("1.9CombatDelay", false);
    // Range
    DoubleValue rangeValue = new DoubleValue("Range", 8, 1, 3.7, "0.0");
    private final DoubleValue throughWallsRangeValue = new DoubleValue("ThroughWallsRange", 8, 0, 1.5, "0.0");
    private final DoubleValue swingRangeValue = new DoubleValue("SwingRange", 15, 0, 5, "0.0");
    private final DoubleValue discoverRangeValue = new DoubleValue("DiscoverRange", 15, 0, 6, "0.0");
    // Modes
    private final ModeValue priorityValue = new ModeValue("Priority", priorityMode.Distance, priorityMode.values());
    private final ModeValue targetModeValue = new ModeValue("TargetMode", targetMode.Single, targetMode.values());

    // Bypass
    private final ModeValue swingValue = new ModeValue("Swing", swingMode.Normal, swingMode.values());
    private final ModeValue attackTimingValue = new ModeValue("AttackTiming", attackTimingMode.All, attackTimingMode.values());

    private final OptionValue keepSprintValue = new OptionValue("KeepSprint", true);
    private final OptionValue noBadPacketsValue = new OptionValue("NoBadPackets", false);

    // AutoBlock
    ModeValue autoBlockValue = new ModeValue("AutoBlock", autoBlockMode.Off, autoBlockMode.values());
    // vanilla will send block packet at pre
    private final ModeValue blockTimingValue = new ModeValue("BlockTiming", blockTimingMode.Both, blockTimingMode.values()) {
        @Override
        public boolean show() {
            return autoBlockValue.getValue() == autoBlockMode.Range;
        }
    };

    private final DoubleValue autoBlockRangeValue = new DoubleValue("AutoBlockRange", 8, 0, 2.5, "0.0") {
        @Override
        public boolean show() {
            return autoBlockValue.getValue() == autoBlockMode.Range;
        }
    };

    private final ModeValue autoBlockPacketValue = new ModeValue("AutoBlockPacket", autoBlockPacketMode.AfterTick, autoBlockPacketMode.values()) {
        @Override
        public boolean show() {
            return autoBlockValue.getValue() == autoBlockMode.Range;
        }
    };
    private final OptionValue interactAutoBlockValue = new OptionValue("InteractAutoBlock", true) {
        @Override
        public boolean show() {
            return autoBlockValue.getValue() == autoBlockMode.Range;
        }
    };
    private final DoubleValue blockRateValue = new DoubleValue("BlockRate", 100, 1, 100, "0.0") {
        @Override
        public boolean show() {
            return autoBlockValue.getValue() == autoBlockMode.Range;
        }
    };

    // Raycast
    private final OptionValue raycastValue = new OptionValue("RayCast", true);
    private final OptionValue raycastIgnoredValue = new OptionValue("RayCastIgnored", false) {
        @Override
        public boolean show() {
            return raycastValue.getValue();
        }
    };
    private final OptionValue livingRaycastValue = new OptionValue("LivingRayCast", true) {
        @Override
        public boolean show() {
            return raycastValue.getValue();
        }
    };

    // Bypass
    private final OptionValue aacValue = new OptionValue("AAC", true);
    // TODO: Divide AAC Opinion into three separated opinions

    // Rotations
    private final ModeValue rotationModeValue = new ModeValue("RotationMode", rotationMode.LiquidBounce, rotationMode.values());
    // TODO: RotationMode Bypass Intave

    private final DoubleValue maxTurnSpeedValue = new DoubleValue("MaxTurnSpeed", 180, 1, 180, "0.0");

    private final DoubleValue minTurnSpeedValue = new DoubleValue("MinTurnSpeed", 180, 1, 180, "0.0");

    private final ModeValue rotationSmoothModeValue = new ModeValue("SmoothMode", smoothMode.Custom, smoothMode.values());

    private final DoubleValue rotationSmoothValue = new DoubleValue("CustomSmooth", 10, 1, 2, "0.0") {
        @Override
        public boolean show() {
            return rotationSmoothModeValue.getValue() == smoothMode.Custom;
        }
    };

    private final ModeValue randomCenterModeValue = new ModeValue("RandomCenter", randomCenterMode.Off, randomCenterMode.values());
    private final DoubleValue randomCenRangeValue = new DoubleValue("RandomRange", 1.2, 0.0, 0.0, "0.0");

    private final OptionValue silentRotationValue = new OptionValue("SilentRotation", true) {
        @Override
        public boolean show() {
            return !(rotationModeValue.getValue() == rotationMode.None);
        }
    };

    private final ModeValue rotationStrafeValue = new ModeValue("Strafe", rotationStrafeMode.Silent, rotationStrafeMode.values()) {
        @Override
        public boolean show() {
            return (silentRotationValue.getValue() && !(rotationModeValue.getValue() == rotationMode.None));
        }
    };

    private final OptionValue strafeOnlyGroundValue = new OptionValue("StrafeOnlyGround", true) {
        @Override
        public boolean show() {
            return rotationStrafeValue.show() && !(rotationStrafeValue.getValue() == rotationStrafeMode.Off);
        }
    };

    private final OptionValue rotationRevValue = new OptionValue("RotationReverse", false) {
        @Override
        public boolean show() {
            return !(rotationModeValue.getValue() == rotationMode.None);
        }
    };

    private final DoubleValue rotationRevTickValue = new DoubleValue("RotationReverseTick", 20, 1, 5, "0.0") {
        @Override
        public boolean show() {
            return !(rotationModeValue.getValue() == rotationMode.None);
        }
    };
    private final OptionValue keepDirectionValue = new OptionValue("KeepDirection", true) {
        @Override
        public boolean show() {
            return !(rotationModeValue.getValue() == rotationMode.None);
        }
    };
    private final DoubleValue keepDirectionTickValue = new DoubleValue("KeepDirectionTick", 20, 1, 15, "0.0") {
        @Override
        public boolean show() {
            return !(rotationModeValue.getValue() == rotationMode.None);
        }
    };
    private final OptionValue hitableValue = new OptionValue("AlwaysHitable", true) {
        @Override
        public boolean show() {
            return !(rotationModeValue.getValue() == rotationMode.None);
        }
    };
    private final DoubleValue fovValue = new DoubleValue("FOV", 180, 0, 180, "0.0");

    // Predict
    private final OptionValue predictValue = new OptionValue("Predict", true) {
        @Override
        public boolean show() {
            return !(rotationModeValue.getValue() == rotationMode.None);
        }
    };

    private final DoubleValue maxPredictSizeValue = new DoubleValue("MaxPredictSize", 5, 0.1, 1, "0.0") {
        @Override
        public boolean show() {
            return predictValue.show() && predictValue.getValue();
        }
    };

    private final DoubleValue minPredictSizeValue = new DoubleValue("MinPredictSize", 5, 0.1, 1, "0.0") {
        @Override
        public boolean show() {
            return predictValue.show() && predictValue.getValue();
        }
    };

    // Bypass
    private final DoubleValue failRateValue = new DoubleValue("FailRate", 100, 0, 0, "0.0");
    private final OptionValue fakeSwingValue = new OptionValue("FakeSwing", true) {
        @Override
        public boolean show() {
            return failRateValue.getValue().floatValue() != 0f || failRateValue.getValue() != 0;
        }
    };

    private final ModeValue noInventoryAttackValue = new ModeValue("NoInvAttack", noInventoryAttackMode.Off, noInventoryAttackMode.values());

    private final DoubleValue noInventoryDelayValue = new DoubleValue("NoInvDelay", 500, 0, 200, "0.0");
    private final DoubleValue switchDelayValue = new DoubleValue("SwitchDelay", 2000, 1, 300, "0.0") {
        @Override
        public boolean show() {
            return targetModeValue.getValue() == targetMode.Switch;
        }
    };

    private final DoubleValue limitedMultiTargetsValue = new DoubleValue("LimitedMultiTargets", 50, 0, 0, "0") {
        @Override
        public boolean show() {
            return targetModeValue.getValue() == targetMode.Multi;
        }
    };

    // Visuals
    private final ModeValue markValue = new ModeValue("Mark", markMode.FDP, markMode.values());
    private final OptionValue circleValue = new OptionValue("Circle", false);
    private final DoubleValue circleRedValue = new DoubleValue("CircleRed", 255, 0, 255, "0") {
        @Override
        public boolean show() {
            return circleValue.getValue();
        }
    };
    private final DoubleValue circleGreenValue = new DoubleValue("CircleGreen", 255, 0, 255, "0") {
        @Override
        public boolean show() {
            return circleValue.getValue();
        }
    };
    private final DoubleValue circleBlueValue = new DoubleValue("CircleBlue", 255, 0, 255, "0") {
        @Override
        public boolean show() {
            return circleValue.getValue();
        }
    };
    private final DoubleValue circleAlphaValue = new DoubleValue("CircleAlpha", 255, 0, 255, "0") {
        @Override
        public boolean show() {
            return circleValue.getValue();
        }
    };
    private final DoubleValue circleThicknessValue = new DoubleValue("CircleThickness", 5, 1, 2, "0") {
        @Override
        public boolean show() {
            return circleValue.getValue();
        }
    };


    /**
     * MODULE
     */

    // Target
    EntityLivingBase target = new EntityLivingBase(null);
    private EntityLivingBase currentTarget = new EntityLivingBase(null);
    private boolean hitable = false;
    private final boolean lastPacketSent = false;
    private boolean packetSent = false;
    private final ArrayList<Integer> prevTargetEntities = new ArrayList<>();
    private final ArrayList<EntityLivingBase> discoveredTargets = new ArrayList<>();
    private final ArrayList<EntityLivingBase> inRangeDiscoveredTargets = new ArrayList<>();

    private boolean canFakeBlock() {
        return !inRangeDiscoveredTargets.isEmpty();
    }

    // Attack delay
    private final MSTimer attackTimer = new MSTimer();
    private final MSTimer switchTimer = new MSTimer();
    private final long attackDelay = 0L;
    private int clicks = 0;

    // Container Delay
    private final long containerOpen = -1L;

    // Swing
    private final MSTimer swingTimer = new MSTimer();
    private final long swingDelay = 0L;
    boolean strictStrafe = false;

    // Fake block status
    boolean blockingStatus = false;
    private boolean canSwing = false;

    private boolean displayBlocking() {
        return blockingStatus || (autoBlockValue.getValue() == autoBlockMode.Fake && canFakeBlock());
    }

    /**
     * Enable kill aura module
     */
    @Override
    public void onEnable() {
        if (mc.getThePlayer().isNull()) return;
        if (mc.getTheWorld().isNull()) return;
//        updateTarget();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        strictStrafe = false;
        target = new EntityLivingBase(null);
        currentTarget = new EntityLivingBase(null);
        hitable = false;
        packetSent = false;
        prevTargetEntities.clear();
        discoveredTargets.clear();
        inRangeDiscoveredTargets.clear();
        attackTimer.reset();
        clicks = 0;
        canSwing = false;
        swingTimer.reset();

//        stopBlocking();
        RotationUtils.setTargetRotationReverse(RotationUtils.serverRotation, 0, 0);
        super.onDisable();
    }

    @EventTarget
    public void onPlayerPreUpdate(EventPreUpdate e) {
        onMotion(e);
    }

    @EventTarget
    public void onPlayerPostUpdate(EventPostUpdate e) {
        onMotion(e);
    }

    public void onMotion(Event e) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (e instanceof EventPostUpdate) {
            packetSent = false;
        }
        if (thePlayer.isRiding()) {
            return;
        }

        if (attackTimingValue.getValue() == attackTimingMode.All ||
                attackTimingValue.getValue() == attackTimingMode.Both ||
                (attackTimingValue.getValue() == attackTimingMode.Pre && e instanceof EventPreUpdate) ||
                (attackTimingValue.getValue() == attackTimingMode.Post && e instanceof EventPostUpdate)) {
//            runAttackLoop();
        }

    }


    public MagicKillaura() {
        super("MagicKillaura", ModuleType.Combat);
        addValues(
                maxCpsValue,
                minCpsValue,
                hurtTimeValue,
                combatDelayValue,
                rangeValue,
                throughWallsRangeValue,
                swingRangeValue,
                discoverRangeValue,
                priorityValue,
                targetModeValue,
                swingValue,
                attackTimingValue,
                keepSprintValue,
                noBadPacketsValue,
                autoBlockValue,
                blockTimingValue,
                autoBlockRangeValue,
                autoBlockPacketValue,
                interactAutoBlockValue,
                blockRateValue,
                raycastValue,
                raycastIgnoredValue,
                livingRaycastValue,
                aacValue,
                rotationModeValue,
                maxTurnSpeedValue,
                minTurnSpeedValue,
                rotationSmoothModeValue,
                rotationSmoothValue,
                randomCenterModeValue,
                randomCenRangeValue,
                silentRotationValue,
                rotationStrafeValue,
                strafeOnlyGroundValue,
                rotationRevValue,
                rotationRevTickValue,
                keepDirectionValue,
                keepDirectionTickValue,
                hitableValue,
                fovValue,
                predictValue,
                maxPredictSizeValue,
                minPredictSizeValue,
                failRateValue,
                fakeSwingValue,
                noInventoryAttackValue,
                noInventoryDelayValue,
                switchDelayValue,
                limitedMultiTargetsValue,
                markValue,
                circleValue,
                circleRedValue,
                circleGreenValue,
                circleBlueValue,
                circleAlphaValue,
                circleThicknessValue
        );
    }



    enum markMode {
        Liquid,
        FDP,
        Block,
        Jello,
        Sims,
        None
    }

    enum noInventoryAttackMode {
        Spoof,
        CancelRun,
        Off
    }

    enum rotationStrafeMode {
        Off,
        Strict,
        Silent
    }

    enum priorityMode {
        Health,
        Distance,
        Fov,
        LivingTime,
        Armor,
        HurtResistantTime
    }

    enum randomCenterMode {
        Off,
        Cubic,
        Horizonal,
        Vertical
    }

    enum targetMode {
        Single,
        Switch,
        Multi
    }

    enum swingMode {
        Normal,
        Packet,
        None
    }

    enum rotationMode {
        None,
        LiquidBounce,
        ForceCenter,
        SmoothCenter,
        SmoothLiquid,
        LockView,
        OldMatrix
    }

    enum smoothMode {
        Custom,
        Line,
        Quad,
        Sine,
        QuadSine
    }

    enum autoBlockPacketMode {
        AfterTick,
        AfterAttack,
        Vanilla
    }

    enum attackTimingMode {
        All,
        Pre,
        Post,
        Both
    }

    enum autoBlockMode {
        Range,
        Fake,
        Off
    }
    enum blockTimingMode {
        Pre,
        Post,
        Both
    }
}
