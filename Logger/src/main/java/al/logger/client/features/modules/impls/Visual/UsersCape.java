package al.logger.client.features.modules.impls.Visual;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventCape;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.bases.AuthUser;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import com.mojang.authlib.GameProfile;

public class UsersCape extends Module {
    private GameProfile gameProfile;
    public UsersCape() {
        super("UsersCape", Category.Visual);
        this.setHide(true);
    }

}
