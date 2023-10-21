package al.logger.client.bridge.bridges;

import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;

import java.io.IOException;

public class GuiScreenBridge {
    @ExportObfuscate(name = "initGui")
    public void initGui(){

    }
    @ExportObfuscate(name = "drawScreen")
    public void drawScreen(int mouseX, int mouseY, float partialTicks){

    }
    @ExportObfuscate(name = "onGuiClosed")
    public void onGuiClosed() {

    }
    @ExportObfuscate(name = "updateScreen")
    public void updateScreen() {
    }
    @ExportObfuscate(name = "mouseReleased")
    public void mouseReleased(int p_mouseReleased_1_, int p_mouseReleased_2_, int p_mouseReleased_3_) {

    }
    @ExportObfuscate(name = "keyTyped")
    public boolean keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) throws IOException {
        return true;
    }
    @ExportObfuscate(name = "mouseClicked")
    public void mouseClicked(int p_mouseClicked_1_, int p_mouseClicked_2_, int p_mouseClicked_3_) throws IOException {

    }
}
