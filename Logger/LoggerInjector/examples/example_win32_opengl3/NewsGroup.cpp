#include "NewsGroup.h"
#include "render.h"
#include "Fonts.h"
#include <algorithm>


bool NewsGroup::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
	if (mouseDWheel != 0 && isHovered(this->position.getX(),this->position.getY() + 48,this->position.getWidth(),this->position.getHeight() - 48,mouseX,mouseY)) {
		if (mouseDWheel > 0) {
			scrollTo += 23;
		}
		else {
			if (componentHeight > (this->position.getHeight() - 48.0f)) {
				scrollTo -= 23;
			}
		}
		scrollAnimation.reset();
	}
	return false;
}

void NewsGroup::update()
{
}

void NewsGroup::update(ComponentBase* parent)
{
}

void NewsGroup::KeyTyped(int code, wchar_t in_char)
{
}

void NewsGroup::DrawElement()
{
	float boxHeight = (this->position.getHeight() - 48.0f);
	scrollTo = min(0, max(-componentHeight + boxHeight, scrollTo));
	scrollAnimation.setBegin(scrollAnimation.getValue());
	scrollAnimation.setEnd(scrollTo);
	scrollAnimation.update(0.1f);

	ImFont* geologicaRegular18 = FontsLoader::GetGeologicaRegular18();
	RenderUtil::drawRect(this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight(), ImColor(255, 255, 255, 10), 5);

	RenderUtil::onStencil("NewsGroup_One", this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight());
	RenderUtil::drawText(this->position.getX() + 24, this->position.getY() + 16, "News", ImColor(255, 255, 255), geologicaRegular18);

	RenderUtil::onStencil("NewsGroup", this->position.getX(), this->position.getY() + 48, this->position.getWidth(), boxHeight);
	float append = this->position.getY() + scrollAnimation.getValue() + 48;
	float posY = append;
	for (NewsComponent* newsComponent : this->newsComponents)
	{
		newsComponent->update();
		newsComponent->drawComponent(this->position.getX() + 16, posY, this->position.getWidth() - 32, mouseX, mouseY, false);
		posY += newsComponent->position.getHeight() + 10;
	}
	this->componentHeight = posY - append;

	RenderUtil::uninstallStencil();
	RenderUtil::uninstallStencil();
}

bool NewsGroup::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
	for (NewsComponent* newsComponent : this->newsComponents)
	{
		if (newsComponent->mouseClicked(mouseX,mouseY,mouseButton)) {
			return true;
		}
	}
	return false;
}

bool NewsGroup::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
	for (NewsComponent* newsComponent : this->newsComponents)
	{
		newsComponent->mouseReleased(mouseX, mouseY, mouseStatus);
	}
	return false;
}

NewsGroup::NewsGroup():ComponentBase("NewsGroup")
{
}

void NewsGroup::addComponent(News news)
{
	this->newsComponents.push_back(new NewsComponent(news));
}

