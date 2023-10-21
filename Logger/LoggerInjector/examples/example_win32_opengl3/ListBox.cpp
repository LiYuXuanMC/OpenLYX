#include "ListBox.h"
#include "render.h"
#include "Fonts.h"
#include <algorithm>

bool ListBox::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
	if (mouseDWheel != 0 && isHovered(this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight(), mouseX, mouseY)) {
		if (mouseDWheel > 0) {
			scrollTo += 23;
		}
		else {
			if (componentHeight > this->position.getHeight()) {
				scrollTo -= 23;
			}
		}
		scrollAnimation.reset();
	}
	return false;
}

void ListBox::update()
{
}

void ListBox::update(ComponentBase* parent)
{
}

void ListBox::KeyTyped(int code, wchar_t in_char)
{
}

void ListBox::DrawElement()
{
	scrollTo = min(0, max(-componentHeight + this->position.getHeight(), scrollTo));
	scrollAnimation.setBegin(scrollAnimation.getValue());
	scrollAnimation.setEnd(scrollTo);
	scrollAnimation.update(0.1f);

	RenderUtil::onStencil("ListBox", this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight());

	float append = this->position.getY() + scrollAnimation.getValue();
	float posY = append;
	for (size_t i = 0; i < this->components.size(); i++)
	{
		ComponentBase* child = this->components[i];
		if (!child->visibility) {
			continue;
		}
		child->update();
		child->drawComponent(this->position.getX(), posY, this->position.getWidth(), mouseX, mouseY, false);
		posY += child->position.getHeight() + 12;
	}
	this->componentHeight = posY - append - 12;

	RenderUtil::uninstallStencil();
}

bool ListBox::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
	for (ComponentBase* componentBase : this->components) {
		if (componentBase->mouseClicked(mouseX,mouseY,mouseButton)) {
			return true;
		}
	}
	return false;
}

bool ListBox::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
	for (ComponentBase* componentBase : this->components) {
		if (componentBase->mouseReleased(mouseX, mouseY, mouseStatus)) {
			return true;
		}
	}
	return false;
}

ListBox::ListBox():ComponentBase("ListBox")
{
}

void ListBox::addComponent(ComponentBase* component)
{
	this->components.push_back(component);
}
