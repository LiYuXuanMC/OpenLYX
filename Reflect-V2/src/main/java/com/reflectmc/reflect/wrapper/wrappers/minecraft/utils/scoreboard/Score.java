package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.scoreboard;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.scoreboard.Score", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.scoreboard.Score", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Score extends WrapperBase {
    @WrapField(deobfName = "scorePlayerName", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "scorePlayerName", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field scorePlayerName;
    public Score(Object obj) {
        super(obj);
    }
    public String getPlayerName(){
        return (String) getField(scorePlayerName);
    }
}
