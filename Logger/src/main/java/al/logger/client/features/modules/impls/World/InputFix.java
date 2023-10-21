package al.logger.client.features.modules.impls.World;

import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public class InputFix extends Module {
    public InputFix() {
        super("InputFix", "Fix Chinese Input", Category.World);
        this.setHide(true);
    }
    @ExportObfuscate(name = "inputFix")
    public void inputFix(Object screen) {
        final char c0 = Keyboard.getEventCharacter();
        if ((Keyboard.getEventKey() == 0 && c0 >= ' ') || Keyboard.getEventKeyState()) {
            new GuiScreen(screen).keyTyped(c0, Keyboard.getEventKey());
        }
        Minecraft.getMinecraft().dispatchKeypresses();
    }
}
