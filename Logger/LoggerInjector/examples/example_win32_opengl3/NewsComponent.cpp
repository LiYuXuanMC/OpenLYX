#include "NewsComponent.h"
#include "Fonts.h"
#include "render.h"

bool NewsComponent::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
	return false;
}

void NewsComponent::update()
{
	ImFont* geologicaRegular18 = FontsLoader::GetGeologicaRegular18();
	ImVec2 textSize = geologicaRegular18->CalcTextSizeA(geologicaRegular18->FontSize, FLT_MAX, this->position.getWidth() - 16, this->news.message.c_str());
	this->position.setHeight(textSize.y + 16);
}

void NewsComponent::update(ComponentBase* parent)
{
}

void NewsComponent::KeyTyped(int code, wchar_t in_char)
{
}

void NewsComponent::DrawElement()
{
	alphaAnimation.update(0.2f,this->isMouseHover || this->isSelected);
	ImFont* geologicaRegular18 = FontsLoader::GetGeologicaRegular18();
	RenderUtil::drawRect(this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight(), ImColor(255, 255, 255, (int)(5 + alphaAnimation.getValue())), 5);
	RenderUtil::onStencil("NewsComponent", this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight());
	RenderUtil::drawText(this->position.getX() + 8,this->position.getY() + 8, this->position.getWidth() - 16, this->news.message,ImColor(255,255,255,160), geologicaRegular18);
	RenderUtil::uninstallStencil();
}

bool NewsComponent::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
	if (isMouseHover) {
		alphaAnimation.setEnd(20);
		this->isSelected = true;
	}
	else {
		alphaAnimation.setEnd(10);
		this->isSelected = false;
	}
	return false;
}

bool NewsComponent::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
	return false;
}

NewsComponent::NewsComponent(News news) :ComponentBase("NewsComponent"),news(news)
{
}

