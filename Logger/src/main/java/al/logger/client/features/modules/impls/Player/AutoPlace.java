package al.logger.client.features.modules.impls.Player;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.render.EventDrawBlockHighlight;
import al.logger.client.event.client.render.EventRender3D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ChatUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.KeyBinding;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.block.BlockLiquid;
import al.logger.client.wrapper.LoggerMC.block.Blocks;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.item.ItemBlock;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.EnumFacing;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectType;
import al.logger.client.wrapper.lwjgl.Mouse;

public class AutoPlace extends Module {

    private DoubleValue delay = new DoubleValue("Delay", 10, 0, 0 , 1);
    private OptionValue onRight = new OptionValue("MouseDown", true);
    private long l = 0L;
    private int f = 0;
    private MovingObjectPosition lm = null;
    private BlockPos lp = null;


    public AutoPlace() {
        super("AutoPlace", Category.Player);
        addValues(delay, onRight);
    }


   @Listener
   public void onRender3D(EventRender3D e){
       EntityPlayer thePlayer = mc.getThePlayer();
       if (mc.getCurrentScreen().isNull() && !thePlayer.getCapabilities().isFlying()) {
           ItemStack i = thePlayer.getHeldItem();
           if (i != null && ItemBlock.isItemBlock(i.getItem())) {
               MovingObjectPosition m = mc.getObjectMouseOver();
               if (m != null && m.getTypeOfHit() == MovingObjectType.BLOCK
                       && (m.getSideHit_Enum() != EnumFacing.UP && m.getSideHit_Enum() != EnumFacing.DOWN)
                       || (m.getSideHit_Enum() == EnumFacing.NORTH || m.getSideHit_Enum() == EnumFacing.EAST
                       || m.getSideHit_Enum() == EnumFacing.SOUTH || m.getSideHit_Enum() == EnumFacing.WEST)) {
                   if (this.lm != null && this.f < delay.getValue()) {
                       ++this.f;
                   } else {
                       this.lm = m;
                       BlockPos pos = m.getBlockPos();
                       if (this.lp == null || pos.getX() != lp.getX() || pos.getY() != lp.getY() || pos.getZ() != lp.getZ()) {
                           Block b = mc.getTheWorld().getBlockState(pos).getBlock();
                           if (b != null && b != Blocks.air && !(BlockLiquid.isBlockLiquid(b))) {
                               if (!onRight.getValue() || Mouse.isButtonDown(1)) {
                                   long n = System.currentTimeMillis();
                                   if (n - this.l >= 25L) {
                                       this.l = n;
                                       if (mc.getPlayerController().onPlayerRightClick(mc.getThePlayer(), mc.getTheWorld(), i, pos, m.getSideHit_Enum(), m.getHitVec())) {
                                           //setMouseButtonState(1, true);
                                           mc.getGameSettings().getKeyBindRight().setPressed(true);
                                           mc.getThePlayer().swingItem();
                                           mc.getItemRenderer().resetEquippedProgress();
                                           //setMouseButtonState(1, false);
                                           mc.getGameSettings().getKeyBindRight().setPressed(false);
                                           this.lp = pos;
                                           this.f = 0;
                                       }
                                   }
                               }
                           }
                       }
                   }
               }
           }
       }
   }


}



