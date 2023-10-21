package al.logger.client.features.modules.impls.Movement.speeds.other;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventJump;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.utils.player.PlayerUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.potion.Potion;

public class LegitSpeed extends Module {
    private DoubleValue Boost = new DoubleValue("Boost", 0.2, 0.0, 0.15, 0.01);
    private DoubleValue Boost1 = new DoubleValue("Boost1",0.2, 0.0, 0.15, 0.01);
    private DoubleValue Boost2 = new DoubleValue("Boost2",0.2, 0.0, 0.15, 0.01);
    public OptionValue fastfall = new OptionValue("FastFall" , false);
    public LegitSpeed() {
        super("Legit", Category.Movement);
        addValues(Boost,Boost1,Boost2,fastfall);
    }
    @Listener
    public void onTick(EventTick e){
        if(fastfall.getValue()) {
            if(mc.getThePlayer().getFallDistance() > 1.5) {
                mc.getThePlayer().setMotionY(mc.getThePlayer().getMotionX() * 1.075);
            }
        }
    }

    @Listener
    public void onJump(EventJump e){
        if (!mc.getThePlayer().isNull() || MovementUtils.isMoving()) {
            EntityPlayerSP player = mc.getThePlayer();
            float boost;
            if (player.isPotionActive(Potion.moveSpeed) && player.getActivePotionEffect(Potion.moveSpeed).getAmplifier() != 1) {
                boost = Boost.getValue().floatValue();
                player.setMotionX(player.getMotionX() * (double)(1.0f + (float)PlayerUtils.getBaseMoveSpeed() * boost));
                player.setMotionZ(player.getMotionZ() * (double)(1.0f + (float)PlayerUtils.getBaseMoveSpeed() * boost));
            }
            if (player.isPotionActive(Potion.moveSpeed) && player.getActivePotionEffect(Potion.moveSpeed).getAmplifier() != 2) {
                boost = Boost1.getValue().floatValue();
                player.setMotionX(player.getMotionX() * (double)(1.0f + (float)PlayerUtils.getBaseMoveSpeed() * boost));
                player.setMotionZ(player.getMotionZ() * (double)(1.0f + (float)PlayerUtils.getBaseMoveSpeed() * boost));
            }
            if (player.isPotionActive(Potion.moveSpeed) && player.getActivePotionEffect(Potion.moveSpeed).getAmplifier() != 3) {
                boost = Boost2.getValue().floatValue();
                player.setMotionX(player.getMotionX() * (double)(1.0f + (float) PlayerUtils.getBaseMoveSpeed() * boost));
                player.setMotionZ(player.getMotionZ() * (double)(1.0f + (float)PlayerUtils.getBaseMoveSpeed() * boost));
            }
        }
    }


}
