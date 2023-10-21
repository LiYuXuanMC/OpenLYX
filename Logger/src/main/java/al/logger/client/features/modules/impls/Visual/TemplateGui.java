package al.logger.client.features.modules.impls.Visual;

import al.logger.client.Logger;
import al.logger.client.bridge.bridges.GuiScreenBridge;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.panels.EditTemplateScreen;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.gui.GuiScreen;
import al.logger.client.wrapper.ReflectUtil;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

public class TemplateGui extends Module {
    public TemplateGui() {
        super("TemplateGui", "Edit Your Templates", Category.Visual);
    }

    @SneakyThrows
    @Override
    public void onEnable() {
        Class<?> bridge = Logger.getInstance().getBridgeManager().getGuiScreenBridgeClass();
        Constructor<?> constructor = bridge.getConstructor(GuiScreenBridge.class);
        Object gui = ReflectUtil.construction(constructor, new EditTemplateScreen());
        Minecraft.getMinecraft().displayGuiScreen(new GuiScreen(gui));
        this.toggle();
    }
}
