#ifndef WINDOWGUI_H
#define WINDOWGUI_H

#include <imgui.h>

class WindowGui {
public:
    virtual void Setup_Style(ImGuiIO& io) = 0;
    virtual void Draw_GuiScreen(ImVec2& mouse) = 0;
    virtual void Mouse_Clicked(ImVec2& mouse, int mouseButton) = 0;
    virtual void Mouse_Released(ImVec2& mouse, int mouseButton) = 0;
    virtual void Mouse_DWheel(ImVec2& mouse, float dWheel) = 0;
    virtual void Key_Typed(int code, wchar_t in_char) = 0;
};

#endif // !WINDOWGUI_H
