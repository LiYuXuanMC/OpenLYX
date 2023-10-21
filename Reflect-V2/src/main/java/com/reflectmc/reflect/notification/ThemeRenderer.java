package com.reflectmc.reflect.notification;

import com.reflectmc.reflect.event.events.render.EventRender2D;
import com.reflectmc.reflect.features.modules.Module;

import java.util.List;

public abstract class ThemeRenderer {
    public abstract void onRender(EventRender2D render2D, List<Notification> notifications);
    public abstract Notification publishModule(Module module);
}
