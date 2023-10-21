package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventRender2D;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.MathHelper;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.block.Block;
import al.nya.reflect.wrapper.wraps.wrapper.block.BlockSnow;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemBlock;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import org.lwjgl.input.Keyboard;

public class SelfRescue extends Module {
    public OptionValue cpsCap = new OptionValue("CpsCap",true);
    public DoubleValue range = new DoubleValue("Range",7,0,4,"0");
    public SelfRescue() {
        super("SelfRescue", ModuleType.Player);
        addValues(cpsCap, range);
    }

    @EventTarget
    public void render2D(EventRender2D evt){
        if(!cpsCap.getValue()) {
            this.placeBlock(range.getValue().intValue(), true, evt.getPartialTicks());
        } else {
            this.placeBlock(range.getValue().intValue(), false, evt.getPartialTicks());
        }
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if(cpsCap.getValue()) {
            this.placeBlock(range.getValue().intValue(), true);
        }
    }

    public boolean placeBlock(int range, boolean place, float partialTicks) {
        EntityPlayerSP thePlayer = this.mc.getThePlayer();
        if(!this.isAirBlock(getBlock((new BlockPos(thePlayer)).down()))) {
            return true;
        } else if(this.placeBlockSimple((new BlockPos(thePlayer)).down(), place, partialTicks)) {
            return true;
        } else {
            int dist = 0;

            for (; dist <= range; ++dist) {
                for (int blockDist = 0; dist != blockDist; ++blockDist) {
                    for (int x = blockDist; x >= 0; --x) {
                        int z = blockDist - x;
                        int y = dist - blockDist;
                        if (this.placeBlockSimple((new BlockPos(thePlayer)).down(y).north(x).west(z), place, partialTicks)) {
                            return true;
                        }

                        if (this.placeBlockSimple((new BlockPos(thePlayer)).down(y).north(x).west(-z), place, partialTicks)) {
                            return true;
                        }

                        if(this.placeBlockSimple((new BlockPos(thePlayer)).down(y).north(-x).west(z), place, partialTicks)) {
                            return true;
                        }

                        if(this.placeBlockSimple((new BlockPos(thePlayer)).down(y).north(-x).west(-z), place, partialTicks)) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }


    public boolean placeBlock(int range, boolean place) {
        EntityPlayerSP thePlayer = this.mc.getThePlayer();
        if(!this.isAirBlock(getBlock((new BlockPos(thePlayer)).down()))) {
            return true;
        } else if(this.placeBlockSimple((new BlockPos(thePlayer)).down(), place)) {
            return true;
        } else {
            int dist = 0;

            for(; dist <= range; ++dist) {
                for(int blockDist = 0; dist != blockDist; ++blockDist) {
                    for(int x = blockDist; x >= 0; --x) {
                        int z = blockDist - x;
                        int y = dist - blockDist;
                        if(this.placeBlockSimple((new BlockPos(thePlayer)).down(y).north(x).west(z), place)) {
                            return true;
                        }

                        if(this.placeBlockSimple((new BlockPos(thePlayer)).down(y).north(x).west(-z), place)) {
                            return true;
                        }

                        if(this.placeBlockSimple((new BlockPos(thePlayer)).down(y).north(-x).west(z), place)) {
                            return true;
                        }

                        if(this.placeBlockSimple((new BlockPos(thePlayer)).down(y).north(-x).west(-z), place)) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }

    public boolean isAirBlock(Block block) {
        return !block.getMaterial().isReplaceable()?false:!(BlockSnow.isBlockSnow(block)) || block.getMaxY() <= 0.125D;
    }

    public int getFirstHotBarSlotWithBlocks() {
        EntityPlayerSP thePlayer = this.mc.getThePlayer();
        for(int i = 0; i < 9; ++i) {
            if(!thePlayer.getInventory().getStackInSlot(i).isNull() && ItemBlock.isItemBlock(thePlayer.getInventory().getStackInSlot(i).getItem())) {
                return i;
            }
        }

        return 0;
    }

    public boolean placeBlockSimple(BlockPos pos, boolean place, float partialTicks) {
        EntityPlayerSP thePlayer = this.mc.getThePlayer();
        if(!this.doesSlotHaveBlocks(thePlayer.getInventory().currentItem())) {
            thePlayer.getInventory().currentItem(this.getFirstHotBarSlotWithBlocks());
        }
        Entity entity = this.mc.getRenderViewEntity();
        double d0 = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * (double)partialTicks;
        double d1 = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * (double)partialTicks;
        double d2 = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosY()) * (double)partialTicks;
        Vec3 eyesPos = new Vec3(d0, d1 + (double)mc.getThePlayer().getEyeHeight(), d2);

        for(Enum side : EnumFacing.values()) {
            if(!side.equals(EnumFacing.UP) && !side.equals(EnumFacing.DOWN)) {
                BlockPos neighbor = pos.offset(side);
                Enum side2 = EnumFacing.getOpposite(side);
                if(getBlock(neighbor).canCollideCheck(mc.getTheWorld().getBlockState(neighbor), false)) {
                    Vec3 hitVec = (new Vec3(neighbor)).addVector(0.5D, 0.5D, 0.5D).add(new Vec3(EnumFacing.getDirectionVec(side2)));
                    if(eyesPos.squareDistanceTo(hitVec) <= 36.0D) {
                        float[] angles = this.getRotations(neighbor, side2, partialTicks);
                        entity.setRotationYaw(angles[0]);
                        entity.setRotationPitch(angles[1]);
                        if(place) {
                            thePlayer.setRotationYaw( angles[0]);
                            thePlayer.setRotationPitch( angles[1]);
                            mc.getPlayerController().onPlayerRightClick(thePlayer, mc.getTheWorld(), thePlayer.getCurrentEquippedItem(), neighbor, side2, hitVec);
                            thePlayer.swingItem();
                        }

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean doesSlotHaveBlocks(int slotToCheck) {
        EntityPlayerSP thePlayer = this.mc.getThePlayer();
        return !thePlayer.getInventory().getStackInSlot(slotToCheck).isNull() && ItemBlock.isItemBlock(thePlayer.getInventory().getStackInSlot(slotToCheck).getItem()) && thePlayer.getInventory().getStackInSlot(slotToCheck).getStackSize() > 0;
    }


    public boolean placeBlockSimple(BlockPos pos, boolean place) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        Entity entity = this.mc.getRenderViewEntity();
        double d0 = entity.getPosX();
        double d1 = entity.getPosY();
        double d2 = entity.getPosZ();
        Vec3 eyesPos = new Vec3(d0, d1 + (double)thePlayer.getEyeHeight(), d2);

        for(Enum side : EnumFacing.values()) {
            if(!side.equals(EnumFacing.UP) && (!side.equals(EnumFacing.DOWN) || Keyboard.isKeyDown(mc.getGameSettings().getKeyBindJump().getKeyCode()))) {
                BlockPos neighbor = pos.offset(side);
                Enum side2 = EnumFacing.getOpposite(side);
                if(getBlock(neighbor).canCollideCheck(mc.getTheWorld().getBlockState(neighbor), false)) {
                    Vec3 hitVec = (new Vec3(neighbor)).addVector(0.5D, 0.5D, 0.5D).add(new Vec3(EnumFacing.getDirectionVec(side2)));
                    if(eyesPos.squareDistanceTo(hitVec) <= 36.0D) {
                        float[] angles = this.getRotations(neighbor, side2);
                        entity.setRotationYaw(angles[0]);
                        entity.setRotationPitch(angles[1]);
                        if(place) {
                            thePlayer.setRotationYaw(angles[0]);
                            thePlayer.setRotationPitch(angles[1]);
                            mc.getPlayerController().onPlayerRightClick(thePlayer, mc.getTheWorld(), thePlayer.getCurrentEquippedItem(), neighbor, side2, hitVec);
                            thePlayer.swingItem();
                        }

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Block getBlock(BlockPos pos) {
        return this.mc.getTheWorld().getBlockState(pos).getBlock();
    }

    public float[] getRotations(BlockPos block, Enum face, float partialTicks) {
        EntityPlayerSP thePlayer = this.mc.getThePlayer();
        Entity entity = this.mc.getRenderViewEntity();
        double posX = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * (double)partialTicks;
        double posY = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * (double)partialTicks;
        double posZ = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * (double)partialTicks;
        double x = (double)block.getX() + 0.5D - posX + (double)EnumFacing.getFrontOffsetX(face) / 2.0D;
        double z = (double)block.getZ() + 0.5D - posZ + (double)EnumFacing.getFrontOffsetZ(face) / 2.0D;
        double y = (double)block.getY() + 0.5D;
        double d1 = posY + (double)thePlayer.getEyeHeight() - y;
        double d3 = (double)MathHelper.sqrt_double(x * x + z * z);
        float yaw = (float)(Math.atan2(z, x) * 180.0D / 3.141592653589793D) - 90.0F;
        float pitch = (float)(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D);
        if(yaw < 0.0F) {
            yaw += 360.0F;
        }

        return new float[]{yaw, pitch};
    }

    public float[] getRotations(BlockPos block, Enum face) {
        EntityPlayerSP thePlayer = this.mc.getThePlayer();
        Entity entity = this.mc.getRenderViewEntity();
        double posX = entity.getPosX();
        double posY = entity.getPosY();
        double posZ = entity.getPosZ();
        double x = (double)block.getX() + 0.5D - posX + (double)EnumFacing.getFrontOffsetX(face) / 2.0D;
        double z = (double)block.getZ() + 0.5D - posZ + (double)EnumFacing.getFrontOffsetZ(face) / 2.0D;
        double y = (double)block.getY() + 0.5D;
        double d1 = posY + (double) thePlayer.getEyeHeight() - y;
        double d3 = MathHelper.sqrt_double(x * x + z * z);
        float yaw = (float) (Math.atan2(z, x) * 180.0D / 3.141592653589793D) - 90.0F;
        float pitch = (float)(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D);
        if(yaw < 0.0F) {
            yaw += 360.0F;
        }
        
        return new float[]{yaw, pitch};
    }
}
