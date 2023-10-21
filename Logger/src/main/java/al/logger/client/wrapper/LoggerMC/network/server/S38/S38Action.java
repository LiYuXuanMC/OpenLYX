package al.logger.client.wrapper.LoggerMC.network.server.S38;

import al.logger.client.wrapper.LoggerMC.EnumWrapper;
import al.logger.client.wrapper.annotations.WrapEnum;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

@WrapperClass(mcpName = "net.minecraft.network.play.server.S38PacketPlayerListItem$Action",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class S38Action extends EnumWrapper {
    @WrapEnum(mcpName = "ADD_PLAYER",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "ADD_PLAYER",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum ADD_PLAYER;

    public S38Action(Object obj) {
        super(obj);
    }
}
