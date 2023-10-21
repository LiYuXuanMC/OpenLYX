package al.logger.client.features.modules.impls.Player;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.rotation.RotationUtils;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityFireball;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02Action;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02PacketUseEntity;
import al.logger.client.wrapper.LoggerMC.network.client.C0APacketAnimation;

public class AntiFireBall extends Module{
    ModeValue swingmode = new ModeValue("Mode" , Mode.None , Mode.values());

    OptionValue rotation = new OptionValue("Rotation" , true);
    TimerUtils time = new TimerUtils();
    public AntiFireBall(){
        super("AntiFireBall" , Category.Player);
        addValues(swingmode , rotation);
    }

    @Listener
    public void onUpdate(EventUpdate e){
        for (Entity entity: mc.getTheWorld().getLoadedEntityList()){
            if (EntityFireball.isEntityFireball(entity) && mc.getThePlayer().getDistanceToEntity(entity) < 5.5 && time.hasTimeElapsed(500)){
                if (rotation.getValue()) {
                    RotationUtils.setTargetRotation(RotationUtils.getRotationsNonLivingEntity(entity));
                }
                mc.getThePlayer().getSendQueue().addToSendQueue(new C02PacketUseEntity(entity, C02Action.ATTACK));

                if (swingmode.getValue() == Mode.Normal){
                    mc.getThePlayer().swingItem();
                }

                if (swingmode.getValue() == Mode.Packet){
                    mc.getThePlayer().getSendQueue().addToSendQueue(new C0APacketAnimation());
                }

                time.reset();
            }
        }
    }


    enum Mode{
        Normal,
        Packet,
        None
    }

}
