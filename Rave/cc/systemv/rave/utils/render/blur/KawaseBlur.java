package cc.systemv.rave.utils.render.blur;

import cc.systemv.rave.utils.render.RenderUtil;
import cc.systemv.rave.utils.render.ShaderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;


public class KawaseBlur{

    public static ShaderUtil kawaseDown = new ShaderUtil("rave/shaders/kawaseDown.frag");
    public static ShaderUtil kawaseUp = new ShaderUtil("rave/shaders/kawaseUp.frag");

    public static Framebuffer framebuffer = new Framebuffer(1, 1, false);


    public static void setupUniforms(float offset) {
        kawaseDown.setUniformf("offset", offset, offset);
        kawaseUp.setUniformf("offset", offset, offset);
    }

    private static int currentIterations;

    private static final List<Framebuffer> framebufferList = new ArrayList<>();

    private static void initFramebuffers(float iterations) {
        Minecraft mc = Minecraft.getMinecraft();
        for(Framebuffer framebuffer : framebufferList) {
            framebuffer.deleteFramebuffer();
        }
        framebufferList.clear();

        framebufferList.add(RenderUtil.createFrameBuffer(framebuffer));


        for(int i = 1; i <= iterations; i++) {
            Framebuffer framebuffer = new Framebuffer(mc.displayWidth, mc.displayHeight, false);
          //  framebuffer.setFramebufferFilter(GL11.GL_LINEAR);
            framebufferList.add(RenderUtil.createFrameBuffer(framebuffer));
        }
    }



    public static void renderBlur(int iterations, int offset) {
        Minecraft mc = Minecraft.getMinecraft();
        if(currentIterations != iterations) {
            initFramebuffers(iterations);
            currentIterations = iterations;
        }

        renderFBO(framebufferList.get(1), mc.getFramebuffer().framebufferTexture, kawaseDown, offset);

        //Downsample
        for (int i = 1; i < iterations; i++) {
            renderFBO(framebufferList.get(i + 1), framebufferList.get(i).framebufferTexture, kawaseDown, offset);
        }

        //Upsample
        for (int i = iterations; i > 1; i--) {
            renderFBO(framebufferList.get(i - 1), framebufferList.get(i).framebufferTexture, kawaseUp, offset);
        }


        mc.getFramebuffer().bindFramebuffer(true);

        bindTexture(framebufferList.get(1).framebufferTexture);
        kawaseUp.init();
        kawaseUp.setUniformf("offset", offset, offset);
        kawaseUp.setUniformf("halfpixel", 0.5f / mc.displayWidth, 0.5f / mc.displayHeight);
        kawaseUp.setUniformi("inTexture", 0);
        ShaderUtil.drawQuads();
        kawaseUp.unload();

    }

    private static void renderFBO(Framebuffer framebuffer, int framebufferTexture, ShaderUtil shader, float offset) {
        Minecraft mc = Minecraft.getMinecraft();
        framebuffer.framebufferClear();
        framebuffer.bindFramebuffer(true);
        shader.init();
        bindTexture(framebufferTexture);
        shader.setUniformf("offset", offset, offset);
        shader.setUniformi("inTexture", 0);
        shader.setUniformf("halfpixel", 0.5f / mc.displayWidth, 0.5f / mc.displayHeight);
        ShaderUtil.drawQuads();
        shader.unload();
        framebuffer.unbindFramebuffer();
    }
    public static void bindTexture(int texture) {
        glBindTexture(GL_TEXTURE_2D, texture);
    }

}
