#include "LoginWindow.h"
#include "ThemidaSDK.h"
#include "curl/curl.h"
#include <websocketpp/server.hpp>
#include <websocketpp/config/asio_no_tls.hpp>
#include <boost/property_tree/ptree.hpp>  
#include <boost/property_tree/json_parser.hpp>  

typedef websocketpp::server<websocketpp::config::asio> websocketsvr;
typedef websocketsvr::message_ptr message_ptr;
using websocketpp::lib::placeholders::_1;
using websocketpp::lib::placeholders::_2;
using websocketpp::lib::bind;

LoginWindow* loginWindow;

size_t write_callback(char* ptr, size_t size, size_t nmemb, void* userdata) {
    std::string* response = (std::string*)userdata;
    response->append(ptr, size * nmemb);
    return size * nmemb;
}

std::string generateHWID(const std::vector<std::string>& hwidList) {
    VM_LION_BLACK_START

    std::ostringstream oss;
    for (const auto& hwid : hwidList) {
        oss << hwid;
    }
    std::string hwidString = oss.str();
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(reinterpret_cast<const unsigned char*>(hwidString.c_str()), hwidString.length(), hash);
    std::ostringstream hashOss;
    hashOss << std::hex << std::setfill('0') << std::uppercase;
    for (unsigned char c : hash) {
        hashOss << std::setw(2) << static_cast<int>(c);
    }
    std::string hashString = hashOss.str();
    std::transform(hashString.begin(), hashString.end(), hashString.begin(), [](char c) { return std::toupper(c); });

    VM_LION_BLACK_END
    return hashString;
}

std::vector<News> GetNews() {
    VM_LION_BLACK_START

    std::vector<News> news_vec;
    std::string url = "http://111.180.205.223:22218/v2/news";

    CURL* curl = curl_easy_init();
    if (curl) {
        curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_callback);

        std::string response;
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);

        CURLcode res = curl_easy_perform(curl);
        if (res == CURLE_OK) {
            std::stringstream ss(response);
            boost::property_tree::ptree pt;
            boost::property_tree::read_json(ss, pt);

            for (auto& news_node : pt.get_child("news")) {
                News news;
                news.message = news_node.second.get<std::string>("message");
                news.timestamp = news_node.second.get<std::string>("timestamp");
                news_vec.push_back(news);
            }

            // 按照timestamp排序，最新的在最前面
            std::sort(news_vec.begin(), news_vec.end(), [](const News& a, const News& b) {
                return std::stol(a.timestamp) > std::stol(b.timestamp);
                });
        }
        else {
            std::cerr << "Error: " << curl_easy_strerror(res) << std::endl;
        }

        curl_easy_cleanup(curl);
    }

    VM_LION_BLACK_END
    return news_vec;
}

std::map<std::string, Versions> GetVersions() {
    VM_LION_BLACK_START

    std::string url = "http://111.180.205.223:22218/v2/versions";
    std::map<std::string, Versions> result;

    CURL* curl = curl_easy_init();
    if (curl) {
        curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, write_callback);

        std::string response;
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);

        CURLcode res = curl_easy_perform(curl);
        if (res == CURLE_OK) {
            std::stringstream ss(response);
            boost::property_tree::ptree pt;
            boost::property_tree::read_json(ss, pt);
            for (auto& versions_node : pt.get_child("versions")) {
                Versions versions;
                versions.name = versions_node.second.get<std::string>("name");
                versions.version = versions_node.second.get<std::string>("version");
                result[versions.name] = versions;
            }
        }
        else {
            std::cerr << "Error: " << curl_easy_strerror(res) << std::endl;
        }

        curl_easy_cleanup(curl);
    }

    VM_LION_BLACK_END
    return result;
}

std::string GetMacAddress() {

    VM_LION_BLACK_START

    PIP_ADAPTER_INFO pIpAdapterInfo = new IP_ADAPTER_INFO[16];// 10个网卡空间 足够了 
    unsigned long stSize = sizeof(IP_ADAPTER_INFO) * 16;
    // 获取所有网卡信息，参数二为输入输出参数 
    int nRel = GetAdaptersInfo(pIpAdapterInfo, &stSize);
    // 空间不足
    if (ERROR_BUFFER_OVERFLOW == nRel) {
        // 释放空间
        if (pIpAdapterInfo != NULL)
            delete[] pIpAdapterInfo;
        return "";
    }

    PIP_ADAPTER_INFO cur = pIpAdapterInfo;
    std::vector<std::string> hwids;

    while (cur) {
        char hex[16] = { '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F' };
        int k = 0;
        char macStr[18] = { 0 };
        for (int j = 0; j < cur->AddressLength; j++) {
            macStr[k++] = hex[(cur->Address[j] & 0xf0) >> 4];
            macStr[k++] = hex[cur->Address[j] & 0x0f];
            macStr[k++] = '-';
        }
        macStr[k - 1] = 0;
        hwids.push_back(macStr);
        cur = cur->Next;
    }

    if (pIpAdapterInfo != NULL)
        delete[] pIpAdapterInfo;
    std::sort(hwids.begin(), hwids.end());
    std::string macAddress = generateHWID(hwids);

    VM_LION_BLACK_END
    return macAddress;
}

DWORD thread_func(LPVOID lpParam) {
    
        websocketsvr server;
    server.set_access_channels(websocketpp::log::alevel::all);
    server.clear_access_channels(websocketpp::log::alevel::frame_payload);
    server.init_asio();
    // Register our open handler
    server.set_open_handler(
        [&](websocketpp::connection_hdl hdl) {

        });
    // Register our close handler
    server.set_close_handler(
        [&](websocketpp::connection_hdl hdl) {

        });
    server.set_message_handler(
        [&](websocketpp::connection_hdl hdl, message_ptr msg) {
            VM_LION_BLACK_START
            try
            {
                std::string message = msg->get_payload();
                boost::property_tree::ptree jsonObject;
                std::stringstream ss(message);
                boost::property_tree::json_parser::read_json(ss, jsonObject);
                if (jsonObject.count("header")) {
                    std::string header = jsonObject.get<std::string>("header");
                    if (header == "getInfo") {
                        boost::property_tree::ptree pt;
                        pt.put("header", "userInfo");
                        pt.put("username", LoggerInstance::getInstance().currentUser.username);
                        pt.put("password", LoggerInstance::getInstance().currentUser.password);
                        std::ostringstream oss;
                        boost::property_tree::write_json(oss, pt);
                        server.send(hdl, oss.str(), websocketpp::frame::opcode::text);
                        return;
                    }
                    if (header == "addProgressBar") {
                        std::string title = jsonObject.get<std::string>("title");
                        int value = jsonObject.get<int>("value");
                        int max = jsonObject.get<int>("max");
                        if (value < 0) {
                            value = 0;
                        }
                        if (value > max) {
                            value = max;
                        }
                        ProgressBar* addProgressBar = new ProgressBar(title, value, 0, max);
                        addProgressBar->position.setHeight(8);
                        addProgressBar->position.setWidth(400);
                        addProgressBar->heightBase = 8;
                        loginWindow->Inject_Box.get()->addComponent(addProgressBar);
                    }
                    else if (header == "removeProgressBar") {
                        std::string title = jsonObject.get<std::string>("title");
                        loginWindow->Inject_Box.get()->removeComponent(title);
                    }
                    else if (header == "setProgressBar") {
                        std::string title = jsonObject.get<std::string>("title");
                        int value = jsonObject.get<int>("value");
                        ComponentBase* componentBase = loginWindow->Inject_Box.get()->getComponent(title);
                        if (componentBase == nullptr) {
                            return;
                        }
                        ProgressBar* progressBar = dynamic_cast<ProgressBar*>(componentBase);
                        progressBar->value = value;
                    }
                    else if (header == "success") {
                        if (loginWindow->checkThread != nullptr) {
                            TerminateThread(loginWindow->checkThread, 0);
                        }
                        loginWindow->NextInjectorPage = Injector_Page::INFOMATION;
                        loginWindow->Inject_Box.get()->clearComponents();
                        loginWindow->Infomation_Box.get()->clearComponents();
                        loginWindow->Infomation_Box.get()->addComponent(new TextDisplay("Inject Success", FontsLoader::GetGeologicaRegular20()));
                        loginWindow->Infomation_Box.get()->addComponent(new TextButton("Return",
                            [&]() {
                                loginWindow->animation = true;
                                loginWindow->NextPage = Page::MANAGER;
                            }
                        ));
                        return;
                    }
                    else if (header == "failed") {
                        if (loginWindow->checkThread != nullptr) {
                            TerminateThread(loginWindow->checkThread, 0);
                        }
                        std::string reason = jsonObject.get<std::string>("reason");
                        loginWindow->NextInjectorPage = Injector_Page::INFOMATION;
                        loginWindow->Inject_Box.get()->clearComponents();
                        loginWindow->Infomation_Box.get()->clearComponents();
                        loginWindow->Infomation_Box.get()->addComponent(new TextDisplay(reason, FontsLoader::GetGeologicaRegular20()));
                        loginWindow->Infomation_Box.get()->addComponent(new TextButton("Return",
                            [&]() {
                                loginWindow->animation = true;
                                loginWindow->NextPage = Page::MANAGER;
                            }
                        ));
                        return;
                    }
                    if (loginWindow->NormalPage != Page::INJECTOR) {
                        loginWindow->NextInjectorPage = Injector_Page::PRGS;
                        loginWindow->NormalPage = Page::INJECTOR;
                    }
                }
            }
            catch (const std::exception& e)
            {
                std::cout << e.what() << std::endl;
            }
            VM_LION_BLACK_END
        });
    server.listen(10234);
    server.start_accept();
    server.run();
    return 0;
}


void LoginWindow::Logined()
{
    VM_LION_BLACK_START

    loginWindow = this;
    News_Group = std::unique_ptr<NewsGroup>(new NewsGroup());
    for (News news : GetNews())
    {
        News_Group.get()->addComponent(news);
    }
    VersionMap = GetVersions();
    ListBox_Box = std::unique_ptr<ListBox>(new ListBox());
    ListBox_Box.get()->addComponent(new VersionItem(this, ImColor(20, 100, 120), VersionMap["Normal"]));
    VersionItem* betaItem = new VersionItem(this, ImColor(20, 100, 255), VersionMap["Beta"]);
    ListBox_Box.get()->addComponent(betaItem);
    VersionItem* nightlyItem = new VersionItem(this, ImColor(208, 175, 0), VersionMap["Nightly"]);
    ListBox_Box.get()->addComponent(nightlyItem);
    if (std::stoi(LoggerInstance::getInstance().currentUser.power) < 10) {
        betaItem->anti = true;
        nightlyItem->anti = true;
    }
    VersionItem* devItem = new VersionItem(this, ImColor(208, 100, 0), VersionMap["Dev"]);
    if (LoggerInstance::getInstance().currentUser.power != "255") {
        devItem->anti = true;
    }
    ListBox_Box.get()->addComponent(devItem);
    Process_Box = std::unique_ptr<LeftGroupBox>(new LeftGroupBox(this));
    Infomation_Box = std::unique_ptr<LeftGroupBox>(new LeftGroupBox(this));
    Inject_Box = std::unique_ptr<LeftGroupBox>(new LeftGroupBox(this));
    if (WebSocketThread == nullptr) {
        WebSocketThread = CreateThread(NULL, NULL, thread_func, NULL, NULL, NULL);
    }

    VM_LION_BLACK_END
}

void LoginWindow::Setup_Style(ImGuiIO& io)
{
    VM_LION_BLACK_START

    //fonts
    FontsLoader::InitFonts();

    //window styles
    ImGuiStyle& style = ImGui::GetStyle();
    ImVec4* colors = style.Colors;

    colors[ImGuiCol_WindowBg] = (ImVec4)ImColor(21, 21, 21);

    style.WindowBorderSize = 0.0f;
    style.WindowPadding = { 0.0f,0.0f };

    Tab_Control = std::unique_ptr<TabControl>(new TabControl(this));
    SelectedModule = &HomeModule;
    Tab_Control.get()->addModule(&HomeModule);
    Tab_Control.get()->addModule(&LauncherModule);

    Account_Box = std::unique_ptr<TextBox>(new TextBox("Account_Box", "Account", &Account_Text, FontsLoader::GetGeologicaRegular18()));
    Account_Box.get()->position.setWidth(300);
    Account_Box.get()->position.setHeight(40);

    Password_Box = std::unique_ptr<TextBox>(new TextBox("Password_Box", "Password", &Password_Text, FontsLoader::GetGeologicaRegular18(), true));
    Password_Box.get()->position.setWidth(300);
    Password_Box.get()->position.setHeight(40);

    Recharge_Box = std::unique_ptr<TextBox>(new TextBox("Recharge_Box", "KeyCode", &Recharge_Text, FontsLoader::GetGeologicaRegular18()));
    Recharge_Box.get()->position.setWidth(300);
    Recharge_Box.get()->position.setHeight(40);

    KeyCode_Box = std::unique_ptr<TextBox>(new TextBox("KeyCode_Box", "KeyCode", &KeyCode_Text, FontsLoader::GetGeologicaRegular18()));
    KeyCode_Box.get()->position.setWidth(300);
    KeyCode_Box.get()->position.setHeight(40);
    KeyCode_Box.get()->visibility = false;

    Login_Box = std::unique_ptr<GroupBox>(new GroupBox("Login Box", 28));
    TextGroup_Box = std::unique_ptr<GroupBox>(new GroupBox("TextGroup Box", 16));
    Button_Box = std::unique_ptr<GroupBox>(new GroupBox("TextGroup Box", 16));
    Account_Group = std::unique_ptr<AccountGroup>(new AccountGroup(this));
    Account_Group.get()->position.setHeight(96);
    Account_Group.get()->heightBase = 96;

    Recharge_Button = std::unique_ptr<Button>(
        new Button("Recharge",
            [&]()
            {

                VM_LION_BLACK_START
                std::string macAddrsss = GetMacAddress();
                std::string sendPoint = "{\"header\":\"recharge\",\"username\":\"" + LoggerInstance::getInstance().currentUser.username + "\",\"keycode\":\"" + Recharge_Text + "\",\"hwid\":\"" + macAddrsss + "\",\"timestamp\":" + std::to_string(std::chrono::duration_cast<std::chrono::milliseconds>(std::chrono::system_clock::now().time_since_epoch()).count()) + "}";
                LoggerInstance::getInstance().send(LoggerInstance::getInstance().encrypt(sendPoint, LoggerInstance::getInstance().processRemoteKey.substr(0, 16)));
                this->RechargeWindow = false;
                LoggerInstance::getInstance().send(LoggerInstance::getInstance().encrypt("{\"header\":\"update\"}", LoggerInstance::getInstance().processRemoteKey.substr(0, 16)));
                VM_LION_BLACK_END


            }
    ));

    Refer_Button = std::unique_ptr <TextButton>(
        new TextButton("Create an account.",
            [&]()
            {
                if (this->CreateAccount) {
                    this->CreateAccount = false;
                    this->KeyCode_Box.get()->visibility = false;
                    this->Login_Button.get()->elementName = "Login";
                    this->Refer_Button.get()->text = "Create an account.";
                }
                else {
                    this->CreateAccount = true;
                    this->KeyCode_Box.get()->visibility = true;
                    this->Login_Button.get()->elementName = "Create account";
                    this->Refer_Button.get()->text = "or sign in.";
                }
            }
        )
    );

    Login_Button = std::unique_ptr<Button>(
        new Button("Login",
            [&]()
            {

                VM_LION_BLACK_START
                LoggerInstance::getInstance().currentUser.username = Account_Text;
                LoggerInstance::getInstance().currentUser.password = Password_Text;
                std::string macAddress = GetMacAddress();
                if (this->CreateAccount) {
                    LoggerInstance::getInstance().send(LoggerInstance::getInstance().encrypt("{\"header\":\"register\",\"username\":\"" + Account_Text + "\",\"password\":\"" + Password_Text + "\",\"keycode\":\"" + KeyCode_Text + "\",\"hwid\":\"" + macAddress + "\",\"timestamp\":" + std::to_string(std::chrono::duration_cast<std::chrono::milliseconds>(std::chrono::system_clock::now().time_since_epoch()).count()) + "}", LoggerInstance::getInstance().processRemoteKey.substr(0, 16)));
                }
                else {
                    LoggerInstance::getInstance().send(LoggerInstance::getInstance().encrypt("{\"header\":\"login\",\"username\":\"" + Account_Text + "\",\"password\":\"" + Password_Text + "\",\"hwid\":\"" + macAddress + "\",\"timestamp\":" + std::to_string(std::chrono::duration_cast<std::chrono::milliseconds>(std::chrono::system_clock::now().time_since_epoch()).count()) + "}", LoggerInstance::getInstance().processRemoteKey.substr(0, 16)));
                }
                VM_LION_BLACK_END

            }));


    Login_Box.get()->addComponent(new TextDisplay(Login_Title_Text, FontsLoader::GetGeologicaThin32()));
    TextGroup_Box.get()->addComponent(Account_Box.get());
    TextGroup_Box.get()->addComponent(Password_Box.get());
    TextGroup_Box.get()->addComponent(KeyCode_Box.get());
    Login_Box.get()->addComponent(TextGroup_Box.get());
    Button_Box.get()->addComponent(Login_Button.get());
    Button_Box.get()->addComponent(Refer_Button.get());
    Login_Box.get()->addComponent(Button_Box.get());

    VM_LION_BLACK_END
}

void LoginWindow::Draw_GuiScreen(ImVec2& mouse)
{
    const ImGuiViewport* viewport = ImGui::GetMainViewport();
    ImGui::SetNextWindowPos(viewport->WorkPos);
    ImGui::SetNextWindowSize(viewport->WorkSize);

    reAlphaAnimation.update(0.1f, RechargeWindow);
    alphaAnimation.update(0.1f, animation);
    if (alphaAnimation.isFinished()) {
        NormalPage = NextPage;
        animation = false;
    }

    ImGui::Begin("Login", nullptr, window_flags);
    float windowWidth = ImGui::GetWindowWidth();
    float windowHeight = ImGui::GetWindowHeight();

    ImFont* geologicaRegular14 = FontsLoader::GetGeologicaRegular14();
    ImVec2 textSize = geologicaRegular14->CalcTextSizeA(14, FLT_MAX, 0.0f, "UI By Kendall");
    RenderUtil::drawText(8, windowHeight - textSize.y - 8, "UI By Kendall", ImColor(255, 255, 255, 40), geologicaRegular14);

    switch (NormalPage)
    {
    case LOGIN:
    {
        float listX = (windowWidth - Login_Box.get()->position.getWidth()) / 2;
        float listY = (windowHeight - Login_Box.get()->position.getHeight()) / 2;
        Login_Box.get()->drawComponent(listX, listY, mouse.x, mouse.y, false);
    }
    break;
    case MANAGER:
    {
        float width = windowWidth - 90;
        Tab_Control.get()->position.setWidth(width);
        Tab_Control.get()->drawComponent(45, 45, mouse.x, mouse.y, false);
        if (SelectedModule == &HomeModule) {
            Account_Group.get()->position.setWidth(width);
            Account_Group.get()->drawComponent(45, 45 + Tab_Control.get()->position.getHeight() + 20, mouse.x, mouse.y, false);
            float news_Y = 106 + Account_Group.get()->position.getX() + Account_Group.get()->position.getHeight();
            News_Group.get()->position.setWidth(width);
            News_Group.get()->position.setHeight(windowHeight - (news_Y + 36));
            News_Group.get()->drawComponent(45, news_Y, mouse.x, mouse.y, false);
        }
        else if (SelectedModule == &LauncherModule) {
            float appendY = 45 + Tab_Control.get()->position.getHeight() + 20;
            ListBox_Box.get()->position.setHeight(windowHeight - appendY - 32);
            ListBox_Box.get()->drawComponent(45, appendY, width, mouse.x, mouse.y, false);
        }
    }
    break;
    case INJECTOR:
    {
        if (this->NextInjectorPage == Injector_Page::PROCESS) {
            float processBoxY = (windowHeight - Process_Box.get()->position.getHeight()) / 2;
            Process_Box.get()->position.setWidth(windowWidth);
            Process_Box.get()->position.setHeight(windowHeight);
            Process_Box.get()->drawComponent(0, processBoxY, mouse.x, mouse.y, false);
        }
        else if (this->NextInjectorPage == Injector_Page::PRGS) {
            float injectY = (windowHeight - Inject_Box.get()->position.getHeight()) / 2;
            Inject_Box.get()->position.setWidth(windowWidth);
            Inject_Box.get()->position.setHeight(windowHeight);
            Inject_Box.get()->drawComponent(0, injectY, mouse.x, mouse.y, false);
        }
        else if (this->NextInjectorPage == Injector_Page::INFOMATION) {
            float infomationY = (windowHeight - Infomation_Box.get()->position.getHeight()) / 2;
            Infomation_Box.get()->position.setWidth(windowWidth);
            Infomation_Box.get()->position.setHeight(windowHeight);
            Infomation_Box.get()->drawComponent(0, infomationY, mouse.x, mouse.y, false);
        }
    }
    break;
    default:
        break;
    }


    if (RechargeWindow || reAlphaAnimation.getValue() != 0) {
        RenderUtil::drawRect(0, 0, windowWidth, windowHeight, ImColor(21, 21, 21, (int)reAlphaAnimation.getValue()), 0);
        if (reAlphaAnimation.isFinished()) {
            float boxX = (windowWidth - Recharge_Box.get()->position.getWidth()) / 2;
            float butX = (windowWidth - Recharge_Button.get()->position.getWidth()) / 2;
            float boxY = (windowHeight - Recharge_Box.get()->position.getHeight()) / 2;
            Recharge_Box.get()->drawComponent(boxX, boxY - 30, mouse.x, mouse.y, false);
            Recharge_Button.get()->drawComponent(butX, boxY + 22, mouse.x, mouse.y, false);
        }
    }

    RenderUtil::drawRect(0, 0, windowWidth, windowHeight, ImColor(21, 21, 21, (int)alphaAnimation.getValue()), 0);

    ImGui::End();
}

void LoginWindow::Mouse_Clicked(ImVec2& mouse, int mouseButton)
{
    if (RechargeWindow) {
        Recharge_Box.get()->mouseClicked(mouse.x, mouse.y, mouseButton);
        Recharge_Button.get()->mouseClicked(mouse.x, mouse.y, mouseButton);
        return;
    }
    switch (NormalPage)
    {
    case LOGIN:
        Login_Box.get()->mouseClicked(mouse.x, mouse.y, mouseButton);
        break;
    case MANAGER:
        Tab_Control.get()->mouseClicked(mouse.x, mouse.y, mouseButton);
        if (SelectedModule == &HomeModule) {
            Account_Group.get()->mouseClicked(mouse.x, mouse.y, mouseButton);
            News_Group.get()->mouseClicked(mouse.x, mouse.y, mouseButton);
        }
        else if (SelectedModule == &LauncherModule) {
            ListBox_Box.get()->mouseClicked(mouse.x, mouse.y, mouseButton);
        }
        break;
    case INJECTOR:
        if (this->NextInjectorPage == Injector_Page::PROCESS) {
            this->Process_Box.get()->mouseClicked(mouse.x, mouse.y, mouseButton);
        }
        else if (this->NextInjectorPage == Injector_Page::PRGS) {
            this->Inject_Box.get()->mouseClicked(mouse.x, mouse.y, mouseButton);
        }
        else if (this->NextInjectorPage == Injector_Page::INFOMATION) {
            this->Infomation_Box.get()->mouseClicked(mouse.x, mouse.y, mouseButton);
        }
        break;
    default:
        break;
    }

}

void LoginWindow::Mouse_Released(ImVec2& mouse, int mouseButton)
{
    if (RechargeWindow) {
        Recharge_Box.get()->mouseReleased(mouse.x, mouse.y, mouseButton);
        Recharge_Button.get()->mouseReleased(mouse.x, mouse.y, mouseButton);
        return;
    }
    switch (NormalPage)
    {
    case LOGIN:
        Login_Box.get()->mouseReleased(mouse.x, mouse.y, mouseButton);
        break;
    case MANAGER:
        Tab_Control.get()->mouseReleased(mouse.x, mouse.y, mouseButton);

        if (SelectedModule == &HomeModule) {
            News_Group.get()->mouseReleased(mouse.x, mouse.y, mouseButton);
        }
        else if (SelectedModule == &LauncherModule) {
            ListBox_Box.get()->mouseReleased(mouse.x, mouse.y, mouseButton);
        }


        break;
    case INJECTOR:
        if (this->NextInjectorPage == Injector_Page::PROCESS) {
            this->Process_Box.get()->mouseReleased(mouse.x, mouse.y, mouseButton);
        }
        else if (this->NextInjectorPage == Injector_Page::PRGS) {
            this->Inject_Box.get()->mouseReleased(mouse.x, mouse.y, mouseButton);
        }
        else if (this->NextInjectorPage == Injector_Page::INFOMATION) {
            this->Infomation_Box.get()->mouseReleased(mouse.x, mouse.y, mouseButton);
        }
        break;
    default:
        break;
    }

}

void LoginWindow::Mouse_DWheel(ImVec2& mouse, float dWheel)
{
    if (RechargeWindow) {
        Recharge_Box.get()->MouseDWheel(mouse.x, mouse.y, dWheel);
        return;
    }
    switch (NormalPage)
    {
    case LOGIN:
        break;
    case MANAGER:
        if (SelectedModule == &HomeModule) {
            News_Group.get()->MouseDWheel(mouse.x, mouse.y, dWheel);
        }
        else if (SelectedModule == &LauncherModule) {
            ListBox_Box.get()->MouseDWheel(mouse.x, mouse.y, dWheel);
        }
        break;
    case INJECTOR:
        if (this->NextInjectorPage == Injector_Page::PROCESS) {

        }
        else if (this->NextInjectorPage == Injector_Page::PRGS) {

        }
        break;
    default:
        break;
    }
}

void LoginWindow::Key_Typed(int code, wchar_t in_char)
{
    if (RechargeWindow) {
        Recharge_Box.get()->KeyTyped(code, in_char);
        return;
    }
    switch (NormalPage)
    {
    case LOGIN:
        Login_Box.get()->KeyTyped(code, in_char);
        break;
    case MANAGER:
        break;
    case INJECTOR:
        break;
    default:
        break;
    }
}

