package com.reflectmc.reflect.notification.impl.reflect;

import com.reflectmc.reflect.event.events.render.EventRender2D;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.notification.Stopwatch;
import com.reflectmc.reflect.utils.ClientUtil;
import com.reflectmc.reflect.utils.render.Translate;
import com.reflectmc.reflect.utils.render.shapes.Shape;
import com.reflectmc.reflect.utils.render.shapes.ShapeCheck;
import com.reflectmc.reflect.utils.render.shapes.ShapeClose;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.ScaledResolution;

import java.awt.*;

public class ReflectModuleNotification extends ReflectNotification {
    private Module module;
    private boolean status;
    private long time;
    private Translate xPosAnimation;
    private Stopwatch stopwatch;
    private Shape icon;
    public ReflectModuleNotification(Module module,long time){
        this.module = module;
        this.status = module.isEnable();
        this.time = time;
        this.xPosAnimation = new Translate(0,0);
        this.stopwatch = new Stopwatch();
        this.icon = module.isEnable() ? new ShapeCheck(new Color(223,95,228)) : new ShapeClose(new Color(223,95,228));
    }
    @Override
    public void render(EventRender2D render2D, ScaledResolution sr, int startY) {
        int width = 50;
        int height = 30;
        if (isDisappear()){
            xPosAnimation.interpolate(width + 6,0,0.5);
        }
        int startX = sr.getScaledWidth() - width - 5 + Math.round(xPosAnimation.getX());
        double progress = Math.min((1.0 * (stopwatch.getElapsedTime()) / time),1D);
        renderCanvas(startX,width,startY,height,progress,status ? new Color(6348946) : new Color(0xFF2F2F));
        icon.draw(startY + 11 + 2 ,startX + 2 + 11);
        icon.draw(startY + 11 + 2 ,startX + 2 + 11);
        if (stopwatch.elapsed(time)){
            setDisappear(true);
        }
        if (xPosAnimation.getX() >= (width + 5)){
            delete();
        }
    }
}
