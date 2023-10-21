package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text;

import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.event.ClickEvent;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.event.HoverEvent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.util.ChatStyle", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.text.Style", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ChatStyle extends WrapperBase {
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Constructor ChatStyle_V;
    @WrapMethod(deobfName = "getChatHoverEvent", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getHoverEvent", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getChatHoverEvent;
    @WrapMethod(deobfName = "getChatClickEvent", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getClickEvent", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getChatClickEvent;
    @WrapMethod(deobfName = "getInsertion", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getInsertion", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getInsertion;
    @WrapMethod(deobfName = "setChatHoverEvent", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setHoverEvent", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setChatHoverEvent;
    @WrapMethod(deobfName = "setChatClickEvent", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setClickEvent", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setChatClickEvent;

    public ChatStyle(Object obj) {
        super(obj);
    }

    public ChatStyle() {
        super(construct(ChatStyle_V));
    }

    public ChatStyle setHoverEvent(HoverEvent event) {
        invokeMethod(setChatHoverEvent, event.getWrappedObject());
        return this;
    }
    public ChatStyle setClickEvent(ClickEvent event) {
        invokeMethod(setChatClickEvent, event.getWrappedObject());
        return this;
    }
    public HoverEvent getChatHoverEvent() {
        return new HoverEvent(invokeMethod(getChatHoverEvent));
    }
    public ClickEvent getChatClickEvent() {
        return new ClickEvent(invokeMethod(getChatClickEvent));
    }
    public String getInsertion() {
        return (String) invokeMethod(getInsertion);
    }

}
