package al.nya.reflect.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.imageio.ImageIO;

import al.nya.reflect.wrapper.wraps.wrapper.GameProfile;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.render.DynamicTexture;

public class SessionAyatar {
	
    private static BufferedImage avatar;
    private static DynamicTexture dynamicAvatar;
    
    public static DynamicTexture getDynamicAvatar() {
        return dynamicAvatar;
    }
    
    public static BufferedImage getAvatar() {
        if (avatar == null) setUpAvatar();
        return avatar;
    }
    
    public static void setUpAvatar() {
        try {
            avatar = ImageIO.read(new URL("https://visage.surgeplay.com/face/" + getUUID(Minecraft.getMinecraft().getSession().getProfile())));
            dynamicAvatar = new DynamicTexture(avatar);
        } catch (IOException e) {
            avatar = null;
            e.printStackTrace();
        }
    }
    
    public static UUID getUUID(GameProfile profile)
    {
        UUID uuid = profile.getID();

        if (uuid == null)
        {
            uuid = getOfflineUUID(profile.getName());
        }

        return uuid;
    }

    public static UUID getOfflineUUID(String username)
    {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(StandardCharsets.UTF_8));
    }
}
