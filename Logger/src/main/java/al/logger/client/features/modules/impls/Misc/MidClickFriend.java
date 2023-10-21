package al.logger.client.features.modules.impls.Misc;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventLivingUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.FriendManager;
import al.logger.client.utils.misc.ColorUtil;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.lwjgl.Mouse;

public class MidClickFriend extends Module {
    private boolean wasDown = false;
    public MidClickFriend() {
        super("MidClickFriend", Category.Misc);
    }
    @Listener
    private void onUpdate(EventLivingUpdate update){
        if (!mc.getCurrentScreen().isNull())
            return;

        if (!wasDown && Mouse.isButtonDown(2)) {
            Entity entity = mc.getObjectMouseOver().getEntityHit();

            if (EntityPlayer.isEntityPlayer(entity)) {
                String playerName = ColorUtil.stripColor(entity.getName());
                FriendManager friendManager = Logger.getInstance().getFriendManager();
                if (!friendManager.isFriend(playerName)) {
                    friendManager.addFriend(playerName);
                    ChatUtils.message(String.format("§a§l$%s§c was added to your friends.", playerName));
                } else {
                    friendManager.removeFriend(playerName);
                    ChatUtils.message(String.format("§a§l$%s§c was removed from your friends.", playerName));
                }

            } else
                ChatUtils.error("§c§lError: §aYou need to select a player.");
        }
        wasDown = Mouse.isButtonDown(2);
    }
}
