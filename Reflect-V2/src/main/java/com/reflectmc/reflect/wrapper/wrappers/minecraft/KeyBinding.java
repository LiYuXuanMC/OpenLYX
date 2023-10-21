package com.reflectmc.reflect.wrapper.wrappers.minecraft;

import com.reflectmc.reflect.wrapper.annotation.CactusWrapping;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.settings.KeyBinding",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.settings.KeyBinding",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class KeyBinding extends WrapperBase {
    @WrapField(deobfName = "pressed", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "pressed", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field pressed;
    @WrapMethod(deobfName = "onTick", targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(I)V")
    @WrapMethod(deobfName = "onTick", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @CactusWrapping
    public static Method onTick;
    @WrapField(deobfName = "keyCode", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "keyCode", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field keyCode;
    @WrapField(deobfName = "pressTime", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "pressTime", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field pressTime;

    public KeyBinding(Object obj) {
        super(obj);
    }

    public int getPressTime() {
        return (int) getField(pressTime);
    }

    public void setPressTime(int i) {
        setField(pressTime, i);
    }

    public boolean isKeyDown() {
        return (boolean) getField(pressed);
    }

    public void setPressed(boolean v) {
        setField(pressed, v);
    }

    public boolean isPressed() {
        return (boolean) getField(pressed);
    }

    public static void onTick(int keyCode) {
        invokeStaticMethod(onTick,keyCode);
    }

    public int getKeyCode() {
        return (int) getField(keyCode);
    }
}
