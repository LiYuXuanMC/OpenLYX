package com.reflectmc.reflect.notification;

import com.reflectmc.reflect.event.events.render.EventRender2D;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.modules.visual.HUD;
import com.reflectmc.reflect.notification.impl.reflect.ReflectNormalRenderer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class NotificationManager {
    private static List<Notification> notifications = new ArrayList<>();
    @Getter
    @Setter
    private static Theme theme = Theme.ReflectNormal;
    public static void onRender2D(EventRender2D render2D){
        theme.getThemeRenderer().onRender(render2D,notifications);
        //Remove popped notification(s)
        notifications.removeIf(Notification::shouldDelete);
    }
    public static void changeTheme(Theme t){
        theme = t;
        notifications.clear();
    }
    public static void publishModule(Module module){
        publish(theme.getThemeRenderer().publishModule(module));
    }
    private static void publish(Notification notification){
        if (HUD.renderNotification.getValue()){
            notifications.add(notification);
        }
    }
    public enum Theme {
        ReflectNormal(new ReflectNormalRenderer())
        ;
        @Getter
        private ThemeRenderer themeRenderer;
        Theme(ThemeRenderer themeRenderer){
            this.themeRenderer = themeRenderer;
        }
    }
}
