#ifndef BUTTON_H
#define BUTTON_H

#include "ComponentBase.h"
#include "animation.h"
#include "Fonts.h"
#include <functional>


class Button :
    public ComponentBase
{
private:
    std::function<void()> MouseClickedLambda;
    Animation alphaAnimation{ 10, 30, 4.0f, TweenUtil::easeOut };

public:
    Button(std::string_view elementName, std::function<void()> MouseClickedLambda) : ComponentBase(elementName.data()), MouseClickedLambda(MouseClickedLambda) {
        this->position.setWidth(200);
        this->position.setHeight(40);
    }

    virtual void update() override;

    virtual void update(ComponentBase* parent) override;

    virtual void DrawElement();

    virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton);

    virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus);

    virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel);

    virtual void KeyTyped(int code, wchar_t in_char) override;


};


#endif