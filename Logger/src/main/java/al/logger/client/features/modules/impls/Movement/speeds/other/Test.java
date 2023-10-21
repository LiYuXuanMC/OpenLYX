package al.logger.client.features.modules.impls.Movement.speeds.other;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventEntityAction;
import al.logger.client.event.client.player.EventMove;
import al.logger.client.event.client.player.EventPlayerState;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.ModuleCarrier;
import al.logger.client.utils.misc.MathHelper;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.utils.rotation.RotationUtils;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.block.BlockIce;
import al.logger.client.wrapper.LoggerMC.block.BlockPackedIce;
import al.logger.client.wrapper.LoggerMC.block.BlockSlime;
import al.logger.client.wrapper.LoggerMC.potion.Potion;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import lombok.Getter;
import net.minecraft.client.entity.EntityPlayerSP;


import java.util.ArrayList;

public class Test extends ModuleCarrier {

    private boolean shouldBoost, expBoost;
    private double moveSpeed, lastDist, baseSpeed, boostSpeed;

    // vestige
    private double speed;
    private boolean prevOnGround;
    private int counter, ticks, offGroundTicks, ticksSinceVelocity;

    private boolean takingVelocity, wasTakingVelocity;
    private double velocityX, velocityY, velocityZ;
    private double velocityDist;
    private float lastDirection;
    private float lastYaw;
    private double motionX, motionY, motionZ;

    @Getter
    private double actualX, actualY, actualZ, lastActualX, lastActualY, lastActualZ;
    private boolean actualGround;
    private boolean started, firstJumpDone;
    private boolean wasCollided;
    private int oldSlot;
    private final ArrayList<BlockPos> barriers = new ArrayList<>();
    private float lastForward, lastStrafe;

    OptionValue rotate = new OptionValue("Rotate" , false);

    OptionValue dmgboost = new OptionValue("DMG Boost" , false);
    public Test() {
        super("Test" , Category.Movement);
        addValues(rotate , dmgboost);
    }


    @Override
    public void onEnable() {
        moveSpeed = MovementUtils.getBaseMoveSpeed();

        prevOnGround = false;
        speed = 0.28;

        ticks = offGroundTicks = counter = 0;

        ticksSinceVelocity = Integer.MAX_VALUE;

        started = firstJumpDone = false;

        takingVelocity = wasTakingVelocity = false;

        motionX = mc.getThePlayer().getMotionX();
        motionY = mc.getThePlayer().getMotionY();
        motionZ = mc.getThePlayer().getMotionZ();

        actualX = mc.getThePlayer().getPosX();
        actualY = mc.getThePlayer().getPosY();
        actualZ = mc.getThePlayer().getPosZ();

        actualGround = mc.getThePlayer().isOnGround();

        lastDirection = MovementUtils.getPlayerDirection();

        lastYaw = mc.getThePlayer().getRotationYaw();

        lastForward = mc.getThePlayer().getMoveForward();
        lastStrafe = mc.getThePlayer().getMoveStrafing();

        oldSlot = mc.getThePlayer().getInventory().currentItem();

        wasCollided = false;
    }

    @Override
    public void onDisable() {
        mc.getTimer().setTimerSpeed(1.0F);
        mc.getThePlayer().setPosition(actualX, actualY, actualZ);
        mc.getThePlayer().setMotionX(motionX);
        mc.getThePlayer().setMotionY(motionY);
        mc.getThePlayer().setMotionZ(motionZ);

        mc.getThePlayer().setOnGround(actualGround);
    }
    @Listener
    public void onPre(EventPreUpdate event) {
        event.setX(actualX);
        event.setY(actualY);
        event.setZ(actualZ);
        event.setOnGround(actualGround);

        float direction = RotationUtils.getRotationsToPosition(lastActualX, lastActualY, lastActualZ, mc.getThePlayer().getPosX(), mc.getThePlayer().getPosY(), mc.getThePlayer().getPosZ())[0];

        if (rotate.getValue()) {
            final float f = mc.getGameSettings().getMouseSensitivity() * 0.6F + 0.2F;
            final float gcd = f * f * f * 1.2F;

            final float deltaYaw = direction - lastYaw;

            final float fixedDeltaYaw = deltaYaw - (deltaYaw % gcd);

            direction = lastYaw + fixedDeltaYaw;

            lastYaw = direction;

            event.setYaw(direction);
        }
    }

    @Listener
    public void onEventPlayerState(EventPlayerState e){
        if(MovementUtils.isMoving()||mc.getThePlayer().isOnGround()) {
            lastActualX = actualX;
            lastActualY = actualY;
            lastActualZ = actualZ;

            float direction = RotationUtils.getRotationsToPosition(lastActualX, lastActualY, lastActualZ,
                    mc.getThePlayer().getPosX(), mc.getThePlayer().getPosY(), mc.getThePlayer().getPosZ())[0];

            float gcd = RotationUtils.getGCD();

            float yawDiff = (direction - lastYaw);

            float fixedYawDiff = yawDiff - (yawDiff % gcd);

            direction = lastYaw + fixedYawDiff;

            float dir = direction * 0.017453292F;

            float friction = getFriction(actualX, actualY, actualZ) * 0.91F;

            if (actualGround) {
                motionY = 0.42;

                if (mc.getThePlayer().isPotionActive(Potion.jump)) {
                    motionY += (float) (mc.getThePlayer().getActivePotionEffect(Potion.jump).getAmplifier() + 1)
                            * 0.1F;
                }

                if (!wasCollided) {
                    motionX -= MathHelper.sin(dir) * 0.2F;
                    motionZ += MathHelper.cos(dir) * 0.2F;
                }
            }

            float aa = 0.16277136F / (friction * friction * friction);

            float attributeSpeed;

            mc.getThePlayer().setSprinting(!wasCollided);

            if (actualGround) {
                attributeSpeed = MovementUtils.getSpeed() * aa;
            } else {
                attributeSpeed = wasCollided ? 0.02F : 0.026F;
            }

            boolean oldActualGround = actualGround;

            float forward = 0.98F;
            float strafe = 0F;

            float thing = strafe * strafe + forward * forward;

            if (thing >= 1.0E-4F) {
                thing = MathHelper.sqrt_float(thing);

                if (thing < 1.0F) {
                    thing = 1.0F;
                }

                thing = attributeSpeed / thing;
                strafe = strafe * thing;
                forward = forward * thing;
                float f1 = MathHelper.sin(direction * (float) Math.PI / 180.0F);
                float f2 = MathHelper.cos(direction * (float) Math.PI / 180.0F);
                motionX += strafe * f2 - forward * f1;
                motionZ += forward * f2 + strafe * f1;
            }

            if (dmgboost.getValue() && actualGround) {
                double speed = Math.hypot(motionX, motionZ);

                motionX = -Math.sin(Math.toRadians(direction)) * speed;
                motionZ = Math.cos(Math.toRadians(direction)) * speed;
            }

            double clientX = mc.getThePlayer().getPosX();
            double clientY = mc.getThePlayer().getPosY();
            double clientZ = mc.getThePlayer().getPosZ();

            double clientMotionX = mc.getThePlayer().getMotionX();
            double clientMotionY = mc.getThePlayer().getMotionY();
            double clientMotionZ = mc.getThePlayer().getMotionZ();

            boolean clientGround = mc.getThePlayer().isOnGround();

            mc.getThePlayer().setPosition(actualX, actualY, actualZ);

            mc.getThePlayer().setOnGround(actualGround);

            mc.getThePlayer().moveEntity(motionX, motionY, motionZ);

            boolean collided = mc.getThePlayer().isCollidedHorizontally();

            motionX = mc.getThePlayer().getPosX() - lastActualX;
            motionY = mc.getThePlayer().getPosY() - lastActualY;
            motionZ = mc.getThePlayer().getPosZ() - lastActualZ;

            actualX = mc.getThePlayer().getPosX();
            actualY = mc.getThePlayer().getPosY();
            actualZ = mc.getThePlayer().getPosZ();

            actualGround = mc.getThePlayer().isOnGround();

            mc.getThePlayer().setPosition(clientX, clientY, clientZ);
            mc.getThePlayer().setOnGround(clientGround);

            mc.getThePlayer().setMotionX(clientMotionX);
            mc.getThePlayer().setMotionY(clientMotionY);
            mc.getThePlayer().setMotionZ(clientMotionZ);

            if (oldActualGround) {
                motionX *= friction * 0.91F;
                motionZ *= friction * 0.91F;
            } else {
                motionX *= 0.91F;
                motionZ *= 0.91F;
            }

            motionY -= 0.08;
            this.motionY *= 0.9800000190734863D;

            if (Math.abs(motionX) < 0.005) {
                motionX = 0;
            }

            if (Math.abs(motionY) < 0.005) {
                motionY = 0;
            }

            if (Math.abs(motionZ) < 0.005) {
                motionZ = 0;
            }

            e.setSprint(!wasCollided);


            mc.getThePlayer().setSprinting(true);

            e.setSprint(false);

            wasCollided = collided;
        }
    }

    @Listener
    public void onMove(EventMove e){
        if(!takingVelocity && mc.getThePlayer().isOnGround()) {
            wasTakingVelocity = false;
        }

        double velocityExtra = 0.28 + MovementUtils.getSpeedAmplifier() * 0.07;

        float direction = MathHelper.wrapAngleTo180_float(MovementUtils.getPlayerDirection());

        float forward = mc.getThePlayer().getMoveForward();
        float strafe = mc.getThePlayer().getMoveStrafing();

        double distance = Math.hypot(mc.getThePlayer().getPosX() - actualX, mc.getThePlayer().getPosZ() - actualZ);

        //  if(mc.player.onGround && MovementUtils.isMoving()) {
        //    mc.player.jump();
        //        MovementUtils.Lowjumptest(event);
        //}


        if(!started) {
            speed = 0.65;
            started = true;
        } else {

            double baseSpeed = 0.33 + MovementUtils.getSpeedAmplifier() * 0.02;

            if(mc.getThePlayer().isOnGround()) {
                speed = 0.33 + baseSpeed;
            } else {
                speed = Math.min(speed - baseSpeed * distance * 0.15, baseSpeed);
            }

            speed = Math.max(speed, 0.295);

        }
        if (!mc.getThePlayer().isPotionActive(Potion.moveSpeed))
            MovementUtils.setMotion(speed, true);
        else MovementUtils.setMotion(speed*1.25,true);
        lastDirection = direction;
    }

    private float getFriction(double x, double y, double z) {
        Block block = mc.getTheWorld().getBlockState(new BlockPos(x, Math.floor(y) - 1, z)).getBlock();

        if(block != null) {
            if(BlockIce.isBlockIce(block) || BlockPackedIce.isBlockPackedIce(block)) {
                return 0.98F;
            } else if(BlockSlime.isBlockSlime(block)) {
                return 0.8F;
            }
        }

        return 0.6F;
    }
}

