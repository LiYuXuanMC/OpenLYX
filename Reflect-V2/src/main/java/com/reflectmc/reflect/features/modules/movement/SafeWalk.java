package com.reflectmc.reflect.features.modules.movement;

import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.player.EventLivingUpdate;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.KeyBinding;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.block.Blocks;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.BlockPos;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.world.WorldClient;

public class SafeWalk extends Module {
    public SafeWalk() {
        super("SafeWalk", Category.Movement);
    }
    @EventTarget
    public void onUpdate(EventLivingUpdate update){
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
