package al.logger.client.ui.templates.KeyStrokes;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.bases.Position;
import org.lwjgl.input.Keyboard;

import java.util.concurrent.CopyOnWriteArrayList;

public class KeyStrokesTemplate extends ComponentBase {

    public CopyOnWriteArrayList<ComponentBase> KeyStrokeButtons = new CopyOnWriteArrayList<>();

    public KeyStrokesTemplate() {
        super("KeyStrokesTemplate");
        KeyStrokeButtons.clear();

        KeyStrokeButtons.add(new KeyStrokesButton("", Keyboard.KEY_NONE, new Position(0, 0, 20f, 20f), true));
        KeyStrokeButtons.add(new KeyStrokesButton("W", Keyboard.KEY_W, new Position(0, 0, 20f, 20f), true));
        KeyStrokeButtons.add(new KeyStrokesButton("", Keyboard.KEY_NONE, new Position(0, 0, 20f, 20f), true));
        KeyStrokeButtons.add(new KeyStrokesButton("A", Keyboard.KEY_A, new Position(0, 0, 20f, 20f), true));
        KeyStrokeButtons.add(new KeyStrokesButton("S", Keyboard.KEY_S, new Position(0, 0, 20f, 20f), true));
        KeyStrokeButtons.add(new KeyStrokesButton("D", Keyboard.KEY_D, new Position(0, 0, 20f, 20f), true));
        KeyStrokeButtons.add(new KeyStrokesButton("LMB", 0, new Position(0, 0, 31f, 20f), false));
        KeyStrokeButtons.add(new KeyStrokesButton("RMB", 1, new Position(0, 0, 31f, 20f), false));
        KeyStrokeButtons.add(new KeyStrokesButton("", Keyboard.KEY_NONE, new Position(0, 0, 0, 0), false));
        KeyStrokeButtons.add(new KeyStrokesButton("SPACE", Keyboard.KEY_SPACE, new Position(0, 0, 64f, 20f), true));
        KeyStrokeButtons.add(new KeyStrokesButton("", Keyboard.KEY_NONE, new Position(0, 0, 0, 0), false));
        KeyStrokeButtons.add(new KeyStrokesButton("", Keyboard.KEY_NONE, new Position(0, 0, 0, 0), false));
    }

    @Override
    public void _initElements() {
    }

    @Override
    public void _drawElement() {
        int wrapSize = 3;
        float posX = this.position.getX();
        float posY = this.position.getY();
        int i = 0;
        int addI = 0;
        for (ComponentBase keyStrokeButton : KeyStrokeButtons) {
            if (i >= wrapSize) {
                i = 0;
                posX = this.position.getX();
                posY += keyStrokeButton.getPosition().getHeight() + 2f;
            }
            keyStrokeButton.update();
            keyStrokeButton.drawComponent(posX, posY, mouseX, mouseY, false);
            if (addI == KeyStrokeButtons.size() - 1) {
                posY += keyStrokeButton.getPosition().getHeight() + 2f;
            }
            if (keyStrokeButton.getPosition().getWidth() != 0) {
                posX += keyStrokeButton.getPosition().getWidth() + 2f;
            }
            i++;
            addI++;
        }
        this.position.setWidth(posX - this.position.getX() - 2f);
        this.position.setHeight(posY - this.position.getY() + 16f);
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (ComponentBase keyStrokeButton : KeyStrokeButtons) {
            keyStrokeButton.mouseClicked(mouseX, mouseY, mouseButton);
        }
        if (isMouseHover) {
            this.isMouseDown = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {
        for (ComponentBase keyStrokeButton : KeyStrokeButtons) {
            keyStrokeButton.keyTyped(p_keyTyped_1_, p_keyTyped_2_);
        }
    }
}
