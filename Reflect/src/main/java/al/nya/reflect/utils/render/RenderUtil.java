package al.nya.reflect.utils.render;

import al.nya.reflect.utils.SessionAyatar;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.Timer;
import al.nya.reflect.wrapper.wraps.wrapper.block.Block;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.gui.Gui;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import al.nya.reflect.wrapper.wraps.wrapper.render.*;
import al.nya.reflect.wrapper.wraps.wrapper.shader.Framebuffer;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.glu.Sphere;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;


public class RenderUtil {

    private final static Minecraft mc = Minecraft.getMinecraft();

    public static final double circleSteps = 30.0;
    public static double zDepth = 0.0D;


    private static final Map<Integer, Boolean> glCapMap = new HashMap<>();

    public static void resetCaps() {
        glCapMap.forEach(RenderUtil::setGlState);
    }

    public static void resetColor() {
        GlStateManager.color(1, 1, 1, 1);
    }

    public static void setAlphaLimit(float limit) {
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(GL_GREATER, (float) (limit * .01));
    }

    public static void drawGradientRect2(double x, double y, double width, double height, int startColor, int endColor) {
        drawGradientRect(x, y, x + width, y + height, startColor, endColor);
    }

    public static void drawRect2(double x, double y, double width, double height, int color) {
        RenderUtil.resetColor();
        GLUtil.setup2DRendering(() -> GLUtil.render(GL11.GL_QUADS, () -> {
            RenderUtil.color(color);
            GL11.glVertex2d(x, y);
            GL11.glVertex2d(x, y + height);
            GL11.glVertex2d(x + width, y + height);
            GL11.glVertex2d(x + width, y);
        }));
    }

    public static void disableStencil() {
        GL11.glDepthMask(true);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
    }

    public static void drawGradientRect(double left, double top, double right, double bottom, int startColor, int endColor) {
        float f = (float) (startColor >> 24 & 255) / 255.0F;
        float f1 = (float) (startColor >> 16 & 255) / 255.0F;
        float f2 = (float) (startColor >> 8 & 255) / 255.0F;
        float f3 = (float) (startColor & 255) / 255.0F;
        float f4 = (float) (endColor >> 24 & 255) / 255.0F;
        float f5 = (float) (endColor >> 16 & 255) / 255.0F;
        float f6 = (float) (endColor >> 8 & 255) / 255.0F;
        float f7 = (float) (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(right, top, 0f).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, top, 0f).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, bottom, 0f).color(f5, f6, f7, f4).endVertex();
        worldrenderer.pos(right, bottom, 0f).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static int reAlpha(int color, float alpha) {
        try {
            Color c = new Color(color);
            float r = ((float) 1 / 255) * c.getRed();
            float g = ((float) 1 / 255) * c.getGreen();
            float b = ((float) 1 / 255) * c.getBlue();
            return new Color(r, g, b, alpha).getRGB();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return color;
    }

    public static void color(Color color) {
        if (color == null)
            color = Color.white;
        color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
    }

    public static void color(int color) {
        color(color, (float) (color >> 24 & 255) / 255.0F);
    }

    public static void color(int color, float alpha) {
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        GlStateManager.color(r, g, b, alpha);
    }


    public static void color(final double red, final double green, final double blue, final double alpha) {
        GL11.glColor4d(red, green, blue, alpha);
    }

    public static void enableSmoothLine(float width) {
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2884);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glLineWidth(width);
    }

    public static void disableSmoothLine() {
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glDepthMask(true);
        GL11.glCullFace(1029);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
    public static void drawFilledCircle(final double x, final double y, final double r, final int c, final int id) {
        final float f = (c >> 24 & 0xFF) / 255.0f;
        final float f2 = (c >> 16 & 0xFF) / 255.0f;
        final float f3 = (c >> 8 & 0xFF) / 255.0f;
        final float f4 = (c & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(9);
        if (id == 1) {
            GL11.glVertex2d(x, y);
            for (int i = 0; i <= 90; ++i) {
                final double x2 = Math.sin(i * 3.141526 / 180.0) * r;
                final double y2 = Math.cos(i * 3.141526 / 180.0) * r;
                GL11.glVertex2d(x - x2, y - y2);
            }
        }
        else if (id == 2) {
            GL11.glVertex2d(x, y);
            for (int i = 90; i <= 180; ++i) {
                final double x2 = Math.sin(i * 3.141526 / 180.0) * r;
                final double y2 = Math.cos(i * 3.141526 / 180.0) * r;
                GL11.glVertex2d(x - x2, y - y2);
            }
        }
        else if (id == 3) {
            GL11.glVertex2d(x, y);
            for (int i = 270; i <= 360; ++i) {
                final double x2 = Math.sin(i * 3.141526 / 180.0) * r;
                final double y2 = Math.cos(i * 3.141526 / 180.0) * r;
                GL11.glVertex2d(x - x2, y - y2);
            }
        }
        else if (id == 4) {
            GL11.glVertex2d(x, y);
            for (int i = 180; i <= 270; ++i) {
                final double x2 = Math.sin(i * 3.141526 / 180.0) * r;
                final double y2 = Math.cos(i * 3.141526 / 180.0) * r;
                GL11.glVertex2d(x - x2, y - y2);
            }
        }
        else {
            for (int i = 0; i <= 360; ++i) {
                final double x2 = Math.sin(i * 3.141526 / 180.0) * r;
                final double y2 = Math.cos(i * 3.141526 / 180.0) * r;
                GL11.glVertex2f((float)(x - x2), (float)(y - y2));
            }
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    public static void enableGlCap(final int cap) {
        setGlCap(cap, true);
    }

    public static void enableGlCap(final int... caps) {
        for (final int cap : caps)
            setGlCap(cap, true);
    }

    public static void disableGlCap(final int cap) {
        setGlCap(cap, true);
    }

    public static void disableGlCap(final int... caps) {
        for (final int cap : caps)
            setGlCap(cap, false);
    }
    public static void glColor(final int red, final int green, final int blue, final int alpha) {
        glColor4f(red / 255F, green / 255F, blue / 255F, alpha / 255F);
    }
    public static void setGlState(final int cap, final boolean state) {
        if (state)
            glEnable(cap);
        else
            glDisable(cap);
    }
    public static void drawFilledCircle(final float xx, final float yy, final float radius, final int color) {
        int sections = 50;
        double dAngle = 2 * PI / sections;
        float x, y;
        drawCircle(xx,yy,radius,0,360,color);
        glPushAttrib(GL_ENABLE_BIT);

        glEnable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_LINE_SMOOTH);
        glBegin(GL_TRIANGLE_FAN);

        for (int i = 0; i < sections; i++) {
            x = (float) (radius * sin((i * dAngle)));
            y = (float) (radius * cos((i * dAngle)));

            glColor(color);
            glVertex2f(xx + x, yy + y);
        }

        glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        glEnd();

        glPopAttrib();
    }
    public static void drawCircle(float x, float y, float radius, int start, int end,int color) {
        glEnable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
        GlStateManager.tryBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
        glColor(color);

        glEnable(GL_LINE_SMOOTH);
        glLineWidth(2F);
        glBegin(GL_LINE_STRIP);
        for (float i = end; i >= start; i -= (360 / 90.0f)) {
            glVertex2f((float) (x + (cos(i * PI / 180) * (radius * 1.001F))), (float) (y + (sin(i * PI / 180) * (radius * 1.001F))));
        }
        glEnd();
        glDisable(GL_LINE_SMOOTH);

        glEnable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
    }
    public static void setGlCap(final int cap, final boolean state) {
        glCapMap.put(cap, glGetBoolean(cap));
        setGlState(cap, state);
    }
    public static void glColor(Color color, float alpha) {
        float[] colorComponents = color.getColorComponents(null);
        GlStateManager.color(colorComponents[0], colorComponents[1], colorComponents[2], alpha);
    }
    public static void glColor(int hex) {
        float alpha = (float)(hex >> 24 & 255) / 255.0f;
        float red = (float)(hex >> 16 & 255) / 255.0f;
        float green = (float)(hex >> 8 & 255) / 255.0f;
        float blue = (float)(hex & 255) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public static void glColor(Color color) {
       GL11.glColor4f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
    }

    public static void post3D() {
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
        GL11.glColor4f(1, 1, 1, 1);
    }

    public static void begin2D() {
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 1, 0);
    }

    public static void end2D() {
        GlStateManager.enableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    public static void begin3D() {
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableTexture2D();
        GlStateManager.disableDepth();
        //GlStateManager.disableLighting();
        GL11.glEnable(GL_LINE_SMOOTH);
        glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
    }

    public static void end3D() {
        GL11.glDisable(GL_LINE_SMOOTH);
        //GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
    }


    public static float smooth(float current, float end, float smoothSpeed, float minSpeed) {
        float movement = (end - current) * smoothSpeed;
        if (movement > 0) {
            movement = Math.max(minSpeed, movement);
            movement = Math.min(end - current, movement);
        } else if (movement < 0) {
            movement = Math.min(-minSpeed, movement);
            movement = Math.max(end - current, movement);
        }
        return current + movement;
    }
    
    public static double getEntityRenderX(Entity entity) {
        return entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * Minecraft.getMinecraft().getTimer().getRenderPartialTicks() - Minecraft.getMinecraft().getRenderManager().getRenderPosX();
    }
    public static double getEntityRenderY(Entity entity) {
        return entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * Minecraft.getMinecraft().getTimer().getRenderPartialTicks() - Minecraft.getMinecraft().getRenderManager().getRenderPosY();
    }
    public static double getEntityRenderZ(Entity entity) {
        return entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * Minecraft.getMinecraft().getTimer().getRenderPartialTicks() - Minecraft.getMinecraft().getRenderManager().getRenderPosZ();
    }
    public static void drawLine(final double x, final double y, final double x1, final double y1, final float width) {
        GL11.glDisable(GL_TEXTURE_2D);
        GL11.glLineWidth(width);
        GL11.glBegin(GL_LINES);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x1, y1);
        GL11.glEnd();
        GL11.glEnable(GL_TEXTURE_2D);
    }
    public static void drawLine(final float x, final float y, final float x1, final float y1, final float width,int color) {
        GL11.glDisable(GL_TEXTURE_2D);
        GL11.glLineWidth(width);
        GL11.glBegin(GL_LINES);
        glColor(color);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x1, y1);
        GL11.glEnd();
        GL11.glEnable(GL_TEXTURE_2D);
    }
    public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float)(col1 >> 24 & 255) / 255.0f;
        float f1 = (float)(col1 >> 16 & 255) / 255.0f;
        float f2 = (float)(col1 >> 8 & 255) / 255.0f;
        float f3 = (float)(col1 & 255) / 255.0f;
        float f4 = (float)(col2 >> 24 & 255) / 255.0f;
        float f5 = (float)(col2 >> 16 & 255) / 255.0f;
        float f6 = (float)(col2 >> 8 & 255) / 255.0f;
        float f7 = (float)(col2 & 255) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        GL11.glColor4f(f5, f6, f7, f4);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }
    
    public static void beginScissor(int x, int y, int width, int height) {
        final int scaleFactor = new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor();

        if (!GL11.glIsEnabled(GL11.GL_SCISSOR_TEST))
            GL11.glEnable(GL11.GL_SCISSOR_TEST);

        GL11.glScissor((x * scaleFactor), (Minecraft.getMinecraft().getDisplayHeight() - (y + height) * scaleFactor), (width * scaleFactor), (height * scaleFactor));
    }
    public static void drawSelectionBoundingBox(AxisAlignedBB boundingBox) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();

        worldrenderer.begin(GL_LINE_STRIP, DefaultVertexFormats.POSITION);
        double minX = boundingBox.getMinX();
        double maxX = boundingBox.getMaxX();
        double minY = boundingBox.getMinY();
        double maxY = boundingBox.getMaxY();
        double minZ = boundingBox.getMinZ();
        double maxZ = boundingBox.getMaxZ();
        // Lower Rectangle
        worldrenderer.pos(minX, minY, minZ).endVertex();
        worldrenderer.pos(minX, minY, maxZ).endVertex();
        worldrenderer.pos(maxX, minY, maxZ).endVertex();
        worldrenderer.pos(maxX, minY, minZ).endVertex();
        worldrenderer.pos(minX, minY, minZ).endVertex();

        // Upper Rectangle
        worldrenderer.pos(minX, maxY, minZ).endVertex();
        worldrenderer.pos(minX, maxY, maxZ).endVertex();
        worldrenderer.pos(maxX, maxY, maxZ).endVertex();
        worldrenderer.pos(maxX, maxY, minZ).endVertex();
        worldrenderer.pos(minX, maxY, minZ).endVertex();

        // Upper Rectangle
        worldrenderer.pos(minX, maxY, maxZ).endVertex();
        worldrenderer.pos(minX, minY, maxZ).endVertex();

        worldrenderer.pos(maxX, minY, maxZ).endVertex();
        worldrenderer.pos(maxX, maxY, maxZ).endVertex();

        worldrenderer.pos(maxX, maxY, minZ).endVertex();
        worldrenderer.pos(maxX, minY, minZ).endVertex();

        tessellator.draw();
    }

    public static void drawFilledBox(double x, double y, double z, AxisAlignedBB bb, int color) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        drawFilledBox(bb, color);
        GL11.glPopMatrix();
    }

    public static void drawFilledBox(AxisAlignedBB bb, int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0F;
        final float red = (color >> 16 & 0xFF) / 255.0F;
        final float green = (color >> 8 & 0xFF) / 255.0F;
        final float blue = (color & 0xFF) / 255.0F;


        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();

        double minX = bb.getMinX();
        double maxX = bb.getMaxX();
        double minY = bb.getMinY();
        double maxY = bb.getMaxY();
        double minZ = bb.getMinZ();
        double maxZ = bb.getMaxZ();

        worldRenderer.begin(GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        worldRenderer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();

        worldRenderer.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();

        worldRenderer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();

        worldRenderer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();

        worldRenderer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();

        worldRenderer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
    }

    public static void drawFilledBox(final AxisAlignedBB axisAlignedBB) {
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();

        double minX = axisAlignedBB.getMinX();
        double maxX = axisAlignedBB.getMaxX();
        double minY = axisAlignedBB.getMinY();
        double maxY = axisAlignedBB.getMaxY();
        double minZ = axisAlignedBB.getMinZ();
        double maxZ = axisAlignedBB.getMaxZ();

        worldRenderer.begin(7, DefaultVertexFormats.POSITION);

        worldRenderer.pos(minX, minY, minZ).endVertex();
        worldRenderer.pos(minX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, minZ).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).endVertex();
        worldRenderer.pos(minX, minY, maxZ).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).endVertex();

        worldRenderer.pos(maxX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, minZ).endVertex();
        worldRenderer.pos(minX, maxY, minZ).endVertex();
        worldRenderer.pos(minX, minY, minZ).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).endVertex();
        worldRenderer.pos(minX, minY, maxZ).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).endVertex();

        worldRenderer.pos(minX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).endVertex();
        worldRenderer.pos(minX, maxY, minZ).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).endVertex();

        worldRenderer.pos(minX, minY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).endVertex();
        worldRenderer.pos(minX, minY, maxZ).endVertex();
        worldRenderer.pos(minX, minY, minZ).endVertex();
        worldRenderer.pos(minX, minY, maxZ).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).endVertex();
        worldRenderer.pos(maxX, minY, minZ).endVertex();

        worldRenderer.pos(minX, minY, minZ).endVertex();
        worldRenderer.pos(minX, maxY, minZ).endVertex();
        worldRenderer.pos(minX, minY, maxZ).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).endVertex();
        worldRenderer.pos(maxX, minY, minZ).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).endVertex();

        worldRenderer.pos(minX, maxY, maxZ).endVertex();
        worldRenderer.pos(minX, minY, maxZ).endVertex();
        worldRenderer.pos(minX, maxY, minZ).endVertex();
        worldRenderer.pos(minX, minY, minZ).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, minZ).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).endVertex();
        tessellator.draw();
    }

    public static void endScissor() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static void drawRect(double x, double y, double w, double h) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Tessellator tes = Tessellator.getInstance();
        WorldRenderer worlderRenderer = tes.getWorldRenderer();
        worlderRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worlderRenderer.pos(x + w, y, 0.0D).endVertex();
        worlderRenderer.pos(x, y, 0.0D).endVertex();
        worlderRenderer.pos(x, y + h, 0.0D).endVertex();
        worlderRenderer.pos(x + w, y + h, 0.0D).endVertex();
        tes.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {

        if (left > right) {
            final double cacheRight = right;
            right = left;
            left = cacheRight;
        }

        if (top > bottom) {
            final double cacheBottom = bottom;
            bottom = top;
            top = cacheBottom;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0).endVertex();
        worldrenderer.pos(right, bottom, 0).endVertex();
        worldrenderer.pos(right, top, 0).endVertex();
        worldrenderer.pos(left, top, 0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawFastRoundedRect(float left, float top, float right, float bottom, float radius, int color) {
        RoundedUtil.drawRound(left, top, right - left, bottom - top, radius, new Color(color));
//		final int Semicircle = 18;
//		final float f = 90.0f / Semicircle;
//		final float f2 = (color >> 24 & 0xFF) / 255.0f;
//		final float f3 = (color >> 16 & 0xFF) / 255.0f;
//		final float f4 = (color >> 8 & 0xFF) / 255.0f;
//		final float f5 = (color & 0xFF) / 255.0f;
//		GL11.glDisable(2884);
//		GL11.glDisable(3553);
//		GL11.glEnable(3042);
//		GL11.glBlendFunc(770, 771);
//		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
//		GL11.glColor4f(f3, f4, f5, f2);
//		GL11.glBegin(5);
//		GL11.glVertex2f(left + radius, top);
//		GL11.glVertex2f(left + radius, bottom);
//		GL11.glVertex2f(right - radius, top);
//		GL11.glVertex2f(right - radius, bottom);
//		GL11.glEnd();
//		GL11.glBegin(5);
//		GL11.glVertex2f(left, top + radius);
//		GL11.glVertex2f(left + radius, top + radius);
//		GL11.glVertex2f(left, bottom - radius);
//		GL11.glVertex2f(left + radius, bottom - radius);
//		GL11.glEnd();
//		GL11.glBegin(5);
//		GL11.glVertex2f(right, top + radius);
//		GL11.glVertex2f(right - radius, top + radius);
//		GL11.glVertex2f(right, bottom - radius);
//		GL11.glVertex2f(right - radius, bottom - radius);
//		GL11.glEnd();
//		GL11.glBegin(6);
//		float f6 = right - radius;
//		float f7 = top + radius;
//		GL11.glVertex2f(f6, f7);
//		int j = 0;
//		for (j = 0; j <= Semicircle; ++j) {
//			final float f8 = j * f;
//			GL11.glVertex2f((float)(f6 + radius * cos(Math.toRadians(f8))), (float)(f7 - radius * sin(Math.toRadians(f8))));
//		}
//		GL11.glEnd();
//		GL11.glBegin(6);
//		f6 = left + radius;
//		f7 = top + radius;
//		GL11.glVertex2f(f6, f7);
//		for (j = 0; j <= Semicircle; ++j) {
//			final float f9 = j * f;
//			GL11.glVertex2f((float)(f6 - radius * cos(Math.toRadians(f9))), (float)(f7 - radius * sin(Math.toRadians(f9))));
//		}
//		GL11.glEnd();
//		GL11.glBegin(6);
//		f6 = left + radius;
//		f7 = bottom - radius;
//		GL11.glVertex2f(f6, f7);
//		for (j = 0; j <= Semicircle; ++j) {
//			final float f10 = j * f;
//			GL11.glVertex2f((float)(f6 - radius * cos(Math.toRadians(f10))), (float)(f7 + radius * sin(Math.toRadians(f10))));
//		}
//		GL11.glEnd();
//		GL11.glBegin(6);
//		f6 = right - radius;
//		f7 = bottom - radius;
//		GL11.glVertex2f(f6, f7);
//		for (j = 0; j <= Semicircle; ++j) {
//			final float f11 = j * f;
//			GL11.glVertex2f((float)(f6 + radius * cos(Math.toRadians(f11))), (float)(f7 + radius * sin(Math.toRadians(f11))));
//		}
//		GlStateManager.resetColor();
//		GL11.glEnd();
//		GL11.glEnable(3553);
//		GL11.glEnable(2884);
//		GL11.glDisable(3042);
//		GlStateManager.enableTexture2D();
//		GlStateManager.disableBlend();
    }
    
    public static void drawSessionHead(float x, float y, int width, int height, int color) {
    	if (SessionAyatar.getAvatar() == null) return;
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        GlStateManager.color(f, f1, f2, f3);
        GlStateManager.bindTexture(SessionAyatar.getDynamicAvatar().getGlTextureId());
        drawQuad(x, y, width, height, 0, 0, SessionAyatar.getAvatar().getWidth(), SessionAyatar.getAvatar().getWidth(), SessionAyatar.getAvatar().getHeight());
        GlStateManager.resetColor();
        GlStateManager.bindTexture(0);
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
    }
    
    public static void drawQuad(float x, float y, float width, float height, float srcX, float srcY, int imgSize, float srcWidth, float srcHeight) {
        float renderSRCX = srcX / imgSize;
        float renderSRCY = srcY / imgSize;
        float renderSRCWidth = srcWidth / imgSize;
        float renderSRCHeight = srcHeight / imgSize;
        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d(x + width, y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY);
        GL11.glVertex2d(x, y);
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x, y + height);
        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x, y + height);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
        GL11.glVertex2d(x + width, y + height);
        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
        GL11.glVertex2d(x + width, y);
        GL11.glEnd();
    }
    
    public static void drawVerticalGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float) (col1 >> 24 & 255) / 255.0F;
        float f1 = (float) (col1 >> 16 & 255) / 255.0F;
        float f2 = (float) (col1 >> 8 & 255) / 255.0F;
        float f3 = (float) (col1 & 255) / 255.0F;
        float f4 = (float) (col2 >> 24 & 255) / 255.0F;
        float f5 = (float) (col2 >> 16 & 255) / 255.0F;
        float f6 = (float) (col2 >> 8 & 255) / 255.0F;
        float f7 = (float) (col2 & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer world = tessellator.getWorldRenderer();
        world.begin(7, DefaultVertexFormats.POSITION_COLOR);
        world.pos(right, top, 0).color(f1, f2, f3, f).endVertex();
        world.pos(left, top, 0).color(f1, f2, f3, f).endVertex();
        world.pos(left, bottom, 0).color(f5, f6, f7, f4).endVertex();
        world.pos(right, bottom, 0).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawHorizontalGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float) (col1 >> 24 & 255) / 255.0F;
        float f1 = (float) (col1 >> 16 & 255) / 255.0F;
        float f2 = (float) (col1 >> 8 & 255) / 255.0F;
        float f3 = (float) (col1 & 255) / 255.0F;
        float f4 = (float) (col2 >> 24 & 255) / 255.0F;
        float f5 = (float) (col2 >> 16 & 255) / 255.0F;
        float f6 = (float) (col2 >> 8 & 255) / 255.0F;
        float f7 = (float) (col2 & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer world = tessellator.getWorldRenderer();
        world.begin(7, DefaultVertexFormats.POSITION_COLOR);
        world.pos(left, top, 0).color(f1, f2, f3, f).endVertex();
        world.pos(left, bottom, 0).color(f1, f2, f3, f).endVertex();
        world.pos(right, bottom, 0).color(f5, f6, f7, f4).endVertex();
        world.pos(right, top, 0).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawImage(ResourceLocation image, int x, int y, int width, int height) {
        drawImage(image, x, y, width, height, 1.0f);
    }

    public static void drawImage(ResourceLocation image, int x, int y, int width, int height, float alpha) {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, alpha);
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height,
                (float) width, (float) height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }

    public static void drawImageAntiAlias(ResourceLocation image, int x, int y, int width, int height) {
        drawImageAntiAlias(image, x, y, width, height, 1.0f);
    }

    public static void drawImageAntiAlias(ResourceLocation image, int x, int y, int width, int height, float alpha) {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glEnable(GL13.GL_SAMPLE_ALPHA_TO_COVERAGE);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, alpha);
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height,
                (float) width, (float) height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glDisable(GL13.GL_SAMPLE_ALPHA_TO_COVERAGE);
        GL11.glEnable(2929);
    }

    public static void drawImage(ResourceLocation image, int x, int y, int width, int height, Color color) {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f((float) color.getRed() / 255.0f, color.getBlue() / 255.0f,
                (float) color.getRed() / 255.0f, 1.0f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height,
                (float) width, (float) height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }

    public static void targethuddrawImage(ResourceLocation image, float x, float y, int width, int height) {
    	GlStateManager.enableAlpha();
        GL11.glDisable(2929);
       GL11.glEnable(3042);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }
    public static void drawCircle(double x, double y, double radius, int color) {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        float alpha = (color >> 24 & 255) / 255F;
        float red = (color >> 16 & 255) / 255F;
        float green = (color >> 8 & 255) / 255F;
        float blue = (color & 255) / 255F;
        GlStateManager.color(red, green, blue, alpha);
        GL11.glEnable(GL11.GL_POLYGON_SMOOTH);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
        GL11.glEnable(GL11.GL_POINT_SMOOTH);
        final int vertices = (int) Math.min(Math.max(radius, 45F), 360F);
        GL11.glBegin(9);
        for (int ii = 0; ii < 2; ii++) {
            for (int i = 0; i < vertices; ++i) {
                final double angleRadians = PI * 2 * (double) i / (double) vertices;
                GL11.glVertex2d(x + sin(angleRadians) * radius, y + cos(angleRadians) * radius);
            }
        }
        GL11.glEnd();
        GL11.glDisable(GL11.GL_POINT_SMOOTH);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_POLYGON_SMOOTH);
        GlStateManager.resetColor();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static void drawCircle(double x, double y, double r) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Tessellator tes = Tessellator.getInstance();
//        tes.startDrawing(GL11.GL_TRIANGLE_FAN);
        WorldRenderer worlderRenderer = tes.getWorldRenderer();
        worlderRenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
        worlderRenderer.pos(x, y, zDepth);
        // for some the circle is only drawn if theta is decreasing rather than ascending
        double end = Math.PI * 2.0;
        double incr = end / circleSteps;
        for (double theta = -incr; theta < end; theta += incr) {
            worlderRenderer.pos(x + (r * Math.cos(-theta)), y + (r * Math.sin(-theta)), zDepth).endVertex();
        }
        tes.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static boolean isHovered(float x, float y, float right, float bottom, int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= right && mouseY >= y && mouseY <= bottom;
    }

    public static void drawBlockBox(final BlockPos blockPos, final Color color, final boolean outline) {
        Minecraft mc = Minecraft.getMinecraft();
        final RenderManager renderManager = mc.getRenderManager();
        final Timer timer = mc.getTimer();

        final double x = blockPos.getX() - renderManager.getRenderPosX();
        final double y = blockPos.getY() - renderManager.getRenderPosY();
        final double z = blockPos.getZ() - renderManager.getRenderPosZ();

        AxisAlignedBB axisAlignedBB = AxisAlignedBB.fromBounds(x, y, z, x + 1.0, y + 1.0, z + 1.0);
        final Block block = mc.getTheWorld().getBlockState(blockPos).getBlock();

        if (block != null) {
            final EntityPlayer player = mc.getThePlayer();
            float renderPartialTicks = timer.getRenderPartialTicks();
            final double posX = player.getLastTickPosX() + (player.getPosX() - player.getLastTickPosX()) * (double) renderPartialTicks;
            final double posY = player.getLastTickPosY() + (player.getPosY() - player.getLastTickPosY()) * (double) renderPartialTicks;
            final double posZ = player.getLastTickPosZ() + (player.getPosZ() - player.getLastTickPosZ()) * (double) renderPartialTicks;

            block.setBlockBoundsBasedOnState(mc.getTheWorld(), blockPos);

            axisAlignedBB = block.getSelectedBoundingBox(mc.getTheWorld(), blockPos)
                    .expand(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D)
                    .offset(-posX, -posY, -posZ);
        }

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        enableGlCap(GL_BLEND);
        disableGlCap(GL_TEXTURE_2D, GL_DEPTH_TEST);
        glDepthMask(false);

        glColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha() != 255 ? color.getAlpha() : outline ? 26 : 35);
        drawFilledBox(axisAlignedBB);

        if (outline) {
            glLineWidth(1F);
            enableGlCap(GL_LINE_SMOOTH);
            glColor(color);

            drawSelectionBoundingBox(axisAlignedBB);
        }

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        glDepthMask(true);
        resetCaps();
    }

    public static void drawPlatform(final Entity entity, final Color color) {
        Minecraft mc = Minecraft.getMinecraft();
        final RenderManager renderManager = mc.getRenderManager();
        final Timer timer = mc.getTimer();
        float renderPartialTicks = timer.getRenderPartialTicks();
        double posX = entity.getPosX();
        double posY = entity.getPosZ();
        double posZ = entity.getPosZ();
        double lastTickPosX = entity.getLastTickPosX();
        double lastTickPosY = entity.getLastTickPosY();
        double lastTickPosZ = entity.getLastTickPosZ();
        final double x = lastTickPosX + (posX - lastTickPosX) * renderPartialTicks
                - renderManager.getRenderPosX();
        final double y = lastTickPosY + (posY - lastTickPosY) * renderPartialTicks
                - renderManager.getRenderPosY();
        final double z = lastTickPosZ + (posZ - lastTickPosZ) * renderPartialTicks
                - renderManager.getRenderPosZ();

        final AxisAlignedBB axisAlignedBB = entity.getEntityBoundingBox()
                .offset(-posX, -posY, -posZ)
                .offset(x, y, z);

        drawAxisAlignedBB(
                AxisAlignedBB.fromBounds(axisAlignedBB.getMinX(), axisAlignedBB.getMaxY() + 0.2, axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), axisAlignedBB.getMaxY() + 0.26, axisAlignedBB.getMaxZ()),
                color
        );
    }
    public static void drawAxisAlignedBB(final AxisAlignedBB axisAlignedBB, final Color color) {
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_BLEND);
        glLineWidth(2F);
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);
        glDepthMask(false);
        glColor(color);
        drawFilledBox(axisAlignedBB);
        glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        glDepthMask(true);
        glDisable(GL_BLEND);
    }

    public static void prepareScissorBox(float x, float y, float x2, float y2) {
        ScaledResolution scale = new ScaledResolution(Minecraft.getMinecraft());
        int factor = scale.getScaleFactor();
        GL11.glScissor((int) (x * (float) factor), (int) (((float) scale.getScaledHeight() - y2) * (float) factor), (int) ((x2 - x) * (float) factor), (int) ((y2 - y) * (float) factor));
    }

    public static void drawEntityOnScreen(final int posX, final int posY, final int scale, final EntityLivingBase entity) {
        GlStateManager.pushMatrix();
        GlStateManager.enableColorMaterial();

        GlStateManager.translate(posX, posY, 50.0);
        GlStateManager.scale((-scale), scale, scale);
        GlStateManager.rotate(180F, 0F, 0F, 1F);
        GlStateManager.rotate(135F, 0F, 1F, 0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135F, 0F, 1F, 0F);
        GlStateManager.translate(0.0, 0.0, 0.0);

        float renderYawOffset = entity.getRenderYawOffset();
        float rotationYaw = entity.getRotationYaw();
        float rotationPitch = entity.getRotationPitch();
        float prevRotationYawHead = entity.getPrevRotationYawHead();
        float rotationYawHead = entity.getRotationYawHead();


        entity.setRenderYawOffset(0);
        entity.setRotationYaw(0);
        entity.setRotationPitch(90);
        entity.setRotationYawHead(entity.getRotationYaw());
        entity.setPrevRotationYawHead(entity.getRotationYaw());

        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        rendermanager.setRenderShadow(true);

        entity.setRenderYawOffset(renderYawOffset);
        entity.setRotationYawHead(rotationYaw);
        entity.setRotationPitch(rotationPitch);
        entity.setPrevRotationYawHead(prevRotationYawHead);
        entity.setRotationYawHead(rotationYawHead);

        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit());
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit());
    }
    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent) {
        drawEntityOnScreen(posX, posY, scale, mouseX, mouseY, ent, 135);
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent, float rotate) {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) posX, (float) posY, 50.0F);
        GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.getRenderYawOffset();
        float f1 = ent.getRotationYaw();
        float f2 = ent.getRotationPitch();
        float f3 = ent.getPrevRotationYawHead();
        float f4 = ent.getRotationYawHead();
        GlStateManager.rotate(rotate, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float) Math.atan(mouseY / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.setRenderYawOffset((float) Math.atan(mouseX / 40.0F) * 20.0F);
        ent.setRotationYaw( (float) Math.atan(mouseX / 40.0F) * 40.0F);
        ent.setRotationPitch(-((float) Math.atan(mouseY / 40.0F)) * 20.0F);
        ent.setRotationYawHead(ent.getRotationYaw());
        ent.setPrevRotationYawHead(ent.getRotationYaw());
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        rendermanager.setRenderShadow(true);
        ent.setRenderYawOffset(f);
        ent.setRotationYaw(f1);
        ent.setRotationPitch(f2);
        ent.setPrevRotationYawHead(f3);
        ent.setRotationYawHead(f4);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit());
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit());
    }

    public static void drawBorderedRect(final float x, final float y, final float x2, final float y2, final float l1,
                                        final int col1, final int col2) {
        drawRect(x, y, x2, y2, col2);
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }

    public static void drawBoundingBox(AxisAlignedBB bb, float width, int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0F;
        final float red = (color >> 16 & 0xFF) / 255.0F;
        final float green = (color >> 8 & 0xFF) / 255.0F;
        final float blue = (color & 0xFF) / 255.0F;
        drawBoundingBox(bb, width, red, green, blue, alpha);
    }

    public static void drawBoundingBox(double x, double y, double z, AxisAlignedBB bb, float width, int color) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        drawBoundingBox(bb, width, color);
        GL11.glPopMatrix();
    }

    public static void drawFilledPyramid(AxisAlignedBB bb, int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0F;
        final float red = (color >> 16 & 0xFF) / 255.0F;
        final float green = (color >> 8 & 0xFF) / 255.0F;
        final float blue = (color & 0xFF) / 255.0F;

        final Tessellator tessellator = Tessellator.getInstance();

        GlStateManager.disableCull();

        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();

        double minX = bb.getMinX();
        double maxX = bb.getMaxX();
        double minY = bb.getMinY();
        double maxY = bb.getMaxY();
        double minZ = bb.getMinZ();
        double maxZ = bb.getMaxZ();

        worldRenderer.begin(GL_TRIANGLES, DefaultVertexFormats.POSITION_COLOR);

        worldRenderer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex(); // 1
        worldRenderer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();

        worldRenderer.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex(); // 2
        worldRenderer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();

        worldRenderer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex(); // 3
        worldRenderer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(0, maxY, 0).color(red, green, blue, alpha).endVertex();

        worldRenderer.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex(); // 4
        worldRenderer.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(0, maxY, 0).color(red, green, blue, alpha).endVertex();

        worldRenderer.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex(); // 5
        worldRenderer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(0, maxY, 0).color(red, green, blue, alpha).endVertex();

        worldRenderer.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex(); // 6
        worldRenderer.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        worldRenderer.pos(0, maxY, 0).color(red, green, blue, alpha).endVertex();

        tessellator.draw();
    }

    public static void drawBoundingBoxPyramid(AxisAlignedBB bb, float width, int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0F;
        final float red = (color >> 16 & 0xFF) / 255.0F;
        final float green = (color >> 8 & 0xFF) / 255.0F;
        final float blue = (color & 0xFF) / 255.0F;

        glLineWidth(width);

        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer bufferbuilder = tessellator.getWorldRenderer();

        double minX = bb.getMinX();
        double maxX = bb.getMaxX();
        double minY = bb.getMinY();
        double maxY = bb.getMaxY();
        double minZ = bb.getMinZ();
        double maxZ = bb.getMaxZ();

        bufferbuilder.begin(GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(minX, minY, minZ).color(red, green, blue, 0.0F).endVertex();
        bufferbuilder.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(0, maxY, 0).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(0, maxY, 0).color(red, green, blue, 0.0F).endVertex();
        bufferbuilder.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(0, maxY, 0).color(red, green, blue, 0.0F).endVertex();
        bufferbuilder.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(0, maxY, 0).color(red, green, blue, 0.0F).endVertex();

        tessellator.draw();
    }

    public static void drawSphere(float radius, int slices, int stacks, int color) {
        glColor(color);
        new Sphere().draw(radius, slices, stacks);
    }

    public static void drawFilledDiamond(AxisAlignedBB bb, float yOffset, float extraY, int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0F;
        final float red = (color >> 16 & 0xFF) / 255.0F;
        final float green = (color >> 8 & 0xFF) / 255.0F;
        final float blue = (color & 0xFF) / 255.0F;

        double minX = bb.getMinX();
        double maxX = bb.getMaxX();
        double minY = bb.getMinY();
        double maxY = bb.getMaxY();
        double minZ = bb.getMinZ();
        double maxZ = bb.getMaxZ();


        final Tessellator tessellator = Tessellator.getInstance();

        GlStateManager.disableCull();

        final WorldRenderer bufferbuilder = tessellator.getWorldRenderer();

        bufferbuilder.begin(GL_TRIANGLES, DefaultVertexFormats.POSITION_COLOR);

        // Top Half

        bufferbuilder.pos(minX, yOffset, minZ).color(red, green, blue, alpha).endVertex(); // 1
        bufferbuilder.pos(minX, yOffset, maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(0, maxY + extraY, 0).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(minX, yOffset, maxZ).color(red, green, blue, alpha).endVertex(); // 2
        bufferbuilder.pos(maxX, yOffset, maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(0, maxY + extraY, 0).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(maxX, yOffset, maxZ).color(red, green, blue, alpha).endVertex(); // 3
        bufferbuilder.pos(maxX, yOffset, minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(0, maxY + extraY, 0).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(maxX, yOffset, minZ).color(red, green, blue, alpha).endVertex(); // 4
        bufferbuilder.pos(minX, yOffset, minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(0, maxY + extraY, 0).color(red, green, blue, alpha).endVertex();

        // Bottom Half

        bufferbuilder.pos(minX, yOffset, minZ).color(red, green, blue, alpha).endVertex(); // 5
        bufferbuilder.pos(minX, yOffset, maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(0, minY - extraY, 0).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(minX, yOffset, maxZ).color(red, green, blue, alpha).endVertex(); // 6
        bufferbuilder.pos(maxX, yOffset, maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(0, minY - extraY, 0).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(maxX, yOffset, maxZ).color(red, green, blue, alpha).endVertex(); // 7
        bufferbuilder.pos(maxX, yOffset, minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(0, minY - extraY, 0).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(maxX, yOffset, minZ).color(red, green, blue, alpha).endVertex(); // 8
        bufferbuilder.pos(minX, yOffset, minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(0, minY - extraY, 0).color(red, green, blue, alpha).endVertex();

        tessellator.draw();
    }

    public static void drawBoundingBoxDiamond(AxisAlignedBB bb, float width, float yOffset, float extraY, int color) {
        final float alpha = (color >> 24 & 0xFF) / 255.0F;
        final float red = (color >> 16 & 0xFF) / 255.0F;
        final float green = (color >> 8 & 0xFF) / 255.0F;
        final float blue = (color & 0xFF) / 255.0F;

        glLineWidth(width);

        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer bufferbuilder = tessellator.getWorldRenderer();

        double minX = bb.getMinX();
        double maxX = bb.getMaxX();
        double minY = bb.getMinY();
        double maxY = bb.getMaxY();
        double minZ = bb.getMinZ();
        double maxZ = bb.getMaxZ();

        bufferbuilder.begin(GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);

        // Top Half

        bufferbuilder.pos(minX, yOffset, minZ).color(red, green, blue, 0).endVertex();

        bufferbuilder.pos(minX, yOffset, minZ).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(maxX, yOffset, minZ).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(maxX, yOffset, maxZ).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(minX, yOffset, maxZ).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(minX, yOffset, minZ).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(0, maxY + extraY, 0).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(maxX, yOffset, minZ).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(0, maxY + extraY, 0).color(red, green, blue, 0).endVertex();

        bufferbuilder.pos(maxX, yOffset, maxZ).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(0, maxY + extraY, 0).color(red, green, blue, 0).endVertex();

        bufferbuilder.pos(minX, yOffset, maxZ).color(red, green, blue, alpha).endVertex();

        // Bottom Half

        bufferbuilder.pos(0, minY - extraY, 0).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(minX, yOffset, minZ).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(0, minY - extraY, 0).color(red, green, blue, 0).endVertex();

        bufferbuilder.pos(maxX, yOffset, minZ).color(red, green, blue, alpha).endVertex();

        bufferbuilder.pos(0, minY - extraY, 0).color(red, green, blue, 0).endVertex();

        bufferbuilder.pos(maxX, yOffset, maxZ).color(red, green, blue, alpha).endVertex();

        tessellator.draw();
    }

    public static void drawBoundingBox(AxisAlignedBB bb, float width, float red, float green, float blue, float alpha) {
        glLineWidth(width);

        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRender = tessellator.getWorldRenderer();

        double minX = bb.getMinX();
        double maxX = bb.getMaxX();
        double minY = bb.getMinY();
        double maxY = bb.getMaxY();
        double minZ = bb.getMinZ();
        double maxZ = bb.getMaxZ();

        worldRender.begin(GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        worldRender.pos(minX, minY, minZ).color(red, green, blue, 0.0F).endVertex();
        worldRender.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        worldRender.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        worldRender.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRender.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRender.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
        worldRender.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        worldRender.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        worldRender.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRender.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRender.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        worldRender.pos(minX, maxY, maxZ).color(red, green, blue, 0.0F).endVertex();
        worldRender.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRender.pos(maxX, maxY, maxZ).color(red, green, blue, 0.0F).endVertex();
        worldRender.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
        worldRender.pos(maxX, maxY, minZ).color(red, green, blue, 0.0F).endVertex();
        worldRender.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
        worldRender.pos(maxX, minY, minZ).color(red, green, blue, 0.0F).endVertex();
        tessellator.draw();
    }

    public static void setColourWithAlphaPercent(int colour, int alphaPercent) {
        setColour(((((alphaPercent * 0xff) / 100) & 0xff) << 24) | (colour & 0xffffff));
    }

    public static void setColour(int colour) {
        GlStateManager.color((float) ((colour >> 16) & 0xff) / 255.0f,
                (float) ((colour >> 8) & 0xff) / 255.0f,
                (float) ((colour) & 0xff) / 255.0f,
                (float) ((colour >> 24) & 0xff) / 255.0f);

    }

    public static Framebuffer createFrameBuffer(Framebuffer framebuffer) {
        if (framebuffer == null || framebuffer.getFramebufferWidth() != mc.getDisplayWidth() || framebuffer.getFramebufferHeight() != mc.getDisplayHeight()) {
            if (framebuffer != null) {
                framebuffer.deleteFramebuffer();
            }
            return new Framebuffer(mc.getDisplayWidth(), mc.getDisplayHeight(), true);
        }
        return framebuffer;
    }

    public static void bindTexture(int texture) {
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    public static void setRectStencil(double x, double y, double w, double h) {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        // disable drawing to the color buffer.
        // circle will only be drawn to depth buffer.
        GL11.glColorMask(false, false, false, false);
        // enable writing to depth buffer
        GL11.glDepthMask(true);

        // Clearing the depth buffer causes problems with shader mods.
        // I guess we just have to hope that the rest of the depth buffer
        // contains z values greater than 2000 at this stage in the frame
        // render.
        // It would be much easier to use the stencil buffer instead, but it is
        // not specifically requested in the Minecraft LWJGL display setup code.
        // So the stencil buffer is only available on GL implementations that
        // set it up by default.

        // clear depth buffer to z = 3000.0
        //GL11.glClearDepth(3000.0);
        //GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);

        // always write to depth buffer
        GL11.glDepthFunc(GL11.GL_ALWAYS);

        // draw stencil pattern (filled circle at z = 1000.0)
        RenderUtil.setColour(0xffffffff);
        RenderUtil.zDepth = 1000.0;
        RenderUtil.drawRect(x, y, w, h);
        RenderUtil.zDepth = 0.0;

        // re-enable drawing to colour buffer
        GL11.glColorMask(true, true, true, true);
        // disable drawing to depth buffer
        GL11.glDepthMask(false);
        // only draw pixels with z values that are greater
        // than the value in the depth buffer.
        // The overlay is drawn at 2000 so this will pass inside
        // the circle (2000 > 1000) but not outside (2000 <= 3000).
        GL11.glDepthFunc(GL11.GL_GREATER);
    }


    public static class GuiRender {
        public static void drawBorderedRect(float x, float y, float width, float height, float borderWidth, Color rectColor, Color borderColor) {
            drawBorderedRect(x, y, width, height, borderWidth, rectColor.getRGB(), borderColor.getRGB());
        }

        public static void drawBorderedRect(float x, float y, float width, float height, float borderWidth, int rectColor, int borderColor) {
            drawRect(x + borderWidth, y + borderWidth, width - borderWidth * 2.0F, height - borderWidth * 2.0F, rectColor);
            drawRect(x, y, width, borderWidth, borderColor);
            drawRect(x, y + borderWidth, borderWidth, height - borderWidth, borderColor);
            drawRect(x + width - borderWidth, y + borderWidth, borderWidth, height - borderWidth, borderColor);
            drawRect(x + borderWidth, y + height - borderWidth, width - borderWidth * 2.0F, borderWidth, borderColor);
        }

        public static void drawBorder(float x, float y, float width, float height, float borderWidth, int borderColor) {
            drawRect(x + borderWidth, y + borderWidth, width - borderWidth * 2.0F, borderWidth, borderColor);
            drawRect(x, y + borderWidth, borderWidth, height - borderWidth, borderColor);
            drawRect(x + width - borderWidth, y + borderWidth, borderWidth, height - borderWidth, borderColor);
            drawRect(x + borderWidth, y + height - borderWidth, width - borderWidth * 2.0F, borderWidth, borderColor);
        }

        public static void drawRect(float x, float y, float width, float height, Color color) {
            drawRect(x, y, width, height, color.getRGB());
        }

        public static void drawRect(float x, float y, float width, float height, int color) {
            enableRender2D();
            setColor(color);
            GL11.glBegin(7);
            GL11.glVertex2d(x, y);
            GL11.glVertex2d(x + width, y);
            GL11.glVertex2d(x + width, y + height);
            GL11.glVertex2d(x, y + height);
            GL11.glEnd();
            disableRender2D();
        }
        public static void setColor(int colorHex) {
            float alpha = (float) (colorHex >> 24 & 255) / 255.0F;
            float red = (float) (colorHex >> 16 & 255) / 255.0F;
            float green = (float) (colorHex >> 8 & 255) / 255.0F;
            float blue = (float) (colorHex & 255) / 255.0F;
            GL11.glColor4f(red, green, blue, alpha);
        }
        public static void disableRender3D(boolean enableDepth) {
            if (enableDepth) {
                GL11.glDepthMask(true);
                GL11.glEnable(2929);
            }

            GL11.glEnable(3553);
            GL11.glDisable(3042);
            GL11.glEnable(3008);
            GL11.glDisable(2848);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }

        public static void enableRender2D() {
            GL11.glEnable(3042);
            GL11.glDisable(2884);
            GL11.glDisable(3553);
            GL11.glEnable(2848);
            GL11.glBlendFunc(770, 771);
            GL11.glLineWidth(1.0F);
        }

        public static void disableRender2D() {
            GL11.glDisable(3042);
            GL11.glEnable(2884);
            GL11.glEnable(3553);
            GL11.glDisable(2848);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.shadeModel(7424);
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
        }
    }
}
