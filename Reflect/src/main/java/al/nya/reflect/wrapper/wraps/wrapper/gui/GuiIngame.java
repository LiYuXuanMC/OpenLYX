package al.nya.reflect.wrapper.wraps.wrapper.gui;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiIngame",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiIngame",targetMap = Maps.Srg1_12_2)
public class GuiIngame extends GuiScreen {
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiIngame",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiIngame",targetMap = Maps.Srg1_12_2)
    public static Class GuiIngameClass;
    @WrapMethod(mcpName = "renderGameOverlay", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "renderGameOverlay", targetMap = Maps.Srg1_12_2)
    public static Method renderGameOverlay;
    @WrapMethod(mcpName = "renderTooltip", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "renderHotbar", targetMap = Maps.Srg1_12_2)
    public static Method renderTooltip;
    @WrapField(mcpName = "persistantChatGUI", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "persistantChatGUI", targetMap = Maps.Srg1_12_2)
    public static Field persistantChatGUI;
    @WrapMethod(mcpName = "renderScoreboard", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "renderScoreboard", targetMap = Maps.Srg1_12_2)
    public static Method renderScoreboard;

    public GuiIngame(Object obj) {
        super(obj);
    }

    public GuiNewChat getGuiChat() {
        return new GuiNewChat(ReflectUtil.getField(persistantChatGUI, getWrapObject()));
    }
}
