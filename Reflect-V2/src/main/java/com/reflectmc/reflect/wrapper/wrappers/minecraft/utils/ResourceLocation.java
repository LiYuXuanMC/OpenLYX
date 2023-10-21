package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


@WrapperClass(deobfName = "net.minecraft.util.ResourceLocation",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.ResourceLocation",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ResourceLocation extends WrapperBase {
    @ClassInstance
    public static Class ResourceLocation;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {String.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {String.class})
    public static Constructor ResourceLocation_S;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {String.class,String.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {String.class,String.class})
    public static Constructor ResourceLocation_SS;
    @WrapField(deobfName = "resourcePath",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field resourcePath;
    public ResourceLocation(Object obj) {
        super(obj);
    }
    public ResourceLocation(String resourceName)
    {
        this(construct(ResourceLocation_S,resourceName));
    }

    public ResourceLocation(String resourceDomainIn, String resourcePathIn)
    {
        this(construct(ResourceLocation_SS,resourceDomainIn,resourcePathIn));
    }
    public String getResourcePath(){
        return (String) getField(resourcePath);
    }
}
