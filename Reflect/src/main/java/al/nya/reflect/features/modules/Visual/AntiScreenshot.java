package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.features.modules.Combat.KillAura;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.Movement.Flight;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AntiScreenshot extends Module {
    public static boolean isGetingSS = false;
    private static BufferedImage lastImage;

    public AntiScreenshot() {
        super("AntiScreenshot", ModuleType.Visual);
    }

    public static List<Module> disabledModules = new ArrayList<>();

    @Override
    public void onDisable() {
        setEnable(true);
        ClientUtil.printChat(ClientUtil.Level.INFO, "Can't disable AntiScreenshot due to screenshot-based anticheat detected");
    }

    public static void onScreenShotAntiCheatPre() {
        NotificationPublisher.queue("AntiScreenshot", "ScreenShot after 1s", 1000, NotificationType.ERROR);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isGetingSS = true;
        try {
            if (Minecraft.getDebugFPS() <= 10)
                Thread.sleep(1000);
            else
                Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static BufferedImage onScreenShotAntiCheatPost(BufferedImage bufferedImage){
        if (lastImage != null) {
            if (ModuleManager.getModule(KillAura.class).isEnable() || ModuleManager.getModule(Flight.class).isEnable()) {
                NotificationPublisher.queue("AntiScreenshot", "ScreenShot finish!", 5000, NotificationType.SUCCESS);
                ClientUtil.printChat(ClientUtil.Level.INFO, "Send safe screenshot due to dangerous modules enable");
                isGetingSS = false;
                return lastImage;
            }
        }
        lastImage = bufferedImage;
        disabledModules.clear();
        NotificationPublisher.queue("AntiScreenshot", "ScreenShot finish!", 5000, NotificationType.SUCCESS);
        isGetingSS = false;
        return bufferedImage;
    }
}
