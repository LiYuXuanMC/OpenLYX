package al.nya.reflect.wrapper.wraps.wrapper.gui;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiScreen",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiScreen",targetMap = Maps.Srg1_12_2)
public class GuiScreen extends Gui {
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiScreen",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiScreen",targetMap = Maps.Srg1_12_2)
    public static Class GuiScreenClass;
    @WrapMethod(mcpName = "drawScreen",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "drawScreen",targetMap = Maps.Srg1_12_2)
    public static Method drawScreen;
    @WrapMethod(mcpName = "initGui",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "initGui",targetMap = Maps.Srg1_12_2)
    public static Method initGui;
    @WrapMethod(mcpName = "onGuiClosed",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "onGuiClosed",targetMap = Maps.Srg1_12_2)
    public static Method onGuiClosed;
    @WrapField(mcpName = "mc",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "mc",targetMap = Maps.Srg1_12_2)
    public static Field mc;
    @WrapField(mcpName = "width",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "width",targetMap = Maps.Srg1_12_2)
    public static Field width;
    @WrapField(mcpName = "height", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "height", targetMap = Maps.Srg1_12_2)
    public static Field height;
    @WrapMethod(mcpName = "keyTyped", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "keyTyped", targetMap = Maps.Srg1_12_2)
    public static Method keyTyped;
    @WrapMethod(mcpName = "sendChatMessage", targetMap = Maps.Srg1_8_9, signature = "(Ljava/lang/String;Z)V")
    @WrapMethod(mcpName = "sendChatMessage", targetMap = Maps.Srg1_12_2, signature = "(Ljava/lang/String;Z)V")
    public static Method sendChatMessage_SZ_V;
    @WrapMethod(mcpName = "sendChatMessage", targetMap = Maps.Srg1_8_9, signature = "(Ljava/lang/String;)V")
    @WrapMethod(mcpName = "sendChatMessage", targetMap = Maps.Srg1_12_2, signature = "(Ljava/lang/String;)V")
    public static Method sendChatMessage_S_V;
    @WrapMethod(mcpName = "handleComponentHover", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "handleComponentHover", targetMap = Maps.Srg1_12_2)
    public static Method handleComponentHover;
    @WrapMethod(mcpName = "updateScreen", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "updateScreen", targetMap = Maps.Srg1_12_2)
    public static Method updateScreen;
    @WrapMethod(mcpName = "mouseClicked", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "mouseClicked", targetMap = Maps.Srg1_12_2)
    public static Method mouseClicked;
    @WrapMethod(mcpName = "drawHoveringText", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "drawHoveringText", targetMap = Maps.Srg1_12_2)
    public static Method drawHoveringText;
    @WrapMethod(mcpName = "actionPerformed", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "actionPerformed", targetMap = Maps.Srg1_12_2)
    public static Method actionPerformed;
    @WrapMethod(mcpName = "handleKeyboardInput", targetMap = Maps.Srg1_8_9)
    public static Method handleKeyboardInput;

    public GuiScreen(Object obj) {
        super(obj);
    }

    public void drawHoveringText(List<?> list, int i, int i1) {
        invoke(drawHoveringText, list, i, i1);
    }

    public void setMc(Minecraft mcIn) {
        ReflectUtil.setField(mc, mcIn.getWrapObject(), getWrapObject());
    }

    public void setHeight(int heightIn) {
        ReflectUtil.setField(height, heightIn, getWrapObject());
    }

    public void setWidth(int widthIn) {
        ReflectUtil.setField(width,widthIn,getWrapObject());
    }
    public void initGui(){
        ReflectUtil.invoke(initGui,getWrapObject());
    }
    public void keyTyped(char c,int i){
        invoke(keyTyped, c, i);
    }
    public int getHeight(){
        return (int) getField(height);
    }
    public int getWidth(){
        return (int) getField(width);
    }
    public void drawScreen(int var1, int var2, float var3){

    }
    public void handleComponentHover(IChatComponent chatComponent,int i1,int i2){
        invoke(handleComponentHover,chatComponent.getWrapObject(),i1,i2);
    }
    public void sendChatMessage(String s){
        invoke(sendChatMessage_SZ_V,s,true);
    }
}
