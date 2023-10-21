package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.Reflect;
import al.nya.reflect.config.FriendManager;
import al.nya.reflect.events.EventBus;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventNameTag;
import al.nya.reflect.events.events.EventRender2D;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.features.command.commands.Teleport;
import al.nya.reflect.features.modules.Combat.AntiBot;
import al.nya.reflect.features.modules.Combat.Teams;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.Player.UHCHelper;
import al.nya.reflect.utils.RaycastUtils;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.GameSettings;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.*;
import al.nya.reflect.wrapper.wraps.wrapper.render.FontRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderManager;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;

public class NameTags extends Module {
    private static final ModeValue mode = new ModeValue("Mode",TagThemes.Jello,TagThemes.values());
    private static final DoubleValue size = new DoubleValue("Size", 5.0, 0.1, 1.5, "0.0");
    private Thread tpThread;
    public int renderNameTag = 0;
    public NameTags() {
        super("NameTags",ModuleType.Visual);
        addValue(mode);
        addValue(size);
    }
    @Override
    public String getSuffix(){
        return mode.getValue().name();
    }
    @EventTarget
    public void onRender3D(EventRender3D eventRender3D) {
        renderNameTag = 0;
        Entity raycastedEntity;
        if (ModuleManager.getModule(UHCHelper.class).isEnable() && UHCHelper.clickPlayerTP.getValue()) {
            raycastedEntity = RaycastUtils.raycastEntity(500D, EntityLivingBase::isEntityLivingBase);
        } else {
            raycastedEntity = null;
        }
        EntityPlayerSP thePlayer = mc.getThePlayer();
        FontRenderer fontRenderer = mc.getFontRenderer();
        RenderManager renderManager = mc.getRenderManager();
            for (Entity entity : Minecraft.getMinecraft().getTheWorld().getLoadedEntityList()) {
                if (EntityPlayer.isEntityPlayer(entity) && !EntityPlayerSP.isEntityPlayerSP(entity) && !AntiBot.isEntityBot(entity)) {

                    // Push
                    GL11.glPushMatrix();
                    GlStateManager.resetColor();

                        // Translate to player position

                        double lastTickPosX = entity.getLastTickPosX();
                        double lastTickPosY = entity.getLastTickPosY();
                        double lastTickPosZ = entity.getLastTickPosZ();
                        GL11.glTranslated( // Translate to player position with render pos and interpolate it
                                lastTickPosX + (entity.getPosX() - lastTickPosX) * eventRender3D.getPartialTicks() - renderManager.getViewerPosX(),
                                lastTickPosY + (entity.getPosY() - lastTickPosY) * eventRender3D.getPartialTicks() - renderManager.getViewerPosY() + (double) entity.getEyeHeight() + 0.55,
                                lastTickPosZ + (entity.getPosZ() - lastTickPosZ) * eventRender3D.getPartialTicks() - renderManager.getViewerPosZ()
                        );

                        // Rotate view to player
                        GL11.glRotatef(-renderManager.getPlayerViewY(), 0F, 1F, 0F);
                        GL11.glRotatef(renderManager.getPlayerViewX(), 1F, 0F, 0F);

                        // Scale
                        float distance = thePlayer.getDistanceToEntity(entity) / 4F;

                        if (distance < 1F) {
                            distance = 1F;
                        }


                        float scale = (distance / 150F) * size.getValue().floatValue();

                        // Disable lightning and depth test
                        RenderUtil.disableGlCap(GL11.GL_LIGHTING, GL11.GL_DEPTH_TEST);

                        // Enable blend
                        RenderUtil.enableGlCap(GL11.GL_BLEND);
                        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);


                    // colors
                    int hpBarColor = new Color(255, 255, 255, 170).getRGB();
                    String name = entity.getDisplayName().getUnformattedText();
                    String tag = entity.getName();


                    if (name.startsWith("§")) {
                        hpBarColor = ColorUtils.colorCode(name.substring(1, 2), 170);
                    }

                    if ((raycastedEntity != null && !raycastedEntity.isNull()) && entity.getWrapObject().equals(raycastedEntity.getWrapObject()) && mc.getCurrentScreen().isNull()) {
                        tag = "§c[按下中键TP]§r " + tag;
                        if (Mouse.isButtonDown(2)) {
                            if (tpThread == null || !tpThread.isAlive()) {
                                tpThread = new Thread(() -> {
                                    final double y = UHCHelper.YMode.getValue() == UHCHelper.YMODE.TargetY ? entity.getPosY() : UHCHelper.YMode.getValue() == UHCHelper.YMODE.L100 ? 100D : 200D;
                                    Teleport.pos = new double[]{entity.getPosX(), y, entity.getPosZ()};
                                    mc.getThePlayer().sendChatMessage("/hub");
                                    try {
                                        Thread.sleep(500L);
                                    } catch (InterruptedException ignored) {
                                    }
                                    mc.getThePlayer().sendChatMessage("/rejoin");
                                    ClientUtil.printChat("Try to teleport to " + entity.getPosX() + "," + y + "," + entity.getPosZ());
                                });
                                tpThread.start();
                            }
                        }
                    }

                    if (Teams.isTeam(entity)) {
                        tag = tag + "§a[Team]";
                    }

                    if (FriendManager.friendsList.contains(entity.getName())) {
                        tag = tag + "§b[Friend]";
                    }
                    EventNameTag eventNameTag = new EventNameTag(entity, tag);
                    Reflect.Instance.eventBus.callEvent(eventNameTag);
                    tag = eventNameTag.getName();

                    int bgColor = new Color(0, 0, 0, 100).getRGB();
                    int width = fontRenderer.getStringWidth(tag) / 2;
                    float maxWidth = (width + 4F) - (-width - 4F);
                    EntityLivingBase entityLivingBase = new EntityLivingBase(entity.getWrapObject());
                    float healthPercent = entityLivingBase.getHealth() / entityLivingBase.getMaxHealth();
                    int fontHeight = fontRenderer.getFontHeight();
                    // render bg
                    GL11.glScalef(-scale * 2, -scale * 2, scale * 2);
//                        if (betterFont.getValue()) {
//                            RenderUtils.drawRect(-width - 4F, -FontManager.PingFang18.getHeight() * 3F, width + 4F, -3F, bgColor);
//                        } else {
                            RenderUtil.drawRect(-width - 4F, -fontHeight * 3F, width + 4F, -3F, bgColor);
//                        }


                        // render hp bar
                        if (healthPercent > 1) {
                            healthPercent = 1F;
                        }

                        RenderUtil.drawRect(-width - 4F, -3F, (-width - 4F) + (maxWidth * healthPercent), 1F, hpBarColor);
                        RenderUtil.drawRect((-width - 4F) + (maxWidth * healthPercent), -3F, width + 4F, 1F, bgColor);

                        // string
//                        if (betterFont.getValue()) {
//                            FontManager.PingFang18.drawString(tag, -width, -FontManager.PingFang18.getHeight() * 2 - 4, Color.WHITE.getRGB());
//                            GL11.glScalef(0.5F, 0.5F, 0.5F);
//                            FontManager.PingFang18.drawString("Health: " + entity.getHealth(), -width * 2, -FontManager.PingFang18.getHeight() * 2, Color.WHITE.getRGB());
//                        } else {
                            fontRenderer.drawString("§f" + tag, -width, -fontHeight * 2 - 4, Color.WHITE.getRGB());
                            GL11.glScalef(0.5F, 0.5F, 0.5F);
                            fontRenderer.drawString("§fHealth: " + entityLivingBase.getHealth(), -width * 2, -fontHeight * 2, Color.WHITE.getRGB());
//                        }


                        // Reset caps
                        RenderUtil.resetCaps();

                    // Reset color
                    GlStateManager.resetColor();
                    GL11.glColor4f(1F, 1F, 1F, 1F);

                    // Pop
                    GL11.glPopMatrix();
                }
            }
    }

    @EventTarget
    public void onRender2D(EventRender2D event) {
        if (mode.getValue() == TagThemes.Vapu) {
            renderNameTag++;
            if (renderNameTag != 1)
                return;

            GameSettings gameSettings = mc.getGameSettings();

            WorldClient world = mc.getTheWorld();

            EntityPlayerSP thePlayer = mc.getThePlayer();

            int keyCode = gameSettings.getkeyBindPlayerList().getKeyCode();
            boolean tab;
            if (keyCode < 0) {
                tab = Mouse.isButtonDown(keyCode + 100);
            } else {
                tab = Keyboard.isKeyDown(keyCode);
            }

            FontRenderer fontRenderer = mc.getFontRenderer();
            List<Entity> entityList = world.getLoadedEntityList();
            int count = 0;
            GL11.glPushMatrix();

            for (Entity entity : entityList) {

                if (EntityPlayerSP.isEntityPlayerSP(entity) || entity.getWrapObject() == thePlayer.getWrapObject())
                    continue;
                else if (EntityPlayer.isEntityPlayer(entity) || EntityVillager.isEntityVillager(entity)) {
                    String text = "";
                    if (EntityVillager.isEntityVillager(entity)) {
                        text = entity.getDisplayName().getUnformattedText();
                    } else {
                        if ((new EntityPlayer(entity.getWrapObject())).isPlayerSleeping())
                            continue;
                        if (!(EntityOtherPlayerMP.isEntityOtherPlayerMP(entity.getWrapObject())))
                            continue;
                        if (!AntiBot.isOnTab(entity))
                            continue;
                    }

                    int fontColor = 0xFFFFFF;
                    double scaleMult = 1.0;
                    boolean isSpecial = false;

                    // 防隐身处理
                    if ((EntityPlayer.isEntityPlayer(entity)) && entity.isInvisible()) {
                        text = "§7[§c隐身§7]§r " + text;
                        scaleMult = 1.0;
                        isSpecial = true;
                    }
                    if (!(tab || isSpecial))
                        continue;

                    count++;

                    GL11.glPushMatrix();

                    GL11.glLoadIdentity();


                    Entity player = mc.getRenderViewEntity();

                    mc.getEntityRenderer().setupCameraTransform(event.getPartialTicks(), 0);


                    double d0 = player.getLastTickPosX() + (player.getPosX() - player.getLastTickPosX()) * (double) event.getPartialTicks();
                    double d1 = player.getLastTickPosY() + (player.getPosY() - player.getLastTickPosY()) * (double) event.getPartialTicks();
                    double d2 = player.getLastTickPosZ() + (player.getPosZ() - player.getLastTickPosZ()) * (double) event.getPartialTicks();


                    GL11.glTranslated( // Translate to player position with render pos and interpolate it
                            -d0, -d1, -d2);

                    double distance = thePlayer.getDistanceToEntity(entity) / 4F;

                    if (distance < 1F)
                        distance = 1F;

                    double maxDistance = 24.0;

                    GL11.glTranslated( // Translate to player position with render pos and interpolate it
                            entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * event.getPartialTicks(),
                            entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * event.getPartialTicks()
                                    + (double) entity.getEyeHeight() + 1.0
                                    + Math.min((thePlayer.getDistanceToEntity(entity)) / maxDistance, 1.0) * 1.5,
                            entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * event.getPartialTicks());
//
                    GL11.glRotatef(-mc.getRenderManager().getPlayerViewY(), 0F, 1F, 0F);
                    GL11.glRotatef(mc.getRenderManager().getPlayerViewX(), 1F, 0F, 0F);
//
                    double scale = distance / 100F * 1.6 * scaleMult;

                    GL11.glScaled(-scale, -scale, scale);
//
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glDisable(GL11.GL_DEPTH_TEST);

                    // Enable blend
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

                    int width = fontRenderer.getStringWidth(text) / 2;

                    RenderUtil.setColourWithAlphaPercent(0x000000, 25);
                    RenderUtil.drawRect(-width - 2F, -2F, width + 4F - (-width - 2F), fontRenderer.getFontHeight() + 2F - (-2F));

                    RenderUtil.setColourWithAlphaPercent(fontColor, 100);
                    fontRenderer.drawString(text, 1F + -width, 1F, fontColor, true);

                    GL11.glPopMatrix();

                }


            }
            GL11.glPopMatrix();
            mc.getEntityRenderer().setupOverlayRendering();

            if (!this.mc.getCurrentScreen().isNull()) {
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
            }

        }

    }

    public enum TagThemes {
        Jello,
        Vapu
    }
}
