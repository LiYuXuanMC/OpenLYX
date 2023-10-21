package al.logger.client.utils.player;

import al.logger.client.Logger;
import al.logger.client.event.client.player.EventMove;
import al.logger.client.features.modules.impls.Combat.KillAura;
import al.logger.client.features.modules.impls.Movement.Speed;
import al.logger.client.features.modules.impls.Movement.TargetStrafe;
import al.logger.client.utils.misc.MathHelper;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.network.NetHandlerPlayClient;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C04PacketPlayerPosition;
import al.logger.client.wrapper.LoggerMC.potion.Potion;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.MovementInput;
import al.logger.client.wrapper.LoggerMC.world.WorldClient;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import static java.lang.Math.*;

public class MovementUtils {
    public static boolean isMoving() {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        MovementInput movementInput = thePlayer.getMovementInput();
        return !thePlayer.isNull() && (movementInput.getMoveForward() != 0f || movementInput.getMoveStrafe() != 0f);
    }
    public static float getSpeed() {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        return (float) sqrt(thePlayer.getMotionX() * thePlayer.getMotionX() + thePlayer.getMotionZ() * thePlayer.getMotionZ());

    }
    public static void strafe(float speed) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        if (!isMoving()){
            return;
        }
        double yaw = getDirection();
        thePlayer.setMotionX(-sin(yaw) * speed);
        thePlayer.setMotionZ(cos(yaw) * speed);
    }
    public static void jump(){
        if (((Speed)Logger.getInstance().getModuleManager().getModule(Speed.class)).canJump()){
            Minecraft.getMinecraft().getThePlayer().jump();
        }
    }

    public static int getSpeedAmplifier() {
        if(Minecraft.getMinecraft().getThePlayer().isPotionActive(Potion.moveSpeed)) {
            return 1 + Minecraft.getMinecraft().getThePlayer().getActivePotionEffect(Potion.moveSpeed).getAmplifier();
        }

        return 0;
    }

    public static float getPlayerDirection() {
        Minecraft mc = Minecraft.getMinecraft();
        float direction = mc.getThePlayer().getRotationYaw();

        if (mc.getThePlayer().getMoveForward() > 0) {
            if (mc.getThePlayer().getMoveStrafing() > 0) {
                direction -= 45;
            } else if (mc.getThePlayer().getMoveStrafing() < 0) {
                direction += 45;
            }
        } else if (mc.getThePlayer().getMoveForward() < 0) {
            if (mc.getThePlayer().getMoveStrafing() > 0) {
                direction -= 135;
            } else if (mc.getThePlayer().getMoveStrafing() < 0) {
                direction += 135;
            } else {
                direction -= 180;
            }
        } else {
            if (mc.getThePlayer().getMoveStrafing() > 0) {
                direction -= 90;
            } else if (mc.getThePlayer().getMoveStrafing() < 0) {
                direction += 90;
            }
        }

        return direction;
    }
    public static void setSpeed(final EventMove eventMove, final double speed) {
        float direction = (float)Math.toRadians(getDirection());
        Minecraft mc = Minecraft.getMinecraft();
        //final KillAura killaura = (KillAura) Logger.getInstance().getModuleManager().getModule(KillAura.class);
        //TODO: Support for TargetStrafe
        /*final TargetStrafe kl = (TargetStrafe)Logger.getInstance().getModuleManager().getModule(TargetStrafe.class);
        if (killaura.Cr() && killaura.uZ() != null && kl.Cr() && (Keyboard.isKeyDown(mc.getGameSettings().RHEX.getKeyCode()) || !kl.MS.getValue())) {
            direction = kl.rT();
        }

         */
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (isMoving()) {
            double motionX = -Math.sin(direction) * speed;
            double motionZ = Math.cos(direction) * speed;
            thePlayer.setMotionX(motionX);
            eventMove.setX(motionX);
            thePlayer.setMotionZ(motionZ);
            eventMove.setZ(motionZ);
        }
        else {
            double motionX = 0;
            double motionZ = 0;
            thePlayer.setMotionX(motionX);
            eventMove.setX(motionX);
            thePlayer.setMotionZ(motionZ);
            eventMove.setZ(motionZ);
        }
    }

    public static void setMotion(double speed, boolean smoothStrafe) {
        Minecraft mc = Minecraft.getMinecraft();
        double forward = mc.getThePlayer().getMovementInput().getMoveForward();
        double strafe = mc.getThePlayer().getMovementInput().getMoveStrafe();
        float yaw = mc.getThePlayer().getRotationYaw();
        int direction = smoothStrafe ? 45 : 90;

        if (forward == 0.0 && strafe == 0.0) {
            mc.getThePlayer().setMotionX(0.0);
            mc.getThePlayer().setMotionZ(0.0);
        } else {
            if (forward != 0.0) {
                if (strafe > 0.0) {
                    yaw += (float) (forward > 0.0 ? -direction : direction);
                } else if (strafe < 0.0) {
                    yaw += (float) (forward > 0.0 ? direction : -direction);
                }
                strafe = 0.0;
                if (forward > 0.0) {
                    forward = 1.0;
                } else if (forward < 0.0) {
                    forward = -1.0;
                }
            }

            mc.getThePlayer().setMotionX(forward * speed * (-Math.sin(Math.toRadians(yaw))) + strafe * speed * Math.cos(Math.toRadians(yaw)));
            mc.getThePlayer().setMotionZ(forward * speed * Math.cos(Math.toRadians(yaw)) - strafe * speed * (-Math.sin(Math.toRadians(yaw))));
        }
    }

    public static double getBaseMoveSpeed() {
        Potion moveSlowdown = Potion.moveSlowdown;
        Potion moveSpeed = Potion.moveSpeed;
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        double baseSpeed = thePlayer.getCapabilities().getWalkSpeed() * 2.873;
        if (thePlayer.isPotionActive(moveSlowdown)) {
            baseSpeed /= 1.0 + 0.2 * (thePlayer.getActivePotionEffect(moveSlowdown).getAmplifier() + 1);
        }
        if (thePlayer.isPotionActive(moveSpeed)) {
            baseSpeed *= 1.0 + 0.2 * (thePlayer.getActivePotionEffect(moveSpeed).getAmplifier() + 1);
        }
        return baseSpeed;
    }
    public static boolean isOnGround(double height) {
        Minecraft mc = Minecraft.getMinecraft();
        WorldClient theWorld = mc.getTheWorld();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        return !theWorld.getCollidingBoundingBoxes(thePlayer, thePlayer.getEntityBoundingBox().offset(0.0D, -height, 0.0D)).isEmpty();
    }
    public static float getMoveYaw(float yaw) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        Vector2f from = new Vector2f((float) thePlayer.getLastTickPosY(), (float) thePlayer.getLastTickPosZ()),
                to = new Vector2f((float) thePlayer.getPosX(), (float) thePlayer.getPosZ()),
                diff = new Vector2f(to.x - from.x, to.y - from.y);

        double x = diff.x, z = diff.y;
        if (x != 0 && z != 0) {
            yaw = (float) Math.toDegrees((Math.atan2(-x, z) + MathHelper.PI2) % MathHelper.PI2);
        }
        return yaw;
    }
    public static void strafe(){
        strafe(getSpeed());
    }
    public static double getDirection() {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        float rotationYaw = thePlayer.getRotationYaw();
        float moveForward = thePlayer.getMoveForward();
        float moveStrafing = thePlayer.getMoveStrafing();
        if (moveForward < 0F)
            rotationYaw += 180F;
        float forward = 1F;
        if (moveForward < 0F)
            forward = -0.5F;
        else if (moveForward > 0F)
            forward = 0.5F;
        if (moveStrafing > 0F)
            rotationYaw -= 90F * forward;
        if (moveStrafing < 0F)
            rotationYaw += 90F * forward;
        return Math.toRadians(rotationYaw);
    }
    public static void handleVanillaKickBypass() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        NetHandlerPlayClient netHandler = mc.getNetHandler();
        double posX = thePlayer.getPosX();
        double posZ = thePlayer.getPosZ();
        double ground = calculateGround();
        {
            double posY = thePlayer.getPosY();
            while (posY > ground) {
                netHandler.addToSendQueue(new C04PacketPlayerPosition(posX, posY, posZ, true));
                if (posY - 8.0 < ground) break; // Prevent next step
                posY -= 8.0;
            }
        }
        netHandler.addToSendQueue(new C04PacketPlayerPosition(posX, ground, posZ, true));
        double posY = ground;
        while (posY < thePlayer.getPosY()) {
            netHandler.addToSendQueue(new C04PacketPlayerPosition(posX, posY, posZ, true));
            if (posY + 8.0 > thePlayer.getPosY()) break; // Prevent next step
            posY += 8.0;
        }
        netHandler.addToSendQueue(new C04PacketPlayerPosition(posX, thePlayer.getPosY(), posZ, true));
    }

    public static double calculateGround() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        AxisAlignedBB playerBoundingBox = thePlayer.getEntityBoundingBox();
        double blockHeight = 1.0;
        double ground = thePlayer.getPosY();
        while (ground > 0.0) {
            AxisAlignedBB customBox = new AxisAlignedBB(playerBoundingBox.getMaxX(), ground + blockHeight, playerBoundingBox.getMaxZ(), playerBoundingBox.getMinX(), ground, playerBoundingBox.getMinZ());
            if (mc.getTheWorld().checkBlockCollision(customBox)) {
                if (blockHeight <= 0.05) return ground + blockHeight;
                ground += blockHeight;
                blockHeight = 0.05;
            }
            ground -= blockHeight;
        }
        return 0.0;
    }

    public static void setMotion() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        AxisAlignedBB playerBoundingBox = thePlayer.getEntityBoundingBox();
        double forward = thePlayer.getMovementInput().getMoveForward();
        double strafe = thePlayer.getMovementInput().getMoveStrafe();
        double yaw = thePlayer.getRotationYaw();

        if (forward == 0.0 && strafe == 0.0) {
            mc.getThePlayer().setMotionX(0.0);
            mc.getThePlayer().setMotionZ(0.0);
        } else {
            if (forward != 0.0) {
                if (strafe > 0.0) {
                    if (forward > 0.0) {
                        yaw += -45;
                    } else {
                        yaw += 45;
                    }
                } else if (strafe < 0.0) {
                    if (forward > 0.0) {
                        yaw += 45;
                    } else {
                        yaw += -45;
                    }
                }
                strafe = 0.0;
                if (forward > 0.0) {
                    forward = 1.0;
                } else if (forward < 0.0) {
                    forward = -1.0;
                }
            }
        }
        double cos = MathHelper.cos((float) toRadians(yaw + 90.0));
        double sin = MathHelper.sin((float) Math.toRadians(yaw + 90.0));
        thePlayer.setMotionX(forward * getSpeed() * cos + strafe * getSpeed() * sin);
        thePlayer.setMotionZ(forward * getSpeed() * sin - strafe * getSpeed() * cos);
    }

    public static void setMotion(double speed) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        double forward = thePlayer.getMovementInput().getMoveForward();
        double strafe = thePlayer.getMovementInput().getMoveStrafe();
        float yaw = thePlayer.getRotationYaw();

        if (forward == 0.0 && strafe == 0.0) {
            thePlayer.setMotionX(0.0);
            thePlayer.setMotionZ(0.0);
        } else {
            if (forward != 0.0) {
                if (strafe > 0.0) {
                    yaw += (forward > 0.0) ? -45 : 45;
                } else if (strafe < 0.0) {
                    yaw += (forward > 0.0) ? 45 : -45;
                }
                strafe = 0.0;
                if (forward > 0.0) {
                    forward = 1.0;
                } else if (forward < 0.0) {
                    forward = -1.0;
                }
            }
            double cos = Math.cos(Math.toRadians(yaw + 90.0));
            double sin = Math.sin(Math.toRadians(yaw + 90.0));

            thePlayer.setMotionX((forward * speed * cos + strafe * speed * sin));
            thePlayer.setMotionZ((forward * speed * sin - strafe * speed * cos));
        }
    }
}
