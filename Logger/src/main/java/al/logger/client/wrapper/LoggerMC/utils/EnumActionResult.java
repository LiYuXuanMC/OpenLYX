package al.logger.client.wrapper.LoggerMC.utils;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapEnum;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.EnumWrapper;
@WrapperClass(mcpName = "net.minecraft.util.EnumActionResult",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EnumActionResult extends EnumWrapper {
    @WrapEnum(mcpName = "SUCCESS",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum SUCCESS;
    @WrapEnum(mcpName = "PASS",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum PASS;
    @WrapEnum(mcpName = "FAIL",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum FAIL;
    public EnumActionResult(Object obj) {
        super(obj);
    }
}
