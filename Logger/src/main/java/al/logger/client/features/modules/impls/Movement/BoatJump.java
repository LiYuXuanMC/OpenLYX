package al.logger.client.features.modules.impls.Movement;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.network.PacketUtil;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.block.Material;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityBoat;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02Action;
import al.logger.client.wrapper.LoggerMC.network.client.C07.C07Action;
import al.logger.client.wrapper.LoggerMC.network.client.C0B.C0BAction;
import al.logger.client.wrapper.LoggerMC.network.client.C0CPacketInput;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.Vec3;
import est.builder.annotations.Clear;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;


public class BoatJump extends Module {
    private ModeValue mode = new ModeValue("Mode" , Mode.Matrix , Mode.values());
    private DoubleValue hBoostValue = new DoubleValue("HBoost", 5 , 0 ,2 ,0.1);
    private DoubleValue vBoostValue = new DoubleValue("VBoost",10 , 0 ,2 ,0.1);
    private DoubleValue matrixTimerStartValue  = new DoubleValue("MtrixTimerStart",1.2 , 0.1 ,0.3 ,0.1);
    private DoubleValue matrixTimerAirValue  = new DoubleValue("MatrixTimerAir ",1.2 , 0.1 ,0.5 ,0.1);
    private DoubleValue delay = new DoubleValue("Delay",500 , 100 ,200 ,1.0);
    private int jumpState = 1;
    private TimerUtils timer = new TimerUtils();
    private TimerUtils hitTimer = new TimerUtils();
    private boolean lastRide = false;
    private boolean hasStopped = false;

    OptionValue c0c = new OptionValue("C0C" , false);



    public BoatJump() {
        super("BoatJump", "BoatJump" , Category.Movement);
        addValues(mode,hBoostValue,vBoostValue,matrixTimerStartValue,matrixTimerAirValue,delay , c0c);
        c0c.addCallBack(()-> mode.getValue() == Mode.Test);
    }


    @Override
    public void onEnable() {
        jumpState = 1;
        lastRide = false;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        hasStopped = false;
        mc.getTimer().setTimerSpeed(1f);
        mc.getThePlayer().setSpeedInAir(0.02f);
        super.onDisable();
    }

    @Listener
    public void onUpdate(EventTick e){
       if (mode.getValue() == Mode.Matrix){
           if (mc.getThePlayer().isOnGround() && !mc.getThePlayer().isRiding()){
               hasStopped = false;
               mc.getTimer().setTimerSpeed(1f);
               mc.getThePlayer().setSpeedInAir(0.02f);
           }
           if (hasStopped) {
               mc.getTimer().setTimerSpeed(matrixTimerAirValue.getValue().floatValue());
           } else {
               mc.getTimer().setTimerSpeed(1f);
           }

           if (mc.getThePlayer().isRiding() && jumpState == 1){
               if (!lastRide){
                   timer.reset();
               }

               if (timer.hasTimePassed(delay.getValue().longValue())){
                   jumpState = 2;

                   mc.getTimer().setTimerSpeed(matrixTimerStartValue.getValue().floatValue());
                   PacketUtil.sendPacket(new C0CPacketInput(mc.getThePlayer().getMoveStrafing() , mc.getThePlayer().getMoveForward(),false ,true));
               }
           }else if (jumpState == 2 && !mc.getThePlayer().isRiding()){
               double radiansYaw = mc.getThePlayer().getRotationYaw()  * Math.PI / 180;

               hasStopped = true;
               mc.getTimer().setTimerSpeed(matrixTimerAirValue.getValue().floatValue());
               mc.getThePlayer().setMotionX(hBoostValue.getValue() * -Math.sin(radiansYaw));
               mc.getThePlayer().setMotionZ(hBoostValue.getValue() * Math.cos(radiansYaw));
               mc.getThePlayer().setMotionY(vBoostValue.getValue());
               jumpState = 1;

               timer.reset();
               hitTimer.reset();
           }
       }

       if (mode.getValue() == Mode.Test){
           if (mc.getThePlayer().isOnGround() && !mc.getThePlayer().isRiding()){
               hasStopped = false;
               mc.getTimer().setTimerSpeed(1f);
               mc.getThePlayer().setSpeedInAir(0.02f);
           }
           if (hasStopped) {
               mc.getTimer().setTimerSpeed(matrixTimerAirValue.getValue().floatValue());
           } else {
               mc.getTimer().setTimerSpeed(1f);
           }

           if (mc.getThePlayer().isRiding() && jumpState == 1){
               if (!lastRide){
                   timer.reset();
               }

               if (timer.hasTimePassed(delay.getValue().longValue())){
                   jumpState = 2;

                   mc.getTimer().setTimerSpeed(matrixTimerStartValue.getValue().floatValue());
                   if (c0c.getValue()){
                       PacketUtil.sendPacket(new C0CPacketInput(mc.getThePlayer().getMoveStrafing() , mc.getThePlayer().getMoveForward(),false ,true));
                   }
               }
           }else if (jumpState == 2 && !mc.getThePlayer().isRiding()){
               double radiansYaw = mc.getThePlayer().getRotationYaw()  * Math.PI / 180;

               hasStopped = true;
               mc.getTimer().setTimerSpeed(matrixTimerAirValue.getValue().floatValue());
               mc.getThePlayer().setMotionX(hBoostValue.getValue() * -Math.sin(radiansYaw));
               mc.getThePlayer().setMotionZ(hBoostValue.getValue() * Math.cos(radiansYaw));
               mc.getThePlayer().setMotionY(vBoostValue.getValue());
               jumpState = 1;

               timer.reset();
               hitTimer.reset();
           }
       }
    }
    public static Block getBlock(BlockPos block) {
        return Minecraft.getMinecraft().getTheWorld().getBlockState(block).getBlock();
    }

    @Listener
    public void onPacket(EventPacket e){

    }


    enum Mode {
        Matrix,
        Test
    }
}
