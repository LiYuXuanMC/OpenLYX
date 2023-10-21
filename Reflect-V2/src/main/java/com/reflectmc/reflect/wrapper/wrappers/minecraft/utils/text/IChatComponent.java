package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.util.IChatComponent", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.text.ITextComponent", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class IChatComponent extends WrapperBase {
    @ClassInstance
    public static Class<?> IChatComponentClass;
    @WrapMethod(deobfName = "getUnformattedText", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getUnformattedText", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getUnformattedText;
    @WrapMethod(deobfName = "getFormattedText", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getFormattedText", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getFormattedText;
    @WrapMethod(deobfName = "getChatStyle", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getStyle", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getChatStyle;
    @WrapMethod(deobfName = "appendSibling", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "appendSibling", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method appendSibling;
    @WrapMethod(deobfName = "setChatStyle", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setStyle", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setChatStyle;

    public IChatComponent(Object obj) {
        super(obj);
    }

    public IChatComponent setChatStyle(ChatStyle chatStyle) {
        invokeMethod(setChatStyle, chatStyle.getWrappedObject());
        return this;
    }
    public IChatComponent appendSibling(IChatComponent component) {
        invokeMethod(appendSibling, component.getWrappedObject());
        return this;
    }
    public String getUnformattedText() {
        return (String) invokeMethod(getUnformattedText);
    }
    public String getFormattedText() {
        return (String) invokeMethod(getFormattedText);
    }
    public ChatStyle getChatStyle() {
        return new ChatStyle(invokeMethod(getChatStyle));
    }
}
