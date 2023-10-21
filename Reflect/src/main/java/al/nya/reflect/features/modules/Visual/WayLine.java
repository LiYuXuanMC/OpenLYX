package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WayLine extends Module {
    private List<double[]> poses;

    public WayLine() {
        super("WayLine",ModuleType.Visual);
        poses = new CopyOnWriteArrayList<>();
    }
    private double lastCoord = Double.MAX_VALUE;
    @EventTarget
    public void onPre(EventUpdate e){
        if(!isAfk(mc.getThePlayer())) {
            RenderManager rm = mc.getRenderManager();
            poses.add(new double[]{rm.getRenderPosX(), rm.getRenderPosY(), rm.getRenderPosZ()});
            while (poses.size() > 500){
                poses.remove(0);
            }
        }
    }
    @EventTarget
    public void onWorldLoad(EventWorldLoad worldLoad){
        poses.clear();
    }

    public boolean isAfk(EntityLivingBase e) {
        double coord = e.getPosX() + e.getPosY() + e.getPosZ();
        if (coord == lastCoord) {
            return true;
        } else {
            lastCoord = coord;
            return false;
        }
    }
    @EventTarget
    public void on3d(EventRender3D e) {
        GL11.glPushMatrix();
        drawWayLiner(
                poses,
                //   ClientSetting.themecolor.getRed(),
                //   ClientSetting.themecolor.getGreen(),
                //   ClientSetting.themecolor.getBlue(),
                //   155,
                1.5F,155
        );
        GL11.glPopMatrix();
    }
    public static void drawWayLiner(List<double[]> poses,  float w,int a) {
        int i = 0;
        Iterator<double[]> iterator = poses.iterator();
        RenderManager rm = Minecraft.getMinecraft().getRenderManager();
        double pX = rm.getRenderPosX();
        double pY = rm.getRenderPosY();
        double pZ = rm.getRenderPosZ();
        float al = ((float) 1 / 255) *a;
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        float speed = 5000f;
        float baseHue = System.currentTimeMillis() % (int) speed;
        while (baseHue > speed) {
            baseHue -= speed;
        }
        baseHue /= speed;
        GL11.glLineWidth(w);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        while(iterator.hasNext()) {
            double[] p = iterator.next();
            i++;
            float max = ((float) i + (float) (3 * 8)) / 90;
            float hue = max - baseHue;
            while (hue > 1) {
                hue -= 1;
            }
            final float r = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getRed();
            final float g = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getGreen();
            final float b = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getBlue();
            int color = Color.WHITE.getRGB();
            GL11.glColor4f(r, g, b,al);
            GL11.glVertex3d(p[0] - pX, p[1] - pY, p[2] - pZ);
            GL11.glColor4f(255, 255, 255,255);
        }
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }
}
