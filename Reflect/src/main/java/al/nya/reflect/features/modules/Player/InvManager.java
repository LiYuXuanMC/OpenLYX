package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.math.TimerUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.block.Blocks;
import al.nya.reflect.wrapper.wraps.wrapper.enchantment.Enchantment;
import al.nya.reflect.wrapper.wraps.wrapper.enchantment.EnchantmentHelper;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiChat;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiContainer;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiInventory;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;
import al.nya.reflect.wrapper.wraps.wrapper.item.*;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;
import al.nya.reflect.wrapper.wraps.wrapper.potion.PotionEffect;

public class InvManager extends Module {
    //@OptionInfo(description = "Manage mode selector.", name = "Mode", priority = 0)
    public ModeValue mode = new ModeValue("Mode", Mode.Vanilla, Mode.values());
    //@OptionInfo(description = "Manage delay.", name = "Delay", priority = 1)
    public DoubleValue delayV = new DoubleValue("Delay", 500.0, 0.0, 1.0, "0.0");
    //@OptionInfo(description = "Keep food.", name = "Food", priority = 2)
    public OptionValue food = new OptionValue("KeepFoods",true);
    //@OptionInfo(description = "UHC mode.", name = "UHC", priority = 3)
    public OptionValue uhc = new OptionValue("UHC",false);

    public static int weaponSlot = 36;
    public static int pickaxeSlot = 37;
    public static int axeSlot = 38;
    public static int shovelSlot = 39;
    private TimerUtil timer = new TimerUtil();

    public InvManager() {
        super("InvManager",ModuleType.Player);
        addValues(mode,delayV,food,uhc);
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
        Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
        GuiScreen currentScreen = mc.getCurrentScreen();
        if (!(ContainerChest.isContainerChest(mc.getThePlayer().getOpenContainer())) || !(GuiContainer.isGuiContainer(currentScreen))) {
            long delay = delayV.getValue().longValue();
            if (mode.getValue() == Mode.Vanilla || GuiInventory.isGuiInventory(currentScreen)) {
                if (currentScreen.isNull() || GuiInventory.isGuiInventory(currentScreen)
                        || GuiChat.isGuiChat(currentScreen)) {
                    if (timer.hasPassed(delay) && weaponSlot >= 36) {
                        if (!inventoryContainer.getSlot(weaponSlot).getHasStack()) {
                            getBestWeapon(weaponSlot);
                        } else {
                            if (!isBestWeapon(inventoryContainer.getSlot(weaponSlot).getStack())) {
                                getBestWeapon(weaponSlot);
                            }
                        }
                    }

                    if (timer.hasPassed(delay) && pickaxeSlot >= 36) {
                        getBestPickaxe();
                    }

                    if (timer.hasPassed(delay) && shovelSlot >= 36) {
                        getBestShovel();
                    }

                    if (timer.hasPassed(delay) && axeSlot >= 36) {
                        getBestAxe();
                    }
                    if (timer.hasPassed(delay)) {
                        for (int i = 9; i < 45; ++i) {
                            if (inventoryContainer.getSlot(i).getHasStack()) {
                                ItemStack is = inventoryContainer.getSlot(i).getStack();
                                if (shouldDrop(is, i)) {
                                    drop(i);
                                    if (delay > 0L) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void swap(int slot1, int hotbarSlot) {
        mc.getPlayerController().windowClick(mc.getThePlayer().getInventoryContainer().getWindowId(), slot1, hotbarSlot, 2, mc.getThePlayer());
    }

    public void drop(int slot) {
        mc.getPlayerController().windowClick(mc.getThePlayer().getInventoryContainer().getWindowId(), slot, 1, 4, mc.getThePlayer());
        timer.reset();
    }
    public boolean isBestWeapon(ItemStack stack) {
        Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
        float damage = this.getDamage(stack);
        for (int i = 9; i < 45; ++i) {
            if (inventoryContainer.getSlot(i).getHasStack()) {
                ItemStack is = inventoryContainer.getSlot(i).getStack();
                if (this.getDamage(is) > damage && (ItemSword.isItemSword(is.getItem()))) {
                    return false;
                }
            }
        }
        if (!(ItemSword.isItemSword(stack.getItem()))) {
            return false;
        } else {
            return true;
        }
    }
    public void getBestWeapon(int slot) {
        Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
        for (int i = 9; i < 45; ++i) {
            if (inventoryContainer.getSlot(i).getHasStack()) {
                ItemStack is = inventoryContainer.getSlot(i).getStack();
                if (isBestWeapon(is) && getDamage(is) > 0.0F && ItemSword.isItemSword(is.getItem())) {
                    swap(i, slot - 36);
                    timer.reset();
                    break;
                }
            }
        }

    }
    private float getDamage(ItemStack stack) {
        float damage = 0.0F;
        Item item = stack.getItem();
        if (ItemTool.isItemTool(item)) {
            ItemTool sword = new ItemTool(item.getWrapObject());
            damage += (float) sword.getMaxDamage();
        }

        if (ItemSword.isItemSword(item)) {
            ItemSword sword1 = new ItemSword(item.getWrapObject());
            damage += sword1.getDamageVsEntity();
        }

        damage += (float) EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.getEffectId(), stack) * 1.25F
                + (float) EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.getEffectId(), stack) * 0.01F;
        return damage;
    }
    public boolean shouldDrop(ItemStack stack, int slot) {
        String displayName = stack.getDisplayName().toLowerCase();
        if (displayName.contains("(right click)")) {
            return false;
        } else {
            if (uhc.getValue()) {
                if (displayName.contains("ͷ")) {return false;}
                if (displayName.contains("apple")) {return false;}
                if (displayName.contains("head")) {return false;}
                if (displayName.contains("gold")) {return false;}
                if (displayName.contains("crafting table")) {return false;}
                if (displayName.contains("stick")) {return false;}
                if (displayName.contains("and") && displayName.contains("ril")) {return false;}
                if (displayName.contains("axe of perun")) {return false;}
                if (displayName.contains("barbarian")) {return false;}
                if (displayName.contains("bloodlust")) {return false;}
                if (displayName.contains("dragonchest")) {return false;}
                if (displayName.contains("dragon sword")) {return false;}
                if (displayName.contains("dragon armor")) {return false;}
                if (displayName.contains("excalibur")) {return false;}
                if (displayName.contains("exodus")) {return false;}
                if (displayName.contains("fusion armor")) {return false;}
                if (displayName.contains("hermes boots")) {return false;}
                if (displayName.contains("hide of leviathan")) {return false;}
                if (displayName.contains("scythe")) {return false;}
                if (displayName.contains("seven-league boots")) {return false;}
                if (displayName.contains("shoes of vidar")) {return false;}
                if (displayName.contains("apprentice")) {return false;}
                if (displayName.contains("master")) {return false;}
                if (displayName.contains("vorpal")) {return false;}
                if (displayName.contains("enchanted")) {return false;}
                if (displayName.contains("spiked")) {return false;}
                if (displayName.contains("tarnhelm")) {return false;}
                if (displayName.contains("philosopher")) {return false;}
                if (displayName.contains("anvil")) {return false;}
                if (displayName.contains("panacea")) {return false;}
                if (displayName.contains("fusion")) {return false;}
                if (displayName.contains("excalibur")) {return false;}
                if (displayName.contains("backpack")) {return false;}
                if (displayName.contains("hermes")) {return false;}
                if (displayName.contains("barbarian")) {return false;}
            }
            Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
            if (slot == weaponSlot) {
                if (isBestWeapon(inventoryContainer.getSlot(weaponSlot).getStack()) && weaponSlot >= 0) {
                    return false;
                }
            }
            if (slot == pickaxeSlot) {
                if (isBestPickaxe(inventoryContainer.getSlot(pickaxeSlot).getStack())
                        && pickaxeSlot >= 0) {
                    return false;
                }
            }
            if (slot == axeSlot) {
                if (isBestAxe(inventoryContainer.getSlot(axeSlot).getStack()) && axeSlot >= 0) {
                    return false;
                }
            }
            if (slot == shovelSlot) {
                if (isBestShovel(inventoryContainer.getSlot(shovelSlot).getStack())
                        && shovelSlot >= 0) {
                    return false;
                }
            }

            if (ItemArmor.isItemArmor(stack.getItem())) {
                for (int type = 1; type < 5; ++type) {
                    if (inventoryContainer.getSlot(4 + type).getHasStack()) {
                        ItemStack is = inventoryContainer.getSlot(4 + type).getStack();
                        if (isBestArmor(is, type)) {
                            continue;
                        }
                    }

                    if (isBestArmor(stack, type)) {
                        return false;
                    }
                }
            }

            if (ItemPotion.isItemPotion(stack.getItem()) && isBadPotion(stack)) {
                return true;
            } else if (ItemFood.isItemFood(stack.getItem()) && !food.getValue()
                    && !(ItemAppleGold.isItemAppleGold(stack.getItem()))) {
                return true;
            } else if (!(ItemHoe.isItemHoe(stack.getItem())) && !(ItemTool.isItemTool(stack.getItem()))
                    && !(ItemSword.isItemSword(stack.getItem())) && !(ItemArmor.isItemArmor(stack.getItem()))) {
                String unlocalizedName = stack.getUnlocalizedName();
                if (!unlocalizedName.contains("tnt")
                        && !unlocalizedName.contains("stick")
                        && !unlocalizedName.contains("egg")
                        && !unlocalizedName.contains("string")
                        && !unlocalizedName.contains("cake")
                        && !unlocalizedName.contains("mushroom")
                        && !unlocalizedName.contains("flint")
                        && !unlocalizedName.contains("dyePowder")
                        && !unlocalizedName.contains("feather")
                        && !unlocalizedName.contains("bucket")
                        && (!unlocalizedName.contains("chest")
                        || stack.getDisplayName().toLowerCase().contains("collect"))
                        && !unlocalizedName.contains("snow")
                        && !unlocalizedName.contains("fish")
                        && !unlocalizedName.contains("enchant")
                        && !unlocalizedName.contains("exp")
                        && !unlocalizedName.contains("shears")
                        && !unlocalizedName.contains("anvil")
                        && !unlocalizedName.contains("torch")
                        && !unlocalizedName.contains("seeds")
                        && !unlocalizedName.contains("leather")
                        && !unlocalizedName.contains("reeds")
                        && !unlocalizedName.contains("skull")
                        && !unlocalizedName.contains("record")
                        && !unlocalizedName.contains("snowball")
                        && !(ItemGlassBottle.isItemGlassBottle(stack.getItem()))
                        && !unlocalizedName.contains("piston")) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }

    private void getBestPickaxe() {
        Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
        for (int i = 9; i < 45; ++i) {
            if (inventoryContainer.getSlot(i).getHasStack()) {
                ItemStack is = inventoryContainer.getSlot(i).getStack();
                if (isBestPickaxe(is) && pickaxeSlot != i && !isBestWeapon(is)) {
                    if (!inventoryContainer.getSlot(pickaxeSlot).getHasStack()) {
                        swap(i, pickaxeSlot - 36);
                        timer.reset();
                        if (delayV.getValue().longValue() > 0L) {
                            return;
                        }
                    } else {
                        if (!isBestPickaxe(inventoryContainer.getSlot(pickaxeSlot).getStack())) {
                            swap(i, pickaxeSlot - 36);
                            timer.reset();
                            if (delayV.getValue().longValue() > 0L) {
                                return;
                            }
                        }
                    }
                }
            }
        }

    }
    private void getBestShovel() {
        Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
        for (int i = 9; i < 45; ++i) {
            if (inventoryContainer.getSlot(i).getHasStack()) {
                ItemStack is = inventoryContainer.getSlot(i).getStack();
                if (isBestShovel(is) && shovelSlot != i && !isBestWeapon(is)) {
                    if (!inventoryContainer.getSlot(shovelSlot).getHasStack()) {
                        swap(i, shovelSlot - 36);
                        timer.reset();
                        if (delayV.getValue().longValue() > 0L) {
                            return;
                        }
                    } else {
                        if (!isBestShovel(inventoryContainer.getSlot(shovelSlot).getStack())) {
                            swap(i, shovelSlot - 36);
                            timer.reset();
                            if (delayV.getValue().longValue() > 0L) {
                                return;
                            }
                        }
                    }
                }
            }
        }

    }
    private void getBestAxe() {
        Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
        for (int i = 9; i < 45; ++i) {
            if (inventoryContainer.getSlot(i).getHasStack()) {
                ItemStack is = inventoryContainer.getSlot(i).getStack();
                if (isBestAxe(is) && axeSlot != i && !isBestWeapon(is)) {
                    if (!inventoryContainer.getSlot(axeSlot).getHasStack()) {
                        swap(i, axeSlot - 36);
                        timer.reset();
                        if (delayV.getValue().longValue() > 0L) {
                            return;
                        }
                    } else {
                        if (!isBestAxe(inventoryContainer.getSlot(axeSlot).getStack())) {
                            swap(i, axeSlot - 36);
                            timer.reset();
                            if (delayV.getValue().longValue() > 0L) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
    boolean isBestPickaxe(ItemStack stack) {
        Item item = stack.getItem();
        Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
        if (!(ItemPickaxe.isItemPickaxe(item))) {
            return false;
        } else {
            float value = getToolEffect(stack);

            for (int i = 9; i < 45; ++i) {
                if (inventoryContainer.getSlot(i).getHasStack()) {
                    ItemStack is = inventoryContainer.getSlot(i).getStack();
                    if (getToolEffect(is) > value && ItemPickaxe.isItemPickaxe(is.getItem())) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
    boolean isBestShovel(ItemStack stack) {
        Item item = stack.getItem();
        Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
        if (!(ItemSpade.isItemSpade(item))) {
            return false;
        } else {
            float value = getToolEffect(stack);

            for (int i = 9; i < 45; ++i) {
                if (inventoryContainer.getSlot(i).getHasStack()) {
                    ItemStack is = inventoryContainer.getSlot(i).getStack();
                    if (getToolEffect(is) > value && ItemSpade.isItemSpade(is.getItem())) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
    boolean isBestAxe(ItemStack stack) {
        Item item = stack.getItem();
        Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
        if (!(ItemAxe.isItemAxe(item))) {
            return false;
        } else {
            float value = getToolEffect(stack);

            for (int i = 9; i < 45; ++i) {
                if (inventoryContainer.getSlot(i).getHasStack()) {
                    ItemStack is = inventoryContainer.getSlot(i).getStack();
                    if (getToolEffect(is) > value && ItemAxe.isItemAxe(is.getItem())
                            && !isBestWeapon(stack)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
    float getToolEffect(ItemStack stack) {
        Item item = stack.getItem();
        if (!(ItemTool.isItemTool(item))) {
            return 0.0F;
        } else {
            String name = item.getUnlocalizedName();
            ItemTool tool = new ItemTool(item.getWrapObject());
            float value = 1.0F;
            if (ItemPickaxe.isItemPickaxe(item)) {
                value = tool.getStrVsBlock(stack, Blocks.stone);
                if (name.toLowerCase().contains("gold")) {
                    value -= 5.0F;
                }
            } else if (ItemSpade.isItemSpade(item)) {
                value = tool.getStrVsBlock(stack, Blocks.dirt);
                if (name.toLowerCase().contains("gold")) {
                    value -= 5.0F;
                }
            } else {
                if (!(ItemAxe.isItemAxe(item))) {
                    return 1.0F;
                }

                value = tool.getStrVsBlock(stack, Blocks.log);
                if (name.toLowerCase().contains("gold")) {
                    value -= 5.0F;
                }
            }

            value = (float) ((double) value
                    + (double) EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.getEffectId(), stack) * 0.0075D);
            value = (float) ((double) value
                    + (double) EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.getEffectId(), stack) / 100.0D);
            return value;
        }
    }
    boolean isBadPotion(ItemStack stack) {
        if (!stack.isNull() && ItemPotion.isItemPotion(stack.getItem())) {
            ItemPotion potion = new ItemPotion(stack.getItem().getWrapObject());
            if (potion.getEffects(stack) == null) {
                return true;
            }

            for (PotionEffect effect : potion.getEffects(stack)) {
                int id = effect.getPotionID();
                if (id == Potion.poison.getId() || id == Potion.harm.getId()
                        || id == Potion.moveSlowdown.getId()
                        || id == Potion.weakness.getId()) {
                    return true;
                }
            }
        }
        return false;
    }
    boolean invContainsType(int type) {
        for (int i = 9; i < 45; ++i) {
            Container inventoryContainer = mc.getThePlayer().getInventoryContainer();
            if (inventoryContainer.getSlot(i).getHasStack()) {
                ItemStack is = inventoryContainer.getSlot(i).getStack();
                Item item = is.getItem();
                if (ItemArmor.isItemArmor(item)) {
                    ItemArmor armor = new ItemArmor(item.getWrapObject());
                    if (type == armor.getArmorType()) {
                        return true;
                    }
                }
            }
        }

        return false;
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
    private float getProtection(ItemStack stack) {
        float prot = 0.0f;
        if (ItemArmor.isItemArmor(stack.getItem())) {
            ItemArmor armor = new ItemArmor(stack.getItem().getWrapObject());
            prot = (float) ((double) prot
                    + ((double) armor.getDamageReduceAmount() + (double) ((100 - armor.getDamageReduceAmount())
                    * EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.getEffectId(), stack)) * 0.0075));
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
