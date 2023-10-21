#ifndef COMPONENTBASE_H
#define COMPONENTBASE_H

#include <imgui.h>
#include <string>

class Position {

private:
    float x;
    float y;
    float width;
    float height;

public:

    Position(float x, float y, float width, float height) : x(x), y(y), width(width), height(height) {}

    Position() : x(0), y(0), width(0), height(0) {}

    void setX(float x) {
        this->x = x;
    }

    void setY(float y) {
        this->y = y;
    }

    void setWidth(float width) {
        this->width = width;
    }

    void setHeight(float height) {
        this->height = height;
    }

    float getX() {
        return x;
    }

    float getY() {
        return y;
    }

    float getWidth() {
        return width;
    }

    float getHeight() {
        return height;
    }
};

class ComponentBase {
public:
    std::string elementName;
    Position position;
    Position boxPosition;
    bool isMouseHover = false;
    bool isEdit = false;
    bool isMouseDown = false;
    float mouseX = 0;
    float mouseY = 0;
    float moveX = 0.0f;
    float moveY = 0.0f;
    float heightBase = 0.0f;
    float widthBase = 0.0f;
    bool visibility = true;
    bool anti = false;
    bool editNormalStage = false;

    ComponentBase(std::string elementName) : elementName(elementName) {
    }

    ComponentBase(std::string elementName, float x, float y, float width, float height) : elementName(elementName), position{ x, y, width, height } {
    }

    ComponentBase(std::string elementName, float width, float height) : elementName(elementName), position(0, 0, width, height) {
    }

    void drawComponent(float x, int mouseX, int mouseY, bool isEdit) {
        updateComponent(x, 0, 0, 0, mouseX, mouseY, isEdit);
    }

    void drawComponent(int mouseX, int mouseY, bool isEdit) {
        updateComponent(0, 0, 0, 0, mouseX, mouseY, isEdit);
    }

    void drawComponent(float x, float y, int mouseX, int mouseY, bool isEdit) {
        updateComponent(x, y, 0, 0, mouseX, mouseY, isEdit);
    }

    void drawComponent(float x, float y, float width, int mouseX, int mouseY, bool isEdit) {
        updateComponent(x, y, width, 0, mouseX, mouseY, isEdit);
    }

    void drawComponent(float x, float y, float width, float height, int mouseX, int mouseY, bool isEdit) {
        updateComponent(x, y, width, height, mouseX, mouseY, isEdit);
    }

    void updateComponent(float x, float y, float width, float height, int mouseX, int mouseY, bool isEdit) {
        if (!visibility && !isEdit) {
            return;
        }
        else if (!isEdit) {
            isEdit = editNormalStage;
        }
        if (x != 0) position.setX(x);
        if (y != 0) position.setY(y);
        if (width != 0) position.setWidth(width);
        if (height != 0) position.setHeight(height);
        this->mouseX = mouseX;
        this->mouseY = mouseY;
        this->isEdit = isEdit;
        this->isMouseHover = isHovered(position.getX(), position.getY(), position.getWidth(), position.getHeight(), mouseX, mouseY);
        Component();
    }

    void Component() {
        if (!visibility && !isEdit) {
            return;
        }

        if (isEdit) {
            if (isMouseDown) {
                if (moveX == 0 && moveY == 0) {
                    moveX = mouseX - position.getX() - 300;
                    moveY = mouseY - position.getY() - 300;
                }
                else {
                    position.setX((mouseX - moveX - 300));
                    position.setY((mouseY - moveY - 300));
                }
            }
            else if (moveX != 0.0f || moveY != 0.0f) {
                moveX = 0.0f;
                moveY = 0.0f;
            }
        }

        DrawElement();
    }

    bool mouseClicked(float mouseX, float mouseY, int mouseButton) {
        this->mouseX = mouseX;
        this->mouseY = mouseY;
        this->isMouseHover = isHovered(position.getX(), position.getY(), position.getWidth(), position.getHeight(), mouseX, mouseY);
        bool isSelect = MouseClicked(mouseX, mouseY, mouseButton);
        if (!isSelect) {
            if (isMouseHover) {
                if (mouseButton == 0) {
                    isMouseDown = true;
                }
            }
        }
        return isSelect;
    }

    bool mouseReleased(float mouseX, float mouseY, int mouseStatus) {
        this->mouseX = mouseX;
        this->mouseY = mouseY;
        this->isMouseHover = isHovered(position.getX(), position.getY(), position.getWidth(), position.getHeight(), mouseX, mouseY);
        if (mouseStatus == 0) {
            isMouseDown = false;
        }
        return MouseReleased(mouseX, mouseY, mouseStatus);
    }

    bool isHovered(float x, float y, float width, float height, int mouseX, int mouseY) {
        width += x;
        height += y;
        if (mouseX >= x && mouseX <= width && mouseY >= y && mouseY <= height) {
            return true;
        }
        return false;
    }


    virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) = 0;
    virtual void update() = 0;
    virtual void update(ComponentBase* parent) = 0;
    virtual void KeyTyped(int code, wchar_t in_char) = 0;

private:

    virtual void DrawElement() = 0;
    virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton) = 0;
    virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus) = 0;


};

#endif // !COMPONENTBASE_H


