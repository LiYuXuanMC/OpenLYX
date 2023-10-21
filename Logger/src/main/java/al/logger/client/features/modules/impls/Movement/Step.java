package al.logger.client.features.modules.impls.Movement;

import al.logger.client.event.EventType;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventStep;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;

public class Step extends Module {
    public ModeValue mode = new ModeValue("Mode",Mode.Vanilla,Mode.values());
    public DoubleValue height = new DoubleValue("Height",2D,0.5D,1D,0.1);
    public DoubleValue delay = new DoubleValue("Delay",1000D,0D,0D,1);

    private TimerUtils timer = new TimerUtils();
    public Step() {
        super("Step", Category.Movement);
        addValues(mode,height,delay);
    }
    public void onEnable() {
        this.timer.reset();
        super.onEnable();
    }
    public void onDisable(){
        mc.getThePlayer().setStepHeight(0.5F);
        super.onDisable();
    }
    @Listener
    public void onStep(EventStep event) {
        event.setHeight(0.5F);
        if (mode.getValue() == Mode.Vanilla){
            EntityPlayerSP thePlayer = mc.getThePlayer();
            if (event.getEventType() == EventType.Pre) {
                if (thePlayer.isOnGround() && timer.hasReached(delay.getValue().longValue())) {
                    timer.reset();
                    event.setHeight(this.height.getValue().floatValue());
                    return;
                }
            }
        }
    }
    public enum Mode{
        Vanilla
    }
}
