package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.features.modules.Combat.AntiBot;
import al.nya.reflect.features.modules.Combat.Teams;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.EntitySelect;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityArmorStand;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderManager;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class Tracers extends Module {
    private ModeValue colorModeValue = new ModeValue("Color",ColorMode.DistanceColor,ColorMode.values());
    private DoubleValue thicknessValue = new DoubleValue("Thickness", 5F, 1F, 2F,"0");
    private DoubleValue colorRedValue = new DoubleValue("Red", 255, 0, 0,""){
        @Override
        public boolean show(){
            return colorModeValue.getValue() == ColorMode.Custom;
        }
    };
    private DoubleValue colorGreenValue = new DoubleValue("Green", 255, 0, 160,""){
        @Override
        public boolean show(){
            return colorModeValue.getValue() == ColorMode.Custom;
        }
    };
    private DoubleValue colorBlueValue = new DoubleValue("Blue", 255, 0, 255,""){
        @Override
        public boolean show(){
            return colorModeValue.getValue() == ColorMode.Custom;
        }
    };
    private DoubleValue colorAlphaValue = new DoubleValue("Alpha", 255, 0, 150,"0");
    private DoubleValue distanceMultplierValue = new DoubleValue("DistanceMultiplier", 10F, 0.1F, 5F,"0.0") {
        @Override
        public boolean show(){
            return colorModeValue.getValue() == ColorMode.DistanceColor;
        }
    };
    private OptionValue playerHeightValue = new OptionValue("PlayerHeight", true);
    private OptionValue entityHeightValue = new OptionValue("EntityHeight", true);
    private EntitySelect select = new EntitySelect(true,false,false,true);
    public Tracers() {
        super("Tracers",ModuleType.Visual);
        addValues(colorModeValue,thicknessValue,colorRedValue,colorGreenValue,colorBlueValue,colorAlphaValue,distanceMultplierValue,playerHeightValue,entityHeightValue);
        addValues(select.getValues());
    }
    @EventTarget
    public void onRender3D(EventRender3D render3D) {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(thicknessValue.getValue().floatValue());
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        EntityPlayerSP thePlayer = mc.getThePlayer();
        for (Entity entity : mc.getTheWorld().getLoadedEntityList()) {
            if (isValid(entity)){
                Color color;
                if (colorModeValue.getValue() == ColorMode.Custom){
                    color = new Color(colorRedValue.getValue().intValue(), colorGreenValue.getValue().intValue(), colorBlueValue.getValue().intValue());
                }else if (colorModeValue.getValue() == ColorMode.DistanceColor){
                    int dist = (int) (thePlayer.getDistanceToEntity(entity) * distanceMultplierValue.getValue());
                    if (dist > 255) dist = 255;
                    color = new Color(255 - dist, dist, 0);
                }else if (colorModeValue.getValue() == ColorMode.Rainbow){
                    color = new Color(ColorUtils.rainbow(50,50,1F));
                }else {
                    color = Color.white;
                }
                drawTraces(entity,color);
            }
        }

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GlStateManager.resetColor();
    }
    private boolean isValid(Entity entity){
        if (entity.isNull()) return false;
        if (EntityArmorStand.isEntityArmorStand(entity)) return false;
        if (EntityPlayerSP.isEntityPlayerSP(entity)) return false;
        if (!select.check(entity)) return false;
        if (AntiBot.isEntityBot(entity)) return false;
        if (Teams.isTeam(entity)) return false;
        return true;
    }
    private void drawTraces(Entity entity, Color color) {
        float renderPartialTicks = mc.getTimer().getRenderPartialTicks();
        RenderManager renderManager = mc.getRenderManager();
        double x = (entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * renderPartialTicks -
                renderManager.getRenderPosX());
        double y = (entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * renderPartialTicks -
                renderManager.getRenderPosY());
        double z = (entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * renderPartialTicks -
                renderManager.getRenderPosZ());
        EntityPlayerSP thePlayer = mc.getThePlayer();
        Vec3 eyeVector = new Vec3(0.0, 0.0, 1.0)
                .rotatePitch((float) (-Math.toRadians(thePlayer.getRotationPitch())))
                .rotateYaw((float) (-Math.toRadians(thePlayer.getRotationYaw())));
        RenderUtil.glColor(color, colorAlphaValue.getValue().floatValue());

        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex3d(eyeVector.getXCoord(), (playerHeightValue.getValue() ? thePlayer.getEyeHeight() : 0) + eyeVector.getYCoord(), eyeVector.getZCoord());
        GL11.glVertex3d(x, y, z);
        if(entityHeightValue.getValue()) {
            GL11.glVertex3d(x, y + entity.getHeight(), z);
        }
        GL11.glEnd();
    }
    public enum ColorMode {
        Custom,
        DistanceColor,
        Rainbow
    }
}
