// dllmain.cpp : 定义 DLL 应用程序的入口点。
#include "pch.h"
#include <stdio.h>
#include <iostream>
#include "jni.h"
#include "jvmti.h"

using namespace std;

void registerNative(JNIEnv* env, jclass caller, jclass target);
jclass findClass(JNIEnv* env, jvmtiEnv* jvmti, const char* name);
jobject getClassLoader(JNIEnv* env);
jclass defineClassWithReflect(JNIEnv* env, jclass caller, jobject classLoader, jstring name, jbyteArray bytes);
jint transform(JNIEnv* env, jclass, jclass target);
void JNICALL ClassFileLoadHook
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
unsigned char* asUnsignedCharArray(JNIEnv* env, jbyteArray array);
jbyteArray asByteArray(JNIEnv* env, const unsigned char* buf, int len);

static jrawMonitorID lock;

BOOL APIENTRY DllMain( HMODULE hModule,
                       DWORD  ul_reason_for_call,
                       LPVOID lpReserved
                     )
{
    switch (ul_reason_for_call)
    {
    case DLL_PROCESS_ATTACH:
    {
        cout << "Attached to process" << endl;
    }
    case DLL_THREAD_ATTACH:
    case DLL_THREAD_DETACH:
    case DLL_PROCESS_DETACH:
        break;
    }
    return TRUE;
}


JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved) {

    cout << "Attached to jvm " << endl;
    JNIEnv* jnienv = NULL;
    jvmtiEnv* jvmti = NULL;
    jint result = vm->GetEnv((void**)(&jnienv), JNI_VERSION_1_8);
    if (result != JNI_OK) {
        cout << "cant get jni context in this thread" << endl;
        cout << result << endl;
        return JNI_VERSION_1_8;
    }
    result = vm->GetEnv((void**)(&jvmti), JVMTI_VERSION_1_2);
    cout << "registerNative" << endl;
    jclass nativeClass = jnienv->FindClass("al/logger/client/Logger");
    JNINativeMethod natives[] = {
        {const_cast <char*>("registerNative"),const_cast <char*>("(Ljava/lang/Class;)V"),reinterpret_cast<void*>(registerNative)},
    };
    if (nativeClass == NULL) {
        cout << "Class == NULL" << endl;
    }
    if (jnienv->RegisterNatives(nativeClass, natives, sizeof(natives) / sizeof(JNINativeMethod)) < 0) {
        cout << "fail to register native" << endl;
    }
    cout << "registerNative succ" << endl;
    jvmti->CreateRawMonitor("agent lock", &lock);
    return JNI_VERSION_1_8;
}

void registerNative(JNIEnv* env, jclass caller,jclass target) {
    jvmtiEnv* jvmTi = NULL;
    JavaVM* jvm = NULL;
    env->GetJavaVM(&jvm);
    jvm->GetEnv((void**)&jvmTi, JVMTI_VERSION_1_2);
    char* signature;
    jvmTi->GetClassSignature(target, &signature, NULL);
    cout << "register native to " << signature << endl;
    if (!strcmp(signature, "Lal/logger/client/agent/NativeAgent;")) {
        cout << "register native to " << signature << endl;
        //private static native Class<?> defineClass0(ClassLoader classLoader, String name, byte[] classBytes);
        JNINativeMethod natives[] = {
            {const_cast <char*>("defineClass0"),const_cast <char*>("(Ljava/lang/ClassLoader;Ljava/lang/String;[B)Ljava/lang/Class;"),reinterpret_cast<void*>(defineClassWithReflect)},
            {const_cast<char*>("retransform0"),const_cast<char*>("(Ljava/lang/Class;)I"),reinterpret_cast<void*>(transform)},
        };
        if (env->RegisterNatives(target, natives, sizeof(natives) / sizeof(JNINativeMethod)) < 0) {
            cout << "fail to register native" << endl;
        }
    }
    cout << "register succ" << endl;
}

jobject getClassLoader(JNIEnv* env) {
    jvmtiEnv* jvmTi = NULL;
    JavaVM* jvm = NULL;
    env->GetJavaVM(&jvm);
    jvm->GetEnv((void**)&jvmTi, JVMTI_VERSION_1_2);

    //Find target classes
    jclass* loadedClasses;
    jint loadedClassesCount = 0;
    jvmTi->GetLoadedClasses(&loadedClassesCount, &loadedClasses);

    jclass java_lang_Thread = NULL;
    jclass java_lang_ClassLoader = NULL;

    for (jint i = 0; i < loadedClassesCount; i++)
    {
        char* signature;
        jvmTi->GetClassSignature(loadedClasses[i], &signature, NULL);

        if (!strcmp(signature, "Ljava/lang/Thread;")) {
            java_lang_Thread = loadedClasses[i];
        }
        if (!strcmp(signature, "Ljava/lang/ClassLoader;")) {
            java_lang_ClassLoader = loadedClasses[i];
        }
    }

    if (!java_lang_Thread) {
        cout << "cannot find java/lang/Thread" << endl;
        return NULL;
    }
    if (!java_lang_ClassLoader) {
        cout << "cannot find java/lang/ClassLoader" << endl;
        return NULL;
    }

    //Thread.currentThread().getContextClassLoader()
    jmethodID currentThread = env->GetStaticMethodID(java_lang_Thread, "currentThread", "()Ljava/lang/Thread;");
    jobject thread = env->CallStaticObjectMethod(java_lang_Thread, currentThread);

    jmethodID getContextClassLoader = env->GetMethodID(java_lang_Thread, "getContextClassLoader", "()Ljava/lang/ClassLoader;");
    jobject classLoader = env->CallObjectMethod(java_lang_Thread, getContextClassLoader, thread);

    return classLoader;
}

jclass findClass(JNIEnv* env, jvmtiEnv* jvmti, const char* name) {
    jclass* loadedClasses;
    jint loadedClassesCount = 0;
    jvmti->GetLoadedClasses(&loadedClassesCount, &loadedClasses);

    jclass findClass = NULL;
    for (jint i = 0; i < loadedClassesCount; i++)
    {
        char* signature;
        jvmti->GetClassSignature(loadedClasses[i], &signature, NULL);
        if (!strcmp(signature, name))
        {
            findClass = loadedClasses[i];
            return findClass;
        }
    }
    return NULL;
}
jclass defineClassWithReflect(JNIEnv* env,jclass caller, jobject classLoader,jstring name, jbyteArray bytes) {
    jclass clClass = env->FindClass("java/lang/ClassLoader");
    jmethodID defineClass = env->GetMethodID(clClass, "defineClass", "([BII)Ljava/lang/Class;");
    jobject classDefined = env->CallObjectMethod(classLoader, defineClass, bytes, 0, env->GetArrayLength(bytes));
    return (jclass)classDefined;
}
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
    jvmtiError error = jvmTi->AddCapabilities(&capabilities);
    jvmtiEventCallbacks callbacks;
    (void)memset(&callbacks, 0, sizeof(callbacks));
    callbacks.ClassFileLoadHook = ClassFileLoadHook;
    jvmTi->SetEventCallbacks(&callbacks, sizeof(callbacks));
    jvmTi->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, NULL);
    jclass* jvmClasses = new jclass[1];
    jvmClasses[0] = target;
    jvmtiError error2 = jvmTi->RetransformClasses(1, jvmClasses);
    jvmTi->SetEventNotificationMode(JVMTI_DISABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, NULL);
    return error2;
}
void JNICALL ClassFileLoadHook
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
    if (!class_being_redefined || !data || !data_len) {
        return;
    }
    jvmti->Allocate(data_len, new_data);
    jclass transformerClass = findClass(env, jvmti, "Lal/logger/client/transform/TransformManager;");
    jmethodID transfromMethod = env->GetStaticMethodID(transformerClass, "onTransform", "(Ljava/lang/Class;[B)[B");
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
        return;
    }
    unsigned char* newChars = asUnsignedCharArray(env, transformedData);
    const jint newLength = (jint)env->GetArrayLength(transformedData);
    jvmti->Allocate(newLength, new_data);
    *new_data_len = newLength;
    memcpy(*new_data, newChars, newLength);
};
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