package al.nya.reflect.features.modules.Combat;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemBow;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C05PacketPlayerLook;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07PacketPlayerDigging;
import al.nya.reflect.wrapper.wraps.wrapper.network.C08PacketPlayerBlockPlacement;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetHandlerPlayClient;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;

public class FastBow extends Module {
    public DoubleValue packetsValue = new DoubleValue("Packets",20,3,20,"0");
    public FastBow() {
        super("FastBow", "快速射箭", ModuleType.Combat);
        addValues(packetsValue);
    }
    @EventTarget
    public void onUpdate(EventUpdate eventUpdate) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (!thePlayer.isUsingItem())
            return;
        ItemStack currentItem = thePlayer.getInventory().getCurrentItem();

        if (!currentItem.isNull() && ItemBow.isItemBow(currentItem.getItem())) {
            NetHandlerPlayClient netHandler = mc.getNetHandler();
            netHandler.addToSendQueue(new C08PacketPlayerBlockPlacement(BlockPos.ORIGIN, 255, thePlayer.getCurrentEquippedItem(), 0F, 0F, 0F));
            float yaw = BowAimBot.rotation != null ?
                    BowAimBot.rotation.getYaw()
            :
                thePlayer.getRotationYaw();
            float pitch = BowAimBot.rotation != null ?
                    BowAimBot.rotation.getPitch()
            :
                thePlayer.getRotationPitch();
            for (int i = 0; i < packetsValue.getValue(); i ++) {
                netHandler.addToSendQueue(new C05PacketPlayerLook(yaw, pitch, true));
            }
            netHandler.addToSendQueue(new C07PacketPlayerDigging(C07Action.RELEASE_USE_ITEM,BlockPos.ORIGIN, EnumFacing.DOWN));
            thePlayer.setItemInUseCount(currentItem.getMaxItemUseDuration() - 1);
        }
    }
}
