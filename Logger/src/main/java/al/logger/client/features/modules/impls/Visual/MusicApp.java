package al.logger.client.features.modules.impls.Visual;

import al.logger.client.Logger;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.PanelBase;
import al.logger.client.ui.screens.music.MusicPanel;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.gui.GuiScreen;
import al.logger.client.wrapper.ReflectUtil;
import lombok.SneakyThrows;

public class MusicApp extends Module {
    public MusicApp() {
        super("MusicApp", "Open Music App Gui", Category.Visual);
    }

    public PanelBase musicPanel;

    @SneakyThrows
    @Override
    public void onEnable() {
        if (musicPanel == null) {
            musicPanel = new MusicPanel();
        }
        Minecraft.getMinecraft().displayGuiScreen(new GuiScreen(ReflectUtil.construction(Logger.getInstance().getBridgeManager().getGuiScreenBridgeConstructor(), new MusicPanel())));
        this.toggle();
    }
}
