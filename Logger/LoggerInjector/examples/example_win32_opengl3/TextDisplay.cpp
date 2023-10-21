#include "TextDisplay.h"
#include "render.h"

bool TextDisplay::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
    return false;
}

void TextDisplay::update()
{
}

void TextDisplay::update(ComponentBase* parent)
{
}

void TextDisplay::DrawElement()
{
    if (this->position.getWidth() == 0 && this->position.getWidth() == 0) {
        ImVec2 textSize = imFont->CalcTextSizeA(imFont->FontSize, FLT_MAX, 0, renderText.c_str());
        this->position.setWidth(textSize.x);
        this->position.setHeight(textSize.y);
    }
    RenderUtil::drawText(this->position.getX(), this->position.getY(), renderText, this->fontColor, this->imFont);
}

bool TextDisplay::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
    return false;
}

bool TextDisplay::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
    return false;
}

void TextDisplay::KeyTyped(int code, wchar_t in_char)
{
}
