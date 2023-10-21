package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.EventType;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.World.Timer;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.entity.PlayerUtil;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C00PacketKeepAlive;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02PacketUseEntity;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;

public class NoFall extends Module {
    public ModeValue mode = new ModeValue("Mode", Mode.Vanilla, Mode.values());

    public NoFall() {
        super("NoFall", ModuleType.Player);
        addValues(mode);
    }

    @EventTarget
    public void onUpdate(EventPreUpdate update) {
        if (mode.getValue() == Mode.Vanilla) {
            update.setOnGround(true);
        }
        if (mode.getValue() == Mode.Simple) {
            if (mc.getThePlayer().getFallDistance() > 2f) {
                mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C03PacketPlayer(true));
            }
        }
        if (mode.getValue() == Mode.Hypixel /* && !AntiVoid.isPullbacking()*/) {
            EntityPlayerSP thePlayer = mc.getThePlayer();
            if (thePlayer.getPosY() > 0 && thePlayer.getLastTickPosY() - thePlayer.getPosY() > 0 && thePlayer.getMotionY() != 0 && thePlayer.getFallDistance() >= 2.5) {
                if (!PlayerUtil.isBlockUnder() || thePlayer.getFallDistance() > 255 || !PlayerUtil.isBlockUnder() && thePlayer.getFallDistance() > 50) {
                    return;
                }

                if (thePlayer.getFallDistance() > 10 || thePlayer.getTicksExisted() % 2 == 0) {
                    mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C03PacketPlayer(true));
                    ModuleManager.disable(Timer.class);
                    ClientUtil.resetTimer();
                }
            }
        }
    }
    @EventTarget
    public void onPacketSend(EventPacket send) {
        if (send.getEventType() == EventType.SendPacket) {
            if (mode.getValue() == Mode.Hypixel) {
                EntityPlayerSP thePlayer = mc.getThePlayer();
                if (thePlayer.isNull()) return;
                if (thePlayer.getPosY() > 0 && thePlayer.getFallDistance() >= 2 && thePlayer.getLastTickPosY() - thePlayer.getPosY() > 0 && thePlayer.getMotionY() != 0) {
                    if (!PlayerUtil.isBlockUnder() || thePlayer.getFallDistance() > 255 || !PlayerUtil.isBlockUnder() && thePlayer.getFallDistance() > 50) {
                        return;
                    }

                    if (C02PacketUseEntity.isC02PacketUseEntity(send.getPacket())) {
                        C02PacketUseEntity packet = new C02PacketUseEntity(send.getPacket().getWrapObject());

                        if (packet.getAction() == C02Action.ATTACK) {
                            send.setCancel(true);
                        }
                    }

                    if (C03PacketPlayer.isPacketPlayer(send.getPacket())) {
                        C03PacketPlayer packet = new C03PacketPlayer(send.getPacket().getWrapObject());

                        if (packet.isMoving() && packet.isRotating()) {
                            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C04PacketPlayerPosition(packet.getX(), packet.getY(), packet.getZ(), packet.isOnGround()));
                            send.setCancel(true);
                        }
                    }
                }
            }
        }
    }
    private double getLastTickYDistance() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        return Math.hypot(thePlayer.getPosY() - thePlayer.getPrevPosY(), thePlayer.getPosY() - thePlayer.getPrevPosY());
    }
    @Override
    public String getSuffix(){
        return mode.getValue().name();
    }
    public enum Mode{
        Vanilla,
        Hypixel,
        Simple
    }
}
