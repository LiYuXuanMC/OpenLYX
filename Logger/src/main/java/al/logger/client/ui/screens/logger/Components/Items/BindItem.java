package al.logger.client.ui.screens.logger.Components.Items;

import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class BindItem extends ComponentBase {

    public Module module;
    public boolean isBind = false;

    public BindItem(Module module) {
        super("BindItem");
        this.module = module;
    }

    @Override
    protected void _initElements() {
    }

    @Override
    protected void _drawElement() {
        String keyName = "Bind: " + Keyboard.getKeyName(this.module.getKeyCode());
        if (this.isBind) {
            keyName = "Press a key...";
        }
        this.getPosition().setWidth(FontLoadersWithChinese.hongMengSR14.getStringWidth(keyName));
        this.getPosition().setHeight(FontLoadersWithChinese.hongMengSR14.getStringHeight(keyName));
        FontLoadersWithChinese.hongMengSR14.drawString(keyName, this.getPosition().getX(), this.getPosition().getY(), new Color(255, 255, 255, 128).getRGB());
    }

    @Override
    protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isMouseHover) {
            this.isBind = true;
            return true;
        } else {
            this.isBind = false;
        }
        return false;
    }

    @Override
    protected boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        return false;
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {
        if (isBind) {
            if (p_keyTyped_2_ == Keyboard.KEY_DELETE) {
                this.module.setKeyCode(Keyboard.KEY_NONE);
                this.isBind = false;
            } else {
                this.module.setKeyCode(p_keyTyped_2_);
                this.isBind = false;
            }
        }
    }
}
