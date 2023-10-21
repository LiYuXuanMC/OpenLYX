package al.logger.client.wrapper.LoggerMC.utils;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapEnum;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.EnumWrapper;
@WrapperClass(mcpName = "net.minecraft.util.EnumHand",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EnumHand extends EnumWrapper {
    @WrapEnum(mcpName = "MAIN_HAND",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum MAIN_HAND;
    @WrapEnum(mcpName = "MAIN_HAND",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum OFF_HAND;
    public EnumHand(Object obj) {
        super(obj);
    }
}
