package com.reflectmc.reflect.wrapper.wrappers.minecraft.gui;

import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.gui.Gui",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.gui.Gui",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Gui extends WrapperBase {
    @WrapMethod(deobfName = "drawModalRectWithCustomSizedTexture",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "drawModalRectWithCustomSizedTexture",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method drawModalRectWithCustomSizedTexture;
    public Gui(Object obj) {
        super(obj);
    }
    public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight)
    {
        invokeStaticMethod(drawModalRectWithCustomSizedTexture,x,y,u,v,width,height,textureWidth,textureHeight);
    }
}
