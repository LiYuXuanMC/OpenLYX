package al.nya.reflect.utils.render;

import al.nya.reflect.utils.math.MathUtil;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;
import al.nya.reflect.wrapper.wraps.wrapper.render.OpenGlHelper;
import al.nya.reflect.wrapper.wraps.wrapper.shader.Framebuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.glUniform1;

public class BloomUtil {
    private final static Minecraft mc = Minecraft.getMinecraft();

    public static ShaderUtil gaussianBloom = new ShaderUtil("bloom.frag");

    public static Framebuffer framebuffer = new Framebuffer(1, 1, false);


    public static void renderBlur(int sourceTexture, int radius, int offset) {
        framebuffer = RenderUtil.createFrameBuffer(framebuffer);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.0f);
        GlStateManager.enableBlend();
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

        final FloatBuffer weightBuffer = BufferUtils.createFloatBuffer(256);
        for (int i = 0; i <= radius; i++) {
            weightBuffer.put(MathUtil.calculateGaussianValue(i, radius));
        }
        weightBuffer.rewind();

        RenderUtil.setAlphaLimit(0.0F);

        framebuffer.framebufferClear();
        framebuffer.bindFramebuffer(true);
        gaussianBloom.init();
        setupUniforms(radius, offset, 0, weightBuffer);
        RenderUtil.bindTexture(sourceTexture);
        ShaderUtil.drawQuads();
        gaussianBloom.unload();
        framebuffer.unbindFramebuffer();


        mc.getFramebuffer().bindFramebuffer(true);

        gaussianBloom.init();
        setupUniforms(radius, 0, offset, weightBuffer);
        GL13.glActiveTexture(GL13.GL_TEXTURE16);
        RenderUtil.bindTexture(sourceTexture);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        RenderUtil.bindTexture(framebuffer.getFramebufferTexture());
        ShaderUtil.drawQuads();
        gaussianBloom.unload();

        GlStateManager.alphaFunc(516, 0.1f);
        GlStateManager.enableAlpha();

        GlStateManager.bindTexture(0);
    }

    public static void setupUniforms(int radius, int directionX, int directionY, FloatBuffer weights) {
        gaussianBloom.setUniformi("inTexture", 0);
        gaussianBloom.setUniformi("textureToCheck", 16);
        gaussianBloom.setUniformf("radius", radius);
        gaussianBloom.setUniformf("texelSize", 1.0F / (float) mc.getDisplayWidth(), 1.0F / (float) mc.getDisplayHeight());
        gaussianBloom.setUniformf("direction", directionX, directionY);
        glUniform1(gaussianBloom.getUniform("weights"), weights);
    }

}
