#include "pch.h"
#include "psapi.h"
#include "jni.h"
#include "jvmti.h"
#include "classes.h"
#include "ThemidaSDK.h"
#include <stdio.h>
#include <stack>
#include <cstring>
#include "utils.h"
#include "jvm_wrapper.h"
#include "md5.h"
#include "AntiCheatData.h"
#include <winsock.h>
#pragma comment(lib,"ws2_32.lib")
#if defined _DEBUG
#include "export_debug.h"
#else
#include "export.h"
#pragma comment(lib, "native_library_static.lib")
#endif


bool detectNAC();
jobject getClassLoader(JNIEnv* env);
bool injectLoader(JNIEnv* env, jobject classLoader);
void registerNative(JNIEnv* env, jclass caller, jclass target);
bool start(JNIEnv* env, jclass loaderClass,jclass nativeLoader);
jclass defineClass(JNIEnv* env, jvmtiEnv* jvmti, jobject classLoader, jbyteArray bytes, const char* name, jsize size);
jclass defineClass0(JNIEnv* env, jclass caller, jobject classLoader, jstring name, jbyteArray bytes);
void envDetect(JNIEnv* env);
std::string TCHAR2STRING(TCHAR* STR);
void nativeLog(JNIEnv* env, jclass, jstring log);
bool compareMemory(PVOID address, BYTE data[], int length);
void JNICALL ClassFileLoadHookCallBack
(jvmtiEnv* jvmti,
	JNIEnv* env,
	jclass class_being_redefined,
	jobject loader, const char* name,
	jobject protection_domain,
	jint data_len,
	const unsigned char* data,
	jint* new_data_len,
	unsigned char** new_data
);


typedef const char* (*GetClassNameUTF)(JNIEnv* env, jclass cb);
typedef jint(*GetCreatedJavaVMs)(JavaVM** vmBuf, jsize bufLen, jsize* nVMs);
typedef jclass(*FindClassFromCaller)(JNIEnv* env, const char* name, jboolean init, jobject loader, jclass caller);
typedef jclass(*DefineClass)(JNIEnv* env, const char* name, jobject loader, const jbyte* buf, jsize len, jobject pd);
typedef jmethodID(JNICALL* GetMethodID)(JNIEnv* env, jclass clazz, const char* name, const char* sig);
typedef jint(JNICALL* RegisterNatives)(JNIEnv* env, jclass clazz, const JNINativeMethod* methods, jint nMethods);
typedef jvmtiError(JNICALL* GetClassSignature) (jvmtiEnv* env,jclass klass,char** signature_ptr,char** generic_ptr);
typedef jvmtiError(JNICALL* RetransformClasses) (jvmtiEnv* env,jint class_count,const jclass* classes);
typedef jvmtiError(JNICALL* AddCapabilities) (jvmtiEnv* env,const jvmtiCapabilities* capabilities_ptr);
#define getClass(name) {findClassFromCaller(jniEnv, #name, false, classLoader, java_lang_ClassLoader), sizeof(##name) / sizeof(##name[0]), ##name}
typedef jmethodID(JNICALL* GetStaticMethodID)(JNIEnv* env, jclass clazz, const char* name, const char* sig);
typedef jint(JNICALL* RegisterNatives)(JNIEnv* env, jclass clazz, const JNINativeMethod* methods,jint nMethods);

extern void InitializeJVMWrapper(JNIEnv*);
#if !defined _DEBUG
extern __declspec(dllexport) void sideload(JavaVM* vm, jclass loaderClass);
#endif

using namespace std;


void init(JNIEnv* env,jclass searchCls) {
	int CCI = 0;
	int CD = 0;
	int CP = 0;

#if !defined _DEBUG
	VM_LION_BLACK_START;
#endif
#if !defined _DEBUG


	CHECK_CODE_INTEGRITY(CCI, 0x12345678);
	CHECK_DEBUGGER(CD, 0x1234567);
	CHECK_PROTECTION(CP, 0x123456);


	string data ="RelixNativeDLL=Release230110";
	int SendPacket_len = 0;
	int recvPacket_len = 0;
	char recvPacket_buffer[1024];

	SOCKET m_Server;
	SOCKADDR_IN serverAddress;

	WORD Wreq = MAKEWORD(2, 2);
	WSADATA wsa_date;
	int nodeError = WSAStartup(Wreq, &wsa_date);
	if (nodeError != 0) {
		MessageBox(NULL, L"Socket Init", L"Logger", NULL);
		ExitProcess(0);
	}
	if (LOBYTE(wsa_date.wVersion) != 2 || HIBYTE(wsa_date.wHighVersion) != 2) {
		MessageBox(NULL, L"Socket Version Error", L"Logger", NULL);
		ExitProcess(0);
	}
	
	serverAddress.sin_family = AF_INET;
	serverAddress.sin_addr.S_un.S_addr = inet_addr("111.180.205.223");
	serverAddress.sin_port = htons(1732);
	m_Server = socket(AF_INET, SOCK_STREAM, 0);
	nodeError = connect(m_Server, (SOCKADDR*)&serverAddress, sizeof(SOCKADDR));
	if (nodeError == SOCKET_ERROR) {
		MessageBoxA(NULL, "Socket Connection Error", "Logger", NULL);
		ExitProcess(0);
	}


	SendPacket_len = send(m_Server, data.c_str(), 1024, 0);
	if (SendPacket_len < 0) {
		MessageBoxA(NULL, "Socket Timed Out", "Logger", NULL);
		ExitProcess(0);
	}

	if (CCI != 0x12345678) { ExitProcess(0); }
	if (CD != 0x1234567) { ExitProcess(0); }
	if (CP != 0x123456) { ExitProcess(0); }

	while (true) {
		recvPacket_len = recv(m_Server, recvPacket_buffer, 1024, 0);
		if (recvPacket_len > 80)
		{
			printf("Verified!\n");
			InitializeJVMWrapper(env);

			envDetect(env);
			JavaVM* jvm = NULL;
			env->GetJavaVM(&jvm);
			jvmtiEnv* jvmTi = NULL;
			if (!jvm) {
				cout << "jvm fail" << endl;
			}
			jvm->GetEnv((void**)&jvmTi, JVMTI_VERSION_1_2);
			if (!jvmTi) {
				cout << "jvmTi fail" << endl;
			}
			jobject classLoaderObj = getClassLoader(env);
			injectLoader(env, classLoaderObj);
			break;
		}
	}

#endif


#if defined _DEBUG

	InitializeJVMWrapper(env);

	envDetect(env);
	JavaVM* jvm = NULL;
	env->GetJavaVM(&jvm);
	jvmtiEnv* jvmTi = NULL;
	if (!jvm) {
		cout << "jvm fail" << endl;
	}
	jvm->GetEnv((void**)&jvmTi, JVMTI_VERSION_1_2);
	if (!jvmTi) {
		cout << "jvmTi fail" << endl;
	}
	jobject classLoaderObj = getClassLoader(env);
	injectLoader(env, classLoaderObj);
	//inject(env, jvmTi, classLoaderObj);
	//registerNativeFunc(env, jvmTi);
	//start(env, jvmTi);

#endif

#if !defined _DEBUG
	if (CCI != 0x12345678) { ExitProcess(0); }
	if (CD != 0x1234567) { ExitProcess(0); }
	if (CP != 0x123456) { ExitProcess(0); }
	VM_LION_BLACK_END;
#endif
}
bool injectLoader(JNIEnv* env, jobject classLoader) {

#if !defined _DEBUG
	STR_ENCRYPTW_START;
#endif
	jvmtiEnv* jvmTi = NULL;
	JavaVM* jvm = NULL;
	cout << &ClassFileLoadHookCallBack << endl;
	env->GetJavaVM(&jvm);
	jvm->GetEnv((void**)&jvmTi, JVMTI_VERSION_1_2);
#if defined _DEBUG
	cout << "define logger" << endl;
#endif
	int classNum = 0;
	jclass mainClass = NULL;
	jclass loadClass = NULL;
	jclass nativeLoader = NULL;
	jsize tempClassIndex = 0;
	jsize lastClassIndex = 0;
	std::vector<unsigned char> classes = getCombinedArray();
#if defined _DEBUG
	cout << "buffer allocated" << endl;
#endif
	for (jsize j = 0; j != classCount; j++) {
		char* lastClass = new char[classSizes[j] + 1];
		for (jsize classIndex = 0; classIndex != classSizes[j]; classIndex++) {
			tempClassIndex++;;
			lastClass[classIndex] = classes[lastClassIndex + classIndex] ^ classXorKey[j];
		}
		jbyteArray classBytes = env->NewByteArray(classSizes[j]);
		env->SetByteArrayRegion(classBytes, 0, classSizes[j], (jbyte*)lastClass);
		loadClass = env->DefineClass(NULL, classLoader, (jbyte*)lastClass, classSizes[j]);//
		//loadClass = defineClass(env, jvmTi, classLoader, classBytes, classNames[j], classSizes[j]);
		//loadClass = defineClass(env, jvmTi, classLoader, classBytes, classNames[j], classSizes[j]);
		if (!loadClass) {
			//MessageBoxA(NULL, "Error on class defining", randstr(name, 8), MB_OK | MB_ICONERROR);
			//jvm->DetachCurrentThread();
			//break;
#if defined _DEBUG
			cout << classNames[j] << endl;

			cout << "Load 1 class fail" << endl;
#endif
		}
		else {
			classNum++;
		}
		//cout << "defined" << endl;
		char* signature;
		WGetClassSignature(jvmTi,loadClass, &signature, NULL);
		if (!strcmp(signature, al_logger_client_Logger_className))
		{
			mainClass = loadClass;
#if defined _DEBUG
			cout << "Found MainClass!" << endl;
#endif
		}
		if (!strcmp(signature, "Lal/logger/Loader;"))
		{
			nativeLoader = loadClass;
			cout << "Found Native!" << endl;
		}
		delete[]lastClass;
		lastClassIndex = tempClassIndex;
	}
#if defined _DEBUG
	cout << "Defined " << classNum << " Classes For Logger" << endl;
#endif
	JNINativeMethod natives[] = {
		{const_cast <char*>(al_logger_client_Logger_registerNative_methodName),const_cast <char*>("(Ljava/lang/Class;)V"),reinterpret_cast<void*>(registerNative)},
	};
	if (WRegisterNatives(env,mainClass, natives, sizeof(natives) / sizeof(JNINativeMethod)) < 0) {
#if defined _DEBUG
		cout << "fail to register native" << endl;
#endif
	}
#if defined _DEBUG
	cout << "registerNative succ" << endl;
#endif
	start(env, mainClass,nativeLoader);
#if !defined _DEBUG
	STR_ENCRYPTW_END;
#endif
	return true;
}
jclass defineClass0(JNIEnv* env, jclass caller,jobject classLoader,jstring name,jbyteArray bytes) {
	jvmtiEnv* jvmTi = NULL;
	JavaVM* jvm = NULL;

	env->GetJavaVM(&jvm);
	jvm->GetEnv((void**)&jvmTi, JVMTI_VERSION_1_2);
	jboolean isCopy;
	return defineClass(env, jvmTi, classLoader, bytes, env->GetStringUTFChars(name, &isCopy), env->GetArrayLength(bytes));
}
bool start(JNIEnv* env, jclass loaderClass,jclass nativeLoader) {
#if !defined _DEBUG
	STR_ENCRYPTW_START;
#endif
#if !defined _DEBUG
	JavaVM* jvm = NULL;

	env->GetJavaVM(&jvm);
	sideload(jvm,nativeLoader);
#endif
	jmethodID loaderid = NULL;
	loaderid = WGetMethodID(env,loaderClass, "<init>", "()V");
	if (!loaderid) {
#if defined _DEBUG
		cout << "Loader method not found" << endl;
#endif
		return false;
	}
	jobject LoadClent = env->NewObject(loaderClass, loaderid);
	if (!LoadClent) {
#if defined _DEBUG
		cout << "new Load Client Error" << endl;
#endif
		return false;
	}
#if !defined _DEBUG
	STR_ENCRYPTW_END;
#endif
	return true;
}
jclass defineClass(JNIEnv* env, jvmtiEnv* jvmti, jobject classLoader, jbyteArray bytes, const char* name, jsize size) {

	jclass java_lang_ClassLoader = env->FindClass("java/lang/ClassLoader");
	//jmethodID defineClass = env->GetMethodID(java_lang_ClassLoader, "defineClass", "(Ljava/lang/String;[BII)Ljava/lang/Class;");
	jmethodID defineClass = WGetMethodID(env,java_lang_ClassLoader, "defineClass", "(Ljava/lang/String;[BII)Ljava/lang/Class;");
	if (!defineClass) {
#if defined _DEBUG
		cout << "defineClass" << endl;
#endif
	}
	jstring className = env->NewStringUTF(name);
	jclass defined = (jclass)env->CallObjectMethod(classLoader, defineClass, className, bytes, 0, size);
	if (env->ExceptionCheck()) {
#if defined _DEBUG
		cout << "exception" << endl;
#endif
	}
	if (!defined) {
#if defined _DEBUG
		cout << "Define class " << name << " fail" << endl;
#else
		cout << "fail" << endl;
#endif
	}
	return defined;
}
unsigned char* asUnsignedCharArray(JNIEnv* env, jbyteArray array) {
	int len = env->GetArrayLength(array);
	unsigned char* buf = new unsigned char[len];
	env->GetByteArrayRegion(array, 0, len, reinterpret_cast<jbyte*>(buf));
	return buf;
}
jbyteArray asByteArray(JNIEnv* env, const unsigned char* buf, int len) {
	jbyteArray array = env->NewByteArray(len);
	env->SetByteArrayRegion(array, 0, len, (const jbyte*)buf);
	return array;
}
jclass defineClassWithReflect(JNIEnv* env, jobject classLoader, jbyteArray bytes) {
	jclass clClass = env->FindClass("java/lang/ClassLoader");
	jmethodID defineClass = WGetMethodID(env,clClass, "defineClass", "([BII)Ljava/lang/Class;");
	jobject classDefined = env->CallObjectMethod(classLoader, defineClass, bytes, 0, env->GetArrayLength(bytes));
	return (jclass)classDefined;
}

jclass findClass(JNIEnv* env, jvmtiEnv* jvmti, const char* name) {
	jclass* loadedClasses;
	jint loadedClassesCount = 0;
	jvmti->GetLoadedClasses(&loadedClassesCount, &loadedClasses);

	jclass findClass = NULL;
	for (jint i = 0; i < loadedClassesCount; i++)
	{
		char* signature;
		WGetClassSignature(jvmti,loadedClasses[i], &signature, NULL);
		if (!strcmp(signature, name))
		{
			findClass = loadedClasses[i];
			return findClass;
		}
	}
	return NULL;
}
static jrawMonitorID lock;
void JNICALL ClassFileLoadHookCallBack
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
	jvmti->RawMonitorEnter(lock);
	if (!class_being_redefined || !data || !data_len) {
		jvmti->RawMonitorExit(lock);
		return;
	}
	cout << name << endl;
	cout << (void*)(data) << endl;
	cout << (void*)new_data << endl;
	jvmti->Allocate(data_len, new_data);
	jclass transformerClass = findClass(env, jvmti, al_logger_client_transform_TransformManager_className);
	jmethodID transfromMethod = WGetStaticMethodID(env,transformerClass, al_logger_client_transform_TransformManager_onTransform_methodName, "(Ljava/lang/Class;[B)[B");
	jbyteArray classdata = asByteArray(env, data, data_len);
	jbyteArray transformedData = env->NewByteArray(0);
	if (!class_being_redefined || !classdata || !transformerClass || !transfromMethod) {
		*new_data_len = data_len;
		memcpy(*new_data, data, data_len);
		jvmti->RawMonitorExit(lock);
		return;
	}
	transformedData = (jbyteArray)env->CallStaticObjectMethod(transformerClass, transfromMethod, class_being_redefined, classdata);
	if (!transformedData) {
		*new_data_len = data_len;
		memcpy(*new_data, data, data_len);
		jvmti->RawMonitorExit(lock);
		return;
	}
	unsigned char* newChars = asUnsignedCharArray(env, transformedData);
	const jint newLength = (jint)env->GetArrayLength(transformedData);
	jvmti->Allocate(newLength, new_data);
	*new_data_len = newLength;
	memcpy(*new_data, newChars, newLength);
	jvmti->RawMonitorExit(lock);
};
jint transform(JNIEnv* env, jclass, jclass target) {
	jvmtiEnv* jvmTi = NULL;
	JavaVM* jvm = NULL;
	env->GetJavaVM(&jvm);
	jvm->GetEnv((void**)&jvmTi, JVMTI_VERSION_1_2);
	jvmtiCapabilities capabilities;
	memset(&capabilities, 0, sizeof(jvmtiCapabilities));
	capabilities.can_retransform_any_class = 1;
	capabilities.can_retransform_classes = 1;
	capabilities.can_redefine_any_class = 1;
	capabilities.can_redefine_classes = 1;
	jvmtiError error = WAddCapabilities(jvmTi,&capabilities);
	jvmtiEventCallbacks callbacks;
	(void)memset(&callbacks, 0, sizeof(callbacks));
	callbacks.ClassFileLoadHook = ClassFileLoadHookCallBack;
	jvmTi->SetEventCallbacks(&callbacks, sizeof(callbacks));
	jvmTi->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, NULL);
	
	jclass* jvmClasses = new jclass[1];
	jvmClasses[0] = target;
	jvmtiError error2 = WRetransformClasses(jvmTi,1, jvmClasses);
	jvmTi->SetEventNotificationMode(JVMTI_DISABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, NULL);
	return error2;
}
void registerNative(JNIEnv* env, jclass caller, jclass target) {
#if !defined _DEBUG
	STR_ENCRYPTW_START;
#endif
	jvmtiEnv* jvmTi = NULL;
	JavaVM* jvm = NULL;
	env->GetJavaVM(&jvm);
	jvm->GetEnv((void**)&jvmTi, JVMTI_VERSION_1_2);
	char* signature;
	WGetClassSignature(jvmTi,target, &signature, NULL);
#if defined _DEBUG
	cout << "register native to " << signature << endl;
#endif
	if (!strcmp(signature, al_logger_client_agent_NativeAgent_className)) {
#if defined _DEBUG
		cout << "register native to " << signature << endl;
#endif
		//private static native Class<?> defineClass0(ClassLoader classLoader, String name, byte[] classBytes);
		JNINativeMethod natives[] = {
			{const_cast <char*>(al_logger_client_agent_NativeAgent_defineClass0_methodName),const_cast <char*>("(Ljava/lang/ClassLoader;Ljava/lang/String;[B)Ljava/lang/Class;"),reinterpret_cast<void*>(defineClass0)},
			{const_cast<char*>(al_logger_client_agent_NativeAgent_retransform0_methodName),const_cast<char*>("(Ljava/lang/Class;)I"),reinterpret_cast<void*>(transform)},
			{const_cast<char*>(al_logger_client_agent_NativeAgent_nativeLog0_methodName),const_cast<char*>("(Ljava/lang/String;)V"),reinterpret_cast<void*>(nativeLog)},
		};
		if (WRegisterNatives(env,target, natives, sizeof(natives) / sizeof(JNINativeMethod)) < 0) {
#if defined _DEBUG
			cout << "fail to register native" << endl;
#endif
		}
	}
#if defined _DEBUG
	cout << "register succ" << endl;
#endif
#if !defined _DEBUG
	STR_ENCRYPTW_END;
#endif
}
void nativeLog(JNIEnv* env, jclass, jstring log) {
	jboolean isCopy;
	const char* convertedValue = env->GetStringUTFChars(log, &isCopy);
	std::string str = convertedValue;
	cout << str << endl;
}
jobject getClassLoader(JNIEnv* env) {
	jvmtiEnv* jvmTi = NULL;
	JavaVM* jvm = NULL;
	env->GetJavaVM(&jvm);
	jvm->GetEnv((void**)&jvmTi, JVMTI_VERSION_1_2);

	jthread* thr_ptr;
	jint thr_count;
	jvmTi->GetAllThreads(&thr_count, &thr_ptr);
	jvmtiThreadInfo info1;
	if (thr_count >= 1) {
		for (int i = 0; i < thr_count; i++) {
			jvmTi->GetThreadInfo(thr_ptr[i], &info1);
			if (strstr(info1.name, "Client thread"))
			{
#if defined _DEBUG
				cout<< "ClassLoader of Client thread was found!" <<endl;
#endif
				return info1.context_class_loader;
			}
		}
	}
	return NULL;
}
void envDetect(JNIEnv* env) {
#if !defined _DEBUG
	VM_LION_BLACK_START;
#endif

	JavaVM* jvm = NULL;
	env->GetJavaVM(&jvm);
	jvmtiEnv* jvmTi = NULL;
	if (!jvm) {
#if defined _DEBUG
		cout << "jvm fail" << endl;
#endif
	}
	jvm->GetEnv((void**)&jvmTi, JVMTI_VERSION_1_2);
	if (!jvmTi) {
#if defined _DEBUG
		cout << "jvmTi fail" << endl;
#endif
	}
#if defined _DEBUG
		cout << "Dumping base address" << endl;
		cout << "GetMethodID" << "->" << WGetMethodID << endl;
		cout << "GetStaticMethodID" << "->" << WGetStaticMethodID << endl;
		cout << "RegisterNatives" << "->" << WRegisterNatives << endl;
		cout << "GetJavaVM" << "->" << env->functions->GetJavaVM << endl;
		cout << "SetByteArrayRegion" << "->" << env->functions->SetByteArrayRegion << endl;
		cout << "GetClassSignature" << "->" << WGetClassSignature << endl;
		cout << "RetransformClasses" << "->" << WRetransformClasses << endl;
		cout << "SetEventCallbacks" << "->" << jvmTi->functions->SetEventCallbacks << endl;
		cout << "SetEventNotificationMode" << "->" << jvmTi->functions->SetEventNotificationMode << endl;
		cout << "Allocate" << "->" << jvmTi->functions->Allocate << endl;
		cout << "AddCapabilities" << "->" << WAddCapabilities << endl;
#endif
		if (detectNAC()) {
			//你是真几把闲
			HANDLE hProcess = OpenProcess(PROCESS_QUERY_INFORMATION | PROCESS_VM_READ, FALSE, GetCurrentProcessId());

			UINT64 JVM_Address = (UINT64)GetProcessMoudleBase(hProcess, "jvm.dll");
			UINT64 JNI_CreateJavaVM_Address = GetFunAddrByName(GetCurrentProcess(), "jvm.dll", "JNI_CreateJavaVM");
			UINT64 JNI_GetCreatedJavaVMs_Address = GetFunAddrByName(GetCurrentProcess(), "jvm.dll", "JNI_GetCreatedJavaVMs");
			
			for (int i = 0; i != GetStaticMethodIDOffset.size(); i++) {
				if (compareMemory(reinterpret_cast<void*>(JNI_CreateJavaVM_Address + GetStaticMethodIDOffset[i].offset), GetStaticMethodIDOffset[i].bytes.data(),8)) {
					WGetStaticMethodID = (GetStaticMethodID)(JNI_CreateJavaVM_Address + GetStaticMethodIDOffset[i].offset);
				}
			}
			for (int i = 0; i != GetMethodIDOffset.size(); i++) {
				if (compareMemory(reinterpret_cast<void*>(JNI_CreateJavaVM_Address + GetMethodIDOffset[i].offset), GetMethodIDOffset[i].bytes.data(), 8)) {
					WGetMethodID = (GetMethodID)(JNI_CreateJavaVM_Address + GetMethodIDOffset[i].offset);
				}
			}
			for (int i = 0; i != RegisterNativesOffset.size(); i++) {
				if (compareMemory(reinterpret_cast<void*>(JNI_GetCreatedJavaVMs_Address + RegisterNativesOffset[i].offset), RegisterNativesOffset[i].bytes.data(), 8)) {
					WRegisterNatives = (RegisterNatives)(JNI_GetCreatedJavaVMs_Address + RegisterNativesOffset[i].offset);
				}
			}
			for (int i = 0; i != GetClassSignatureOffset.size(); i++) {
				if (compareMemory(reinterpret_cast<void*>(JVM_Address + GetClassSignatureOffset[i].offset), GetClassSignatureOffset[i].bytes.data(), 8)) {
					WGetClassSignature = (GetClassSignature)(JVM_Address + GetClassSignatureOffset[i].offset);
				}
			}
			for (int i = 0; i != RetransformClassesOffset.size(); i++) {
				if (compareMemory(reinterpret_cast<void*>(JVM_Address + RetransformClassesOffset[i].offset), RetransformClassesOffset[i].bytes.data(), 8)) {
					WRetransformClasses = (RetransformClasses)(JVM_Address + RetransformClassesOffset[i].offset);
				}
			}
			for (int i = 0; i != AddCapabilitiesOffset.size(); i++) {
				if (compareMemory(reinterpret_cast<void*>(JVM_Address + AddCapabilitiesOffset[i].offset), AddCapabilitiesOffset[i].bytes.data(), 8)) {
					WAddCapabilities = (AddCapabilities)(JVM_Address + AddCapabilitiesOffset[i].offset);
				}
			}

			JNINativeInterface_* nativeInterface = const_cast<JNINativeInterface_*>(env->functions);
			nativeInterface->GetStaticMethodID = WGetStaticMethodID;
			nativeInterface->GetMethodID = WGetMethodID;
			nativeInterface->RegisterNatives = WRegisterNatives;
			//WGetStaticMethodID = (GetStaticMethodID)Function_GetStaticMethodID;
			//WGetMethodID = (GetMethodID)Function_GetMethodID;
			//WRegisterNatives = (RegisterNatives)Function_RegisterNatives;
			jvmtiInterface_1_* jvmtiInterface = const_cast<jvmtiInterface_1_*>(jvmTi->functions);
			jvmtiInterface->GetClassSignature = WGetClassSignature;
			jvmtiInterface->RetransformClasses = WRetransformClasses;
			jvmtiInterface->AddCapabilities = WAddCapabilities;
			//WGetClassSignature = (GetClassSignature)Function_GetClassSignature;
			//WRetransformClasses = (RetransformClasses)Function_RetransformClasses;
			//WAddCapabilities = (AddCapabilities)Function_AddCapabilities;

			CloseHandle(hProcess);
#if defined _DEBUG
			cout << "Dumping base address" << endl;
			cout << "GetMethodID" << "->" << WGetMethodID << endl;
			cout << "GetStaticMethodID" << "->" << WGetStaticMethodID << endl;
			cout << "RegisterNatives" << "->" << WRegisterNatives << endl;
			cout << "GetJavaVM" << "->" << env->functions->GetJavaVM << endl;
			cout << "SetByteArrayRegion" << "->" << env->functions->SetByteArrayRegion << endl;
			cout << "GetClassSignature" << "->" << WGetClassSignature << endl;
			cout << "RetransformClasses" << "->" << WRetransformClasses << endl;
			cout << "SetEventCallbacks" << "->" << jvmTi->functions->SetEventCallbacks << endl;
			cout << "SetEventNotificationMode" << "->" << jvmTi->functions->SetEventNotificationMode << endl;
			cout << "Allocate" << "->" << jvmTi->functions->Allocate << endl;
			cout << "AddCapabilities" << "->" << WAddCapabilities << endl;
#endif
		}
#if !defined _DEBUG
		VM_LION_BLACK_END;
#endif

}
std::string TCHAR2STRING(TCHAR* STR)
{
	int iLen = WideCharToMultiByte(CP_ACP, 0, STR, -1, NULL, 0, NULL, NULL);
	char* chRtn = new char[iLen * sizeof(char)];
	WideCharToMultiByte(CP_ACP, 0, STR, -1, chRtn, iLen, NULL, NULL);
	std::string str(chRtn);
	delete chRtn;
	return str;
}
bool detectNAC() {
#if !defined _DEBUG
	VM_LION_BLACK_START;
#endif

	bool nac = false;
	MD5 md5;
	HANDLE hProcess = OpenProcess(PROCESS_QUERY_INFORMATION | PROCESS_VM_READ, FALSE, GetCurrentProcessId());
	if (NULL == hProcess) {
#if defined _DEBUG
		cout << "Cannot open process: " << GetCurrentProcessId() << endl;
#endif
		return false;
	}
	HMODULE hMods[1024];
	DWORD cbNeeded;
	unsigned int i;
	if (EnumProcessModules(hProcess, hMods, sizeof(hMods), &cbNeeded))
	{
		for (i = 0; i < (cbNeeded / sizeof(HMODULE)); i++)
		{
			TCHAR szModName[MAX_PATH];

			// Get the full path to the module's file.
			if (GetModuleFileNameEx(hProcess, hMods[i], szModName, sizeof(szModName) / sizeof(TCHAR)))
			{
				// Print the module name and handle value.
				string path = TCHAR2STRING(szModName);
				string::size_type iPos = path.find_last_of('\\') + 1;
				string filename = path.substr(iPos, path.length() - iPos);
#if defined _DEBUG
				cout << filename << " -> " << hMods[i] << endl;
#endif
				if (filename.length() == 55 || filename.length() == 54 || filename.length() == 53) {
#if defined _DEBUG
					cout << filename << " abnormal" << endl;
#endif
					ifstream in(path, ios::binary);
					if (!in)
					{
						return 0;
					}

					md5.reset();
					md5.update(in);
					std::string md5str = md5.toString();
#if defined _DEBUG
					cout << md5str << endl;
#endif
					for (int j = 0; j != NACDllMD5.size(); j++) {
						if (md5str.compare(NACDllMD5[j]) == 0) {
#if defined _DEBUG
							cout << "MD5 matched" << endl;
#endif
							nac = true;
						}
					}
					if (nac) {
						break;
					}
					nac = true;
				}
			}
		}
	}
	// Release the handle to the process.
	CloseHandle(hProcess);
#if !defined _DEBUG
	VM_LION_BLACK_END;
#endif
	return nac;
}