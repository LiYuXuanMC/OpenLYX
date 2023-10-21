package com.reflectmc.reflect.wrapper.wrappers.minecraft.gui;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text.IChatComponent;

import java.lang.reflect.Method;
import java.util.List;

@WrapperClass(deobfName = "net.minecraft.client.gui.GuiNewChat",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.gui.GuiNewChat",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class GuiNewChat extends Gui {
    @ClassInstance
    public static Class GuiNewChatClass;
    @WrapMethod(deobfName = "printChatMessage",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "printChatMessage",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method printChatMessage;
    @WrapMethod(deobfName = "resetScroll",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "resetScroll",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method resetScroll;
    @WrapMethod(deobfName = "getSentMessages",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getSentMessages",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getSentMessages;
    @WrapMethod(deobfName = "getChatComponent",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getChatComponent",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getChatComponent;
    @WrapMethod(deobfName = "printChatMessageWithOptionalDeletion",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "printChatMessageWithOptionalDeletion",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method printChatMessageWithOptionalDeletion;
    @WrapMethod(deobfName = "scroll",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "scroll",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method scroll;
    @WrapMethod(deobfName = "getLineCount",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getLineCount",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getLineCount;
    @WrapMethod(deobfName = "addToSentMessages",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "addToSentMessages",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method addToSentMessages;
    @WrapMethod(deobfName = "getChatOpen",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getChatOpen",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getChatOpen;
    public GuiNewChat(Object obj) {
        super(obj);
    }
    public void printChatMessage(IChatComponent text){
        invokeMethod(printChatMessage,text.getWrappedObject());
    }
    public void resetScroll(){
        invokeMethod(resetScroll);
    }
    public List<String> getSentMessages(){
        return (List<String>) invokeMethod(getSentMessages);
    }
    /*public IChatComponent getChatComponent(int i1,int i2){
        return new IChatComponent(invokeMethod(getChatComponent,i1,i2));
    }
    public void printChatMessageWithOptionalDeletion(IChatComponent icc,int i){
        invokeMethod(printChatMessageWithOptionalDeletion,icc.getWrapObject(),i);
    }*/
    public void scroll(int i){
        invokeMethod(scroll,i);
    }
    public int getLineCount(){
        return (int) invokeMethod(getLineCount);
    }
    public void addToSentMessages(String s){
        invokeMethod(addToSentMessages,s);
    }
    public static boolean isGuiNewChat(GuiScreen screen){
        return GuiNewChatClass.isInstance(screen.getWrappedObject());
    }
}
