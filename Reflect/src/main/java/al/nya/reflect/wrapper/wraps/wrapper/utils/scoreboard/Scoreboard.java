package al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

@WrapperClass(mcpName = "net.minecraft.scoreboard.Scoreboard", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.scoreboard.Scoreboard", targetMap = Maps.Srg1_12_2)
public class Scoreboard extends IWrapper {
    @WrapMethod(mcpName = "getPlayersTeam", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getPlayersTeam", targetMap = Maps.Srg1_12_2)
    public static Method getPlayersTeam;
    @WrapMethod(mcpName = "getObjectiveInDisplaySlot", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getObjectiveInDisplaySlot", targetMap = Maps.Srg1_12_2)
    public static Method getObjectiveInDisplaySlot;
    @WrapMethod(mcpName = "getSortedScores", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getSortedScores", targetMap = Maps.Srg1_12_2)
    public static Method getSortedScores;
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
        Collection<Object> objects = (Collection<Object>) invoke(getSortedScores,objective.getWrapObject());
        Collection<Score> scores = new ArrayList<Score>();
        for (Object object : objects) {
            scores.add(new Score(object));
        }
        return scores;
    }
}
