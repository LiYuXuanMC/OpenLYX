#ifndef NEWSCOMPONENT_H
#define NEWSCOMPONENT_H

#include "ComponentBase.h"
#include "News.h"
#include "animation.h"

class NewsComponent : public ComponentBase {
public:
	News news;
	Animation alphaAnimation{ 0, 10, 4.0f, TweenUtil::easeOut };
	bool isSelected = false;

	virtual bool MouseDWheel(float mouseX, float mouseY, int mouseDWheel) override;
	virtual void update() override;
	virtual void update(ComponentBase* parent) override;
	virtual void KeyTyped(int code, wchar_t in_char) override;
	virtual void DrawElement() override;
	virtual bool MouseClicked(float mouseX, float mouseY, int mouseButton) override;
	virtual bool MouseReleased(float mouseX, float mouseY, int mouseStatus) override;

	NewsComponent(News news);
};

#endif // !NEWSCOMPONENT_H

