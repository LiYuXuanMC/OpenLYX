package al.nya.reflect.wrapper.wraps.wrapper.gui;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;

import java.lang.reflect.Method;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiNewChat",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiNewChat",targetMap = Maps.Srg1_12_2)
public class GuiNewChat extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiNewChat",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiNewChat",targetMap = Maps.Srg1_12_2)
    public static Class GuiNewChatClass;
    @WrapMethod(mcpName = "printChatMessage",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "printChatMessage",targetMap = Maps.Srg1_12_2)
    public static Method printChatMessage;
    @WrapMethod(mcpName = "resetScroll",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "resetScroll",targetMap = Maps.Srg1_12_2)
    public static Method resetScroll;
    @WrapMethod(mcpName = "getSentMessages",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getSentMessages",targetMap = Maps.Srg1_12_2)
    public static Method getSentMessages;
    @WrapMethod(mcpName = "getChatComponent",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getChatComponent",targetMap = Maps.Srg1_12_2)
    public static Method getChatComponent;
    @WrapMethod(mcpName = "printChatMessageWithOptionalDeletion",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "printChatMessageWithOptionalDeletion",targetMap = Maps.Srg1_12_2)
    public static Method printChatMessageWithOptionalDeletion;
    @WrapMethod(mcpName = "scroll",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "scroll",targetMap = Maps.Srg1_12_2)
    public static Method scroll;
    @WrapMethod(mcpName = "getLineCount",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getLineCount",targetMap = Maps.Srg1_12_2)
    public static Method getLineCount;
    @WrapMethod(mcpName = "addToSentMessages",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "addToSentMessages",targetMap = Maps.Srg1_12_2)
    public static Method addToSentMessages;
    @WrapMethod(mcpName = "getChatOpen",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getChatOpen",targetMap = Maps.Srg1_12_2)
    public static Method getChatOpen;
    public GuiNewChat(Object obj) {
        super(obj);
    }
    public void printChatMessage(IChatComponent text){
        ReflectUtil.invoke(printChatMessage,getWrapObject(),text.getWrapObject());
    }
    public void resetScroll(){
        invoke(resetScroll);
    }
    public List<String> getSentMessages(){
        return (List<String>) invoke(getSentMessages);
    }
    public IChatComponent getChatComponent(int i1,int i2){
        return new IChatComponent(invoke(getChatComponent,i1,i2));
    }
    public void printChatMessageWithOptionalDeletion(IChatComponent icc,int i){
        invoke(printChatMessageWithOptionalDeletion,icc.getWrapObject(),i);
    }
    public void scroll(int i){
        invoke(scroll,i);
    }
    public int getLineCount(){
        return (int) invoke(getLineCount);
    }
    public void addToSentMessages(String s){
        invoke(addToSentMessages,s);
    }
    public static boolean isGuiNewChat(GuiScreen screen){
        return GuiNewChatClass.isInstance(screen.getWrapObject());
    }
}
