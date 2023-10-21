package al.nya.reflect.features.modules.minigames;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventNameTag;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.wrapper.utils.WrapperAdaptArrayList;
import al.nya.reflect.wrapper.wraps.wrapper.block.Blocks;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.InventoryPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.item.Item;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemBlock;
import al.nya.reflect.wrapper.wraps.wrapper.item.Items;

import java.util.List;

public class MurderDetector extends Module {
    public static int[] itemIds = {288, 396, 412, 398, 75, 50};
    public static Item[] itemTypes = new Item[]{
            Items.fishing_rod,
            Items.diamond_hoe,
            Items.golden_hoe,
            Items.iron_hoe,
            Items.stone_hoe,
            Items.wooden_hoe,
            Items.stone_sword,
            Items.diamond_sword,
            Items.golden_sword,
            ItemBlock.getItemFromBlock(Blocks.sponge),
            Items.iron_sword,
            Items.wooden_sword,
            Items.diamond_axe,
            Items.golden_axe,
            Items.iron_axe,
            Items.stone_axe,
            Items.diamond_pickaxe,
            Items.wooden_axe,
            Items.golden_pickaxe,
            Items.iron_pickaxe,
            Items.stone_pickaxe,
            Items.wooden_pickaxe,
            Items.stone_shovel,
            Items.diamond_shovel,
            Items.golden_shovel,
            Items.iron_shovel,
            Items.wooden_shovel
    };
    public WrapperAdaptArrayList<EntityPlayer> killerData = new WrapperAdaptArrayList<>();

    public MurderDetector() {
        super("MurderDetector", ModuleType.MiniGame);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        for (Entity entity : mc.getTheWorld().getLoadedEntityList()) {
            if (EntityPlayer.isEntityPlayer(entity)) {
                EntityPlayer player = new EntityPlayer(entity.getWrapObject());
                InventoryPlayer inventory = player.getInventory();
                if (!inventory.getCurrentItem().isNull()) {
                    if (!killerData.contains(player.getWrapObject())) {
                        if (isWeapon(inventory.getCurrentItem().getItem())) {
                            NotificationPublisher.queue("Murder Detected", String.format("%s is Murder", player.getName()), NotificationType.ERROR);
                            killerData.add(player);
                        }
                    }
                }
            }
        }
    }

    @EventTarget
    public void onWorldLoad(EventWorldLoad worldLoad) {
        killerData.clear();
    }

    @EventTarget
    public void onNameTag(EventNameTag nameTag) {
        int id = nameTag.getEntity().getEntityId();
        for (EntityPlayer killerDatum : killerData) {
            if (killerDatum.getEntityId() == id) {
                nameTag.setName(nameTag.getName() + "§c[Murder]");
                break;
            }
        }
    }

    @Override
    public void onEnable() {
        killerData.clear();
    }

    public boolean isWeapon(Item item) {
        int itemId = Item.getIdFromItem(item);
        for (int id : itemIds) {
            //ClientUtils.INSTANCE.displayChatMessage(itemId+":"+item);
            if (id == itemId) {
                return true;
            }
        }
        for (Item id : itemTypes) {
            if (item.equals(id)) {
                return true;
            }
        }
        return false;
    }
}
