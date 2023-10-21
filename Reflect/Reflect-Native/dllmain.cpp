// dllmain.cpp : Defines the entry point for the DLL application.
#include "pch.h"
#include <Windows.h>
#include "jni.h"
#include "MinHook.h"
#include "jvmti.h"
#include "classes.h"
#include <winuser.h>
#include <iostream>
#include "native_string.h"
#include "eacimpl.h"

using namespace std;

#if defined _M_X64
#pragma comment(lib, "minhook.x64.lib")
#elif defined _M_IX86
#pragma comment(lib, "MinHook.x86.lib")
#endif


template <typename T>
inline MH_STATUS MH_CreateHookEx(LPVOID pTarget, LPVOID pDetour, T** ppOriginal);
template <typename T>
inline MH_STATUS MH_CreateHookApiEx(
    LPCWSTR pszModule, LPCSTR pszProcName, LPVOID pDetour, T** ppOriginal);

JavaVM* jvm = NULL;
jvmtiEnv* jvmTi = NULL;
jclass loaderClass = NULL;
jclass nativeClass = NULL;
jclass stringNativeClass = NULL;
jvmtiEventCallbacks callbacks;
jint classdata_len;
jclass clazzt;
jbyteArray  classdata;
jbyteArray data_wait_redefine = NULL;
bool got;
jvmtiClassDefinition* definition = NULL;
jint redefineReturn = 0;
static HANDLE hdlWrite;

DWORD WINAPI init(LPVOID args);
DWORD WINAPI MainThread();
void hookJVM();
typedef void(*Java_org_lwjgl_opengl_GL11_nglFlush)(JNIEnv* env, jclass clazz, jlong lVar);
void registerNativeFunc(JNIEnv* jnienv,jclass nativeClass);
jobjectArray asClassArray(JNIEnv* env, jclass* buf, int len);
jbyteArray asByteArray(JNIEnv* env, const unsigned char* buf, int len);
unsigned char* asUnsignedCharArray(JNIEnv* env, jbyteArray array);
Java_org_lwjgl_opengl_GL11_nglFlush nglFlush = NULL;
jobjectArray getAllLoadedClasses(JNIEnv* env);
JNIEnv* getEnvCurrentThread();
bool inject(JNIEnv* env);
bool start(JNIEnv* env);
jbyteArray getClassBytes(JNIEnv* env,jclass jcaller, jclass target);
void JNICALL classTransformerHook(jvmtiEnv* jvmti,JNIEnv* env,jclass class_being_redefined,jobject loader, const char* name,jobject protection_domain,jint data_len,const unsigned char* data,jint* new_data_len,unsigned char** new_data);
jint redefineClass(JNIEnv* env, jclass, jclass target, jbyteArray bytes);
jint retransform(JNIEnv* env, jclass, jclass target, jobject transformer);
jclass defineClass(JNIEnv* env, jclass jcaller, jobject classLoader, jbyteArray bytes);
BOOL CALLBACK EnumWindowsProc(HWND hwnd, LPARAM lParam);
jboolean setState(JNIEnv* env, jclass jcaller, jint type, jboolean state);
jclass defineClassWithReflect(JNIEnv* env,jobject classLoader, jbyteArray bytes);
HWND GetWindowHwndByPID(DWORD dwProcessID);
jbyteArray getString(JNIEnv* env, jclass, jint index);
void nativeLog(JNIEnv* env, jclass, jstring log);
void detectEAC(JNIEnv* jnienv);

void nglFlush_Hook(JNIEnv* env, jclass clazz, jlong lVar) {
	cout << "hook trigger" << endl;
	MH_DisableHook(MH_ALL_HOOKS);
	nglFlush(env, clazz, lVar);
	//SetWindowDisplayAffinity(GetWindowHwndByPID(GetCurrentProcessId()), 1);
	JNIEnv* jnienv = NULL;
	jnienv = env;
	jnienv->GetJavaVM(&jvm);
	if (!jvm) {
		cout << "jvm fail" << endl;
	}
	jvm->GetEnv((void**)&jvmTi,JVMTI_VERSION_1_2);
	if (!jvmTi) {
		cout << "jvmTi fail" << endl;
	}
	jvmtiCapabilities capabilities;
	memset(&capabilities, 0, sizeof(jvmtiCapabilities));
	capabilities.can_generate_all_class_hook_events = 1;
	capabilities.can_retransform_any_class = 1;
	capabilities.can_retransform_classes = 1;
	capabilities.can_redefine_any_class = 1;
	capabilities.can_redefine_classes = 1;
	jvmtiError error = jvmTi->AddCapabilities(&capabilities);
	if (error != JVMTI_ERROR_NONE) {
		std::cout << "Failed to add capabilities." << std::endl;
	}

	callbacks.ClassFileLoadHook = classTransformerHook;
	jvmTi->SetEventCallbacks(&callbacks, sizeof(callbacks));
	detectEAC(jnienv);
	MainThread();
	return;
}

BOOL APIENTRY DllMain( HMODULE hModule,
                       DWORD  ul_reason_for_call,
                       LPVOID lpReserved
                     )
{
	switch (ul_reason_for_call)
	{
	case DLL_PROCESS_ATTACH:
		HANDLE tHandle = CreateThread(NULL, 0, &init, lpReserved, NULL, NULL);
		CloseHandle(tHandle);
		break;
	}
	return TRUE;

    return TRUE;
}
DWORD WINAPI init(LPVOID args) {
	//AllocConsole();
	//hdlWrite = GetStdHandle(STD_OUTPUT_HANDLE);
	//freopen("CONOUT$", "w+t", stdout);
	//freopen("CONIN$", "r+t", stdin);
	cout << "Start thread" << endl;
	hookJVM();
	return NULL;
}
DWORD WINAPI MainThread() {
	cout << "Start inject" << endl;
	JNIEnv* jnienv = NULL;
	jint result = jvm->GetEnv((void**)(&jnienv), JNI_VERSION_1_8);
	if (result != JNI_OK) {
		cout << "cant get jni context in this thread" << endl;
		if (result == JNI_EINVAL)
		{
			cout << "JNI_EINVAL" << endl;
		}
		if (result == JNI_EDETACHED)
		{
			cout << "JNI_EDETACHED" << endl;
		}
		return NULL;
	}

	if (!inject(jnienv)) {
		cout << "Inject reflect fail" << endl;
		return NULL;
	}
	cout << "find reflect class" << endl;
	if (nativeClass == NULL) {
		cout << "cannot find native class" << endl;
		return NULL;
	}
	registerNativeFunc(jnienv,nativeClass);
	start(jnienv);
	return NULL;
}
bool start(JNIEnv* env) {
	jmethodID loaderid = NULL;
	loaderid = env->GetMethodID(loaderClass, "<init>", "()V");
	if (!loaderid) {
		cout << "Loader method not found" << endl;
		return false;
	}
	jobject LoadClent = env->NewObject(loaderClass, loaderid);
	if (!LoadClent) {
		cout << "new Load Client Error" << endl;
		return false;
	}
	return true;
}
bool inject(JNIEnv* env) {
	jclass* loadedClasses;
	jint loadedClassesCount = 0;
	jvmTi->GetLoadedClasses(&loadedClassesCount, &loadedClasses);
	jclass launchHandlerClass = NULL;
	jclass launchClassLoaderClass = NULL;
	jclass enityRenderClass = NULL;
	jclass LaunchWrapper = NULL;
	jclass java_lang_ClassLoader = NULL;
	jobject CtxClassLoader = NULL;

	/////让我们通过这↑里↓ 来获取所有的线程 从而得到ClientThread 并拿到 CtxCL/////
	//线程类
	jclass thread = NULL;
	//获取所有的栈追踪
	jmethodID getAllStackTraces = NULL;
	//获取名字
	jmethodID getThreadName = NULL;
	//拿上下文类加载器
	jmethodID getContextClassLoader = NULL;
	//Map类
	jclass map = NULL;
	//获取KeySet
	jmethodID KeySet = NULL;
	//Set类
	jclass set = NULL;
	//转数组
	jmethodID toArray = NULL;
	//客户端线程
	jthread clientThread = NULL;

	//定义类
	jmethodID defineClass = NULL;


	//寻找类
	for (jint i = 0; i < loadedClassesCount; i++)
	{
		char* signature;
		jvmTi->GetClassSignature(loadedClasses[i], &signature, NULL);

		if (!strcmp(signature, "Ljava/lang/Thread;")) {
			thread = loadedClasses[i];
			cout << "find Thread" << endl;
		}

		if (!strcmp(signature, "Ljava/util/Set;")) {
			set = loadedClasses[i];
			cout << "find Set" << endl;
		}

		if (!strcmp(signature, "Ljava/util/Map;")) {
			map = loadedClasses[i];
			cout << "find Map" << endl;
		}

		if (!strcmp(signature, "Ljava/lang/ClassLoader;")) {
			java_lang_ClassLoader = loadedClasses[i];
			cout << "find java/lang/ClassLoader" << endl;
		}

	}

	//寻找方法
	getAllStackTraces = env -> GetStaticMethodID(thread,"getAllStackTraces","()Ljava/util/Map;");
	getThreadName = env->GetMethodID(thread, "getName", "()Ljava/lang/String;");
	getContextClassLoader = env->GetMethodID(thread, "getContextClassLoader", "()Ljava/lang/ClassLoader;");

	KeySet = env ->GetMethodID(map, "keySet", "()Ljava/util/Set;");
	toArray = env->GetMethodID(set, "toArray", "()[Ljava/lang/Object;");
	defineClass = env->GetMethodID(java_lang_ClassLoader, "defineClass", "([BII)Ljava/lang/Class;");

	//得到所有线程
	jobjectArray allThread = (jobjectArray)env->CallObjectMethod(env->CallObjectMethod(env->CallStaticObjectMethod(thread, getAllStackTraces), KeySet),toArray);

	jsize atLength = env->GetArrayLength(allThread);

	for (jsize i = 0; i < atLength; i++) {
		jobject curThread = env->GetObjectArrayElement(allThread, i);
		jstring ctName = (jstring)env->CallObjectMethod(curThread, getThreadName);
		const char* ctCname = env->GetStringUTFChars(ctName, 0);
		cout<< ctCname << endl;
		if (!strcmp(ctCname, "Client thread")) {
			clientThread = curThread;
			cout << "find Client Thread" << endl;
			break;
		}
	}

	//拿到类加载器
	CtxClassLoader = env->CallObjectMethod(clientThread,getContextClassLoader);



	jclass loadClass = NULL;
	jsize tempClassIndex = 0;
	jsize lastClassIndex = 0;
	for (jsize j = 0; j != classCount; j++) {
		char* lastClass = new char[classSizes[j] + 1];
		for (jsize classIndex = 0; classIndex != classSizes[j]; classIndex++) {
			tempClassIndex++;;
			lastClass[classIndex] = classes[lastClassIndex + classIndex] ^ classXorKey;
		}

		jbyteArray classBytes = env->NewByteArray(classSizes[j]);
		env->SetByteArrayRegion(classBytes, 0, classSizes[j], (jbyte*)lastClass);


		loadClass = (jclass)env->CallObjectMethod(CtxClassLoader,defineClass, classBytes, 0, classSizes[j]);//env->DefineClass(NULL, CtxClassLoader, (jbyte*)lastClass, classSizes[j]);//
		char* signature;
		jvmTi->GetClassSignature(loadClass, &signature, NULL);
		if (!strcmp(signature, "Lal/nya/reflect/Reflect;"))
		{
			loaderClass = loadClass;
		}
		if (!strcmp(signature, "Lal/nya/reflect/transform/ReflectNative;"))
		{
			nativeClass = loadClass;
			cout << "find al/nya/reflect/transform/ReflectNative" << endl;
		}
		if (!strcmp(signature, className)) {
			stringNativeClass = loadClass;
			cout << "find " << className << endl;
		}
		if (!loadClass) {
			//MessageBoxA(NULL, "Error on class defining", randstr(name, 8), MB_OK | MB_ICONERROR);
			//jvm->DetachCurrentThread();
			//break;
			cout << "Load 1 class fail" << endl;
		}
		delete[]lastClass;
		lastClassIndex = tempClassIndex;
	}
	if (!loaderClass) {
		cout << "loaderclass not found" << endl;
		return false;
	}
	return true;
}
void detectEAC(JNIEnv* jnienv) {
	jclass* loadedClasses;
	jint loadedClassesCount = 0;
	jvmTi->GetLoadedClasses(&loadedClassesCount, &loadedClasses);
	
	jclass EAC_F = NULL;

	for (jint i = 0; i < loadedClassesCount; i++)
	{
		char* signature;
		jvmTi->GetClassSignature(loadedClasses[i], &signature, NULL);

		if (!strcmp(signature, "LEAC/F;")) {
			EAC_F = loadedClasses[i];
			cout << "EAC detected" << endl;
			break;
		}
	}
	if (!EAC_F) return;
	JNINativeMethod natives[] = {
		{const_cast <char*>("ALLATORIxDEMO"),const_cast <char*>("(Lnet/minecraftforge/fml/common/gameevent/TickEvent$ClientTickEvent;)V"),reinterpret_cast<void*>(EAC_F_ALLATORIxDEMO_1)},
		{const_cast <char*>("g"),const_cast <char*>("()V"),reinterpret_cast<void*>(EAC_F_g)},
		{const_cast <char*>("A"),const_cast <char*>("()V"),reinterpret_cast<void*>(EAC_F_A)},
		{const_cast <char*>("B"),const_cast <char*>("()V"),reinterpret_cast<void*>(EAC_F_B)},
	};
	jint result = jnienv->RegisterNatives(EAC_F, natives, sizeof(natives) / sizeof(JNINativeMethod));
	cout << "register" << result << endl;
}
void registerNativeFunc(JNIEnv* jnienv,jclass nativeClass) {
	cout << "registerNative" << endl;
	JNINativeMethod natives[] = {
		{const_cast < char*>("getClassBytes"),const_cast < char*>("(Ljava/lang/Class;)[B"),reinterpret_cast<void*>(getClassBytes)},
		{const_cast <char*>("getAllLoadedClasses"),const_cast <char*>("()[Ljava/lang/Class;"),reinterpret_cast<void*>(getAllLoadedClasses)},
		{const_cast <char*>("redefineClass"),const_cast <char*>("(Ljava/lang/Class;[B)I"),reinterpret_cast<void*>(redefineClass)},
		{const_cast <char*>("retransform"),const_cast <char*>("(Ljava/lang/Class;Lal/nya/reflect/transform/ClassTransformer;)I"),reinterpret_cast<void*>(retransform)},
		{const_cast <char*>("defineClass1"),const_cast <char*>("(Ljava/lang/ClassLoader;[B)Ljava/lang/Class;"),reinterpret_cast<void*>(defineClass)},
		{const_cast <char*>("setState"),const_cast <char*>("(IZ)Z"),reinterpret_cast<void*>(setState),},
		{const_cast <char*>("log"),const_cast <char*>("(Ljava/lang/String;)V"),reinterpret_cast<void*>(nativeLog),},
	};
	if (jnienv->RegisterNatives(nativeClass, natives, sizeof(natives) / sizeof(JNINativeMethod)) < 0) {
		cout << "fail to register native" << endl;
	}
	if (!stringNativeClass) {
		cout << "Maybe debug mode" << endl;
		return;
	}
	JNINativeMethod stringNatives[] = {
		{const_cast <char*>(methodName),const_cast <char*>("(I)[B"),reinterpret_cast<void*>(getString)},
	};
	if (jnienv->RegisterNatives(stringNativeClass, stringNatives, sizeof(stringNatives) / sizeof(JNINativeMethod)) < 0) {
		cout << "fail to register native" << endl;
	}
}
JNIEnv* getEnvCurrentThread() {
	JNIEnv* jniEnv = NULL;
	jvm->GetEnv((void**)(&jniEnv), JNI_VERSION_1_8);
	return jniEnv;
}
void JNICALL classTransformerHook
(jvmtiEnv* jvmti,
	JNIEnv* env,
	jclass class_being_redefined,
	jobject loader, const char* name,
	jobject protection_domain,
	jint data_len,
	const unsigned char* data,
	jint* new_data_len,
	unsigned char** new_data
)
{
	jvmti->Allocate(data_len, new_data);
	jclass transformerClass = env->FindClass("al/nya/reflect/transform/TransformManager");
	jmethodID transfrom = env->GetStaticMethodID(transformerClass, "onTransform", "(Ljava/lang/Class;[B)[B");
	clazzt = class_being_redefined;
	classdata = asByteArray(env, data,data_len);
	jbyteArray transformedData = env->NewByteArray(0);
	cout << "0" << endl;
	if (!clazzt || !classdata || !transformerClass || !transfrom) {
		cout << "Unknown transform" << endl;
		*new_data_len = data_len;
		memcpy(*new_data, data, data_len);
		return;
	}
	transformedData = (jbyteArray) env->CallStaticObjectMethod(transformerClass, transfrom, clazzt, classdata);
	if (!transformedData) {
		cout << "Unknown transform" << endl;
		*new_data_len = data_len;
		memcpy(*new_data, data, data_len);
		return;
	}
	unsigned char* newChars = asUnsignedCharArray(env, transformedData);
	const jint newLength = (jint)env->GetArrayLength(transformedData);
	jvmti->Allocate(newLength, new_data);
	*new_data_len = newLength;
	memcpy(*new_data, newChars, newLength);
};

jclass defineClassWithReflect(JNIEnv* env, jobject classLoader, jbyteArray bytes) {
	jclass clClass = env->FindClass("java/lang/ClassLoader");
	jmethodID defineClass = env->GetMethodID(clClass, "defineClass", "([BII)Ljava/lang/Class;");
	jobject classDefined = env->CallObjectMethod(classLoader, defineClass, bytes, 0, env->GetArrayLength(bytes));
	return (jclass)classDefined;
}

//**********************************Native methods
jbyteArray getString(JNIEnv* env, jclass, jint index) {
	char* b = new char[string_dim[index][1] - string_dim[index][0]];
	copy(bytes + string_dim[index][0], bytes + string_dim[index][1], b);
	return asByteArray(env, reinterpret_cast<unsigned char*>(b), string_dim[index][1] - string_dim[index][0]);
}
jclass defineClass(JNIEnv* env, jclass jcaller, jobject classLoader, jbyteArray bytes) {
	return defineClassWithReflect(env, classLoader, bytes);
}
jboolean setState(JNIEnv* env, jclass jcaller, jint type, jboolean state) {
	if (type == 1) {
		if (state == true) {
			jvmTi->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, NULL);
		}
		if (state == false) {
			jvmTi->SetEventNotificationMode(JVMTI_DISABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, NULL);
		}
	}
	return true;
}
jobjectArray getAllLoadedClasses(JNIEnv* env) {
	cout << "getAllLoadedClasses trigger" << endl;
	JNIEnv* jnienv = env;
	jint err = 0;
	jclass* jvmClasses;
	jint classCount;

	err = (jint)jvmTi->GetLoadedClasses(&classCount, &jvmClasses);
	if (err) {

		return asClassArray(jnienv, jvmClasses, classCount);
	}

	return asClassArray(jnienv, jvmClasses, classCount);
}
jbyteArray getClassBytes(JNIEnv* env,jclass,jclass target) {
	return NULL;
}
void nativeLog(JNIEnv* env, jclass, jstring log) {
	jboolean isCopy;
	const char* convertedValue = env->GetStringUTFChars(log, &isCopy);
	std::string str = convertedValue;
	cout << str << endl;
}
jint redefineClass(JNIEnv* env, jclass, jclass target, jbyteArray bytes) {
	return 0;
}
jint retransform(JNIEnv* env, jclass, jclass target, jobject transformer) {
	callbacks.ClassFileLoadHook = classTransformerHook;
	jvmTi->SetEventCallbacks(&callbacks, sizeof(callbacks));
	jvmTi->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, NULL);
	jclass* jvmClasses = new jclass[1];
	jvmClasses[0] = target;
	jvmtiError error = jvmTi->RetransformClasses(1, jvmClasses);
	jvmTi->SetEventNotificationMode(JVMTI_DISABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, NULL);
	return error;
}
//**********************************Native methods
jobjectArray asClassArray(JNIEnv* env, jclass* buf, int len) {
	jobjectArray array = env->NewObjectArray(len, env->FindClass("java/lang/Class"), NULL);
	for (int i = 0; i < len; i++) { env->SetObjectArrayElement(array, i, buf[i]); }
	return array;
}
jbyteArray asByteArray(JNIEnv* env, const unsigned char* buf, int len) {
	jbyteArray array = env->NewByteArray(len);
	env->SetByteArrayRegion(array, 0, len, (const jbyte*)buf);
	return array;
}
unsigned char* asUnsignedCharArray(JNIEnv* env, jbyteArray array) {
	int len = env->GetArrayLength(array);
	unsigned char* buf = new unsigned char[len];
	env->GetByteArrayRegion(array, 0, len, reinterpret_cast<jbyte*>(buf));
	return buf;
}

void hookJVM() {
	cout << "Hooking jvm" << endl;

	if (MH_Initialize() != MH_OK)
	{
		cout << "Fail to setup hook1" << endl;
		return;
	}
	if (MH_CreateHookApi(L"lwjgl64.dll", "Java_org_lwjgl_opengl_GL11_nglFlush",(void *)&nglFlush_Hook, reinterpret_cast<LPVOID*>(&nglFlush)) != MH_OK) {
		cout << "Fail to setup hook2" << endl;
		return;
	}
	if (MH_EnableHook(MH_ALL_HOOKS) != MH_OK) {
		cout << "Fail to setup hook3" << endl;
		return;
	}
	cout << "Hooked" << endl;
	return;
}

template <typename T>
inline MH_STATUS MH_CreateHookEx(LPVOID pTarget, LPVOID pDetour, T** ppOriginal)
{
	return MH_CreateHook(pTarget, pDetour, reinterpret_cast<LPVOID*>(ppOriginal));
}

template <typename T>
inline MH_STATUS MH_CreateHookApiEx(
	LPCWSTR pszModule, LPCSTR pszProcName, LPVOID pDetour, T** ppOriginal)
{
	return MH_CreateHookApi(
		pszModule, pszProcName, pDetour, reinterpret_cast<LPVOID*>(ppOriginal));
}
typedef struct
{
	HWND hwndWindow; // 窗口句柄
	DWORD dwProcessID; // 进程ID
}EnumWindowsArg;
///< 枚举窗口回调函数
BOOL CALLBACK EnumWindowsProc(HWND hwnd, LPARAM lParam)
{
	EnumWindowsArg* pArg = (EnumWindowsArg*)lParam;
	DWORD dwProcessID = 0;
	// 通过窗口句柄取得进程ID
	::GetWindowThreadProcessId(hwnd, &dwProcessID);
	if (dwProcessID == pArg->dwProcessID)
	{
		pArg->hwndWindow = hwnd;
		// 找到了返回FALSE
		return FALSE;
	}
	// 没找到，继续找，返回TRUE
	return TRUE;
}
///< 通过进程ID获取窗口句柄
HWND GetWindowHwndByPID(DWORD dwProcessID)
{
	HWND hwndRet = NULL;
	EnumWindowsArg ewa;
	ewa.dwProcessID = dwProcessID;
	ewa.hwndWindow = NULL;
	EnumWindows(EnumWindowsProc, (LPARAM)&ewa);
	if (ewa.hwndWindow)
	{
		hwndRet = ewa.hwndWindow;
	}
	return hwndRet;
}