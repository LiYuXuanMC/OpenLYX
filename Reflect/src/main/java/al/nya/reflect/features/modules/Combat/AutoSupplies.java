package al.nya.reflect.features.modules.Combat;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.entity.InventoryUtils;
import al.nya.reflect.utils.timer.MSTimer;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiInventory;
import al.nya.reflect.wrapper.wraps.wrapper.item.Container;
import al.nya.reflect.wrapper.wraps.wrapper.item.Item;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.item.Items;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.PlayerControllerMP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07PacketPlayerDigging;
import al.nya.reflect.wrapper.wraps.wrapper.network.C08PacketPlayerBlockPlacement;
import al.nya.reflect.wrapper.wraps.wrapper.network.C09PacketHeldItemChange;
import al.nya.reflect.wrapper.wraps.wrapper.network.C0DPacketCloseWindow;
import al.nya.reflect.wrapper.wraps.wrapper.network.C16.C16PacketClientStatus;
import al.nya.reflect.wrapper.wraps.wrapper.network.C16.C16State;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetHandlerPlayClient;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;

public class AutoSupplies extends Module {
    private final ModeValue suppliesType = new ModeValue("Supplies", SuppliesType.Soup, SuppliesType.values());
    private final DoubleValue healthValue = new DoubleValue("Health", 20f, 0f, 15f, "0");
    private final DoubleValue delayValue = new DoubleValue("Delay", 500, 0, 150, "0");
    private final OptionValue openInventoryValue = new OptionValue("OpenInv", false);
    private final OptionValue simulateInventoryValue = new OptionValue("SimulateInventory", true);
    private final ModeValue bowlValue = new ModeValue("Bowl", BowlMode.Drop, BowlMode.values()){
        @Override
        public boolean show(){
            return suppliesType.getValue() == SuppliesType.Soup;
        }
    };

    private final MSTimer timer = new MSTimer();

    public AutoSupplies() {
        super("AutoSupplies", "自动补给", ModuleType.Combat);
        addValues(suppliesType,healthValue, delayValue, openInventoryValue, simulateInventoryValue, bowlValue);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (!timer.hasTimePassed(delayValue.getValue().longValue())) {
            return;
        }
        NetHandlerPlayClient netHandler = mc.getNetHandler();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        Container inventoryContainer = thePlayer.getInventoryContainer();
        PlayerControllerMP playerController = mc.getPlayerController();
        int soupInHotbar = InventoryUtils.findItem(36, 45, getSupplies());
        if (thePlayer.getHealth() <= healthValue.getValue() && soupInHotbar != -1) {
            netHandler.addToSendQueue(new C09PacketHeldItemChange(soupInHotbar - 36));
            netHandler.addToSendQueue(new C08PacketPlayerBlockPlacement(inventoryContainer.getSlot(soupInHotbar).getStack()));
            if (bowlValue.equals("Drop") && suppliesType.getValue() == SuppliesType.Soup ) {
                netHandler.addToSendQueue(new C07PacketPlayerDigging(C07Action.DROP_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
            }
            netHandler.addToSendQueue(new C09PacketHeldItemChange(thePlayer.getInventory().currentItem()));
            timer.reset();
            return;
        }

        int bowlInHotbar = InventoryUtils.findItem(36, 45, Items.bowl);
        if (bowlValue.getValue() == BowlMode.Move && bowlInHotbar != -1 && suppliesType.getValue() == SuppliesType.Soup) {
            if (openInventoryValue.getValue() && !GuiInventory.isGuiInventory(mc.getCurrentScreen())) {
                return;
            }

            boolean bowlMovable = false;
            for (int i = 9; i < 36; i++) {
                ItemStack itemStack = inventoryContainer.getSlot(i).getStack();
                if (itemStack == null) {
                    bowlMovable = true;
                    break;
                } else if (itemStack.getItem() == Items.bowl && itemStack.getStackSize() < 64) {
                    bowlMovable = true;
                    break;
                }
            }

            if (bowlMovable) {
                boolean openInventory = !GuiInventory.isGuiInventory(mc.getCurrentScreen()) && simulateInventoryValue.getValue();

                if (openInventory) {
                    netHandler.addToSendQueue(new C16PacketClientStatus(C16State.OPEN_INVENTORY_ACHIEVEMENT));
                }
                playerController.windowClick(0, bowlInHotbar, 0, 1, thePlayer);
            }
        }

        int soupInInventory = InventoryUtils.findItem(9, 36, getSupplies());
        if (soupInInventory != -1 && InventoryUtils.hasSpaceHotbar()) {
            if (openInventoryValue.getValue() && !GuiInventory.isGuiInventory(mc.getCurrentScreen())) {
                return;
            }

            boolean openInventory = !GuiInventory.isGuiInventory(mc.getCurrentScreen()) && simulateInventoryValue.getValue();
            if (openInventory) {
                netHandler.addToSendQueue(new C16PacketClientStatus(C16State.OPEN_INVENTORY_ACHIEVEMENT));
            }

            playerController.windowClick(0, soupInInventory, 0, 1, thePlayer);

            if (openInventory) {
                netHandler.addToSendQueue(new C0DPacketCloseWindow());
            }

            timer.reset();
        }
    }
    public Item getSupplies(){
        return suppliesType.getValue() == SuppliesType.Head ? Items.skull : Items.mushroom_stew;
    }
    public enum SuppliesType {
        Head,
        Soup
    }

    public enum BowlMode {
        Drop,
        Move,
        Stay
    }
}
