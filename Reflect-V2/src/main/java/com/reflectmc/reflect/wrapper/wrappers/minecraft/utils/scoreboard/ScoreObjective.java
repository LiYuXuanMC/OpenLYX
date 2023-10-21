package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.scoreboard;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.scoreboard.ScoreObjective", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.scoreboard.ScoreObjective", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ScoreObjective extends WrapperBase {
    @ClassInstance
    public static Class<?> ScoreObjectiveClass;
    @WrapField(deobfName = "displayName", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "displayName", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field displayName;

    public ScoreObjective(Object obj) {
        super(obj);
    }

    public String getDisplayName() {
        return (String) getField(displayName);
    }
}
