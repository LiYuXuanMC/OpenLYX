package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.scoreboard;

import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

@WrapperClass(deobfName = "net.minecraft.scoreboard.Scoreboard", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.scoreboard.Scoreboard", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Scoreboard extends WrapperBase {
    @WrapMethod(deobfName = "getPlayersTeam", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getPlayersTeam", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getPlayersTeam;
    @WrapMethod(deobfName = "getObjectiveInDisplaySlot", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getObjectiveInDisplaySlot", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getObjectiveInDisplaySlot;
    @WrapMethod(deobfName = "getSortedScores", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getSortedScores", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getSortedScores;
    public Scoreboard(Object obj) {
        super(obj);
    }

    public ScorePlayerTeam getPlayersTeam(String name){
        return new ScorePlayerTeam(invokeMethod(getPlayersTeam,name));
    }
    public ScoreObjective getObjectiveInDisplaySlot(int slot){
        return new ScoreObjective(invokeMethod(getObjectiveInDisplaySlot,slot));
    }
    public Collection<Score> getSortedScores(ScoreObjective objective){
        Collection<Object> objects = (Collection<Object>) invokeMethod(getSortedScores,objective.getWrappedObject());
        Collection<Score> scores = new ArrayList<Score>();
        for (Object object : objects) {
            scores.add(new Score(object));
        }
        return scores;
    }
}
