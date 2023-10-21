package al.logger.client.features.modules.impls.Movement;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.player.EventJump;
import al.logger.client.event.client.world.EventLoadWorld;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.ModuleCarrier;
import al.logger.client.features.modules.impls.Movement.speeds.Vulcan.Vulcan;
import al.logger.client.features.modules.impls.Movement.speeds.WatchDog.WatchDog;
import al.logger.client.features.modules.impls.Movement.speeds.matrix.Matrix;
import al.logger.client.features.modules.impls.Movement.speeds.other.LegitSpeed;
import al.logger.client.features.modules.impls.Movement.speeds.other.AutoJump;
import al.logger.client.features.modules.impls.Movement.speeds.other.Edit;
import al.logger.client.features.modules.impls.Movement.speeds.other.Test;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.network.server.S08.S08PacketPlayerPosLook;
import est.builder.annotations.Clear;

public class Speed extends ModuleCarrier {

    private OptionValue lagbackCheck = new OptionValue("LagbackCheck", true);
    public DoubleValue timerSpeed = new DoubleValue("TimerSpeed", 1.5D, 0.1D, 1.0D, 0.01);
    public OptionValue ignoreJump = new OptionValue("IgnoreJump", true);


    public Speed(){
        super("Speed", Category.Movement);
        addSubModule(new LegitSpeed());
        addSubModule(new Edit());
        addSubModule(new WatchDog());
        addSubModule(new AutoJump());
        addSubModule(new Vulcan());
        addSubModule(new Test());
        if (Logger.getInstance().getLoggerUser().getPower() == 10 || Logger.getInstance().getLoggerUser().getPower() == 255) {
            registerBeta();
        }
        addValues(lagbackCheck,timerSpeed,ignoreJump);
    }
    @Clear(when = "Release")
    public void registerBeta(){
        addSubModule(new Matrix());
    }
    @Listener
    public void onLagback(EventPacket packet){
        if (S08PacketPlayerPosLook.isS08PacketPlayerPosLook(packet.getPacket()) && lagbackCheck.getValue()){
            setDisable();
        }
    }
    @Listener
    public void onJump(EventJump e){
        if (ignoreJump.getValue() && mc.getGameSettings().getKeyBindJump().isKeyDown()){
            mc.getGameSettings().getKeyBindJump().setPressed(false);
        }
    }
    public boolean canJump(){
        if (ignoreJump.getValue() && mc.getGameSettings().getKeyBindJump().isKeyDown()){
            return false;
        }
        return true;
    }

    @Listener
    public void onWorld(EventLoadWorld e){
        this.setDisable();
    }
}
