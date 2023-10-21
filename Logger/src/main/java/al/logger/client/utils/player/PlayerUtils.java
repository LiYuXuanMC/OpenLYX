package al.logger.client.utils.player;

import al.logger.client.utils.misc.MathHelper;
import al.logger.client.wrapper.LoggerMC.GameSettings;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.block.BlockLiquid;
import al.logger.client.wrapper.LoggerMC.block.Material;
import al.logger.client.wrapper.LoggerMC.potion.Potion;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;

public class PlayerUtils {
    public static boolean MovementInput() {
        GameSettings gameSettings = Minecraft.getMinecraft().getGameSettings();
        return gameSettings.getKeyBindForward().isKeyDown() || gameSettings.getKeyBindLeft().isKeyDown() || gameSettings.getKeyBindRight().isKeyDown() || gameSettings.getKeyBindBack().isKeyDown();
    }

    public static void setSpeed(double speed) {
        Minecraft.getMinecraft().getThePlayer().setMotionX(-Math.sin(PlayerUtils.getDirection()) * speed);
        Minecraft.getMinecraft().getThePlayer().setMotionZ(Math.cos(PlayerUtils.getDirection()) * speed);
    }

    public static double getBaseMoveSpeed() {
        double baseSpeed = 0.2873;
        if (Minecraft.getMinecraft().getThePlayer().isPotionActive(Potion.moveSpeed)) {
            int amplifier = Minecraft.getMinecraft().getThePlayer().getActivePotionEffect(Potion.moveSpeed).getAmplifier();
            baseSpeed *= 1.0 + 0.2 * (double)(amplifier + 1);
        }
        return baseSpeed;
    }
    public static float getDirection() {
        float yaw = Minecraft.getMinecraft().getThePlayer().getRotationYaw();
        if (Minecraft.getMinecraft().getThePlayer().getMoveForward() < 0.0f) {
            yaw += 180.0f;
        }
        float forward = 1.0f;
        if (Minecraft.getMinecraft().getThePlayer().getMoveForward() < 0.0f) {
            forward = -0.5f;
        } else {
            if (Minecraft.getMinecraft().getThePlayer().getMoveForward() > 0.0f) {
                forward = 0.5f;
            }
        }
        Minecraft.getMinecraft();
        if (Minecraft.getMinecraft().getThePlayer().getMoveStrafing() > 0.0f) {
            yaw -= 90.0f * forward;
        }
        Minecraft.getMinecraft();
        if (Minecraft.getMinecraft().getThePlayer().getMoveStrafing() < 0.0f) {
            yaw += 90.0f * forward;
        }
        return yaw *= (float)Math.PI / 180;
    }

    public static boolean isInLiquid() {
        if (Minecraft.getMinecraft().getThePlayer() == null) {
            return false;
        }
        if (Minecraft.getMinecraft().getThePlayer().isInWater()) {
            return true;
        }
        boolean result = false;
        int y = (int)Minecraft.getMinecraft().getThePlayer().getEntityBoundingBox().getMinY();
        for (int x = MathHelper.floor_double(Minecraft.getMinecraft().getThePlayer().getEntityBoundingBox().getMinX()); x < MathHelper.floor_double(Minecraft.getMinecraft().getThePlayer().getEntityBoundingBox().getMinX()) + 1; ++x) {
            for (int z = MathHelper.floor_double(Minecraft.getMinecraft().getThePlayer().getEntityBoundingBox().getMinZ()); z < MathHelper.floor_double(Minecraft.getMinecraft().getThePlayer().getEntityBoundingBox().getMaxZ()) + 1; ++z) {
                Block block = Minecraft.getMinecraft().getTheWorld().getBlockState(new BlockPos(x, y, z)).getBlock();
                if (block == null || block.getMaterial().equals(Material.getAir())) continue;
                if (!(BlockLiquid.isBlockLiquid(block))) {
                    return false;
                }
                result = true;
            }
        }
        return result;
    }

    public static boolean isBlockUnder(final double height) {
        return isBlockUnder(height, true);
    }

    public static boolean isBlockUnder(final double height, final boolean boundingBox) {
        if (boundingBox) {
            for (int offset = 0; offset < height; offset += 2) {
                final AxisAlignedBB bb = Minecraft.getMinecraft().getThePlayer().getEntityBoundingBox().offset(0, -offset, 0);

                if (!Minecraft.getMinecraft().getTheWorld().getCollidingBoundingBoxes(Minecraft.getMinecraft().getThePlayer(), bb).isEmpty()) {
                    return true;
                }
            }
        } else {
            for (int offset = 0; offset < height; offset++) {
                if (blockRelativeToPlayer(0, -offset, 0).isFullBlock()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isBlockUnder() {
        return isBlockUnder(Minecraft.getMinecraft().getThePlayer().getPosX() + Minecraft.getMinecraft().getThePlayer().getEyeHeight());
    }

    public static Block blockRelativeToPlayer(final int offsetX, final int offsetY, final int offsetZ) {
        return Minecraft.getMinecraft().getTheWorld().getBlockState(new BlockPos(Minecraft.getMinecraft().getThePlayer()).add(offsetX, offsetY, offsetZ)).getBlock();
    }

}
