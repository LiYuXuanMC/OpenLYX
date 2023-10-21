package al.nya.reflect.features.modules.World;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPushBlock;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.MathHelper;
import al.nya.reflect.utils.timer.TickTimer;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class Phase extends Module {
    private ModeValue mode = new ModeValue("Mode", Mode.Vanilla, Mode.values());
    private TickTimer timer = new TickTimer();

    public Phase() {
        super("Phase", ModuleType.World);
        addValues(mode);
    }

    public void onDisable() {
        timer.reset();
        if (mode.getValue() == Mode.HitBox) {
            mc.getThePlayer().setWidth(0.6F);
        }
    }

    @EventTarget
    public void onUpdate(EventUpdate update) {
        timer.update();
        if (mode.getValue() == Mode.HitBox) {
            mc.getThePlayer().setWidth(0.6F + 2F);
        }
        if (mode.getValue() == Mode.Vanilla) {
            EntityPlayerSP thePlayer = mc.getThePlayer();
            thePlayer.setNoClip(true);
            thePlayer.setMotionY(0D);
            thePlayer.setOnGround(true);
            double yaw = Math.toRadians(thePlayer.getRotationYaw());
            double x = -Math.sin(yaw) * 0.04;
            double z = Math.cos(yaw) * 0.04;
            thePlayer.setPosition(thePlayer.getPosX() + (x * 10), thePlayer.getPosY(), thePlayer.getPosZ() + (z * 10));
            timer.reset();
        }
    }

    @EventTarget
    public void onPushBlock(EventPushBlock pushBlock) {
        pushBlock.setCancel(true);
    }

    public void onEnable() {
        timer.reset();
        if (mode.getValue() == Mode.HitBox) {
            mc.getThePlayer().setWidth(0.6F + 2F);
        }
    }

    public enum Mode {
        HitBox,
        Vanilla,
        Matrix
    }
}
