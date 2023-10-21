package al.logger.client.ui.screens.logger.Components.Items;

import al.logger.client.Logger;
import al.logger.client.features.modules.Module;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.screens.logger.Components.Items.ColorView.ColorEdit;
import al.logger.client.ui.screens.logger.Components.Items.ColorView.ColorView;
import al.logger.client.ui.screens.logger.Components.Items.ComboBox.ComboBox;
import al.logger.client.ui.screens.logger.Components.Items.Slider.Slider;
import al.logger.client.ui.screens.logger.Components.Items.Switch.Switch;
import al.logger.client.ui.screens.logger.Container.ListBox;
import al.logger.client.utils.ScreenUtil;
import al.logger.client.utils.animation.logger.Smoother;
import al.logger.client.utils.fontRender.FontLoadersWithChinese;
import al.logger.client.utils.render.RenderUtil;
import al.logger.client.value.impls.*;
import al.logger.client.value.bases.Value;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import scala.tools.nsc.doc.base.comment.Bold;

import java.awt.*;
import java.util.ArrayList;

public class ListItem extends ComponentBase {

    public Module module;
    public ListBox parent;
    public ItemButton itemButton;
    public Smoother positionAnimation = new Smoother(0, 120);
    public float elementHeight = 0f;
    public ArrayList<ComponentBase> values = new ArrayList<>();
    public Smoother yAnimation = new Smoother(0, 120);
    public BindItem bindItem;

    public ListItem(Module module, ListBox parent) {
        super("ListItem");
        this.module = module;
        this.parent = parent;
        this.heightBase = this.position.getHeight();
        this.itemButton = new ItemButton(this.module, this);
        this.bindItem = new BindItem(this.module);
        for (Value value : this.module.getValues()) {
            if (value instanceof OptionValue) {
                values.add(new Switch((OptionValue) value, this));
            } else if (value instanceof DoubleValue) {
                values.add(new Slider((DoubleValue) value, this));
            } else if (value instanceof ModeValue) {
                values.add(new ComboBox((ModeValue) value, this));
            } else if (value instanceof ColorValue) {
                values.add(new ColorEdit((ColorValue) value, this));
            } else if (value instanceof MultipleColorValue) {
                values.add(new ColorView((MultipleColorValue) value, this));
            }
        }
    }

    @Override
    public void _initElements() {
        this.position.setWidth(480);
        this.position.setHeight(65);
    }

    @Override
    public void _drawElement() {
        if (this.isMouseDown && this.parent.isItemDrag && this.isDrag) {
            return;
        }
        float posX = this.position.getX() + 18f;
        float posWidth = this.position.getWidth() - 36f;
        RenderUtil.upShadow(posX, (float) yAnimation.get(), posWidth, this.position.getHeight(), 4, 4);
        boolean isNotOutline = this.parent.parent.parent.IsNotOutline;
        if (this.isMouseDown && this.parent.isItemDrag) {
            RenderUtil.drawOutline(posX - 4, (float) yAnimation.get() - 4, posWidth + 8, this.position.getHeight() + 8, 4f, 2f, new Color(61, 119, 193), isNotOutline);
        }
        RenderUtil.drawRoundOutline(posX, (float) yAnimation.get(), posWidth, this.position.getHeight(), 4f, 2f, new Color(38, 38, 38), new Color(49, 49, 49), isNotOutline);
        this.bindItem.drawComponent(posX + 11f, (float) yAnimation.get() + 11f, mouseX, mouseY, false);
        FontLoadersWithChinese.hongMengBlod17.drawString(this.module.getDisplayName(), posX + 11f, (float) yAnimation.get() + 30f, new Color(255, 255, 255, 255).getRGB());
        RenderUtil.drawFullImage(Logger.getInstance().resourceManager.getResourceLocation("clickgui/" + this.module.getHazard().name() + ".png"), posX + 11f, (float) yAnimation.get() + 44f, 9, 9, 255);
        int size = FontLoadersWithChinese.hongMengSR14.formatString(this.module.getDescription(), posWidth - 34f).size();
        float addBase = FontLoadersWithChinese.hongMengSR14.getStringHeight(this.module.getDescription()) * size;
        if (size == 1) {
            addBase = 0f;
        } else {
            addBase += 4f;
        }
        FontLoadersWithChinese.hongMengSR14.drawSplitString(this.module.getDescription(), posX + 24f, (float) yAnimation.get() + 46f, posWidth - 34f, new Color(255, 255, 255, 128).getRGB());

        float titleHeight = this.heightBase + addBase;
        if (positionAnimation.get() != 0 && positionAnimation.get() > 0.01f) {

            GL11.glEnable(GL11.GL_SCISSOR_TEST);
            RenderUtil.doScissor(posX, (float) yAnimation.get(), posWidth, this.position.getHeight());

            float posY = (float) yAnimation.get() + titleHeight;
            for (ComponentBase value : values) {
                value.update();
                if (posY + value.getPosition().getHeight() > parent.getPosition().getY() && posY < parent.getPosition().getY() + parent.getPosition().getHeight()) {
                    value.drawComponent(posX + 11f, posY, posWidth - 19f, mouseX, mouseY, false);
                }
                if (value.isVisibility()) {
                    posY += value.getPosition().getHeight() + 6f;
                }
            }
            this.elementHeight = posY - ((float) yAnimation.get() + titleHeight) + 5f;

            GL11.glDisable(GL11.GL_SCISSOR_TEST);
        }

        positionAnimation.update(this.module.getOpenness() && !isMouseDown ? 1.0 : 0.0, System.currentTimeMillis());
        this.position.setHeight((float) (titleHeight + (this.elementHeight * positionAnimation.get())));
        this.itemButton.drawComponent(posX + posWidth - 34f, (float) yAnimation.get() + 11f, mouseX, mouseY, false);
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (this.module.getOpenness()) {
            for (ComponentBase value : values) {
                if (value.isVisibility()) {
                    if (value.mouseClicked(mouseX, mouseY, mouseButton)) {
                        return true;
                    }
                }
            }
        }
        if (this.itemButton.mouseClicked(mouseX, mouseY, mouseButton)) {
            return true;
        } else if (this.bindItem.mouseClicked(mouseX, mouseY, mouseButton)) {
            return true;
        } else if (ScreenUtil.isHovered(this.position.getX(), this.position.getY(), this.position.getWidth(), this.heightBase, mouseX, mouseY)) {
            this.parent.renderItem = this;
            if (mouseButton == 1 && this.module.getValues().size() > 0) {
                this.module.setOpenness(!this.module.getOpenness());
                return true;
            }
        } else {
            return isMouseHover;
        }
        return false;
    }

    @Override
    public boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        for (ComponentBase value : values) {
            if (value.isVisibility()) {
                if (value.mouseReleased(mouseX, mouseY, mouseStatus)) {
                    return true;
                }
            }
        }
        if (this.itemButton.mouseReleased(mouseX, mouseY, mouseStatus)) {
            return true;
        } else if (this.bindItem.mouseReleased(mouseX, mouseY, mouseStatus)) {
            return true;
        }
        return false;
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {
    }

    @Override
    public void update() {
        if (yAnimation.get() != this.position.getY() && parent.isItemDrag && !this.isMouseDown && (int) this.parent.scrollTo == (int) this.parent.scrollAnimation.get()) {
            yAnimation.update(this.position.getY(), System.currentTimeMillis());
        } else {
            yAnimation.setValue(this.position.getY());
        }
    }

    @Override
    public boolean mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {
        if (positionAnimation.get() != 0) {
            for (ComponentBase value : values) {
                if (value.mouseDWheel(mouseX, mouseY, mouseDWheel)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {
        this.bindItem.keyTyped(p_keyTyped_1_, p_keyTyped_2_);
    }
}
