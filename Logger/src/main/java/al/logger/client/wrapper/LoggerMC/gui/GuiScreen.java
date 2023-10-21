package al.logger.client.wrapper.LoggerMC.gui;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.utils.text.IChatComponent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiScreen",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiScreen",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class GuiScreen extends Gui {
@ClassInstance    
public static Class GuiScreenClass;
    @WrapMethod(mcpName = "drawScreen",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "drawScreen",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method drawScreen;
    @WrapMethod(mcpName = "initGui",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "initGui",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method initGui;
    @WrapMethod(mcpName = "onGuiClosed",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "onGuiClosed",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method onGuiClosed;
    @WrapField(mcpName = "mc",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "mc",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field mc;
    @WrapField(mcpName = "width",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "width",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field width;
    @WrapField(mcpName = "height", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "height", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field height;
    @WrapMethod(mcpName = "keyTyped", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "keyTyped", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method keyTyped;
    @WrapMethod(mcpName = "sendChatMessage", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "(Ljava/lang/String;Z)V")
    @WrapMethod(mcpName = "sendChatMessage", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "(Ljava/lang/String;Z)V")
    public static Method sendChatMessage_SZ_V;
    @WrapMethod(mcpName = "sendChatMessage", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "(Ljava/lang/String;)V")
    @WrapMethod(mcpName = "sendChatMessage", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "(Ljava/lang/String;)V")
    public static Method sendChatMessage_S_V;
    @WrapMethod(mcpName = "handleComponentHover", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "handleComponentHover", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method handleComponentHover;
    @WrapMethod(mcpName = "updateScreen", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "updateScreen", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method updateScreen;
    @WrapMethod(mcpName = "mouseClicked", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "mouseClicked", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method mouseClicked;
    @WrapMethod(mcpName = "mouseReleased", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "mouseReleased", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method mouseReleased;
    @WrapMethod(mcpName = "drawHoveringText", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "drawHoveringText", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method drawHoveringText;
    @WrapMethod(mcpName = "actionPerformed", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "actionPerformed", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method actionPerformed;
    @WrapMethod(mcpName = "handleKeyboardInput", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method handleKeyboardInput;

    public GuiScreen(Object obj) {
        super(obj);
    }

    public void drawHoveringText(List<?> list, int i, int i1) {
        invoke(drawHoveringText, list, i, i1);
    }

    public void setMc(Minecraft mcIn) {
        ReflectUtil.setField(mc, mcIn.getWrappedObject(), getWrappedObject());
    }

    public void setHeight(int heightIn) {
        ReflectUtil.setField(height, heightIn, getWrappedObject());
    }

    public void setWidth(int widthIn) {
        ReflectUtil.setField(width,widthIn,getWrappedObject());
    }
    public void initGui(){
        ReflectUtil.invoke(initGui,getWrappedObject());
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
        invoke(handleComponentHover,chatComponent.getWrappedObject(),i1,i2);
    }
    public void sendChatMessage(String s){
        invoke(sendChatMessage_SZ_V,s,true);
    }
}
