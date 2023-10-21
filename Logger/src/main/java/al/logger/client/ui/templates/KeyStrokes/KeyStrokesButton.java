package al.logger.client.ui.templates.KeyStrokes;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.bases.Position;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.wrapper.lwjgl.KeyBoard;
import lombok.Getter;
import lombok.Setter;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class KeyStrokesButton extends ComponentBase {

    @Getter
    @Setter
    private String value;
    @Getter
    @Setter
    private int keycode;
    private final CopyOnWriteArrayList<KeyStrokesChanger> onChangers = new CopyOnWriteArrayList<>();
    private boolean keyboard;

    public KeyStrokesButton(String value, int keycode, Position position, boolean keyboard) {
        super("KeyStrokesButton");
        this.position = position;
        this.value = value;
        this.keycode = keycode;
        this.keyboard = keyboard;
    }

    @Override
    public void _initElements() {
    }

    @Override
    public void _drawElement() {
        if (value.isEmpty() && keycode == Keyboard.KEY_NONE) {
            return;
        }


        RenderUtil.upShadow(this.position.getX() + 1, this.position.getY() + 1, this.position.getWidth() - 2, this.position.getHeight() - 2, 2, 2);
        RenderUtil.drawRound(this.position.getX() + 1, this.position.getY() + 1, this.position.getWidth() - 2, this.position.getHeight() - 2, 0f, new Color(29, 29, 29, 80));
        RenderUtil.doStencil(this.position.getX() + 1, this.position.getY() + 1, this.position.getWidth() - 2, this.position.getHeight() - 2, 0f);
        float textWidth = FontLoadersWithChinese.hongMengSR15.getStringWidth(this.value);
        float textHeight = FontLoadersWithChinese.hongMengSR15.getStringHeight(this.value);
        float textX = this.position.getX() + (this.position.getWidth() - textWidth) / 2;
        float textY = this.position.getY() + (this.position.getHeight() - textHeight) / 2;
        FontLoadersWithChinese.hongMengSR15.drawString(this.value, textX, textY, new Color(255, 255, 255,225).getRGB());


        for (KeyStrokesChanger onChanger : onChangers) {
            if (onChanger.alphaAnimation.getValue() == 0.0) {
                onChangers.remove(onChanger);
                continue;
            }
            if (onChanger.getPosition().getWidth() >= this.position.getWidth() + 30) {
                if (!(onChangers.get(onChangers.size() - 1) == onChanger && (KeyBoard.isKeyDown(this.keycode) || Mouse.isButtonDown(this.keycode)))) {
                    onChanger.alphaAnimation.start(onChanger.alphaAnimation.getValue(), 0.0, 0.2f, Type.LINEAR);
                    onChanger.alphaAnimation.update();
                }
            }
            onChanger.update();
            onChanger.drawComponent(mouseX, mouseY, false);
        }

        RenderUtil.uninstallStencil();
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == this.keycode && !keyboard) {
            if (onChangers.size() > 5) {
                try {
                    onChangers.remove(0);
                } catch (Exception e) {
                    //因为多线程的原因，可能导致remove失败，但是不影响使用
                }
            }
            onChangers.add(new KeyStrokesChanger(this));
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
        if (p_keyTyped_2_ == this.keycode && keyboard) {
            if (onChangers.size() > 5) {
                try {
                    onChangers.remove(0);
                } catch (Exception e) {
                    //因为多线程的原因，可能导致remove失败，但是不影响使用
                }
            }
            onChangers.add(new KeyStrokesChanger(this));
        }
    }
}
