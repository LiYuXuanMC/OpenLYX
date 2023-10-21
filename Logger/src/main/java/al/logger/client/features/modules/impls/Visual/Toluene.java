package al.logger.client.features.modules.impls.Visual;

import al.logger.client.Logger;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.PanelBase;
import al.logger.client.ui.screens.toluene.TolueneScreen;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.gui.GuiScreen;
import al.logger.client.wrapper.ReflectUtil;

public class Toluene extends Module {

    public PanelBase tolueneScreen;

    public Toluene() {
        super("Toluene", "Script(Toluene) System", Category.Visual);
    }

    @Override
    public void onEnable() {
        if (tolueneScreen == null) {
            tolueneScreen = new TolueneScreen();
        }
        Minecraft.getMinecraft().displayGuiScreen(new GuiScreen(ReflectUtil.construction(Logger.getInstance().getBridgeManager().getGuiScreenBridgeConstructor(), tolueneScreen)));
        this.toggle();
    }
}
