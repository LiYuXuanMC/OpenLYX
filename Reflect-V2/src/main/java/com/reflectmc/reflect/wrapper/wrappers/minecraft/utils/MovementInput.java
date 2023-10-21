package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.util.MovementInput",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.MovementInput",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class MovementInput extends WrapperBase {
    @WrapField(deobfName = "moveStrafe",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "moveStrafe",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field moveStrafeWrapper;
    @WrapField(deobfName = "moveForward",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "moveForward",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field moveForwardWrapper;
    @WrapField(deobfName = "jump",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "jump",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field jumpWrapper;
    @WrapField(deobfName = "sneak",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "sneak",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field sneakWrapper;

    public MovementInput(Object obj) {
        super(obj);
    }

    public float getMoveForward() {
        return (float) getField(moveForwardWrapper);
    }
    public float getMoveStrafe() {
        return (float) getField(moveStrafeWrapper);
    }
    public boolean isJump() {
        return (boolean) getField(jumpWrapper);
    }
    public boolean isSneak() {
        return (boolean) getField(sneakWrapper);
    }
    public void setMoveForward(float moveForward) {
        setField(moveForwardWrapper,moveForward);
    }
    public void setJump(boolean jump) {
        setField(jumpWrapper,jump);
    }
    public void setMoveStrafe(float moveStrafe) {
        setField(moveStrafeWrapper,moveStrafe);
    }
    public void setSneak(boolean sneak) {
        setField(sneakWrapper,sneak);
    }
}
