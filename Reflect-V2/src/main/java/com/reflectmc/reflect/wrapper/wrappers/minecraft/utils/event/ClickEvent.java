package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.event;

import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.event.ClickEvent", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.text.event.ClickEvent", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ClickEvent extends WrapperBase {
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = {ClickEventAction.class, String.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = {ClickEventAction.class, String.class})
    public static Constructor<?> ClickEvent_ClickEventAction_String;
    @WrapField(deobfName = "action", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "action", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field action;
    @WrapField(deobfName = "value", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "value", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field value;

    public ClickEvent(Object obj) {
        super(obj);
    }

    public ClickEvent(Enum<?> e, String s) {
        super(construct(ClickEvent_ClickEventAction_String, e, s));
    }

    public Enum getAction() {
        return (Enum) getField(action);
    }
    public String getValue() {
        return (String) getField(value);
    }
}
