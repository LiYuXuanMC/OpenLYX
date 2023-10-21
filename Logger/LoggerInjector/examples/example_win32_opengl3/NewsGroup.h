#ifndef NEWSGROUP_H
#define NEWSGROUP_H

#include "ComponentBase.h"
#include "NewsComponent.h"
#include "News.h"
#include <vector>


class NewsGroup : public ComponentBase {
public:
	std::vector<NewsComponent*> newsComponents;
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

	NewsGroup();

	void addComponent(News news);
};


#endif // !NEWSGROUP_H

