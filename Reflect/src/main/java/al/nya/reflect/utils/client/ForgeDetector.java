package al.nya.reflect.utils.client;

/**
 * Detect mc be moded by Forge
 * Fucking Forge
 */
public class ForgeDetector {
    public static Class<?> getGuiIngameForge(){
        try {
            return Class.forName("net.minecraftforge.client.GuiIngameForge");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isModedGuiIngame(){
        try {
            Class.forName("net.minecraftforge.client.GuiIngameForge");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
