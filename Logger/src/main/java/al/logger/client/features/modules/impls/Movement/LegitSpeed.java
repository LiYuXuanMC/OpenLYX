package al.logger.client.features.modules.impls.Movement;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventJump;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.event.client.player.EventMove;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.potion.Potion;


public class LegitSpeed extends Module {

    public DoubleValue Boost = new DoubleValue("Boost", 0.2, 0.0, 0.17, 0.01);
    public DoubleValue Boost1 = new DoubleValue("Boost", 0.2, 0.0, 0.17, 0.01);
    public DoubleValue Boost2 = new DoubleValue("Boost", 0.2, 0.0, 0.17, 0.01);
    public OptionValue fastfall = new OptionValue("FastFall" , false);

    public LegitSpeed(){
        super("LegitSpeed" , Category.Movement);
        addValues(Boost,Boost1,Boost2,fastfall);
    }

    @Listener
    public void onLivingUpdate(EventLivingUpdate e){
        if(fastfall.getValue()) {
            if(mc.getThePlayer().getFallDistance() > 1.5) {
                mc.getThePlayer().setMotionY(mc.getThePlayer().getMotionX() * 1.075);
            }
        }
    }

    @Listener
    public void onJump(EventJump e){
        if (this.mc.getThePlayer() != null || MovementUtils.isMoving()) {
            float boost;
            if (mc.getThePlayer().isPotionActive(Potion.moveSpeed) && this.mc.getThePlayer().getActivePotionEffect(Potion.moveSpeed).getAmplifier() != 1) {
                boost = ((Number)Boost.getValue()).floatValue();
                mc.getThePlayer().setMotionX(mc.getThePlayer().getMotionX() * (double)(1.0f + (float)this.BaseSpeed() * boost));
                mc.getThePlayer().setMotionZ(mc.getThePlayer().getMotionX() * (double)(1.0f + (float)this.BaseSpeed() * boost));
            }
            if (mc.getThePlayer().isPotionActive(Potion.moveSpeed) && this.mc.getThePlayer().getActivePotionEffect(Potion.moveSpeed).getAmplifier() != 2) {
                boost = ((Number)Boost1.getValue()).floatValue();
                mc.getThePlayer().setMotionX(mc.getThePlayer().getMotionX() * (double)(1.0f + (float)this.BaseSpeed() * boost));
                mc.getThePlayer().setMotionZ(mc.getThePlayer().getMotionX() * (double)(1.0f + (float)this.BaseSpeed() * boost));
            }
            if (mc.getThePlayer().isPotionActive(Potion.moveSpeed) && this.mc.getThePlayer().getActivePotionEffect(Potion.moveSpeed).getAmplifier() != 3) {
                boost = ((Number)Boost2.getValue()).floatValue();
                mc.getThePlayer().setMotionX(mc.getThePlayer().getMotionX() * (double)(1.0f + (float)this.BaseSpeed() * boost));
                mc.getThePlayer().setMotionZ(mc.getThePlayer().getMotionX() * (double)(1.0f + (float)this.BaseSpeed() * boost));
            }
        }
    }

    public int BaseSpeed() {
        if (this.mc.getThePlayer().isPotionActive(Potion.moveSpeed)) {
            return this.mc.getThePlayer().getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1;
        }
        return 0;
    }

}
