package al.logger.client.wrapper.LoggerMC.world;

import al.logger.client.wrapper.LoggerMC.EnumWrapper;
import al.logger.client.wrapper.annotations.WrapEnum;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;
import net.minecraft.world.WorldSettings;

@WrapperClass(mcpName = "net.minecraft.world.WorldSettings$GameType",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class GameType extends EnumWrapper {
    @WrapEnum(mcpName = "NOT_SET",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "NOT_SET",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum NOT_SET;
    public GameType(Object obj) {
        super(obj);
    }
}
