package al.logger.client.wrapper.LoggerMC.utils.scoreboard;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
@WrapperClass(mcpName = "net.minecraft.scoreboard.Team", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.scoreboard.Team", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Team extends IWrapper {
    public Team(Object obj) {
        super(obj);
    }
}
