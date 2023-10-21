package al.nya.reflect.wrapper.wraps.wrapper.utils.text;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.ChatStyle;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.IChatComponent", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.text.ITextComponent", targetMap = Maps.Srg1_12_2)
public class IChatComponent extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.util.IChatComponent", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.util.text.ITextComponent", targetMap = Maps.Srg1_12_2)
    public static Class<?> IChatComponentClass;
    @WrapMethod(mcpName = "getUnformattedText", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getUnformattedText", targetMap = Maps.Srg1_12_2)
    public static Method getUnformattedText;
    @WrapMethod(mcpName = "getFormattedText", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getFormattedText", targetMap = Maps.Srg1_12_2)
    public static Method getFormattedText;
    @WrapMethod(mcpName = "getChatStyle", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getStyle", targetMap = Maps.Srg1_12_2)
    public static Method getChatStyle;
    @WrapMethod(mcpName = "appendSibling", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "appendSibling", targetMap = Maps.Srg1_12_2)
    public static Method appendSibling;
    @WrapMethod(mcpName = "setChatStyle", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setStyle", targetMap = Maps.Srg1_12_2)
    public static Method setChatStyle;

    public IChatComponent(Object obj) {
        super(obj);
    }

    public IChatComponent setChatStyle(ChatStyle chatStyle) {
        invoke(setChatStyle, chatStyle.getWrapObject());
        return this;
    }

    public IChatComponent appendSibling(IChatComponent component) {
        invoke(appendSibling, component.getWrapObject());
        return this;
    }

    public String getUnformattedText() {
        return (String) ReflectUtil.invoke(getUnformattedText, getWrapObject());
    }

    public String getFormattedText() {
        return (String) ReflectUtil.invoke(getFormattedText, getWrapObject());
    }

    public ChatStyle getChatStyle() {
        return new ChatStyle(invoke(getChatStyle));
    }
}
