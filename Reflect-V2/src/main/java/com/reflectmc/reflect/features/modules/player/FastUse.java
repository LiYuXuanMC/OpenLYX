package com.reflectmc.reflect.features.modules.player;

import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.player.EventLivingUpdate;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.ModeValue;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.Item;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.ItemBucketMilk;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.ItemFood;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.ItemPotion;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.NetworkManager;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.c03.C03PacketPlayer;

public class FastUse extends Module {
    private ModeValue mode = new ModeValue("Mode", Mode.Vanilla, Mode.values());

    public FastUse() {
        super("FastUse", Category.Player);
        addValues(mode);
    }

    @EventTarget
    public void onUpdate(EventLivingUpdate update) {
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
