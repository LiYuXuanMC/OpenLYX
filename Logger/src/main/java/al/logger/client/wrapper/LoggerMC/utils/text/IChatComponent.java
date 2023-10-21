package al.logger.client.wrapper.LoggerMC.utils.text;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.utils.ChatStyle;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.IChatComponent", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.text.ITextComponent", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class IChatComponent extends IWrapper {
@ClassInstance    
public static Class<?> IChatComponentClass;
    @WrapMethod(mcpName = "getUnformattedText", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getUnformattedText", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getUnformattedText;
    @WrapMethod(mcpName = "getFormattedText", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getFormattedText", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getFormattedText;
    @WrapMethod(mcpName = "getChatStyle", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getStyle", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getChatStyle;
    @WrapMethod(mcpName = "appendSibling", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "appendSibling", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method appendSibling;
    @WrapMethod(mcpName = "setChatStyle", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setStyle", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setChatStyle;

    public IChatComponent(Object obj) {
        super(obj);
    }

    public IChatComponent setChatStyle(ChatStyle chatStyle) {
        invoke(setChatStyle, chatStyle.getWrappedObject());
        return this;
    }

    public IChatComponent appendSibling(IChatComponent component) {
        invoke(appendSibling, component.getWrappedObject());
        return this;
    }

    public String getUnformattedText() {
        return (String) invoke(getUnformattedText);
    }

    public String getFormattedText() {
        return (String) invoke(getFormattedText);
    }

    public ChatStyle getChatStyle() {
        return new ChatStyle(invoke(getChatStyle));
    }
}
