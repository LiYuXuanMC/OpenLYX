package al.logger.client.features.modules.impls.Player;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.item.ItemBlock;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;

public class FastPlace extends Module {
    private OptionValue blockOnly = new OptionValue("OnlyBlock", true);
    public FastPlace() {
        super("FastPlace", Category.Player);
        addValues(blockOnly);
    }
    @Listener
    public void onUpdate(EventLivingUpdate update){
        if (blockOnly.getValue()) {
            ItemStack item = mc.getThePlayer().getInventory().getCurrentItem();
            if (!item.isNull() && ItemBlock.isItemBlock(item.getItem()))
                mc.setRightClickDelayTimer(0);
        }else {
            mc.setRightClickDelayTimer(0);
        }
    }
}
