package com.reflectmc.reflect.notification.impl.reflect;

import com.reflectmc.reflect.notification.Notification;
import com.reflectmc.reflect.utils.render.RenderUtil;
import com.reflectmc.reflect.utils.render.Translate;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.ScaledResolution;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public abstract class ReflectNotification extends Notification {
    @Setter
    @Getter
    private boolean disappear = false;
    private boolean delete = false;
    public ReflectNotification(){
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        yPosAnimation = new Translate(0,sr.getScaledHeight() + 30);
    }
    public void renderCanvas(int startX,int width,int startY,int height,double progress,Color progressBarColor){
        RenderUtil.drawFastRoundedRect(startX,startY,startX + width,startY + height,5, Color.white.getRGB());
        RenderUtil.drawRect(startX+5,startY + height - 5 - 2 ,startX+width -5,startY + height - 5,Color.lightGray.getRGB());
        RenderUtil.drawRect(startX+5,startY + height -5 -2 , startX + 5 + (width - 10) * progress,startY + height - 5,progressBarColor.getRGB());
    }
    @Override
    public boolean shouldDelete() {
        return disappear && delete;
    }
    public void delete(){
        delete = true;
    }
}
