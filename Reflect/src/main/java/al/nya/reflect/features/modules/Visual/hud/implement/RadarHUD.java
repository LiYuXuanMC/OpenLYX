package al.nya.reflect.features.modules.Visual.hud.implement;

import al.nya.reflect.events.events.client.EventShader;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.Visual.HUD;
import al.nya.reflect.features.modules.Visual.hud.HUDObject;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.utils.render.RoundedUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import al.nya.reflect.wrapper.wraps.wrapper.world.World;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class RadarHUD extends HUDObject {
    public static int SIZE = 64;
    private int X = 0;
    private int Y = 0;
    private final DoubleValue sizeValue;
    private boolean editing;

    public RadarHUD() {
        super(50, 0, SIZE, "Radar");
        ModuleManager.getModule(HUD.class).addValue(sizeValue = new DoubleValue("MapSize", 300, 30, 128, "0"));
    }


    @Override
    public void drawHUD(int x, int y, float partialTicks, boolean isEditinh) {
        SIZE = sizeValue.getValue().intValue();
        this.setWidth(SIZE);
        X = x;
        Y = y;
        float radarX = x;
        float radarY = y;
        editing = isEditinh;
        RenderUtil.drawRect(radarX + ((SIZE / 2f) - 0.5f), radarY + 3.5f, radarX + (SIZE / 2f) + 0.5f, (radarY + SIZE) - 3.5f, new Color(255, 255, 255, 80).getRGB());
        RenderUtil.drawRect(radarX + 3.5f, radarY + ((SIZE / 2f) - 0.5f), (radarX + SIZE) - 3.5f, radarY + (SIZE / 2) + 0.5f, new Color(255, 255, 255, 80).getRGB());

        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc);
        Entity player = mc.getThePlayer();
        GL11.glPushMatrix();
        GL11.glTranslated(radarX, radarY, 0);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glScissor((int) (radarX * sr.getScaleFactor()), (int) (mc.getDisplayHeight() - (radarY + SIZE) * sr.getScaleFactor()), SIZE * sr.getScaleFactor(), SIZE * sr.getScaleFactor());
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glTranslated(SIZE / (double) 2, SIZE / (double) 2, 0);
        GL11.glRotated(player.getPrevRotationYaw() + (player.getPrevRotationYaw() - player.getPrevRotationYaw()) * partialTicks, 0, 0, -1);
        GL11.glPointSize(4 * sr.getScaleFactor());
        GL11.glEnable(GL11.GL_POINT_SMOOTH);
        GL11.glBegin(GL11.GL_POINTS);
        GL11.glColor4f(1, 1, 1, 1);
        GL11.glVertex2d(0, 0);
        GL11.glColor4f(1, 0, 0, 1);
        Entity[] entities = getAllMatchedEntity();
        for (Entity entity : entities) {
            double dx = (player.getPrevPosX() + (player.getPosX() - player.getPrevPosX()) * partialTicks) - (entity.getPrevPosX() + (entity.getPosX() - entity.getPrevPosX()) * partialTicks),
                    dz = (player.getPrevPosZ() + (player.getPosZ() - player.getPrevPosZ()) * partialTicks) - (entity.getPrevPosZ() + (entity.getPosZ() - entity.getPrevPosZ()) * partialTicks);
            GL11.glVertex2d(dx, dz);
        }
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_POINT_SMOOTH);
    }

    private Entity[] getAllMatchedEntity() {
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.getTheWorld();
        Entity player = mc.getThePlayer();
        if (!world.isNull()) {
            ArrayList<Entity> entities = new ArrayList<>(world.getLoadedEntityList().size());
            double max = (SIZE * SIZE) * 2;
            for (Entity entity : world.getLoadedEntityList()) {
                if (entity == player)
                    continue;
                double dx = player.getPosX() - entity.getPosX(),
                        dz = player.getPosZ() - entity.getPosZ();
                double distance = dx * dx + dz * dz;
                if (distance > max)
                    continue;
                if (!(EntityPlayer.isEntityPlayer(entity)))
                    continue;
                if (entity.isInvisible())
                    continue;
                entities.add(entity);
            }
            return entities.toArray(new Entity[0]);
        }
        return new Entity[0];
    }

    @Override
    public void onBlur(EventShader event) {
        float y = (isSpecial() || editing) ? Y - 18 : Y;
        float add = (isSpecial() || editing) ? 18 : 0;
        this.translate.interpolate(X, y, 0.2f);
        RoundedUtil.drawRoundOutline(translate.getX(), translate.getY(), SIZE, SIZE + add, 2, .5f, ColorUtils.applyOpacity(Color.WHITE, .85f), ColorUtils.applyOpacity(Color.WHITE, .85f));
        RoundedUtil.drawRound(translate.getX(), translate.getY(), SIZE, SIZE + add, 2, false, Color.WHITE);
    }
}