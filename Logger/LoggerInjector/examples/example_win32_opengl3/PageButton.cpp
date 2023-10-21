#include "PageButton.h"
#include "TabControl.h"
#include "LoginWindow.h"
#include "render.h"
#include "Fonts.h"

bool PageButton::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
	return false;
}

void PageButton::update()
{
	ImFont* geologicaRegular20 = FontsLoader::GetGeologicaRegular20();
	ImVec2 fontPosition = geologicaRegular20->CalcTextSizeA(geologicaRegular20->FontSize, FLT_MAX, 0, this->module->PageName.c_str());
	this->position.setWidth(fontPosition.x);
	this->position.setHeight(fontPosition.y);
}

void PageButton::update(ComponentBase* parent)
{
}

void PageButton::KeyTyped(int code, wchar_t in_char)
{
}

void PageButton::DrawElement()
{
	alphaAnimation.update(0.2f, this->isMouseHover);
	ImFont* geologicaRegular20 = FontsLoader::GetGeologicaRegular20();
	RenderUtil::drawText(this->position.getX(),this->position.getY(),this->module->PageName,ImColor(255,255,255,(int)(102 + alphaAnimation.getValue())), geologicaRegular20);
}

bool PageButton::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
	if (this->isMouseHover) {
		this->parent->parent->SelectedModule = this->module;
		return true;
	}
	return false;
}

bool PageButton::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
	return false;
}

PageButton::PageButton(TabControl* parent,Module* module):ComponentBase("PageButton"),parent(parent), module(module)
{

}
