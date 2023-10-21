package al.nya.reflect.features.modules.Ghost;


import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventTick;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.GameSettings;
import al.nya.reflect.wrapper.wraps.wrapper.KeyBinding;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectType;

public class AutoClicker extends Module {
    private final ModeValue mode = new ModeValue("Mode", Mode.Normal, Mode.values());
    private final DoubleValue VALUE_AUTO_CLICK_ATTACK_DELAY = new DoubleValue("Delay", 10, 0, 1, "0") {
        @Override
        public boolean show() {
            return mode.getValue() == Mode.Vapu;
        }
    };
    private final DoubleValue VALUE_BLOCK_ATTACK_DELAY = new DoubleValue("AB Delay", 10, 0, -1, "0") {
        @Override
        public boolean show() {
            return mode.getValue() == Mode.Vapu;
        }
    };
    private final OptionValue leftValue = new OptionValue("LeftClick", true) {
        @Override
        public boolean show() {
            return mode.getValue() == Mode.Normal;
        }
    };
    private final OptionValue rightValue = new OptionValue("RightClick", true) {
        @Override
        public boolean show() {
            return mode.getValue() == Mode.Normal;
        }
    };
    private final DoubleValue minCPSValue = new DoubleValue("MinCPS", 20D, 1D, 7D, "0.0") {
        @Override
        public boolean show() {
            return mode.getValue() == Mode.Normal;
        }
    };
    private final DoubleValue maxCPSValue = new DoubleValue("MaxCPS", 20D, 1D, 7D, "0.0") {
        @Override
        public boolean show() {
            return mode.getValue() == Mode.Normal;
        }
    };
    public int noBlockTimer = 0;
    public double autoClickAttackDelay = 0;
    private long rightLastSwing = 0L;
    private long leftLastSwing = 0L;
    private long rightDelay = randomClickDelay(minCPSValue.getValue().intValue(), maxCPSValue.getValue().intValue());
    private long leftDelay = randomClickDelay(minCPSValue.getValue().intValue(), maxCPSValue.getValue().intValue());

    public AutoClicker() {
        super("AutoClicker", ModuleType.Ghost);
        addValue(mode);
        addValue(leftValue);
        addValue(rightValue);
        addValue(minCPSValue);
        addValue(maxCPSValue);
        addValue(VALUE_AUTO_CLICK_ATTACK_DELAY);
        addValue(VALUE_BLOCK_ATTACK_DELAY);
    }


    public long randomClickDelay(final int minCPS, final int maxCPS) {
        return (long) ((Math.random() * (1000 / minCPS - 1000 / maxCPS + 1)) + 1000 / maxCPS);
    }

    @EventTarget
    public void onTick(EventTick event) {
        if (mode.getValue() == Mode.Vapu) {
            noBlockTimer--;
            autoClickAttackDelay--;
            autoClickAttackDelay = Math.max(-1, autoClickAttackDelay);
            if (mc.getTheWorld().isNull() || mc.getThePlayer().isNull()) {
                noBlockTimer = 0;
                autoClickAttackDelay = 0;
                return;
            }

            GameSettings settings = mc.getGameSettings();
            int attackPressTime = settings.getKeyBindAttack().getPressTime();
            int usePressTime = settings.getKeyBindUseItem().getPressTime();
            boolean attackKeyDown = settings.getKeyBindAttack().isKeyDown();
            boolean useKeyDown = settings.getKeyBindUseItem().isKeyDown();
            if (attackPressTime > 0) {
                this.autoClickAttackDelay = VALUE_AUTO_CLICK_ATTACK_DELAY.getValue();
            } else if (attackKeyDown && !mc.getObjectMouseOver().isNull()
                    && mc.getObjectMouseOver().getTypeOfHit() == MovingObjectType.ENTITY
                    && EntityLivingBase.isEntityLivingBase(mc.getObjectMouseOver().getEntityHit())
                    && this.autoClickAttackDelay <= 0) {
                attackPressTime += 1;
//            this.autoToolDelayTimer = -20*5;
                settings.getKeyBindAttack().setPressTime(attackPressTime);
                this.autoClickAttackDelay += 1;
            }
            if (!attackKeyDown) {
//                this.autoToolDelayTimer = 0;
            }

//            if (VALUE_BLOCK_ATTACK_DELAY.getValue() >= 0) {
//                Item item = null;
//                ItemStack itemStack = null;
//                if (mc.getThePlayer().isNull()) {
//                    itemStack = mc.getThePlayer().getCurrentEquippedItem();
//                    if (itemStack != null) {
//                        item = itemStack.getItem();
//                    }
//                }
//                if (item != null && item.getItemUseAction(itemStack) == EnumAction.BLOCK) {
//                    if (mc != null && mc.playerController != null && mc.gameSettings != null
//                            && mc.gameSettings.keyBindAttack != null && mc.gameSettings.keyBindUseItem != null) {
//
//
//                        // Tool.log("%s\t%s\t%s\t%s\t", attackPressTime, attackKeyDown, usePressTime,
//                        // useKeyDown);
//
//                        if (attackPressTime > 0 && mc.objectMouseOver != null
//                                && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
//
//                            boolean needAttack = true;
//
//                            Entity entity = mc.objectMouseOver.entityHit;
//
//                            if (entity instanceof EntityLivingBase) {
//                                EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
//
//                                boolean hasBeenCrit = false;
//                                EntityLivingBaseData data = this.entityLivingBaseDict.get(entity);
//                                if (data != null) {
//                                    hasBeenCrit = data.hasBeenCrit;
//                                }
//                                boolean isCrit = false;
//                                if ((!mc.thePlayer.onGround && mc.thePlayer.fallDistance > 0)
//                                        || (mc.thePlayer.onGround && this.crit)) {
//                                    isCrit = true;
//                                }
//                                boolean isHurting = entityLivingBase.hurtTime > 0;
//                                boolean additionalCrit = isCrit && !hasBeenCrit;
//                                if (isHurting && !additionalCrit) {
//                                    needAttack = false;
//                                }
//                                if (!entityLivingBase.isEntityAlive())
//                                    needAttack = false;
//
//                                if (!needAttack)
//                                    this.onAttack();
//
////							Tool.log("needAttack: %s, isCrit: %s, hasBeenCrit: %s, hurtResistantTime: %s, maxHurtResistantTime/2 : %s", needAttack, isCrit, hasBeenCrit, entityLivingBase.hurtResistantTime, entityLivingBase.maxHurtResistantTime/2);
//
//                            }
//
//                            if (needAttack) {
//                                if (usePressTime > 0) {
//                                    while (mc.gameSettings.keyBindUseItem.isPressed()) {
//                                    }
//                                }
//
//                                if (mc.thePlayer.isUsingItem()) {
//
//                                    mc.playerController.onStoppedUsingItem(mc.thePlayer);
//                                }
//
//                                noBlockTimer = setting.VALUE_BLOCK_ATTACK_DELAY;
//                            }
//
//                        }
//                        int keyCode = mc.gameSettings.keyBindUseItem.getKeyCode();
//                        boolean press;
//                        if (keyCode < 0) {
//                            press = Mouse.isButtonDown(keyCode + 100);
//                        } else {
//                            press = Keyboard.isKeyDown(keyCode);
//                        }
//
//                        if (noBlockTimer > 0 && useKeyDown) {
//                            press = false;
//                        }
//
//                        if (press != useKeyDown) {
//                            ReflectionHelper.setPrivateValue(KeyBinding.class, mc.gameSettings.keyBindUseItem,
//                                    new Boolean(press), new String[] { "pressed", "field_74513_e" });
//                        }
//
//                    }
//                }
//            } else {
//                noBlockTimer = 0;
//            }

        }
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (!(mode.getValue() == Mode.Normal)) return;
        if (mc.getGameSettings().getKeyBindAttack().isKeyDown() && leftValue.getValue() &&
                System.currentTimeMillis() - leftLastSwing >= leftDelay && mc.getPlayerController().getCurBlockDamageMP() == 0F) {
            mc.setLeftClickCounter(0);
            KeyBinding.onTick(mc.getGameSettings().getKeyBindAttack().getKeyCode()); // Minecraft Click Handling
            leftLastSwing = System.currentTimeMillis();
            leftDelay = randomClickDelay(minCPSValue.getValue().intValue(), maxCPSValue.getValue().intValue());
        }

        // Right click
        if (mc.getGameSettings().getKeyBindUseItem().isKeyDown() && !mc.getThePlayer().isUsingItem() && rightValue.getValue() &&
                System.currentTimeMillis() - rightLastSwing >= rightDelay) {
            KeyBinding.onTick(mc.getGameSettings().getKeyBindUseItem().getKeyCode()); // Minecraft Click Handling
            rightLastSwing = System.currentTimeMillis();
            rightDelay = randomClickDelay(minCPSValue.getValue().intValue(), maxCPSValue.getValue().intValue());
        }
    }

    enum Mode {
        Normal,
        Vapu
    }
}
