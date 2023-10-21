package al.logger.client.wrapper.LoggerMC.utils.event.click;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapEnum;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.EnumWrapper;
@WrapperClass(mcpName = "net.minecraft.event.ClickEvent$Action",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.text.event.ClickEvent$Action",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ClickEventAction extends EnumWrapper {
    @WrapEnum(mcpName = "OPEN_URL",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "OPEN_URL",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum OPEN_URL;
    @WrapEnum(mcpName = "OPEN_FILE",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "OPEN_FILE",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum OPEN_FILE;
    @WrapEnum(mcpName = "RUN_COMMAND",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "RUN_COMMAND",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum RUN_COMMAND;
    @WrapEnum(mcpName = "TWITCH_USER_INFO",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enum TWITCH_USER_INFO;
    @WrapEnum(mcpName = "SUGGEST_COMMAND",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "SUGGEST_COMMAND",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum SUGGEST_COMMAND;
    @WrapEnum(mcpName = "CHANGE_PAGE",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapEnum(mcpName = "CHANGE_PAGE",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Enum CHANGE_PAGE;
    public ClickEventAction(Object obj) {
        super(obj);
    }
}
