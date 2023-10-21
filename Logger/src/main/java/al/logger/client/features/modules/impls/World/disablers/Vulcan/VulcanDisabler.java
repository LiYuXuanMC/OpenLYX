package al.logger.client.features.modules.impls.World.disablers.Vulcan;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.world.EventLoadWorld;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.network.PacketUtil;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.network.client.C07.C07Action;
import al.logger.client.wrapper.LoggerMC.network.client.C07.C07PacketPlayerDigging;
import al.logger.client.wrapper.LoggerMC.network.client.C0B.C0BAction;
import al.logger.client.wrapper.LoggerMC.network.client.C0B.C0BPacketEntityAction;
import al.logger.client.wrapper.LoggerMC.network.client.C0FPacketConfirmTransaction;
import al.logger.client.wrapper.LoggerMC.network.client.C17PacketCustomPayload;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.EnumFacing;
import tv.twitch.chat.Chat;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


//Skid From FDP Client
public class VulcanDisabler extends Module {

    OptionValue sprint = new OptionValue("Sprint", false);
    OptionValue combat = new OptionValue("Combat" , false);
    OptionValue strafe = new OptionValue("Strafe" , false);

    OptionValue debug = new OptionValue("Debug" , false);
    private float c03Counter = 0;

    private int vulTickCounterUID = -25767;
    List<Packet> packetBuffer = new CopyOnWriteArrayList<Packet>();
    private final TimerUtils fakeLagDelay = new TimerUtils();
    public VulcanDisabler(){
        super("Vulcan" , Category.World);
        addValues(sprint,combat,strafe,debug);
    }

    @Override
    public void onEnable() {
        c03Counter = -15;
        super.onEnable();
    }
    @Listener
    public void onWorldLoad(EventLoadWorld e){
        c03Counter = -15;
    }

    @Listener
    public void onTick(EventTick e){
        if (mc.getThePlayer().isNull() || mc.getTheWorld().isNull()){
            return;
        }

        if (sprint.getValue()){
            PacketUtil.sendPacket(new C0BPacketEntityAction(mc.getThePlayer() , C0BAction.START_SPRINTING));
            PacketUtil.sendPacket(new C0BPacketEntityAction(mc.getThePlayer() , C0BAction.STOP_SPRINTING));
        }

        if (combat.getValue()){
            if(fakeLagDelay.hasTimePassed(5000L) && packetBuffer.size() > 4) {
                fakeLagDelay.reset();
                while (packetBuffer.size() > 4) {
                    for (Packet packet : packetBuffer) {
                        PacketUtil.sendPacketNoEvent(packet);
                    }
                }
            }
        }
    }


    @Listener
    public void onPacket(EventPacket e) {
        Packet packet = new Packet(e.getPacket().getWrappedObject());
        if (combat.getValue()){
            if (C17PacketCustomPayload.isC17PacketCustomPayload(packet)) {
                e.cancel();
                if (debug.getValue()){
                    ChatUtils.message("Cancel C17");
                }
            }
        }

        if (strafe.getValue()){
            if (C03PacketPlayer.isPacketPlayer(packet)){
                c03Counter++;
                C03PacketPlayer c03 = new C03PacketPlayer(packet.getWrappedObject());
                if (c03.isMoving()){
                    if (c03Counter > 6){
                        PacketUtil.sendPacketNoEvent(new C07PacketPlayerDigging(C07Action.STOP_DESTROY_BLOCK, BlockPos.ORIGIN , EnumFacing.DOWN));
                        c03Counter = 0;
                    }else if (c03Counter == 4) {
                        PacketUtil.sendPacketNoEvent(new C07PacketPlayerDigging(C07Action.START_DESTROY_BLOCK, BlockPos.ORIGIN, EnumFacing.DOWN));
                    }
                }
            }
        }

        if (combat.getValue()){
            if (C0FPacketConfirmTransaction.isPacketConfirmTransaction(packet)){
                C0FPacketConfirmTransaction c0f = new C0FPacketConfirmTransaction(packet.getWrappedObject());
                if (Math.abs((Math.abs((c0f.getUID())) - Math.abs(vulTickCounterUID))) <= 4) {
                    vulTickCounterUID = c0f.getUID();
                    packetBuffer.add(c0f);
                    e.cancel();
                }else if (Math.abs((Math.abs((c0f.getUID())) - 25767)) <= 4) {
                    vulTickCounterUID = (c0f.getUID());
                }
            }
        }



        super.onPacket(e);
    }

    public void updateLagTime() {

    }
}
