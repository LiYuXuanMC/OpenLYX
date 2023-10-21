package al.logger.client.features.modules.impls.Player;


import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.NumberValue;
import al.logger.client.wrapper.LoggerMC.multiplayer.PlayerControllerMP;

public class SpeedMine extends Module {

    public DoubleValue damage = new DoubleValue("Damge" , 2 , 1 , 1 , 0.1);

    public SpeedMine() {
        super("SpeedMine", Category.Player);
        addValues(damage);
    }
    @Listener
    public void onUpdate(EventLivingUpdate event) {
        PlayerControllerMP playerController = mc.getPlayerController();
        playerController.setBlockHitDelay(0);
        if (playerController.getCurBlockDamageMP() >= 0.75F) playerController.setCurBlockDamageMP(damage.getValue().floatValue());
    }
}
