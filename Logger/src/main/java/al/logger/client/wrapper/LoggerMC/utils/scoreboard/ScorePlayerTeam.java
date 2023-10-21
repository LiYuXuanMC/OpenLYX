package al.logger.client.wrapper.LoggerMC.utils.scoreboard;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.scoreboard.ScorePlayerTeam", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.scoreboard.ScorePlayerTeam", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ScorePlayerTeam extends Team{
    @WrapMethod(mcpName = "getChatFormat", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getColor", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getChatFormat;
    @WrapMethod(mcpName = "formatPlayerName", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "formatPlayerName", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method formatPlayerName;
    public ScorePlayerTeam(Object obj) {
        super(obj);
    }
    public Enum getChatFormat(){
        return (Enum) invoke(getChatFormat);
    }
    public static String formatPlayerName(Team p_96667_0_, String p_96667_1_)
    {
        return (String) invokeStatic(formatPlayerName, p_96667_0_.getWrappedObject(), p_96667_1_);
    }
}
