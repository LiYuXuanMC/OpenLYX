package al.logger.client.wrapper.LoggerMC.gui;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiMemoryErrorScreen",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class GuiMemoryErrorScreen extends GuiScreen{
@ClassInstance    
public static Class GuiMemoryErrorScreenClass;
    public GuiMemoryErrorScreen(Object obj) {
        super(obj);
    }
}
