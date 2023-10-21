package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.renderer.RenderHelper", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.RenderHelper", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class RenderHelper extends WrapperBase {
    @ClassInstance
    public static Class<?> RenderHelperClass;
    @WrapMethod(deobfName = "enableStandardItemLighting", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableStandardItemLighting", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableStandardItemLighting;
    @WrapMethod(deobfName = "disableStandardItemLighting", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "disableStandardItemLighting", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method disableStandardItemLighting;
    @WrapMethod(deobfName = "enableGUIStandardItemLighting", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "enableGUIStandardItemLighting", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method enableGUIStandardItemLighting;

    public RenderHelper(Object obj) {
        super(obj);
    }

    public static void enableGUIStandardItemLighting() {
        invokeStaticMethod(enableGUIStandardItemLighting);
    }

    public static void disableStandardItemLighting() {
        invokeStaticMethod(disableStandardItemLighting);
    }

    public static void enableStandardItemLighting() {
        invokeStaticMethod(enableStandardItemLighting);
    }
}
