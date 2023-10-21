package al.logger.client.features.modules.impls.Visual;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventKey;
import al.logger.client.event.client.EventMouse;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.templates.KeyStrokes.KeyStrokesTemplate;

public class KeyStrokes extends Module {
    public KeyStrokes() {
        super("KeyStrokes", "display the button you pressed", Category.Visual);
    }

    @Listener
    public void onRender2D(EventRender2D eventRender2D) {
        KeyStrokesTemplate keyStrokesTemplate = Logger.getInstance().getGuiManager().keyStrokesTemplate;
        keyStrokesTemplate.drawComponent(0, 0, false);
    }

    @Listener
    public void keyTyped(EventKey eventKey) {
        KeyStrokesTemplate keyStrokesTemplate = Logger.getInstance().getGuiManager().keyStrokesTemplate;
        keyStrokesTemplate.keyTyped((char) 0, eventKey.getKey());
    }

    @Listener
    public void onMouse(EventMouse eventMouse) {
        KeyStrokesTemplate keyStrokesTemplate = Logger.getInstance().getGuiManager().keyStrokesTemplate;
        if (eventMouse.isDown()) {
            keyStrokesTemplate.mouseClicked(0, 0, eventMouse.getButton());
        }
    }
}
