package al.logger.client.features.modules.impls.Combat;


import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.wrapper.LoggerMC.enchantment.Enchantment;
import al.logger.client.wrapper.LoggerMC.enchantment.EnchantmentHelper;
import al.logger.client.wrapper.LoggerMC.gui.GuiInventory;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;
import al.logger.client.wrapper.LoggerMC.item.ItemSword;

public class AutoSword extends Module {
    TimerUtils timer = new TimerUtils();
    TimerUtils time = new TimerUtils();

    public AutoSword() {
        super("AutoSword", Category.Combat);
    }

    @Listener
    public void onClientTick(EventLivingUpdate event) {
        if (!timer.isDelay(300)) return;

        if (!time.isDelay(1000L) || (mc.getCurrentScreen() != null && !(GuiInventory.isGuiInventory(mc.getCurrentScreen()))))
            return;

        int best = -1;
        float swordDamage = 0;
        for (int i = 9; i < 45; ++i) {
            if (mc.getThePlayer().getInventoryContainer().getSlot(i).getHasStack()) {
                ItemStack is = mc.getThePlayer().getInventoryContainer().getSlot(i).getStack();
                if (ItemSword.isItemSword(is.getItem())) {
                    float swordD = getSharpnessLevel(is);
                    if (swordD > swordDamage) {
                        swordDamage = swordD;
                        best = i;
                    }
                }
            }
        }
        final ItemStack current = mc.getThePlayer().getInventoryContainer().getSlot(1 + 35).getStack();
        if (best != -1 && (current == null || !(current.getItem() instanceof ItemSword) || swordDamage > getSharpnessLevel(current))) {

            timer.reset();
            mc.getPlayerController().windowClick(mc.getThePlayer().getInventoryContainer().getWindowId(), best, 0, 2, mc.getThePlayer());
            time.reset();

        }
    }

    private float getSharpnessLevel(ItemStack stack) {
        float damage = ((ItemSword) stack.getItem()).getDamageVsEntity();
        damage += EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.getEffectId(), stack) * 1.25f;
        damage += EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.getEffectId(), stack) * 0.01f;
        return damage;
    }
}
