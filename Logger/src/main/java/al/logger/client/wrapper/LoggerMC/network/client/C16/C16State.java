package al.logger.client.wrapper.LoggerMC.network.client.C16;

import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapEnum;
import al.logger.client.wrapper.LoggerMC.EnumWrapper;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C16PacketClientStatus$EnumState",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class C16State extends EnumWrapper {
    @WrapEnum(mcpName = "PERFORM_RESPAWN",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "PERFORM_RESPAWN",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum PERFORM_RESPAWN;
    @WrapEnum(mcpName = "REQUEST_STATS",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "REQUEST_STATS",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum REQUEST_STATS;
    @WrapEnum(mcpName = "OPEN_INVENTORY_ACHIEVEMENT",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "OPEN_INVENTORY_ACHIEVEMENT",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum OPEN_INVENTORY_ACHIEVEMENT;
    public C16State(Object obj) {
        super(obj);
    }
}
