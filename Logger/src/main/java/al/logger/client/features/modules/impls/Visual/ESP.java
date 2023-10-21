package al.logger.client.features.modules.impls.Visual;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.event.client.render.EventRender3D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ValidUtils;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.player.PositionConverter;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.utils.rotation.RotationUtils;
import al.logger.client.value.impls.*;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.gui.ScaledResolution;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;
import al.logger.client.wrapper.LoggerMC.render.GlStateManager;
import al.logger.client.wrapper.LoggerMC.render.RenderHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector4f;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glTranslated;

public class ESP extends Module {

    public static ModeValue mode = new ModeValue("Mode", Mode.Outline, Mode.values());
    public static DoubleValue renderRange = new DoubleValue("Render Range", 128.0D, 16.0D, 32.0D, 1.0D);
    public static DoubleValue renderFov = new DoubleValue("Render Fov", 360.0D, 30.0D, 180.0D, 1.0D);
    public static OptionValue nameTag = new OptionValue("NameTag", true);
    public static OptionValue healthBar = new OptionValue("HealthBar", true);
    private final LinkedHashMap<EntityLivingBase, double[]> entityPositions = new LinkedHashMap<>();
    private final LinkedHashMap<EntityLivingBase, Vector4f> outlinePositions = new LinkedHashMap<>();
    private final MultipleColorValue colors = new MultipleColorValue("NameTag Colors",
            new ColorValue("Background Color", new Color(26, 26, 26, 102)),
            new ColorValue("Health Color", new Color(255, 50, 100, 255)),
            new ColorValue("Font Color", new Color(255, 255, 255))
    );
    private final MultipleColorValue boxColors = new MultipleColorValue("Box Colors",
            new ColorValue("Frame Color", new Color(255, 255, 255, 30)),
            new ColorValue("Line Color", new Color(255, 255, 255, 255))
    );
    public static DoubleValue lineWidth = new DoubleValue("Line Width", 10.0D, 1.0D, 2.0D, 0.5D);
    private final MultipleColorValue outlineColors = new MultipleColorValue("OutLine Colors",
            new ColorValue("FirstColor", new Color(255, 255, 255, 255)),
            new ColorValue("SecondColor", new Color(255, 255, 255, 255)),
            new ColorValue("HighHealth", new Color(66, 246, 123)),
            new ColorValue("MediumHealth", new Color(228, 255, 105)),
            new ColorValue("LowHealth", new Color(236, 100, 64)),
            new ColorValue("SuperLowHealth", new Color(255, 65, 68))
    );

    public ESP() {
        super("ESP", Category.Visual);
        healthBar.addCallBack(() -> nameTag.getValue());
        colors.addCallBack(() -> nameTag.getValue());
        boxColors.addCallBack(() -> mode.getValue() == Mode.Box);
        lineWidth.addCallBack(() -> mode.getValue() == Mode.Box);
        outlineColors.addCallBack(() -> mode.getValue() == Mode.Outline);
        this.addValues(mode, boxColors, outlineColors, lineWidth, nameTag, healthBar, colors, renderRange, renderFov);
    }

    @Listener
    public void onRender3D(EventRender3D e) {
        entityPositions.clear();
        outlinePositions.clear();
        for (Entity entity : mc.getTheWorld().getLoadedEntityList()) {
            if (entity != mc.getThePlayer() && ValidUtils.isValid(entity)) {
                EntityLivingBase entityLivingBase = new EntityLivingBase(entity.getWrappedObject());
                if (mc.getThePlayer().getDistanceToEntity(entityLivingBase) > renderRange.getValue()) {
                    continue;
                } else if (RotationUtils.getFovParallax(RotationUtils.getEntityHeadPosition(entityLivingBase)[0], mc.getThePlayer().getRotationYaw()) > renderFov.getValue()) {
                    continue;
                }
                if (nameTag.getValue()) {
                    entityPositions.put(entityLivingBase, PositionConverter.convertEntityPosition(entityLivingBase));
                }
                if (mode.getValue().equals(Mode.Outline)) {
                    outlinePositions.put(entityLivingBase, PositionConverter.getEntityPositionsOn2D(entityLivingBase));
                }
                if (mode.getValue().equals(Mode.Box)) {
                    renderEntityBox(entityLivingBase);
                }
            }
        }
    }

    public void renderEntityBox(EntityLivingBase entityLivingBase) {
        double x = entityLivingBase.getLastTickPosX() + (entityLivingBase.getPosX() - entityLivingBase.getLastTickPosX()) * mc.getTimer().getRenderPartialTicks() - mc.getRenderManager().getViewerPosX();
        double y = entityLivingBase.getLastTickPosY() + (entityLivingBase.getPosY() - entityLivingBase.getLastTickPosY()) * mc.getTimer().getRenderPartialTicks() - mc.getRenderManager().getViewerPosY();
        double z = entityLivingBase.getLastTickPosZ() + (entityLivingBase.getPosZ() - entityLivingBase.getLastTickPosZ()) * mc.getTimer().getRenderPartialTicks() - mc.getRenderManager().getViewerPosZ();
        double width = entityLivingBase.getEntityBoundingBox().getMaxX() - entityLivingBase.getEntityBoundingBox().getMinX() - 0.1;
        double height = entityLivingBase.getEntityBoundingBox().getMaxY() - entityLivingBase.getEntityBoundingBox().getMinY() + 0.25;
        RenderUtil.drawEntityESP(x, y, z, width, height, boxColors.getColorValue("Frame Color").getValue().getRGB(), boxColors.getColorValue("Line Color").getValue().getRGB(), lineWidth.getValue());
    }

    public void renderOutline(EntityLivingBase entityLivingBase) {

    }

    @Listener
    public void onRender2D(EventRender2D e) {

        if (mode.getValue().equals(Mode.Outline)) {
            Color firstColor = outlineColors.getColorValue("FirstColor").getValue();
            Color secondColor = outlineColors.getColorValue("SecondColor").getValue();
            Color highHealth = outlineColors.getColorValue("HighHealth").getValue();
            Color mediumHealth = outlineColors.getColorValue("MediumHealth").getValue();
            Color lowHealth = outlineColors.getColorValue("LowHealth").getValue();
            Color superLowHealth = outlineColors.getColorValue("SuperLowHealth").getValue();

            for (EntityLivingBase entityLivingBase : outlinePositions.keySet()) {
                Vector4f pos = outlinePositions.get(entityLivingBase);
                float x = pos.getX();
                float y = pos.getY();
                float right = pos.getZ();
                float bottom = pos.getW();
                float outlineThickness = .5f;
                float healthPercent = entityLivingBase.getHealth() / entityLivingBase.getMaxHealth();
                Color healthColor = healthPercent > .75 ? highHealth : healthPercent > .5 ? mediumHealth : healthPercent > .35 ? lowHealth : superLowHealth;
                float height = (bottom - y) + 1;
                //HealthBar
                RenderUtil.drawRect(right + 2.5f, y - .5f, 3.25f, height + 1, new Color(0, 0, 0, 180).getRGB());
                RenderUtil.drawRect(right + 3.85f, y + (height - (height * healthPercent)) + 0.5f, 0.5f, height * healthPercent - 1f, healthColor.getRGB());
                float textX = right + 8;
                String health = String.valueOf(healthPercent * 100);
                String text = health + "%";
                float fontHeight = FontLoadersWithChinese.hongMengBlod20.getStringHeight(text);
                float newHeight = height - fontHeight;
                float textY = y + (newHeight - (newHeight * healthPercent));
                glPushMatrix();
                glTranslated(textX - 5, textY, 1);
                glScaled(0.8f, 0.8f, 1);
                glTranslated(-(textX - 5), -textY, 1);
                FontLoadersWithChinese.hongMengBlod20.drawString(text, textX, textY, Color.WHITE.getRGB());
                glPopMatrix();
                //Outline Box
                RenderUtil.drawRect(x - .5f, y - outlineThickness, (right - x) + 2, outlineThickness, Color.BLACK.getRGB());
                RenderUtil.drawRect(x - outlineThickness, y, outlineThickness, (bottom - y) + 1, Color.BLACK.getRGB());
                RenderUtil.drawRect(x - .5f, (bottom + 1), (right - x) + 2, outlineThickness, Color.BLACK.getRGB());
                RenderUtil.drawRect(right + 1, y, outlineThickness, (bottom - y) + 1, Color.BLACK.getRGB());
                RenderUtil.drawRect(x + 1, y + 1, (right - x) - 1, outlineThickness, Color.BLACK.getRGB());
                RenderUtil.drawRect(x + 1, y + 1, outlineThickness, (bottom - y) - 1, Color.BLACK.getRGB());
                RenderUtil.drawRect(x + 1, (bottom - outlineThickness), (right - x) - 1, outlineThickness, Color.BLACK.getRGB());
                RenderUtil.drawRect(right - outlineThickness, y + 1, outlineThickness, (bottom - y) - 1, Color.BLACK.getRGB());
                RenderUtil.drawRoundedRectGradientRect(x, y, (right - x), 1, 1f, firstColor, secondColor, false);
                RenderUtil.drawRoundedRectGradientRect(x, y, 1, bottom - y, 1f, firstColor, secondColor, true);
                RenderUtil.drawRoundedRectGradientRect(x, bottom, right - x, 1, 1f, secondColor, firstColor, false);
                RenderUtil.drawRoundedRectGradientRect(right, y, 1, (bottom - y) + 1, 1f, secondColor, firstColor, true);
            }
        }

        if (nameTag.getValue()) {
            Color bgColor = colors.getColorValue("Background Color").getValue();
            Color healthColor = colors.getColorValue("Health Color").getValue();
            Color fontColor = colors.getColorValue("Font Color").getValue();

            for (EntityLivingBase entity : entityPositions.keySet()) {
                ScaledResolution scaledRes = new ScaledResolution(mc);
                GlStateManager.pushMatrix();
                double[] renderPositions = entityPositions.get(entity);
                if ((renderPositions[3] < 0.0D) || (renderPositions[3] >= 1.0D)) {
                    GlStateManager.popMatrix();
                    return;
                }
                GlStateManager.translate(renderPositions[0] / scaledRes.getScaleFactor(), renderPositions[1] / scaledRes.getScaleFactor(), 0.0D);
                GlStateManager.scale(1, 1, 1);
                GlStateManager.translate(0.0D, -2.5D, 0.0D);

                String string = entity.getName();
/*                EventNameTag event = new EventNameTag(string, entity);
                Logger.getInstance().getEventBus().callEvent(event);
                string = event.getRenderName();*/
                float allWidth = FontLoadersWithChinese.hongMengBlod17.getStringWidth(string.replaceAll("\u00a7.", "")) + 12f;
                RenderUtil.drawRound(-allWidth / 2f, -16.0f, allWidth, 18f, 5f, bgColor);
                if (healthBar.getValue()) {
                    RenderUtil.drawRound(-allWidth / 2f, -16.0f, (allWidth * entity.getHealth() / entity.getMaxHealth()), 18f, 5f, healthColor);
                }
                FontLoadersWithChinese.hongMengBlod17.drawString(string.replaceAll("\u00a7.", ""), -allWidth / 2f + 6f, -10.0, fontColor.getRGB());

                if (EntityPlayer.isEntityPlayer(entity)) {
                    ArrayList<ItemStack> itemsToRender = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        ItemStack itemStack = entity.getEquipmentInSlot(i);
                        if (!itemStack.isNull()) {
                            itemsToRender.add(itemStack);
                        }
                    }
                    float posX = -(itemsToRender.size() * 9) - 8f;
                    for (ItemStack stack : itemsToRender) {
                        GlStateManager.pushMatrix();
                        RenderHelper.enableGUIStandardItemLighting();
                        GlStateManager.disableAlpha();
                        GlStateManager.clear(256);
                        mc.getRenderItem().setZLevel(-150.0f);
                        GlStateManager.disableDepth();
                        //绘制背景
                        RenderUtil.drawRound(posX + 5, -39f, 18f, 18f, 5f, bgColor);
                        //修复附魔
                        mc.getRenderItem().renderItemIntoGUI(stack, (int) (posX + 6), -38);
                        mc.getRenderItem().renderItemOverlayIntoGUI(mc.getFontRenderer(), stack, (int) (posX + 6), -38, null);
                        mc.getRenderItem().setZLevel(0.0f);
                        //恢复GL
                        GlStateManager.enableDepth();
                        GlStateManager.enableAlpha();
                        RenderHelper.disableStandardItemLighting();
                        GlStateManager.popMatrix();
                        posX += 20f;
                    }
                }


                GlStateManager.popMatrix();
            }
        }
    }



    public enum Mode {
        Outline,
        Box
    }
}
