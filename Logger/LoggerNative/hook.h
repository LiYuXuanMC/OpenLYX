#include "pch.h"
#include "lwjgl.h"

typedef void(*Java_org_lwjgl_opengl_GL11_nglFlush)(JNIEnv* env, jclass clazz, jlong lVar);

void setupHook();
void uninstallHook();

void nglFlush_Hook(JNIEnv*, jclass, jlong);