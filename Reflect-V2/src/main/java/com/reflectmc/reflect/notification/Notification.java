package com.reflectmc.reflect.notification;

import com.reflectmc.reflect.event.events.render.EventRender2D;
import com.reflectmc.reflect.utils.render.Translate;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.ScaledResolution;

public abstract class Notification {
    public Translate yPosAnimation = new Translate(0,0);
    public Notification(){

    }
    public abstract boolean shouldDelete();
    public abstract void render(EventRender2D render2D, ScaledResolution sr, int startY);
}
