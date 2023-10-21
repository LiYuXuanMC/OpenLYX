package com.reflectmc.reflect.utils.player;

import com.reflectmc.reflect.wrapper.wrappers.minecraft.GameSettings;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;

public class PlayerUtil {
    public static boolean moving(EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.getMoveForward() != 0.0f || entityPlayerSP.getMoveStrafing() != 0.0f;
    }
    public static boolean isMovementInput() {
        GameSettings gameSettings = Minecraft.getMinecraft().getGameSettings();
        return gameSettings.getKeyBindForward().isKeyDown() || gameSettings.getKeyBindLeft().isKeyDown() || gameSettings.getKeyBindRight().isKeyDown() || gameSettings.getKeyBindBack().isKeyDown();
    }
}
