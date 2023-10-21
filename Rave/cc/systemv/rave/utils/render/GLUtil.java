package cc.systemv.rave.utils.render;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class GLUtil {

    public static void render(int mode, Runnable runnable) {
        GL11.glBegin(mode);
        runnable.run();
        GL11.glEnd();
    }

    public static void setupRender2D(Runnable runnable) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        runnable.run();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GlStateManager.disableBlend();
    }

    public static void onRenderRotate(float x, float y, float rotate, Runnable runnable) {
        //堆栈计数1
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0);
        GlStateManager.rotate(rotate,0, 0, -1);
        GlStateManager.translate(-x, -y, 0);
        runnable.run();
        //结束堆栈1
        GlStateManager.popMatrix();
    }

}
