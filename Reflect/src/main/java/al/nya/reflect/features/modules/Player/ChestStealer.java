package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.math.TimerUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.enchantment.Enchantment;
import al.nya.reflect.wrapper.wraps.wrapper.enchantment.EnchantmentHelper;
import al.nya.reflect.wrapper.wraps.wrapper.item.*;

import java.util.Arrays;
import java.util.List;

/**
 * ChestStealer
 * Skid from SkidLine Client
 * Create Data 18/04/2022
 */
public class ChestStealer extends Module {
    //@OptionInfo(description = "Steal delay.", name = "Delay", priority = 0)
    public DoubleValue delay = new DoubleValue("Delay",500D,0D,50D,"0");
    //@OptionInfo(description = "Check chests.", name = "Chest Checker", priority = 1)
    public OptionValue check = new OptionValue("Check",true);
    private TimerUtil timer = new TimerUtil();
    private final List<String> chestName = Arrays.asList("Chest","Low","箱子");
    public ChestStealer() {
        super("ChestStealer",ModuleType.Player);
        addValue(delay);
        addValue(check);
    }

    @Override
    public void onEnable() {
        timer.reset();
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
        Container openContainer = mc.getThePlayer().getOpenContainer();
        if (!openContainer.isNull()) {

            if (ContainerChest.isContainerChest(openContainer)) {

                final ContainerChest container = new ContainerChest(openContainer.getWrapObject());

                if (check.getValue()) {
                    String name = container.getLowerChestInventory().getDisplayName().getUnformattedText().toLowerCase();
                    boolean target = false;
                    for (String s : chestName) {
                        if (s.equalsIgnoreCase(name)){
                            target = true;
                        }
                    }
                    if (!target)
                    return;
                }

                for (int i = 0; i < container.getLowerChestInventory().getSizeInventory(); ++i) {
                    if (!container.getLowerChestInventory().getStackInSlot(i).isNull() &&
                            timer.hasPassed(delay.getValue().longValue()) &&
                            (!(ItemArmor.isItemArmor(container.getLowerChestInventory().getStackInSlot(i).getItem())) ||
                                    betterCheck(container, container.getLowerChestInventory().getStackInSlot(i), i)) &&
                            (!(ItemSword.isItemSword(container.getLowerChestInventory().getStackInSlot(i).getItem())) ||
                                    (double) this.getDamage(container.getLowerChestInventory().getStackInSlot(i)) >= this.bestDamage(container, i))) {
                        mc.getPlayerController().windowClick(container.getWindowId(), i, 0, 1, mc.getThePlayer());
                        timer.reset();
                    }
                }
                if (isEmpty()) mc.getThePlayer().closeScreen();
            }
        }
    }
    private boolean isEmpty() {
        Container container = mc.getThePlayer().getOpenContainer();
        if (!container.isNull()) {
            if (ContainerChest.isContainerChest(container)) {
                ContainerChest containerChest = new ContainerChest(container.getWrapObject());
                for (int i = 0; i < containerChest.getLowerChestInventory().getSizeInventory(); ++i) {
                    ItemStack itemStack = containerChest.getLowerChestInventory().getStackInSlot(i);
                    if ((!itemStack.isNull())
                            && (!itemStack.getItem().isNull())
                            && (!(ItemArmor.isItemArmor(itemStack.getItem()))
                            || betterCheck(containerChest, itemStack, i))
                            && (!(ItemSword.isItemSword(itemStack.getItem()))
                            || (double) getDamage(itemStack) >= bestDamage(containerChest, i))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    private boolean betterCheck(ContainerChest c, ItemStack item, int slot) {
        Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
        double item1 = (double) (new ItemArmor(item.getItem().getWrapObject())).getDamageReduceAmount() + getProtectionValue(item);
        double item2 = 0.0D;
        int bestslot = 0;
        int i;
        String unlocalizedName = item.getUnlocalizedName();
        double temp;
        double var12;
        if (unlocalizedName.contains("helmet")) {
            for (i = 0; i < 45; ++i) {
                if (inventoryContainer.getSlot(i).getHasStack()) {
                    if (inventoryContainer.getSlot(i).getStack().getItem().getUnlocalizedName()
                            .contains("helmet")) {
                        var12 = (new ItemArmor(inventoryContainer.getSlot(i).getStack()
                                .getItem().getWrapObject())).getDamageReduceAmount();
                        temp = var12 + getProtectionValue(inventoryContainer.getSlot(i).getStack());
                        if (temp > item2) {
                            item2 = temp;
                            bestslot = i;
                        }
                    }
                }
            }

            for (i = 0; i < c.getLowerChestInventory().getSizeInventory(); ++i) {
                if (!c.getLowerChestInventory().getStackInSlot(i).isNull()
                        && c.getLowerChestInventory().getStackInSlot(i).getUnlocalizedName().contains("helmet")) {
                    temp = (double) (new ItemArmor(c.getLowerChestInventory().getStackInSlot(i)
                            .getItem().getWrapObject())).getDamageReduceAmount()
                            + getProtectionValue(c.getLowerChestInventory().getStackInSlot(i));
                    if (temp > item2) {
                        item2 = temp;
                        bestslot = i;
                    }
                }
            }
        }

        if (unlocalizedName.contains("chestplate")) {
            for (i = 0; i < 45; ++i) {
                if (inventoryContainer.getSlot(i).getHasStack()) {
                    if (inventoryContainer.getSlot(i).getStack().getItem().getUnlocalizedName()
                            .contains("chestplate")) {
                        var12 = (new ItemArmor(inventoryContainer.getSlot(i).getStack()
                                .getItem().getWrapObject())).getDamageReduceAmount();
                        temp = var12 + getProtectionValue(inventoryContainer.getSlot(i).getStack());
                        if (temp > item2) {
                            item2 = temp;
                            bestslot = i;
                        }
                    }
                }
            }

            for (i = 0; i < c.getLowerChestInventory().getSizeInventory(); ++i) {
                if (!c.getLowerChestInventory().getStackInSlot(i).isNull()
                        && c.getLowerChestInventory().getStackInSlot(i).getUnlocalizedName().contains("chestplate")) {
                    temp = (double) (new ItemArmor(c.getLowerChestInventory().getStackInSlot(i)
                            .getItem().getWrapObject())).getDamageReduceAmount()
                            + getProtectionValue(c.getLowerChestInventory().getStackInSlot(i));
                    if (temp > item2) {
                        item2 = temp;
                        bestslot = i;
                    }
                }
            }
        }

        if (unlocalizedName.contains("leggings")) {
            for (i = 0; i < 45; ++i) {
                if (inventoryContainer.getSlot(i).getHasStack()) {
                    if (inventoryContainer.getSlot(i).getStack().getItem().getUnlocalizedName()
                            .contains("leggings")) {
                        var12 = (new ItemArmor(inventoryContainer.getSlot(i).getStack()
                                .getItem().getWrapObject())).getDamageReduceAmount();
                        temp = var12 + getProtectionValue(inventoryContainer.getSlot(i).getStack());
                        if (temp > item2) {
                            item2 = temp;
                            bestslot = i;
                        }
                    }
                }
            }

            for (i = 0; i < c.getLowerChestInventory().getSizeInventory(); ++i) {
                if (!c.getLowerChestInventory().getStackInSlot(i).isNull()
                        && c.getLowerChestInventory().getStackInSlot(i).getUnlocalizedName().contains("leggings")) {
                    temp = (double) (new ItemArmor(c.getLowerChestInventory().getStackInSlot(i)
                            .getItem().getWrapObject())).getDamageReduceAmount()
                            + getProtectionValue(c.getLowerChestInventory().getStackInSlot(i));
                    if (temp > item2) {
                        item2 = temp;
                        bestslot = i;
                    }
                }
            }
        }

        if (unlocalizedName.contains("boots")) {
            for (i = 0; i < 45; ++i) {
                if (inventoryContainer.getSlot(i).getHasStack()) {
                    if (inventoryContainer.getSlot(i).getStack().getItem().getUnlocalizedName()
                            .contains("boots")) {
                        var12 = (new ItemArmor(inventoryContainer.getSlot(i).getStack()
                                .getItem().getWrapObject())).getDamageReduceAmount();
                        temp = var12 + getProtectionValue(inventoryContainer.getSlot(i).getStack());
                        if (temp > item2) {
                            item2 = temp;
                            bestslot = i;
                        }
                    }
                }
            }

            for (i = 0; i < c.getLowerChestInventory().getSizeInventory(); ++i) {
                if (!c.getLowerChestInventory().getStackInSlot(i).isNull()
                        && c.getLowerChestInventory().getStackInSlot(i).getUnlocalizedName().contains("boots")) {
                    temp = (double) (new ItemArmor(c.getLowerChestInventory().getStackInSlot(i)
                            .getItem().getWrapObject())).getDamageReduceAmount()
                            + getProtectionValue(c.getLowerChestInventory().getStackInSlot(i));
                    if (temp > item2) {
                        item2 = temp;
                        bestslot = i;
                    }
                }
            }
        }

        return item1 >= item2 && c.getLowerChestInventory().getStackInSlot(bestslot).equals(item);
    }

    private double getProtectionValue(ItemStack stack) {
        return ItemArmor.isItemArmor(stack.getItem()) ? (double) (new ItemArmor(stack.getItem().getWrapObject())).getDamageReduceAmount()
                + (double) ((100 - (new ItemArmor(stack.getItem().getWrapObject())).getDamageReduceAmount())
                * EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.getEffectId(), stack)) * 0.0075D
                : 0.0D;
    }
    private double bestDamage(ContainerChest container, int slot) {
        double bestDamage = 0.0D;
        Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
        int i;
        ItemStack is;
        for (i = 0; i < 45; ++i) {
            if (inventoryContainer.getSlot(i).getHasStack()) {
                is = inventoryContainer.getSlot(i).getStack();
                if (ItemSword.isItemSword(is.getItem()) && (double) getDamage(is) > bestDamage) {
                    bestDamage = getDamage(is);
                }
            }
        }
        IInventory lowerChestInventory = container.getLowerChestInventory();
        for (i = 0; i < lowerChestInventory.getSizeInventory(); ++i) {
            if (!lowerChestInventory.getStackInSlot(i).isNull()) {
                is = lowerChestInventory.getStackInSlot(i);
                if (i != slot && ItemSword.isItemSword(is.getItem()) && (double) getDamage(is) > bestDamage) {
                    bestDamage = getDamage(is);
                }
            }
        }

        return bestDamage;
    }
    private float getDamage(ItemStack stack) {
        float damage = 0.0F;
        Item item = stack.getItem();
        if (ItemTool.isItemTool(item)) {
            damage += getSpeed(stack);
        }

        if (ItemSword.isItemSword(item)) {
            damage += getAttackDamage(stack);
        } else {
            ++damage;
        }

        return damage;
    }
    private float getAttackDamage(ItemStack itemStack) {
        float damage = (new ItemSword(itemStack.getItem().getWrapObject())).getDamageVsEntity();
        damage += (float) EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.getEffectId(), itemStack) * 1.25F;
        damage += (float) EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.getEffectId(), itemStack) * 0.01F;
        return damage;
    }
    private float getSpeed(ItemStack stack) {
        return ToolMaterial.getEfficiency((new ItemTool(stack.getItem().getWrapObject())).getToolMaterial());
    }
}
