package al.nya.reflect.features.modules.Combat;

import al.nya.reflect.config.FriendManager;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;

public class Teams extends Module {

	public Teams() {
		super("Teams", "队伍白名单", ModuleType.Combat);
	}
	public static boolean isTeam(Entity entity) {
        FriendManager.friendsList.contains(entity.getName());
		if (ModuleManager.getModule(Teams.class).isEnable()) {
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