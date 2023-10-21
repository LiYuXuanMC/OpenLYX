package al.logger.client.features.modules.impls.Combat;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.misc.MathHelper;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02Action;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02PacketUseEntity;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C04PacketPlayerPosition;
import al.logger.client.wrapper.LoggerMC.network.NetHandlerPlayClient;
import lombok.val;

public class Criticals extends Module {
    private final ModeValue mode = new ModeValue("Mode", Mode.Packet, Mode.values());
    private final OptionValue debug = new OptionValue("Debug",false);
    public Criticals() {
        super("Criticals", Category.Combat);
        addValues(mode,debug);
    }
    @Listener
    public void onPacket(EventPacket eventPacket){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isOnGround() && !thePlayer.isInWater() && (mode.getValue() == Mode.Packet || mode.getValue() == Mode.OldHypixel)) {
            if (C02PacketUseEntity.isC02PacketUseEntity(eventPacket.getPacket())) {
                if (new C02PacketUseEntity(eventPacket.getPacket().getWrappedObject()).getAction() == C02Action.ATTACK) {
                    val x = thePlayer.getPosX();
                    val y = thePlayer.getPosY();
                    val z = thePlayer.getPosZ();
                    if (mode.getValue() == Mode.Packet) {
                        NetHandlerPlayClient netHandler = mc.getNetHandler();
                        netHandler.addToSendQueue(new C04PacketPlayerPosition(x, y + 0.0625, z, true));
                        netHandler.addToSendQueue(new C04PacketPlayerPosition(x, y, z, false));
                        netHandler.addToSendQueue(new C04PacketPlayerPosition(x, y + 1.1E-5, z, false));
                        netHandler.addToSendQueue(new C04PacketPlayerPosition(x, y, z, false));
                    }
                    if (mode.getValue() == Mode.OldHypixel) {
                        NetHandlerPlayClient netHandler = mc.getNetHandler();
                        netHandler.addToSendQueue(new C04PacketPlayerPosition(x, y + 0.0123237654168D, z, false));
                        netHandler.addToSendQueue(new C04PacketPlayerPosition(x, y + 0.0128372365D, z, false));
                        netHandler.addToSendQueue(new C04PacketPlayerPosition(x, y + 0.0923129837D, z, false));
                        netHandler.addToSendQueue(new C04PacketPlayerPosition(x, y, z, false));
                    }
                    if (mode.getValue() == Mode.Hypixel) {
                        double random = MathHelper.getRandom(1E-8, 1E-6) * (MathHelper.getRandom().nextBoolean() ? 1 : -1);
                        double[] offsets = new double[]{
                                .0174926 + random,
                                .0207984 + random,
                                .0132894 + random,
                                .0098461 + random
                        };
                        NetHandlerPlayClient netHandler = mc.getNetHandler();
                        for (double offset : offsets){
                            netHandler.addToSendQueue(new C04PacketPlayerPosition(x, y + offset, z, false));
                        }

                    }
                    if (debug.getValue()){
                        ChatUtils.message("Critical!");
                    }
                }
            }
        }
        if (mode.getValue() == Mode.AAC5) {
            if (C03PacketPlayer.isPacketPlayer(eventPacket.getPacket())) {
                C03PacketPlayer packet = new C03PacketPlayer(eventPacket.getPacket().getWrappedObject());
                packet.setOnGround(false);
                if (debug.getValue()){
                    ChatUtils.message("Critical!");
                }
            }
        }
    }


    public enum Mode {
        Packet,
        AAC5,
        Hypixel,
        OldHypixel
    }
}
