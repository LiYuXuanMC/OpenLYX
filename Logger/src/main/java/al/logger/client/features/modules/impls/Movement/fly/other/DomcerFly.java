package al.logger.client.features.modules.impls.Movement.fly.other;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventJump;
import al.logger.client.event.client.player.EventMove;
import al.logger.client.event.client.world.EventBlockBB;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.wrapper.LoggerMC.block.BlockAir;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;

public class DomcerFly extends Module {
    DoubleValue flyValue = new DoubleValue("Vertical" , 3 , 0.1 , 0.5 , 0.1);
    private int ticks;
    double launchY = 0.0F;

    public DomcerFly() {
        super("Domcer", "", Category.Movement);
        addValues(flyValue);
    }

    @Override
    public void onEnable() {
        launchY = mc.getThePlayer().getPosY();
        ticks =  0;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        mc.getTimer().setTimerSpeed(1);
        super.onDisable();
    }

    @Listener
    public void onMove(EventMove e){
        if (ticks % 10 == 0 && mc.getThePlayer().isOnGround()) {
            MovementUtils.strafe(1f);
            e.setY(0.42);
            ticks = 0;
            mc.getThePlayer().setMotionY(0.0);
            MovementUtils.setMotion(1.1485 + Math.random() / 50);
        } else {
            if (mc.getGameSettings().getKeyBindJump().isKeyDown() && ticks % 2 == 1) {
                e.setY(flyValue.getValue());
                MovementUtils.strafe(0.425f);
                launchY += flyValue.getValue();
                mc.getTimer().setTimerSpeed(0.95f);
                return;
            }
            mc.getTimer().setTimerSpeed(1f);
            MovementUtils.strafe(0.685f);
        }
        ticks++;
    }

    @Listener
    public void onJump(EventJump event){
        event.cancel();
    }

    @Listener
    public void onBlockBB(EventBlockBB e){
        if (BlockAir.isBlockAir(e.getBlock()) && e.getY() <= launchY){
            e.setBoundingBox(new AxisAlignedBB(e.getX(), e.getY(), e.getZ(), e.getX() + 1.0, launchY, e.getZ() + 1.0));
        }
    }
}
