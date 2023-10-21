package com.reflectmc.reflect.wrapper.wrappers.minecraft.gui;

import com.reflectmc.reflect.wrapper.annotation.*;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.gui.GuiIngame",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.gui.GuiIngame",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class GuiIngame extends GuiScreen {
    @ClassInstance
    public static Class GuiIngameClass;
    @WrapMethod(deobfName = "renderGameOverlay", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "renderGameOverlay", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method renderGameOverlay;
    @WrapMethod(deobfName = "renderTooltip", targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Lnet/minecraft/client/gui/ScaledResolution;F)V")
    @WrapMethod(deobfName = "renderHotbar", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    @CactusWrapping
    public static Method renderTooltip;
    @WrapField(deobfName = "persistantChatGUI", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "persistantChatGUI", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field persistantChatGUI;
    @WrapMethod(deobfName = "renderScoreboard", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "renderScoreboard", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method renderScoreboard;

    public GuiIngame(Object obj) {
        super(obj);
    }

    public GuiNewChat getGuiChat() {
        return new GuiNewChat(getField(persistantChatGUI));
    }
}
