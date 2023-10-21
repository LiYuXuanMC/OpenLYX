package al.nya.reflect.utils.entity;

import al.nya.reflect.events.events.EventMove;
import al.nya.reflect.utils.MathHelper;
import al.nya.reflect.wrapper.wraps.wrapper.GameSettings;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.block.Blocks;
import al.nya.reflect.wrapper.wraps.wrapper.block.Material;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetHandlerPlayClient;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovementInput;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;

public class PlayerUtil {
    private static final Minecraft mc = Minecraft.getMinecraft();
    public static boolean isInWater() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        return mc.getTheWorld().getBlockState(new BlockPos(thePlayer.getPosX(), thePlayer.getPosY(), thePlayer.getPosZ())).getBlock().getMaterial().getWrapObject().equals(Material.getWater().getWrapObject());
    }
    public static void setSpeed(EventMove event, double speed) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        MovementInput movementInput = thePlayer.getMovementInput();
        if (event == null) {
            setSpeed(speed);
            return;
        }

        float yaw = thePlayer.getRotationYaw();
        double forward = movementInput.getMoveForward();
        double strafe = movementInput.getMoveStrafe();
        if (forward == 0 && strafe == 0) {
            event.setX(0);
            event.setZ(0);
        } else {
            if (forward != 0) {
                if (strafe > 0) {
                    yaw += (forward > 0 ? -45 : 45);
                } else if (strafe < 0) {
                    yaw += (forward > 0 ? 45 : -45);
                }
                strafe = 0;
                if (forward > 0) {
                    forward = 1;
                } else {
                    forward = -1;
                }
            }
            double value = yaw + 90;
            double motionX = forward * speed * Math.cos(Math.toRadians(value)) + strafe * speed * Math.sin(Math.toRadians(value));
            double motionZ = forward * speed * Math.sin(Math.toRadians(value)) - strafe * speed * Math.cos(Math.toRadians(value));
            thePlayer.setMotionX(motionX);
            thePlayer.setMotionZ(motionZ);
            event.setX(motionX);
            event.setZ(motionZ);
        }
    }
    public static void setSpeed(double speed) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        MovementInput movementInput = thePlayer.getMovementInput();
        float yaw = thePlayer.getRotationYaw();
        double forward = movementInput.getMoveForward();
        double strafe = movementInput.getMoveStrafe();
        if (forward == 0 && strafe == 0) {
            thePlayer.setMotionX(0);
            thePlayer.setMotionZ(0);
        } else {
            if (forward != 0) {
                if (strafe > 0) {
                    yaw += (forward > 0 ? -45 : 45);
                } else if (strafe < 0) {
                    yaw += (forward > 0 ? 45 : -45);
                }
                strafe = 0;
                if (forward > 0) {
                    forward = 1;
                } else {
                    forward = -1;
                }
            }
            double value = yaw + 90;
            double motionX = forward * speed * Math.cos(Math.toRadians(value)) + strafe * speed * Math.sin(Math.toRadians(value));
            double motionZ = forward * speed * Math.sin(Math.toRadians(value)) - strafe * speed * Math.cos(Math.toRadians(value));
            thePlayer.setMotionX(motionX);
            thePlayer.setMotionZ(motionZ);
        }
    }

    public static boolean moving(EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getMoveForward() != 0.0f || entityPlayerSP.getMoveStrafing() != 0.0f;
    }

    public static boolean MovementInput() {
        GameSettings gameSettings = Minecraft.getMinecraft().getGameSettings();
        return gameSettings.getKeyBindForward().isKeyDown() || gameSettings.getKeyBindLeft().isKeyDown() || gameSettings.getKeyBindRight().isKeyDown() || gameSettings.getKeyBindBack().isKeyDown();
    }

    public static boolean isBlockUnder() {
        EntityPlayerSP player = mc.getThePlayer();
        WorldClient world = mc.getTheWorld();
        AxisAlignedBB pBb = player.getEntityBoundingBox();
        double height = player.getPosY() + (double) player.getEyeHeight();
        int offset = 0;
        while ((double) offset < height) {
            if (!world.getCollidingBoundingBoxes(player, pBb.offset(0.0, -offset, 0.0)).isEmpty()) {
                return true;
            }
            offset += 2;
        }
        return false;
    }

    public static void selfDamage() {
        NetHandlerPlayClient netHandler = mc.getNetHandler();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        double x = thePlayer.getPosX();
        double y = thePlayer.getPosY();
        double z = thePlayer.getPosZ();
        final double fallHeight = 3.0625, offset = .0625;
        for (int i = 0; i <= (fallHeight / offset); i++) {
            netHandler.addToSendQueue(new C04PacketPlayerPosition(x, y + offset, z, false));
            netHandler.addToSendQueue(new C04PacketPlayerPosition(x, y, z, false));
        }
        netHandler.addToSendQueue(new C04PacketPlayerPosition(x, MathHelper.floor_double(y), z, true));
    }

    public static boolean isBlockUnder(Entity ent) {
        if (ent.getPosY() < 0.0) {
            return false;
        }
        WorldClient theWorld = Minecraft.getMinecraft().getTheWorld();
        for (int off = 0; off < (int) ent.getPosY() + 2; off += 2) {
            AxisAlignedBB bb = ent.getEntityBoundingBox().offset(0.0, -off, 0.0);
            if (theWorld.getCollidingBoundingBoxes(ent, bb).isEmpty())
                continue;
            return true;
        }
        return false;
    }

    public static boolean isAirUnder(Entity ent) {
        return Minecraft.getMinecraft().getTheWorld().getBlockState(new BlockPos(ent.getPosX(), ent.getPosY() - 1, ent.getPosZ())).getBlock().equals(Blocks.air);
    }
}
