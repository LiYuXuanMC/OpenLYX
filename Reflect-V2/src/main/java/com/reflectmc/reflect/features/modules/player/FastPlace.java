package com.reflectmc.reflect.features.modules.player;

import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.player.EventLivingUpdate;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.OptionValue;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.ItemBlock;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.ItemStack;

public class FastPlace extends Module {
    private OptionValue blockOnly = new OptionValue("OnlyBlock", true);
    public FastPlace() {
        super("FastPlace", Category.Player);
        addValues(blockOnly);
    }
    @EventTarget
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
