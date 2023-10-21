package al.logger.client.wrapper.LoggerMC.gui;

import al.logger.client.wrapper.LoggerMC.item.Container;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Field;
import java.util.Map;

@WrapperClass(mcpName = "net.minecraft.client.gui.inventory.GuiContainer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.gui.inventory.GuiContainer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class GuiContainer extends GuiScreen{
@ClassInstance    
public static Class GuiContainerClass;
     @WrapField(mcpName = "inventorySlots",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
     public static Field inventorySlots;
    public GuiContainer(Object obj) {
        super(obj);
    }
    public static boolean isGuiContainer(GuiScreen guiScreen){
        return GuiContainerClass.isInstance(guiScreen.getWrappedObject());
    }

    public Container getInventorySlots(){
        return new Container(getField(inventorySlots));
    }

}
