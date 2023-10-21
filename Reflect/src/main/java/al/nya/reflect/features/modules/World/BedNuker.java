package al.nya.reflect.features.modules.World;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.*;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.MathHelper;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.block.Block;
import al.nya.reflect.wrapper.wraps.wrapper.block.Blocks;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07PacketPlayerDigging;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetHandlerPlayClient;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BedNuker
 * From m2bt
 */
public class BedNuker extends Module {
    public static boolean isNuking = false;
    public static BlockPos blockBreaking = BlockPos.wrap(null);
    public OptionValue Swing = new OptionValue("Swing",true);
    List<BlockPos> beds = new ArrayList<>();
    public BedNuker() {
        super("BedNuker", ModuleType.World);
        addValues(Swing);
    }

    @Override
    public void onEnable() {
        isNuking = false;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        isNuking = false;
        super.onDisable();
    }

    private boolean blockChecks(Block block) {
        return block.equals(Blocks.bed);
    }
    @EventTarget
    public void onClientTick(EventPreUpdate event) {
        if (ModuleManager.getModule(Scaffold.class).isEnable()) return;
        int reach;
        WorldClient theWorld = mc.getTheWorld();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (theWorld.isNull() || thePlayer.isNull()) {
            blockBreaking = BlockPos.wrap(null);
            return;
        }
        for (int y = reach = 6; y >= -reach; --y) {
            for (int x = -reach; x <= reach; ++x) {
                for (int z = -reach; z <= reach; ++z) {
                    if (thePlayer.isSneaking()) {
                        return;
                    }
                    BlockPos pos = new BlockPos(thePlayer.getPosX() + (double) x, thePlayer.getPosY() + (double) y, thePlayer.getPosZ() + (double) z);
                    if (!this.blockChecks(theWorld.getBlockState(pos).getBlock()) || !(thePlayer.getDistance(thePlayer.getPosX() + (double) x, thePlayer.getPosY() + (double) y, thePlayer.getPosZ() + (double) z) < (double) mc.getPlayerController().getBlockReachDistance() - 0.2) || this.beds.contains(pos))
                        continue;
                    this.beds.add(pos);
                }
            }
        }
        BlockPos closest = BlockPos.wrap(null);
        if (!this.beds.isEmpty()) {
            for (int i = 0; i < this.beds.size(); ++i) {
                BlockPos bed = this.beds.get(i);
                if (thePlayer.getDistance(bed.getX(), bed.getY(), bed.getZ()) > (double) mc.getPlayerController().getBlockReachDistance() - 0.2 || !theWorld.getBlockState(bed).getBlock().equals(Blocks.bed)) {
                    this.beds.remove(i);
                }
                if (!(closest.isNull()) && (!(thePlayer.getDistance(bed.getX(), bed.getY(), bed.getZ()) < thePlayer.getDistance(closest.getX(), closest.getY(), closest.getZ())) || thePlayer.getTicksExisted() % 50 != 0))
                    continue;
                closest = bed;
            }
        }
        if (!closest.isNull()) {
            float[] rot = this.getRotations(closest, this.getClosestEnum(closest));
            event.setYaw(rot[0]);
            event.setPitch(rot[1]);
            blockBreaking = closest;
            isNuking = true;
            return;
        }
        isNuking = false;
        blockBreaking = BlockPos.wrap(null);
    }
    @EventTarget
    public void onPlayerTick(EventUpdate update) {
        if (ModuleManager.getModule(Scaffold.class).isEnable()) return;
        NetHandlerPlayClient sendQueue = mc.getNetHandler();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (!blockBreaking.isNull()) {
            isNuking = true;
            sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07Action.START_DESTROY_BLOCK, blockBreaking, EnumFacing.DOWN));
            if (Swing.getValue()) thePlayer.swingItem();
            sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07Action.STOP_DESTROY_BLOCK, blockBreaking, EnumFacing.DOWN));
        } else {
            isNuking = false;
        }
    }
    @EventTarget
    public void onRender3D(EventRender3D event) {
        if (!blockBreaking.isNull()) {
            RenderUtil.drawBlockBox(blockBreaking, Color.RED,true);
        }
    }
    public float[] getRotations(BlockPos block, Enum<?> face) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        double x = (double) block.getX() + 0.5 - thePlayer.getPosX();
        double z = (double) block.getZ() + 0.5 - thePlayer.getPosZ();
        double d1 = thePlayer.getPosY() + (double) thePlayer.getEyeHeight() - ((double) block.getY() + 0.5);
        double d3 = MathHelper.sqrt_double(x * x + z * z);
        float yaw = (float) (Math.atan2(z, x) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float) (Math.atan2(d1, d3) * 180.0 / Math.PI);
        if (yaw < 0.0f) {
            yaw += 360.0f;
        }
        return new float[]{yaw, pitch};
    }

    private Enum<?> getClosestEnum(BlockPos pos) {
        Enum<?> closestEnum = EnumFacing.UP;
        float rotations = MathHelper.wrapAngleTo180_float(this.getRotations(pos, EnumFacing.UP)[0]);
        if (rotations >= 45.0f && rotations <= 135.0f) {
            closestEnum = EnumFacing.EAST;
        } else if (rotations >= 135.0f && rotations <= 180.0f || rotations <= -135.0f && rotations >= -180.0f) {
            closestEnum = EnumFacing.SOUTH;
        } else if (rotations <= -45.0f && rotations >= -135.0f) {
            closestEnum = EnumFacing.WEST;
        } else if (rotations >= -45.0f && rotations <= 0.0f || rotations <= 45.0f && rotations >= 0.0f) {
            closestEnum = EnumFacing.NORTH;
        }
        if (MathHelper.wrapAngleTo180_float(this.getRotations(pos, EnumFacing.UP)[1]) > 75.0f || MathHelper.wrapAngleTo180_float(this.getRotations(pos, EnumFacing.UP)[1]) < -75.0f) {
            closestEnum = EnumFacing.UP;
        }
        return closestEnum;
    }

}
