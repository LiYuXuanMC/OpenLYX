#ifndef LISTBOX_H
#define LISTBOX_H

#include "ComponentBase.h"
#include <vector>
#include "animation.h"

class ListBox : public ComponentBase
{
public:
	std::vector<ComponentBase*> components;
	float componentHeight;
	float scrollTo = 0;
	Animation scrollAnimation{ 0, 0, 4.0f, TweenUtil::linear };

	virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) override;
	virtual void update() override;
	virtual void update(ComponentBase* parent) override;
	virtual void KeyTyped(int code, wchar_t in_char) override;
	virtual void DrawElement() override;
	virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton) override;
	virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus) override;

	ListBox();

	void addComponent(ComponentBase* component);
};


#endif // !LISTBOX_H
