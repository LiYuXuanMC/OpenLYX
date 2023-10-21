package al.logger.client.features.modules.impls.Player;


import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.network.NetHandlerPlayClient;
import al.logger.client.wrapper.LoggerMC.potion.Potion;

public class Regen extends Module {
    private ModeValue mode = new ModeValue("Mode", Mode.Vanilla, Mode.values());
    private DoubleValue healthValue = new DoubleValue("Health", 1D, 0.01D, 1D,0.01D);
    private DoubleValue foodValue = new DoubleValue("Food", 20, 0, 18, 1D);
    private DoubleValue speedValue = new DoubleValue("Speed",100,0,100,1D);
    private OptionValue noAirValue = new OptionValue("NoAir",true);
    private OptionValue potionEffectValue = new OptionValue("PotionEffect", false);
    public Regen() {
        super("Regen", Category.Player);
        addValues(mode,healthValue,foodValue,speedValue,noAirValue,potionEffectValue);
    }
    @Listener
    public void onUpdate(EventLivingUpdate update) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if ((!noAirValue.getValue() || thePlayer.isOnGround()) && !thePlayer.getCapabilities().isCreativeMode() &&
                thePlayer.getFoodStats().getFoodLevel() > foodValue.getValue() && thePlayer.isEntityAlive() && (thePlayer.getHealth() / thePlayer.getMaxHealth()) < healthValue.getValue()) {
            if (potionEffectValue.getValue() && !thePlayer.isPotionActive(Potion.regeneration))
                return;
            if (mode.getValue() == Mode.Vanilla) {
                int i = 0;
                NetHandlerPlayClient netHandler = mc.getNetHandler();
                boolean onGround = thePlayer.isOnGround();
                while (i != speedValue.getValue()) {
                    netHandler.addToSendQueue(new C03PacketPlayer(onGround));
                    i++;
                }
            }
        }
    }

    public enum Mode {
        Vanilla
    }
}
