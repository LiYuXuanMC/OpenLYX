#include "GroupBox.h"
#include "render.h"

void GroupBox::DrawElement()
{
    //Debug Rect
    //RenderUtil::drawRect(this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight(), ImColor(255, 255, 255, 10), 0);

    float posY = this->position.getY();
    for (size_t i = 0; i < this->childs.size(); i++)
    {
        ComponentBase* child = this->childs[i];
        if (!child->visibility) {
            continue;
        }
        child->update();
        float childX = this->position.getX() + (this->position.getWidth() - child->position.getWidth()) / 2;
        child->drawComponent(childX, posY, mouseX, mouseY, false);
        if (child->position.getWidth() > this->position.getWidth()) {
            this->position.setWidth(child->position.getWidth());
        }
        posY += child->position.getHeight() + margin;
    }
    this->position.setHeight(posY - this->position.getY() - margin);
}

bool GroupBox::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
    for (size_t i = 0; i < this->childs.size(); i++)
    {
        if (this->childs[i]->mouseClicked(mouseX, mouseY, mouseButton)) {
            return true;
        }
    }
    return false;
}

bool GroupBox::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
    for (size_t i = 0; i < this->childs.size(); i++)
    {
        if (this->childs[i]->mouseReleased(mouseX, mouseY, mouseStatus)) {
            return true;
        }
    }
    return false;
}

bool GroupBox::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
    for (size_t i = 0; i < this->childs.size(); i++)
    {
        if (this->childs[i]->MouseDWheel(mouseX,mouseY, mouseDWheel)) {
            return true;
        }
    }
    return false;
}

void GroupBox::update()
{
}

void GroupBox::update(ComponentBase* parent)
{
}

void GroupBox::addComponent(ComponentBase* component)
{
    childs.push_back(component);
}

void GroupBox::KeyTyped(int code, wchar_t in_char)
{
    for (size_t i = 0; i < this->childs.size(); i++)
    {
        this->childs[i]->KeyTyped(code, in_char);
    }
}
