package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.event;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapEnum;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.EnumWrapper;

@WrapperClass(deobfName = "net.minecraft.event.HoverEvent$Action", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.text.event.HoverEvent$Action", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class HoverEventAction extends EnumWrapper {
    @ClassInstance
    public static Class<?> HoverEventActionClass;
    @WrapEnum(deobfName = "SHOW_TEXT", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "SHOW_TEXT", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static HoverEventAction SHOW_TEXT;
    @WrapEnum(deobfName = "SHOW_ACHIEVEMENT", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "SHOW_ACHIEVEMENT", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static HoverEventAction SHOW_ACHIEVEMENT;
    @WrapEnum(deobfName = "SHOW_ITEM", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "SHOW_ITEM", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static HoverEventAction SHOW_ITEM;
    @WrapEnum(deobfName = "SHOW_ENTITY", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "SHOW_ENTITY", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static HoverEventAction SHOW_ENTITY;

    public HoverEventAction(Object obj) {
        super(obj);
    }
}
