package al.logger.client.wrapper.LoggerMC.gui;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.*;
import al.logger.client.wrapper.LoggerMC.render.FontRenderer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiTextField",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiTextField",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class GuiTextField extends Gui{
@ClassInstance    
public static Class GuiTextFieldClass;
    @WrapField(mcpName = "text",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "text",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field text;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {int.class,FontRenderer.class,int.class,int.class,int.class,int.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = {int.class,FontRenderer.class,int.class,int.class,int.class,int.class})
    public static Constructor GuiTextField_I_FontRenderer_IIII;
    @WrapField(mcpName = "maxStringLength",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "maxStringLength",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field maxStringLength;
    @WrapField(mcpName = "enableBackgroundDrawing",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "enableBackgroundDrawing",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field enableBackgroundDrawing;
    @WrapMethod(mcpName ="setFocused",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName ="setFocused",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setFocused;
    @WrapField(mcpName = "canLoseFocus",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "canLoseFocus",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field canLoseFocus;
    @WrapMethod(mcpName = "drawTextBox",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "drawTextBox",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method drawTextBox;
    @WrapMethod(mcpName = "func_146197_a",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getNthWordFromPosWS",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method func_146197_a;
    @WrapMethod(mcpName = "deleteFromCursor",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "deleteFromCursor",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method deleteFromCursor;
    @WrapField(mcpName = "cursorPosition",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "cursorPosition",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field cursorPosition;
    @WrapMethod(mcpName = "writeText",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "writeText",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method writeText;
    @WrapMethod(mcpName = "setText",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setText",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setText;
    @WrapMethod(mcpName = "textboxKeyTyped",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "textboxKeyTyped",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method textboxKeyTyped;
    @WrapMethod(mcpName = "updateCursorCounter",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "updateCursorCounter",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method updateCursorCounter;
    @WrapMethod(mcpName = "mouseClicked",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "mouseClicked",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method mouseClicked;
    public GuiTextField(Object obj) {
        super(obj);
    }
    public String getText(){
        return (String) getField(text);
    }
    public GuiTextField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height)
    {
        super(ReflectUtil.construction(GuiTextField_I_FontRenderer_IIII,componentId,fontrendererObj.getWrappedObject(),x,y,par5Width,par6Height));
    }
    public void setMaxStringLength(int max){
        setField(maxStringLength,max);
    }
    public void setEnableBackgroundDrawing(boolean max){
        setField(enableBackgroundDrawing,max);
    }
    public void setFocused(boolean b){
        invoke(setFocused,b);
    }
    public void setCanLoseFocus(boolean b){
        setField(canLoseFocus,b);
    }
    public void drawTextBox(){
        invoke(drawTextBox);
    }
    public void deleteFromCursor(int length){
        invoke(deleteFromCursor,length);
    }
    public int func_146197_a(int p_146197_1_, int p_146197_2_, boolean p_146197_3_)
    {
        return (int) invoke(func_146197_a,p_146197_1_,p_146197_2_,p_146197_3_);
    }
    public int getCursorPosition(){
        return (int) getField(cursorPosition);
    }
    public void writeText(String s){
        invoke(writeText,s);
    }
    public void setText(String s){
        invoke(setText,s);
    }
    public boolean textboxKeyTyped(char c,int i){
        return (boolean) invoke(textboxKeyTyped,c,i);
    }
    public void updateCursorCounter(){
        invoke(updateCursorCounter);
    }
    public void mouseClicked(int i1,int i2,int i3){
        invoke(mouseClicked,i1,i2,i3);
    }
}
