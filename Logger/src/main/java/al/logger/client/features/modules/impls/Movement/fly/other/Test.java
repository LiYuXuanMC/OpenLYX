package al.logger.client.features.modules.impls.Movement.fly.other;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.player.EventMove;
import al.logger.client.event.client.player.EventPostUpdate;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.event.client.player.EventUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.impls.World.Scaffold;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.misc.MathHelper;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.utils.rotation.RotationUtils;
import al.logger.client.wrapper.LoggerMC.block.*;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.item.ItemBlock;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;
import al.logger.client.wrapper.LoggerMC.network.NetworkManager;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C04PacketPlayerPosition;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C05PacketPlayerLook;
import al.logger.client.wrapper.LoggerMC.network.server.S08.S08PacketPlayerPosLook;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.EnumFacing;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectType;
import al.logger.client.wrapper.LoggerMC.utils.Vec3;
import al.logger.client.wrapper.LoggerMC.world.WorldClient;

import java.util.Random;

public class Test extends Module {
    private BlockData blockData;
    private int startY, stage, slot;
    private boolean place, flag, onGround, reverse;
    private double[] startPos;
    private int packets;

    public Test(){
        super("Test" , Category.Movement);
    }

    @Listener
    public void onPre(EventPreUpdate event) {
            EntityPlayer thePlayer = mc.getThePlayer();
            if (!place) {
                if (slot != -1) {
                    if (thePlayer.getPosY() >= startY + 1) {
                        place = true;
                        thePlayer.getInventory().currentItem(slot);
                    }
                } else {
                    thePlayer.setMotionY(0);
                    stage++;
                }
            }

            switch (stage) {
                case 1:
                    thePlayer.setPosition(thePlayer.getPosX(), MathHelper.floor_double(event.getY()) + 0.125, thePlayer.getPosZ());
                    break;
                case 3:
                    damage();
                    event.setY(MathHelper.floor_double(event.getY()) - 0.0625);
                    break;
            }

            double distance;

            if (flag) {

                distance = thePlayer.getDistance(startPos[0], startPos[1], startPos[2]);

                if (MathHelper.floor_double(distance) % 5 == 0) {
                    if (!reverse) {
                        onGround = !onGround;
                        reverse = true;
                    }
                } else {
                    reverse = false;
                }

                thePlayer.setCameraYaw(0.09F);
            } else {
                //if (stage > 3) event.setCancel(true);
                event.setPitch(90);
            }

            event.setOnGround(flag ? onGround : new Random().nextBoolean());
        }

        @Listener
        public void onPost(EventPostUpdate e){
            if (place) {
                EntityPlayerSP thePlayer = mc.getThePlayer();
                thePlayer.swingItem();
                mc.getPlayerController().onPlayerRightClick(thePlayer, mc.getTheWorld(), thePlayer.getInventory().getMainInventory()[thePlayer.getInventory().currentItem()], blockData.getBlockPos(), blockData.getEnumFacing(), blockData.getVec());
                place = false;
                stage = 0;
                slot = -1;
            }
        }

        @Listener
        public void onMove(EventMove e){
        if (flag)
            MovementUtils.setSpeed(e, MovementUtils.getBaseMoveSpeed());
        else {
            MovementUtils.setSpeed(e, 0);
        }
        }

    private void damage() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        NetworkManager networkManager = mc.getNetHandler().getNetworkManager();
        final double fallHeight = 3.0625, offset = .0625;
        for (int i = 0; i <= (fallHeight / offset); i++) {
            networkManager.sendPacketNoEvent(new C04PacketPlayerPosition(thePlayer.getPosX(), MathHelper.floor_double(thePlayer.getPosY()) + offset, thePlayer.getPosZ(), false));
            networkManager.sendPacketNoEvent(new C04PacketPlayerPosition(thePlayer.getPosX(), MathHelper.floor_double(thePlayer.getPosY()), thePlayer.getPosZ(), (i == fallHeight / offset)));
        }
        networkManager.sendPacketNoEvent(new C04PacketPlayerPosition(thePlayer.getPosX(), MathHelper.floor_double(thePlayer.getPosY()) + .1249999999999, thePlayer.getPosZ(), false));
        networkManager.sendPacketNoEvent(new C04PacketPlayerPosition(thePlayer.getPosX(), MathHelper.floor_double(thePlayer.getPosY()) + .12268518518519, thePlayer.getPosZ(), false));
    }

    @Override
    public void onEnable() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (!thePlayer.isOnGround()) {
            ChatUtils.message("You Must Stand on a block");
            setEnabled(false);
            return;
        }

        slot = getBlockSlot();

        if (slot == -1) {
            ChatUtils.message("You need at least 1 block.");
            setEnabled(false);
            return;
        }

        if (!searchData(new BlockPos(thePlayer))) {
            ChatUtils.message("Flight Failed");
            setEnabled(false);
            return;
        }

        startY = MathHelper.floor_double(thePlayer.getPosY());

        thePlayer.setPosition(MathHelper.floor_double(thePlayer.getPosX()) + .5, thePlayer.getPosY(), MathHelper.floor_double(thePlayer.getPosZ()) + .5);

        place = false;
        stage = 0;
        flag = false;
        onGround = true;
        reverse = false;
        startPos = null;

        thePlayer.jump();
        super.onEnable();
    }

    private int getBlockSlot() {
        int slot = -1;
        EntityPlayerSP thePlayer = mc.getThePlayer();
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = thePlayer.getInventory().getMainInventory()[i];
            if (itemStack.isNull() || !(ItemBlock.isItemBlock(itemStack.getItem()))) continue;
            if (!Scaffold.isValid(itemStack.getItem()))
                continue;
            slot = i;
            break;
        }
        return slot;
    }


    @Override
    public void onDisable() {
        //ClientUtil.resetTimer();

        packets = 0;
        super.onDisable();
    }

    @Listener
    public void onPacket(EventPacket e) {
        Packet packet = new Packet(e.getPacket().getWrappedObject());

        if (S08PacketPlayerPosLook.isS08PacketPlayerPosLook(packet)) {
            EntityPlayerSP thePlayer = mc.getThePlayer();
            NetworkManager netHandler = thePlayer.getSendQueue().getNetworkManager();
            S08PacketPlayerPosLook playerPosLook = new S08PacketPlayerPosLook(packet.getWrappedObject());
            if (!flag) {
                flag = true;
                startPos = new double[]{
                        playerPosLook.getX(),
                        playerPosLook.getY(),
                        playerPosLook.getZ()
                };

                e.cancel();
                netHandler.sendPacketNoEvent(new C04PacketPlayerPosition(playerPosLook.getX(), playerPosLook.getY(), playerPosLook.getZ(), false));
                netHandler.sendPacketNoEvent(new C05PacketPlayerLook(playerPosLook.getYaw(), playerPosLook.getPitch(), false));
                thePlayer.setPosition(playerPosLook.getX(), playerPosLook.getY(), playerPosLook.getZ()); // no rotate
            }
        }
        super.onPacket(e);
    }

    private boolean searchData(BlockPos blockPos) { // search Data as Scaffold.
        EntityPlayerSP thePlayer = mc.getThePlayer();
        WorldClient theWorld = mc.getTheWorld();
        if (!isReplaceable(blockPos)) return false;

        Vec3 eyesPos = new Vec3(thePlayer.getPosX(), thePlayer.getEntityBoundingBox().getMinY() + thePlayer.getEyeHeight(), thePlayer.getPosZ());

        BlockData findData = null;

        for (Enum<?> side : EnumFacing.values()) {

            final BlockPos aroundBlock = blockPos.offset(side);

            if (!isSolid(aroundBlock)) continue;

            final Vec3 dirVec = new Vec3(EnumFacing.getDirectionVec(side));

            for (double xSearch = .1; xSearch < .9; xSearch += .1) {
                for (double ySearch = .1; ySearch < .9; ySearch += .1) {
                    for (double zSearch = .1; zSearch < .9; zSearch += .1) {

                        final Vec3 posVec = new Vec3(blockPos).addVector(xSearch, ySearch, zSearch);
                        final Vec3 hitVec = posVec.add(new Vec3(dirVec.getXCoord() * .5, dirVec.getYCoord() * .5, dirVec.getZCoord() * .5));
                        final double distanceSqPosVec = eyesPos.squareDistanceTo(posVec);

                        if (eyesPos.squareDistanceTo(hitVec) > 18 || distanceSqPosVec > eyesPos.squareDistanceTo(posVec.add(dirVec)) || !theWorld.rayTraceBlocks(eyesPos, hitVec, false, true, false).isNull())
                            continue;

                        final float[] rotation = RotationUtils.getRotationFromEyeHasPrev(hitVec.getXCoord(), hitVec.getYCoord(), hitVec.getZCoord());
                        final Vec3 rotationVector = RotationUtils.getVectorForRotation(rotation);
                        final Vec3 vector = eyesPos.addVector(rotationVector.getXCoord() * 4, rotationVector.getYCoord() * 4, rotationVector.getZCoord() * 4);
                        final MovingObjectPosition obj = theWorld.rayTraceBlocks(eyesPos, vector, false, false, true);

                        if (!(obj.getTypeOfHit() == MovingObjectType.BLOCK) && obj.getBlockPos().equals(aroundBlock))
                            continue;

                        findData = new BlockData(aroundBlock, EnumFacing.getOpposite(side), hitVec);
                    }
                }
            }
        }

        if (findData == null) return false;
        blockData = findData;

        return true;
    }

    private boolean isReplaceable(BlockPos pos) {
        WorldClient world = mc.getTheWorld();
        return BlockTallGrass.isBlockTallGrass(world.getBlockState(pos).getBlock()) || world.getBlockState(pos).getBlock().getMaterial().isReplaceable();
    }

    private boolean isSolid(BlockPos pos) {
        WorldClient world = mc.getTheWorld();
        Block block = world.getBlockState(pos).getBlock();
        return (block.getMaterial().isSolid() || !block.isTranslucent() || block.isVisuallyOpaque() || BlockLadder.isBlockLaddeer(block) || BlockCarpet.isBlockCarpet(block) || BlockSnow.isBlockSnow(block) || BlockSkull.isBlockSkull(block)) && !block.getMaterial().isLiquid() && !(BlockContainer.isBlockContainer(block));
    }
    private static class BlockData {
        private final BlockPos pos;
        private final Enum<?> facing;
        private Vec3 vec;

        public BlockData(final BlockPos pos, final Enum<?> facing, Vec3 vec) {
            this.pos = pos;
            this.facing = facing;
            this.vec = vec;
        }

        public Vec3 getVec() {
            return this.vec;
        }

        public void setVec(final Vec3 vec) {
            this.vec = vec;
        }

        public BlockPos getBlockPos() {
            return this.pos;
        }

        public Enum<?> getEnumFacing() {
            return this.facing;
        }
    }
}
