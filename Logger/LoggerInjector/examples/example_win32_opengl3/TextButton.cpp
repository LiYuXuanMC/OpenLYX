#include "TextButton.h"
#include "Fonts.h"
#include "render.h"

bool TextButton::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
	return false;
}

void TextButton::update()
{
	ImFont* geologicaRegular18 = FontsLoader::GetGeologicaRegular18();
	ImVec2 textSize = geologicaRegular18->CalcTextSizeA(geologicaRegular18->FontSize, FLT_MAX, 0, this->text.c_str());
	this->position.setWidth(textSize.x);
	this->position.setHeight(textSize.y);
}

void TextButton::update(ComponentBase* parent)
{
	update();
}

void TextButton::KeyTyped(int code, wchar_t in_char)
{
}

void TextButton::DrawElement()
{
	ImFont* geologicaRegular18 = FontsLoader::GetGeologicaRegular18();
	alphaAnimation.update(0.2, isMouseHover);
	RenderUtil::drawText(this->position.getX(), this->position.getY(), this->text, ImColor(30, (int)(140 + alphaAnimation.getValue()), 255), geologicaRegular18);
}

bool TextButton::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
	if (isMouseHover) {
		this->MouseClickedLambda();
		return true;
	}
	return false;
}

bool TextButton::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
	return false;
}

TextButton::TextButton(std::string text, std::function<void()> MouseClickedLambda):ComponentBase("TextButton"),text(text), MouseClickedLambda(MouseClickedLambda)
{
}
