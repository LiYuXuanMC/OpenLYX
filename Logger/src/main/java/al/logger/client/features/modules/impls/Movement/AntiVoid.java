package al.logger.client.features.modules.impls.Movement;

import al.logger.client.Logger;
import al.logger.client.event.EventType;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.player.EventUpdate;
import al.logger.client.event.client.world.EventLoadWorld;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.impls.World.Scaffold;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.network.PacketUtil;
import al.logger.client.utils.player.PlayerUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.wrapper.LoggerMC.block.BlockAir;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C04PacketPlayerPosition;
import al.logger.client.wrapper.LoggerMC.network.server.S08.S08PacketPlayerPosLook;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.Vec3;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AntiVoid extends Module {
    DoubleValue falldistance = new DoubleValue("FallDistance", 20.0, 1.0, 3.0,0.5);
    private float[] last = new float[3];
    private TimerUtils timer = new TimerUtils();
    private ArrayList<Packet> packets = new ArrayList<>();

    DoubleValue pullbackTime = new DoubleValue("PullbackTime", 1000.0, 100.0, 2000.0, 100.0);
    private boolean falling = false;

    public AntiVoid(){
        super("AntiVoid" , "AntiVoid" , Category.Movement);
        addValues(pullbackTime);
    }
    @Listener
    public void onUpdate(EventUpdate e){
        if (mc.getThePlayer().isNull() || mc.getTheWorld().isNull()) {
            return;
        }

        if (!isAboveVoid()) {
            last = new float[]{(float) mc.getThePlayer().getPosX(), (float) mc.getThePlayer().getPosY(), (float) mc.getThePlayer().getPosZ()};
        }
    }

    @Listener
    public void onPacket(EventPacket e){
        Packet packet = new Packet(e.getPacket().getWrappedObject());
        if (S08PacketPlayerPosLook.isS08PacketPlayerPosLook(packet)){
            packets.clear();
            S08PacketPlayerPosLook s08 = new S08PacketPlayerPosLook(packet.getWrappedObject());
            float x = (float) s08.getX();
            float y = (float) s08.getY();
            float z = (float) s08.getZ();

            if (!isAboveVoid(x, y, z)) {
                last[0] = x;
                last[1] = y;
                last[2] = z;
//            } else if (mc.player.ticksExisted > 600) {
//                DebugUtil.log("AntiVoid", "AntiVoid失效");
//                mc.getNetHandler().sendPacket(new C01PacketChatMessage("/play solo_insane"));
            }
        }

        if (mc.getThePlayer() != null && mc.getThePlayer().getTicksExisted() < 100) {
            packets.clear();
            return;
        }
        if (C03PacketPlayer.isPacketPlayer(packet)) {
            if (isAboveVoid() && !mc.getThePlayer().isOnGround()) {
                e.cancel();
                packets.add(packet);
                if (timer.isDelayComplete(pullbackTime.getValue())) {
                    mc.getThePlayer().setPosition(last[0], last[1] + 0.1, last[2]);
                    PacketUtil.sendPacketNoEvent(new C04PacketPlayerPosition(last[0], last[1], last[2], true));
                    mc.getThePlayer().setMotionY(0.0);
                    mc.getThePlayer().setMotionX(-mc.getThePlayer().getMotionX() / 2);
                    mc.getThePlayer().setMotionZ(-mc.getThePlayer().getMotionZ() / 2);
                    packets.clear();
                    timer.reset();
                }
            } else {
                if (packets.size() > 0) {
                    for (Packet p : packets) {
                        PacketUtil.sendPacketNoEvent(p);
                    }
                    packets.clear();
                }
                timer.reset();
            }
        }
    }

    public boolean isAboveVoid() {
        if (mc.getThePlayer() == null) {
            return false;
        }
        if (mc.getThePlayer().getPosY() < 0) {
            return true;
        }
        for (int i = (int) (mc.getThePlayer().getPosY() - 1); i >= 1; i--) {
            if (!BlockAir.isBlockAir(mc.getTheWorld().getBlockState(new BlockPos(mc.getThePlayer().getPosX(), i, mc.getThePlayer().getPosZ())).getBlock())) {
                return false;
            }
        }
        return !mc.getThePlayer().isOnGround();
    }

    public boolean isAboveVoid(float x, float y, float z) {
        if (mc.getThePlayer() == null) {
            return false;
        }
        if (y < 0) {
            return true;
        }
        for (int i = (int) (y - 1); i >= 1; i--) {
            if (!(BlockAir.isBlockAir(mc.getTheWorld().getBlockState(new BlockPos(mc.getThePlayer().getPosX(), i, mc.getThePlayer().getPosZ())).getBlock()))) {
                return false;
            }
        }
        return !mc.getThePlayer().isOnGround();
    }
    @Listener
    public void onWorldChange(EventLoadWorld e){
        packets.clear();
    }
}

