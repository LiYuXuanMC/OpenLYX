package al.logger.client.ui.screens.logger.Components.Items.Switch;

import al.logger.client.ui.screens.logger.Components.Items.ListItem;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.value.impls.OptionValue;

import java.awt.*;

public class Switch extends ComponentBase {
    public ListItem parent;
    public OptionValue value;
    public SwitchButton switchButton;

    public Switch(OptionValue value, ListItem parent) {
        super("Switch");
        this.value = value;
        this.parent = parent;
        this.switchButton = new SwitchButton(value, this);
    }

    @Override
    public void _initElements() {
        this.position.setHeight(13);
    }

    @Override
    public void _drawElement() {
        FontLoadersWithChinese.hongMengBlod17.drawString(this.value.getName(), this.position.getX(), this.position.getY() + 4, new Color(255, 255, 255).getRGB());
        this.switchButton.drawComponent(this.position.getX() + this.position.getWidth() - 26, this.position.getY(), mouseX, mouseY, false);
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(this.switchButton.mouseClicked(mouseX, mouseY, mouseButton)){
            return true;
        }
        return false;
    }

    @Override
    public boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        if(this.switchButton.mouseReleased(mouseX, mouseY, mouseStatus)){
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        this.visibility = this.value.callBack();
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

    }
}
