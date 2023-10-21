package al.nya.reflect.utils.render;

import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.shader.Framebuffer;

import java.util.ArrayList;
import java.util.List;

public class KawaseBlur {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final List<Framebuffer> framebufferList = new ArrayList<>();
    public static ShaderUtil kawaseDown = new ShaderUtil("kawaseDown.frag");
    public static ShaderUtil kawaseUp = new ShaderUtil("kawaseUp.frag");
    public static Framebuffer framebuffer = new Framebuffer(1, 1, false);
    private static int currentIterations;

    public static void setupUniforms(float offset) {
        kawaseDown.setUniformf("offset", offset, offset);
        kawaseUp.setUniformf("offset", offset, offset);
    }

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

        RenderUtil.bindTexture(framebufferList.get(1).getFramebufferTexture());
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
        RenderUtil.bindTexture(framebufferTexture);
        shader.setUniformf("offset", offset, offset);
        shader.setUniformi("inTexture", 0);
        shader.setUniformf("halfpixel", 0.5f / mc.getDisplayWidth(), 0.5f / mc.getDisplayHeight());
        ShaderUtil.drawQuads();
        shader.unload();
        framebuffer.unbindFramebuffer();
    }

}
