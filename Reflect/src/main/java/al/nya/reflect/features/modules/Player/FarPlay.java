package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.*;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.gui.notification.Notification;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.key.EnumKey;
import al.nya.reflect.utils.math.Vector3d;
import al.nya.reflect.utils.pathfinding.PathUtils;
import al.nya.reflect.utils.render.PathRender;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.KeyBindValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityOtherPlayerMP;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemBow;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02PacketUseEntity;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C05PacketPlayerLook;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C06PacketPlayerPositionLook;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07Action;
import al.nya.reflect.wrapper.wraps.wrapper.network.C07.C07PacketPlayerDigging;
import al.nya.reflect.wrapper.wraps.wrapper.network.C08PacketPlayerBlockPlacement;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetworkManager;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;
import al.nya.reflect.wrapper.wraps.wrapper.network.S08.S08PacketPlayerPosLook;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class FarPlay extends Module {
    public DoubleValue speed = new DoubleValue("Speed", 2.0, 0.8, 1.0, "0.0");
    public OptionValue clip = new OptionValue("Clip", true);
    public OptionValue render = new OptionValue("Render", true);
    public OptionValue pullBack = new OptionValue("AutoPullBack", false);
    public KeyBindValue pushPos = new KeyBindValue("push", EnumKey.L);

    public ConcurrentMap<Long, List<Vec3>> ways = new ConcurrentHashMap<>();

    public Vector3d fakePlayerStandPos = null;

    private EntityOtherPlayerMP fakePlayer = null;

    public FarPlay() {
        super("FarPlay", ModuleType.Player);
        addValues(speed, clip, render, pullBack, pushPos);
    }

    @Override
    public void onEnable() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isNull()) return;
        thePlayer.setMotionX(0);
        thePlayer.setMotionY(0);
        thePlayer.setMotionZ(0);

        fakePlayer = new EntityOtherPlayerMP(mc.getTheWorld(), thePlayer.getGameProfile());
        fakePlayer.clonePlayer(thePlayer, true);
        fakePlayer.setRotationYawHead(thePlayer.getRotationYaw());
        fakePlayer.copyLocationAndAnglesFrom(thePlayer);
        mc.getTheWorld().addEntityToWorld((int) -(Math.random() * 10000), fakePlayer);
        if (!clip.getValue()) thePlayer.setNoClip(true);
        fakePlayerStandPos = new Vector3d(fakePlayer.getPosX(), fakePlayer.getPosY(), fakePlayer.getPosZ());
    }

    @Override
    public void onDisable() {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isNull() || fakePlayer == null) return;
        thePlayer.setPositionAndRotation(fakePlayer.getPosX(), fakePlayer.getPosY(), fakePlayer.getPosZ(), thePlayer.getRotationYaw(), thePlayer.getRotationPitch());
        mc.getTheWorld().removeEntityFromWorld(fakePlayer.getEntityId());
        fakePlayer = null;
        thePlayer.setNoClip(false);
        fakePlayerStandPos = null;
    }

    @EventTarget
    public void onKey(EventKey key) {
        if (key.getKey() == pushPos.getValue().getKeyCode()) {
            EntityPlayerSP thePlayer = mc.getThePlayer();
            double targetPosX = thePlayer.getPosX();
            double targetPosY = thePlayer.getPosY();
            double targetPosZ = thePlayer.getPosZ();
            double startPosX = fakePlayer.getPosX();
            double startPosY = fakePlayer.getPosY();
            double startPosZ = fakePlayer.getPosZ();
            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C04PacketPlayerPosition(targetPosX, targetPosY, targetPosZ, true));
            List<Vec3> tpPos = PathUtils.findBlinkPath(startPosX, startPosY, startPosZ, targetPosX, targetPosY, targetPosZ, 5);
            sendPacket(tpPos);
            fakePlayerStandPos = new Vector3d(fakePlayer.getPosX(), fakePlayer.getPosY(), fakePlayer.getPosZ());
        }
    }

    @EventTarget
    public void onUpdate(EventUpdate update) {
        Double speed = this.speed.getValue();
        EntityPlayerSP player = Minecraft.getMinecraft().getThePlayer();
        if (!clip.getValue()) player.setNoClip(true);
        player.setJumpMovementFactor(0.4f);
        player.setMotionX(0.0);
        player.setMotionY(0.0);
        player.setMotionZ(0.0);
        player.setJumpMovementFactor(player.getJumpMovementFactor() * (speed.floatValue() * 3.0f));
        if (mc.getGameSettings().getKeyBindJump().isKeyDown()) {
            player.setMotionY(player.getMotionY() + speed);
        }
        if (mc.getGameSettings().getKeyBindSneak().isKeyDown()) {
            player.setMotionY(player.getMotionY() - speed);
        }
    }

    @EventTarget
    public void onPacket(EventPacket event) {
        Packet packet = event.getPacket();
        if (C03PacketPlayer.isPacketPlayer(packet)) {
            event.setCancel(true);
        }
        if (S08PacketPlayerPosLook.isS08PacketPlayerPosLook(packet)) {
            S08PacketPlayerPosLook s08 = new S08PacketPlayerPosLook(packet.getWrapObject());
            fakePlayer.setPosition(s08.getX(), s08.getY(), s08.getZ());
            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C06PacketPlayerPositionLook(s08.getX(), s08.getY(), s08.getZ(), fakePlayer.getRotationYaw(), fakePlayer.getRotationPitch(), fakePlayer.isOnGround()));
            NotificationPublisher.queue("FarPlay", String.format("Real entity tp to %s %s %s by Server", s08.getX(), s08.getY(), s08.getZ()), NotificationType.ERROR);
            fakePlayerStandPos = new Vector3d(fakePlayer.getPosX(), fakePlayer.getPosY(), fakePlayer.getPosZ());
            if (pullBack.getValue()) {
                EntityPlayerSP thePlayer = mc.getThePlayer();
                double targetPosX = thePlayer.getPosX();
                double targetPosY = thePlayer.getPosY();
                double targetPosZ = thePlayer.getPosZ();
                double startPosX = fakePlayer.getPosX();
                double startPosY = fakePlayer.getPosY();
                double startPosZ = fakePlayer.getPosZ();
                mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C04PacketPlayerPosition(targetPosX, targetPosY, targetPosZ, true));
                List<Vec3> tpPos = PathUtils.findBlinkPath(startPosX, startPosY, startPosZ, targetPosX, targetPosY, targetPosZ, 5);
                sendPacket(tpPos);
                fakePlayerStandPos = new Vector3d(fakePlayer.getPosX(), fakePlayer.getPosY(), fakePlayer.getPosZ());
                NotificationPublisher.queue("FarPlay", String.format("Auto push entity tp to %s %s %s", fakePlayerStandPos.getX(), fakePlayerStandPos.getY(), fakePlayerStandPos.getZ()), NotificationType.SUCCESS);
            }
            event.setCancel(true);
        }
        if (C02PacketUseEntity.isC02PacketUseEntity(packet)) {
            processEntityAction(new C02PacketUseEntity(packet.getWrapObject()));
            event.setCancel(true);
        }
        if (C07PacketPlayerDigging.isCPacketPlayerDigging(packet)) {
            processPlayerAction(new C07PacketPlayerDigging(packet.getWrapObject()));
            event.setCancel(true);
        }
        if (C08PacketPlayerBlockPlacement.isPacketPlayerBlockPlacement(packet)) {
            processBlockPlacement(new C08PacketPlayerBlockPlacement(packet.getWrapObject()));
            event.setCancel(true);
        }
    }

    private void processBlockPlacement(C08PacketPlayerBlockPlacement c08) {
        NetworkManager networkManager = mc.getNetHandler().getNetworkManager();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        double targetPosX = thePlayer.getPosX();
        double targetPosY = thePlayer.getPosY();
        double targetPosZ = thePlayer.getPosZ();
        double startPosX = fakePlayer.getPosX();
        double startPosY = fakePlayer.getPosY();
        double startPosZ = fakePlayer.getPosZ();
        List<Vec3> tpPos = PathUtils.findBlinkPath(startPosX, startPosY, startPosZ, targetPosX, targetPosY, targetPosZ, 5);
        ways.put(System.currentTimeMillis(), tpPos);
        sendPacket(tpPos);
        mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C04PacketPlayerPosition(targetPosX, targetPosY, targetPosZ, true));
        networkManager.sendPacketNoEvent(c08);
        Collections.reverse(tpPos);
        sendPacket(tpPos);
        mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C04PacketPlayerPosition(startPosX, startPosY, startPosZ, true));
        return;
        //networkManager.sendPacketNoEvent(c08);
    }

    private void processPlayerAction(C07PacketPlayerDigging c07) {
        NetworkManager networkManager = mc.getNetHandler().getNetworkManager();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (c07.getStatus() == C07Action.RELEASE_USE_ITEM) {
            if (ItemBow.isItemBow(thePlayer.getInventory().getCurrentItem().getItem())) {
                double targetPosX = thePlayer.getPosX();
                double targetPosY = thePlayer.getPosY();
                double targetPosZ = thePlayer.getPosZ();
                double startPosX = fakePlayer.getPosX();
                double startPosY = fakePlayer.getPosY();
                double startPosZ = fakePlayer.getPosZ();
                List<Vec3> tpPos = PathUtils.findBlinkPath(startPosX, startPosY, startPosZ, targetPosX, targetPosY, targetPosZ, 5);
                ways.put(System.currentTimeMillis(), tpPos);
                sendPacket(tpPos);
                mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C04PacketPlayerPosition(targetPosX, targetPosY, targetPosZ, true));
                networkManager.sendPacketNoEvent(c07);
                Collections.reverse(tpPos);
                sendPacket(tpPos);
                mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C04PacketPlayerPosition(startPosX, startPosY, startPosZ, true));

                return;
            }
        }
        networkManager.sendPacketNoEvent(c07);
    }

    private void processEntityAction(C02PacketUseEntity c02) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (c02.getAction() == C02Action.ATTACK) {
            double targetPosX = thePlayer.getPosX();
            double targetPosY = thePlayer.getPosY();
            double targetPosZ = thePlayer.getPosZ();
            double startPosX = fakePlayer.getPosX();
            double startPosY = fakePlayer.getPosY();
            double startPosZ = fakePlayer.getPosZ();
            List<Vec3> tpPos = PathUtils.findBlinkPath(startPosX, startPosY, startPosZ, targetPosX, targetPosY, targetPosZ, 5);
            ways.put(System.currentTimeMillis(), tpPos);
            sendPacket(tpPos);
            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C04PacketPlayerPosition(targetPosX, targetPosY, targetPosZ, true));
            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(c02);
            Collections.reverse(tpPos);
            sendPacket(tpPos);
            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C04PacketPlayerPosition(startPosX, startPosY, startPosZ, true));
            return;
        }
        mc.getNetHandler().getNetworkManager().sendPacketNoEvent(c02);
    }

    private void sendPacket(List<Vec3> pos) {
        NetworkManager networkManager = mc.getNetHandler().getNetworkManager();
        for (Vec3 p : pos) {
            networkManager.sendPacketNoEvent(new C04PacketPlayerPosition(p.getXCoord(), p.getYCoord(), p.getZCoord(), false));
        }
        networkManager.sendPacketNoEvent(new C05PacketPlayerLook(mc.getThePlayer().getRotationYaw(), mc.getThePlayer().getRotationPitch(), false));
    }

    @EventTarget
    public void onRender3D(EventRender3D render3D) {
        if (render.getValue()) {
            ways.forEach((time, path) -> {
                if (System.currentTimeMillis() - time > 2000L) {
                    ways.remove(time);
                    return;
                }
                if (path.isEmpty()) ways.remove(time);
                double[][] points = new double[path.size()][3];
                //convert
                for (int i = 0; i < path.size(); i++) {
                    points[i][0] = path.get(i).getXCoord();
                    points[i][1] = path.get(i).getYCoord();
                    points[i][2] = path.get(i).getZCoord();
                }
                //render
                PathRender.renderPath(points, mc.getThePlayer().getEntityBoundingBox());
            });
        }
    }
}
