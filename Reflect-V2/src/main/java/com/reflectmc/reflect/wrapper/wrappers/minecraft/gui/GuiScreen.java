package com.reflectmc.reflect.wrapper.wrappers.minecraft.gui;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@WrapperClass(deobfName = "net.minecraft.client.gui.GuiScreen",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.gui.GuiScreen",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class GuiScreen extends Gui {
    @ClassInstance
    public static Class GuiScreenClass;
    @WrapMethod(deobfName = "drawScreen",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "drawScreen",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method drawScreen;
    @WrapMethod(deobfName = "initGui",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "initGui",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method initGui;
    @WrapMethod(deobfName = "onGuiClosed",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "onGuiClosed",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method onGuiClosed;
    @WrapField(deobfName = "mc",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "mc",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field mc;
    @WrapField(deobfName = "width",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "width",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field width;
    @WrapField(deobfName = "height", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "height", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field height;
    @WrapMethod(deobfName = "keyTyped", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "keyTyped", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method keyTyped;
    @WrapMethod(deobfName = "sendChatMessage", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "(Ljava/lang/String;Z)V")
    @WrapMethod(deobfName = "sendChatMessage", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = "(Ljava/lang/String;Z)V")
    public static Method sendChatMessage_SZ_V;
    @WrapMethod(deobfName = "sendChatMessage", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "(Ljava/lang/String;)V")
    @WrapMethod(deobfName = "sendChatMessage", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = "(Ljava/lang/String;)V")
    public static Method sendChatMessage_S_V;
    @WrapMethod(deobfName = "handleComponentHover", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "handleComponentHover", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method handleComponentHover;
    @WrapMethod(deobfName = "updateScreen", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "updateScreen", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method updateScreen;
    @WrapMethod(deobfName = "mouseClicked", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "mouseClicked", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method mouseClicked;
    @WrapMethod(deobfName = "drawHoveringText", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "drawHoveringText", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method drawHoveringText;
    @WrapMethod(deobfName = "actionPerformed", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "actionPerformed", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method actionPerformed;
    @WrapMethod(deobfName = "handleKeyboardInput", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method handleKeyboardInput;

    public GuiScreen(Object obj) {
        super(obj);
    }

    public void drawHoveringText(List<?> list, int i, int i1) {
        invokeMethod(drawHoveringText, list, i, i1);
    }

    public void setMc(Minecraft mcIn) {
        setField(mc,mcIn.getWrappedObject());
    }

    public void setHeight(int heightIn) {
        setField(height,heightIn);
    }

    public void setWidth(int widthIn) {
        setField(width,widthIn);
    }
    public void initGui(){
        invokeMethod(initGui);
    }
    public void keyTyped(char c,int i){
        invokeMethod(keyTyped, c, i);
    }
    public int getHeight(){
        return (int) getField(height);
    }
    public int getWidth(){
        return (int) getField(width);
    }
    public void drawScreen(int var1, int var2, float var3){

    }
    /*public void handleComponentHover(IChatComponent chatComponent,int i1,int i2){
        invoke(handleComponentHover,chatComponent.getWrapObject(),i1,i2);
    }

     */
    public void sendChatMessage(String s){
        invokeMethod(sendChatMessage_SZ_V,s,true);
    }
}
