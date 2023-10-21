package al.logger.client.ui.bases;

import al.logger.client.AccessInstance;
import al.logger.client.ui.bases.styles.NormalStyle;
import al.logger.client.utils.ScreenUtil;
import al.logger.client.utils.animation.TimerUtils;
import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import lombok.Getter;
import lombok.Setter;

public abstract class ComponentBase implements AccessInstance {

    @Getter
    @Setter
    protected Minecraft mc = Minecraft.getMinecraft();

    @Getter
    @Setter
    protected String elementName;

    @Getter
    @Setter
    protected Position position = new Position(0, 0, 0, 0);

    @Getter
    @Setter
    protected boolean isMouseHover = false;

    @Getter
    @Setter
    protected boolean isEdit = false;

    @Getter
    @Setter
    protected boolean isMouseDown = false;

    @Getter
    @Setter
    protected int mouseX = 0;

    @Getter
    @Setter
    protected int mouseY = 0;

    @Getter
    @Setter
    protected float moveX = 0f;

    @Getter
    @Setter
    protected float moveY = 0f;

    @Getter
    @Setter
    protected float heightBase = 0f;

    @Getter
    @Setter
    protected float widthBase = 0f;

    @Getter
    @Setter
    protected boolean visibility = true;

    @Getter
    @Setter
    protected TimerUtils timerUtils = new TimerUtils();

    @Getter
    @Setter
    protected int timeStamp = 0;

    @Getter
    @Setter
    protected int maxTimeStamp = 100;

    @Getter
    @Setter
    protected Animation whenAnimation = new Animation();

    @Getter
    @Setter
    protected NormalStyle styles = new NormalStyle();

    @Getter
    @Setter
    protected boolean editNormalStage = false;

    @Getter
    @Setter
    protected boolean activate = false;

    @Getter
    @Setter
    protected boolean isDrag = false;


    public ComponentBase(String elementName) {
        this.elementName = elementName;
        this._initElements();
    }

    public ComponentBase(String elementName, float x, float y, float width, float height) {
        this.elementName = elementName;
        this.position.setX(x);
        this.position.setY(y);
        this.position.setWidth(width);
        this.position.setHeight(height);
        this._initElements();
    }

    public ComponentBase(String elementName, float width, float height) {
        this.elementName = elementName;
        this.position.setWidth(width);
        this.position.setHeight(height);
        this._initElements();
    }

    public void drawComponent(float x, int mouseX, int mouseY, boolean isEdit) {
        updateComponent(x, 0, 0, 0, mouseX, mouseY, isEdit);
    }

    public void drawComponent(int mouseX, int mouseY, boolean isEdit) {
        updateComponent(0, 0, 0, 0, mouseX, mouseY, isEdit);
    }

    public void drawComponent(float x, float y, int mouseX, int mouseY, boolean isEdit) {
        updateComponent(x, y, 0, 0, mouseX, mouseY, isEdit);
    }

    public void drawComponent(float x, float y, float width, int mouseX, int mouseY, boolean isEdit) {
        updateComponent(x, y, width, 0, mouseX, mouseY, isEdit);
    }

    public void drawComponent(float x, float y, float width, float height, int mouseX, int mouseY, boolean isEdit) {
        updateComponent(x, y, width, height, mouseX, mouseY, isEdit);
    }

    private void updateComponent(float x, float y, float width, float height, int mouseX, int mouseY, boolean isEdit) {
        if (!visibility && !isEdit) {
            return;
        } else if (!isEdit) {
            isEdit = editNormalStage;
        }
        if (x != 0) this.position.setX(x);
        if (y != 0) this.position.setY(y);
        if (width != 0) this.position.setWidth(width);
        if (height != 0) this.position.setHeight(height);
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.isEdit = isEdit;
        this.isMouseHover = ScreenUtil.isHovered(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), mouseX, mouseY);
        this.Component();
    }

    private void Component() {

        if (!visibility && !isEdit) {
            return;
        }

        if (isEdit) {
            if (this.isMouseDown) {
                if (moveX == 0 && moveY == 0) {
                    moveX = mouseX - this.position.getX() - 300;
                    moveY = mouseY - this.position.getY() - 300;
                } else {
                    this.position.setX((mouseX - moveX - 300));
                    this.position.setY((mouseY - moveY - 300));
                }
            } else if (moveX != 0f || moveY != 0f) {
                moveX = 0f;
                moveY = 0f;
            }
        }

        this._drawElement();
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.isMouseHover = ScreenUtil.isHovered(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), mouseX, mouseY);
        boolean isSelect = this._mouseClicked(mouseX, mouseY, mouseButton);
        if (!isSelect) {
            if (isMouseHover) {
                if (mouseButton == 0) {
                    this.isMouseDown = true;
                }
            }
        }
        return isSelect;
    }

    public boolean mouseReleased(int mouseX, int mouseY, int mouseStatus) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.isMouseHover = ScreenUtil.isHovered(this.position.getX(), this.position.getY(), this.position.getWidth(), this.position.getHeight(), mouseX, mouseY);
        if (mouseStatus == 0) {
            this.isMouseDown = false;
        }
        return this._mouseReleased(mouseX, mouseY, mouseStatus);
    }

    public void update(ComponentBase parent) {

    }

    public boolean mouseDWheel(int mouseX, int mouseY, int mouseDWheel){
        _mouseDWheel(mouseX, mouseY, mouseDWheel);
        return false;
    }

    protected abstract void _initElements();

    protected abstract void _drawElement();

    protected abstract boolean _mouseClicked(int mouseX, int mouseY, int mouseButton);

    protected abstract boolean _mouseReleased(int mouseX, int mouseY, int mouseStatus);

    public abstract void _mouseDWheel(int mouseX, int mouseY, int mouseDWheel);

    public abstract void update();

    public abstract void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_);

}