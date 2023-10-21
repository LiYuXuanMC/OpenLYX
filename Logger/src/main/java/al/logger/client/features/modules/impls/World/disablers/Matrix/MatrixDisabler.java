package al.logger.client.features.modules.impls.World.disablers.Matrix;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventAttack;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.event.client.player.EventUpdate;
import al.logger.client.event.client.world.EventLoadWorld;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.impls.Movement.Speed;
import al.logger.client.features.modules.impls.Movement.TargetStrafe;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.network.PacketUtil;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.block.BlockAir;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.network.client.*;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02PacketUseEntity;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C05PacketPlayerLook;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C06PacketPlayerPositionLook;
import al.logger.client.wrapper.LoggerMC.network.client.C07.C07Action;
import al.logger.client.wrapper.LoggerMC.network.client.C07.C07PacketPlayerDigging;
import al.logger.client.wrapper.LoggerMC.network.client.C0B.C0BAction;
import al.logger.client.wrapper.LoggerMC.network.client.C0B.C0BPacketEntityAction;
import al.logger.client.wrapper.LoggerMC.network.server.S08.S08PacketPlayerPosLook;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.EnumFacing;
import by.radioegor146.nativeobfuscator.Native;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import static al.logger.client.utils.network.PacketUtil.sendPacketNoEvent;

@Native
public class MatrixDisabler extends Module {

    public OptionValue cancel = new OptionValue("Cancel" ,false);

    public OptionValue reset = new OptionValue("Reset" ,false);
    public OptionValue strafe = new OptionValue("Strafe" ,false);
    public OptionValue fastuse = new OptionValue("FastUse" ,false);

    public OptionValue debug = new OptionValue("Debug" ,false);

    public OptionValue c0b = new OptionValue("C0B" ,false);

    public OptionValue s08d = new OptionValue("S08" ,false);
    public OptionValue movefix = new OptionValue("MoveFix" ,false);


    OptionValue strict = new OptionValue("Strict" , false);

    DoubleValue tick = new DoubleValue("Tick" , 30 , 1 ,10 ,1);
    private static List<Packet> packetBuf = new CopyOnWriteArrayList<>();
    TimerUtils timer = new TimerUtils();

    private final CopyOnWriteArrayList<Packet> packetsQueue = new CopyOnWriteArrayList<>();

    private boolean blinkingMove;
    private boolean blinkingPing;
    private boolean blinkingOther;

    private boolean clearedPackets;
    private boolean inventoryOpen;
    private int chestCloseTicks;
    private int chestId;
    private int groundSpoofTicks, ticks;

    private double motionX, motionZ;
    private double lastMotionX, lastMotionZ;

    private double lastX, lastY, lastZ;


    private boolean blinking;

    boolean pflag = false;

    private LinkedList<Packet> packets = new LinkedList<>();

    private LinkedBlockingQueue<C03PacketPlayer> c03s = new LinkedBlockingQueue<C03PacketPlayer>();
    public MatrixDisabler(){
        super("Matrix" , Category.World);
        addValues(strafe ,strict,tick ,s08d , movefix);
    }

    @Listener
    public void onPacket(EventPacket event) {
            Packet packet = new Packet(event.getPacket().getWrappedObject());

            if (C0FPacketConfirmTransaction.isPacketConfirmTransaction(packet)) {
                event.cancel();
            }


            if (movefix.getValue()){
                if (C06PacketPlayerPositionLook.isPacketPlayerPositionLook(packet) && pflag){
                    pflag = false;
                    mc.getThePlayer().setMotionX(lastX);
                    mc.getThePlayer().setMotionY(lastY);
                    mc.getThePlayer().setMotionZ(lastZ);
                }
            }

            if (S08PacketPlayerPosLook.isS08PacketPlayerPosLook(packet)){
                S08PacketPlayerPosLook s08 = new S08PacketPlayerPosLook(packet.getWrappedObject());
                double x = s08.getX();
                double y = s08.getX();
                double z = s08.getX();
                float yaw = s08.getYaw();
                float pitch = s08.getPitch();
                if (movefix.getValue()){
                    pflag = true;
                    lastX = x;
                    lastY = y;
                    lastZ = z;
                }



                if (s08d.getValue()){
                    event.cancel();
                    //PacketUtil.sendPacketNoEvent(new C03PacketPlayer(x,y,z , mc.getThePlayer().isOnGround()));
                    PacketUtil.sendPacketNoEvent(new C05PacketPlayerLook(yaw , pitch , mc.getThePlayer().isOnGround()));
                }

            }

            if (C00PacketKeepAlive.isC00PacketKeepAlive(packet)) {
                event.cancel();
            }

        if (strafe.getValue() && !mc.isSingleplayer()  && Logger.getInstance().getModuleManager().getModule(Speed.class).isEnable()) {
            if (C02PacketUseEntity.isC02PacketUseEntity(packet)) return;
            if (C0APacketAnimation.isC0APacket(packet)) return;
            if (C08PacketPlayerBlockPlacement.isPacketPlayerBlockPlacement(packet)) return;
            if (C09PacketHeldItemChange.isC09PacketHeldItemChange(packet)) return;
            //    if (event.getPacket() instanceof C03PacketPlayer) return;
            // if (event.getPacket() instanceof C0BPacketEntityAction) return;
            if (C07PacketPlayerDigging.isCPacketPlayerDigging(packet)) return;
            if (mc.getThePlayer() == null || mc.getThePlayer().getTicksExisted() < 5) {
                if (!clearedPackets) {
                    packetsQueue.clear();
                    stopAll();
                    clearedPackets = true;
                }
            } else {
                clearedPackets = false;
            }


            if (!event.isCanceled()) {
                if (isMove(packet)) {
                    if (blinkingMove) {
                        event.cancel();
                        packetsQueue.add(packet);
                    }
                } else if (isPing(packet)) {
                    if (blinkingPing) {
                        event.cancel();
                        packetsQueue.add(packet);
                    }
                } else {
                    if (blinkingOther) {
                        event.cancel();
                        packetsQueue.add(packet);
                    }
                }
            }
        }

        if (C0BPacketEntityAction.isC0BPacketEntityActionClass(packet)) {
            final C0BPacketEntityAction wrapper = new C0BPacketEntityAction(packet.getWrappedObject()) ;

            if (wrapper.getAction().equals(C0BAction.START_SPRINTING) || wrapper.getAction().equals(C0BAction.STOP_SPRINTING)) {
                event.cancel();
                mc.getThePlayer().setServerSprintState(false);
            }
        }
    }


    @Listener
    public void onPre(EventPreUpdate e) {
        if (strafe.getValue()){
            if (!mc.isSingleplayer() && strafe.getValue() && (Logger.getInstance().getModuleManager().getModule(Speed.class).isEnable() || Logger.getInstance().getModuleManager().getModule(TargetStrafe.class).isEnable())) {
                if (mc.getThePlayer().getTicksExisted() < 20) {
                    groundSpoofTicks = ticks = 0;
                }

                if (false) {
                    if (++ticks >= 2 && mc.getThePlayer().getFallDistance() < 1 && isBlockUnder(8)) {
                        boolean canGroundSpoof = false;

                        double diff = strict.getValue() ? 0.034 : 0.098;

                        double lastX = lastMotionX * 0.91F;
                        double lastZ = lastMotionZ * 0.91F;

                        if (motionX - lastX > diff) {
                            canGroundSpoof = true;
                        } else if (motionX - lastX < -diff) {
                            canGroundSpoof = true;
                        } else if (motionZ - lastZ > diff) {
                            canGroundSpoof = true;
                        } else if (motionZ - lastZ < -diff) {
                            canGroundSpoof = true;
                        }

                        if (canGroundSpoof) {
                            e.setOnGround(true);

                            if (groundSpoofTicks == 1) {
                                groundSpoofTicks = tick.getValue().intValue() + 1;
                                releaseMove();
                                releaseOther();
                            } else if (groundSpoofTicks < 1) {
                                groundSpoofTicks = tick.getValue().intValue() + 1;
                            }
                            ticks = 0;

                            startBlinkingMove();
                            startBlinkingOther();
                            blinking = true;
                        }
                        else e.setOnGround(mc.getThePlayer().isOnGround());
                    }
                } else {
                    ticks = 0;
                }

                if (groundSpoofTicks == 1 && blinking) {
                    stopAll();
                    blinking = false;
                }

                groundSpoofTicks--;
            }
        }
        super.onPre(e);
    }



    @Override
    public void onEnable() {
        super.onEnable();
        packets.clear();
        timer.reset();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        stopAll();
        resetDisabler();
    }

    @Listener
    public void onUpdaet(EventUpdate e) {
        if(mc.getTheWorld().isNull() || mc.getThePlayer().isNull()){
            return;
        }
        if (timer.hasTimeElapsed(2000 , true)) {
            sendPacketNoEvent(new C00PacketKeepAlive());
        }
    }

    public void startBlinkingMove() {
        this.blinkingMove = true;
    }
    public void startBlinkingOther() {
        this.blinkingOther = true;
    }
    public void releaseOther() {
        if(!packetsQueue.isEmpty()) {
            ArrayList<Packet> toRemove = new ArrayList<>();

            for(Packet packet : packetsQueue) {
                if(!isMove(packet) && !isPing(packet)) {
                    sendPacketNoEvent(packet);
                    toRemove.add(packet);
                }
            }

            if(!toRemove.isEmpty()) {
                for(Packet p : toRemove) {
                    packetsQueue.remove(p);
                }
            }

            toRemove.clear();
        }
    }
    public void stopAll() {
        this.releaseAll();

        this.blinkingMove = false;
        this.blinkingPing = false;
        this.blinkingOther = false;
    }
    public void releaseMove() {
        if(!packetsQueue.isEmpty()) {
            ArrayList<Packet> toRemove = new ArrayList<>();

            for(Packet packet : packetsQueue) {
                if(isMove(packet)) {
                    sendPacketNoEvent(packet);
                    toRemove.add(packet);
                }
            }

            if(!toRemove.isEmpty()) {
                for(Packet p : toRemove) {
                    packetsQueue.remove(p);
                }
            }

            toRemove.clear();
        }
    }
    public boolean isBlinking() {
        return blinkingMove || blinkingPing || blinkingOther;
    }

    public boolean isBlinkingAll() {
        return blinkingMove && blinkingPing && blinkingOther;
    }
    public void startBlinkingAll() {
        this.blinkingMove = true;
        this.blinkingPing = true;
        this.blinkingOther = true;
    }

    public void releaseAll() {
        if(!packetsQueue.isEmpty()) {
            for(Packet packet : packetsQueue) {
                sendPacketNoEvent(packet);
            }

            packetsQueue.clear();
        }
    }

    public boolean isMove(Packet p) {
        return C03PacketPlayer.isPacketPlayer(p) || C0BPacketEntityAction.isC0BPacketEntityActionClass(p);
    }

    public boolean isPing(Packet p) {
        return C0FPacketConfirmTransaction.isPacketConfirmTransaction(p)|| C00PacketKeepAlive.isC00PacketKeepAlive(p);
    }
    @Listener
    public void onLoadWorld(EventLoadWorld event) {
        resetDisabler();
    }

    public boolean isBlockUnder() {
        for(int y = (int) mc.getThePlayer().getPosY(); y >= 0; y--) {
            if(!(BlockAir.isBlockAir(mc.getTheWorld().getBlockState(new BlockPos(mc.getThePlayer().getPosX(), y, mc.getThePlayer().getPosZ())).getBlock()))) {
                return true;
            }
        }
        return false;
    }

    public boolean isBlockUnder(int distance) {
        for(int y = (int) mc.getThePlayer().getPosY(); y >= (int) mc.getThePlayer().getPosY() - distance; y--) {
            if(!(BlockAir.isBlockAir(mc.getTheWorld().getBlockState(new BlockPos(mc.getThePlayer().getPosX(), y, mc.getThePlayer().getPosZ())).getBlock()))) {
                return true;
            }
        }
        return false;
    }

    private boolean isKeepAlive(Packet packet) {
        return C00PacketKeepAlive.isC00PacketKeepAlive(packet);
    }

    private boolean isConfirm(Packet packet) {
        return C0FPacketConfirmTransaction.isPacketConfirmTransaction(packet);
    }

    private void resetDisabler() {
        packets.forEach(p -> {
            if (isKeepAlive(p)) sendPacketNoEvent(p);
        });
        packets.forEach(p -> {
            if (isConfirm(p)) sendPacketNoEvent(p);
        });
        packets.clear();
        timer.reset();
    }
}
