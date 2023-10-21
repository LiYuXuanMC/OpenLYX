#include "VersionItem.h"
#include "LoginWindow.h"
#include "Fonts.h"
#include "render.h"
#include "ThemidaSDK.h"
#include "injector.h"
#include "TextDisplay.h"
#include "ProcessItem.h"
#include <Psapi.h>
#include <filesystem>
#include <thread>
#include <atomic>

bool file_exists(const std::string& filename) {
	std::ifstream file(filename);
	return file.good();
}

BOOL CALLBACK EnumWindowsCallbackA(HWND handle, LPARAM lParam);
BOOL IsMainWindowA(HWND handle);

struct handle_dataA {
	DWORD process_id;
	HWND best_handle;
};

BOOL IsMainWindowA(HWND handle)
{
	return GetWindow(handle, GW_OWNER) == (HWND)0 && IsWindowVisible(handle);
}

HWND FindMainWindowA(DWORD process_id)
{
	handle_dataA data;
	data.process_id = process_id;
	data.best_handle = 0;
	EnumWindows(EnumWindowsCallbackA, (LPARAM)&data);
	return data.best_handle;
}

BOOL CALLBACK EnumWindowsCallbackA(HWND handle, LPARAM lParam)
{
	handle_dataA& data = *(handle_dataA*)lParam;
	DWORD process_id = 0;
	GetWindowThreadProcessId(handle, &process_id);
	if (data.process_id != process_id || !IsMainWindowA(handle)) {
		return TRUE;
	}
	data.best_handle = handle;
	return FALSE;
}

std::vector<DWORD> FindMinecraftProcesses()
{
    std::vector<DWORD> minecraftProcesses;

    // Get the list of process identifiers.
    DWORD processes[1024], bytesNeeded;
    if (!EnumProcesses(processes, sizeof(processes), &bytesNeeded))
    {
        return minecraftProcesses;
    }

    // Calculate how many process identifiers were returned.
    DWORD numberOfProcesses = bytesNeeded / sizeof(DWORD);

    // Iterate through each process identifier.
    for (DWORD i = 0; i < numberOfProcesses; i++)
    {
        // Get the handle to the process.
        HANDLE hProcess = OpenProcess(PROCESS_QUERY_INFORMATION | PROCESS_VM_READ, FALSE, processes[i]);
        if (hProcess != NULL)
        {
            // Get the process name.
            char processName[MAX_PATH];
            if (GetModuleBaseNameA(hProcess, NULL, processName, sizeof(processName)) > 0)
            {
                std::string processNames(processName);
                if (processNames == "javaw.exe" || processNames == "java.exe")
                {
                    HWND mainWindow = FindMainWindowA(processes[i]);
					char className[MAX_PATH];
					if (GetClassNameA(mainWindow, className, sizeof(className)) > 0)
					{
						std::string classNameString(className);
						if (classNameString.find("LWJGL") != std::string::npos)
						{
							minecraftProcesses.push_back(processes[i]);
						}
					}
                }
            }

            // Close the handle to the process.
            CloseHandle(hProcess);
        }
    }

    return minecraftProcesses;
}

bool VersionItem::MouseDWheel(float mouseX, float mouseY, int mouseDWheel)
{
	return false;
}

void VersionItem::update()
{
}

void VersionItem::update(ComponentBase* parent)
{
}

void VersionItem::KeyTyped(int code, wchar_t in_char)
{
}

void VersionItem::DrawElement()
{

        //std::cout << this->dllEncryptBytes << std::endl;
        //int data_len = sizeof(this->dllEncryptBytes) / sizeof(unsigned char);
        //std::cout << this->dllEncryptBytes, data_len, "AOGnfgTiZikNzTrVFBUJJDEQtldSKYNK") << std::endl;
    

	alphaAnimation.update(0.2f, this->isMouseHover);
	ImFont* interSemibold24 = FontsLoader::GetInterSemiBold24();
	ImFont* interSemibold18 = FontsLoader::GetInterSemiBold18();
	RenderUtil::drawRect(this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight(), this->anti ? ImColor(255, 255, 255, 20) : BackgroundColor, 5);
	if (!this->anti) {
		RenderUtil::drawRect(this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight(), ImColor(255, 255, 255, (int)alphaAnimation.getValue()), 5);
	}
	RenderUtil::onStencil("VersionItem", this->position.getX(), this->position.getY(), this->position.getWidth(), this->position.getHeight());

	int alpha = this->anti ? 102 : 255;
	double posY = this->position.getY() + 20;
	RenderUtil::drawText(this->position.getX() + 20, posY, this->version.name, ImColor(255,255,255, alpha), interSemibold24);
	ImVec2 fontSize = interSemibold24->CalcTextSizeA(interSemibold24->FontSize, FLT_MAX, 0, this->version.name.c_str());
	RenderUtil::drawText(this->position.getX() + 20, posY + fontSize.y + 4, this->version.version, ImColor(255, 255, 255, alpha), interSemibold18);

	RenderUtil::uninstallStencil();
}

std::string toLowerCase(const std::string& str) {
    std::string result;
    std::transform(str.begin(), str.end(), std::back_inserter(result), [](char c) {
        return std::tolower(c);
        });
    return result;
}

std::vector<unsigned char> stringToVector(const std::string& str) {
	return std::vector<unsigned char>(str.begin(), str.end());
}


bool VersionItem::MouseClicked(float mouseX, float mouseY, int mouseButton)
{
	VM_LION_BLACK_START
	if (isMouseHover) {
		if (this->isDownloaded == false) {

		}
		else {
			//LoggerInstance::getInstance().base64Dll = &(this->base64Dll);
			LoggerInstance::getInstance().dllPath = this->base64Dll;
		}
		this->parent->Process_Box.get()->clearComponents();
		this->parent->Infomation_Box.get()->clearComponents();
		if (!file_exists(LoggerInstance::getInstance().dllPath)) {
			this->isDownloaded = false;
			this->parent->animation = true;
			this->parent->NextPage = Page::INJECTOR;
			this->parent->NextInjectorPage = Injector_Page::INFOMATION;
			this->parent->Infomation_Box.get()->addComponent(new TextDisplay("Try Download Again", FontsLoader::GetGeologicaRegular20()));
			this->parent->Infomation_Box.get()->addComponent(new TextDisplay("not found memory", FontsLoader::GetGeologicaRegular18(), ImColor(255, 255, 255, 100)));
			this->parent->Infomation_Box.get()->addComponent(new TextButton("Return",
				[&]() {
					this->parent->animation = true;
					this->parent->NextPage = Page::MANAGER;
				}
			));
			return true;
		}
		std::vector<DWORD> minecraftProcessIds = FindMinecraftProcesses();
		if (minecraftProcessIds.size() >= 1) {
			this->parent->NextInjectorPage = Injector_Page::PROCESS;
			this->parent->Process_Box.get()->addComponent(new TextDisplay("Minecraft Processes", FontsLoader::GetGeologicaRegular18()));
			for (DWORD dWord : minecraftProcessIds)
			{
				this->parent->Process_Box.get()->addComponent(new ProcessItem(dWord));
			}
			this->parent->Process_Box.get()->addComponent(new TextButton("Return",
				[&]() {
					this->parent->animation = true;
					this->parent->NextPage = Page::MANAGER;
				}
			));
		}
		else {
			this->parent->NextInjectorPage = Injector_Page::INFOMATION;
			this->parent->Infomation_Box.get()->addComponent(new TextDisplay("Not Found Minecraft", FontsLoader::GetGeologicaRegular20()));
			this->parent->Infomation_Box.get()->addComponent(new TextButton("Return",
				[&]() {
					this->parent->animation = true;
					this->parent->NextPage = Page::MANAGER;
				}
			));
		}
		this->parent->animation = true;
		this->parent->NextPage = Page::INJECTOR;
		return true;
	}
	VM_LION_BLACK_END
	return false;
}

bool VersionItem::MouseReleased(float mouseX, float mouseY, int mouseStatus)
{
	return false;
}

VersionItem::VersionItem(LoginWindow* parent, ImColor BackgroundColor,Versions version):ComponentBase("VersionItem"),parent(parent), BackgroundColor(BackgroundColor), version(version)
{
	this->position.setHeight(86);
}
