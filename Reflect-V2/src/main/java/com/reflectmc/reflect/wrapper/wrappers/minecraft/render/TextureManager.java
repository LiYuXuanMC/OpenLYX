package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.ResourceLocation;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.renderer.texture.TextureManager",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.texture.TextureManager",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class TextureManager extends WrapperBase {
    @WrapMethod(deobfName = "bindTexture",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "bindTexture",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method bindTexture;
    @WrapMethod(deobfName = "loadTexture",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapMethod(deobfName = "loadTexture",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method loadTexture;
    @WrapMethod(deobfName = "getTexture",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getTexture",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getTexture;

    public TextureManager(Object obj) {
        super(obj);
    }
    public void bindTexture(ResourceLocation resource){
        invokeMethod(bindTexture,resource.getWrappedObject());
    }
    public void loadTexture(ResourceLocation resourceLocation, DynamicTexture dynamicTexture) {
        invokeMethod(loadTexture,resourceLocation.getWrappedObject(),dynamicTexture.getWrappedObject());
    }
}
