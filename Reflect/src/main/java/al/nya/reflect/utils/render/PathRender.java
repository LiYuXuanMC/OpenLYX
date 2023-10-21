package al.nya.reflect.utils.render;

import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.Timer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderManager;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class PathRender {
    public static void renderPath(double[][] pos, AxisAlignedBB axisAlignedBB){
        GL11.glPushMatrix();
        drawWayLiner(
                Arrays.asList(pos),
                1.5F,155
        );
        GL11.glPopMatrix();
        if (axisAlignedBB != null){
            drawESP(pos,axisAlignedBB);
        }
    }
    public static void drawESP(double[][] pos,AxisAlignedBB axisAlignedBB){
        int rainbowOffset = 0;
        double width = axisAlignedBB.getMaxX() - axisAlignedBB.getMinX();
        double height = axisAlignedBB.getMaxY() - axisAlignedBB.getMinY();
        for (double[] pos3d : pos) {
            drawEntityESP(pos3d[0],pos3d[1],pos3d[2],width, height, 0, 1, 0f, 0.2f, 0, 0, 0.0f, 1f, 2f);
            rainbowOffset += 10;
        }
    }
    public static void drawEntityESP(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        RenderUtil.drawFilledBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        RenderUtil.drawSelectionBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    public static void drawWayLiner(List<double[]> poses, float w, int a) {
        int i = 0;
        Iterator<double[]> iterator = poses.iterator();
        RenderManager rm = Minecraft.getMinecraft().getRenderManager();
        double pX = rm.getRenderPosX();//RenderManager.renderPosX;
        double pY = rm.getRenderPosY();//RenderManager.renderPosY;
        double pZ = rm.getRenderPosZ();//RenderManager.renderPosZ;

        float al = ((float) 1 / 255) *a;

        GL11.glPushMatrix();

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        //GL11.glDisable(GL11.GL_LIGHTING);


        //GL11.glBlendFunc(770, 771);
        //GL11.glHint(3154, 4354);
        //GL11.glHint(3155, 4354);
        // GL11.glHint(3153, 4354);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        float speed = 5000f;
        float baseHue = System.currentTimeMillis() % (int) speed;
        while (baseHue > speed) {
            baseHue -= speed;
        }
        baseHue /= speed;



        GL11.glLineWidth(w);
        GL11.glBegin(GL11.GL_LINE_STRIP//11//ULOF8//FSGJ -- 6//4//
        );
        while(iterator.hasNext()) {
            double[] p = iterator.next();
            i++;
            //GL11.glColor4f(1, 2, 3, 155);

            float max = ((float) i + (float) (3 * 8)) / 90;
            float hue = max - baseHue;
            while (hue > 1) {
                hue -= 1;
            }
            final float r = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getRed();
            final float g = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getGreen();
            final float b = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getBlue();
            int color = Color.WHITE.getRGB();
            GL11.glColor4f(0,0,0,255);



            GL11.glVertex3d(p[0] - pX, p[1] - pY, p[2] - pZ);
            GL11.glColor4f(255, 255, 255,255);
        }
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        //GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);


        // GL11.glClearColor(0,0,0,0);
        GL11.glPopMatrix();

    }
}
