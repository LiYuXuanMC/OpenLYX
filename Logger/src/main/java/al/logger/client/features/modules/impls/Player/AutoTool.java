package al.logger.client.features.modules.impls.Player;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventTick;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.enchantment.Enchantment;
import al.logger.client.wrapper.LoggerMC.enchantment.EnchantmentHelper;
import al.logger.client.wrapper.LoggerMC.item.*;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectType;

public class AutoTool extends Module {
    public AutoTool() {
        super("AutoTool", "Auto select the best tool", Category.Player);
    }

    @Listener
    public void onTick(EventTick event){
        MovingObjectPosition objectMouseOver = mc.getObjectMouseOver();
        if (objectMouseOver.isNull() || mc.getThePlayer().isUsingItem())
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

    protected void updateTool(Block block) {

        float strength = 1F;
        int slot = -1;

        for (int i = 0; i < 9; ++i) {
            final ItemStack itemStack = mc.getThePlayer().getInventory().getMainInventory()[i];
            if (itemStack.isNull() || itemStack.getStrVsBlock(block) <= strength) continue;
            strength = itemStack.getStrVsBlock(block);
            slot = i;
        }

        if (slot != -1 && mc.getThePlayer().getInventory().currentItem() != slot) {
            mc.getThePlayer().getInventory().currentItem(slot);
        }
    }

    protected void updateSword() {

        float strength = 1F;
        int slot = -1;

        for (int i = 0; i < 9; ++i) {
            final ItemStack itemStack = mc.getThePlayer().getInventory().getMainInventory()[i];
            if (itemStack.isNull() || getDamage(itemStack) <= strength) continue;
            strength = getDamage(itemStack);
            slot = i;
        }

        if (slot != -1 && mc.getThePlayer().getInventory().currentItem() != slot) {
            mc.getThePlayer().getInventory().currentItem(slot);
        }
    }
    private float getDamage(ItemStack stack) {
        float damage = 0.0F;
        Item item = stack.getItem();
        if (ItemSword.isItemSword(item)) {
            damage += getAttackDamage(stack);
        } else {
            ++damage;
        }

        return damage;
    }
    private float getAttackDamage(ItemStack itemStack) {
        float damage = (new ItemSword(itemStack.getItem().getWrappedObject())).getDamageVsEntity();
        damage += (float) EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.getEffectId(), itemStack) * 1.25F;
        damage += (float) EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.getEffectId(), itemStack) * 0.01F;
        return damage;
    }
    private float getSpeed(ItemStack stack) {
        return ToolMaterial.getEfficiency((new ItemTool(stack.getItem())).getToolMaterial());
    }
}
