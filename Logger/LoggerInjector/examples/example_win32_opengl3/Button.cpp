#include "Button.h"
#include "render.h"



void Button::update()
{
}

void Button::update(ComponentBase* parent)
{
}

void Button::DrawElement()
{
    if (this->position.getWidth() == 0 && this->position.getWidth() == 0) {
        ImVec2 textSize = FontsLoader::GetInterSemiBold18()->CalcTextSizeA(FontsLoader::GetInterSemiBold18()->FontSize, FLT_MAX, 0, this->elementName.c_str());
    }

    alphaAnimation.update(0.2f, this->isMouseHover);

    ImVec2 textSize = FontsLoader::GetInterSemiBold18()->CalcTextSizeA(FontsLoader::GetInterSemiBold18()->FontSize, FLT_MAX, 0, this->elementName.c_str());
    float textX = this->position.getX() + (this->position.getWidth() - textSize.x) / 2;
    float textY = this->position.getY() + (this->position.getHeight() - textSize.y) / 2;

    auto renderEngine = ImGui::GetBackgroundDrawList();
    RenderUtil::drawRect(this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight(), ImColor(30, (int)alphaAnimation.getValue() + 100, 255, 255), 5);
    RenderUtil::drawText(textX, textY, this->elementName, ImColor(255, 255, 255, 255), FontsLoader::GetInterSemiBold18());
}

bool Button::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
    if (this->isMouseHover) {
        this->MouseClickedLambda();
        return true;
    }
    return false;
}

bool Button::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
    return false;
}

bool Button::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
    return false;
}

void Button::KeyTyped(int code, wchar_t in_char)
{
}

