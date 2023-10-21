package al.logger.client.features.modules.impls.Movement;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.impls.World.Disabler;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.network.PacketUtil;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.network.NetworkManager;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02PacketUseEntity;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C04PacketPlayerPosition;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C05PacketPlayerLook;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C06PacketPlayerPositionLook;
import al.logger.client.wrapper.LoggerMC.network.client.C08PacketPlayerBlockPlacement;
import al.logger.client.wrapper.LoggerMC.network.client.C0APacketAnimation;
import al.logger.client.wrapper.LoggerMC.network.client.C0B.C0BPacketEntityAction;
import al.logger.client.wrapper.LoggerMC.network.client.C0FPacketConfirmTransaction;
import est.builder.annotations.Clear;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
@Clear(when = "Release")
public class Blink extends Module {
    public Queue<Packet> packets = new ConcurrentLinkedDeque<>();
    private boolean disableLogger = false;
    private LinkedList<double[]> positions = new LinkedList<>();

    private TimerUtils pulseTimer = new TimerUtils();

    OptionValue bypass = new OptionValue("Bypass" , false);

    public Blink() {
        super("Blink", Category.Movement);
        addValues(bypass);
    }


    @Override
    public void onEnable() {
        EntityPlayer thePlayer = mc.getThePlayer();
        if (mc.getThePlayer().isNull()){
            return;
        }


        synchronized (positions) {
            positions.add(new double[]{thePlayer.getPosX(), thePlayer.getEntityBoundingBox().getMinY() + thePlayer.getEyeHeight() / 2, thePlayer.getPosZ()});
            positions.add(new double[]{thePlayer.getPosX(), thePlayer.getEntityBoundingBox().getMinY(), mc.getThePlayer().getPosZ()});
        }
    }

    @Override
    public void onDisable() {
        if (mc.getThePlayer().isNull()){
            return;

        }

        blink();
    }
    @Listener
    public void onTick(EventTick e){
        EntityPlayer thePlayer = mc.getThePlayer();
        if (thePlayer.isNull() || mc.getTheWorld().isNull()){
            return;
        }

        synchronized (positions) {
            positions.add(new double[]{
                    thePlayer.getPosX(),
                    thePlayer.getEntityBoundingBox().getMinY(),
                    thePlayer.getPosZ()
            });
        }
    }

    @Listener
    public void onPacket(EventPacket e) {
        Packet packet = new Packet(e.getPacket().getWrappedObject());
        EntityPlayer thePlayer = mc.getThePlayer();
        if (thePlayer.isNull() || disableLogger){
            return;
        }


        if (C03PacketPlayer.isPacketPlayer(packet)){
            // Cancel all movement stuff
            e.cancel();
        }

        if (C04PacketPlayerPosition.isPacketPlayerPosition(packet) || C05PacketPlayerLook.isC05PacketPlayerLook(packet) || C06PacketPlayerPositionLook.isPacketPlayerPositionLook(packet)
                || C08PacketPlayerBlockPlacement.isPacketPlayerBlockPlacement(packet)
                || C0APacketAnimation.isC0APacket(packet)
                || C0BPacketEntityAction.isC0BPacketEntityActionClass(packet) || C02PacketUseEntity.isC02PacketUseEntity(packet)) {
            e.cancel();
            packets.add(packet);

        }

        if (bypass.getValue() && C0FPacketConfirmTransaction.isPacketConfirmTransaction(packet)){
            e.cancel();
            packets.add(packet);
        }

        super.onPacket(e);
    }

    private void blink() {
        try {
            disableLogger = true;
            while (!packets.isEmpty()) {
                PacketUtil.sendPacketNoEvent(packets.poll());
            }
            disableLogger = false;
        } catch (Exception e) {
            e.printStackTrace();
            disableLogger = false;
        }
        synchronized (positions) {
            positions.clear();
        }
    }
}
