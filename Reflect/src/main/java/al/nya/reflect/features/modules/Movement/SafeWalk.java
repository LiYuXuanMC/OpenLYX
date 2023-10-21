package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.wrapper.wraps.wrapper.KeyBinding;
import al.nya.reflect.wrapper.wraps.wrapper.block.Blocks;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;

import java.util.Objects;

public class SafeWalk extends Module {
    public SafeWalk() {
        super("SafeWalk",ModuleType.Movement);
    }
    @EventTarget
    public void onUpdate(EventUpdate update){
        EntityPlayerSP player = mc.getThePlayer();
        WorldClient theWorld = mc.getTheWorld();
        if (!(mc.getGameSettings().getKeyBindJump().isKeyDown())) {
            BlockPos pos = new BlockPos(player.getPosX(), player.getPosY() - 1.0, player.getPosZ());
            KeyBinding sneak = mc.getGameSettings().getKeyBindSneak();
            //theWorld.getBlockState(pos).getBlock() == Blocks.getAir()
            if (theWorld.getBlockState(pos).getBlock().equals(Blocks.air)) {
                sneak.setPressed(true);
            } else {
                sneak.setPressed(false);
            }
        }
    }
    @Override
    public void onDisable(){
        mc.getGameSettings().getKeyBindSneak().setPressed(false);
    }
}
