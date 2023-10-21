package al.nya.reflect.features.modules.Combat;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.entity.PlayerUtil;
import al.nya.reflect.utils.math.MathUtil;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02PacketUseEntity;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetHandlerPlayClient;
import lombok.val;

public class Criticals extends Module {
    private final ModeValue mode = new ModeValue("Mode", Mode.Packet, Mode.values());
    public Criticals() {
        super("Criticals", "刀刀暴击", ModuleType.Combat);
        addValue(mode);
    }
    @EventTarget
    public void onPacket(EventPacket eventPacket){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isOnGround() && !PlayerUtil.isInWater() && (mode.getValue() == Mode.Packet || mode.getValue() == Mode.OldHypixel)) {
            if (C02PacketUseEntity.isC02PacketUseEntity(eventPacket.getPacket())) {
                if (new C02PacketUseEntity(eventPacket.getPacket().getWrapObject()).getAction() == C02Action.ATTACK) {
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
                        double random = MathUtil.getRandom(1E-8, 1E-6) * (MathUtil.getRandom().nextBoolean() ? 1 : -1);
                        double[] offsets = new double[]{
                                .0174926 + random,
                                .0207984 + random,
                                .0132894 + random,
                                .0098461 + random
                        };
                        NetHandlerPlayClient netHandler = mc.getNetHandler();
                        for (double offset : offsets)
                            netHandler.addToSendQueue(new C04PacketPlayerPosition(x, y + offset, z, false));
                    }
                }
            }
        }
        if (mode.getValue() == Mode.AAC5) {
            if (C03PacketPlayer.isPacketPlayer(eventPacket.getPacket())) {
                C03PacketPlayer packet = new C03PacketPlayer(eventPacket.getPacket().getWrapObject());
                packet.setOnGround(false);
            }
        }
    }

    @Override
    public String getSuffix(){
        return mode.getValue().name();
    }

    public enum Mode {
        Packet,
        AAC5,
        Hypixel,
        OldHypixel
    }
}
