package com.reflectmc.reflect.notification.impl.reflect;

import com.reflectmc.reflect.event.events.render.EventRender2D;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.notification.Notification;
import com.reflectmc.reflect.notification.ThemeRenderer;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.ScaledResolution;

import java.util.List;

public class ReflectNormalRenderer extends ThemeRenderer {
    @Override
    public void onRender(EventRender2D render2D, List<Notification> notifications) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int startY = sr.getScaledHeight();
        for (Notification notification : notifications) {
            startY -= 35;
            notification.yPosAnimation.interpolate(0,startY,1);
            notification.render(render2D,sr,Math.round(notification.yPosAnimation.getY()));
        }
    }

    @Override
    public Notification publishModule(Module module) {
        return new ReflectModuleNotification(module,2000);
    }
}
