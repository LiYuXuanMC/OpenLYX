package al.logger.client.wrapper.LoggerMC.entity.player;

import al.logger.client.wrapper.LoggerMC.EnumWrapper;
import al.logger.client.wrapper.annotations.WrapEnum;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

@WrapperClass(mcpName = "net.minecraft.entity.player.EnumPlayerModelParts", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.entity.player.EnumPlayerModelParts", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EnumPlayerModelParts extends EnumWrapper {
    @WrapEnum(mcpName = "CAPE", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "CAPE", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum CAPE;
    public EnumPlayerModelParts(Object obj) {
        super(obj);
    }
}
