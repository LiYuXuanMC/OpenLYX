
#ifndef GROUPBOX_H
#define GROUPBOX_H

#include "ComponentBase.h"
#include <vector>

class GroupBox : public ComponentBase {
private:
    std::vector<ComponentBase*> childs;
    float margin = 20;

public:
    GroupBox(std::string_view elementName, float margin) : ComponentBase(elementName.data()), margin(margin) {

    }

    void addComponent(ComponentBase* component);
    virtual void DrawElement() override;
    virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton) override;
    virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus) override;
    virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) override;
    virtual void update() override;
    virtual void update(ComponentBase* parent) override;
    virtual void KeyTyped(int code, wchar_t in_char) override;

};

#endif
