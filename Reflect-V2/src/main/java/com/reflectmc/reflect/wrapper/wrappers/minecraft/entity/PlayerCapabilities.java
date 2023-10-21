package com.reflectmc.reflect.wrapper.wrappers.minecraft.entity;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.entity.player.PlayerCapabilities", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.entity.player.PlayerCapabilities", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class PlayerCapabilities extends WrapperBase {
    @WrapField(deobfName = "isCreativeMode", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "isCreativeMode", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field isCreativeMode;
    @WrapField(deobfName = "isFlying", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "isFlying", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field isFlying;
    @WrapField(deobfName = "disableDamage", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "disableDamage", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field disableDamage;
    @WrapMethod(deobfName = "getWalkSpeed", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getWalkSpeed", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getWalkSpeed;

    public PlayerCapabilities(Object obj) {
        super(obj);
    }

    public boolean isCreativeMode() {
        return (boolean) getField(isCreativeMode);
    }
    public boolean isFlying() {
        return (boolean) getField(isFlying);
    }
    public boolean isDisableDamage() {
        return (boolean) getField(disableDamage);
    }
    public float getWalkSpeed() {
        return (float) invokeMethod(getWalkSpeed);
    }
}
