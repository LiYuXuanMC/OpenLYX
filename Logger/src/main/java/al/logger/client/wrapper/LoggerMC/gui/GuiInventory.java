package al.logger.client.wrapper.LoggerMC.gui;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.util.Map;

@WrapperClass(mcpName = "net.minecraft.client.gui.inventory.GuiInventory",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.gui.inventory.GuiInventory",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class GuiInventory extends GuiScreen {
@ClassInstance    
public static Class GuiInventoryClass;
    public GuiInventory(Object obj) {
        super(obj);
    }
    public static boolean isGuiInventory(GuiScreen guiScreen){
        return GuiInventoryClass.isInstance(guiScreen.getWrappedObject());
    }
}
