#ifndef LOGINWINDOW_H
#define LOGINWINDOW_H

#include "Logger.h"
#include "WindowGui.h"
#include "animation.h"
#include "TextBox.h"
#include "TextDisplay.h"
#include "Button.h"
#include "GroupBox.h"
#include "ProgressBar.h"
#include "Module.h"
#include "TabControl.h"
#include "AccountGroup.h"
#include "NewsGroup.h"
#include "TextButton.h"
#include "ListBox.h"
#include "News.h"
#include "Versions.h"
#include "VersionItem.h"
#include "LeftGroupBox.h"

#include <Windows.h>
#include <iphlpapi.h>
#include <stdio.h>
#include <boost/asio.hpp>

#pragma comment(lib, "iphlpapi.lib")

class LoginWindow :public WindowGui {

	ImGuiWindowFlags window_flags =
		ImGuiWindowFlags_NoResize
		| ImGuiWindowFlags_NoCollapse
		| ImGuiWindowFlags_NoMove
		| ImGuiWindowFlags_NoTitleBar;

	HANDLE WebSocketThread = nullptr;
	std::unique_ptr<GroupBox> Login_Box;
	std::unique_ptr<GroupBox> Button_Box;
	std::unique_ptr<GroupBox> TextGroup_Box;
	std::unique_ptr<TextBox> Account_Box;
	std::unique_ptr<TextBox> Password_Box;
	std::unique_ptr<TextBox> KeyCode_Box;
	std::unique_ptr<TextBox> Recharge_Box;
	std::unique_ptr<TabControl> Tab_Control;
	std::unique_ptr<AccountGroup> Account_Group;
	std::unique_ptr<NewsGroup> News_Group;
	std::unique_ptr<Button> Recharge_Button;
	std::unique_ptr<ListBox> ListBox_Box;
	Module HomeModule{"Home", "Logger and Account Infomation"};
	Module LauncherModule{ "Launcher", "Launcher Logger Beta"};
	
	std::string Login_Title_Text{ "Logger Loader" };
	std::string Account_Text{""};
	std::string Password_Text{""};
	std::string Recharge_Text{ "" };
	std::string KeyCode_Text{ "" };
	std::unique_ptr<float> ProgressBar_Value = std::make_unique<float>(10.0f);

	Animation alphaAnimation{ 0, 255, 10.0f, TweenUtil::easeInOut,10.0f,255 };
	Animation reAlphaAnimation{ 0, 250, 10.0f, TweenUtil::easeInOut };

public:
	bool animation = false;
	bool RechargeWindow = false;
	bool CreateAccount = false;
	HANDLE checkThread = nullptr;
	Module* SelectedModule = nullptr;
	Page NextPage = Page::LOGIN;
	Page NormalPage = Page::LOGIN;
	Injector_Page NextInjectorPage = Injector_Page::PROCESS;
	std::unique_ptr<TextButton> Refer_Button;
	std::unique_ptr<Button> Login_Button;
	std::unique_ptr<LeftGroupBox> Process_Box;
	std::unique_ptr<LeftGroupBox> Infomation_Box;
	std::unique_ptr<LeftGroupBox> Inject_Box;
	std::map<std::string, Versions> VersionMap;

	LoginWindow(){
	}

	void Logined();

	virtual void Setup_Style(ImGuiIO& io) override;
	virtual void Draw_GuiScreen(ImVec2& mouse) override;
	virtual void Mouse_Clicked(ImVec2& mouse, int mouseButton) override;
	virtual void Mouse_Released(ImVec2& mouse, int mouseButton) override;
	virtual void Mouse_DWheel(ImVec2& mouse, float dWheel) override;
	virtual void Key_Typed(int code, wchar_t in_char) override;
};

#endif // MYHEADER_H