package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.item.Item;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemBucketMilk;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemFood;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemPotion;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetworkManager;

public class FastUse extends Module {
    private ModeValue mode = new ModeValue("Mode", Mode.Vanilla, Mode.values());

    public FastUse() {
        super("FastUse", ModuleType.Player);
        addValues(mode);
    }

    @EventTarget
    public void onUpdate(EventUpdate update) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (!thePlayer.isUsingItem()) {
            return;
        }
        Item usingItem = thePlayer.getItemInUse().getItem();
        if (ItemFood.isItemFood(usingItem) || ItemPotion.isItemPotion(usingItem) || ItemBucketMilk.isItemBucketMilk(usingItem)) {
            if (mode.getValue() == Mode.Vanilla) {
                int packets = 36 - thePlayer.getItemInUseDuration();
                NetworkManager networkManager = mc.getNetHandler().getNetworkManager();
                for (int i = 0; i < packets; i++) {
                    networkManager.sendPacket(new C03PacketPlayer(thePlayer.isOnGround()));
                }
                mc.getPlayerController().onStoppedUsingItem(thePlayer);
            }
        }
    }

    enum Mode {
        Vanilla
    }
}
