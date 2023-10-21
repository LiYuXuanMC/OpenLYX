#ifndef LEFTGROUPBOX_H
#define LEFTGROUPBOX_H

#include "ComponentBase.h"
#include <vector>

class LoginWindow;

class LeftGroupBox : public ComponentBase
{
private:
	std::vector<ComponentBase*> components;

public:
	LoginWindow* parent;
	float margin = 8;

	virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) override;
	virtual void update() override;
	virtual void update(ComponentBase* parent) override;
	virtual void KeyTyped(int code, wchar_t in_char) override;
	virtual void DrawElement() override;
	virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton) override;
	virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus) override;

	LeftGroupBox(LoginWindow* parent);

	void addComponent(ComponentBase* component);

	void clearComponents();

	void removeComponent(std::string componentName);

	ComponentBase* getComponent(std::string componentName);
};

#endif // !1
