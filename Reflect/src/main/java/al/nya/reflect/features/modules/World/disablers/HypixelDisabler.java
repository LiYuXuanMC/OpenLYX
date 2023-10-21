package al.nya.reflect.features.modules.World.disablers;

import al.nya.reflect.events.EventType;
import al.nya.reflect.events.events.*;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.Movement.Flight;
import al.nya.reflect.features.modules.World.Scaffold;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.math.MathUtil;
import al.nya.reflect.utils.math.TimerUtil;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import al.nya.reflect.wrapper.wraps.wrapper.network.*;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C05PacketPlayerLook;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C06PacketPlayerPositionLook;
import al.nya.reflect.wrapper.wraps.wrapper.network.C0B.C0BAction;
import al.nya.reflect.wrapper.wraps.wrapper.network.C0B.C0BPacketEntityAction;
import al.nya.reflect.wrapper.wraps.wrapper.network.S08.S08PacketPlayerPosLook;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;

public class HypixelDisabler extends DisableSubModule {
    public static final List<Packet> packet = new ArrayList<>();
    private static Vec3 initPos;
    public int counter = 0;
    public double posX;
    public double posY;
    public double posZ;
    String nofication = "§l§cDisabler 正在生效，请稍等。在此期间请不要乱动。§r";
    String success = "§l§aDisabler 生效成功！§r";
    Timer timer1 = new Timer();
    Timer timer2 = new Timer();
    private final ArrayList<Packet> packets2 = new ArrayList<>();
    private final ArrayList<Packet> packets = new ArrayList<>();
    private boolean cancel;
    private final Queue<C0FPacketConfirmTransaction> queue = new ConcurrentLinkedDeque<>();
    private final ConcurrentMap<C00PacketKeepAlive, Long> map = new ConcurrentHashMap<>();

    private final TimerUtil timer = new TimerUtil();
    private boolean disabled;

    public HypixelDisabler() {
        super(DisablerMode.Hypixel);
    }

    @Override
    public void onWorld(EventWorldLoad worldLoad) {
        resetWatchdog(false);
        super.onWorld(worldLoad);
    }

    @Override
    public void enable() {
        counter = 0;
        super.enable();
        resetWatchdog(false);
    }

    @Override
    public void disable() {
        super.disable();
        resetWatchdog(true);
    }

    @Override
    public void loop(EventLoop loop) {
        if (mc.getThePlayer().isNull() || mc.getTheWorld().isNull()) {
            timer.reset();
            return;
        }

        if (!queue.isEmpty()) {
            if (queue.size() > MathUtil.getRandom(8, 12)) {
                while (!queue.isEmpty()) pollC0F();
            } else if (timer.hasPassed(MathUtil.getRandom(55, 110))) {
                pollC0F();
                timer.reset();
            }
        }

        if (!map.isEmpty()) {
            map.forEach((C00, Time) -> {
                if (Time <= System.currentTimeMillis()) {
                    mc.getThePlayer().getSendQueue().getNetworkManager().sendPacketNoEvent(C00);
                    map.remove(C00);
                }
            });
        }
    }

    @Override
    public void preUpdate(EventPreUpdate preUpdate) {
        if (timer1.hasTimeElapsed(120, true)) {
            packets2.forEach(mc.getNetHandler().getNetworkManager()::sendPacketNoEvent);
            packets2.clear();
            timer1.reset();
        }
        if (!mc.getThePlayer().isNull() && mc.getThePlayer().getTicksExisted() == 7)
            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C0BPacketEntityAction(mc.getThePlayer(), C0BAction.STOP_SPRINTING));
        super.preUpdate(preUpdate);
    }

    @Override
    public void onMove(EventMove update) {
        if (mc.getThePlayer().isNull()) return;
        if (timer1.hasTimeElapsed(10000, true)) {
            cancel = true;
            timer2.reset();
        }
        super.onMove(update);
    }
    @Override
    public void packet(EventPacket e) {
        Packet packet = e.getPacket();
        EntityPlayerSP thePlayer = mc.getThePlayer();
//        if (C03PacketPlayer.isPacketPlayer(e.getPacket()) || C04PacketPlayerPosition.isPacketPlayerPosition(e.getPacket()) || C06PacketPlayerPositionLook.isPacketPlayerPositionLook(e.getPacket())) {
//            if (thePlayer.getTicksExisted() < 50) {
//                e.setCancel(true);
//            }
//        }
        doTimerDisabler(e);
        if (C05PacketPlayerLook.isC05PacketPlayerLook(packet) && thePlayer.isRiding()) {
            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C0BPacketEntityAction(thePlayer, C0BAction.START_SPRINTING));
        } else if (C0CPacketInput.isC0CPacketInput(packet) && thePlayer.isRiding()) {
            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(e.getPacket());
            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C0BPacketEntityAction(thePlayer, C0BAction.STOP_SNEAKING));
            e.setCancel(true);
        }
        if (C03PacketPlayer.isPacketPlayer(packet)) {
            C03PacketPlayer C03 = new C03PacketPlayer(packet.getWrapObject());
            if (thePlayer.getTicksExisted() == 1) {
                initPos = new Vec3(C03.getX() + MathUtil.getRandomByThreadLocalRandom(-1000000, 1000000), C03.getY() + MathUtil.getRandomByThreadLocalRandom(-1000000, 1000000), C03.getZ() + MathUtil.getRandomByThreadLocalRandom(-1000000, 1000000));
            } else if (mc.getNetHandler().isDoneLoadingTerrain() && (initPos != null) && mc.getThePlayer().getTicksExisted() < 100) {
                C03.setX(initPos.getXCoord());
                C03.setY(initPos.getYCoord());
                C03.setZ(initPos.getZCoord());
            }
        }

        if (C0BPacketEntityAction.isC0BPacketEntityActionClass(packet)) {
            e.setCancel(true);
        }

        if (mc.getNetHandler().isDoneLoadingTerrain()) {
            if (!e.isCancel() || (C03PacketPlayer.isPacketPlayer(packet) || C0FPacketConfirmTransaction.isPacketConfirmTransaction(packet) || C00PacketKeepAlive.isC00PacketKeepAlive(packet))) {
                e.setCancel(true);
                packets2.add(e.getPacket());
            }
        }
        if (C0FPacketConfirmTransaction.isPacketConfirmTransaction(packet)) {
            final C0FPacketConfirmTransaction C0F = new C0FPacketConfirmTransaction(packet.getWrapObject());

            if (C0F.getWindowId() == 0 && C0F.getUID() < -1) {
                if (!disabled) {
                    disabled = true;
                }

                queue.offer(C0F);
                e.setCancel(true);
            }
        }

        if (C00PacketKeepAlive.isC00PacketKeepAlive(packet)) {
            final C00PacketKeepAlive C00 = new C00PacketKeepAlive(packet.getWrapObject());

            map.put(C00, System.currentTimeMillis() + MathUtil.getRandom(800, 880));
            e.setCancel(true);
        }
        super.packet(e);
    }

    private void doTimerDisabler(EventPacket e) {
        if (C03PacketPlayer.isPacketPlayer(e.getPacket())) {
            C03PacketPlayer c03PacketPlayer = new C03PacketPlayer(e.getPacket().getWrapObject());
            if (!c03PacketPlayer.isMoving() && !mc.getThePlayer().isUsingItem()) {
                e.setCancel(true);
            }
            if (cancel) {
                if (!timer2.hasTimeElapsed(400, true)) {
                    if (ModuleManager.getModule(Scaffold.class).isEnable()) {
//                        e.setCancel(true);
//                        packets.add(e.getPacket());
                    }
                } else {
                    packets.forEach(mc.getNetHandler().getNetworkManager()::sendPacketNoEvent);
                    packets.clear();
                    cancel = false;
                }
            }
        }
    }

    @Override
    public void render2D(EventRender2D evt) {
        int tick = mc.getThePlayer().getTicksExisted();
        ScaledResolution sr = new ScaledResolution(mc);
        if (tick < 60 || !disabled) {
            mc.getFontRenderer().drawStringWithShadow(nofication, sr.getScaledWidth() / 2 - mc.getFontRenderer().getStringWidth(nofication) / 2, (sr.getScaledHeight() / 2) / 2, 0xFFFFFF);
        }
        if (tick > 60 && disabled && tick < 80) {
            mc.getFontRenderer().drawStringWithShadow(success, sr.getScaledWidth() / 2 - mc.getFontRenderer().getStringWidth(success) / 2, (sr.getScaledHeight() / 2) / 2, 0xFFFFFF);
        }
    }

    private void pollC0F() {
        if (!queue.isEmpty())
            mc.getThePlayer().getSendQueue().getNetworkManager()
                    .sendPacketNoEvent(queue.poll());
    }

    private void resetWatchdog(boolean release) {
        if (release) {
            while (!queue.isEmpty()) pollC0F();
            map.forEach((C00, Time) -> mc.getThePlayer().getSendQueue().getNetworkManager().sendPacketNoEvent(C00));
            map.clear();
        } else {
            queue.clear();
            map.clear();
        }
        disabled = false;
        timer.reset();
    }

    class Timer {

        public long lastMS = System.currentTimeMillis();

        public void reset() {
            lastMS = System.currentTimeMillis();
        }

        public boolean hasTimeElapsed(long time, boolean reset) {
            if (System.currentTimeMillis() - lastMS > time) {
                if (reset)
                    reset();

                return true;
            }
            return false;
        }

        public boolean hasTimeElapsed(double time, boolean reset) {
            if (System.currentTimeMillis() - lastMS > time) {
                if (reset)
                    reset();

                return true;
            }
            return false;
        }
    }

}
