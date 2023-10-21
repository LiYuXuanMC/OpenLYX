package al.nya.reflect.wrapper.wraps.impl;


import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiButton;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiScreen;

public abstract class GuiScreenImpl {
    public GuiScreen renderingObject;

    public abstract void drawScreen(int mouseX, int mouseY, float partialTicks);

    public void initGui() {
    }

    public void onGuiClosed() {
    }

    public boolean keyTyped(char typedChar, int keyCode) {
        return true;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
    }

    public void actionPerformed(GuiButton button) {
    }

    public void updateScreen() {
    }
}
