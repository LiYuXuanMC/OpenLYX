package al.logger.client.features.modules.impls.Visual;


import al.logger.client.Logger;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.PanelBase;
import al.logger.client.ui.screens.logger.LoggerScreen;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.gui.GuiScreen;
import al.logger.client.wrapper.ReflectUtil;
import lombok.SneakyThrows;
import org.lwjgl.input.Keyboard;

public class ClickGUI extends Module {

    public static OptionValue shadow = new OptionValue("Shadow", false);
    public static ModeValue language = new ModeValue("Language", Language.English, Language.values()){
        @Override
        public void onValueChange(Enum old, Enum newValue) {
            if(newValue.equals(Language.English)){

            }else if(newValue.equals(Language.Chinese)){

            }
        }
    };
    public PanelBase loggerScreen;

    public ClickGUI() {
        super("ClickGUI", Category.Visual);
        this.addValues(shadow);
        this.setKeyCode(Keyboard.KEY_RSHIFT);
        this.setDescription("Display a GUI to debug the client");
    }


    @SneakyThrows
    @Override
    public void onEnable() {
        if (loggerScreen == null) {
            loggerScreen = new LoggerScreen();
        }
        Minecraft.getMinecraft().displayGuiScreen(new GuiScreen(ReflectUtil.construction(Logger.getInstance().getBridgeManager().getGuiScreenBridgeConstructor(), loggerScreen)));
        this.toggle();
    }

    public enum Language {
        English,
        Chinese
    }
}
