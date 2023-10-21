#include "TextBox.h"
#include "render.h"


bool TextBox::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
    return false;
}

void TextBox::update()
{
}

void TextBox::update(ComponentBase* parent)
{
}

std::string repeat_string(std::string string,int count) {
    std::string result = "";
    for (int i = 0; i < count; i++) {
        result += string;
    }
    return result;
}

void TextBox::DrawElement()
{
    alphaAnimation.update(0.2f, this->isPressed);

    ImVec2 textSize = this->imFont->CalcTextSizeA(this->imFont->FontSize, FLT_MAX, 0, this->text->c_str());
    float textY = this->position.getY() + (this->position.getHeight() - textSize.y) / 2;
    float textX = this->position.getX() + 10;
    std::string password = repeat_string("*", this->text->size());
    if (isPassword) {
        textSize = this->imFont->CalcTextSizeA(this->imFont->FontSize, FLT_MAX, 0, password.c_str());
    }
    float textWidth = textSize.x + 20;
    if (textWidth > this->position.getWidth()) {
        textX = this->position.getX() + (this->position.getWidth() - textWidth) + 10;
    }
    

    RenderUtil::drawRect(this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight(), ImColor(255, 255, 255, (int)alphaAnimation.getValue()), 5);
    RenderUtil::onStencil(this->elementName, this->position.getX() + 10, this->position.getY() + 2, this->position.getWidth(), this->position.getHeight() - 4);

    RenderUtil::drawText(textX, textY, this->text->empty() ? this->normal.c_str() : isPassword ? password.c_str() : this->text->c_str(), ImColor(255, 255, 255, this->text->empty() ? 80 : 150), this->imFont);
    if (isPressed) {

        RenderUtil::drawRect(textX + textSize.x + 1, this->position.getY() + 11, 1, textSize.y, ImColor(255, 255, 255, 150), 0);
    }
    RenderUtil::uninstallStencil();
}

bool TextBox::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
    if (isMouseHover) {
        isPressed = true;
    }
    else if(isPressed) {
        isPressed = false;
    }
    return false;
}

bool TextBox::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
    return false;
}

std::string getClipboardText()
{
    // 打开剪贴板
    if (!OpenClipboard(nullptr))
        return "";

    // 获取剪贴板上的句柄
    HANDLE hData = GetClipboardData(CF_TEXT);
    if (hData == nullptr)
    {
        CloseClipboard();
        return "";
    }

    // 锁定剪贴板句柄，以获取指向剪贴板数据的指针
    LPSTR pszText = static_cast<LPSTR>(GlobalLock(hData));
    if (pszText == nullptr)
    {
        CloseClipboard();
        return "";
    }

    // 将剪贴板数据复制到std::string对象中
    std::string clipboardText(pszText);

    // 解锁剪贴板句柄并关闭剪贴板
    GlobalUnlock(hData);
    CloseClipboard();

    // 返回获取的字符串
    return clipboardText;
}


std::string WcharToString(wchar_t wcharStr)
{
    std::wstring_convert<std::codecvt_utf8<wchar_t>, wchar_t> converter;
    std::string utf8Str = converter.to_bytes(wcharStr);
    return utf8Str;
}

void TextBox::KeyTyped(int code, wchar_t in_char)
{
    if (isPressed) {
        if (code == 22) {
            this->text->append(getClipboardText());
        }else if (code == 8) {
            if (this->text->size() > 0) {
                this->text->pop_back();
            }
        }
        else {
            this->text->append(WcharToString(in_char));
        }
    }
}
