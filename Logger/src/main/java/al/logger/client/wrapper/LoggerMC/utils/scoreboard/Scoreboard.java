package al.logger.client.wrapper.LoggerMC.utils.scoreboard;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import com.google.common.collect.Lists;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.*;

@WrapperClass(mcpName = "net.minecraft.scoreboard.Scoreboard", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.scoreboard.Scoreboard", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Scoreboard extends IWrapper {
    @WrapMethod(mcpName = "getPlayersTeam", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getPlayersTeam", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getPlayersTeam;
    @WrapMethod(mcpName = "getObjectiveInDisplaySlot", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getObjectiveInDisplaySlot", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getObjectiveInDisplaySlot;
    @WrapMethod(mcpName = "getSortedScores", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getSortedScores", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getSortedScores;
    @WrapMethod(mcpName = "getScores", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getScores", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getScores;
    public Scoreboard(Object obj) {
        super(obj);
    }

    public ScorePlayerTeam getPlayersTeam(String name){
        return new ScorePlayerTeam(invoke(getPlayersTeam,name));
    }
    public ScoreObjective getObjectiveInDisplaySlot(int slot){
        return new ScoreObjective(invoke(getObjectiveInDisplaySlot,slot));
    }
    public Collection<Score> getSortedScores(ScoreObjective objective){
        Collection<Object> objects = (Collection<Object>) invoke(getSortedScores,objective.getWrappedObject());
        Collection<Score> scores = new ArrayList<Score>();
        for (Object object : objects) {
            scores.add(new Score(object));
        }
        return scores;
    }
    public Collection<Score> getScores() {
        Collection<Object> objects = (Collection<Object>) invoke(getScores);
        Collection<Score> scores = new ArrayList<Score>();
        for (Object object : objects) {
            scores.add(new Score(object));
        }
        return scores;
    }
}
