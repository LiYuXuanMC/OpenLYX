#ifndef ACCOUNTGROUP_H
#define ACCOUNTGROUP_H

#include "ComponentBase.h"
#include "Logger.h"
#include "Button.h"
#include "TextButton.h"

class LoginWindow;

class AccountGroup : public ComponentBase {

public:
	LoginWindow* parent;
	std::unique_ptr<TextButton> logOutButton;
	std::unique_ptr<TextButton> rechargeButton;

	virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) override;
	virtual void update() override;
	virtual void update(ComponentBase* parent) override;
	virtual void KeyTyped(int code, wchar_t in_char) override;
	virtual void DrawElement() override;
	virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton) override;
	virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus) override;

	AccountGroup(LoginWindow* parent);
};


#endif // !ACCOUNTGROUP_H

