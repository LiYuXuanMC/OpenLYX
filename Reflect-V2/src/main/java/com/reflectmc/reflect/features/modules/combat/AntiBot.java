package com.reflectmc.reflect.features.modules.combat;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.ModeValue;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayer;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.NetworkPlayerInfo;

public class AntiBot extends Module {
    public static ModeValue mode = new ModeValue("Mode",Mode.Hypixel,Mode.values());

    public AntiBot() {
        super("AntiBot",Category.Combat);
        addValue(mode);
    }
    public static boolean isEntityBot(Entity entity) {
        if (!Reflect.getINSTANCE().getModuleManager().getModule(AntiBot.class).isEnable()) return false;
        Minecraft mc = Minecraft.getMinecraft();
        if (!(EntityPlayer.isEntityPlayer(entity))) {
            return false;
        }
        if (mc.getCurrentServerData() == null) {
            return false;
        }
        if (mode.getValue() == Mode.Hypixel && entity.getTicksExisted() <= 10 * 20)
            return false;


        return entity.getDisplayName().getFormattedText().toLowerCase().contains("npc") || entity.getDisplayName().getFormattedText().startsWith("\u0e22\u0e07") || !isOnTab(entity);
    }

    enum Mode {
        Basic,
        Hypixel
    }
    public static boolean isOnTab(Entity entity) {
        Minecraft mc = Minecraft.getMinecraft();
        for (NetworkPlayerInfo npi : mc.getNetHandler().getPlayerInfoMap()) {
            if (npi.getGameProfile().isNull() || !npi.getGameProfile().getName().contains(entity.getName())) continue;
            return true;
        }
        return false;
    }
}
