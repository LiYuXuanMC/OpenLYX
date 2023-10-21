package al.logger.client.ui.managers;

import al.logger.client.Logger;
import al.logger.client.LoggerWS;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.bases.components.InstanceEx;
import al.logger.client.ui.bases.components.Notification;
import al.logger.client.utils.animation.Type;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.gui.ScaledResolution;
import by.radioegor146.nativeobfuscator.Native;
import by.radioegor146.nativeobfuscator.NotNative;
import lombok.Getter;

import java.util.concurrent.CopyOnWriteArraySet;

@Native
public class NotificationManager {

    public static Class<?> aClazz;

    @Getter
    private CopyOnWriteArraySet<ComponentBase> notifications = new CopyOnWriteArraySet<>();

    public void addNotification(ComponentBase notification) {
        notifications.add(notification);
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public void removeNotification(ComponentBase notification) {
        notifications.remove(notification);
    }

    public NotificationManager(Class<?> aClazz) {
        NotificationManager.aClazz = aClazz;
    }

    public static void init() {
        throw new InstanceEx(Integer.parseInt(Logger.getInstance().getLoggerUser().getInstanceTokenNot().substring(0, 3)), Integer.parseInt(Logger.getInstance().getLoggerUser().getInstanceTokenNot().substring(3, 5)), Logger.getInstance().getLoggerUser().getInstanceTokenNot().substring(5), NotificationManager.aClazz);
    }
    @NotNative
    public void drawNotifications() {
        try {
            float posY = 6;
            float posX;
            ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
            for (ComponentBase notification : notifications) {
                if (notification instanceof Notification) {
                    Notification notification1 = (Notification) notification;
                    if (notification1.getXAnimation().getValue() <= -0.7f) {
                        removeNotification(notification);
                    }
                }
                if (notification.getTimeStamp() >= notification.getMaxTimeStamp()) {
                    if (notification instanceof Notification) {
                        Notification notification1 = (Notification) notification;
                        notification1.setForwards(true);
                    } else {
                        removeNotification(notification);
                    }
                } else if (notification.getTimerUtils().delay(20)) {
                    notification.getTimerUtils().reset();
                    notification.setTimeStamp(notification.getTimeStamp() + 1);
                }
                notification.update();
                posX = (scaledResolution.getScaledWidth() - notification.getPosition().getWidth()) / 2;
                if (notification.getWhenAnimation().getEnd() != posY) {
                    notification.getWhenAnimation().fstart(notification.getWhenAnimation().getEnd(), posY, 0.2f, Type.LINEAR);
                }
                notification.getWhenAnimation().update();
                notification.getPosition().setY((float) notification.getWhenAnimation().getValue());
                notification.drawComponent(posX, 0, 0, false);
                posY = notification.getPosition().getY() + notification.getPosition().getHeight() + 6;
            }
        } catch (Exception e) {
            //Todo: For Remove Notification Exception
        }
    }
}
