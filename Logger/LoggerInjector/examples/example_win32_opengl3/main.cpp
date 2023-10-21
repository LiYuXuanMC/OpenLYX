// Dear ImGui: standalone example application for Win32 + OpenGL 3
// If you are new to Dear ImGui, read documentation from the docs/ folder + read the top of imgui.cpp.
// Read online: https://github.com/ocornut/imgui/tree/master/docs

// This is provided for completeness, however it is strogly recommended you use OpenGL with SDL or GLFW.

#include "imgui.h"
#include "imgui_impl_opengl3.h"
#include "imgui_impl_win32.h"
#ifndef WIN32_LEAN_AND_MEAN
#define WIN32_LEAN_AND_MEAN
#endif
#include <windows.h>
#include <GL/GL.h>
#include <tchar.h>
#include "Logger.h"
#include "render.h"
#include <vector>
#include <fstream>
#include <sstream>
#include <iostream>
#include "Fonts.h"
#include "LoginWindow.h"
#include "ThemidaSDK.h"
#include <boost/property_tree/ptree.hpp>  
#include <boost/property_tree/json_parser.hpp>  


using client = websocketpp::client<websocketpp::config::asio_client>;
using message_ptr = websocketpp::config::asio_client::message_type::ptr;

// Data stored per platform window
struct WGL_WindowData { HDC hDC; };

// Data
static HGLRC            g_hRC;
static WGL_WindowData   g_MainWindow;
static int              g_Width;
static int              g_Height;

// Forward declarations of helper functions
bool CreateDeviceWGL(HWND hWnd, WGL_WindowData* data);
void CleanupDeviceWGL(HWND hWnd, WGL_WindowData* data);
void ResetDeviceWGL();
LRESULT WINAPI WndProc(HWND hWnd, UINT msg, WPARAM wParam, LPARAM lParam);
std::string hexToByteArray(const std::string& hex);
std::string genSha3(const std::string& originalString);

LoginWindow* windowInject = nullptr;

class Scheduler {
public:
    template<typename F>
    void scheduleAtFixedRate(F func, std::chrono::milliseconds initialDelay, std::chrono::milliseconds period) {
        std::thread([func, initialDelay, period]() {
            std::this_thread::sleep_for(initialDelay);

            while (true) {
                auto startTime = std::chrono::steady_clock::now();
                func();
                auto endTime = std::chrono::steady_clock::now();

                auto elapsedTime = std::chrono::duration_cast<std::chrono::milliseconds>(endTime - startTime);
                auto remainingTime = period - elapsedTime;

                if (remainingTime > std::chrono::milliseconds::zero()) {
                    std::this_thread::sleep_for(remainingTime);
                }
            }
            }).detach();
    }
};

bool WriteFileToCharArray(const std::string& filename, const std::string& arrayName, const std::string& outputFilename)
{
    std::ifstream infile(filename, std::ios::binary);

    if (!infile.is_open())
    {
        return false;
    }

    std::vector<unsigned char> fileData((std::istreambuf_iterator<char>(infile)), std::istreambuf_iterator<char>());
    infile.close();

    std::ostringstream ss;
    ss << "#pragma once\nunsigned char " << arrayName << "[] = { ";
    size_t j = 0;
    for (size_t i = 0; i < fileData.size(); i++, j++)
    {
        ss << "0x" << std::hex << static_cast<int>(fileData[i]);
        if (i < fileData.size() - 1)
        {
            ss << ", ";
        }
        if (j == 3) {
            ss << "\n";
			j = 0;
        }
    }
    ss << " };";

    std::ofstream outfile(outputFilename, std::ios::trunc);

    if (!outfile.is_open())
    {
        return false;
    }

    outfile << ss.str();
    outfile.close();

    return true;
}

void on_message(client* c, websocketpp::connection_hdl hdl, message_ptr msg)
{
    VM_LION_BLACK_START

    try
    {
        boost::property_tree::ptree newJsonObject;
        std::stringstream ss(msg->get_payload());
        boost::property_tree::json_parser::read_json(ss, newJsonObject);
        std::string pingMessage = newJsonObject.get<std::string>("ping");
        return;
    }
    catch (const std::exception& ex)
    {
    }

    if (LoggerInstance::getInstance().tab == 0) {
        LoggerInstance::getInstance().remoteKey = msg->get_payload();
        std::cout << "Your Socket ComputerId: " << LoggerInstance::getInstance().remoteKey << std::endl;
        std::string randomKey = hexToByteArray(LoggerInstance::getInstance().remoteKey);
        LoggerInstance::getInstance().processRemoteKey = genSha3(randomKey+ "tJsT6Yns");
        LoggerInstance::getInstance().send(LoggerInstance::getInstance().processRemoteKey);
        LoggerInstance::getInstance().tab = 1;
    }
    else if (LoggerInstance::getInstance().tab == 1) {
        if (msg->get_payload() == "OK") {
            LoggerInstance::getInstance().tab = 2;
            std::cout << "HandShake Success" << std::endl;
        }
        else {
            std::cout << "HandShake to Server Error" << std::endl;
        }
    }
    else if (LoggerInstance::getInstance().tab == 2) {
        std::string decodeMessage = LoggerInstance::getInstance().decrypt(msg->get_payload(), LoggerInstance::getInstance().remoteKey);
        boost::property_tree::ptree jsonObject;
        std::stringstream ss(decodeMessage);
        boost::property_tree::json_parser::read_json(ss, jsonObject);
        std::string header = jsonObject.get<std::string>("header");
        if (header == "error") {
            std::cout << "Erro Reponse: " << jsonObject.get<std::string>("value") << std::endl;
        }
        else if (header == "success") {
            std::cout << "Success Reponse: " << jsonObject.get<std::string>("value") << std::endl;
        }
        else if (header == "info") {
            std::cout << "Infomation Reponse: " << jsonObject.get<std::string>("value") << std::endl;
        }
        else if (header == "logined") {
            LoggerInstance::getInstance().isLogin = true;
            LoggerInstance::getInstance().currentUser.userid = jsonObject.get<std::string>("userid");
            LoggerInstance::getInstance().currentUser.entity_token = jsonObject.get<std::string>("entity_token");
            LoggerInstance::getInstance().currentUser.power = jsonObject.get<std::string>("power");
            LoggerInstance::getInstance().currentUser.expirationdate = jsonObject.get<std::string>("expiration");
            std::cout << "Login to " + LoggerInstance::getInstance().currentUser.username + "(" << LoggerInstance::getInstance().currentUser.userid.substr(0, 4) << ")" << std::endl;
            LoginWindow* loginWindowObj = dynamic_cast<LoginWindow*>(LoggerInstance::getInstance().currentWindowGui);
            if (loginWindowObj != nullptr) {
                loginWindowObj->Logined();
                loginWindowObj->animation = true;
                loginWindowObj->NextPage = Page::MANAGER;
            }
        }
        else if (header == "update") {
            LoggerInstance::getInstance().currentUser.power = jsonObject.get<std::string>("power");
            LoggerInstance::getInstance().currentUser.expirationdate = jsonObject.get<std::string>("expiration");
        }
    }   

    VM_LION_BLACK_END
}

std::string genSha3(const std::string& originalString) {

    VM_LION_BLACK_START

    EVP_MD_CTX* mdctx;
    const EVP_MD* md;
    unsigned char md_value[EVP_MAX_MD_SIZE];
    unsigned int md_len;

    md = EVP_sha3_256();
    mdctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(mdctx, md, NULL);
    EVP_DigestUpdate(mdctx, originalString.c_str(), originalString.size());
    EVP_DigestFinal_ex(mdctx, md_value, &md_len);
    EVP_MD_CTX_free(mdctx);

    std::stringstream ss;
    ss << std::hex << std::setfill('0');
    for (int i = 0; i < md_len; ++i) {
        ss << std::setw(2) << static_cast<unsigned int>(md_value[i]);
    }

    std::string result = ss.str();
    std::transform(result.begin(), result.end(), result.begin(), ::toupper); // 将小写字母转换为大写字母

    VM_LION_BLACK_END
    return result;
}

std::string hexToByteArray(const std::string& hex) {
    std::vector<unsigned char> byteVec;
    for (std::size_t i = 0; i < hex.length(); i += 2) {
        std::string byteString = hex.substr(i, 2);
        unsigned char byte = static_cast<unsigned char>(std::stoi(byteString, nullptr, 16));
        byteVec.push_back(byte);
    }
    return std::string(reinterpret_cast<const char*>(byteVec.data()), byteVec.size());
}

void on_open(client* c, websocketpp::connection_hdl hdl)
{
    VM_LION_BLACK_START

    LoggerInstance::getInstance().wHdl = hdl;
    LoggerInstance::getInstance().isConnection = true;
    ImGuiIO& io = ImGui::GetIO(); (void)io;

    if (LoggerInstance::getInstance().currentWindowGui != windowInject) {
        LoggerInstance::getInstance().displayWindowGui(windowInject);
    }

    Scheduler scheduler;
    scheduler.scheduleAtFixedRate(
        [&]() {
            LoggerInstance::getInstance().send("{\"pong\":\"heartbeat\"}");
        }, std::chrono::milliseconds(0), std::chrono::milliseconds(25000));

    VM_LION_BLACK_END
}

void on_close(websocketpp::connection_hdl hdl) {

    ExitProcess(0);
}

// Main code
int main(int, char**)
{

    //WriteFileToCharArray("H:\\Geologica-Regular.ttf", "GeologicaRegularTTF", "G:\\MyProject\\LoggerDev\\LoggerInjector\\examples\\example_win32_opengl3\\GeologicaRegular.h");
    std::cout << "Logger Injector Version:" << LoggerInstance::getInstance().Version << "\n";
        
    // Create application window
    //ImGui_ImplWin32_EnableDpiAwareness();
    WNDCLASSEXW wc = { sizeof(wc), CS_OWNDC, WndProc, 0L, 0L, GetModuleHandle(NULL), NULL, NULL, NULL, NULL, L"Logger-InJ", NULL };
    ::RegisterClassExW(&wc);
    HWND hwnd = ::CreateWindowW(wc.lpszClassName, L"Logger Injector", WS_OVERLAPPEDWINDOW ^ WS_MAXIMIZEBOX , 100, 100, LoggerInstance::getInstance().Window_Width, LoggerInstance::getInstance().Window_Height, NULL, NULL, wc.hInstance, NULL);

    // Initialize OpenGL
    if (!CreateDeviceWGL(hwnd, &g_MainWindow))
    {
        CleanupDeviceWGL(hwnd, &g_MainWindow);
        ::DestroyWindow(hwnd);
        ::UnregisterClassW(wc.lpszClassName, wc.hInstance);
        return 1;
    }
    wglMakeCurrent(g_MainWindow.hDC, g_hRC);

    // Show the window
    ::ShowWindow(hwnd, SW_SHOWDEFAULT);
    ::UpdateWindow(hwnd);

    // Setup Dear ImGui context
    IMGUI_CHECKVERSION();
    ImGui::CreateContext();
    ImGuiIO& io = ImGui::GetIO(); (void)io;
    io.ConfigFlags |= ImGuiConfigFlags_NavEnableKeyboard;   // Enable Keyboard Controls
    io.ConfigFlags |= ImGuiConfigFlags_NavEnableGamepad;    // Enable Gamepad Controls
    io.IniFilename = nullptr;

    windowInject = new LoginWindow();
    windowInject->Setup_Style(io);

    FontsLoader::InitFonts();
    std::thread websocket_thread([&]() {
        //111.180.205.223

        std::string uri = "ws://111.180.205.223:22218/launch";
        LoggerInstance::getInstance().wClient = new client();
        client& wClient = *LoggerInstance::getInstance().wClient;
        wClient.clear_access_channels(websocketpp::log::alevel::all);
        wClient.init_asio();
        wClient.set_message_handler(bind(&on_message, &wClient, websocketpp::lib::placeholders::_1, websocketpp::lib::placeholders::_2));
        wClient.set_open_handler(bind(&on_open, &wClient, websocketpp::lib::placeholders::_1));
        wClient.set_close_handler(bind(&on_close, websocketpp::lib::placeholders::_1));
        websocketpp::lib::error_code ec;
        client::connection_ptr con = wClient.get_connection(uri, ec);
        if (ec) {
            std::cout << "could not create connection because: " << ec.message() << std::endl;
            return;
        }
        wClient.connect(con);
        wClient.run();
        });

    // Setup Platform/Renderer backends
    ImGui_ImplWin32_InitForOpenGL(hwnd);
    ImGui_ImplOpenGL3_Init();


    // Load Fonts
    // - If no fonts are loaded, dear imgui will use the default font. You can also load multiple fonts and use ImGui::PushFont()/PopFont() to select them.
    // - AddFontFromFileTTF() will return the ImFont* so you can store it if you need to select the font among multiple.
    // - If the file cannot be loaded, the function will return NULL. Please handle those errors in your application (e.g. use an assertion, or display an error and quit).
    // - The fonts will be rasterized at a given size (w/ oversampling) and stored into a texture when calling ImFontAtlas::Build()/GetTexDataAsXXXX(), which ImGui_ImplXXXX_NewFrame below will call.
    // - Use '#define IMGUI_ENABLE_FREETYPE' in your imconfig file to use Freetype for higher quality font rendering.
    // - Read 'docs/FONTS.md' for more instructions and details.
    // - Remember that in C/C++ if you want to include a backslash \ in a string literal you need to write a double backslash \\ !
    //io.Fonts->AddFontDefault();
    //io.Fonts->AddFontFromFileTTF("c:\\Windows\\Fonts\\segoeui.ttf", 18.0f);
    //io.Fonts->AddFontFromFileTTF("../../misc/fonts/DroidSans.ttf", 16.0f);
    //io.Fonts->AddFontFromFileTTF("../../misc/fonts/Roboto-Medium.ttf", 16.0f);
    //io.Fonts->AddFontFromFileTTF("../../misc/fonts/Cousine-Regular.ttf", 15.0f);
    //ImFont* font = io.Fonts->AddFontFromFileTTF("c:\\Windows\\Fonts\\ArialUni.ttf", 18.0f, NULL, io.Fonts->GetGlyphRangesJapanese());
    //IM_ASSERT(font != NULL);

    

    ImVec4 clear_color = ImVec4(0.45f, 0.55f, 0.60f, 1.00f);
    bool LMouse_Is_Down = false;
    bool RMouse_Is_Down = false;

    // Main loop
    bool done = false;
    while (!done)
    {
        // Poll and handle messages (inputs, window resize, etc.)
        // See the WndProc() function below for our to dispatch events to the Win32 backend.
        MSG msg;
        while (::PeekMessage(&msg, NULL, 0U, 0U, PM_REMOVE))
        {
            ::TranslateMessage(&msg);
            ::DispatchMessage(&msg);
            if (msg.message == WM_QUIT)
                done = true;
        }
        if (done)
            break;

        // Start the Dear ImGui frame
        ImGui_ImplOpenGL3_NewFrame();
        ImGui_ImplWin32_NewFrame();
        ImGui::NewFrame();

        

        // Render My Screen
        if (LoggerInstance::getInstance().currentWindowGui != nullptr) {
            //Events
            ImVec2 mousePos = io.MousePos;

            //Left Clicked
            if (io.MouseDown[0] && !LMouse_Is_Down) {
                LMouse_Is_Down = true;
                LoggerInstance::getInstance().currentWindowGui->Mouse_Clicked(mousePos, 0);

            }

            //Right Clicked
            if (io.MouseDown[1] && !RMouse_Is_Down) {
                RMouse_Is_Down = true;
                LoggerInstance::getInstance().currentWindowGui->Mouse_Clicked(mousePos, 1);
            }

            //Left Released
            if (io.MouseReleased[0] && LMouse_Is_Down) {
                LMouse_Is_Down = false;
                LoggerInstance::getInstance().currentWindowGui->Mouse_Released(mousePos, 0);
            }

            //KeyTyped
            for (int i = 0; i < io.InputQueueCharacters.size(); i++) {
                ImWchar imWChar = io.InputQueueCharacters[i];
                wchar_t c = imWChar;
                LoggerInstance::getInstance().currentWindowGui->Key_Typed(imWChar, c);
            }
            io.ClearInputCharacters();
                
            //Right Released
            if (io.MouseReleased[1] && RMouse_Is_Down) {
                RMouse_Is_Down = false;
                LoggerInstance::getInstance().currentWindowGui->Mouse_Released(mousePos, 1);
            }

            if (io.MouseWheel != 0) {
                LoggerInstance::getInstance().currentWindowGui->Mouse_DWheel(mousePos, io.MouseWheel);
            }

            LoggerInstance::getInstance().currentWindowGui->Draw_GuiScreen(mousePos);
        }

        // Rendering
        ImGui::Render();
        glViewport(0, 0, g_Width, g_Height);
        glClearColor(clear_color.x, clear_color.y, clear_color.z, clear_color.w);
        glClear(GL_COLOR_BUFFER_BIT);
        ImGui_ImplOpenGL3_RenderDrawData(ImGui::GetDrawData());

        // Present
        ::SwapBuffers(g_MainWindow.hDC);
    }

    ImGui_ImplOpenGL3_Shutdown();
    ImGui_ImplWin32_Shutdown();
    ImGui::DestroyContext();

    CleanupDeviceWGL(hwnd, &g_MainWindow);
    wglDeleteContext(g_hRC);
    ::DestroyWindow(hwnd);
    ::UnregisterClassW(wc.lpszClassName, wc.hInstance);

    return 0;
}

// Helper functions
bool CreateDeviceWGL(HWND hWnd, WGL_WindowData* data)
{
    HDC hDc = ::GetDC(hWnd);
    PIXELFORMATDESCRIPTOR pfd = { 0 };
    pfd.nSize = sizeof(pfd);
    pfd.nVersion = 1;
    pfd.dwFlags = PFD_DRAW_TO_WINDOW | PFD_SUPPORT_OPENGL | PFD_DOUBLEBUFFER;
    pfd.iPixelType = PFD_TYPE_RGBA;
    pfd.cColorBits = 32;

    const int pf = ::ChoosePixelFormat(hDc, &pfd);
    if (pf == 0)
        return false;
    if (::SetPixelFormat(hDc, pf, &pfd) == FALSE)
        return false;
    ::ReleaseDC(hWnd, hDc);

    data->hDC = ::GetDC(hWnd);
    if (!g_hRC)
        g_hRC = wglCreateContext(data->hDC);
    return true;
}

void CleanupDeviceWGL(HWND hWnd, WGL_WindowData* data)
{
    wglMakeCurrent(NULL, NULL);
    ::ReleaseDC(hWnd, data->hDC);
}

// Forward declare message handler from imgui_impl_win32.cpp
extern IMGUI_IMPL_API LRESULT ImGui_ImplWin32_WndProcHandler(HWND hWnd, UINT msg, WPARAM wParam, LPARAM lParam);

// Win32 message handler
// You can read the io.WantCaptureMouse, io.WantCaptureKeyboard flags to tell if dear imgui wants to use your inputs.
// - When io.WantCaptureMouse is true, do not dispatch mouse input data to your main application, or clear/overwrite your copy of the mouse data.
// - When io.WantCaptureKeyboard is true, do not dispatch keyboard input data to your main application, or clear/overwrite your copy of the keyboard data.
// Generally you may always pass all inputs to dear imgui, and hide them from your application based on those two flags.
LRESULT WINAPI WndProc(HWND hWnd, UINT msg, WPARAM wParam, LPARAM lParam)
{
    if (ImGui_ImplWin32_WndProcHandler(hWnd, msg, wParam, lParam))
        return true;

    switch (msg)
    {
    case WM_SIZE:
        if (wParam != SIZE_MINIMIZED)
        {
            g_Width = LOWORD(lParam);
            g_Height = HIWORD(lParam);
        }
        return 0;
    case WM_SYSCOMMAND:
        if ((wParam & 0xfff0) == SC_KEYMENU) // Disable ALT application menu
            return 0;
        break;
    case WM_DESTROY:
        ::PostQuitMessage(0);
        return 0;
    }
    return ::DefWindowProc(hWnd, msg, wParam, lParam);
}
