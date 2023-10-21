package al.nya.reflect.utils;

import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectPosition;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectType;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;

public class FallingPlayer {
    private double x;
    private double y;
    private double z;
    private double motionX;
    private double motionY;
    private double motionZ;
    private float yaw;
    private float strafe;
    private float forward;
    private float jumpMovementFactor;

    public FallingPlayer(EntityPlayerSP player) {
        this(player.getPosX(), player.getPosY(), player.getPosZ(), player.getMotionX(), player.getMotionY(), player.getMotionZ(), player.getRotationYaw(), player.getMoveStrafing(), player.getMoveForward(), player.getJumpMovementFactor());
    }

    public FallingPlayer(double x, double y, double z, double motionX, double motionY, double motionZ, float yaw, float strafe, float forward, float jumpMovementFactor) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        this.yaw = yaw;
        this.strafe = strafe;
        this.forward = forward;
        this.jumpMovementFactor = jumpMovementFactor;
    }

    private void calculateForTick() {
        strafe *= 0.98f;
        forward *= 0.98f;
        float v = strafe * strafe + forward * forward;
        if (v >= 0.0001f) {
            v = MathHelper.sqrt_float(v);
            if (v < 1.0f) {
                v = 1.0f;
            }
            float fixedJumpFactor = jumpMovementFactor;
            if (Minecraft.getMinecraft().getThePlayer().isSprinting()) {
                fixedJumpFactor = (float) (fixedJumpFactor * 1.3);
            }
            v = fixedJumpFactor / v;
            strafe *= v;
            forward *= v;
            float f1 = MathHelper.sin(yaw * (float) Math.PI / 180.0f);
            double f2 = MathHelper.cos(yaw * (float) Math.PI / 180.0f);
            motionX += (strafe * f2 - forward * f1);
            motionZ += (forward * f2 + strafe * f1);
        }
        motionY -= 0.08;
        motionX *= 0.91;
        motionY *= 0.9800000190734863;
        motionZ *= 0.91;
        x += motionX;
        y += motionY;
        z += motionZ;
    }

    public final BlockPos findCollision(int ticks) {
        int n = 0;
        while (n < ticks) {
            BlockPos blockPos;
            n++;
            Vec3 start = new Vec3(this.x, this.y, this.z);
            this.calculateForTick();
            Vec3 end = new Vec3(this.x, this.y, this.z);

            float w = Minecraft.getMinecraft().getThePlayer().getWidth();
            // 逻辑1
            blockPos = this.rayTrace(start, end);
            if (blockPos != null) {
                return blockPos;
            }
            // 逻辑2
            blockPos = this.rayTrace(start.addVector(w, 0.0, w), end);
            if (blockPos != null) {
                return blockPos;
            }
            // 逻辑3
            blockPos = this.rayTrace(start.addVector(-((double)w), 0.0, w), end);
            if (blockPos != null) {
                return blockPos;
            }
            // 逻辑4
            blockPos = this.rayTrace(start.addVector(w, 0.0, -((double)w)), end);
            if (blockPos != null) {
                return blockPos;
            }
            // 逻辑5;
            blockPos = this.rayTrace(start.addVector(-((double)w), 0.0, -((double)w)), end);
            if (blockPos != null) {
                return blockPos;
            }
            // 逻辑6
            blockPos = this.rayTrace(start.addVector(w, 0.0, w / 2.0f), end);
            if (blockPos != null) {
                return blockPos;
            }
            // 逻辑7
            blockPos = this.rayTrace(start.addVector(-((double)w), 0.0, w / 2.0f), end);
            if (blockPos != null) {
                return blockPos;
            }
            // 逻辑8
            blockPos = this.rayTrace(start.addVector(w / 2.0f, 0.0, w), end);
            if (blockPos != null) {
                return blockPos;
            }
            // 逻辑9
            blockPos = this.rayTrace(start.addVector(w / 2.0f, 0.0, -((double)w)), end);
            if (blockPos == null) continue;
            return blockPos;
        }
        return null;
    }

    private BlockPos rayTrace(Vec3 start, Vec3 end) {
        MovingObjectPosition result = Minecraft.getMinecraft().getTheWorld().rayTraceBlocks(start, end, true);
        if (result == null || result.isNull()) return null;
        return result.getTypeOfHit() == MovingObjectType.BLOCK && result.getSideHit() == EnumFacing.UP ? result.getBlockPos() : null;
    }
}