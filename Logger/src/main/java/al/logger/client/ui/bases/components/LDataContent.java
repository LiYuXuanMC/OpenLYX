package al.logger.client.ui.bases.components;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.utils.render.RenderUtil;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LDataContent extends ComponentBase {

    private Color background = Color.WHITE;
    @Getter
    public ArrayList<ComponentBase> variables = new ArrayList<>();

    public void addComponent(ComponentBase... component) {
        this.variables.addAll(Arrays.asList(component));
    }

    public LDataContent setBackground(Color background) {
        this.background = background;
        return this;
    }

    public LDataContent(String elementName, float width, float height) {
        super(elementName, width, height);
        this.initVariables();
    }

    public LDataContent(String elementName, float x, float y, float width, float height) {
        super(elementName, x, y, width, height);
        this.initVariables();
    }

    public LDataContent(String elementName) {
        super(elementName);
        this.initVariables();
    }

    public void initVariables() {
    }

    @Override
    protected void _initElements() {

    }

    @Override
    protected void _drawElement() {
        RenderUtil.drawRect(position.getX(), position.getY(), position.getWidth(), position.getHeight(), background.getRGB());
    }

    @Override
    protected boolean _mouseClicked(int mouseX, int mouseY, int mouseButton) {
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

    }
}
