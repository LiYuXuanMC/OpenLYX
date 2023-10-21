package al.logger.client.wrapper.LoggerMC.utils.scoreboard;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.scoreboard.Score", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.scoreboard.Score", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Score extends IWrapper {
    @WrapField(mcpName = "scorePlayerName", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "scorePlayerName", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field scorePlayerName;
    @WrapField(mcpName = "scorePoints", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "scorePoints", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field scorePoints;
    public Score(Object obj) {
        super(obj);
    }
    public String getPlayerName(){
        return (String) getField(scorePlayerName);
    }
    public int getPoints(){
        return (int) getField(scorePoints);
    }
}
