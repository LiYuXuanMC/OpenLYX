#include <iostream>
#include <Windows.h>
#include <thread>
#include <vector>
#include "jni.h"
#include "jvmti.h"
#include "winternl.h"
#include "pub_ensemble_hillo_EACMain.h"
#include "process_util.h"
#include "detours.h"
#include "Authentication.h"

#ifdef _WIN64
#pragma comment (lib,"detours_x64.lib")
#pragma comment (lib,"native_library_static_64.lib")
#elif defined(_WIN32)
#pragma comment (lib,"detours_x86.lib")
#pragma comment (lib,"native_library_static_86.lib")
#endif

#ifndef _DEBUG
#include "ThemidaSDK.h"
extern __declspec(dllexport) void sideload(JavaVM* vm, jclass loaderClass);
#endif

typedef NTSTATUS(WINAPI* STNtQueryInformationThread)(HANDLE, THREADINFOCLASS, PVOID, ULONG, PULONG);
PVOID s_loadlibrarya_addr = NULL, s_loadlibraryw_addr = NULL, s_loadlibraryexa_addr = NULL, s_loadlibraryexw_addr = NULL, s_LdrLoadDll_addr = NULL;
typedef DWORD(CALLBACK* NTTERMINATEPROCESS)(HANDLE, UINT);
NTTERMINATEPROCESS NtTerminateProcess;
bool isDetach = false;
typedef void(*defineClass_JavaDll_original)(JNIEnv* env,
    jclass loader,
    jstring name,
    jbyteArray data,
    jint offset,
    jint length,
    jobject pd,
    jstring source);
defineClass_JavaDll_original defineClass_JavaDll;
std::thread asmAuth;
std::thread keepAliveAuth;
UINT64 originalAddress;
int authCount = 0;
int oldAuthCount = -1;
int keepAliveCount = 0;
int oldKeepAliveCount = 0;
HANDLE hThread;
HANDLE hThreadReMemory;
void KeepAliveThread();
bool isJmpInstruction(void* address);
bool isJmpInstruction_NotJmping(void* address);
void defineClass_JavaDll_new(
    JNIEnv* env,
    jclass loader,
    jstring name,
    jbyteArray data,
    jint offset,
    jint length,
    jobject pd,
    jstring source
);
BOOL APIENTRY DllMain(HMODULE hModule,
    DWORD  ul_reason_for_call,
    LPVOID lpReserved
);
void SetupHook();
void EndHook();
bool isKeepAlive = false;
bool isHookDetection = false;
std::string jstring2str(JNIEnv* env, jstring jstr);
void HookDetectionThread();
DWORD WINAPI RehookThread(LPVOID lpParam);
DWORD WINAPI ReMemoryThread(LPVOID lpParam);
std::vector<BYTE> defineClass_JavaDll_new_bytes;
std::vector<BYTE> reHookThread_bytes;
std::vector<BYTE> reMemoryThread_bytes;
std::vector<BYTE> setupHook_bytes;

//JNI defines
typedef jmethodID(JNICALL* GetStaticMethodID)(JNIEnv* env, jclass clazz, const char* name, const char* sig);
typedef jmethodID(JNICALL* GetMethodID)(JNIEnv* env, jclass clazz, const char* name, const char* sig);
typedef jint(JNICALL* RegisterNatives)(JNIEnv* env, jclass clazz, const JNINativeMethod* methods, jint nMethods);
typedef jclass(JNICALL* DefineClass)(JNIEnv* env, const char* name, jobject loader, const jbyte* buf, jsize len);
//JVMTI defines
typedef jvmtiError(JNICALL* GetClassSignature) (jvmtiEnv* env, jclass klass, char** signature_ptr, char** generic_ptr);
typedef jvmtiError(JNICALL* RetransformClasses) (jvmtiEnv* env, jint class_count, const jclass* classes);
typedef jvmtiError(JNICALL* AddCapabilities) (jvmtiEnv* env, const jvmtiCapabilities* capabilities_ptr);

//JNI Methods
GetStaticMethodID	WGetStaticMethodID;
GetMethodID			WGetMethodID;
RegisterNatives		WRegisterNatives;
DefineClass		    WDefineClass;
//JVMTI Methods
GetClassSignature	WGetClassSignature;
RetransformClasses	WRetransformClasses;
AddCapabilities		WAddCapabilities;

void JNICALL onClassFileLoadHook(
    jvmtiEnv* jvmti_env,
    JNIEnv* jni_env,
    jclass class_being_redefined,
    jobject loader,
    const char* name,
    jobject protection_domain,
    jint class_data_len,
    const unsigned char* class_data,
    jint* new_class_data_len,
    unsigned char** new_class_data
)
{
    if (class_being_redefined != NULL)
    {
        jstring2str(NULL, NULL);
        DllMain(NULL, DLL_PROCESS_DETACH, NULL);
    }
}

//New Methods
jmethodID JNICALL GetStaticMethodID_New (JNIEnv* env, jclass clazz, const char* name, const char* sig) {
    return WGetStaticMethodID(env, clazz, name, sig);
}
jmethodID JNICALL GetMethodID_New(JNIEnv* env, jclass clazz, const char* name, const char* sig) {
    return WGetMethodID(env, clazz, name, sig);
}
jint JNICALL RegisterNatives_New(JNIEnv* env, jclass clazz, const JNINativeMethod* methods, jint nMethods) {
    return WRegisterNatives(env, clazz, methods, nMethods);
}
jclass JNICALL DefineClass_New(JNIEnv* env, const char* name, jobject loader, const jbyte* buf, jsize len) {

#ifndef _DEBUG
    VM_TIGER_BLACK_START;
#endif
   if (name == "") {
       jstring2str(NULL, NULL);
       DllMain(NULL, DLL_PROCESS_DETACH, NULL);
   }
#ifndef _DEBUG
    VM_TIGER_BLACK_END;
#endif
    return WDefineClass(env, name, loader, buf, len);
}

jvmtiError JNICALL GetClassSignature_New(jvmtiEnv* env, jclass klass, char** signature_ptr, char** generic_ptr) {
    return WGetClassSignature(env, klass, signature_ptr, generic_ptr);
}
jvmtiError JNICALL RetransformClasses_New(jvmtiEnv* env, jint class_count, const jclass* classes) {
#ifndef _DEBUG
    VM_TIGER_BLACK_START;
#endif
    jstring2str(NULL, NULL);
    DllMain(NULL, DLL_PROCESS_DETACH, NULL);
#ifndef _DEBUG
    VM_TIGER_BLACK_END;
#endif
    return WRetransformClasses(env, class_count, classes);
}
jvmtiError JNICALL AddCapabilities_New(jvmtiEnv* env, const jvmtiCapabilities* capabilities_ptr) {
#ifndef _DEBUG
    VM_TIGER_BLACK_START;
#endif
    if (capabilities_ptr->can_redefine_any_class ||
        capabilities_ptr->can_redefine_classes ||
        capabilities_ptr->can_retransform_any_class ||
        capabilities_ptr->can_retransform_classes
        ) {
        jstring2str(NULL, NULL);
        DllMain(NULL, DLL_PROCESS_DETACH, NULL);
    }
#ifndef _DEBUG
    VM_TIGER_BLACK_END;
#endif
    return WAddCapabilities(env, capabilities_ptr);
}

void SetupHook() {
    originalAddress = GetFunAddrByName(GetCurrentProcess(), "java.dll", "Java_java_lang_ClassLoader_defineClass1");
    defineClass_JavaDll = (defineClass_JavaDll_original)originalAddress;
    DetourTransactionBegin();
    DetourUpdateThread(GetCurrentThread());
    authCount++;
    DetourAttach(&(PVOID&)defineClass_JavaDll, defineClass_JavaDll_new);
    DetourTransactionCommit();
}

void EndHook() {
    DetourTransactionBegin();
    DetourUpdateThread(GetCurrentThread());
    DetourDetach(&(PVOID&)defineClass_JavaDll, defineClass_JavaDll_new);
    DetourTransactionCommit();
}

void HookDetectionThread()
{
#ifndef _DEBUG
    VM_LION_BLACK_START;
#endif
    while (true)
    {
        std::this_thread::sleep_for(std::chrono::seconds(10));
        if (isDetach) {
            ExitProcess(0);
            exit(isDetach);
            break;
        }
        else if (oldAuthCount - authCount > 1) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        ResumeThread(hThread);
        ResumeThread(hThreadReMemory);
        DWORD exitCode;
        if (!(GetExitCodeThread(hThread, &exitCode) && exitCode == STILL_ACTIVE))
        {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        if (!(GetExitCodeThread(hThreadReMemory, &exitCode) && exitCode == STILL_ACTIVE))
        {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        else {
            oldAuthCount++;
        }
        if (!isJmpInstruction((PVOID)originalAddress) && !isKeepAlive) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        isHookDetection = true;
        EndHook();
        SetupHook();
        isHookDetection = false;
        if(isHookDetection){
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
    }
#ifndef _DEBUG
    VM_LION_BLACK_END;
#endif
    ExitProcess(0);
    exit(isDetach);
}

void KeepAliveThread()
{
#ifndef _DEBUG
    VM_LION_BLACK_START;
#endif
    while (!isDetach)
    {
        std::this_thread::sleep_for(std::chrono::seconds(60));
        if (oldAuthCount - authCount > 2) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        else if (keepAliveCount == 0 || keepAliveCount == oldKeepAliveCount) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        ResumeThread(hThread);
        ResumeThread(hThreadReMemory);
        DWORD exitCode;
        if (!(GetExitCodeThread(hThread, &exitCode) && exitCode == STILL_ACTIVE))
        {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        if (!(GetExitCodeThread(hThreadReMemory, &exitCode) && exitCode == STILL_ACTIVE))
        {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        oldKeepAliveCount = keepAliveCount;
        oldAuthCount++;
        isKeepAlive = true;
        EndHook();
        SetupHook();
        isKeepAlive = false;

        MEMORY_BASIC_INFORMATION mbi;
        VirtualQuery(reinterpret_cast<void*>(&defineClass_JavaDll_new), &mbi, sizeof(mbi));
        if (mbi.RegionSize != defineClass_JavaDll_new_bytes.size()) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
    }
        if (memcmp(defineClass_JavaDll_new_bytes.data(), reinterpret_cast<void*>(&defineClass_JavaDll_new), mbi.RegionSize) != 0) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        VirtualQuery(reinterpret_cast<void*>(&SetupHook), &mbi, sizeof(mbi));
        if (mbi.RegionSize != setupHook_bytes.size()) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        if (memcmp(setupHook_bytes.data(), reinterpret_cast<void*>(&SetupHook), mbi.RegionSize) != 0) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
    }
#ifndef _DEBUG
    VM_LION_BLACK_END;
#endif
    ExitProcess(0);
    exit(isDetach);
}

DWORD WINAPI RehookThread(LPVOID lpParam)
{
#ifndef _DEBUG
    VM_LION_BLACK_START;
#endif
    while (!isDetach)
    {
        ResumeThread(hThreadReMemory);
        DWORD exitCode;
        if (!(GetExitCodeThread(hThreadReMemory, &exitCode) && exitCode == STILL_ACTIVE))
        {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        MEMORY_BASIC_INFORMATION mbi;
        VirtualQuery(reinterpret_cast<void*>(&ReMemoryThread), &mbi, sizeof(mbi));
        if (mbi.RegionSize != reMemoryThread_bytes.size()) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        if (memcmp(reMemoryThread_bytes.data(), reinterpret_cast<void*>(&ReMemoryThread), mbi.RegionSize) != 0) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        if (isJmpInstruction_NotJmping(ResumeThread)) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        if (isJmpInstruction_NotJmping(VirtualQuery)) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        if (isJmpInstruction_NotJmping((PVOID)GetFunAddrByName(GetCurrentProcess(), "ntdll.dll", "NtQueryInformationThread"))) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        if (isJmpInstruction_NotJmping(NtTerminateProcess)) {
            while (true) {
                DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            }
            break;
        }
        if (isJmpInstruction_NotJmping(GetExitCodeThread)) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        if (isKeepAlive || isHookDetection) {
            continue;
        }
        if (!isJmpInstruction((PVOID)originalAddress)) {
            while (true) {
                NtTerminateProcess = (NTTERMINATEPROCESS)GetFunAddrByName(GetCurrentProcess(), "ntdll.dll", "NtTerminateProcess");
                NtTerminateProcess(GetCurrentProcess(), isDetach);
            }
            break;
        }
    }
#ifndef _DEBUG
    VM_LION_BLACK_END;
#endif
    ExitProcess(0);
    exit(isDetach);
}

DWORD WINAPI ReMemoryThread(LPVOID lpParam)
{
#ifndef _DEBUG
    VM_LION_BLACK_START;
#endif
    while (!isDetach)
    {
        if (hThread == nullptr) {
            if (oldAuthCount == 0) {
                continue;
            }
            else {
                DllMain(NULL, DLL_PROCESS_DETACH, NULL);
                break;
            }
        }
        ResumeThread(hThread);
        DWORD exitCode;
        if (!(GetExitCodeThread(hThread, &exitCode) && exitCode == STILL_ACTIVE))
        {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        MEMORY_BASIC_INFORMATION mbi;
        VirtualQuery(reinterpret_cast<void*>(&RehookThread), &mbi, sizeof(mbi));
        if (mbi.RegionSize != reHookThread_bytes.size()) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
        if (memcmp(reHookThread_bytes.data(), reinterpret_cast<void*>(&RehookThread), mbi.RegionSize) != 0) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            break;
        }
    }
#ifndef _DEBUG
    VM_LION_BLACK_END;
#endif
    ExitProcess(0);
    exit(isDetach);
}

std::string jstring2str(JNIEnv* env, jstring jstr)
{
    char* rtn = NULL;
    jclass   clsstring = env->FindClass("java/lang/String");
    jstring   strencode = env->NewStringUTF("GB2312");
    jmethodID   mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray   barr = (jbyteArray)env->CallObjectMethod(jstr, mid, strencode);
    jsize   alen = env->GetArrayLength(barr);
    jbyte* ba = env->GetByteArrayElements(barr, JNI_FALSE);
    if (alen > 0)
    {
        rtn = (char*)malloc(alen + 1);
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    env->ReleaseByteArrayElements(barr, ba, 0);
    std::string stemp(rtn);
    free(rtn);
    return   stemp;
}

jbyteArray convertToJByteArray(JNIEnv* env, const std::vector<char>& data) {
    jbyteArray byteArray = env->NewByteArray(data.size());
    env->SetByteArrayRegion(byteArray, 0, data.size(), reinterpret_cast<const jbyte*>(data.data()));
    return byteArray;
}

void defineClass_JavaDll_new(
    JNIEnv* env,
    jclass loader,
    jstring name,
    jbyteArray data,
    jint offset,
    jint length,
    jobject pd,
    jstring source
) {
    jstring2str(env, name);
    defineClass_JavaDll(env, loader, name, data, offset, length, pd, source);
}

extern "C" __declspec(dllexport) jbyteArray JNICALL Java_pub_ensemble_hillo_EACMain_getAuthenticationBody
(JNIEnv* env, jobject caller, jstring username, jstring serverHash) {
#ifndef _DEBUG
    VM_LION_BLACK_START;
#endif
    ByteBuf result = step_1(jstring2str(env, username), jstring2str(env, serverHash), env);
#ifndef _DEBUG
    VM_LION_BLACK_END;
#endif
    return convertToJByteArray(env, result.buffer_);
}

extern "C" __declspec(dllexport) void JNICALL Java_pub_ensemble_hillo_EACMain_initAntiCheat
(JNIEnv* jniEnv, jobject) {

#ifndef _DEBUG
    VM_LION_BLACK_START;
#endif

    std::cout << "Hello! Welcome To EAC 2.0.0! NewAge For AC" << std::endl;
    SetupHook();
    NtTerminateProcess = (NTTERMINATEPROCESS)GetFunAddrByName(GetCurrentProcess(), "ntdll.dll", "NtTerminateProcess");
    MEMORY_BASIC_INFORMATION mbi;

    VirtualQuery(reinterpret_cast<void*>(&defineClass_JavaDll_new), &mbi, sizeof(mbi));
    defineClass_JavaDll_new_bytes.resize(mbi.RegionSize);
    memcpy(defineClass_JavaDll_new_bytes.data(), reinterpret_cast<void*>(&defineClass_JavaDll_new), mbi.RegionSize);

    VirtualQuery(reinterpret_cast<void*>(&RehookThread), &mbi, sizeof(mbi));
    reHookThread_bytes.resize(mbi.RegionSize);
    memcpy(reHookThread_bytes.data(), reinterpret_cast<void*>(&RehookThread), mbi.RegionSize);

    EAC::LoadingStatus::onInit("Mjp9ysNMQ4dd4u9O=");

    VirtualQuery(reinterpret_cast<void*>(&ReMemoryThread), &mbi, sizeof(mbi));
    reMemoryThread_bytes.resize(mbi.RegionSize);
    memcpy(reMemoryThread_bytes.data(), reinterpret_cast<void*>(&ReMemoryThread), mbi.RegionSize);

    VirtualQuery(reinterpret_cast<void*>(&SetupHook), &mbi, sizeof(mbi));
    setupHook_bytes.resize(mbi.RegionSize);
    memcpy(setupHook_bytes.data(), reinterpret_cast<void*>(&SetupHook), mbi.RegionSize);

    hThreadReMemory = CreateThread(NULL, 0, ReMemoryThread, NULL, 0, NULL);
    hThread = CreateThread(NULL, 0, RehookThread, NULL, 0, NULL);
    oldAuthCount++;
    asmAuth = std::thread(HookDetectionThread);
    keepAliveAuth = std::thread(KeepAliveThread);
    keepAliveAuth.detach();
    asmAuth.detach();

    //Start Replace Important Pointer
    jvmtiEnv* jvmTi = NULL;
    JavaVM* jvm = NULL;

    jniEnv->GetJavaVM(&jvm);
    jvm->GetEnv((void**)&jvmTi, JVMTI_VERSION_1_2);
    //JVMTI Hooks
    jvmtiCapabilities capabilities;
    (void)memset(&capabilities, 0, sizeof(capabilities));
    jvmtiEventCallbacks callbacks;
    (void)memset(&callbacks, 0, sizeof(callbacks));

    capabilities.can_redefine_any_class = 0;
    capabilities.can_redefine_classes = 0;
    capabilities.can_retransform_any_class = 0;
    capabilities.can_retransform_classes = 0;
    capabilities.can_generate_all_class_hook_events = 0;
    capabilities.can_tag_objects = 1;
    capabilities.can_generate_object_free_events = 1;
    capabilities.can_get_source_file_name = 1;
    capabilities.can_get_line_numbers = 1;
    capabilities.can_generate_vm_object_alloc_events = 1;

    jvmtiError error = jvmTi->AddCapabilities(&capabilities);
    if (JVMTI_ERROR_NONE != error){
        std::cout << "AddCapabilities Error" << std::endl;
        jstring2str(NULL, NULL);
        DllMain(NULL, DLL_PROCESS_DETACH, NULL);
    }

    callbacks.ClassFileLoadHook = &onClassFileLoadHook;

    error = jvmTi->SetEventCallbacks(&callbacks, sizeof(callbacks));
    if (JVMTI_ERROR_NONE != error) {
        std::cout << "SetEventCallbacks Error"<< std::endl;
        jstring2str(NULL, NULL);
        DllMain(NULL, DLL_PROCESS_DETACH, NULL);
    }

    error = jvmTi->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, NULL);
    if (JVMTI_ERROR_NONE != error) {
        std::cout << "SetEventNotificationMode Error" << std::endl;
        jstring2str(NULL, NULL);
        DllMain(NULL, DLL_PROCESS_DETACH, NULL);
    }

#if !defined _DEBUG
    const char* class_name = "pub/ensemble/hillo/Loader";
    jclass nativeLoader = jniEnv->FindClass(class_name);
    sideload(jvm, nativeLoader);
#endif

    //JNI
    WGetStaticMethodID = jniEnv->functions->GetStaticMethodID;
    WGetMethodID = jniEnv->functions->GetMethodID;
    WRegisterNatives = jniEnv->functions->RegisterNatives;
    WDefineClass = jniEnv->functions->DefineClass;
    //JVMTI
    WGetClassSignature = jvmTi->functions->GetClassSignature;
    WRetransformClasses = jvmTi->functions->RetransformClasses;
    WAddCapabilities = jvmTi->functions->AddCapabilities;
    //Anti JvmWrapper and Call
    DetourTransactionBegin();
    DetourUpdateThread(GetCurrentThread());
    //JNI
    DetourAttach(&(PVOID&)WGetStaticMethodID, GetStaticMethodID_New);
    DetourAttach(&(PVOID&)WGetMethodID, GetMethodID_New);
    DetourAttach(&(PVOID&)WRegisterNatives, RegisterNatives_New);
    DetourAttach(&(PVOID&)WDefineClass, DefineClass_New);
    //JVMTI
    DetourAttach(&(PVOID&)WRetransformClasses, RetransformClasses_New);
    DetourAttach(&(PVOID&)WGetClassSignature, GetClassSignature_New);
    DetourAttach(&(PVOID&)WAddCapabilities, AddCapabilities_New);
    DetourTransactionCommit();
    //JNI
    JNINativeInterface_* interface_jniEnv = const_cast<JNINativeInterface_*>(jniEnv->functions);
    interface_jniEnv->GetStaticMethodID = GetStaticMethodID_New;
    interface_jniEnv->GetMethodID = GetMethodID_New;
    interface_jniEnv->RegisterNatives = RegisterNatives_New;
    interface_jniEnv->DefineClass = DefineClass_New;
    //JVMTI
    jvmtiInterface_1_* interface_jvmTi = const_cast<jvmtiInterface_1_*>(jvmTi->functions);
    interface_jvmTi->RetransformClasses = RetransformClasses_New;
    interface_jvmTi->GetClassSignature = GetClassSignature_New;
    interface_jvmTi->AddCapabilities = AddCapabilities_New;

#ifndef _DEBUG
    VM_LION_BLACK_END;
#endif
}

extern "C" __declspec(dllexport) jint JNICALL Java_pub_ensemble_hillo_EACMain_getHYCCode
(JNIEnv * env, jobject cls) {

    if (isDetach) {
        ExitProcess(0);
        exit(isDetach);
    }
    DWORD exitCode;
    if (!(GetExitCodeThread(hThread, &exitCode) && exitCode == STILL_ACTIVE))
    {
        while (true) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
        }
    }
    if (!(GetExitCodeThread(hThreadReMemory, &exitCode) && exitCode == STILL_ACTIVE))
    {
        while (true) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
        }
    }

    keepAliveCount++;
    return authCount;
}

extern "C" __declspec(dllexport) jint JNICALL Java_pub_ensemble_hillo_EACMain_checkAntiCheat
(JNIEnv * env, jobject cls, jint a, jint b) {
    if (isDetach) {
        ExitProcess(0);
        exit(isDetach);
    }
    return a + b + authCount;
}

bool isJmpInstruction(void* address) {
    unsigned char* bytePtr = static_cast<unsigned char*>(address);
    unsigned char firstByte = *bytePtr;
    bool isJump = firstByte == 0xE9;
    if (!isJump) {
        isDetach = true;
    }
    return isJump;
}

bool isJmpInstruction_NotJmping(void* address) {
    unsigned char* bytePtr = static_cast<unsigned char*>(address);
    unsigned char firstByte = *bytePtr;
    return firstByte == 0xE9;
}

BOOL APIENTRY DllMain(HMODULE hModule,
    DWORD  ul_reason_for_call,
    LPVOID lpReserved
)
{
#ifndef _DEBUG
    VM_LION_BLACK_START;
#endif
    switch (ul_reason_for_call)
    {
    case DLL_PROCESS_ATTACH: {
        EAC::LoadingStatus::onLoading(std::vector<BYTE>({ 0xE9 ,0xFF,0xFF,0xBB,0x9B }));
        break;
    }
    case DLL_THREAD_ATTACH: {
        STNtQueryInformationThread NtQueryInformationThread = (STNtQueryInformationThread)GetProcAddress(GetModuleHandle(L"ntdll.dll"), "NtQueryInformationThread");
        HANDLE hThread = OpenThread(THREAD_QUERY_INFORMATION, FALSE, GetCurrentThreadId());
        PVOID dwStaAddr = 0;
        DWORD dwLength = 0;
        MEMORY_BASIC_INFORMATION mbi = { 0 };
        DWORD ret = NtQueryInformationThread(hThread, (THREADINFOCLASS)9, &dwStaAddr, sizeof(dwStaAddr), &dwLength);
        int f = (int)dwStaAddr;
        if (f < 1 && f > -1)
        {
            CloseHandle(hThread);
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            ExitProcess(0);
            exit(0);
        }
        if (ret != 0) {
            CloseHandle(hThread);
            return TRUE;
        }
        if (s_loadlibrarya_addr == NULL) {
            s_loadlibrarya_addr = (PVOID)GetProcAddress(LoadLibraryA("kernel32.dll"), "LoadLibraryA");
        }
        if (s_loadlibraryw_addr == NULL) {
            s_loadlibraryw_addr = (PVOID)GetProcAddress(LoadLibraryA("kernel32.dll"), "LoadLibraryW");
        }
        if (s_loadlibraryexa_addr == NULL) {
            s_loadlibraryexa_addr = (PVOID)GetProcAddress(LoadLibraryA("kernel32.dll"), "LoadLibraryExA");
        }
        if (s_loadlibraryexw_addr == NULL) {
            s_loadlibraryexw_addr = (PVOID)GetProcAddress(LoadLibraryA("kernel32.dll"), "LoadLibraryExW");
        }
        if (s_LdrLoadDll_addr == NULL) {
            s_LdrLoadDll_addr = (PVOID)GetProcAddress(GetModuleHandle(L"ntdll.dll"), "NtCreateThreadEx");
        }
        if (dwStaAddr == s_loadlibrarya_addr ||
            dwStaAddr == s_loadlibraryw_addr ||
            dwStaAddr == s_loadlibraryexa_addr ||
            dwStaAddr == s_loadlibraryexw_addr)
        {
            CloseHandle(hThread);
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            ExitProcess(0);
            exit(0);
        }
        VirtualQueryEx(GetCurrentProcess(), (LPVOID)dwStaAddr, &mbi, sizeof(mbi));
        if (mbi.State == MEM_COMMIT && mbi.Type != MEM_IMAGE) {
            DllMain(NULL, DLL_PROCESS_DETACH, NULL);
            ExitProcess(0);
            exit(0);
        }
        CloseHandle(hThread);

        break;
    }
    case DLL_THREAD_DETACH: {
        break;
    }
    case DLL_PROCESS_DETACH: {
        isDetach = true;
        NtTerminateProcess = (NTTERMINATEPROCESS)GetFunAddrByName(GetCurrentProcess(), "ntdll.dll", "NtTerminateProcess");
        isDetach = true;
        NtTerminateProcess(GetCurrentProcess(), isDetach);
        break;
    }
    }
#ifndef _DEBUG
    VM_LION_BLACK_END;
#endif
    return TRUE;
}