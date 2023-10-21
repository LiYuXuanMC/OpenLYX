package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;

import java.awt.*;

import static org.lwjgl.util.glu.GLU.GLU_FILL;
import static org.lwjgl.util.glu.GLU.GLU_SILHOUETTE;

public class FutaESP extends Module {
    float cache = 0, cache2 = 0;

    public FutaESP() {
        super("FutaESP", ModuleType.Visual);
    }

    @EventTarget
    public void onRender3D(EventRender3D e) {
        EntityPlayerSP thePlayer = this.mc.getThePlayer();
        /*
        double gx = 0.2;
        nesp(thePlayer, e.getPartialTicks(), 0.1+gx,thePlayer.getEyeHeight()+0.6+gx);
        nesp(thePlayer, e.getPartialTicks(), 0.15+gx,thePlayer.getEyeHeight()+0.5+gx);
        nesp(thePlayer, e.getPartialTicks(), 0.2+gx,thePlayer.getEyeHeight()+0.4+gx);
        nesp(thePlayer, e.getPartialTicks(), 0.25+gx,thePlayer.getEyeHeight()+0.3+gx);
        nesp(thePlayer, e.getPartialTicks(), 0.3+gx,thePlayer.getEyeHeight()+0.2+gx);
        nesp(thePlayer, e.getPartialTicks(), 0.35+gx,thePlayer.getEyeHeight()+0.1+gx);
        nesp(thePlayer, e.getPartialTicks(), 0.4+gx,thePlayer.getEyeHeight()+gx);

        float offset=0;
        for(int i = 0;i<14;i++) {
            nesp(thePlayer, e.getPartialTicks(), 0.4, offset+=i*0.1);
        }
        */

        //System.out.println(thePlayer.rotationYawHead);


        //   if(cache !=thePlayer.rotationYaw||cache2!=thePlayer.rotationYawHead){
        //    System.out.println("|");
        // System.out.println(thePlayer.rotationYaw);

        //  System.out.println(MathHelper.sin(thePlayer.renderYawOffset));

        //  System.out.println(thePlayer.renderYawOffset);
        //  System.out.println(thePlayer.rotationYawHead);
        // }

        //double x =Math.sin(Math.toRadians(thePlayer.rotationYaw)) * Math.cos(Math.toRadians(thePlayer.rotationPitch));
        // double z=Math.cos(Math.toRadians(thePlayer.rotationYaw)) * Math.cos(Math.toRadians(thePlayer.rotationPitch));


        for (Entity en : mc.getTheWorld().getLoadedEntityList()) {
            if (!(EntityPlayer.isEntityPlayer(en))) {
                return;
            }
            EntityPlayer ep = new EntityPlayer(en.getWrapObject());

            double x = -Math.sin(Math.toRadians(ep.getRenderYawOffset()));//* Math.cos(Math.toRadians(thePlayer.rotationPitch));
            double z = Math.cos(Math.toRadians(ep.getRenderYawOffset()));//* Math.cos(Math.toRadians(thePlayer.rotationPitch));
            double rx = -Math.sin(Math.toRadians(ep.getRotationYawHead())) * Math.cos(Math.toRadians(ep.getRotationPitch()));
            double ry = Math.sin(Math.toRadians(ep.getRotationPitch()));//* Math.cos(Math.toRadians(thePlayer.rotationPitch));
            double rz = Math.cos(Math.toRadians(ep.getRotationYawHead())) * Math.cos(Math.toRadians(ep.getRotationPitch()));
            BYT(e);

            double off = 0.7;
            GL11.glPushMatrix();
            drawHAT(ep, Reflect.ColorStyle.getRGB(), e);
            double h = ep.getHealth();
            GL11.glRotated(180, x, 1, z);
            for (int i = 0; i < 16; i++) {
                nesp(ep, e.getPartialTicks(), 0.1, x * off, i * 0.05, z * off);
            }
            drawESPR(ep, Reflect.ColorStyle.getRGB(), e, x * off, -1.4, z * off);
            double offf = 1.2;
            int im = 16;
            GL11.glPushMatrix();
            GL11.glRotated(8, x, 2, z);
            for (int i = 0; i < im; i++) {
                nesp(ep, e.getPartialTicks(), (im - i) * 0.008, x * offf, 0.1 + i * 0.008, z * offf);

            }
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glRotated(-8, x, 2, z);
            for (int i = 0; i < im; i++) {
                nesp(ep, e.getPartialTicks(), (im - i) * 0.008, x * offf, 0.1 + i * 0.008, z * offf);

            }

            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }


        if (true) {
            return;
        }


        double x = -Math.sin(Math.toRadians(thePlayer.getRenderYawOffset()));//* Math.cos(Math.toRadians(thePlayer.rotationPitch));
        double z = Math.cos(Math.toRadians(thePlayer.getRenderYawOffset()));//* Math.cos(Math.toRadians(thePlayer.rotationPitch));

        double rx = -Math.sin(Math.toRadians(thePlayer.getRotationYawHead())) * Math.cos(Math.toRadians(thePlayer.getRotationPitch()));

        double ry = Math.sin(Math.toRadians(thePlayer.getRotationPitch()));//* Math.cos(Math.toRadians(thePlayer.rotationPitch));

        double rz = Math.cos(Math.toRadians(thePlayer.getRotationYawHead()) * Math.cos(Math.toRadians(thePlayer.getRotationPitch())));


        //  double x =-Math.sin(Math.toRadians(thePlayer.rotationYaw)) ;//* Math.cos(Math.toRadians(thePlayer.rotationPitch));
        //double z=Math.cos(Math.toRadians(thePlayer.rotationYaw)) ;//* Math.cos(Math.toRadians(thePlayer.rotationPitch));


        //cache = thePlayer.rotationYaw;
        //cache2=thePlayer.rotationYawHead;


        BYT(e);

        double off = 0.7;
        //   GL11.glPushMatrix();
        //  GL11.glRotated(180,x*0.8,1.5,z*0.8);
        //    BYT(e);
        //GL11.glPopMatrix();

        GL11.glPushMatrix();
        drawHAT(thePlayer, Reflect.ColorStyle.getRGB(), e);
        ///   GL11.glPushMatrix();
        //  GL11.glRotated(180,thePlayer.posX,thePlayer.posY,thePlayer.posZ);//*(float)thePlayer.posZ
        ///    FontLoaders.default16.drawString("123123123",(float)rz,(float)thePlayer.posY,-1);
        //    GL11.glPopMatrix();
        double h = thePlayer.getHealth();
        GL11.glRotated(180, x, 1, z);
        // nesp(thePlayer, e.getPartialTicks(), 0.6,x,1,z);
        // nesp(thePlayer, e.getPartialTicks(), 0.6,x*off,0.1,z*off);
        //BYT(e);
        for (int i = 0; i < 16; i++) {
            nesp(thePlayer, e.getPartialTicks(), 0.1, x * off, i * 0.05, z * off);
        }


        // GL11.glRotated(180,x* 0.6,1,z* Random.nextFloat(0.5f,0.8f));
        // drawESP(thePlayer ,-1,e);


        // GL11.glRotated(180,x*0.5,1,z* 0.4);
        // drawESP(thePlayer ,-1,e);
        // GL11.glRotated(180,x*0.4,1,z* 0.5);


        drawESPR(thePlayer, Reflect.ColorStyle.getRGB(), e, x * off, -1.4, z * off);

        // drawESPR(thePlayer , Reflect.ColorStyle.getRGB(),e,x*off*-1,-2.4,z*off*-1);

        //  drawESPR(thePlayer , Reflect.ColorStyle.getRGB(),e,x*off*0.2,-1.8,z*off*0.2);
        // GL11.glRotated(90,x,1,z);

        double offf = 1.2;
        int im = 16;

        GL11.glPushMatrix();
        GL11.glRotated(8, x, 2, z);
        //  drawESPRR(thePlayer , Reflect.ColorStyle.getRGB(),e,x*1.2,-2.1,z*1.2);
        // GL11.glTranslated(-x, -2.1, -z);

        for (int i = 0; i < im; i++) {
            nesp(thePlayer, e.getPartialTicks(), (im - i) * 0.008, x * offf, 0.1 + i * 0.008, z * offf);

        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        //GL11.glTranslated(-x, -2.1, -z);
        GL11.glRotated(-8, x, 2, z);
        // double offf =1.2;
        //int im = 16;
        for (int i = 0; i < im; i++) {
            nesp(thePlayer, e.getPartialTicks(), (im - i) * 0.008, x * offf, 0.1 + i * 0.008, z * offf);

        }

        //drawESPRR(thePlayer , Reflect.ColorStyle.getRGB(),e,x*1.2,-2.1,z*1.2);
        //drawESPR(thePlayer , Reflect.ColorStyle.getRGB(),e,x*off,-2.1,z*off);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        //  nesp(thePlayer, e.getPartialTicks(), 0.01,0.6+1.2+0.5 );
    }

    public void niuzi1(EventRender3D e, double x, double y, double z, double off) {
        EntityPlayerSP thePlayer = this.mc.getThePlayer();

        nesp(thePlayer, e.getPartialTicks(), 0.1, x * off, 0.2, z * off);
        nesp(thePlayer, e.getPartialTicks(), 0.1, x * off, 0.3, z * off);
        nesp(thePlayer, e.getPartialTicks(), 0.1, x * off, 0.4, z * off);
        nesp(thePlayer, e.getPartialTicks(), 0.1, x * off, 0.5, z * off);
        nesp(thePlayer, e.getPartialTicks(), 0.1, x * off, 0.6, z * off);
        nesp(thePlayer, e.getPartialTicks(), 0.1, x * off, 0.7, z * off);
        nesp(thePlayer, e.getPartialTicks(), 0.1, x * off, 0.8, z * off);
        nesp(thePlayer, e.getPartialTicks(), 0.1, x * off, 0.9, z * off);

    }

    public void drawESP(EntityLivingBase entity, int color, EventRender3D e) {
        RenderManager rendermanager = mc.getRenderManager();
        double x = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * (double) e.getPartialTicks()
                - rendermanager.getRenderPosX();
        double y = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * (double) e.getPartialTicks()
                - rendermanager.getRenderPosY();
        double z = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * (double) e.getPartialTicks()
                - rendermanager.getRenderPosZ();
        float radius = 0.2f;
        int side = 6;
        GL11.glPushMatrix();
        GL11.glTranslated((double) x, (double) y + 2, (double) z);
        GL11.glRotatef((float) (-entity.getWidth()), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), new Color(color).getAlpha()).getRGB());
        RenderUtil.enableSmoothLine(1.0f);
        Cylinder c = new Cylinder();
        GL11.glRotatef((float) -90.0f, (float) 1.0f, (float) 0.0f, (float) 0.0f);

        c.setDrawStyle(100012);
        c.draw(0, radius, 0.3f, side, 1);
        glColor(color);
        c.setDrawStyle(100012);
        GL11.glTranslated(0, 0, 0.3);
        c.draw(radius, 0, 0.3f, side, 1);

        GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 0.0f, (float) 1.0f);

        RenderUtil.disableSmoothLine();
        GL11.glPopMatrix();
    }

    public void drawHAT(EntityLivingBase entity, int color, EventRender3D e) {
        RenderManager rendermanager = mc.getRenderManager();
        double x = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * (double) e.getPartialTicks()
                - rendermanager.getRenderPosX();
        double y = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * (double) e.getPartialTicks()
                - rendermanager.getRenderPosY() - 0.5;
        double z = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * (double) e.getPartialTicks()
                - rendermanager.getRenderPosZ();

        float radius = 1f;
        int side = 16;
        GL11.glPushMatrix();
        GL11.glTranslated((double) x, (double) y + 2, (double) z);
        GL11.glRotatef((float) (-entity.getWidth()), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), new Color(color).getAlpha()).getRGB());
        RenderUtil.enableSmoothLine(1.0f);

        Cylinder c = new Cylinder();
        Sphere s = new Sphere();
        GL11.glRotatef((float) -90.0f, (float) 1.0f, (float) 0.0f, (float) 0.0f);

        //c.setDrawStyle(100012);
        //c.draw(0, radius, 0.3f, side, 1);
        //glColor(color);
        //c.setOrientation(GLU_FLAT);
        GL11.glPushMatrix();

        //glColor(color);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), 20).getRGB());

        c.setDrawStyle(GLU_FILL);
        GL11.glTranslated(0, 0, 0.3);
        c.draw(radius, 0.5f, 0.4f, side, 1);


        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), 100).getRGB());


        c.setDrawStyle(GLU_SILHOUETTE);
        //   GL11.glTranslated(0, 0, 0.3);
        c.draw(radius, 0.5f, 0.4f, side, 1);
        // c.draw(radius+0.01f, 0.51f, 0.41f, side, 1);

        GL11.glTranslated(0, 0, 0.4);
        c.draw(0.5f, 0, 0.1f, side, 1);
        // c.draw(0.51f, 0, 0.11f, side, 1);
        c.draw(0.5f, 0, -0.4f, side, 1);

        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), 20).getRGB());
        c.setDrawStyle(GLU_FILL);
        c.draw(0.5f, 0, 0.1f, side, 1);

        GL11.glPopMatrix();

        //glColor(color);

        c.setDrawStyle(100010);
        GL11.glTranslated(0, 0, 0.3);
        c.draw(radius, 0.5f, 0.4f, side, 1);
        GL11.glTranslated(0, 0, 0.4);
        c.draw(0.5f, 0, 0.1f, side, 1);
        //  c.draw(0.5f, 0, -0.4f, side, 1);


        //GL11.glPushMatrix();
        //c.setDrawStyle(GLU_SILHOUETTE );
        //GL11.glTranslated(0, 0, 0.3);
        // c.draw(radius+0.05f, 0.55f, 0.45f, side, 1);
        //GL11.glPopMatrix();
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), 20).getRGB());
        s.setDrawStyle(GLU_SILHOUETTE);
        GL11.glTranslated(0, 0, -0.5);
        s.draw(0.7f, side, 1);

        GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 0.0f, (float) 1.0f);

        RenderUtil.disableSmoothLine();
        GL11.glPopMatrix();
    }

    public void drawESP(EntityLivingBase entity, int color, EventRender3D e, double x, double y, double z) {

        float radius = 0.2f;
        int side = 6;
        GL11.glPushMatrix();
        GL11.glTranslated((double) x, (double) y + 2, (double) z);
        GL11.glRotatef((float) (-entity.getWidth()), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), new Color(color).getAlpha()).getRGB());
        RenderUtil.enableSmoothLine(1.0f);
        Cylinder c = new Cylinder();
        GL11.glRotatef((float) -90.0f, (float) 1.0f, (float) 0.0f, (float) 0.0f);

        c.setDrawStyle(100012);
        c.draw(0, radius, 0.3f, side, 1);
        glColor(color);
        c.setDrawStyle(100012);
        GL11.glTranslated(0, 0, 0.3);
        c.draw(radius, 0, 0.3f, side, 1);

        GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 0.0f, (float) 1.0f);

        RenderUtil.disableSmoothLine();
        GL11.glPopMatrix();
    }

    public void drawESPR(EntityLivingBase entity, int color, EventRender3D e, double x, double y, double z) {

        float radius = 0.2f;
        int side = 32;
        GL11.glPushMatrix();
        GL11.glTranslated((double) x, (double) y + 2, (double) z);
        GL11.glRotatef((float) (-entity.getWidth()), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), new Color(color).getAlpha()).getRGB());
        RenderUtil.enableSmoothLine(1.0f);
        Cylinder c = new Cylinder();
        GL11.glRotatef((float) -90.0f, (float) 1.0f, (float) 0.0f, (float) 0.0f);

        //c.setDrawStyle(100011);
        //  c.setDrawStyle(100013);
        //c.draw(0, radius, 0.1f, side, 1);
        glColor(color);
        // c.setDrawStyle(100011);
        c.setDrawStyle(GLU_SILHOUETTE);
        GL11.glTranslated(0, 0, 0.2);
        c.draw(radius, 0.05f, 0.2f, side, 1);

        GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 0.0f, (float) 1.0f);

        RenderUtil.disableSmoothLine();
        GL11.glPopMatrix();
    }

    public void drawHAT(EntityLivingBase entity, int color, EventRender3D e, double x, double y, double z) {

        float radius = 0.2f;
        int side = 32;
        GL11.glPushMatrix();
        GL11.glTranslated((double) x, (double) y + 2, (double) z);
        GL11.glRotatef((float) (-entity.getWidth()), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), new Color(color).getAlpha()).getRGB());
        RenderUtil.enableSmoothLine(1.0f);
        Cylinder c = new Cylinder();
        GL11.glRotatef((float) -90.0f, (float) 1.0f, (float) 0.0f, (float) 0.0f);

        //c.setDrawStyle(100011);
        //  c.setDrawStyle(100013);
        //c.draw(0, radius, 0.1f, side, 1);
        glColor(color);
        // c.setDrawStyle(100011);


        c.setDrawStyle(GLU_SILHOUETTE);
        GL11.glTranslated(0, 0, 0.2);
        c.draw(radius, 0, 0.4f, side, 1);

        GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 0.0f, (float) 1.0f);

        RenderUtil.disableSmoothLine();
        GL11.glPopMatrix();
    }

    public void drawESPRR(EntityLivingBase entity, int color, EventRender3D e, double x, double y, double z) {

        float radius = 0.2f;
        int side = 6;
        GL11.glPushMatrix();
        GL11.glTranslated((double) x, (double) y + 2, (double) z);
        GL11.glRotatef((float) (-entity.getWidth()), (float) 0.0f, (float) 1.0f, (float) 0.0f);
        glColor(new Color(Math.max(new Color(color).getRed() - 75, 0), Math.max(new Color(color).getGreen() - 75, 0),
                Math.max(new Color(color).getBlue() - 75, 0), new Color(color).getAlpha()).getRGB());
        RenderUtil.enableSmoothLine(1.0f);
        Cylinder c = new Cylinder();
        GL11.glRotatef((float) -90.0f, (float) 1.0f, (float) 0.0f, (float) 0.0f);

        //c.setDrawStyle(100011);
        //  c.setDrawStyle(100013);
        //c.draw(0, radius, 0.1f, side, 1);
        glColor(color);
        // c.setDrawStyle(100011);
        c.setDrawStyle(GLU_SILHOUETTE);
        GL11.glTranslated(0, 0, 0.3);
        c.draw(radius, 0, 0.2f, side, 1);

        GL11.glRotatef((float) 90.0f, (float) 0.0f, (float) 0.0f, (float) 1.0f);

        RenderUtil.disableSmoothLine();
        GL11.glPopMatrix();
    }

    public static void glColor(int hex) {
        float alpha = (float) (hex >> 24 & 255) / 255.0f;
        float red = (float) (hex >> 16 & 255) / 255.0f;
        float green = (float) (hex >> 8 & 255) / 255.0f;
        float blue = (float) (hex & 255) / 255.0f;
        GL11.glColor4f((float) red, (float) green, (float) blue, (float) (alpha == 0.0f ? 1.0f : alpha));
    }


    public void BYT(EventRender3D e) {
        float offset = 0;
        EntityPlayerSP thePlayer = this.mc.getThePlayer();

        nesp(thePlayer, e.getPartialTicks(), 0.6, 0.2);
        nesp(thePlayer, e.getPartialTicks(), 0.55, 0.3);
        nesp(thePlayer, e.getPartialTicks(), 0.5, 0.4);
        nesp(thePlayer, e.getPartialTicks(), 0.45, 0.5);
        for (int i = 0; i < 12; i++) {
            nesp(thePlayer, e.getPartialTicks(), 0.4, 0.6 + i * 0.1);
        }
        nesp(thePlayer, e.getPartialTicks(), 0.35, 0.6 + 1.2);
        nesp(thePlayer, e.getPartialTicks(), 0.3, 0.6 + 1.2 + 0.1);
        nesp(thePlayer, e.getPartialTicks(), 0.25, 0.6 + 1.2 + 0.15);
        nesp(thePlayer, e.getPartialTicks(), 0.2, 0.6 + 1.2 + 0.2);
        nesp(thePlayer, e.getPartialTicks(), 0.15, 0.6 + 1.2 + 0.25);
        nesp(thePlayer, e.getPartialTicks(), 0.1, 0.6 + 1.2 + 0.3);
        nesp(thePlayer, e.getPartialTicks(), 0.1, 0.6 + 1.2 + 0.35);
        nesp(thePlayer, e.getPartialTicks(), 0.1, 0.6 + 1.2 + 0.4);
        nesp(thePlayer, e.getPartialTicks(), 0.05, 0.6 + 1.2 + 0.45);
    }


    public void nesp(Entity entity, float partialTicks, double rad, double x, double y, double z) {
        float points = 90F;
        GlStateManager.enableDepth();
        for (double il = 0; il < 4.9E-324; il += 4.9E-324) {
            GL11.glPushMatrix();
            GL11.glDisable(3553);
            GL11.glEnable(2848);
            GL11.glEnable(2881);
            GL11.glEnable(2832);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glHint(3154, 4354);
            GL11.glHint(3155, 4354);
            GL11.glHint(3153, 4354);
            GL11.glDisable(2929);
            GL11.glLineWidth(1f);
            GL11.glBegin(3);
            //  final double x = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * partialTicks - mc.getRenderManager().getViewerPosX();
            //final double y = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * partialTicks - mc.getRenderManager().getViewerPosY()+xx;
            //final double z = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * partialTicks - mc.getRenderManager().getViewerPosZ();
            final double pix2 = 6.283185307179586;
            float speed = 5000f;
            float baseHue = System.currentTimeMillis() % (int) speed;
            while (baseHue > speed) {
                baseHue -= speed;
            }
            baseHue /= speed;
            for (int i = 0; i <= 90; ++i) {
                float max = ((float) i + (float) (il * 8)) / points;
                float hue = max + baseHue;
                while (hue > 1) {
                    hue -= 1;
                }
                final float r = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getRed();
                final float g = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getGreen();
                final float b = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getBlue();
                int color = Color.WHITE.getRGB();
                GL11.glColor3f(r, g, b);
                GL11.glVertex3d(x + rad * Math.cos(i * pix2 / points), y + il, z + rad * Math.sin(i * pix2 / points));
            }
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glDisable(2848);
            GL11.glDisable(2881);
            GL11.glEnable(2832);
            GL11.glEnable(3553);
            GL11.glPopMatrix();
            GlStateManager.color(255, 255, 255);
        }


    }

    public void nesp(Entity entity, float partialTicks, double rad, double xx) {
        float points = 90F;
        GlStateManager.enableDepth();
        for (double il = 0; il < 4.9E-324; il += 4.9E-324) {
            GL11.glPushMatrix();
            GL11.glDisable(3553);
            GL11.glEnable(2848);
            GL11.glEnable(2881);
            GL11.glEnable(2832);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glHint(3154, 4354);
            GL11.glHint(3155, 4354);
            GL11.glHint(3153, 4354);
            GL11.glDisable(2929);
            GL11.glLineWidth(1f);
            GL11.glBegin(3);
            final double x = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * partialTicks - mc.getRenderManager().getViewerPosX();
            final double y = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * partialTicks - mc.getRenderManager().getViewerPosY() + xx;
            final double z = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * partialTicks - mc.getRenderManager().getViewerPosZ();
            final double pix2 = 6.283185307179586;
            float speed = 5000f;
            float baseHue = System.currentTimeMillis() % (int) speed;
            while (baseHue > speed) {
                baseHue -= speed;
            }
            baseHue /= speed;
            for (int i = 0; i <= 90; ++i) {
                float max = ((float) i + (float) (il * 8)) / points;
                float hue = max + baseHue;
                while (hue > 1) {
                    hue -= 1;
                }
                final float r = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getRed();
                final float g = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getGreen();
                final float b = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getBlue();
                int color = Color.WHITE.getRGB();
                GL11.glColor3f(r, g, b);
                GL11.glVertex3d(x + rad * Math.cos(i * pix2 / points), y + il, z + rad * Math.sin(i * pix2 / points));
            }
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glDisable(2848);
            GL11.glDisable(2881);
            GL11.glEnable(2832);
            GL11.glEnable(3553);
            GL11.glPopMatrix();
            GlStateManager.color(255, 255, 255);
        }


    }

    public void nesp(Entity entity, float partialTicks, double rad) {
        float points = 90F;
        GlStateManager.enableDepth();
        for (double il = 0; il < 4.9E-324; il += 4.9E-324) {
            GL11.glPushMatrix();
            GL11.glDisable(3553);
            GL11.glEnable(2848);
            GL11.glEnable(2881);
            GL11.glEnable(2832);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glHint(3154, 4354);
            GL11.glHint(3155, 4354);
            GL11.glHint(3153, 4354);
            GL11.glDisable(2929);
            GL11.glLineWidth(1f);
            GL11.glBegin(3);
            final double x = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * partialTicks - mc.getRenderManager().getViewerPosX();
            final double y = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * partialTicks - mc.getRenderManager().getViewerPosY();
            final double z = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * partialTicks - mc.getRenderManager().getViewerPosZ();
            final double pix2 = 6.283185307179586;
            float speed = 5000f;
            float baseHue = System.currentTimeMillis() % (int) speed;
            while (baseHue > speed) {
                baseHue -= speed;
            }
            baseHue /= speed;
            for (int i = 0; i <= 90; ++i) {
                float max = ((float) i + (float) (il * 8)) / points;
                float hue = max + baseHue;
                while (hue > 1) {
                    hue -= 1;
                }
                final float r = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getRed();
                final float g = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getGreen();
                final float b = 0.003921569f * new Color(Color.HSBtoRGB(hue, 0.75F, 1F)).getBlue();
                int color = Color.WHITE.getRGB();
                GL11.glColor3f(r, g, b);
                GL11.glVertex3d(x + rad * Math.cos(i * pix2 / points), y + il, z + rad * Math.sin(i * pix2 / points));
            }
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glDisable(2848);
            GL11.glDisable(2881);
            GL11.glEnable(2832);
            GL11.glEnable(3553);
            GL11.glPopMatrix();
            GlStateManager.color(255, 255, 255);
        }
    }
}
