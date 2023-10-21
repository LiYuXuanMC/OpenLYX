#ifndef VERSIONITEM_H
#define VERSIONITEM_H

#include "ComponentBase.h"
#include "Versions.h"
#include "animation.h"

class LoginWindow;

class VersionItem : public ComponentBase
{
private:
	std::string base64Dll;
	bool isDownloaded = false;
	bool isDownloading = false;
	double progress = 0;

public:
	LoginWindow* parent;
	ImColor BackgroundColor;
	Versions version;
	Animation alphaAnimation{ 0,40,4.0f,TweenUtil::easeOut };

	virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) override;
	virtual void update() override;
	virtual void update(ComponentBase* parent) override;
	virtual void KeyTyped(int code, wchar_t in_char) override;
	virtual void DrawElement() override;
	virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton) override;
	virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus) override;


	VersionItem(LoginWindow* parent, ImColor BackgroundColor, Versions version);
};


#endif