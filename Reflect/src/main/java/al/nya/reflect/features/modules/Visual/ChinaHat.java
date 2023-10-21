package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.MathHelper;
import al.nya.reflect.utils.math.MathUtil;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.wrapper.wraps.wrapper.GameSettings;
import al.nya.reflect.wrapper.wraps.wrapper.Timer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;
import al.nya.reflect.wrapper.wraps.wrapper.render.OpenGlHelper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;

import static org.lwjgl.opengl.GL11.*;

public class ChinaHat extends Module {
    public ChinaHat() {
        super("ChinaHat", "中国帽", ModuleType.Visual);
    }

    @EventTarget
    public void onRender3D(EventRender3D e) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        WorldClient world = mc.getTheWorld();
        if (thePlayer.isNull() || world == null || thePlayer.isInvisible() || thePlayer.isDead()) return;
        GameSettings settings = mc.getGameSettings();
        if (settings.getThirdPersonView() == 0) return;

        Timer timer = mc.getTimer();

        double posX = thePlayer.getLastTickPosX() + (thePlayer.getPosX() - thePlayer.getLastTickPosX()) * timer.getRenderPartialTicks() - mc.getRenderManager().getRenderPosX(),
                posY = thePlayer.getLastTickPosY() + (thePlayer.getPosY() - thePlayer.getLastTickPosY()) * timer.getRenderPartialTicks() - mc.getRenderManager().getRenderPosY(),
                posZ = thePlayer.getLastTickPosZ() + (thePlayer.getPosZ() - thePlayer.getLastTickPosZ()) * timer.getRenderPartialTicks() - mc.getRenderManager().getRenderPosZ();

        AxisAlignedBB axisalignedbb = thePlayer.getEntityBoundingBox();
        double height = axisalignedbb.getMaxY() - axisalignedbb.getMinY() + 0.02,
                radius = axisalignedbb.getMaxX() - axisalignedbb.getMinX();

        glPushMatrix();
        GlStateManager.disableCull();
        glDisable(GL_DEPTH_TEST);
        glDepthMask(false);
        glDisable(GL_TEXTURE_2D);
        glShadeModel(GL_SMOOTH);
        glEnable(GL_BLEND);
        GlStateManager.disableLighting();
        GlStateManager.color(1, 1, 1, 1);
        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);

        float yaw = MathUtil.interpolate(thePlayer.getPrevRotationYaw(), thePlayer.getRotationYaw(), timer.getRenderPartialTicks()).floatValue();
        float pitchInterpolate = MathUtil.interpolate(thePlayer.getPrevRenderArmPitch(), thePlayer.getRenderArmPitch(), timer.getRenderPartialTicks()).floatValue();

        glTranslated(posX, posY, posZ);
        glEnable(GL_LINE_SMOOTH);
        glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
        glRotated(yaw, 0, -1, 0);
        glRotated(pitchInterpolate / 3.0, 0, 0, 0);
        glTranslatef(0, 0, pitchInterpolate / 270.0F);
        glLineWidth(2);
        glBegin(GL_LINE_LOOP);

        // outline/border or whatever you call it
        for (int i = 0; i <= 180; i++) {
            int color1 = ColorUtils.rainbow(7, i * 4, 1, 1, .5f).getRGB();
            GlStateManager.color(1, 1, 1, 1);
            RenderUtil.color(color1);
            glVertex3d(
                    posX - Math.sin(i * MathHelper.PI2 / 90) * radius,
                    posY + height - (thePlayer.isSneaking() ? 0.23 : 0) - 0.002,
                    posZ + Math.cos(i * MathHelper.PI2 / 90) * radius
            );
        }
        glEnd();

        glBegin(GL_TRIANGLE_FAN);
        int color12 = ColorUtils.rainbow(7, 4, 1, 1, .7f).getRGB();
        RenderUtil.color(color12);
        glVertex3d(posX, posY + height + 0.3 - (thePlayer.isSneaking() ? 0.23 : 0), posZ);

        // draw hat
        for (int i = 0; i <= 180; i++) {
            int color1 = ColorUtils.rainbow(7, i * 4, 1, 1, .2f).getRGB();
            GlStateManager.color(1, 1, 1, 1);
            RenderUtil.color(color1);
            glVertex3d(posX - Math.sin(i * MathHelper.PI2 / 90) * radius,
                    posY + height - (thePlayer.isSneaking() ? 0.23F : 0),
                    posZ + Math.cos(i * MathHelper.PI2 / 90) * radius
            );

        }
        glVertex3d(posX, posY + height + 0.3 - (thePlayer.isSneaking() ? 0.23 : 0), posZ);
        glEnd();


        glPopMatrix();

        glEnable(GL_CULL_FACE);
        glEnable(GL_TEXTURE_2D);
        glShadeModel(GL_FLAT);
        glDepthMask(true);
        glEnable(GL_DEPTH_TEST);
    }
}
