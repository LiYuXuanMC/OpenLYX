package al.logger.client.features.modules.impls.Movement;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.player.EventJump;
import al.logger.client.event.client.player.EventUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.ModuleCarrier;
import al.logger.client.utils.misc.MathHelper;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.block.BlockLiquid;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;

import java.util.function.Predicate;

import static al.logger.client.utils.player.BlockUtils.getBlock;

public class Jesus extends Module {
    private boolean nextTick = false;

    ModeValue mode = new ModeValue("Mode" ,Mode.Matrix , Mode.values());
    OptionValue nojump = new OptionValue("NoJump" , false);
    public Jesus() {
        super("Jesus" , Category.Movement);
        addValues(mode , nojump);
    }

    @Override
    public void onDisable() {
        if (mc.getThePlayer().isInWater()){
            MovementUtils.strafe(0.2f);
        }
        super.onDisable();
    }



    @Listener
    public void onUpdate(EventUpdate e){
        EntityPlayer thePlayer = Minecraft.getMinecraft().getThePlayer();
        if (thePlayer.isNull() || thePlayer.isSneaking()){
            return;
        }

        if (mode.getValue() == Mode.Matrix){
            if (thePlayer.isInWater()){
                thePlayer.setMotionY(0);
                MovementUtils.strafe(0.6f);
            }
        }
    }

    @Listener
    public void onPacket(EventPacket event) {
        EntityPlayer ThePlayer = Minecraft.getMinecraft().getThePlayer();
        if (ThePlayer.isNull()) {
            return;
        }
        /*
        Packet packet = new Packet(event.getPacket().getWrappedObject());
        if (C03PacketPlayer.isPacketPlayer(packet)) {
            AxisAlignedBB boundingBox = new AxisAlignedBB(
                    ThePlayer.getEntityBoundingBox().getMaxX(),
                    ThePlayer.getEntityBoundingBox().getMaxY(),
                    ThePlayer.getEntityBoundingBox().getMaxZ(),
                    ThePlayer.getEntityBoundingBox().getMinX(),
                    ThePlayer.getEntityBoundingBox().getMinY() - 0.01,
                    ThePlayer.getEntityBoundingBox().getMinZ()
            );

            boolean collidesWithLiquid = false;
            BlockPos blockPos = new BlockPos(mc.getThePlayer().getPosX(), mc.getThePlayer().getPosY() - 0.01, mc.getThePlayer().getPosZ());
            Block block = mc.getTheWorld().getBlockState(blockPos).getBlock();

            if (BlockLiquid.isBlockLiquid(block)){
                collidesWithLiquid = collideBlock(boundingBox , (Predicate<Block>) block);
            }


            if (collidesWithLiquid) {
                nextTick = !nextTick;
                if (nextTick) {
                    C03PacketPlayer c03 = new C03PacketPlayer(packet.getWrappedObject());
                    c03.setY(c03.getY() - 0.001);
                }
            }
        }

         */
    }

    @Listener
    public void onJump(EventJump e){
        if (mc.getThePlayer().isNull()) {
            return;
        }

        BlockPos blockPos = new BlockPos(mc.getThePlayer().getPosX(), mc.getThePlayer().getPosY() - 0.01, mc.getThePlayer().getPosZ());
        Block block = mc.getTheWorld().getBlockState(blockPos).getBlock();

        if (nojump.getValue() && BlockLiquid.isBlockLiquid(block)) {
            e.cancel();
        }
    }

    public boolean collideBlock(AxisAlignedBB axisAlignedBB, Predicate<Block> collide) {
        EntityPlayer thePlayer = Minecraft.getMinecraft().getThePlayer();
        for (int x = MathHelper.floor_double(thePlayer.getEntityBoundingBox().getMinX());
             x < MathHelper.floor_double(thePlayer.getEntityBoundingBox().getMaxX()) + 1; x++) {
            for (int z = MathHelper.floor_double(thePlayer.getEntityBoundingBox().getMinZ());
                 z < MathHelper.floor_double(thePlayer.getEntityBoundingBox().getMaxZ()) + 1; z++) {
                Block block = getBlock(new BlockPos(x, axisAlignedBB.getMinY(), z));

                if (!collide.test(block)) {
                    return false;
                }
            }
        }

        return true;
    }

    enum Mode{
        Matrix
    }
}
