package al.nya.reflect.features.modules.Player;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.events.events.EventTick;
import al.nya.reflect.features.command.commands.Teleport;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.World.Waypoints;
import al.nya.reflect.features.modules.World.waypoint.WaypointManager;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.utils.LWJGLKeyBoard;
import al.nya.reflect.utils.math.MathUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C06PacketPlayerPositionLook;
import al.nya.reflect.wrapper.wraps.wrapper.network.*;
import al.nya.reflect.wrapper.wraps.wrapper.network.S08.S08EnumFlags;
import al.nya.reflect.wrapper.wraps.wrapper.network.S08.S08PacketPlayerPosLook;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UHCHelper extends Module {
    public final OptionValue lightningHelper = new OptionValue("LightningHelper", true);
    public static final OptionValue clickPlayerTP = new OptionValue("ClickTP", true);
    public static final ModeValue YMode = new ModeValue("Y", YMODE.L100, YMODE.values()) {
        @Override
        public boolean show() {
            return clickPlayerTP.getValue();
        }
    };
    public final OptionValue test = new OptionValue("Test", false);
    public final OptionValue packetMeme = new OptionValue("Hypixel-Meme", false) {
        @Override
        public boolean show() {
            return Reflect.debug;
        }
    };
    public final OptionValue packetMemeEdit = new OptionValue("Hypixel-Meme-Transform", false) {
        @Override
        public boolean show() {
            return Reflect.debug;
        }
    };
    public final DoubleValue collectTimer = new DoubleValue("Dormant-Collect-Timer", 30D, 1D, 10D, "0") {
        @Override
        public boolean show() {
            return Reflect.debug;
        }
    };
    public final DoubleValue freezeLatency = new DoubleValue("Freeze-Collect-Latency", 50D, 10D, 15D, "0") {
        @Override
        public boolean show() {
            return Reflect.debug;
        }
    };
    public final TimeHelper timer = new TimeHelper(), freezeTimer = new TimeHelper(), resetTimer = new TimeHelper();
    public final ConcurrentLinkedQueue<PosLookPacket> flagStock = new ConcurrentLinkedQueue<>();
    private final OptionValue packetDormant = new OptionValue("Position-Dormant", false) {
        @Override
        public boolean show() {
            return Reflect.debug;
        }
    };
    private final OptionValue lesspacket = new OptionValue("Less-Packet", false) {
        @Override
        public boolean show() {
            return Reflect.debug;
        }
    };
    private final OptionValue positionSpoof = new OptionValue("Position-Spoof", false) {
        @Override
        public boolean show() {
            return Reflect.debug;
        }
    };
    public boolean hasDisable;
    private final OptionValue packetFreeze = new OptionValue("Hypixel-Freeze", false) {
        @Override
        public boolean show() {
            return Reflect.debug;
        }
    };
    private final List<Packet> packets = new ArrayList<>();
    private final TimeHelper burst = new TimeHelper(), choke = new TimeHelper();
    private final List<Packet> dormant = new ArrayList<>();
    public double x, y, z;
    int invalid = 0;
    long delay = 150;

    @Override
    public void onDisable() {
        packets.clear();
    }

    public UHCHelper() {
        super("UHCHelper", ModuleType.Player);
        addValues(lightningHelper, clickPlayerTP, YMode, test, packetMeme, packetMemeEdit, collectTimer, freezeLatency, lesspacket, packetDormant, positionSpoof, packetFreeze);
    }

    @EventTarget
    public void onPacket(EventPacket event) {
        if (S07PacketRespawn.isS07PacketRespawn(event.getPacket())) {
            hasDisable = false;
            timer.reset();
        }
        if (S08PacketPlayerPosLook.isS08PacketPlayerPosLook(event.getPacket())) {
            EntityPlayerSP thePlayer = mc.getThePlayer();
            WorldClient world = mc.getTheWorld();
            if (!thePlayer.isNull() && !world.isNull()) {
                S08PacketPlayerPosLook packet = new S08PacketPlayerPosLook(event.getPacket().getWrapObject());
                double d0 = packet.getX();
                double d1 = packet.getY();
                double d2 = packet.getZ();
                float f = packet.getYaw();
                float f1 = packet.getPitch();

                if (packet.getFlags().contains(S08EnumFlags.X)) {
                    d0 += thePlayer.getPosX();
                } else {
                    thePlayer.setMotionX(0.0D);
                }

                if (packet.getFlags().contains(S08EnumFlags.Y)) {
                    d0 += thePlayer.getPosY();
                } else {
                    thePlayer.setMotionY(0.0D);
                }

                if (packet.getFlags().contains(S08EnumFlags.Z)) {
                    d0 += thePlayer.getPosZ();
                } else {
                    thePlayer.setMotionZ(0.0D);
                }

                x = packet.getX();
                y = packet.getY();
                z = packet.getZ();

                thePlayer.setPositionAndRotation(d0, d1, d2, f, f1);

                if (freezeTimer.hasReached(10000) || (!resetTimer.hasReached(175)) && hasDisable)
                    freezeTimer.reset();

                if (packetMeme.getValue())
                    if (!resetTimer.hasReached(175)) {
                        NotificationPublisher.queue("UHCHelper", "Packet sent", NotificationType.SUCCESS);
                        resetTimer.reset();
                        if (packetMemeEdit.getValue())
                            mc.getNetHandler().getNetworkManager().sendPacket(new C04PacketPlayerPosition(packet.getX(), packet.getY(), packet.getZ(), false));
                        else
                            event.setCancel(true);

                        flagStock.add(new PosLookPacket(new C06PacketPlayerPositionLook(packet.getX(), packet.getY(),
                                packet.getZ(), packet.getYaw(), packet.getPitch(), false)));
                        return;
                    } else {
                        for (PosLookPacket packett : flagStock) {
                            if (packett.isExpired()) {
                                mc.getNetHandler().getNetworkManager().sendPacket(packett.getPacket());
                                flagStock.remove(packett);
                            }

                        }
                    }

                if (!hasDisable)
                    mc.getNetHandler().getNetworkManager().sendPacket(new C04PacketPlayerPosition(MathUtil.randomInRange(-0.99, 0.99) + Teleport.pos[0], MathUtil.randomInRange(-0.99, 0.99) + Teleport.pos[1],
                            MathUtil.randomInRange(-0.99, 0.99) + Teleport.pos[2], true));
                else
                    mc.getNetHandler().getNetworkManager().sendPacket(new C06PacketPlayerPositionLook(packet.getX(), packet.getY(),
                            packet.getZ(), packet.getYaw(), packet.getPitch(), false));

                resetTimer.reset();
                event.setCancel(true);

            }

        }

        if (hasDisable && S00PacketKeepAlive.isS00PacketKeepAlive(event.getPacket())) {
            packets.add(event.getPacket());
            event.setCancel(true);
        }

        if (S32PacketConfirmTransaction.isS32PacketConfirmTransaction(event.getPacket())) {
            S32PacketConfirmTransaction S32 = new S32PacketConfirmTransaction(event.getPacket());
            if (S32.getWindowId() == 0
                    && S32.getActionNumber() < 0) {
                if (!hasDisable) {
                    hasDisable = true;
                    burst.reset();
                    invalid = 0;
                }


                packets.add(event.getPacket());
                event.setCancel(true);
            }
        }

        if (C03PacketPlayer.isPacketPlayer(event.getPacket())) {
            C03PacketPlayer C03 = new C03PacketPlayer(event.getPacket());
            EntityPlayerSP thePlayer = mc.getThePlayer();
            if (lesspacket.getValue())
                if (!(C03).isMoving() && !(C03).isRotating())
                    event.setCancel(true);

            if (!event.isCancel()) {
                //Packet Dormant
                if (packetDormant.getValue() && thePlayer.getTicksExisted() > 32) {
                    //Collect Packets for 1 second
                    if (!choke.hasReached(10000)) return;

                    if (!positionSpoof.getValue() || !((C03).isMoving() && (C03).isRotating()))
                        dormant.add(event.getPacket());
                    else
                        dormant.add(new C04PacketPlayerPosition(x, y, z, (C03).isOnGround()));

                    event.setCancel(true);
                    if (choke.hasReached(10000 + collectTimer.getMax().longValue() * 100)) choke.reset();
                }


                if (packetFreeze.getValue())
                    if (!freezeTimer.hasReached(freezeLatency.getValue().longValue() * 100)) {
                        dormant.add(event.getPacket());
                        event.setCancel(true);
                    }

                //position Spoof
                if (!event.isCancel()) {
                    if (positionSpoof.getValue())
                        if ((C03).isMoving() && (C03).isRotating())
                            if (!packetDormant.getValue()) {
                                //sendPacket(Packet packetIn, GenericFutureListener listener)
                                mc.getNetHandler().getNetworkManager().sendPacket
                                        (new C04PacketPlayerPosition(x, y, z, (C03).isOnGround()));
                                event.setCancel(true);
                            }
                }
            }
        }


    }
    @EventTarget
    public void onTick(EventTick e) {
        if (mc.isSingleplayer()) return;

        if (lightningHelper.getValue()) {
            for (Waypoints.WaypointData waypoint : WaypointManager.INSTANCE.getWaypointDataList()) {
                if (waypoint == null) continue;
                if (waypoint.getName().startsWith("LIGHTNING_")) {
                    if (waypoint.getTime() + 110000 < System.currentTimeMillis()) {
                        WaypointManager.INSTANCE.getWaypointDataList().remove(waypoint);
                    }
                }
            }
        }

        if (!hasDisable)
            packetMeme.setValue(true);
        else
            packetMeme.setValue(false);

        if (!mc.getCurrentScreen().isNull() && LWJGLKeyBoard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            mc.displayGuiScreen(new GuiScreen(null));
        }

        if (!burst.hasReached(delay)) return;
        resetPackets(mc.getNetHandler());
        burst.reset();

        if (packetFreeze.getValue()) {
            if (!freezeTimer.hasReached(freezeLatency.getValue().longValue() * 100)) return;
            resetPacket();
        }

        if (!flagStock.isEmpty() && hasDisable) {
            for (PosLookPacket packet : flagStock) {
                if (System.currentTimeMillis() - packet.time > 3000) {
                    mc.getNetHandler().getNetworkManager().sendPacket(packet.getPacket());
                    flagStock.remove(packet);
                }
            }
        }


    }
    @EventTarget
    public void onUpdatePre(EventPreUpdate e) {
        if (mc.isSingleplayer()) return;
        if (test.getValue() && !hasDisable && timer.hasReached(1000)) {
            e.setX(Teleport.pos[0]);
            e.setY(Teleport.pos[1]);
            e.setZ(Teleport.pos[2]);
            e.setOnGround(true);
        }
    }

    private void resetPackets(INetHandlerPlayClient netHandler) {
        if (packets.size() > 0) {
            synchronized (packets) {
                while (packets.size() != 0) {
                    packets.get(0).processPacket(netHandler);
                    if (S32PacketConfirmTransaction.isS32PacketConfirmTransaction(packets.get(0))) {
                        /*
                                                if (packetChoke.getPropertyValue()) {
                            if (invalid > 8) {
                                mc.getNetHandler().getNetworkManager().sendPacket(new C0FPacketConfirmTransaction(1, ((S32PacketConfirmTransaction) packets.get(0)).getActionNumber(), true));
                                invalid = 0;
                            }
                            invalid++;
                        }
                         */
                    }
                    packets.remove(packets.get(0));
                    if (delay > 375) delay = 200;
                    else delay += 25;
                }
            }
        }
    }


    private void resetPacket() {
        if (dormant.size() > 0) {
            synchronized (dormant) {
                while (dormant.size() != 0) {
                    //sendPacket(Packet packetIn, GenericFutureListener listener)
                    mc.getNetHandler().getNetworkManager().sendPacket(dormant.get(0));
                    dormant.remove(dormant.get(0));
                }
            }
        }
    }

    public enum YMODE {
        TargetY,
        L100,
        L200
    }

    public static class PosLookPacket {
        private final C03PacketPlayer c03PacketPlayer;
        private final long time;

        public PosLookPacket(C03PacketPlayer c03PacketPlayer) {
            this.c03PacketPlayer = c03PacketPlayer;
            time = System.currentTimeMillis();
        }

        public C03PacketPlayer getPacket() {
            return c03PacketPlayer;
        }

        public long getTime() {
            return time;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() - time > 1200;
        }
    }

    class TimeHelper {
        private long lastMS;

        public TimeHelper() {
            this.lastMS = 0L;
        }

        public boolean delay(float milliSec) {
            return (float) (getCurrentMS() - this.lastMS) >= milliSec;
        }

        public int convertToMS(int d) {
            return 1000 / d;
        }

        public long getCurrentMS() {
            return System.nanoTime() / 1000000L;
        }

        public boolean hasReached(long milliseconds) {
            return getCurrentMS() - lastMS >= milliseconds;
        }

        public boolean hasTimeReached(long delay) {
            return System.currentTimeMillis() - lastMS >= delay;
        }

        public long getDelay() {
            return System.currentTimeMillis() - lastMS;
        }

        public void reset() {
            lastMS = getCurrentMS();
        }

        public void setLastMS() {
            lastMS = System.currentTimeMillis();
        }

        public void setLastMS(long lastMS) {
            this.lastMS = lastMS;
        }

        public long getDifference() {
            return getCurrentMS() - lastMS;
        }

        public void setDifference(long diff) {
            lastMS = (getCurrentMS() - diff);
        }
    }

}
