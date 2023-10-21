// dllmain.cpp : 定义 DLL 应用程序的入口点。
#include "pch.h"
#include "hook.h"
#include <iostream>

DWORD WINAPI init(LPVOID);
#if defined _DEBUG
static HANDLE hdlWrite;
#endif

using namespace std;
bool special = false;

BOOL APIENTRY DllMain( HMODULE hModule,
                       DWORD  ul_reason_for_call,
                       LPVOID lpReserved
                     )
{
    switch (ul_reason_for_call)
    {
    case DLL_PROCESS_ATTACH:
    {
        HANDLE tHandle = CreateThread(NULL, 0, &init, lpReserved, NULL, NULL);
        CloseHandle(tHandle);
        break;
    }
    case DLL_THREAD_ATTACH:
    case DLL_THREAD_DETACH:
    case DLL_PROCESS_DETACH:
        break;
    }
    return TRUE;
}

DWORD WINAPI init(LPVOID args) {
#if defined _DEBUG
    AllocConsole();
    hdlWrite = GetStdHandle(STD_OUTPUT_HANDLE);
    freopen("CONOUT$", "w+t", stdout);
    freopen("CONIN$", "r+t", stdin);
    cout << "" << endl;
    cout << "  _                                " << endl;
    cout << " | |    ___   __ _  __ _  ___ _ __ " << endl;
    cout << " | |   / _ \\ / _` |/ _` |/ _ \\ '__|" << endl;
    cout << " | |__| (_) | (_| | (_| |  __/ |   " << endl;
    cout << " |_____\\___/ \\__, |\\__, |\\___|_|   " << endl;
    cout << "             |___/ |___/           " << endl;
    cout << "WARN: YOU ARE RUNNING DEVELOPMENT VERSION !!!!!!" << endl;

#endif
    //Check jvm
    if (GetModuleHandle(L"jvm.dll") == NULL) {
        //The injected process isn't a JVM or the JVM isn't started
#if defined _DEBUG
        cout << "The injected process isn't a JVM or the JVM isn't started" << endl;
#endif
        return NULL;
    }
    //Check Minecraft
#if defined _M_X64
    if (GetModuleHandle(L"lwjgl64.dll") == NULL) {
        //Special support for labymod
        if (GetModuleHandle(L"lwjgl.dll") == NULL) {
            //The injected process isn't a Minecraft instance or the Minecraft isn't started
#if defined _DEBUG
            cout << "The injected process isn't a Minecraft instance or the Minecraft isn't started" << endl;
#endif
            return NULL;
        }
        special = true;
    }
#elif defined _M_IX86
    if (GetModuleHandle(L"lwjgl.dll") == NULL) {
        return NULL;
    }
#endif
    
    setupHook();
    return NULL;
}

