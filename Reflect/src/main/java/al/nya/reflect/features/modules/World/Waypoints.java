package al.nya.reflect.features.modules.World;

import al.nya.reflect.events.EventType;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.events.events.EventRender2D;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.World.waypoint.WaypointManager;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.ServerData;
import al.nya.reflect.wrapper.wraps.wrapper.network.C16.C16PacketClientStatus;
import al.nya.reflect.wrapper.wraps.wrapper.network.C16.C16State;
import al.nya.reflect.wrapper.wraps.wrapper.render.FontRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderManager;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import lombok.Getter;
import lombok.Setter;
import org.lwjgl.opengl.GL11;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 路标点模块
 *
 * @author a6513375
 */
@SuppressWarnings("unused")
public class Waypoints extends Module {
    // 是否在死亡时创建一个路径点
    private final OptionValue death = new OptionValue("Death", true);
    // 是否在距离路径点的距离多少时不渲染
    private final DoubleValue hideDistance = new DoubleValue("HideDistance", 10D, 1D, 5D, "0.0");
    // 是否在屏幕中心绘制一条线到每个路径点
    private final OptionValue tracers = new OptionValue("Tracers", false);
    // 线段的宽度
    private final DoubleValue tracersWidth = new DoubleValue("TracersWidth", 5D, 0.1D, 1D, "0.0") {
        @Override
        public boolean show() {
            return tracers.getValue();
        }
    };
    // 线的 Alpha 数值
    private final DoubleValue tracersAlpha = new DoubleValue("TracersAlpha", 255D, 1D, 255D, "0") {
        @Override
        public boolean show() {
            return tracers.getValue();
        }
    };
    // 是否在在路径点渲染一个 3D 标志
    private final OptionValue point = new OptionValue("Point", true);
    // 渲染的形状 (Skid from Unfair Client)
    private final ModeValue pointShape = new ModeValue("PointShape", Shape.CUBE, Shape.values());
    // TODO
    private final OptionValue pointRotate = new OptionValue("PointRotate", true);
    // TODO
    private final DoubleValue pointRotateSpeed = new DoubleValue("PointRotateSpeed", 2D, 0.1D, 0.5D, "0.0");
    // TODO
    private final DoubleValue pointWidth = new DoubleValue("PointWidth", 5D, 0.1D, 1D, "0.0");
    // TODO
    private final DoubleValue pointAlpha = new DoubleValue("PointAlpha", 255D, 1D, 127D, "0");
    // TODO
    private final DoubleValue pointSize = new DoubleValue("PointSize", 3D, 0.1D, 0.5D, "0.0");
    // TODO
    private final DoubleValue pointYOffset = new DoubleValue("PointYOffset", 1D, -1D, 0D, "0.0");
    // TODO
    private final DoubleValue pointDiamondHeight = new DoubleValue("PointDiamondHeight", 3D, 0.1D, 0.5D, "0.0");
    private String host = "";
    private float angle = 0;

    public Waypoints() {
        super("Waypoints", "路径点", ModuleType.World);
        addValues(
                death,
                hideDistance,
                tracers,
                tracersWidth,
                tracersAlpha,
                point,
                pointShape,
                pointRotate,
                pointRotateSpeed,
                pointWidth,
                pointAlpha,
                pointSize,
                pointYOffset,
                pointDiamondHeight
        );
    }

    @EventTarget
    public void onRender2D(final EventRender2D event) {

        // 将要重复用到的对象存起来，提高效率
        EntityPlayerSP thePlayer = mc.getThePlayer();
        FontRenderer fontRenderer = mc.getFontRenderer();
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        RenderManager renderManager = mc.getRenderManager();
        int scaleFactor = scaledResolution.getScaleFactor();
        GL11.glPushMatrix();

        GL11.glLoadIdentity();

        Entity player = mc.getRenderViewEntity();

        mc.getEntityRenderer().setupCameraTransform(event.getPartialTicks(), 0);
        float partialTicks = mc.getTimer().getRenderPartialTicks();
        double d0 = player.getLastTickPosX() + (player.getPosX() - player.getLastTickPosX()) * (double) partialTicks;
        double d1 = player.getLastTickPosY() + (player.getPosY() - player.getLastTickPosY()) * (double) partialTicks;
        double d2 = player.getLastTickPosZ() + (player.getPosZ() - player.getLastTickPosZ()) * (double) partialTicks;

        GL11.glTranslated( // Translate to player position with render pos and interpolate it
                -d0, -d1, -d2
        );
        for (WaypointData waypointData : WaypointManager.INSTANCE.getWaypointDataList()) {
            if (waypointData != null) {
                if (host.equalsIgnoreCase(waypointData.getHost()) && thePlayer.getDimension() == waypointData.dimension) {
                    final double dist = thePlayer.getDistance(waypointData.getX(), waypointData.getY(), waypointData.getZ());
                    if (dist >= this.hideDistance.getValue()) {
                        final int color = ColorUtils.reAlpha(waypointData.color, this.tracersAlpha.getValue().intValue());

                        final String text = "§f" + waypointData.getName() + " §r(§7" + new DecimalFormat("#.#").format(dist) + "m§r)";


                        int fontColor = 0xFFFFFF;
                        double scaleMult = 1.0;

                        GL11.glPushMatrix();

                        double distance = mc.getThePlayer().getDistance(waypointData.getX(), waypointData.getY(), waypointData.getZ()) / 4F;

                        if (distance < 1F)
                            distance = 1F;

                        Vec3 position = new Vec3(waypointData.getX(), waypointData.getY(), waypointData.getZ());

                        if (position.distanceTo(mc.getThePlayer().getPositionVector()) > 256) {
                            Vec3 direction = position.subtract(mc.getThePlayer().getPositionVector()).normalize();
                            position = new Vec3(direction.getXCoord() * 256, direction.getYCoord() * 256, direction.getZCoord() * 256).add(mc.getThePlayer().getPositionVector());
                        }

                        GL11.glTranslated(
                                position.getXCoord(),
                                position.getYCoord(),
                                position.getZCoord()
                        );

                        GL11.glRotatef(-mc.getRenderManager().getPlayerViewY(), 0F, 1F, 0F);
                        GL11.glRotatef(mc.getRenderManager().getPlayerViewX(), 1F, 0F, 0F);
//
                        double scale = Math.min(distance, 512) / 100F * 1.6 * scaleMult;

                        GL11.glScaled(-scale, -scale, scale);
//
                        GL11.glDisable(GL11.GL_LIGHTING);
                        GL11.glDisable(GL11.GL_DEPTH_TEST);

                        // Enable blend
                        GL11.glEnable(GL11.GL_BLEND);
                        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

                        int width = fontRenderer.getStringWidth(text) / 2;

                        RenderUtil.setColourWithAlphaPercent(0x000000, 25);
                        RenderUtil.drawRect(-width - 2F, -fontRenderer.getFontHeight() / 2 - 2, width + 4F - (-width - 2F), fontRenderer.getFontHeight() + 4);


                        RenderUtil.setColourWithAlphaPercent(fontColor, 100);
                        fontRenderer.drawString(text, 1F + -width, -fontRenderer.getFontHeight() / 2,
                                ColorUtils.reAlpha(color, 255), true);


                        GL11.glPopMatrix();
                    }
                }
            }
        }
        GL11.glPopMatrix();


        mc.getEntityRenderer().setupOverlayRendering();
        if (!this.mc.getCurrentScreen().isNull()) {
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        }
    }

    @EventTarget
    public void onRender3D(final EventRender3D event) {
        if (!this.point.getValue()) // 没开启就return
            return;

        if (this.pointRotate.getValue()) {
            if (this.angle > 360.0f)
                this.angle = 0.0f;
            else
                this.angle += this.pointRotateSpeed.getValue();
        }

        RenderUtil.begin3D();

        // 将要重复用到的对象存起来，提高效率
        EntityPlayerSP thePlayer = mc.getThePlayer();
        RenderManager renderManager = mc.getRenderManager();

        for (WaypointData waypointData : WaypointManager.INSTANCE.getWaypointDataList()) {
            if (waypointData != null) {
                if (host.equalsIgnoreCase(waypointData.getHost()) && thePlayer.getDimension() == waypointData.dimension) {
                    final double dist = thePlayer.getDistance(waypointData.getX(), waypointData.getY(), waypointData.getZ());
                    if (dist >= this.hideDistance.getValue()) {
                        final int color = ColorUtils.reAlpha(waypointData.color, this.pointAlpha.getValue().intValue());
                        GlStateManager.pushMatrix();
                        GlStateManager.translate(waypointData.x - renderManager.getViewerPosX(), waypointData.y - renderManager.getViewerPosY(), waypointData.z - renderManager.getViewerPosZ());
                        if (this.pointRotate.getValue()) {
                            GlStateManager.rotate(this.angle, 0, 1, 0);
                        }
                        final AxisAlignedBB bb = new AxisAlignedBB(
                                -this.pointSize.getValue(),
                                -this.pointSize.getValue() + this.pointYOffset.getValue(),
                                -this.pointSize.getValue(),
                                this.pointSize.getValue(),
                                this.pointSize.getValue() + this.pointYOffset.getValue(),
                                this.pointSize.getValue());

                        if (this.pointShape.getValue() == Shape.CUBE) {
                            RenderUtil.drawFilledBox(bb, color);
                            RenderUtil.drawBoundingBox(bb, this.pointWidth.getValue().floatValue(), color);
                        } else if (this.pointShape.getValue() == Shape.PYRAMID) {
                            RenderUtil.drawFilledPyramid(bb, color);
                            RenderUtil.drawBoundingBoxPyramid(bb, this.pointWidth.getValue().floatValue(), color);
                        } else if (this.pointShape.getValue() == Shape.DIAMOND) {
                            RenderUtil.drawFilledDiamond(bb, this.pointYOffset.getValue().floatValue(), this.pointDiamondHeight.getValue().floatValue(), color);
                            RenderUtil.drawBoundingBoxDiamond(bb, this.pointWidth.getValue().floatValue(), this.pointYOffset.getValue().floatValue(), this.pointDiamondHeight.getValue().floatValue(), color);
                        } else if (this.pointShape.getValue() == Shape.SPHERE) {
                            RenderUtil.drawSphere(this.pointSize.getValue().floatValue(), 32, 32, color);
                        }
                        GlStateManager.popMatrix();

                    }
                }
            }
        }
        RenderUtil.end3D();
    }

    @EventTarget
    public void onRender3DTracers(final EventRender3D event) {
        if (!this.tracers.getValue()) // 没开启就return
            return;


        // 将要重复用到的对象存起来，提高效率
        EntityPlayerSP thePlayer = mc.getThePlayer();
        RenderManager renderManager = mc.getRenderManager();

        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glLineWidth(tracersWidth.getValue().floatValue());
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);

        for (WaypointData waypointData : WaypointManager.INSTANCE.getWaypointDataList()) {
            if (waypointData != null) {
                if (host.equalsIgnoreCase(waypointData.getHost()) && thePlayer.getDimension() == waypointData.dimension) {
                    final double dist = thePlayer.getDistance(waypointData.getX(), waypointData.getY(), waypointData.getZ());
                    if (dist >= this.hideDistance.getValue()) {
                        final int color = ColorUtils.reAlpha(waypointData.color, this.pointAlpha.getValue().intValue());


                        double x = (waypointData.getX()) * event.getPartialTicks() -
                                renderManager.getRenderPosX();
                        double y = (waypointData.getY()) * event.getPartialTicks() -
                                renderManager.getRenderPosY();
                        double z = (waypointData.getZ()) * event.getPartialTicks() -
                                renderManager.getRenderPosZ();

                        Vec3 eyeVector = new Vec3(0.0, 0.0, 1.0)
                                .rotatePitch((float) (-Math.toRadians(thePlayer.getRotationPitch())))
                                .rotateYaw((float) (-Math.toRadians(thePlayer.getRotationYaw())));
                        RenderUtil.glColor(color);
                        GL11.glBegin(GL11.GL_LINE_STRIP);
                        GL11.glVertex3d(eyeVector.getXCoord(), (thePlayer.getEyeHeight()) + eyeVector.getYCoord(), eyeVector.getZCoord());
                        GL11.glVertex3d(x, y, z);
                        GL11.glEnd();


                    }
                }
            }
        }

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GlStateManager.resetColor();

    }

    @EventTarget
    public void onWorldLoad(final EventWorldLoad event) {
        if (!event.getWorld().isNull()) {
            this.host = mc.getCurrentServerData().getServerIP();
        }
    }

    @EventTarget
    public void onPacket(final EventPacket event) {
        if (event.getEventType() == EventType.RecievePacket) return;

        if (C16PacketClientStatus.isC16PacketClientStatus(event.getPacket())) {
            if (!this.death.getValue())
                return;
            C16PacketClientStatus packet = new C16PacketClientStatus(event.getPacket().getWrapObject());
            if (packet.getStatus() == C16State.PERFORM_RESPAWN) {
                ServerData serverData = mc.getCurrentServerData();
                EntityPlayerSP thePlayer = mc.getThePlayer();
                final String host = !serverData.isNull() ? serverData.getServerIP() : "localhost";
                WaypointManager.INSTANCE.getWaypointDataList().add(new WaypointData(host, "death-" + new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss").format(new Timestamp(System.currentTimeMillis())), thePlayer.getDimension(), thePlayer.getPosX(), thePlayer.getPosY() + thePlayer.getEyeHeight(), thePlayer.getPosZ()));
            }
        }
    }

    public enum Shape {
        CUBE, PYRAMID, DIAMOND, SPHERE
    }


    public static final class WaypointData {
        @Getter
        @Setter
        private String host;
        @Getter
        @Setter
        private String name;
        @Getter
        @Setter
        private int dimension;
        @Getter
        @Setter
        private double x;
        @Getter
        @Setter
        private double y;
        @Getter
        @Setter
        private double z;
        @Getter
        @Setter
        private int color;
        @Getter
        @Setter
        private long time;

        public WaypointData(String host, String name, int dimension, double x, double y, double z) {
            this.host = host;
            this.name = name;
            this.dimension = dimension;
            this.x = x;
            this.y = y;
            this.z = z;
            this.color = ColorUtils.getRandomColor();
            this.time = System.currentTimeMillis();
            if (ModuleManager.getModule(Waypoints.class).host == null) {
                ModuleManager.getModule(Waypoints.class).host = Minecraft.getMinecraft().getCurrentServerData().getServerIP();
            }
        }
    }
}
