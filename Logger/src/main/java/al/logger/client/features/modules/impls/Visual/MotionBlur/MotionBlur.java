package al.logger.client.features.modules.impls.Visual.MotionBlur;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.resource.SimpleReloadableResourceManager;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import al.logger.client.wrapper.ReflectUtil;
import lombok.SneakyThrows;
import org.lwjgl.opengl.GL11;

import java.util.Map;

public class MotionBlur extends Module {

    public ModeValue modeValue = new ModeValue("Mode", BlurMode.Shader, BlurMode.values());
    public DoubleValue multiplier = new DoubleValue("FrameMultiplier", 1.0, 0.05, 0.8, 0.01);
    private Map domainResourceManagers;
    private double lastValue = 0;

    public MotionBlur() {
        super("MotionBlur", "Object Motion Blur", Category.Visual);
        this.addValues(modeValue, multiplier);
    }


    public void applyShader() {
        mc.getEntityRenderer().loadShader(new ResourceLocation("motionblur", "motionblur"));
    }

    @Override
    public void onEnable() {
        super.onEnable();

        if (modeValue.getValue() == BlurMode.Shader) {
            if (domainResourceManagers == null) {
                domainResourceManagers = getDomainResourceManagers();
            }
            if (!domainResourceManagers.containsKey("motionblur")) {
                domainResourceManagers.put("motionblur", createMotionBlurResourceManager());
            }
            lastValue = multiplier.getValue().intValue();
            this.applyShader();
        }


    }

    @Listener
    public void onClientTick(EventTick eventTick) {

        if (modeValue.getValue() == BlurMode.Shader) {
            if (!mc.getThePlayer().isNull() && !mc.getTheWorld().isNull()) {
                if ((!Minecraft.getMinecraft().getEntityRenderer().isShaderActive()
                        || (this.lastValue != this.multiplier.getValue())) && (!mc.getTheWorld().isNull())) {
                    this.lastValue = this.multiplier.getValue();
                    this.applyShader();
                }
                if (domainResourceManagers == null) {
                    domainResourceManagers = getDomainResourceManagers();
                }
                if (!domainResourceManagers.containsKey("motionblur")) {
                    domainResourceManagers.put("motionblur", createMotionBlurResourceManager());
                }
            }
        }
    }

    @Listener
    public void onRender2D(EventRender2D eventRender2D) {
        if (modeValue.getValue() == BlurMode.GlAccum) {
            GL11.glAccum(GL11.GL_LOAD, 1f);
            GL11.glAccum(GL11.GL_MULT, multiplier.getValue().floatValue());
            GL11.glAccum(GL11.GL_ACCUM, 1f / multiplier.getValue().floatValue());
            GL11.glAccum(GL11.GL_RETURN, 1f);
        }
    }

    @SneakyThrows
    private Object createMotionBlurResourceManager() {
        return ReflectUtil.construction(Logger.getInstance().getBridgeManager().getIResourceManagerConstructor(), new MotionBlurResourceManager());
    }

    @Override
    public void onDisable() {
        super.onDisable();
        mc.getEntityRenderer().stopUseShader();
    }

    private Map getDomainResourceManagers() {
        SimpleReloadableResourceManager simpleReloadableResourceManager = new SimpleReloadableResourceManager(mc.getResourceManager().getWrappedObject());
        return simpleReloadableResourceManager.getDomainResourceManagers();

    }

    enum BlurMode {
        Shader,
        GlAccum
    }
}
