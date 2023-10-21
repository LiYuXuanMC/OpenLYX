package al.nya.reflect.wrapper.wraps.wrapper.gui;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.util.Map;

@WrapperClass(mcpName = "net.minecraft.client.gui.inventory.GuiInventory",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.gui.inventory.GuiInventory",targetMap = Maps.Srg1_12_2)
public class GuiInventory extends GuiScreen {
    @WrapClass(mcpName = "net.minecraft.client.gui.inventory.GuiInventory",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.gui.inventory.GuiInventory",targetMap = Maps.Srg1_12_2)
    public static Class GuiInventoryClass;
    public GuiInventory(Object obj) {
        super(obj);
    }
    public static boolean isGuiInventory(GuiScreen guiScreen){
        return GuiInventoryClass.isInstance(guiScreen.getWrapObject());
    }
}
