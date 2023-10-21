package al.nya.reflect.wrapper.wraps.wrapper.utils.scoreboard;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.scoreboard.ScorePlayerTeam", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.scoreboard.ScorePlayerTeam", targetMap = Maps.Srg1_12_2)
public class ScorePlayerTeam extends Team{
    @WrapMethod(mcpName = "getChatFormat", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getColor", targetMap = Maps.Srg1_12_2)
    public static Method getChatFormat;
    @WrapMethod(mcpName = "formatPlayerName", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "formatPlayerName", targetMap = Maps.Srg1_12_2)
    public static Method formatPlayerName;
    public ScorePlayerTeam(Object obj) {
        super(obj);
    }
    public Enum getChatFormat(){
        return (Enum) invoke(getChatFormat);
    }
    public static String formatPlayerName(Team p_96667_0_, String p_96667_1_)
    {
        return (String) ReflectUtil.invoke(formatPlayerName, null, p_96667_0_.getWrapObject(), p_96667_1_);
    }
}
