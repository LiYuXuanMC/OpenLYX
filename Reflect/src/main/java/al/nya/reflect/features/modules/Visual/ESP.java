package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.features.modules.Combat.AntiBot;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.Timer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.*;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderManager;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class ESP extends Module {
    private static ModeValue mode = new ModeValue("Mode",ESPMode.Box3D,ESPMode.values());
    private static OptionValue rainbow = new OptionValue("Rainbow",false);
    private static OptionValue player = new OptionValue("Player",true);
    private static OptionValue mob = new OptionValue("Mob",true);
    private static OptionValue animal = new OptionValue("Animal",true);
    public ESP() {
        super("ESP",ModuleType.Visual);
        addValue(mode);
        addValue(rainbow);
        addValue(player);
        addValue(mob);
        addValue(animal);
    }
    @EventTarget
    public void onRender3D(EventRender3D eventRender3D){
        if (mode.getValue() == ESPMode.Box3D){
            Color color;
            if (rainbow.getValue()){
                color = new Color(Color.HSBtoRGB((float) ((double) Minecraft.getMinecraft().getThePlayer().getTicksExisted() / 50.0 + Math.sin((double) 100 / 50.0 * 1.6)) % 2.0f, 0.5f, 1.0f));
            }else {
                color = Reflect.ColorStyle;
            }
            for (Entity entity : mc.getTheWorld().getLoadedEntityList()) {
                if(EntityLivingBase.isEntityLivingBase(entity))
                if (!AntiBot.isEntityBot(entity) && ((player.getValue() && EntityPlayer.isEntityPlayer(entity))||
                        (mob.getValue() && EntityMob.isEntityMob(entity))||
                        (animal.getValue() && EntityAnimal.isEntityAnimal(entity)))&&(!EntityPlayerSP.isEntityPlayerSP(entity))){
                    drawEntityBox(entity,color,true);
                }
            }
        }
    }
    public static void drawEntityBox(final Entity entity, final Color color, final boolean outline) {
        Minecraft mc = Minecraft.getMinecraft();
        final RenderManager renderManager = mc.getRenderManager();
        final Timer timer = mc.getTimer();

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        RenderUtil.enableGlCap(GL_BLEND);
        RenderUtil.disableGlCap(GL_TEXTURE_2D, GL_DEPTH_TEST);
        glDepthMask(false);

        final double x = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * timer.getRenderPartialTicks()
                - renderManager.getRenderPosX();
        final double y = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * timer.getRenderPartialTicks()
                - renderManager.getRenderPosY();
        final double z = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * timer.getRenderPartialTicks()
                - renderManager.getRenderPosZ();
        final AxisAlignedBB entityBox = entity.getEntityBoundingBox();
        final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(
                entityBox.getMinX() - entity.getPosX() + x - 0.05D,
                entityBox.getMinY() - entity.getPosY() + y,
                entityBox.getMinZ() - entity.getPosZ() + z - 0.05D,
                entityBox.getMaxX() - entity.getPosX() + x + 0.05D,
                entityBox.getMaxY() - entity.getPosY() + y + 0.15D,
                entityBox.getMaxZ() - entity.getPosZ() + z + 0.05D
        );
        if (outline) {
            glLineWidth(1F);
            RenderUtil.enableGlCap(GL_LINE_SMOOTH);
            RenderUtil.glColor(color.getRed(), color.getGreen(), color.getBlue(), 95);
            RenderUtil.drawSelectionBoundingBox(axisAlignedBB);
        }

        RenderUtil.glColor(color.getRed(), color.getGreen(), color.getBlue(), outline ? 26 : 35);
        RenderUtil.drawFilledBox(axisAlignedBB);
        glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        glDepthMask(true);
        RenderUtil.resetCaps();
    }
    @Override
    public String getSuffix(){
        return mode.getValue().name();
    }
    public enum ESPMode {
        Box3D
    }
}
