package com.reflectmc.reflect.features.modules.combat;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;

public class Teams extends Module {

	public Teams() {
		super("Teams", Category.Combat);
	}
	public static boolean isTeam(Entity entity) {
        //FriendManager.friendsList.contains(entity.getName());
		if (Reflect.getINSTANCE().getModuleManager().getModule(Teams.class).isEnable()) {
			EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
			if (thePlayer.isNull()) return false;
			if (thePlayer.getDisplayName().getUnformattedText().startsWith("\247")) {
				if (thePlayer.getDisplayName().getUnformattedText().length() <= 2
						|| entity.getDisplayName().getUnformattedText().length() <= 2) {
					return false;
				}
				return thePlayer.getDisplayName().getUnformattedText().substring(0, 2)
						.equals(entity.getDisplayName().getUnformattedText().substring(0, 2));
			}
		}
		return false;
	}
}