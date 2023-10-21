package al.logger.client.ui.screens.logger.Container;

import al.logger.client.features.modules.Category;
import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.screens.logger.Components.Tabs.Tab;
import al.logger.client.ui.screens.logger.LoggerScreen;

import java.util.ArrayList;

public class TabControl extends ComponentBase {

    public LoggerScreen parent;
    public ArrayList<ComponentBase> elements = new ArrayList<>();
    public ListBox listBox = new ListBox(this);
    public Category seletedCategory = Category.World;

    public TabControl(LoggerScreen parent) {
        super("TabControl");
        this.parent = parent;
        for (Category value : Category.values()) {
            elements.add(new Tab(value, this));
        }
    }


    @Override
    public void _initElements() {
        this.position.setWidth(103.5f);
    }

    @Override
    public void _drawElement() {
        listBox.drawComponent(this.position.getX() + this.position.getWidth() + 9f, this.position.getY() - 15f, mouseX, mouseY, false);
        float posY = this.position.getY();
        for (ComponentBase element : elements) {
            element.update();
            element.drawComponent(this.position.getX(), posY, this.mouseX, this.mouseY, false);
            posY += element.getPosition().getHeight() + 4;
        }
        this.position.setHeight(posY - this.position.getY());
    }

    @Override
    public boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (listBox.mouseClicked(mouseX, mouseY, mouseButton)) {
            return true;
        }
        for (ComponentBase element : elements) {
            if (element.mouseClicked(mouseX, mouseY, mouseButton)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        if (listBox.mouseReleased(mouseX, mouseY, mouseStatus)) {
            return true;
        }
        for (ComponentBase element : elements) {
            if (element.mouseReleased(mouseX, mouseY, mouseStatus)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update() {
        listBox.update();
    }

    @Override
    public void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel) {
        this.listBox._mouseDWheel(mouseX, mouseY, mouseDWheel);
    }

    @Override
    public void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) {
        this.listBox.keyTyped(p_keyTyped_1_, p_keyTyped_2_);
    }
}
