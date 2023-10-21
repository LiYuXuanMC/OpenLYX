package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.math.TimerUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.enchantment.Enchantment;
import al.nya.reflect.wrapper.wraps.wrapper.enchantment.EnchantmentHelper;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiChat;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiInventory;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;
import al.nya.reflect.wrapper.wraps.wrapper.item.Container;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemArmor;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;

public class AutoArmor extends Module {
    //@OptionInfo(description = "Auto armor mode selector.", name = "Mode", priority = 0)
    public ModeValue mode = new ModeValue("Mode",Mode.Vanilla,Mode.values());
    //@OptionInfo(description = "Wear delay.", name = "Delay", priority = 1)
    public DoubleValue delayV = new DoubleValue("Delay",500, 0.0, 1.0, "0.0");
    private TimerUtil timer = new TimerUtil();
    public AutoArmor() {
        super("AutoArmor",ModuleType.Player);
        addValues(mode,delayV);
    }

    @Override
    public void onEnable() {
        timer.reset();
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
        long delay = delayV.getValue().intValue();
        GuiScreen currentScreen = mc.getCurrentScreen();
        if (mode.getValue() == Mode.OpenInv && !(GuiInventory.isGuiInventory(currentScreen))) return;
        if ((currentScreen.isNull() || GuiInventory.isGuiInventory(currentScreen) || GuiChat.isGuiChat(currentScreen)) && timer.hasPassed(delay)) {
            Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
            for (int type = 1; type < 5; ++type) {
                if (inventoryContainer.getSlot(4 + type).getHasStack()) {

                    ItemStack is = inventoryContainer.getSlot(4 + type).getStack();

                    if (isBestArmor(is, type)) continue;

                    drop(4 + type);
                }
                for (int i = 9; i < 45; ++i) {
                    if (!inventoryContainer.getSlot(i).getHasStack()) continue;
                    ItemStack is = inventoryContainer.getSlot(i).getStack();
                    if (!isBestArmor(is, type) || !(getProtection(is) > 0.0f)) continue;
                    shiftClick(i);
                    timer.reset();
                    if (delayV.getValue().longValue() <= 0L) continue;
                    return;
                }
            }
        }
    }

    private boolean isBestArmor(ItemStack stack, int type) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        float prot = getProtection(stack);
        String strType = "";
        if (type == 1) {
            strType = "helmet";
        } else if (type == 2) {
            strType = "chestplate";
        } else if (type == 3) {
            strType = "leggings";
        } else if (type == 4) {
            strType = "boots";
        }
        if (!stack.getUnlocalizedName().contains(strType)) {
            return false;
        }
        for (int i = 5; i < 45; ++i) {
            if (!thePlayer.getInventoryContainer().getSlot(i).getHasStack()) continue;
            ItemStack is = thePlayer.getInventoryContainer().getSlot(i).getStack();
            if (!(getProtection(is) > prot) || !is.getUnlocalizedName().contains(strType)) continue;
            return false;
        }
        return true;
    }
    private void shiftClick(int slot) {
        mc.getPlayerController().windowClick(mc.getThePlayer().getInventoryContainer().getWindowId(), slot, 0, 1, mc.getThePlayer());
    }

    private void drop(int slot) {
        mc.getPlayerController().windowClick(mc.getThePlayer().getInventoryContainer().getWindowId(), slot, 1, 4, mc.getThePlayer());
    }
    private float getProtection(ItemStack stack) {
        float prot = 0.0f;
        if (ItemArmor.isItemArmor(stack.getItem())) {
            ItemArmor armor = new ItemArmor(stack.getItem().getWrapObject());
            prot = (float) ((double) prot
                    + ((double) armor.getDamageReduceAmount() + (double) ((100 - armor.getDamageReduceAmount())
                    * EnchantmentHelper.getEnchantmentLevel(Enchantment.fireProtection.getEffectId(), stack)) * 0.0075));
            prot = (float) ((double) prot
                    + (double) EnchantmentHelper.getEnchantmentLevel(Enchantment.blastProtection.getEffectId(), stack)
                    / 100.0);
            prot = (float) ((double) prot
                    + (double) EnchantmentHelper.getEnchantmentLevel(Enchantment.fireProtection.getEffectId(), stack)
                    / 100.0);
            prot = (float) ((double) prot
                    + (double) EnchantmentHelper.getEnchantmentLevel(Enchantment.thorns.getEffectId(), stack) / 100.0);
            prot = (float) ((double) prot
                    + (double) EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.getEffectId(), stack) / 50.0);
            prot = (float) ((double) prot
                    + (double) EnchantmentHelper.getEnchantmentLevel(Enchantment.featherFalling.getEffectId(), stack)
                    / 100.0);
        }
        return prot;
    }
    public enum Mode{
        Vanilla,
        OpenInv
    }
}
