package al.logger.client.features.modules.impls.Movement.speeds.Vulcan;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventTick;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.value.impls.ModeValue;

public class Vulcan extends Module {
    private float ticks = 0;
    private double launchY = 0.0;

    ModeValue mode = new ModeValue("Mode" , Mode.LowHop , Mode.values());
    public Vulcan(){
        super("Vulcan" , Category.Movement);
        addValues(mode);
    }

    @Override
    public void onEnable() {
        ticks = 0;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        ticks = 0;
        super.onDisable();
    }

    @Listener
    public void onTick(EventTick e) {
        if (mode.getValue() == Mode.LowHop){
            ticks++;
            mc.getThePlayer().setJumpMovementFactor(0.0245f);
            if (mc.getThePlayer().isOnGround() && MovementUtils.isMoving()) {
                mc.getThePlayer().jump();
                ticks = 0;
                MovementUtils.strafe();
                if (MovementUtils.getSpeed() < 0.5f) {
                    MovementUtils.strafe(0.484f);
                }
                launchY = mc.getThePlayer().getPosY();
            }else if (mc.getThePlayer().getPosY() > launchY && ticks <= 1) {
                mc.getThePlayer().setPosition(mc.getThePlayer().getPosX(), launchY, mc.getThePlayer().getPosZ());
            }else if (ticks == 5) {
                mc.getThePlayer().setMotionY(-0.17);
            }
            if (MovementUtils.getSpeed() < 0.215) {
                MovementUtils.strafe(0.215f);
            }
        }
        super.onTick(e);
    }

    enum Mode{
        LowHop
    }
}
