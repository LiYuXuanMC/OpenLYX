#ifndef PROGRESSBAR_H
#define PROGRESSBAR_H

#include "ComponentBase.h"
#include <memory>
#include "render.h"
#include "animation.h"

class ProgressBar :public ComponentBase {

private:
	float upValue;

public:
	float minValue;
	float maxValue;
	float value;

	virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) override;
	virtual void update() override;
	virtual void update(ComponentBase* parent) override;
	virtual void KeyTyped(int code, wchar_t in_char) override;
	virtual void DrawElement() override;
	virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton) override;
	virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus) override;

	ProgressBar(std::string_view elementName, float value, float min, float max) : ComponentBase(elementName.data()), value(value), minValue(min), maxValue(max), upValue(0) {
	}


};


#endif // !PROGRESSBAR_H
