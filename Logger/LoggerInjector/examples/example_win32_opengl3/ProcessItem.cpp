#include "ProcessItem.h"
#include "Fonts.h"
#include "ThemidaSDK.h"
#include "render.h"
#include <iostream>
#include <fstream>
#include <Psapi.h>

BOOL CALLBACK EnumWindowsCallback(HWND handle, LPARAM lParam);
BOOL IsMainWindow(HWND handle);

bool isProcessRunning(DWORD process_id) {


    DWORD processes[1024];
    DWORD needed;
    if (!EnumProcesses(processes, sizeof(processes), &needed)) {
        std::cout << "Failed to enumerate processes." << std::endl;
        return 1;
    }
    DWORD count = needed / sizeof(DWORD);
    for (DWORD i = 0; i < count; i++) {
        if (process_id == processes[i]) {
            return true;
        }
    }

    return false;
}

bool InjectDll(DWORD processId, const char* dllPath)
{

    HANDLE hProcess = OpenProcess(PROCESS_ALL_ACCESS, FALSE, processId);
    if (hProcess == NULL)
    {
        return false;
    }

    LPVOID dllPathAddr = VirtualAllocEx(hProcess, NULL, strlen(dllPath) + 1, MEM_COMMIT, PAGE_READWRITE);
    if (dllPathAddr == NULL)
    {
        CloseHandle(hProcess);
        return false;
    }

    if (!WriteProcessMemory(hProcess, dllPathAddr, dllPath, strlen(dllPath) + 1, NULL))
    {
        VirtualFreeEx(hProcess, dllPathAddr, 0, MEM_RELEASE);
        CloseHandle(hProcess);
        return false;
    }

    HMODULE hKernel32 = GetModuleHandleA("kernel32.dll");
    if (hKernel32 == NULL)
    {
        VirtualFreeEx(hProcess, dllPathAddr, 0, MEM_RELEASE);
        CloseHandle(hProcess);
        return false;
    }

    LPVOID loadLibraryAddr = (LPVOID)GetProcAddress(hKernel32, "LoadLibraryA");
    if (loadLibraryAddr == NULL)
    {
        VirtualFreeEx(hProcess, dllPathAddr, 0, MEM_RELEASE);
        CloseHandle(hProcess);
        return false;
    }

    HANDLE hThread = CreateRemoteThread(hProcess, NULL, 0, (LPTHREAD_START_ROUTINE)loadLibraryAddr, dllPathAddr, 0, NULL);
    if (hThread == NULL)
    {
        VirtualFreeEx(hProcess, dllPathAddr, 0, MEM_RELEASE);
        CloseHandle(hProcess);
        return false;
    }

    WaitForSingleObject(hThread, INFINITE);

    VirtualFreeEx(hProcess, dllPathAddr, 0, MEM_RELEASE);
    CloseHandle(hThread);
    CloseHandle(hProcess);

    return true;
}

//BYTE* read_file_as_byte_array(const char* filename, size_t& size) {
//	std::ifstream file(filename, std::ios::binary | std::ios::ate);
//	if (!file.is_open()) {
//		return nullptr;
//	}
//	size = file.tellg();
//	file.seekg(0, std::ios::beg);
//	char* buffer = new char[size];
//	file.read(buffer, size);
//	file.close();
//	BYTE* data = reinterpret_cast<BYTE*>(buffer);
//	return data;
//}

struct handle_data {
	DWORD process_id;
	HWND best_handle;
};

struct process_check {
    LoginWindow* login_window;
    DWORD process_id;
};

BOOL IsMainWindow(HWND handle)
{
	return GetWindow(handle, GW_OWNER) == (HWND)0 && IsWindowVisible(handle);
}

HWND FindMainWindow(DWORD process_id)
{
	handle_data data;
	data.process_id = process_id;
	data.best_handle = 0;
	EnumWindows(EnumWindowsCallback, (LPARAM)&data);
	return data.best_handle;
}

BOOL CALLBACK EnumWindowsCallback(HWND handle, LPARAM lParam)
{
	handle_data& data = *(handle_data*)lParam;
	DWORD process_id = 0;
	GetWindowThreadProcessId(handle, &process_id);
	if (data.process_id != process_id || !IsMainWindow(handle)) {
		return TRUE;
	}
	data.best_handle = handle;
	return FALSE;
}

DWORD thread_func_check(LPVOID lpParam) {

    process_check& Check = *(process_check*)lpParam;
    while (true)
    {
		if (!isProcessRunning(Check.process_id)) {
			Check.login_window->NextInjectorPage = Injector_Page::INFOMATION;
            Check.login_window->Infomation_Box.get()->addComponent(new TextDisplay("Inject Maybe Failed", FontsLoader::GetGeologicaRegular20()));
            Check.login_window->Infomation_Box.get()->addComponent(new TextDisplay("Process Crash", FontsLoader::GetGeologicaRegular18(), ImColor(255, 255, 255, 100)));
			Check.login_window->Infomation_Box.get()->addComponent(new TextButton("Return",
				[&]() {
					Check.login_window->animation = true;
					Check.login_window->NextPage = Page::MANAGER;
				}
			));
			break;
		}
		std::this_thread::sleep_for(std::chrono::seconds(2));
    }

    return 0;
}

bool ProcessItem::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
	return false;
}

void ProcessItem::update()
{
}

void ProcessItem::update(ComponentBase* parent)
{
	this->parent = dynamic_cast<LeftGroupBox*>(parent);
}

void ProcessItem::KeyTyped(int code, wchar_t in_char)
{
}

void ProcessItem::DrawElement()
{
	alphaAnimation.update(0.2f,isMouseHover);
	std::string processId = std::to_string(this->ProcessId);
	ImFont* geologicaRegular20 = FontsLoader::GetGeologicaRegular20();
	ImFont* geologicaRegular14 = FontsLoader::GetGeologicaRegular14();
	ImVec2 textSize = geologicaRegular20->CalcTextSizeA(geologicaRegular20->FontSize, FLT_MAX, 0, this->title.c_str());
	ImVec2 textSize2 = geologicaRegular14->CalcTextSizeA(geologicaRegular14->FontSize, FLT_MAX, 0, processId.c_str());
	this->position.setWidth(textSize.x + textSize2.x + 22);
	this->position.setHeight(textSize.y + 16);
	RenderUtil::drawRect(this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight(), ImColor(255, 255, 255, (int)(alphaAnimation.getValue())), 4);
	float posX = this->position.getX() + 8;
	RenderUtil::drawText(posX, this->position.getY() + 8, this->title, ImColor(255, 255, 255, 160), geologicaRegular20);
	RenderUtil::drawText(posX + textSize.x + 6, this->position.getY() + 12, processId, ImColor(255, 255, 255, 102), geologicaRegular14);
}

bool ProcessItem::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
	if (isMouseHover) {
		/*HANDLE proc = OpenProcess(PROCESS_ALL_ACCESS, false, this->ProcessId);*/
		//std::string decryptData = LoggerInstance::getInstance().decrypt((*LoggerInstance::getInstance().base64Dll), "AOGnfgTiZikNzTrVFBUJJDEQtldSKYNK");
		//std::vector<BYTE> uchar_vec(decryptData.begin(), decryptData.end());
        this->parent->parent->checkThread = CreateThread(NULL, NULL, thread_func_check, new process_check{ this->parent->parent,this->ProcessId }, NULL, NULL);
        InjectDll(this->ProcessId, LoggerInstance::getInstance().dllPath.c_str());
		this->parent->parent->NextInjectorPage = Injector_Page::PRGS;
	}
	return false;
}

bool ProcessItem::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
	return false;
}

ProcessItem::ProcessItem(DWORD ProcessId) : ComponentBase("ProcessItem"), ProcessId(ProcessId)
{
	HWND hWnd = FindMainWindow(ProcessId);
	char title[256];
	GetWindowTextA(hWnd, title, sizeof(title));
	std::string titleStr(title);
	this->title = titleStr;
}
