package al.logger.client.wrapper.LoggerMC.gui;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiChat",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiChat",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class GuiChat extends GuiScreen{
@ClassInstance    
public static Class GuiChatClass;
    @WrapField(mcpName = "inputField",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "inputField",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field inputField;
    @WrapField(mcpName = "sentHistoryCursor",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "sentHistoryCursor",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field sentHistoryCursor;
    public GuiChat(Object obj) {
        super(obj);
    }
    public static boolean isGuiChat(GuiScreen guiScreen){
        return GuiChatClass.isInstance(guiScreen.getWrappedObject());
    }
    public GuiTextField getInputField(){
        return new GuiTextField(getField(inputField));
    }
    public void setSentHistoryCursor(int i){
        setField(sentHistoryCursor,i);
    }
    public void setInputField(GuiTextField textField){
        setField(inputField,textField.getWrappedObject());
    }
}
