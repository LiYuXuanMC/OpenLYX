package al.nya.reflect.wrapper.wraps.wrapper.gui;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiMemoryErrorScreen",targetMap = Maps.Srg1_8_9)
public class GuiMemoryErrorScreen extends GuiScreen{
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiMemoryErrorScreen",targetMap = Maps.Srg1_8_9)
    public static Class GuiMemoryErrorScreenClass;
    public GuiMemoryErrorScreen(Object obj) {
        super(obj);
    }
}
