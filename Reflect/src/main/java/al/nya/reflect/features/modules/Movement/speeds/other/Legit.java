package al.nya.reflect.features.modules.Movement.speeds.other;

import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.utils.entity.PlayerUtil;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class Legit extends SpeedModules {
    public Legit() {
        super(Speeds.Legit);
    }
    @Override
    public void onUpdate(EventUpdate Update){
        if (PlayerUtil.isInWater()) return;
        if (MovementUtils.isMoving()) {
            EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
            if (thePlayer.isOnGround()) thePlayer.jump();
        }
    }
}
