package al.nya.reflect.features.modules.Combat;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.entity.InventoryUtils;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemPotion;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.network.C08PacketPlayerBlockPlacement;
import al.nya.reflect.wrapper.wraps.wrapper.network.C09PacketHeldItemChange;
import al.nya.reflect.wrapper.wraps.wrapper.network.C0DPacketCloseWindow;
import al.nya.reflect.wrapper.wraps.wrapper.network.C16.C16PacketClientStatus;
import al.nya.reflect.wrapper.wraps.wrapper.network.C16.C16State;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetHandlerPlayClient;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;
import al.nya.reflect.wrapper.wraps.wrapper.potion.PotionEffect;

public class AutoPot extends Module {
    private DoubleValue healthValue = new DoubleValue("Health", 1F, 0F, .6F, "0.00");
    private DoubleValue delayValue = new DoubleValue("Delay", 1000, 500, 500, "0");
    private DoubleValue throwTickValue = new DoubleValue("ThrowTick", 10, 1, 3, "0");
    private DoubleValue selectValue = new DoubleValue("SelectSlot", 9, -1, -1, "0");

    private OptionValue openInventoryValue = new OptionValue("OpenInv", false);
    private OptionValue simulateInventoryValue = new OptionValue("SimulateInventory", true);
    private OptionValue regenValue = new OptionValue("Regen", true);
    private OptionValue utilityValue = new OptionValue("Utility", true);
    //private OptionValue notCombatValue = new OptionValue("NotCombat", true);

    private boolean throwing = false;
    private int throwTime = 0;
    private int pot = -1;

    public AutoPot() {
        super("AutoPot", ModuleType.Combat);
        addValues(healthValue, delayValue, throwTickValue, selectValue, openInventoryValue, simulateInventoryValue, regenValue, utilityValue);
    }

    /*@EventTarget
    public void onUpdate(EventUpdate update) {
        //We don't have combatManager
        //if (notCombatValue.get() && LiquidBounce.combatManager.inCombat) return
        EntityPlayerSP thePlayer = mc.getThePlayer();
        NetHandlerPlayClient netHandler = mc.getNetHandler();
        if (!thePlayer.isOnGround()) return;

        if (throwing) {
            throwTime++;
            RotationUtils.setTargetRotation(Rotation(mc.thePlayer.rotationYaw, 90F))
            if (throwTime == throwTickValue.getValue()) {
                netHandler.addToSendQueue(new C09PacketHeldItemChange(pot - 36));
                netHandler.addToSendQueue(new C08PacketPlayerBlockPlacement(thePlayer.getHeldItem()));
                netHandler.addToSendQueue(new C09PacketHeldItemChange(thePlayer.getInventory().currentItem()));
                pot = -1;
            }
            if (throwTime >= (throwTickValue.getValue() * 2)) {
                throwTime = 0;
                throwing = false;
            }
            return;
        }
        if (!InventoryUtils.INV_TIMER.hasTimePassed(delayValue.get().toLong())) return

                val enableSelect = selectValue.get() != -1
        val potion = if (enableSelect) {
            if (findSinglePotion(36 + selectValue.get())) {
                36 + selectValue.get()
            } else {
                -1
            }
        } else {
            findPotion(36, 45)
        }

        if (potion != -1) {
            RotationUtils.setTargetRotation(Rotation(mc.thePlayer.rotationYaw, 90F))
            pot = potion
            throwing = true
            InventoryUtils.INV_TIMER.reset()
            return
        }

        if (openInventoryValue.get() && !enableSelect) {
            val invPotion = findPotion(9, 36)
            if (invPotion != -1) {
                val openInventory = mc.currentScreen !is GuiInventory && simulateInventoryValue.get()
                if (InventoryUtils.hasSpaceHotbar()) {
                    if (openInventory) {
                        mc.netHandler.addToSendQueue(C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT))
                    }

                    mc.playerController.windowClick(0, invPotion, 0, 1, mc.thePlayer)

                    if (openInventory) {
                        mc.netHandler.addToSendQueue(C0DPacketCloseWindow())
                    }

                    return
                } else {
                    for (i in 36 until 45) {
                        val stack = mc.thePlayer.inventoryContainer.getSlot(i).stack
                        if (stack == null || stack.item !is ItemPotion || !ItemPotion.isSplash(stack.itemDamage)) {
                            continue
                        }

                        if (openInventory) {
                            mc.netHandler.addToSendQueue(C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT))
                        }

                        mc.playerController.windowClick(0, invPotion, 0, 0, mc.thePlayer)
                        mc.playerController.windowClick(0, i, 0, 0, mc.thePlayer)

                        if (openInventory) {
                            mc.netHandler.addToSendQueue(C0DPacketCloseWindow())
                        }

                        break
                    }
                }
            }
        }
    }

     */

    private int findPotion(int startSlot, int endSlot) {
        for (int i = startSlot; i < endSlot; i++) {
            if (findSinglePotion(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean findSinglePotion(int slot) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        ItemStack stack = thePlayer.getInventoryContainer().getSlot(slot).getStack();

        if (stack.isNull() || !ItemPotion.isItemPotion(stack.getItem()) || !ItemPotion.isSplash(stack.getItemDamage())) {
            return false;
        }

        ItemPotion itemPotion = new ItemPotion(stack.getItem().getWrapObject());

        if ((thePlayer.getHealth() / thePlayer.getMaxHealth()) < healthValue.getValue() && regenValue.getValue()) {
            for (PotionEffect effect : itemPotion.getEffects(stack)) {
                if (effect.getPotionID() == Potion.heal.getId()) {
                    return true;
                }
            }

            if (!thePlayer.isPotionActive(Potion.regeneration)) {
                for (PotionEffect effect : itemPotion.getEffects(stack)) {
                    if (effect.getPotionID() == Potion.regeneration.getId()) {
                        return true;
                    }
                }
            }
        } else if (utilityValue.getValue()) {
            for (PotionEffect effect : itemPotion.getEffects(stack)) {
                if (InventoryUtils.isPositivePotionEffect(effect.getPotionID()) && !thePlayer.isPotionActive(effect.getPotionID())) {
                    return true;
                }
            }
        }
        return false;
    }
}
