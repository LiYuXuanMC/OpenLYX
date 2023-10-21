package al.logger.client.features.modules.impls.World;

import al.logger.client.Logger;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;

public class Team extends Module {
    public Team() {
        super("Team", Category.World);
    }

    public static boolean isTeam(Entity entity) {
        if (Logger.getInstance().moduleManager.getModule(Team.class).isEnable()) {
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
