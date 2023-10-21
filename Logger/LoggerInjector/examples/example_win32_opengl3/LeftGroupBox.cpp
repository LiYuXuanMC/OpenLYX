#include "LeftGroupBox.h"
#include "LoginWindow.h"

bool LeftGroupBox::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
	return false;
}

void LeftGroupBox::update()
{
}

void LeftGroupBox::update(ComponentBase* parent)
{
}

void LeftGroupBox::KeyTyped(int code, wchar_t in_char)
{
}

void LeftGroupBox::DrawElement()
{

	float appendY = this->position.getY() + this->position.getHeight() / 2;
	float posY = this->position.getY();
	for (ComponentBase* component : this->components)
	{
		component->update(this);
		float componentX = this->position.getX() + (this->position.getWidth() - component->position.getWidth()) / 2;
		component->drawComponent(componentX, posY, mouseX, mouseY, false);
		posY += component->position.getHeight() + margin;
	}
	this->position.setHeight(posY - this->position.getY() - margin);

}

bool LeftGroupBox::MouseClicked(float mouseX, float mouseY, int mouseButton)
{

	for (ComponentBase* component : this->components) {
		if (component->mouseClicked(mouseX, mouseY, mouseButton)) {
			return true;
		}
	}


	return false;
}

bool LeftGroupBox::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{

	for (ComponentBase* component : this->components) {
		if (component->mouseReleased(mouseX, mouseY, mouseStatus)) {
			return true;
		}
	}
	

	return false;
}

LeftGroupBox::LeftGroupBox(LoginWindow* parent) : ComponentBase("LeftGroupBox"),parent(parent)
{
}

void LeftGroupBox::addComponent(ComponentBase* component)
{
	this->components.push_back(component);
}

void LeftGroupBox::clearComponents()
{
	this->components.clear();
}

void LeftGroupBox::removeComponent(std::string componentName)
{
	for (ComponentBase* component : this->components)
	{
		if (component->elementName == componentName) {
			auto it = std::find(this->components.begin(), this->components.end(), component);
			this->components.erase(it);
			break;
		}
	}
}

ComponentBase* LeftGroupBox::getComponent(std::string componentName)
{
	for (ComponentBase* component : this->components)
	{
		if (component->elementName == componentName) {
			return component;
		}
	}
	return nullptr;
}

