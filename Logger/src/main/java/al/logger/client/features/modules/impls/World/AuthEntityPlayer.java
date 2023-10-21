package al.logger.client.features.modules.impls.World;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.render.EventCape;
import al.logger.client.event.client.world.EventLoadWorld;
import al.logger.client.features.commands.commands.Auth;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ChatUtils;
import al.logger.client.value.bases.AuthUser;
import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.LoggerMC.network.client.C02.C02PacketUseEntity;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import jline.internal.Log;

import java.util.HashMap;

public class AuthEntityPlayer extends Module {

    public AuthEntityPlayer() {
        super("AuthEntityPlayer", Category.World);
        this.setEnabled(true);
        this.setHide(true);
    }

    @Listener
    public void onLoadWorld(EventLoadWorld eventLoadWorld) {
        if (Logger.getInstance().authUserManager != null) {
            Logger.getInstance().authUserManager.clear();
            Logger.getInstance().getResourceManager().getSkinCapes().clear();
        }
    }
    @Listener
    public void onCape(EventCape event) {
        if (!event.getPlayer().isNull()){
            if (Logger.getInstance().getAuthUserManager().getAuthUsers().containsKey(event.getPlayer().getName())){
                ResourceLocation cape = getCape(event.getPlayer().getName());
                event.setHasCape(true);
                event.setCapeLocation(cape);
            }
        }
    }
    public static ResourceLocation getCape(String entiyName) {
        if (Logger.getInstance().getAuthUserManager().getAuthUsers().containsKey(entiyName)) {
            AuthUser authUser = Logger.getInstance().getAuthUserManager().getAuthUser(entiyName);
            return Logger.getInstance().getResourceManager().getCape(authUser.username);
        }
        return new ResourceLocation((Object) null);
    }

    @Override
    public void onDisable() {
        if (Logger.getInstance().getLoggerUser().getPower() != 255){
            this.setEnabled(true);
        }
        super.onDisable();
//        try{
//            Logger.getInstance().getLoggerWS().uploadUsers();
//        }catch (Throwable e){
//            e.printStackTrace();
//        }
//        for (String str:Logger.getInstance().getAuthUserManager().getAuthUsers().keySet()){
//            ChatUtils.message(str);
//        }
    }
}
