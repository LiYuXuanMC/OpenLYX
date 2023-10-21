package al.nya.reflect.features.modules.adaptive.v1_12_2;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Player.AutoTool;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.wrapper.wraps.wrapper.block.Block;
import al.nya.reflect.wrapper.wraps.wrapper.entity.InventoryPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectPosition;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectType;

public class AutoTool1_12_2 extends AutoTool {
    @EventTarget
    @Override
    public void onUpdate(EventUpdate event) {
        MovingObjectPosition objectMouseOver = mc.getObjectMouseOver();
        if (objectMouseOver.isNull() || !mc.getThePlayer().getActiveItemStack().getItem().isNull())
            return;
        if (mc.getGameSettings().getKeyBindAttack().isKeyDown()) {
            if (objectMouseOver.getTypeOfHit() == MovingObjectType.BLOCK) {
                updateTool(mc.getTheWorld().getBlockState(objectMouseOver.getBlockPos()).getBlock());
            }
            if (objectMouseOver.getTypeOfHit() == MovingObjectType.ENTITY) {
                updateSword();
            }
        }
    }

    @Override
    protected void updateTool(Block block) {

        float strength = 1F;
        int slot = -1;
        InventoryPlayer inventory = mc.getThePlayer().getInventory();
        for (int i = 0; i < 9; ++i) {
            final ItemStack itemStack = inventory.getMainInventory()[i];
            if (itemStack.isNull() || itemStack.getDestroySpeed(block.getDefaultBlockState()) <= strength) continue;
            strength = itemStack.getDestroySpeed(block.getDefaultBlockState());
            slot = i;
        }

        if (slot != -1 && inventory.currentItem() != slot) {
            inventory.currentItem(slot);
        }
    }
}
