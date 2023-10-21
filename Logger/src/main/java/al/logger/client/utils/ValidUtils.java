package al.logger.client.utils;

import al.logger.client.Logger;
import al.logger.client.features.modules.impls.World.AntiBot;
import al.logger.client.features.modules.impls.World.Target;
import al.logger.client.features.modules.impls.World.Team;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.*;
import al.logger.client.wrapper.LoggerMC.network.NetworkPlayerInfo;

public class ValidUtils {
    public static boolean isValid(Entity entity){
        if (!EntityLivingBase.isEntityLivingBase(entity)) return false;
        if (entity.isNull()) return false;
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        if (
                thePlayer.isPlayerSleeping()
                || thePlayer.isDead()
                || thePlayer.getHealth() <= 0
                || entity.isDead()
                || EntityArmorStand.isEntityArmorStand(entity)
                || isBot(entity)
                || EntityPlayerSP.isEntityPlayerSP(entity)
        ) {
            return false;
        }
        if (!isTarget(entity))return false;
        if (isBot(entity)) return false;
        if (isTeam(entity)) return false;
        return true;
    }
    public static boolean isTeam(Entity entity){
        //FriendManager.friendsList.contains(entity.getName());
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
    public static boolean isBot(Entity entity){
        if (!Logger.getInstance().moduleManager.getModule(AntiBot.class).isEnable()) return false;

        if (!(EntityPlayer.isEntityPlayer(entity))) {
            return false;
        }
        if (Minecraft.getMinecraft().getCurrentServerData() == null) {
            return false;
        }
        return entity.getDisplayName().getFormattedText().toLowerCase().contains("npc") || entity.getDisplayName().getFormattedText().startsWith("\u0e22\u0e07") || !isOnTab(entity);
    }

    public static boolean isTarget(Entity entity){
        if (!Logger.getInstance().moduleManager.getModule(Target.class).isEnable()) return false;
        if ((EntityPlayer.isEntityPlayer(entity)) && Target.players.getValue()) {
            if (entity.isInvisible() && Target.invs.getValue()) {
                return true;
            }else{
                return !entity.isInvisible();
            }
        }
        if ((EntityAnimal.isEntityAnimal(entity)) && Target.animals.getValue()) {
            return true;
        }
        if ((EntityMob.isEntityMob(entity)) && Target.mobs.getValue()) {
            return true;
        }
        return false;
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
