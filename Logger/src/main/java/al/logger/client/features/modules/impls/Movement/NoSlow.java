package al.logger.client.features.modules.impls.Movement;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventPostUpdate;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Hazard;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.impls.Combat.KillAura;
import al.logger.client.features.modules.impls.Combat.LegitAura;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.network.PacketUtil;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.utils.player.PlayerUtils;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.item.*;
import al.logger.client.wrapper.LoggerMC.network.NetHandlerPlayClient;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02PacketUseEntity;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.network.client.C07.C07Action;
import al.logger.client.wrapper.LoggerMC.network.client.C07.C07PacketPlayerDigging;
import al.logger.client.wrapper.LoggerMC.network.client.C08PacketPlayerBlockPlacement;
import al.logger.client.wrapper.LoggerMC.network.client.C09PacketHeldItemChange;
import al.logger.client.wrapper.LoggerMC.network.client.C0APacketAnimation;
import al.logger.client.wrapper.LoggerMC.network.client.C0B.C0BPacketEntityAction;
import al.logger.client.wrapper.LoggerMC.network.client.C0CPacketInput;
import al.logger.client.wrapper.LoggerMC.network.server.S09PacketHeldItemChange;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.EnumFacing;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectType;
import al.logger.client.wrapper.LoggerMC.utils.Vec3;

import javax.vecmath.Vector2f;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NoSlow extends Module {
    private ModeValue mode = new ModeValue("Mode",Mode.Vanilla,Mode.values());
    private OptionValue C07 = new OptionValue("C07" , false);
    private boolean test;
    boolean isBlocking;
    private boolean enabled = false;
    boolean nextTemp;
    boolean should = false;
    private static List<Packet> packetBuf = new CopyOnWriteArrayList<>();
    TimerUtils time = new TimerUtils();
    public NoSlow() {
        super("NoSlow",Category.Movement);
        setDescription("Does not slow down when blocking or eating food");
        setHazard(Hazard.HACK);
        this.addValues(mode,C07);
    }
    //
    @Listener
    public void preUpdate(EventPreUpdate e) {
        if ((!mc.getThePlayer().isUsingItem() || !mc.getThePlayer().isBlocking()) && !MovementUtils.isMoving()){
            return;
        }

        if (mc.getThePlayer().isBlocking() || mc.getThePlayer().isUsingItem()){
            isBlocking = true;
        }else {
            isBlocking = false;
        }

        if (mode.getValue() == Mode.Matrix){
            if (ItemBow.isItemBow(mc.getThePlayer().getHeldItem().getItem()) || ItemFood.isItemFood(mc.getThePlayer().getHeldItem().getItem())){
                return;
            }

            if (time.hasTimeElapsed(230) && nextTemp){
                nextTemp = false;
                PacketUtil.sendPacketNoEvent(new C07PacketPlayerDigging(C07Action.RELEASE_USE_ITEM, new BlockPos(-1, -1, -1), EnumFacing.DOWN));
            }


            if (!nextTemp){
                if (!isBlocking) {
                    return;
                }
                PacketUtil.sendPacketNoEvent(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, mc.getThePlayer().getInventory().getCurrentItem(), 0f, 0f, 0f));
                nextTemp = true;
                time.reset();
            }
        }

        if (mode.getValue() == Mode.Hypixel){
            if (mc.getThePlayer().isUsingItem() && mc.getThePlayer().getHeldItem() != null && ItemSword.isItemSword(mc.getThePlayer().getHeldItem().getItem()) && MovementUtils.isMoving()) {
//            PacketUtil.sendNoEvent(new C08PacketPlayerBlockPlacement(mc.thePlayer.getHeldItem()));
                //   sendPacketNoEvent(new C09PacketHeldItemChange(mc.player.inventory.currentItem % 8 + 1));
                // sendPacketNoEvent(new C09PacketHeldItemChange(mc.player.inventory.currentItem));
            }

            if (mc.getGameSettings().getKeyBindUseItem().isKeyDown() && (mc.getThePlayer().getHeldItem() != null && (ItemFood.isItemFood(mc.getThePlayer().getHeldItem().getItem()) || ItemBucketMilk.isItemBucketMilk(mc.getThePlayer().getHeldItem().getItem()) || (ItemPotion.isItemPotion(mc.getThePlayer().getHeldItem().getItem()) && !ItemPotion.isSplash(mc.getThePlayer().getHeldItem().getMetadata()))))) {
                e.setPitch(90f);
            }
        }

    }



    @Listener
    public void onPacket(EventPacket event){
        if (mode.getValue() == Mode.Matrix && nextTemp) {
            if (ItemBow.isItemBow(mc.getThePlayer().getHeldItem().getItem()) || ItemFood.isItemFood(mc.getThePlayer().getHeldItem().getItem())){
                return;
            }

            if (C07PacketPlayerDigging.isCPacketPlayerDigging(event.getPacket()) || C08PacketPlayerBlockPlacement.isPacketPlayerBlockPlacement(event.getPacket()) && isBlocking)
                event.cancel();
            }


        if (mode.getValue() == Mode.Hypixel){
            Packet packet = new Packet(event.getPacket().getWrappedObject());

            if (mc.getThePlayer().getHeldItem() != null && (ItemFood.isItemFood(mc.getThePlayer().getHeldItem().getItem()) || ItemBucketMilk.isItemBucketMilk(mc.getThePlayer().getHeldItem().getItem()) || (ItemPotion.isItemPotion(mc.getThePlayer().getHeldItem().getItem()) && !ItemPotion.isSplash(mc.getThePlayer().getHeldItem().getMetadata())))) {
                if (C08PacketPlayerBlockPlacement.isPacketPlayerBlockPlacement(packet)) {
                    if (mc.getGameSettings().getKeyBindUseItem().isKeyDown()) {
                        //    event.setCancelled(true);
                        event.cancel();
                        C08PacketPlayerBlockPlacement c08 = new C08PacketPlayerBlockPlacement(packet.getWrappedObject());
                        if (mc.getObjectMouseOver() != null && mc.getObjectMouseOver().getTypeOfHit() == MovingObjectType.BLOCK && !(c08.getPosition().equals(new BlockPos(-1, -1, -1))))
                            return;
                        MovingObjectPosition position = mc.getThePlayer().rayTraceCustom(mc.getPlayerController().getBlockReachDistance(), mc.getThePlayer().getRotationYaw(), 90f);
                        if (position == null) return; else event.cancel();
                        sendUseItem(position);
                    }
                }
            }
        }

        if (mode.getValue() == Mode.Test){
            Packet packet = new Packet(event.getPacket().getWrappedObject());

            if (S09PacketHeldItemChange.isS09PacketHeldItemChange(packet)){
                S09PacketHeldItemChange s09 = new S09PacketHeldItemChange(packet.getWrappedObject());
                ChatUtils.message("Detected Server Item Change");
                event.cancel();
                PacketUtil.sendPacketNoEvent(new C09PacketHeldItemChange(s09.getHeldItemHotbarIndex()));
                PacketUtil.sendPacketNoEvent(new C09PacketHeldItemChange(mc.getThePlayer().getHeldItem()));
            }
        }
    }
    /*
        @EventTarget
    public void onPacket(PacketEvent event) {
        final Packet<?> packet = event.getPacket();
        if (mode.get().equals("Hypixel")) {
            if (mc.player.getHeldItem() != null && (mc.player.getHeldItem().getItem() instanceof ItemFood || mc.player.getHeldItem().getItem() instanceof ItemBucketMilk || (mc.player.getHeldItem().getItem() instanceof ItemPotion && !ItemPotion.isSplash(mc.player.getHeldItem().getMetadata())))) {
                if (packet instanceof C08PacketPlayerBlockPlacement) {
                if (mc.gameSettings.keyBindUseItem.isKeyDown()) {
                    if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && !(((C08PacketPlayerBlockPlacement) packet).getPosition().equals(new BlockPos(-1, -1, -1))))
                        return;
                    event.setCancelled(true);
                    MovingObjectPosition position = mc.player.rayTraceCustom(mc.playerController.getBlockReachDistance(), mc.player.rotationYaw, 90f);
                    if (position == null) return;
                    sendUseItem(position);
                }
            }
            }
        }
    }
    private void sendUseItem(MovingObjectPosition mouse) {
        final float facingX = (float) (mouse.hitVec.xCoord - (double) mouse.getBlockPos().getX());
        final float facingY = (float) (mouse.hitVec.yCoord - (double) mouse.getBlockPos().getY());
        final float facingZ = (float) (mouse.hitVec.zCoord - (double) mouse.getBlockPos().getZ());

        sendPacketNoEvent(new C08PacketPlayerBlockPlacement(mouse.getBlockPos(), mouse.facing.getIndex(), mc.player.getHeldItem(), facingX, facingY, facingZ));
    }
     */

    private void sendUseItem(MovingObjectPosition mouse) {
        final float facingX = (float) (mouse.getHitVec().getXCoord() - (double) mouse.getBlockPos().getX());
        final float facingY = (float) (mouse.getHitVec().getYCoord() - (double) mouse.getBlockPos().getY());
        final float facingZ = (float) (mouse.getHitVec().getYCoord() - (double) mouse.getBlockPos().getZ());

        PacketUtil.sendPacketNoEvent(new C08PacketPlayerBlockPlacement(mouse.getBlockPos(), mouse.getSideHit().getIndex(), mc.getThePlayer().getHeldItem(), facingX, facingY, facingZ));
    }


    @Override
    public void onDisable() {
        nextTemp = false;
        super.onDisable();
    }

    public enum Mode{
        Vanilla,
        Hypixel,
        Matrix,
        Test
    }
}
