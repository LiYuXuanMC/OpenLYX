#ifndef TEXTBOX_H
#define TEXTBOX_H

#include "ComponentBase.h"
#include <codecvt>
#include "animation.h"

class TextBox :public ComponentBase {
private:
    std::string* text;
    ImFont* imFont;
    std::string normal;
    bool isPressed;
    bool isPassword;
    Animation alphaAnimation{ 10, 30, 4.0f, TweenUtil::easeOut };

public:
    virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) override;
    virtual void update() override;
    virtual void update(ComponentBase* parent) override;
    virtual void DrawElement() override;
    virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton) override;
    virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus) override;
    virtual void KeyTyped(int code, wchar_t in_char) override;

    TextBox(std::string_view elementName, std::string normal, std::string* text, ImFont* imFont,bool isPassword = false) : ComponentBase(elementName.data()), normal(normal), text(text), imFont(imFont),isPassword(isPassword)
    {
        isPressed = false;
    }

};

#endif
