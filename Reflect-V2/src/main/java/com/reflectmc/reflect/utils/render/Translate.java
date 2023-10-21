package com.reflectmc.reflect.utils.render;

import com.reflectmc.reflect.utils.MathUtil;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;

public final class Translate {
    private float x;
    private float y;

    public Translate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void interpolate(float targetX, float targetY, double smoothing) {
        //this.x = AnimationUtils.getAnimationState(this.x, targetX,
        //        (float) (Math.max(10, (Math.abs(this.x - targetX)) * 35) * smoothing));
        //this.y = AnimationUtils.getAnimationState(this.y, targetY,
        //        (float) (Math.max(10, (Math.abs(this.y - targetY)) * 35) * smoothing));
        int FPS = Minecraft.getDebugFPS();
        this.x = RenderUtil.smooth(x, targetX, MathUtil.processFPS(FPS, 1000, 0.008F), MathUtil.processFPS(FPS, 1000, 0.004F));
        this.y = RenderUtil.smooth(y, targetY, MathUtil.processFPS(FPS, 1000, 0.008F), MathUtil.processFPS(FPS, 1000, 0.004F));
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }
}

