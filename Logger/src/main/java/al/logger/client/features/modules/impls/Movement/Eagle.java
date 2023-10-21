package al.logger.client.features.modules.impls.Movement;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.GameSettings;
import al.logger.client.wrapper.LoggerMC.KeyBinding;
import al.logger.client.wrapper.LoggerMC.block.Blocks;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.item.ItemBlock;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.world.WorldClient;

public class Eagle extends Module {
    private final OptionValue onlyBlocks = new OptionValue("OnlyBlocks", true);
    private final OptionValue onlyLookingDown = new OptionValue("OnlyLookingDown", false);

    private boolean resetFlag = true;
    public Eagle() {
        super("Eagle", Category.Movement);
        addValues(onlyBlocks, onlyLookingDown);
    }
    @Listener
    public void onUpdate(EventLivingUpdate update){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        WorldClient theWorld = mc.getTheWorld();
        if (!thePlayer.isNull() && !theWorld.isNull()) {
            ItemStack heldItem = thePlayer.getCurrentEquippedItem();
            BlockPos belowPlayer = new BlockPos(thePlayer.getPosX(), thePlayer.getPosY() - 1D, thePlayer.getPosZ());
            if ((!onlyBlocks.getValue() || (!heldItem.isNull() && ItemBlock.isItemBlock(heldItem.getItem())))
                    && (!onlyLookingDown.getValue() || thePlayer.getRotationPitch() > 45)
                    && thePlayer.isOnGround()) {
                KeyBinding keyBindSneak = mc.getGameSettings().getKeyBindSneak();
                keyBindSneak.setPressed(false);

                if (theWorld.getBlockState(belowPlayer).getBlock().equals(Blocks.air)) {
                    keyBindSneak.setPressed(true);
                }

                resetFlag = false;
            } else {
                if (!resetFlag) {
                    KeyBinding keyBindSneak = mc.getGameSettings().getKeyBindSneak();
                    keyBindSneak.setPressed(false);
                    resetFlag = true;
                }
            }
        }

    }
}
