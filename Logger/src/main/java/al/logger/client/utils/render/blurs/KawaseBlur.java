package al.logger.client.utils.render.blurs;

import al.logger.client.AccessInstance;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.utils.render.ShaderUtil;
import al.logger.client.wrapper.LoggerMC.shader.Framebuffer;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class KawaseBlur implements AccessInstance {

    public static ShaderUtil kawaseDown = new ShaderUtil("kawasedown.frag");
    public static ShaderUtil kawaseUp = new ShaderUtil("kawaseup.frag");

    public static Framebuffer framebuffer = new Framebuffer(1, 1, false);


    public static void setupUniforms(float offset) {
        kawaseDown.setUniformf("offset", offset, offset);
        kawaseUp.setUniformf("offset", offset, offset);
    }

    private static int currentIterations;

    private static final List<Framebuffer> framebufferList = new ArrayList<>();

    private static void initFramebuffers(float iterations) {
        for (Framebuffer framebuffer : framebufferList) {
            framebuffer.deleteFramebuffer();
        }
        framebufferList.clear();

        framebufferList.add(RenderUtil.createFrameBuffer(framebuffer));


        for (int i = 1; i <= iterations; i++) {
            Framebuffer framebuffer = new Framebuffer(mc.getDisplayWidth(), mc.getDisplayHeight(), false);
            //  framebuffer.setFramebufferFilter(GL11.GL_LINEAR);
            framebufferList.add(RenderUtil.createFrameBuffer(framebuffer));
        }
    }


    public static void renderBlur(int iterations, int offset) {
        if (currentIterations != iterations) {
            initFramebuffers(iterations);
            currentIterations = iterations;
        }

        renderFBO(framebufferList.get(1), mc.getFramebuffer().getFramebufferTexture(), kawaseDown, offset);

        //Downsample
        for (int i = 1; i < iterations; i++) {
            renderFBO(framebufferList.get(i + 1), framebufferList.get(i).getFramebufferTexture(), kawaseDown, offset);
        }

        //Upsample
        for (int i = iterations; i > 1; i--) {
            renderFBO(framebufferList.get(i - 1), framebufferList.get(i).getFramebufferTexture(), kawaseUp, offset);
        }


        mc.getFramebuffer().bindFramebuffer(true);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebufferList.get(1).getFramebufferTexture());
        kawaseUp.init();
        kawaseUp.setUniformf("offset", offset, offset);
        kawaseUp.setUniformf("halfpixel", 0.5f / mc.getDisplayWidth(), 0.5f / mc.getDisplayHeight());
        kawaseUp.setUniformi("inTexture", 0);
        ShaderUtil.drawQuads();
        kawaseUp.unload();

    }

    private static void renderFBO(Framebuffer framebuffer, int framebufferTexture, ShaderUtil shader, float offset) {
        framebuffer.framebufferClear();
        framebuffer.bindFramebuffer(true);
        shader.init();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebufferTexture);
        shader.setUniformf("offset", offset, offset);
        shader.setUniformi("inTexture", 0);
        shader.setUniformf("halfpixel", 0.5f / mc.getDisplayWidth(), 0.5f / mc.getDisplayHeight());
        ShaderUtil.drawQuads();
        shader.unload();
        framebuffer.unbindFramebuffer();
    }

}
