package al.logger.client.features.modules.impls.Player;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Hazard;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.network.PacketUtil;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.item.Item;
import al.logger.client.wrapper.LoggerMC.item.ItemBucketMilk;
import al.logger.client.wrapper.LoggerMC.item.ItemFood;
import al.logger.client.wrapper.LoggerMC.item.ItemPotion;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.network.NetworkManager;

public class FastUse extends Module {
    private ModeValue mode = new ModeValue("Mode", Mode.Vanilla, Mode.values());
    public FastUse() {
        super("FastUse","Quick use items", Category.Player);
        setHazard(Hazard.HACK);
        addValues(mode);
    }
    @Listener
    public void onUpdate(EventLivingUpdate update) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (!thePlayer.isUsingItem()) {
            if (mode.getValue() == Mode.Matrix) {
                mc.getTimer().setTimerSpeed(1.0f);
            }
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

            if (mode.getValue() == Mode.Matrix){
                mc.getTimer().setTimerSpeed(0.5f);
                PacketUtil.sendPacket(new C03PacketPlayer(thePlayer.isOnGround()));
            }
        }
    }
    enum Mode {
        Vanilla,
        Matrix
    }
}
