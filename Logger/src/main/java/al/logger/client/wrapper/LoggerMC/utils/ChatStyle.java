package al.logger.client.wrapper.LoggerMC.utils;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.utils.event.HoverEvent;
import al.logger.client.wrapper.LoggerMC.utils.event.click.ClickEvent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.ChatStyle", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.text.Style", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ChatStyle extends IWrapper {
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Constructor ChatStyle_V;
    @WrapMethod(mcpName = "getChatHoverEvent", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getHoverEvent", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getChatHoverEvent;
    @WrapMethod(mcpName = "getChatClickEvent", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getClickEvent", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getChatClickEvent;
    @WrapMethod(mcpName = "getInsertion", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getInsertion", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getInsertion;
    @WrapMethod(mcpName = "setChatHoverEvent", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setHoverEvent", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setChatHoverEvent;
    @WrapMethod(mcpName = "setChatClickEvent", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setClickEvent", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setChatClickEvent;

    public ChatStyle(Object obj) {
        super(obj);
    }

    public ChatStyle() {
        super(ReflectUtil.construction(ChatStyle_V));
    }

    public ChatStyle setHoverEvent(HoverEvent event) {
        invoke(setChatHoverEvent, event.getWrappedObject());
        return this;
    }

    public ChatStyle setClickEvent(ClickEvent event) {
        invoke(setChatClickEvent, event.getWrappedObject());
        return this;
    }

    public HoverEvent getChatHoverEvent() {
        return new HoverEvent(invoke(getChatHoverEvent));
    }

    public ClickEvent getChatClickEvent() {
        return new ClickEvent(invoke(getChatClickEvent));
    }

    public String getInsertion() {
        return (String) invoke(getInsertion);
    }

}
