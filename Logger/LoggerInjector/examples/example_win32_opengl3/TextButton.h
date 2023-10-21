#ifndef TEXTBUTTON_H
#define TEXTBUTTON_H

#include "ComponentBase.h"
#include <functional>
#include "animation.h"

class TextButton : public ComponentBase {

public:
	std::function<void()> MouseClickedLambda;
	std::string text;
	Animation alphaAnimation{ 0,40,4.0f,TweenUtil::easeOut };

	virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) override;

	virtual void update() override;

	virtual void update(ComponentBase* parent) override;

	virtual void KeyTyped(int code, wchar_t in_char) override;

	virtual void DrawElement() override;

	virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton) override;

	virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus) override;

	TextButton(std::string text, std::function<void()> MouseClickedLambd);
};


#endif // !TEXTBUTTON_H
