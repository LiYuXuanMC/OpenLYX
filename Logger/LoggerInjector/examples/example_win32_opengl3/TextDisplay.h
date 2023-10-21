#ifndef TEXTDISPLAY_H
#define TEXTDISPLAY_H

#include "ComponentBase.h"

class TextDisplay : public ComponentBase {
private:
    ImFont* imFont;
    std::string renderText;
    ImColor fontColor;

public:
    TextDisplay(std::string elementName, ImFont* imFont) : ComponentBase(elementName), imFont(imFont), renderText(elementName), fontColor(ImColor(255,255,255)){
    }

    TextDisplay(std::string elementName, ImFont* imFont,ImColor fontColor) : ComponentBase(elementName), imFont(imFont), renderText(elementName), fontColor(fontColor){
    }

    virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) override;
    virtual void update() override;
    virtual void update(ComponentBase* parent) override;
    virtual void DrawElement() override;
    virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton) override;
    virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus) override;
    virtual void KeyTyped(int code, wchar_t in_char) override;

};


#endif // !TEXTDISPLAY_H


