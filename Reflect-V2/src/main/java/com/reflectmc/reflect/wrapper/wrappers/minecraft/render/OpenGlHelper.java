package com.reflectmc.reflect.wrapper.wrappers.minecraft.render;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


@WrapperClass(deobfName = "net.minecraft.client.renderer.OpenGlHelper",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.renderer.OpenGlHelper",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class OpenGlHelper extends WrapperBase {
    @WrapMethod(deobfName = "glBlendFunc", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "glBlendFunc", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method glBlendFunc;
    @WrapField(deobfName = "lightmapTexUnit", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapField(deobfName = "lightmapTexUnit", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field lightmapTexUnit;
    @WrapField(deobfName = "defaultTexUnit", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapField(deobfName = "defaultTexUnit", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field defaultTexUnit;
    @WrapField(deobfName = "shadersSupported", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @WrapField(deobfName = "shadersSupported", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field shadersSupported;

    public OpenGlHelper(Object obj) {
        super(obj);
    }

    public static boolean getShadersSupported() {
        return (boolean) getStaticField(shadersSupported);
    }
    public static int defaultTexUnit() {
        return (int) getStaticField(defaultTexUnit);
    }
    public static int lightmapTexUnit() {
        return (int) getStaticField(lightmapTexUnit);
    }
    public static void glBlendFunc(int i1, int i2, int i3, int i4) {
        invokeStaticMethod(glBlendFunc, i1, i2, i3, i4);
    }
}
