package com.reflectmc.reflect.utils.render;

import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.GlStateManager;

import static org.lwjgl.opengl.GL11.*;

public class GLUtil {


    public static void render(int mode, Runnable render) {
        glBegin(mode);
        render.run();
        glEnd();
    }
    public static void setup2DRendering(Runnable f) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDisable(GL_TEXTURE_2D);
        f.run();
        glEnable(GL_TEXTURE_2D);
        GlStateManager.disableBlend();
    }

    public static void rotate(float x, float y, float rotate, Runnable f) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0);
        GlStateManager.rotate(rotate, 0, 0, -1);
        GlStateManager.translate(-x, -y, 0);
        f.run();
        GlStateManager.popMatrix();
    }


}
