package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;
import java.nio.IntBuffer;

@WrapperClass(deobfName = "net.minecraft.client.renderer.GLAllocation",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
public class GLAllocation extends WrapperBase {
    @WrapMethod(deobfName = "createDirectIntBuffer", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "createDirectIntBuffer", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method createDirectIntBuffer;

    public GLAllocation(Object wrap) {
        super(wrap);
    }
    public static IntBuffer createDirectIntBuffer(int i) {
        return (IntBuffer) invokeStaticMethod(createDirectIntBuffer,i);
    }
}
