#include "TabControl.h"
#include "LoginWindow.h"
#include "render.h"
#include "Fonts.h"

bool TabControl::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
	return false;
}

void TabControl::update()
{
}

void TabControl::update(ComponentBase* parent)
{
}

void TabControl::KeyTyped(int code, wchar_t in_char)
{
}

void TabControl::DrawElement()
{
	//Debug Box
	//RenderUtil::drawRect(this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight(), ImColor(255, 255, 255, 10), 1);
	
	ImFont* interBold24 = FontsLoader::GetInterSemiBold24();
	ImFont* geologicaRegular18 = FontsLoader::GetGeologicaRegular18();
	std::string pageName = this->parent->SelectedModule->PageName;
	RenderUtil::drawText(this->position.getX(), this->position.getY(), pageName, ImColor(255, 255, 255), interBold24);
	ImVec2 fontPosition = interBold24->CalcTextSizeA(interBold24->FontSize, FLT_MAX, 0, pageName.c_str());
	float append = this->position.getX() + fontPosition.x + 16;
	float nextX = append;
	for (PageButton* button : this->components)
	{
		//Skip the selected module
		if (button->module == this->parent->SelectedModule) {
			continue;
		}
		button->update();
		button->drawComponent(nextX, this->position.getY() + (fontPosition.y - button->position.getHeight()), mouseX, mouseY, false);
		nextX += button->position.getWidth() + 16;
	}
	float componentsWidth = nextX - append;
	float appendY = this->position.getY() + fontPosition.y + 10;
	RenderUtil::drawRect(this->position.getX(), appendY , componentsWidth, 2.0, ImColor(255, 255, 255, 51), 2.0);
	RenderUtil::drawText(this->position.getX(), appendY + 10, this->parent->SelectedModule->Infomation, ImColor(255, 255, 255, 102), geologicaRegular18);
}

bool TabControl::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
	for (PageButton* button : this->components)
	{
		//Skip the selected module
		if (button->module == this->parent->SelectedModule) {
			continue;
		}
		if (button->mouseClicked(mouseX, mouseY, mouseButton)) {
			return true;
		}
	}
	return false;
}

bool TabControl::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
	for (PageButton* button : this->components)
	{
		//Skip the selected module
		if (button->module == this->parent->SelectedModule) {
			continue;
		}
		button->mouseReleased(mouseX, mouseY, mouseStatus);
	}
	return false;
}

void TabControl::addModule(Module* module)
{
	components.push_back(new PageButton(this, module));
}

TabControl::TabControl(LoginWindow* dataInOut):ComponentBase("TabControl"), parent(dataInOut)
{
	this->position.setHeight(72);
}


