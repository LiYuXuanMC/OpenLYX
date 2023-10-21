#ifndef TABCONTROLE_H
#define TABCONTROLE_H

#include "ComponentBase.h"
#include "Module.h"
#include <vector>
#include "PageButton.h"

class LoginWindow;

class TabControl : public ComponentBase {
public:
	virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) override;
	virtual void update() override;
	virtual void update(ComponentBase* parent) override;
	virtual void KeyTyped(int code, wchar_t in_char) override;
	virtual void DrawElement() override;
	virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton) override;
	virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus) override;

	LoginWindow* parent;
	std::vector<PageButton*> components;

	TabControl(LoginWindow* dataInOut);

	void addModule(Module* module);
};


#endif // !TABCONTROLE_H
