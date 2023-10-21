package al.nya.reflect.wrapper.bridge;

import al.nya.reflect.wrapper.wraps.impl.GuiScreenImpl;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiButton;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;

public class BridgeExample extends GuiScreen {
    private GuiScreenImpl guiScreen;
    public BridgeExample(GuiScreenImpl guiScreen){
        super(guiScreen);
        this.guiScreen = guiScreen;
    }
    public void func_73863_a(int var1, int var2, float var3){
        guiScreen.drawScreen(var1,var2,var3);
        super.drawScreen(var1,var2,var3);
    }

    public void initGui() {
        guiScreen.initGui();
    }

    public void onGuiClosed() {
        guiScreen.onGuiClosed();
    }

    public void keyTyped(char typedChar, int keyCode) {
        if (guiScreen.keyTyped(typedChar, keyCode)) super.keyTyped(typedChar, keyCode);
    }

    public void actionPerformed(Object button) {
        guiScreen.actionPerformed(new GuiButton(button));
    }
}
