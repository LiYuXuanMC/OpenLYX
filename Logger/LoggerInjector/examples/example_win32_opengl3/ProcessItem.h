#ifndef PROCESSITEM_H
#define PROCESSITEM_H

#include "ComponentBase.h"
#include "animation.h"
#include "LoginWindow.h"

class LeftGroupBox;

class ProcessItem : public ComponentBase
{
private:
	LeftGroupBox* parent;
	DWORD ProcessId;
	std::string title;
	Animation alphaAnimation{ 10, 30, 4.0f, TweenUtil::easeOut };

public:
	bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) override;
	void update() override;
	void update(ComponentBase* parent) override;
	void KeyTyped(int code, wchar_t in_char) override;
	void DrawElement() override;
	bool MouseClicked(float mouseX, float mouseY, int mouseButton) override;
	bool MouseReleased(float mouseX, float mouseY, int mouseStatus) override;

	ProcessItem(DWORD ProcessId);
};

#endif // !PROCESSITEM_H

