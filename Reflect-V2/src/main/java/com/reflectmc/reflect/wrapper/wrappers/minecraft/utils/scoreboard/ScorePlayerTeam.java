package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.scoreboard;

import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.scoreboard.ScorePlayerTeam", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.scoreboard.ScorePlayerTeam", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ScorePlayerTeam extends Team{
    @WrapMethod(deobfName = "getChatFormat", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getColor", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getChatFormat;
    @WrapMethod(deobfName = "formatPlayerName", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "formatPlayerName", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method formatPlayerName;
    public ScorePlayerTeam(Object obj) {
        super(obj);
    }
    public Enum getChatFormat(){
        return (Enum) invokeMethod(getChatFormat);
    }
    public static String formatPlayerName(Team p_96667_0_, String p_96667_1_)
    {
        return (String) invokeStaticMethod(formatPlayerName,  p_96667_0_.getWrappedObject(), p_96667_1_);
    }
}
