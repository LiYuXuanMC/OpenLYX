package cc.systemv.rave.utils.client;

import net.minecraft.util.ResourceLocation;
import net.optifine.reflect.ReflectorForge;

import java.io.InputStream;

public class ResourceUtil {
    public static InputStream getResourceStream(ResourceLocation location)
    {
        String s = "/assets/" + location.getResourceDomain() + "/" + location.getResourcePath();
        InputStream inputstream = ReflectorForge.getOptiFineResourceStream(s);
        return inputstream != null ? inputstream : ResourceUtil.class.getResourceAsStream(s);
    }
}
