package al.logger.client.ui.screens.logger.Components.Items.ComboBox;

import al.logger.client.ui.screens.logger.Components.Items.ListItem;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.value.impls.ModeValue;

import java.awt.*;
import java.util.ArrayList;

public class ComboBox extends ComponentBase {


    public ListItem parent;
    public ModeValue value;
    public ArrayList<ComboBoxItem> comboBoxItems = new ArrayList<>();
    public Animation positionAnimation = new Animation();
    public float elementHeight = 0f;
    public boolean openness = false;


    public ComboBox(ModeValue doubleValue, ListItem parent) {
        super("ComboBox");
        this.value = doubleValue;
        this.parent = parent;
        for (Enum value : this.value.getValues()) {
            comboBoxItems.add(new ComboBoxItem(value, this));
        }

    }

    @Override
    public void _initElements() {
        this.position.setHeight(13);
        this.heightBase = this.position.getHeight();
    }

    @Override
    public void _drawElement() {
        String text = this.value.getValue().name();
        float textWidth = FontLoadersWithChinese.hongMengBlod17.getStringWidth(text);
        FontLoadersWithChinese.hongMengBlod17.drawString(this.value.getName(), this.position.getX(), this.position.getY() + 4, new Color(255, 255, 255).getRGB());
        FontLoadersWithChinese.hongMengBlod17.drawString(text, this.position.getX() + this.position.getWidth() - textWidth, this.position.getY() + 4, new Color(128, 128, 128).getRGB());

        positionAnimation.start(positionAnimation.getValue(), openness ? 1.0 : 0.0, 0.1f, Type.EASE_OUT_QUAD);
        positionAnimation.update();

        float comboxItemY = this.position.getY() + this.heightBase + 4f;
        float posY = comboxItemY;
        for (ComboBoxItem comboBoxItem : comboBoxItems) {
            comboBoxItem.drawComponent(this.position.getX(), posY, this.position.getWidth(), mouseX, mouseY, false);
            posY += comboBoxItem.getPosition().getHeight() + 4f;
        }

        RenderUtil.drawRect(this.position.getX(), this.position.getY() + this.heightBase + 4f, this.position.getWidth(), elementHeight, new Color(38, 38, 38, (int) (255 * (1 - positionAnimation.getValue()))).getRGB());
        this.elementHeight = posY - comboxItemY;
        this.position.setHeight(this.heightBase + (float) (this.elementHeight * positionAnimation.getValue()));
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (this.openness) {
            for (ComboBoxItem comboBoxItem : comboBoxItems) {
                if (comboBoxItem.mouseClicked(mouseX, mouseY, mouseButton)) {
                    return true;
                }
            }
        }
        if (isMouseHover) {
            openness = !openness;
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
        this.visibility = this.value.callBack();
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {

    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {

    }
}
