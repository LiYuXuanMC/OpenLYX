package al.logger.client.ui.screens.logger.Components.Items.ColorView;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.screens.logger.Components.Items.ListItem;
import al.logger.client.utils.ScreenUtil;
import al.logger.client.utils.animation.Type;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.utils.animation.logger.Smoother;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.value.impls.ColorValue;
import al.logger.client.value.impls.MultipleColorValue;
import lombok.Getter;
import lombok.Setter;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class ColorView extends ComponentBase {

    @Getter
    @Setter
    private MultipleColorValue value;

    @Getter
    @Setter
    private ListItem parent;

    @Getter
    @Setter
    private ArrayList<ColorEdit> colorEdits = new ArrayList<>();
    private Smoother scrollAnimation = new Smoother(0, 150);
    private float scrollTo = 0;
    private float componentWidth = 0;
    private boolean isOpenEdit = false;

    public ColorView(MultipleColorValue value, ListItem parent) {
        super("ColorView");
        this.value = value;
        this.parent = parent;
        for (ColorValue colorValue : value.getValue()) {
            colorEdits.add(new ColorEdit(colorValue, this.parent));
        }
    }

    @Override
    protected void _initElements() {
        this.heightBase = 36;
        this.position.setHeight(36);
    }

    @Override
    protected void _drawElement() {
        this.isMouseHover = ScreenUtil.isHovered(this.position.getX() - 10, this.position.getY(), this.position.getWidth() + 20, this.position.getHeight(), mouseX, mouseY);
        this.position.setHeight(this.heightBase + (this.isOpenEdit ? 94 : 0));
        FontLoadersWithChinese.hongMengBlod17.drawString(this.value.getName(), this.position.getX(), this.position.getY() + 4, 0xFFFFFFFF);
        scrollTo = Math.min(0f, Math.max(-this.componentWidth + this.position.getWidth(), scrollTo));
        scrollAnimation.update(scrollTo, System.currentTimeMillis());
        float appendX = (float) (this.position.getX() + scrollAnimation.get());
        float posX = appendX;
        for (ColorEdit colorEdit : colorEdits) {
            colorEdit.update();
            if (isOpenEdit) {
                colorEdit.setMiniView(false);
                colorEdit.drawComponent(posX, this.position.getY() + 16, 80f, mouseX, mouseY, false);
            } else {
                colorEdit.setMiniView(true);
                colorEdit.drawComponent(posX, this.position.getY() + 16, 16f, 16f, mouseX, mouseY, false);
            }
            posX += colorEdit.getPosition().getWidth() + 6f;
        }
        this.componentWidth = posX - appendX;
    }

    @Override
    protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isOpenEdit) {
            for (ColorEdit colorEdit : colorEdits) {
                if (colorEdit.mouseClicked(mouseX, mouseY, mouseButton)) {
                    return true;
                }
            }
        }
        if (isMouseHover) {
            isOpenEdit = !isOpenEdit;
        }
        return false;
    }

    @Override
    protected boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        if (isOpenEdit) {
            for (ColorEdit colorEdit : colorEdits) {
                if (colorEdit.mouseReleased(mouseX, mouseY, mouseStatus)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {
    }

    @Override
    public boolean mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {
        if (mouseDWheel != 0 && isOpenEdit && isMouseHover && componentWidth > this.position.getWidth()) {
            if (mouseDWheel < 0) {
                if (this.position.getWidth() < this.componentWidth) {
                    scrollTo -= 15.31415926f;
                }
            } else {
                scrollTo += 15.31415926f;
            }
            colorEdits.forEach(colorEdit -> colorEdit._mouseDWheel(mouseX, mouseY, mouseDWheel));
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        this.visibility = this.value.callBack();
    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {
        colorEdits.forEach(colorEdit -> colorEdit.keyTyped(p_keyTyped_1_, p_keyTyped_2_));
    }
}