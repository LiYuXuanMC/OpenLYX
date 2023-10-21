#pragma once
#include "jni.h"
#include "jni_md.h"
#include "jvmti.h"
#include <iostream>
#include <stdio.h>
//JNI defines
typedef jmethodID(JNICALL* GetStaticMethodID)(JNIEnv* env, jclass clazz, const char* name, const char* sig);
typedef jmethodID(JNICALL* GetMethodID)(JNIEnv* env, jclass clazz, const char* name, const char* sig);
typedef jint(JNICALL* RegisterNatives)(JNIEnv* env, jclass clazz, const JNINativeMethod* methods, jint nMethods);
//JVMTI defines
typedef jvmtiError(JNICALL* GetClassSignature) (jvmtiEnv* env, jclass klass, char** signature_ptr, char** generic_ptr);
typedef jvmtiError(JNICALL* RetransformClasses) (jvmtiEnv* env, jint class_count, const jclass* classes);
typedef jvmtiError(JNICALL* AddCapabilities) (jvmtiEnv* env, const jvmtiCapabilities* capabilities_ptr);

//JNI Methods
GetStaticMethodID	WGetStaticMethodID;
GetMethodID			WGetMethodID;
RegisterNatives		WRegisterNatives;
//JVMTI Methods
GetClassSignature	WGetClassSignature;
RetransformClasses	WRetransformClasses;
AddCapabilities		WAddCapabilities;

void InitializeJVMWrapper(JNIEnv* env) {
#ifdef _DEBUG
	std::cout << "Initializing JVM Wrappers" << std::endl;
#endif // DEBUG

	jvmtiEnv* jvmTi = NULL;
	JavaVM* jvm = NULL;

	env->GetJavaVM(&jvm);
	jvm->GetEnv((void**)&jvmTi, JVMTI_VERSION_1_2);
	//JNI
	WGetStaticMethodID	=	env->functions->GetStaticMethodID;
	WGetMethodID = env->functions->GetMethodID;
	WRegisterNatives = env->functions->RegisterNatives;
	//JVMTI
	WGetClassSignature = jvmTi->functions->GetClassSignature;
	WRetransformClasses = jvmTi->functions->RetransformClasses;
	WAddCapabilities = jvmTi->functions->AddCapabilities;
}