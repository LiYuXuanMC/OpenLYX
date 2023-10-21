package al.logger.client.wrapper.LoggerMC.gui;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.utils.text.IChatComponent;

import java.lang.reflect.Method;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiNewChat",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiNewChat",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class GuiNewChat extends IWrapper {
@ClassInstance    
public static Class GuiNewChatClass;
    @WrapMethod(mcpName = "printChatMessage",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "printChatMessage",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method printChatMessage;
    @WrapMethod(mcpName = "resetScroll",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "resetScroll",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method resetScroll;
    @WrapMethod(mcpName = "getSentMessages",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getSentMessages",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getSentMessages;
    @WrapMethod(mcpName = "getChatComponent",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getChatComponent",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getChatComponent;
    @WrapMethod(mcpName = "printChatMessageWithOptionalDeletion",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "printChatMessageWithOptionalDeletion",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method printChatMessageWithOptionalDeletion;
    @WrapMethod(mcpName = "scroll",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "scroll",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method scroll;
    @WrapMethod(mcpName = "getLineCount",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getLineCount",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getLineCount;
    @WrapMethod(mcpName = "addToSentMessages",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "addToSentMessages",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method addToSentMessages;
    @WrapMethod(mcpName = "getChatOpen",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getChatOpen",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getChatOpen;
    public GuiNewChat(Object obj) {
        super(obj);
    }
    public void printChatMessage(IChatComponent text){
        ReflectUtil.invoke(printChatMessage,getWrappedObject(),text.getWrappedObject());
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
        invoke(printChatMessageWithOptionalDeletion,icc.getWrappedObject(),i);
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
        return GuiNewChatClass.isInstance(screen.getWrappedObject());
    }
}
