package al.nya.reflect.wrapper.wraps.wrapper.gui;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.util.Map;

@WrapperClass(mcpName = "net.minecraft.client.gui.inventory.GuiContainer",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.gui.inventory.GuiContainer",targetMap = Maps.Srg1_12_2)
public class GuiContainer extends GuiScreen{
    @WrapClass(mcpName = "net.minecraft.client.gui.inventory.GuiContainer",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.gui.inventory.GuiContainer",targetMap = Maps.Srg1_12_2)
    public static Class GuiContainerClass;
    public GuiContainer(Object obj) {
        super(obj);
    }
    public static boolean isGuiContainer(GuiScreen guiScreen){
        return GuiContainerClass.isInstance(guiScreen.getWrapObject());
    }
}
