#include "pch.h"
#include "hook.h"
#include "jni.h"
#include "jvmti.h"
#include <stdio.h>
#include <iostream>
#include "MinHook.h"
#include "string.h"
#if defined _M_X64
#pragma comment(lib, "lib/libMinHook.x64.lib")
#elif defined _M_IX86
#pragma comment(lib, "lib/libMinHook.x86.lib")
#endif

extern void init(JNIEnv*,jclass);

using namespace std;
Java_org_lwjgl_opengl_GL11_nglFlush nglFlush = NULL;


//Setup API hook
void setupHook() {



	if (MH_Initialize() != MH_OK)
	{
		cout << "Hook initialize fail" << endl;
		return;
	}
	//ToDo:Compatible with multiple LWJGL versions
#if defined _M_X64
	if (MH_CreateHookApi(
		L"lwjgl64.dll", 
		"Java_org_lwjgl_opengl_GL11_nglFlush", 
		(void*)&nglFlush_Hook, 
		reinterpret_cast<LPVOID*>(&nglFlush)
	) != MH_OK) {
		cout << "Fail to setup hook" << endl;

		return;
	}
#elif defined _M_IX86
	if (MH_CreateHookApi(L"lwjgl.dll", "Java_org_lwjgl_opengl_GL11_nglFlush", (void*)&nglFlush_Hook, reinterpret_cast<LPVOID*>(&nglFlush)) != MH_OK) {
		cout << "Fail to setup hook" << endl;

		return;
	}
#endif
	if (MH_EnableHook(MH_ALL_HOOKS) != MH_OK) {
		cout << "Fail to setup hook_" << endl;
		return;
	}
}

void nglFlush_Hook(JNIEnv* env, jclass clazz, jlong varL){
	cout << "trigged" << endl;
	nglFlush(env, clazz, varL);
	uninstallHook();
	init(env, clazz);
}
void uninstallHook() {
	MH_DisableHook(MH_ALL_HOOKS);
	MH_Uninitialize();
}