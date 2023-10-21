package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventRender2D;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.features.modules.Combat.AntiBot;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.EntitySelect;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.Timer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityArmorStand;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class PointerESP extends Module {
    public ModeValue dimensionValue = new ModeValue("Dimension", DimensionValue.R3d, DimensionValue.values());
    private final EntitySelect select = new EntitySelect(true, false, false, true);

    public PointerESP() {
        super("PointerESP", "指针ESP", ModuleType.Visual);
        addValue(dimensionValue);
        addValues(select.getValues());
    }

    @EventTarget
    public void onRender2D(EventRender2D e) {
        if (dimensionValue.getValue() == DimensionValue.R2d) {
            ScaledResolution scaledResolution = new ScaledResolution(mc);
            GL11.glPushMatrix();
            GL11.glTranslatef(scaledResolution.getScaledWidth() / 2f, scaledResolution.getScaledHeight() / 2f, 0.0f);

            draw();

            GL11.glPopMatrix();
        }
    }

    @EventTarget
    public void onRender3D(EventRender3D e) {
        if (dimensionValue.getValue() == DimensionValue.R3d) {
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
            GL11.glPolygonOffset(1.0f, -1000000f);

            GL11.glPushMatrix();
            GL11.glScaled(0.01, 0.01, 0.01);
            GL11.glRotatef(90f, 1f, 0f, 0f);
            GL11.glRotatef(180f + mc.getThePlayer().getRotationYaw(), 0f, 0f, 1f);
            draw();
            GL11.glPopMatrix();

            GL11.glPolygonOffset(1.0f, 1000000f);
            GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
            GL11.glEnable(GL11.GL_CULL_FACE);
        }
    }

    private void draw() {
        int halfAngle = 50;
        int radius = -70;
        int size = 10;
        EntityPlayerSP thePlayer = mc.getThePlayer();
        Timer timer = mc.getTimer();
        double playerPosX = thePlayer.getPosX() + (thePlayer.getPosX() - thePlayer.getLastTickPosX()) * timer.getRenderPartialTicks();
        double playerPosZ = thePlayer.getPosZ() + (thePlayer.getPosZ() - thePlayer.getLastTickPosZ()) * timer.getRenderPartialTicks();
        Color color = Reflect.ColorStyle;
        Color damageColor = ColorUtils.dmgRainbowValue(color);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        for (EntityPlayer entity : mc.getTheWorld().getPlayerEntities()) {
            if (!isValid(entity)) continue;
            double entX = entity.getPosX() + (entity.getPosX() - entity.getLastTickPosX()) * timer.getRenderPartialTicks();
            double entZ = entity.getPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * timer.getRenderPartialTicks();
            double pos1 = (entX - playerPosX) * 0.2;
            double pos2 = (entZ - playerPosZ) * 0.2;
            double cos = Math.cos(thePlayer.getRotationYaw() * (Math.PI / 180));
            double sin = Math.sin(thePlayer.getRotationYaw() * (Math.PI / 180));
            double rotY = -(pos2 * cos - pos1 * sin);
            double rotX = -(pos1 * cos + pos2 * sin);
            float angle = ((float) (Math.atan2(rotY, rotX) * 180 / Math.PI)) + 90f;
            int aplha = (int) (255 - Math.min((Math.pow(Math.sqrt(playerPosX - entX), 2) + Math.pow((playerPosZ - entZ), 2) / 70), 1.0) * (255 - 100));
            if (entity.getHurtTime() > 0) {
                RenderUtil.glColor(damageColor, aplha);
            } else {
                RenderUtil.glColor(color, aplha);
            }
            GL11.glRotatef(angle, 0.0f, 0.0f, 1.0f);

            GL11.glLineWidth(4f);
            GL11.glBegin(GL11.GL_LINE_STRIP);
            GL11.glVertex2d(Math.sin(-halfAngle * Math.PI / 180) * size, radius + Math.cos(-halfAngle * Math.PI / 180) * size);
            GL11.glVertex2f(0f, (float) radius);
            GL11.glVertex2d(Math.sin(halfAngle * Math.PI / 180) * size, radius + Math.cos(halfAngle * Math.PI / 180) * size);

            GL11.glEnd();
            GL11.glRotatef(-angle, 0.0f, 0.0f, 1.0f);


        }

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);

    }

    private boolean isValid(Entity entity) {
        if (entity.isNull()) return false;
        if (EntityArmorStand.isEntityArmorStand(entity)) return false;
        if (EntityPlayerSP.isEntityPlayerSP(entity)) return false;
        if (!select.check(entity)) return false;
        return !AntiBot.isEntityBot(entity);
    }

    enum DimensionValue {
        R2d,
        R3d,
    }
}
