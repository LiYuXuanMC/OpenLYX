package com.reflectmc.reflect.utils.render;

import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityLivingBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.Gui;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.ScaledResolution;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.render.*;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.AxisAlignedBB;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;

public class RenderUtil {
    private static final Map<Integer, Boolean> glCapMap = new HashMap<>();

    public static void resetColor() {
        GlStateManager.color(1, 1, 1, 1);
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
    public static void drawRectFast(double x, double y, double width, double height, int color) {
        RenderUtil.resetColor();
        GLUtil.setup2DRendering(() -> GLUtil.render(GL11.GL_QUADS, () -> {
            RenderUtil.color(color);
            GL11.glVertex2d(x, y);
            GL11.glVertex2d(x, y + height);
            GL11.glVertex2d(x + width, y + height);
            GL11.glVertex2d(x + width, y);
        }));
    }
    public static void drawImage(ResourceLocation image, int x, int y, int width, int height) {
        drawImage(image, x, y, width, height, 1.0f);
    }
    public static void resetCaps() {
        glCapMap.forEach(RenderUtil::setGlState);
    }
    public static void setGlCap(final int cap, final boolean state) {
        glCapMap.put(cap, glGetBoolean(cap));
        setGlState(cap, state);
    }
    public static void glColor(Color color, float alpha) {
        float[] colorComponents = color.getColorComponents(null);
        GlStateManager.color(colorComponents[0], colorComponents[1], colorComponents[2], alpha);
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
    public static boolean isHovered(float x, float y, float right, float bottom, int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= right && mouseY >= y && mouseY <= bottom;
    }
    public static void glColor(final Color color) {
        final float red = color.getRed() / 255F;
        final float green = color.getGreen() / 255F;
        final float blue = color.getBlue() / 255F;
        final float alpha = color.getAlpha() / 255F;

        GlStateManager.color(red, green, blue, alpha);
    }
    public static void drawCircle(float x, float y, float radius, int start, int end,Color color) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
        glColor(color);

        glEnable(GL_LINE_SMOOTH);
        glLineWidth(2F);
        glBegin(GL_LINE_STRIP);
        for (float i = end; i >= start; i -= (360 / 90.0f))
            glVertex2f((float) (x + (cos(i * PI / 180) * (radius * 1.001F))), (float) (y + (sin(i * PI / 180) * (radius * 1.001F))));
        glEnd();
        glDisable(GL_LINE_SMOOTH);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
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
    public static void drawFastRoundedRect(float left, float top, float right, float bottom, float radius, int color) {
		final int Semicircle = 18;
		final float f = 90.0f / Semicircle;
		final float f2 = (color >> 24 & 0xFF) / 255.0f;
		final float f3 = (color >> 16 & 0xFF) / 255.0f;
		final float f4 = (color >> 8 & 0xFF) / 255.0f;
		final float f5 = (color & 0xFF) / 255.0f;
		GL11.glDisable(2884);
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GL11.glBlendFunc(770, 771);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f(f3, f4, f5, f2);
		GL11.glBegin(5);
		GL11.glVertex2f(left + radius, top);
		GL11.glVertex2f(left + radius, bottom);
		GL11.glVertex2f(right - radius, top);
		GL11.glVertex2f(right - radius, bottom);
		GL11.glEnd();
		GL11.glBegin(5);
		GL11.glVertex2f(left, top + radius);
		GL11.glVertex2f(left + radius, top + radius);
		GL11.glVertex2f(left, bottom - radius);
		GL11.glVertex2f(left + radius, bottom - radius);
		GL11.glEnd();
		GL11.glBegin(5);
		GL11.glVertex2f(right, top + radius);
		GL11.glVertex2f(right - radius, top + radius);
		GL11.glVertex2f(right, bottom - radius);
		GL11.glVertex2f(right - radius, bottom - radius);
		GL11.glEnd();
		GL11.glBegin(6);
		float f6 = right - radius;
		float f7 = top + radius;
		GL11.glVertex2f(f6, f7);
		int j = 0;
		for (j = 0; j <= Semicircle; ++j) {
			final float f8 = j * f;
			GL11.glVertex2f((float)(f6 + radius * cos(Math.toRadians(f8))), (float)(f7 - radius * sin(Math.toRadians(f8))));
		}
		GL11.glEnd();
		GL11.glBegin(6);
		f6 = left + radius;
		f7 = top + radius;
		GL11.glVertex2f(f6, f7);
		for (j = 0; j <= Semicircle; ++j) {
			final float f9 = j * f;
			GL11.glVertex2f((float)(f6 - radius * cos(Math.toRadians(f9))), (float)(f7 - radius * sin(Math.toRadians(f9))));
		}
		GL11.glEnd();
		GL11.glBegin(6);
		f6 = left + radius;
		f7 = bottom - radius;
		GL11.glVertex2f(f6, f7);
		for (j = 0; j <= Semicircle; ++j) {
			final float f10 = j * f;
			GL11.glVertex2f((float)(f6 - radius * cos(Math.toRadians(f10))), (float)(f7 + radius * sin(Math.toRadians(f10))));
		}
		GL11.glEnd();
		GL11.glBegin(6);
		f6 = right - radius;
		f7 = bottom - radius;
		GL11.glVertex2f(f6, f7);
		for (j = 0; j <= Semicircle; ++j) {
			final float f11 = j * f;
			GL11.glVertex2f((float)(f6 + radius * cos(Math.toRadians(f11))), (float)(f7 + radius * sin(Math.toRadians(f11))));
		}
		GlStateManager.resetColor();
		GL11.glEnd();
		GL11.glEnable(3553);
		GL11.glEnable(2884);
		GL11.glDisable(3042);
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
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
    public static void endScissor() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }
    public static void beginScissor(int x, int y, int width, int height) {
        final int scaleFactor = new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor();

        if (!GL11.glIsEnabled(GL11.GL_SCISSOR_TEST))
            GL11.glEnable(GL11.GL_SCISSOR_TEST);

        GL11.glScissor((x * scaleFactor), (Minecraft.getMinecraft().getDisplayHeight() - (y + height) * scaleFactor), (width * scaleFactor), (height * scaleFactor));
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
        glEnable(GL_TEXTURE_2D);
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

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    public static void glColor(int hex) {
        float alpha = (float)(hex >> 24 & 255) / 255.0f;
        float red = (float)(hex >> 16 & 255) / 255.0f;
        float green = (float)(hex >> 8 & 255) / 255.0f;
        float blue = (float)(hex & 255) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
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
        enableStandardItemLighting();
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
        disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit());
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit());
    }
    public static void disableStandardItemLighting()
    {
        GlStateManager.disableLighting();
        GlStateManager.disableLight(0);
        GlStateManager.disableLight(1);
        GlStateManager.disableColorMaterial();
    }
    public static void enableStandardItemLighting()
    {
        RenderHelper.enableStandardItemLighting();
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
