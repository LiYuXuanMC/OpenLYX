#include "ProgressBar.h"
#include "Fonts.h"

bool ProgressBar::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
	return false;
}

void ProgressBar::update()
{
}

void ProgressBar::update(ComponentBase* parent)
{
}

void ProgressBar::KeyTyped(int code, wchar_t in_char)
{
}

void ProgressBar::DrawElement()
{
	const float newValue = this->value;
	const float percent = (newValue - this->minValue) / (this->maxValue - this->minValue);
	const float progressWidth = this->position.getWidth() * percent;
	const float maxRound = (this->position.getHeight() < this->position.getWidth()) ? (this->position.getHeight() / 2) : (this->position.getWidth() / 2);
	const float progressRound = (this->position.getHeight() < progressWidth) ? (this->position.getHeight() / 2) : (progressWidth / 2);
	ImFont* geologicaRegular18 = FontsLoader::GetGeologicaRegular18();
	ImVec2 fontSize = geologicaRegular18->CalcTextSizeA(geologicaRegular18->FontSize, FLT_MAX, 0, this->elementName.c_str());
	this->position.setHeight(this->heightBase + fontSize.y + 8);
	RenderUtil::drawText(this->position.getX(), this->position.getY(), this->elementName, ImColor(255, 255, 255), geologicaRegular18);
	float posY = this->position.getY() + fontSize.y + 8;
	RenderUtil::drawRect(this->position.getX(), posY, this->position.getWidth(), this->heightBase, ImColor(255, 255, 255, 20), maxRound);
	RenderUtil::drawRect(this->position.getX(), posY, progressWidth, this->heightBase, ImColor(30, 110, 255, 255), progressRound);
}

bool ProgressBar::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
	return false;
}

bool ProgressBar::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
	return false;
}
