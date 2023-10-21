package al.logger.client.features.modules.impls.World;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventKey;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import org.lwjgl.input.Keyboard;

public class KeyInputHandler extends Module {
    public KeyInputHandler() {
        super("KeyInputHandler", "Enable KeyInput Control", Category.World);
        this.setEnabled(true);
        this.setHide(true);
    }

    @Listener
    public void onKey(EventKey eventKey) {
        if (!mc.getCurrentScreen().isNull())
            return;
        if (eventKey.getKey() == Keyboard.KEY_NONE)
            return;
        try{
            int key = eventKey.getKey();
            if (Keyboard.getEventKeyState()) {
                for (Module mod :Logger.getInstance().moduleManager.getModules()) {
                    if (mod.getKeyCode() == key){
                        mod.toggle();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
