package com.reflectmc.reflect.wrapper.wrappers.minecraft;

import com.reflectmc.reflect.wrapper.annotation.CactusWrapping;
import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.profiler.Profiler",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.profiler.Profiler",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Profiler extends WrapperBase {
    @ClassInstance
    public static Class ProfilerClass;
    @WrapMethod(deobfName = "endSection", targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "()V")
    @WrapMethod(deobfName = "endSection", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @CactusWrapping
    public static Method endSection;
    @WrapMethod(deobfName = "startSection", targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Ljava/lang/String;)V")
    @WrapMethod(deobfName = "startSection", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @CactusWrapping
    public static Method startSection;
    public void startSection(String s){
        invokeMethod(startSection,s);
    }
    public void endSection(){
        invokeMethod(endSection);
    }
    public Profiler(Object obj) {
        super(obj);
    }
}
