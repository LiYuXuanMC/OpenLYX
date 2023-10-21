package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.CactusWrapping;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.renderer.texture.AbstractTexture",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.texture.AbstractTexture",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class AbstractTexture extends WrapperBase {
    @WrapMethod(deobfName = "getGlTextureId",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "()I")
    @WrapMethod(deobfName = "getGlTextureId",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @CactusWrapping
    public static Method getGlTextureId;
    public AbstractTexture(Object obj) {
        super(obj);
    }
    public int getGlTextureId()
    {
        return (int) invokeMethod(getGlTextureId);
    }
}
