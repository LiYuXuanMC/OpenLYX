package al.logger.client.wrapper.LoggerMC.utils;

import al.logger.client.wrapper.annotations.WrapEnum;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.EnumWrapper;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Method;
import java.util.Map;

@WrapperClass(mcpName = "net.minecraft.util.EnumChatFormatting",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.text.TextFormatting",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EnumChatFormatting extends EnumWrapper {
    @WrapMethod(mcpName = "getTextWithoutFormattingCodes",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getTextWithoutFormattingCodes",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getTextWithoutFormattingCodes;
    @WrapMethod(mcpName = "getColorIndex", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getColorIndex", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getColorIndex;
    @WrapEnum(mcpName = "LIGHT_PURPLE", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enum<?> LIGHT_PURPLE;
    @WrapEnum(mcpName = "GOLD", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enum<?> GOLD;
    @WrapEnum(mcpName = "WHITE", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enum<?> WHITE;
    @WrapEnum(mcpName = "GREEN", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Enum<?> GREEN;
    public EnumChatFormatting(Object obj) {
        super(obj);
    }
    public static String getTextWithoutFormattingCodes(String text){
        return (String) ReflectUtil.invoke(getTextWithoutFormattingCodes,text);
    }
    public static int getColorIndex(Enum enumChatFormatting){
        return (int) ReflectUtil.invoke(getColorIndex,enumChatFormatting);
    }
}
