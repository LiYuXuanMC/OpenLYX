package al.logger.client.wrapper.LoggerMC.gui;


import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.utils.text.IChatComponent;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiDisconnected", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiDisconnected", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class GuiDisconnected extends GuiScreen {
@ClassInstance    
public static Class<?> GuiDisconnectedClass;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = {GuiScreen.class, String.class, IChatComponent.class})
    public static Constructor<?> constructor_GuiScreen_String_String;

    public GuiDisconnected(GuiScreen guiScreen, String s, IChatComponent s1) {
        super(ReflectUtil.construction(constructor_GuiScreen_String_String, guiScreen.getWrappedObject(), s, s1.getWrappedObject()));
    }


    public GuiDisconnected(Object obj) {
        super(obj);
    }

}
