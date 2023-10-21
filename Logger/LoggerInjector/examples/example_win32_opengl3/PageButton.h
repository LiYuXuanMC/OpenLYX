#ifndef PAGEBUTTON_H
#define PAGEBUTTON_H

#include "ComponentBase.h"
#include "Module.h"
#include "animation.h"

class TabControl;

class PageButton : public ComponentBase {
public:
	TabControl* parent;
	Module* module;
	Animation alphaAnimation{ 0, 60, 4.0f, TweenUtil::easeOut };


	virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) override;
	virtual void update() override;
	virtual void update(ComponentBase* parent) override;
	virtual void KeyTyped(int code, wchar_t in_char) override;
	virtual void DrawElement() override;
	virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton) override;
	virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus) override;

	PageButton(TabControl* parent, Module* module);
};

#endif // !PAGEBUTTON_H

