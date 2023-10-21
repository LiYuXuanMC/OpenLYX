package al.logger.client.utils.render;

import al.logger.client.features.modules.impls.Visual.ClickGUI;
import al.logger.client.utils.misc.GLUtil;

import al.logger.client.utils.render.blurs.GaussianBlur;
import al.logger.client.utils.render.blurs.KawaseBlur;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.gui.ScaledResolution;
import al.logger.client.wrapper.LoggerMC.render.Tessellator;
import al.logger.client.wrapper.LoggerMC.render.WorldRenderer;
import al.logger.client.wrapper.LoggerMC.shader.Framebuffer;
import al.logger.client.wrapper.LoggerMC.render.GlStateManager;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import al.logger.client.wrapper.LoggerMC.render.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.glScissor;

public class RenderUtil {
    private static Minecraft mc = Minecraft.getMinecraft();
    public static final ShaderUtil roundedShader = new ShaderUtil("roundedRect");
    public static final ShaderUtil roundedOutlineShader = new ShaderUtil("roundrectOutline");
    public static final ShaderUtil roundedTexturedShader = new ShaderUtil("roundrecttextured");
    public static final ShaderUtil roundOutlineShader = new ShaderUtil("roundoutline");
    public static final ShaderUtil roundedRectGradientRectShader = new ShaderUtil("roundedRectGradientRect");
    public static Framebuffer bloomFramebuffer = new Framebuffer(1, 1, false);

    public static Framebuffer createFrameBuffer(Framebuffer framebuffer) {
        if (framebuffer == null || framebuffer.getFramebufferWidth() != mc.getDisplayWidth() || framebuffer.getFramebufferHeight() != mc.getDisplayHeight()) {
            if (framebuffer != null) {
                framebuffer.deleteFramebuffer();
            }
            return new Framebuffer(mc.getDisplayWidth(), mc.getDisplayHeight(), true);
        }
        return framebuffer;
    }

    public static void drawLine(double x1, double y1, double x2, double y2, double lineWidth, Color color) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        setColor(color.getRGB());
        GL11.glLineWidth((float) lineWidth);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }


    public static void drawEntityESP(double x, double y, double z, double width, double height, int color, int lineColor, double lineWidth) {


        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        setColor(color);
        drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glLineWidth((float) lineWidth);
        setColor(lineColor);
        drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }


    public static void drawLine(double x, double y, double z, double x1, double y1, double z1, final Color color, final float width) {
        x = x - mc.getRenderManager().getRenderPosX();
        x1 = x1 - mc.getRenderManager().getRenderPosX();
        y = y - mc.getRenderManager().getRenderPosY();
        y1 = y1 - mc.getRenderManager().getRenderPosY();
        z = z - mc.getRenderManager().getRenderPosZ();
        z1 = z1 - mc.getRenderManager().getRenderPosZ();

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(width);


        //color(color);
        GL11.glBegin(2);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x1, y1, z1);
        GL11.glEnd();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void color(int color, float alpha) {
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        GlStateManager.color(r, g, b, alpha);
    }


    public static void setColor(int color) {
        color(color, (float) (color >> 24 & 255) / 255.0F);
    }


    public static void drawScaledCustomSizeModalRect(float x, float y, float u, float v, float uWidth, float vHeight, float width, float height, float tileWidth, float tileHeight) {
        float f = 1.0F / tileWidth;
        float f1 = 1.0F / tileHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos((double) x, (double) (y + height), 0.0D).tex((double) (u * f), (double) ((v + (float) vHeight) * f1)).endVertex();
        worldrenderer.pos((double) (x + width), (double) (y + height), 0.0D).tex((double) ((u + (float) uWidth) * f), (double) ((v + (float) vHeight) * f1)).endVertex();
        worldrenderer.pos((double) (x + width), (double) y, 0.0D).tex((double) ((u + (float) uWidth) * f), (double) (v * f1)).endVertex();
        worldrenderer.pos((double) x, (double) y, 0.0D).tex((double) (u * f), (double) (v * f1)).endVertex();
        tessellator.draw();
    }

    public static void drawRect(float x, float y, float width, float height, int color) {
        GlStateManager.color(1, 1, 1, 1);
        float constWidth = x + width;
        float constHeight = y + height;
        GLUtil.setupRender2D(() -> GLUtil.render(GL11.GL_QUADS, () -> {
            setColor(color);
            GL11.glVertex2d(x, y);
            GL11.glVertex2d(x, constHeight);
            GL11.glVertex2d(constWidth, constHeight);
            GL11.glVertex2d(constWidth, y);
        }));
    }

    private static void setupRoundedRectUniforms(float x, float y, float width, float height, float radius, ShaderUtil roundedTexturedShader) {
        ScaledResolution sr = new ScaledResolution(mc);
        roundedTexturedShader.setUniformf("location", x * sr.getScaleFactor(),
                (mc.getDisplayHeight() - (height * sr.getScaleFactor())) - (y * sr.getScaleFactor()));
        roundedTexturedShader.setUniformf("rectSize", width * sr.getScaleFactor(), height * sr.getScaleFactor());
        roundedTexturedShader.setUniformf("radius", radius * sr.getScaleFactor());
    }

    public static void drawRound(float x, float y, float width, float height, float radius, Color color) {
        drawRound(x, y, width, height, radius, false, color);
    }

    public static void drawRoundScale(float x, float y, float width, float height, float radius, Color color, float scale) {
        drawRound(x + width - width * scale, y + height / 2f - ((height / 2f) * scale),
                width * scale, height * scale, radius, false, color);
    }

    public static void doScale(float x, float y, float scale, Runnable data) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0);
        GL11.glScalef(scale, scale, 1);
        GL11.glTranslatef(-x, -y, 0);
        data.run();
        GL11.glPopMatrix();
    }


    public static void drawRound(float x, float y, float width, float height, float radius, boolean blur, Color color) {
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        roundedShader.init();
        setupRoundedRectUniforms(x, y, width, height, radius, roundedShader);
        roundedShader.setUniformi("blur", blur ? 1 : 0);
        roundedShader.setUniformf("color", color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        ShaderUtil.drawQuads(x - 1, y - 1, width + 2, height + 2);
        roundedShader.unload();
        GlStateManager.disableBlend();
    }

    public static void drawFullImage(ResourceLocation image, float x, float y, float width, float height, Color color) {
        GlStateManager.disableAlpha();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        setColor(color.getRGB());
        mc.getTextureManager().bindTexture(image);
        setColor(color.getRGB());
        drawModalRectWithCustomSizedTexture(x, y, 0f, 0f, width, height, width, height);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GL11.glPopMatrix();
    }

    public static void drawFullImage(ResourceLocation image, float x, float y, float width, float height) {
        GlStateManager.disableAlpha();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(image);
        GlStateManager.color(1, 1, 1, 1);
        drawModalRectWithCustomSizedTexture(x, y, 0f, 0f, width, height, width, height);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GL11.glPopMatrix();
    }

    public static void drawRoundTextured(float x, float y, float width, float height, float radius, float alpha) {
        GlStateManager.color(1, 1, 1, 1);
        roundedTexturedShader.init();
        roundedTexturedShader.setUniformi("textureIn", 0);
        setupRoundedRectUniforms(x, y, width, height, radius, roundedTexturedShader);
        roundedTexturedShader.setUniformf("alpha", alpha);
        ShaderUtil.drawQuads(x - 1, y - 1, width + 2, height + 2);
        roundedTexturedShader.unload();
        GlStateManager.disableBlend();
    }

    public static void drawFullImage(ResourceLocation image, float x, float y, float width, float height, int alpha) {
        GlStateManager.disableAlpha();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.color(1, 1, 1, alpha);
        mc.getTextureManager().bindTexture(image);
        GlStateManager.color(1, 1, 1, alpha);
        drawModalRectWithCustomSizedTexture(x, y, 0f, 0f, width, height, width, height);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GL11.glPopMatrix();
    }

    public static void drawModalRectWithCustomSizedTexture(float x, float y, float u, float v, float width, float height, float textureWidth, float textureHeight) {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos((double) x, (double) (y + height), 0.0D).tex((double) (u * f), (double) ((v + (float) height) * f1)).endVertex();
        worldrenderer.pos((double) (x + width), (double) (y + height), 0.0D).tex((double) ((u + (float) width) * f), (double) ((v + (float) height) * f1)).endVertex();
        worldrenderer.pos((double) (x + width), (double) y, 0.0D).tex((double) ((u + (float) width) * f), (double) (v * f1)).endVertex();
        worldrenderer.pos((double) x, (double) y, 0.0D).tex((double) (u * f), (double) (v * f1)).endVertex();
        tessellator.draw();
    }

    public static void doScissor(float x, float y, float width, float height) {
        ScaledResolution sr = new ScaledResolution(mc);
        final double scale = sr.getScaleFactor();
        double finalHeight = height * scale;
        double finalY = (sr.getScaledHeight() - y) * scale;
        double finalX = x * scale;
        double finalWidth = width * scale;
        glScissor((int) finalX, (int) (finalY - finalHeight), (int) finalWidth, (int) finalHeight);
    }

    public static void doStencil(float x, float y, float width, float height, float radius, Runnable content) {
        doStencil(x, y, width, height, radius, content, 1);
    }

    public static void doStencil(float x, float y, float width, float height, float radius, Runnable content, int ref) {
        StencilUtil.initStencilToWrite(ref);
        drawRound(x, y, width, height, radius, new Color(255, 255, 255, 255));
        StencilUtil.readStencilBuffer(ref);
        content.run();
        StencilUtil.uninitStencilBuffer();
    }

    public static void doStencil(float x, float y, float width, float height, float radius) {
        doStencil(x, y, width, height, radius, 1);
    }

    public static void doStencil(float x, float y, float width, float height, float radius, int ref) {
        StencilUtil.initStencilToWrite(ref);
        drawRound(x, y, width, height, radius, new Color(255, 255, 255, 255));
        StencilUtil.readStencilBuffer(ref);
    }

    public static void uninstallStencil() {
        StencilUtil.uninitStencilBuffer();
    }

    public static void drawRoundOutline(float x, float y, float width, float height, float radius, float outlineThickness, Color color, Color outlineColor) {
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        roundedOutlineShader.init();
        ScaledResolution sr = new ScaledResolution(mc);
        setupRoundedRectUniforms(x, y, width, height, radius, roundedOutlineShader);
        roundedOutlineShader.setUniformf("outlineThickness", outlineThickness);
        roundedOutlineShader.setUniformf("color", color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
        roundedOutlineShader.setUniformf("outlineColor", outlineColor.getRed() / 255f, outlineColor.getGreen() / 255f, outlineColor.getBlue() / 255f, outlineColor.getAlpha() / 255f);
        ShaderUtil.drawQuads(x - (2 + outlineThickness), y - (2 + outlineThickness), width + (4 + outlineThickness * 2), height + (4 + outlineThickness * 2));
        roundedOutlineShader.unload();
        GlStateManager.disableBlend();
    }

    public static void drawOutline(float x, float y, float width, float height, float radius, float borderSize, Color color, boolean isNotOutline) {
        if (!isNotOutline) {
            roundOutlineShader.init();
            roundOutlineShader.setUniformf("u_size", width, height);
            roundOutlineShader.setUniformf("u_radius", radius);
            roundOutlineShader.setUniformf("u_border_size", borderSize);
            roundOutlineShader.setUniformf("u_color", color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
            GlStateManager.color(1, 1, 1, 1);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.0F);
            ShaderUtil.drawQuads(x, y, width, height);
            GlStateManager.disableBlend();
            roundOutlineShader.unload();
        }
    }

    public static void drawRoundOutline(float x, float y, float width, float height, float radius, float outlineThickness, Color color, Color outlineColor, boolean isNotOutline) {
        if (isNotOutline) {
            drawRound(x, y, width, height, radius, color);
        } else {
            GlStateManager.color(1, 1, 1, 1);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            roundedOutlineShader.init();
            ScaledResolution sr = new ScaledResolution(mc);
            setupRoundedRectUniforms(x, y, width, height, radius, roundedOutlineShader);
            roundedOutlineShader.setUniformf("outlineThickness", outlineThickness);
            roundedOutlineShader.setUniformf("color", color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
            roundedOutlineShader.setUniformf("outlineColor", outlineColor.getRed() / 255f, outlineColor.getGreen() / 255f, outlineColor.getBlue() / 255f, outlineColor.getAlpha() / 255f);
            ShaderUtil.drawQuads(x - (2 + outlineThickness), y - (2 + outlineThickness), width + (4 + outlineThickness * 2), height + (4 + outlineThickness * 2));
            roundedOutlineShader.unload();
            GlStateManager.disableBlend();
        }

    }

    public static void upShadow(float x, float y, float width, float height, int radius, int offset) {
        if (!ClickGUI.shadow.getValue()) {
            return;
        }
        bloomFramebuffer = RenderUtil.createFrameBuffer(bloomFramebuffer);
        bloomFramebuffer.framebufferClear();
        bloomFramebuffer.bindFramebuffer(true);
        drawRound(x, y, width, height, radius, new Color(255, 255, 255));
        bloomFramebuffer.unbindFramebuffer();
        BloomUtil.renderBlur(bloomFramebuffer.getFramebufferTexture(), radius, offset);
    }

    public static void upBlur(float x, float y, float width, float height, int radius, int offset) {
        RenderUtil.upBlur(x, y, width, height, offset, () -> {
            RenderUtil.drawRound(x, y, width, height, radius, new Color(255, 255, 255, 255));
        });
    }

    public static void upBlur(float x, float y, float width, float height, int offset, Runnable runnable) {
        StencilUtil.initStencilToWrite(1);
        runnable.run();
        StencilUtil.readStencilBuffer(1);
        GaussianBlur.renderBlur(offset);
        StencilUtil.uninitStencilBuffer();
    }

    public static void upBlurKawass(float x, float y, float width, float height, int radius, int iterations, int offset) {
        StencilUtil.initStencilToWrite(1);
        RenderUtil.drawRound(x, y, width, height, radius, new Color(255, 255, 255, 255));
        StencilUtil.readStencilBuffer(1);
        KawaseBlur.renderBlur(iterations, offset);
        StencilUtil.uninitStencilBuffer();
    }

    public static void drawRoundedRectGradientRect(float x, float y, float width, float height, float radius, Color firstColor, Color secondColor, boolean vertical) {
        roundedRectGradientRectShader.init();
        roundedRectGradientRectShader.setUniformf("u_size", width, height);
        roundedRectGradientRectShader.setUniformf("u_radius", radius);
        roundedRectGradientRectShader.setUniformf("u_first_color", firstColor.getRed() / 255.0F, firstColor.getGreen() / 255.0F, firstColor.getBlue() / 255.0F, firstColor.getAlpha() / 255.0F);
        roundedRectGradientRectShader.setUniformf("u_second_color", secondColor.getRed() / 255.0F, secondColor.getGreen() / 255.0F, secondColor.getBlue() / 255.0F, secondColor.getAlpha() / 255.0F);
        roundedRectGradientRectShader.setUniformi("u_direction", vertical ? 1 : 0);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.0F);
        ShaderUtil.drawQuads(x, y, width, height);
        roundedOutlineShader.unload();
    }

    public static void drawEntityBoxESP(double x, double y, double z, double width, double height, int color, float lineRed, float lineGreen, float lineBlue,
                                        float lineAlpha, float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        setColor(color);
        drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
    }

    public static void drawOutlinedBoundingBox(AxisAlignedBB aa) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        double minX = aa.getMinX();
        double minY = aa.getMinY();
        double minZ = aa.getMinZ();
        double maxX = aa.getMaxX();
        double maxY = aa.getMaxY();
        double maxZ = aa.getMaxZ();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(minX, minY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).endVertex();
        worldRenderer.pos(minX, minY, maxZ).endVertex();
        worldRenderer.pos(minX, minY, minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(minX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).endVertex();
        worldRenderer.pos(minX, maxY, minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(1, DefaultVertexFormats.POSITION);
        worldRenderer.pos(minX, minY, minZ).endVertex();
        worldRenderer.pos(minX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, minZ).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).endVertex();
        worldRenderer.pos(minX, minY, maxZ).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).endVertex();
        tessellator.draw();
    }

    public static void drawBoundingBox(AxisAlignedBB aa) {
        Tessellator tessellator = Tessellator.getInstance();
        double minX = aa.getMinX();
        double minY = aa.getMinY();
        double minZ = aa.getMinZ();
        double maxX = aa.getMaxX();
        double maxY = aa.getMaxY();
        double maxZ = aa.getMaxZ();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(minX, minY, minZ).endVertex();
        worldRenderer.pos(minX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, minZ).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).endVertex();
        worldRenderer.pos(minX, minY, maxZ).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(maxX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, minZ).endVertex();
        worldRenderer.pos(minX, maxY, minZ).endVertex();
        worldRenderer.pos(minX, minY, minZ).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).endVertex();
        worldRenderer.pos(minX, minY, maxZ).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(minX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).endVertex();
        worldRenderer.pos(minX, maxY, minZ).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(minX, minY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, minZ).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).endVertex();
        worldRenderer.pos(minX, minY, maxZ).endVertex();
        worldRenderer.pos(minX, minY, minZ).endVertex();
        worldRenderer.pos(minX, minY, maxZ).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).endVertex();
        worldRenderer.pos(maxX, minY, minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(minX, minY, minZ).endVertex();
        worldRenderer.pos(minX, maxY, minZ).endVertex();
        worldRenderer.pos(minX, minY, maxZ).endVertex();
        worldRenderer.pos(minX, maxY, maxZ).endVertex();
        worldRenderer.pos(maxX, minY, maxZ).endVertex();
        worldRenderer.pos(maxX, maxY, maxZ).endVertex();
        worldRenderer.pos(maxX, minY, minZ).endVertex();
        worldRenderer.pos(maxX, maxY, minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
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
}
