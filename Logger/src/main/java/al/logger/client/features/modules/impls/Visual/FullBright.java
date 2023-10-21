package al.logger.client.features.modules.impls.Visual;


import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.wrapper.LoggerMC.potion.Potion;
import al.logger.client.wrapper.LoggerMC.potion.PotionEffect;

public class FullBright extends Module {
    private ModeValue mode = new ModeValue("Mode", Mode.Gamma, Mode.values());
    public FullBright() {
        super("FullBright", Category.Visual);
        addValues(mode);
    }
    @Override
    public void onEnable(){
        if (mode.getValue() == Mode.Gamma)
            mc.getGameSettings().setGammaSetting(1000F);
    }
    @Listener
    public void onUpdate(EventLivingUpdate update){
        if (mode.getValue() == Mode.Potion){
            mc.getThePlayer().addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 100, 1));
        }
    }
    @Override
    public void onDisable(){
        mc.getGameSettings().setGammaSetting(0F);
    }
    public enum Mode {
        Gamma,
        Potion
    }
}
