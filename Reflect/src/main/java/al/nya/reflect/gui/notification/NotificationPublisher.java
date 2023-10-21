/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package al.nya.reflect.gui.notification;

import al.nya.reflect.utils.render.AnimationUtils;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.utils.render.Translate;
import al.nya.reflect.utils.render.font.FontManager;
import al.nya.reflect.utils.render.font.reflect.FontRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public final class NotificationPublisher {

    public static final List<Notification> NOTIFICATIONS = new CopyOnWriteArrayList<Notification>();

    public static void publish(ScaledResolution sr) {
        if (NOTIFICATIONS.isEmpty()) {
            return;
        }
        int srScaledHeight = sr.getScaledHeight();
        int scaledWidth = sr.getScaledWidth();
        int y = srScaledHeight - 30;
        for (Notification notification : NOTIFICATIONS) {
            Translate translate = notification.getTranslate();
            int width = notification.getWidth();
            if (!notification.getTimer().elapsed(notification.getTime())) {
                notification.scissorBoxWidth = AnimationUtils.animate(width, notification.scissorBoxWidth, 0.1f);
                translate.interpolate(scaledWidth - width, y, 0.15);
            } else {
                notification.scissorBoxWidth = AnimationUtils.animate(0.0f, notification.scissorBoxWidth, 0.1f);
                if (notification.scissorBoxWidth < 1.0) {
                    NOTIFICATIONS.remove(notification);
                }
                y += 30;
            }
            double translateX = translate.getX();
            double translateY = translate.getY();
            GL11.glPushMatrix();
            GL11.glEnable(3089);
            RenderUtil.prepareScissorBox(Math.round(scaledWidth - notification.scissorBoxWidth), Math.round(translateY), scaledWidth, Math.round(translateY + 30.0f));
            RenderUtil.drawRect(translateX, translateY, scaledWidth, translateY + 30.0f, -1879048192);
            RenderUtil.drawRect(translateX, translateY + 30.0f - 2.0f, translateX + width * ((long) notification.getTime() - notification.getTimer().getElapsedTime()) / notification.getTime(), translateY + 30.0f, notification.getType().getColor());
            notification.getFontRenderer().drawStringWithShadow(notification.getTitle(), translateX + 4.0f, translateY + 4.0f, -1);
            notification.getFontRenderer().drawStringWithShadow(notification.getContent(), translateX + 4.0f, translateY + 17.0f, -1);
            GL11.glDisable(3089);
            GL11.glPopMatrix();
            y -= 30;
        }
    }

    public static void queue(String title, String content, NotificationType type) {
        NOTIFICATIONS.add(new Notification(title, content, type, 2500 , FontManager.arial20));
    }

    public static void queue(String title, String content, int time, NotificationType type) {
        NOTIFICATIONS.add(new Notification(title, content, type, time , FontManager.arial20));
    }

    public static void queue(String title, String content, NotificationType type, int time, FontRenderer fontRenderer) {
        NOTIFICATIONS.add(new Notification(title, content, type, time, fontRenderer));
    }
}
