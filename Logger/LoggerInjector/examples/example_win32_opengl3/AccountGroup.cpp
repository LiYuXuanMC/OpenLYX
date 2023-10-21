#include "AccountGroup.h"
#include "LoginWindow.h"
#include "render.h"
#include "Fonts.h"

bool AccountGroup::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
	return false;
}

void AccountGroup::update()
{
}

void AccountGroup::update(ComponentBase* parent)
{
}

void AccountGroup::KeyTyped(int code, wchar_t in_char)
{
}

void AccountGroup::DrawElement()
{
	RenderUtil::drawRect(this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight(), ImColor(255, 255, 255, 10), 5);
	RenderUtil::onStencil("AccountGroup", this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight());
	//Name 
	ImFont* geologicaRegular14 = FontsLoader::GetGeologicaRegular14();
	ImFont* geologicaRegular18 = FontsLoader::GetGeologicaRegular18();
	float posX = this->position.getX() + 16;
	RenderUtil::drawText(posX, this->position.getY() + 16, "Name", ImColor(255, 255, 255, 102), geologicaRegular14);
	RenderUtil::drawText(posX, this->position.getY() + 28, LoggerInstance::getInstance().currentUser.username, ImColor(255, 255, 255, 255), geologicaRegular18);
	ImVec2 textSize = geologicaRegular18->CalcTextSizeA(geologicaRegular18->FontSize, FLT_MAX, 0, LoggerInstance::getInstance().currentUser.username.c_str());
	ImVec2 textSizeTitle = geologicaRegular18->CalcTextSizeA(geologicaRegular18->FontSize, FLT_MAX, 0, "Name");
	if (textSize.x < textSizeTitle.x) {
		textSize.x = textSizeTitle.x;
	}
	float nextX = posX + textSize.x + 12.0;
	RenderUtil::drawText(nextX, this->position.getY() + 16, "Expiration Time", ImColor(255, 255, 255, 102), geologicaRegular14);
	RenderUtil::drawText(nextX, this->position.getY() + 28, LoggerInstance::getInstance().currentUser.expirationdate, ImColor(255, 255, 255, 255), geologicaRegular18);
	textSize = geologicaRegular18->CalcTextSizeA(geologicaRegular18->FontSize, FLT_MAX, 0, LoggerInstance::getInstance().currentUser.expirationdate.c_str());
	textSizeTitle = geologicaRegular18->CalcTextSizeA(geologicaRegular18->FontSize, FLT_MAX, 0, "Expiration Time");
	if (textSize.x < textSizeTitle.x) {
		textSize.x = textSizeTitle.x;
	}
	nextX += textSize.x + 12.0;
	RenderUtil::drawText(nextX, this->position.getY() + 16, "Power", ImColor(255, 255, 255, 102), geologicaRegular14);
	RenderUtil::drawText(nextX, this->position.getY() + 28, LoggerInstance::getInstance().currentUser.power, ImColor(255, 255, 255, 255), geologicaRegular18);	
	RenderUtil::drawText(posX, this->position.getY() + 50, "UserId", ImColor(255, 255, 255, 102), geologicaRegular14);
	RenderUtil::drawText(posX, this->position.getY() + 62,this->position.getWidth() - 32, LoggerInstance::getInstance().currentUser.userid, ImColor(255, 255, 255, 255), geologicaRegular18);
	ImVec2 textHeight = geologicaRegular18->CalcTextSizeA(geologicaRegular18->FontSize, FLT_MAX, this->position.getWidth() - 32, LoggerInstance::getInstance().currentUser.userid.data());
	this->position.setHeight(this->heightBase + textHeight.y - textSize.y);

	logOutButton.get()->update();
	rechargeButton.get()->update();
	float textY = this->position.getY() + (this->position.getHeight() - logOutButton.get()->position.getHeight()) / 2;
	posX = this->position.getX() + this->position.getWidth() - logOutButton.get()->position.getWidth() - 32;
	logOutButton.get()->drawComponent(posX, textY, mouseX, mouseY, false);
	posX -= logOutButton.get()->position.getWidth() + 26;
	rechargeButton.get()->drawComponent(posX, textY, mouseX, mouseY, false);

	RenderUtil::uninstallStencil();
}

bool AccountGroup::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
	if (logOutButton.get()->mouseClicked(mouseX, mouseY, mouseButton)) {
		return true;
	}
	else if (rechargeButton.get()->mouseClicked(mouseX, mouseY, mouseButton)) {
		return true;
	}
	return false;
}

bool AccountGroup::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
	return false;
}

AccountGroup::AccountGroup(LoginWindow* parent):ComponentBase("AccountGroup"),parent(parent)
{
	logOutButton = std::unique_ptr<TextButton>(
		new TextButton(
			"LogOut",
			[&] {
				LoggerInstance::getInstance().send(LoggerInstance::getInstance().encrypt("{\"header\":\"loginOut\"}", LoggerInstance::getInstance().processRemoteKey.substr(0, 16)));
				this->parent->NextPage = Page::LOGIN;
				this->parent->animation = true;
			}
	));

	rechargeButton = std::unique_ptr<TextButton>(
		new TextButton(
			"Recharge",
			[&] {
				this->parent->RechargeWindow = true;
			}
	));
}
