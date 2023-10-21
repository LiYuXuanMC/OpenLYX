package al.nya.reflect.wrapper.wraps.wrapper.gui;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiChat",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiChat",targetMap = Maps.Srg1_12_2)
public class GuiChat extends GuiScreen{
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiChat",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiChat",targetMap = Maps.Srg1_12_2)
    public static Class GuiChatClass;
    @WrapField(mcpName = "inputField",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "inputField",targetMap = Maps.Srg1_12_2)
    public static Field inputField;
    @WrapField(mcpName = "sentHistoryCursor",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "sentHistoryCursor",targetMap = Maps.Srg1_12_2)
    public static Field sentHistoryCursor;
    public GuiChat(Object obj) {
        super(obj);
    }
    public static boolean isGuiChat(GuiScreen guiScreen){
        return GuiChatClass.isInstance(guiScreen.getWrapObject());
    }
    public GuiTextField getInputField(){
        return new GuiTextField(getField(inputField));
    }
    public void setSentHistoryCursor(int i){
        setField(sentHistoryCursor,i);
    }
    public void setInputField(GuiTextField textField){
        setField(inputField,textField.getWrapObject());
    }
}
