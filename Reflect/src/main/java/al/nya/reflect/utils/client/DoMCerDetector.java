package al.nya.reflect.utils.client;

/**
 * 王航你妈死了
 */
public class DoMCerDetector {
    public static boolean isDomcer(){
        try {
            Class.forName("com.domcer.uview.module.ModuleManager");
            Class.forName("customskinloader.Logger");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
