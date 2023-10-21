package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.event;

import com.reflectmc.reflect.wrapper.annotation.WrapEnum;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.EnumWrapper;

@WrapperClass(deobfName = "net.minecraft.event.ClickEvent$Action",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.text.event.ClickEvent$Action",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ClickEventAction extends EnumWrapper {
    @WrapEnum(deobfName = "OPEN_URL",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "OPEN_URL",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static ClickEventAction OPEN_URL;
    @WrapEnum(deobfName = "OPEN_FILE",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "OPEN_FILE",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static ClickEventAction OPEN_FILE;
    @WrapEnum(deobfName = "RUN_COMMAND",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "RUN_COMMAND",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static ClickEventAction RUN_COMMAND;
    @WrapEnum(deobfName = "TWITCH_USER_INFO",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static ClickEventAction TWITCH_USER_INFO;
    @WrapEnum(deobfName = "SUGGEST_COMMAND",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "SUGGEST_COMMAND",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static ClickEventAction SUGGEST_COMMAND;
    @WrapEnum(deobfName = "CHANGE_PAGE",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "CHANGE_PAGE",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static ClickEventAction CHANGE_PAGE;
    public ClickEventAction(Object obj) {
        super(obj);
    }
}
