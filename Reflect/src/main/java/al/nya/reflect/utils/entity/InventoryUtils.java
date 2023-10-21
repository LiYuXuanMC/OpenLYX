package al.nya.reflect.utils.entity;

import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.InventoryPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.item.Container;
import al.nya.reflect.wrapper.wraps.wrapper.item.Item;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;

public class InventoryUtils {
    public static int findItem(int startSlot, int endSlot, Item item){
        Container inventoryContainer = Minecraft.getMinecraft().getThePlayer().getInventoryContainer();
        for (int i = startSlot; i < endSlot; i++) {
            ItemStack stack = inventoryContainer.getSlot(i).getStack();
            if (!stack.isNull() && stack.getItem().equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean hasSpaceHotbar() {
        Container inventoryContainer = Minecraft.getMinecraft().getThePlayer().getInventoryContainer();
        for (int i = 36; i < 44; i++) {
            ItemStack stack = inventoryContainer.getSlot(i).getStack();
            if (stack.isNull()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPositivePotionEffect(int id) {
        if (id == Potion.regeneration.getId() || id == Potion.moveSpeed.getId() ||
                id == Potion.heal.getId() || id == Potion.nightVision.getId() ||
                id == Potion.jump.getId() || id == Potion.invisibility.getId() ||
                id == Potion.resistance.getId() || id == Potion.waterBreathing.getId() ||
                id == Potion.absorption.getId() || id == Potion.digSpeed.getId() ||
                id == Potion.damageBoost.getId() || id == Potion.healthBoost.getId() ||
                id == Potion.fireResistance.getId()) {
            return true;
        }
        return false;
    }
}
