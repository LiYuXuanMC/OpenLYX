package al.logger.client.features.modules.impls.World;

import al.logger.client.ExceptionHandler;
import al.logger.client.Logger;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.components.Notification;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import by.radioegor146.nativeobfuscator.Native;
import est.builder.annotations.Clear;
import jline.internal.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Clear(when = "Release")
@Native
public class EACDisabler extends Module {
    public EACDisabler(){
        super("EACDisabler" , Category.World);
    }
    @Override
    public void onEnable() {
        if (EnvironmentDetector.hasAntiCheat(Environment.EnsembleAntiCheat)){
            try {
                Class<?> ct = Class.forName("EAC.coremod.Connection");
                Field c = ct.getDeclaredField("c");
                c.setAccessible(true);
                c.setDouble(ct , 420254);
                c.setAccessible(false);

                Class<?> clazz = Class.forName("EAC.coremod.Running");
                Field g = clazz.getDeclaredField("g");
                g.setAccessible(true);
                g.setDouble(clazz , 206610);
                g.setAccessible(false);

                Logger.getInstance().getNotificationManager().addNotification(new Notification("EAC has been disabled" , Notification.NotificationType.Success));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




}