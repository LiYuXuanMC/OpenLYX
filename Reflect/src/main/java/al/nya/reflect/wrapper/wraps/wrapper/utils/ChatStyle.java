package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.HoverEvent;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.click.ClickEvent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.ChatStyle", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.text.Style", targetMap = Maps.Srg1_12_2)
public class ChatStyle extends IWrapper {
    @WrapConstructor(targetMap = Maps.Srg1_8_9)
    @WrapConstructor(targetMap = Maps.Srg1_12_2)
    public static Constructor ChatStyle_V;
    @WrapMethod(mcpName = "getChatHoverEvent", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getHoverEvent", targetMap = Maps.Srg1_12_2)
    public static Method getChatHoverEvent;
    @WrapMethod(mcpName = "getChatClickEvent", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getClickEvent", targetMap = Maps.Srg1_12_2)
    public static Method getChatClickEvent;
    @WrapMethod(mcpName = "getInsertion", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getInsertion", targetMap = Maps.Srg1_12_2)
    public static Method getInsertion;
    @WrapMethod(mcpName = "setChatHoverEvent", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setHoverEvent", targetMap = Maps.Srg1_12_2)
    public static Method setChatHoverEvent;
    @WrapMethod(mcpName = "setChatClickEvent", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setClickEvent", targetMap = Maps.Srg1_12_2)
    public static Method setChatClickEvent;

    public ChatStyle(Object obj) {
        super(obj);
    }

    public ChatStyle() {
        super(ReflectUtil.construction(ChatStyle_V));
    }

    public ChatStyle setHoverEvent(HoverEvent event) {
        invoke(setChatHoverEvent, event.getWrapObject());
        return this;
    }

    public ChatStyle setClickEvent(ClickEvent event) {
        invoke(setChatClickEvent, event.getWrapObject());
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
