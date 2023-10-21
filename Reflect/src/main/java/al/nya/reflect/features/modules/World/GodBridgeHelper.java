package al.nya.reflect.features.modules.World;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventTick;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.MathHelper;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.wrapper.wraps.wrapper.GameSettings;
import al.nya.reflect.wrapper.wraps.wrapper.block.IBlockState;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemBlock;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectPosition;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectType;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GodBridgeHelper extends Module {
    public int godBridgeTimer = 0;

    public GodBridgeHelper() {
        super("GodBridgeHelper", "神桥助手", ModuleType.World);
    }

    @Override
    public void onEnable() {
        godBridgeTimer = 0;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        godBridgeTimer = 0;
        super.onDisable();
    }

    @EventTarget
    public void onTick(EventTick event) {
        if (godBridgeTimer > 0) {
            mc.setRightClickDelayTimer(0);
            godBridgeTimer--;
        }
        EntityPlayerSP player = mc.getThePlayer();
        WorldClient world = mc.getTheWorld();
        GameSettings gameSettings = mc.getGameSettings();
        if (world.isNull() || player.isNull())
            return;

        boolean isKeyUseDown = false;
        int keyCode = gameSettings.getKeyBindUseItem().getKeyCode();
        if (keyCode >= 0) {
            isKeyUseDown = Keyboard.isKeyDown(keyCode);
        } else {
            isKeyUseDown = Mouse.isButtonDown(keyCode + 100);
        }

        Vec3 targetVec3 = new Vec3(null, null);

        targetVec3 = !isKeyUseDown ? new Vec3(null, null) : getGodBridgeHelperTargetVec3(world, player);


        godBridgeHelperExecute(player, targetVec3, false);

        int rightClickDelayTimer;
        int max = 2;

        if (isGodBridgeHelperState()) {
            rightClickDelayTimer = Math.min(1, mc.getRightClickDelayTimer());
        } else {
            rightClickDelayTimer = Math.min(max, mc.getRightClickDelayTimer());
        }

        mc.setRightClickDelayTimer(rightClickDelayTimer);

    }

    public boolean isGodBridgeHelperState() {
        if (!isEnable())
            return false;
        if (mc.getTheWorld().isNull() || mc.getThePlayer().isNull())
            return false;

        // Vec3 targetVec3 = getGodBridgeHelperTargetVec3(mc.theWorld, mc.thePlayer);
        Vec3 targetVec3 = mc.getThePlayer().getPositionEyes(1.0f).add(mc.getThePlayer().getLook(1.0f));
        return godBridgeHelperExecute(mc.getThePlayer(), targetVec3, true);
    }

    private boolean godBridgeHelperExecute(EntityPlayerSP player,
                                           Vec3 targetVec3, boolean isTest) {
        boolean result = false;
        MovingObjectPosition movingObjectPosition = player.rayTrace(mc.getPlayerController().getBlockReachDistance(), 1);
        if (!targetVec3.isNull() && !movingObjectPosition.isNull()) {
//			log("eee");
            BlockPos blockPos = movingObjectPosition.getBlockPos();
            Vec3 eyeVec3 = player.getPositionEyes((float) 1);
            ItemStack itemstack = player.getInventory().getCurrentItem();
            int i = !itemstack.isNull() ? itemstack.getStackSize() : 0;

            //log(targetVec3.toString());
            double d0 = targetVec3.getXCoord() - eyeVec3.getXCoord();
            double d1 = targetVec3.getYCoord() - eyeVec3.getYCoord();
            double d2 = targetVec3.getZCoord() - eyeVec3.getZCoord();

            // targetVec3 = new Vec3(x,y,z);

            double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);
            float f = (float) (MathHelper.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f1 = (float) (-(MathHelper.atan2(d1, d3) * 180.0D / Math.PI));
            // player.setPositionAndRotation(player.posX, player.posY, player.posZ, f, f1);
            float f2, f3;
            f2 = player.getRotationYaw();
            f3 = player.getRotationPitch();

            player.setRotationYaw(f);
            player.setRotationPitch(f1);

            MovingObjectPosition movingObjectPosition1 = player
                    .rayTrace(mc.getPlayerController().getBlockReachDistance(), 1);

            if (movingObjectPosition1.getTypeOfHit() == MovingObjectType.BLOCK
                    && movingObjectPosition1.getBlockPos().getX() == blockPos.getX()
                    && movingObjectPosition1.getBlockPos().getY() == blockPos.getY()
                    && movingObjectPosition1.getBlockPos().getZ() == blockPos.getZ()) {

                if (isTest) {

                } else {
                    if (mc.getPlayerController().onPlayerRightClick(player, mc.getTheWorld(), itemstack, blockPos,
                            movingObjectPosition1.getSideHit(), movingObjectPosition1.getHitVec())) {
                        player.swingItem();
                    }
                    if (itemstack.isNull()) {
                    } else if (itemstack.getStackSize() == 0) {
                        ItemStack[] itemStacks = player.getInventory().getMainInventory();
                        itemStacks[player.getInventory().currentItem()] = new ItemStack(null);
                        player.getInventory().setMainInventory(itemStacks);
                    } else if (itemstack.getStackSize() != i || mc.getPlayerController().isInCreativeMode()) {
                        mc.getEntityRenderer().getItemRenderer().resetEquippedProgress();
                    }
                }
            }
            //
            player.setRotationYaw(f2);
            player.setRotationPitch(f3);
            double pitchDelta = 2.5;
            double targetPitch = 75.5;
            if (targetPitch - pitchDelta < player.getRotationPitch()
                    && player.getRotationPitch() < targetPitch + pitchDelta) {

                double mod = player.getRotationYaw() % 45.0;
                if (mod < 0) {
                    mod += 45.0;
                }

                double delta = 5.0;

                if (mod < delta) {
                    result = true;

                    if (!isTest) {
                        player.setRotationPitch((float) (player.getRotationYaw() - mod));
                        player.setRotationPitch((float) targetPitch);
                    }

                } else if (45.0 - mod < delta) {
                    result = true;

                    if (!isTest) {
                        player.setRotationYaw((float) (player.getRotationYaw() + 45.0 - mod));
                        player.setRotationPitch((float) targetPitch);
                    }

                }


            }


            if (isTest) {

            } else {
                godBridgeTimer = 10;
            }

        }

        return result;
    }


    private Vec3 getGodBridgeHelperTargetVec3(WorldClient world, EntityPlayerSP player) {
        MovingObjectPosition movingObjectPosition = player.rayTrace(mc.getPlayerController().getBlockReachDistance(), 1);
        Vec3 targetVec3 = new Vec3(null, null);
        //		log(String.format("useKeyCode: %s", mc.gameSettings.keyBindUseItem.getKeyCode()));
        if (!movingObjectPosition.isNull() && movingObjectPosition.getTypeOfHit() == MovingObjectType.BLOCK
                && movingObjectPosition.getSideHit() == EnumFacing.UP) {
//			log("eee");
            ItemStack itemstack = player.getInventory().getCurrentItem();
            int i = !itemstack.isNull() ? itemstack.getStackSize() : 0;
            Vec3 eyeVec3 = player.getPositionEyes((float) 1);

            if (!itemstack.isNull() && ItemBlock.isItemBlock(itemstack.getItem())) {
                ItemBlock itemblock = new ItemBlock(itemstack.getItem().getWrapObject());

                if (!itemblock.canPlaceBlockOnSide(world, movingObjectPosition.getBlockPos(),
                        movingObjectPosition.getSideHit(), player, itemstack)) {
                    BlockPos blockPos = movingObjectPosition.getBlockPos();
                    IBlockState blockState = world.getBlockState(blockPos);
                    AxisAlignedBB axisalignedbb = blockState.getBlock().getSelectedBoundingBox(world, blockPos);
//					log(axisalignedbb.toString());
                    if (axisalignedbb.isNull() || world.isAirBlock(blockPos))
                        return new Vec3(null, null);
                    // EntityLookHelper entityLookHelper = new
                    // EntityLookHelper((EntityLiving)(mc.thePlayer));
                    double x1, x2, y1, y2, z1, z2;


                    x1 = axisalignedbb.getMinX();
                    x2 = axisalignedbb.getMaxX();
                    y1 = axisalignedbb.getMinY();
                    y2 = axisalignedbb.getMaxY();
                    z1 = axisalignedbb.getMinZ();
                    z2 = axisalignedbb.getMaxZ();

//					double x = MathHelper.clamp_double(eyeVec3.xCoord, x1, x2);
//					double y = MathHelper.clamp_double(eyeVec3.yCoord, y1, y2);
//					double z = MathHelper.clamp_double(eyeVec3.zCoord, z1, z2);

                    class Data implements Comparable<Data> {
                        public final BlockPos blockPos;
                        public final Enum<?> enumFacing;
                        public final double cost;

                        public Data(BlockPos blockPos, Enum<?> enumFacing, double cost) {
                            this.blockPos = blockPos;
                            this.enumFacing = enumFacing;
                            this.cost = cost;
                        }

                        @Override
                        public int compareTo(Data data) {
                            return (this.cost - data.cost) > 0 ? -1 : (this.cost - data.cost) < 0 ? 1 : 0;
                        }

                    }

                    List<Data> list = new ArrayList<Data>();

                    if (x1 <= eyeVec3.getXCoord() && eyeVec3.getXCoord() <= x2 && y1 <= eyeVec3.getYCoord() && eyeVec3.getYCoord() <= y2
                            && z1 <= eyeVec3.getZCoord() && eyeVec3.getZCoord() <= z2) {
                        // targetVec3 = new Vec3(0.5*(axisalignedbb.minX+axisalignedbb.maxX),
                        // 0.5*(axisalignedbb.minY+axisalignedbb.maxY),
                        // 0.5*(axisalignedbb.minZ+axisalignedbb.maxZ));
                    } else {
                        double xCost = Math.abs(eyeVec3.getXCoord() - 0.5 * (axisalignedbb.getMinX() + axisalignedbb.getMaxX()));
                        double yCost = Math.abs(eyeVec3.getYCoord() - 0.5 * (axisalignedbb.getMinY() + axisalignedbb.getMinY()));
                        double zCost = Math.abs(eyeVec3.getZCoord() - 0.5 * (axisalignedbb.getMinZ() + axisalignedbb.getMinZ()));
                        double sumCost = xCost + yCost + zCost;
                        if (eyeVec3.getXCoord() < x1) {
                            list.add(new Data(blockPos.west(), EnumFacing.WEST, xCost));
                        } else if (eyeVec3.getXCoord() > x2) {
                            list.add(new Data(blockPos.east(), EnumFacing.EAST, xCost));
                        }

//						if ( eyeVec3.yCoord < y1 ) {
//							list.add(new Data( blockPos.down(), EnumFacing.UP, yCost));
//						} else if ( eyeVec3.yCoord > y2 ) {
//							list.add(new Data( blockPos.up(), EnumFacing.DOWN, yCost ));
//						}

                        if (eyeVec3.getZCoord() < z1) {
                            list.add(new Data(blockPos.north(), EnumFacing.NORTH, zCost));
                        } else if (eyeVec3.getZCoord() > z2) {
                            list.add(new Data(blockPos.south(), EnumFacing.SOUTH, zCost));
                        }

                        Collections.sort(list);
                        double border = 0.05;
                        double x = MathHelper.clamp_double(eyeVec3.getXCoord(), x1 + border, x2 - border);
                        double y = MathHelper.clamp_double(eyeVec3.getYCoord(), y1 + border, y2 - border);
                        double z = MathHelper.clamp_double(eyeVec3.getZCoord(), z1 + border, z2 - border);
                        for (Data data : list) {
                            if (!world.isAirBlock(data.blockPos))
                                continue;
                            if (data.enumFacing == EnumFacing.WEST || data.enumFacing == EnumFacing.EAST) {
                                x = MathHelper.clamp_double(eyeVec3.getXCoord(), x1, x2);
                            } else if (data.enumFacing == EnumFacing.UP || data.enumFacing == EnumFacing.DOWN) {
                                y = MathHelper.clamp_double(eyeVec3.getYCoord(), y1, y2);
                            } else {
                                z = MathHelper.clamp_double(eyeVec3.getZCoord(), z1, z2);
                            }
                            targetVec3 = new Vec3(x, y, z);
                            break;
                        }

//						log(String.format("x: %s, y: %s, z: %s", x,y,z));
                    }
                }
            }
        }
        return targetVec3;
    }


}
