package com.reflectmc.reflect.wrapper.wrappers.minecraft.gui;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.client.gui.ScaledResolution",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.gui.ScaledResolution",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ScaledResolution extends WrapperBase {
    @ClassInstance
    public static Class<?> ScaledResolutionClass;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {Minecraft.class})
    public static Constructor<?> Constructor_ScaledResolution;
    @WrapField(deobfName = "scaledWidthD", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "scaledWidthD", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field scaledWidthD;
    @WrapField(deobfName = "scaledHeightD",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "scaledHeightD",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field scaledHeightD;
    @WrapField(deobfName = "scaledWidth",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "scaledWidth",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field scaledWidth;
    @WrapField(deobfName = "scaledHeight",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "scaledHeight",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field scaledHeight;
    @WrapField(deobfName = "scaleFactor",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "scaleFactor",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field scaleFactor;

    public ScaledResolution(Object obj) {
        super(obj);
    }
    public ScaledResolution(Minecraft mc){
        super(construct(Constructor_ScaledResolution,mc.getWrappedObject()));
    }
    public int getScaledWidth()
    {
        return (int) getField(scaledWidth);
    }

    public int getScaledHeight()
    {
        return (int) getField(scaledHeight);
    }

    public double getScaledWidth_double()
    {
        return (double) getField(scaledWidthD);
    }

    public double getScaledHeight_double()
    {
        return (double) getField(scaledHeightD);
    }

    public int getScaleFactor()
    {
        return (int) getField(scaleFactor);
    }
}
