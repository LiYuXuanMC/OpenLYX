package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.events.client.EventShader;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.render.*;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.shader.Framebuffer;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GLContext;

public class Blur extends Module {
    private final OptionValue blur = new OptionValue("Blur", true);
    private final ModeValue blurMode = new ModeValue("Mode", BlurMode.Kawase, BlurMode.values());
    private final DoubleValue radius = new DoubleValue("Radius", 50, 1, 10, "0") {
        @Override
        public boolean show() {
            return blurMode.getValue() != BlurMode.Kawase;
        }
    };
    private final DoubleValue iterations = new DoubleValue("Iterations", 15, 1, 4, "0") {
        @Override
        public boolean show() {
            return blurMode.getValue() != BlurMode.Gaussian;
        }
    };
    private final DoubleValue offset = new DoubleValue("Offset", 20, 1, 3, "0") {
        @Override
        public boolean show() {
            return blurMode.getValue() != BlurMode.Gaussian;
        }
    };
    private final OptionValue shadow = new OptionValue("Shadow", true);
    private final DoubleValue shadowRadius = new DoubleValue("Shadow Radius", 20, 1, 6, "0") {
        @Override
        public boolean show() {
            return shadow.getValue();
        }
    };
    private final DoubleValue shadowOffset = new DoubleValue("Shadow Offset", 15, 1, 2, "0") {
        @Override
        public boolean show() {
            return shadow.getValue();
        }
    };
    private String currentMode = "unknown";
    private Framebuffer bloomFramebuffer;

    public Blur() {
        super("Blur", "模糊", ModuleType.Visual);
        mc.addScheduledTask(() -> bloomFramebuffer = new Framebuffer(1, 1, false));
        addValues(blur, blurMode, radius, iterations, offset, shadow, shadowRadius, shadowOffset);
    }

    @Override
    public void onEnable() {
        if (mc.getGameSettings().isFastRendering()) {
            ClientUtil.printChat(ClientUtil.Level.ERROR, "请勿开启快速渲染，这和模糊冲突。");
            setEnableNoNotification(false);
            return;
        }
        try {
            ContextCapabilities contextcapabilities = GLContext.getCapabilities();
            if (!contextcapabilities.OpenGL30) {
                ClientUtil.printChat(ClientUtil.Level.ERROR, "您的显卡不支持OpenGL3.0。");
                setEnableNoNotification(false);
            }
        } catch (RuntimeException e) {
            ClientUtil.printChat(e.getMessage());
            ClientUtil.printChat(ClientUtil.Level.ERROR, "无法检查您的显卡是否支持OpenGL3.0。可能会发生不可预料的错误。");
        }

        currentMode = blurMode.getValue().name();

        super.onEnable();
    }

    public void stuffToBlur(boolean bloom) {

        // Gui.drawRect2(40, 40, 400, 40, -1);

    }

    public void blurScreen() {
        if (!isEnable()) return;
        if (mc.getGameSettings().isFastRendering()) return;
        if (!currentMode.equals(blurMode.getValue().name())) {
            currentMode = blurMode.getValue().name();
        }
        if (blur.getValue()) {
            StencilUtil.initStencilToWrite();
            Reflect.Instance.eventBus.callEvent(new EventShader(false));
            stuffToBlur(false);
            StencilUtil.readStencilBuffer(1);

            switch (currentMode) {
                case "Gaussian":
                    GaussianBlur.renderBlur(radius.getValue().floatValue());
                    break;
                case "Kawase":
                    KawaseBlur.renderBlur(iterations.getValue().intValue(), offset.getValue().intValue());
                    break;
            }
            StencilUtil.uninitStencilBuffer();
        }


        if (shadow.getValue()) {
            bloomFramebuffer = RenderUtil.createFrameBuffer(bloomFramebuffer);

            bloomFramebuffer.framebufferClear();
            bloomFramebuffer.bindFramebuffer(true);
            Reflect.Instance.eventBus.callEvent(new EventShader(true));
            stuffToBlur(true);
            bloomFramebuffer.unbindFramebuffer();

            BloomUtil.renderBlur(bloomFramebuffer.getFramebufferTexture(), shadowRadius.getValue().intValue(), shadowOffset.getValue().intValue());
        }


    }


    enum BlurMode {
        Kawase,
        Gaussian
    }
}
