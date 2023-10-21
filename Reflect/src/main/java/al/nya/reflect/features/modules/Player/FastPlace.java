package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.item.Item;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemBlock;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;

public class FastPlace extends Module {
    private OptionValue blockOnly = new OptionValue("OnlyBlock", true);
    public FastPlace() {
        super("FastPlace",ModuleType.Player);
        addValues(blockOnly);
    }
    @EventTarget
    public void onUpdate(EventUpdate update){
        if (blockOnly.getValue()) {
            ItemStack item = mc.getThePlayer().getInventory().getCurrentItem();
            if (!item.isNull() && ItemBlock.isItemBlock(item.getItem()))
                mc.setRightClickDelayTimer(0);
        }else {
            mc.setRightClickDelayTimer(0);
        }
    }
}
