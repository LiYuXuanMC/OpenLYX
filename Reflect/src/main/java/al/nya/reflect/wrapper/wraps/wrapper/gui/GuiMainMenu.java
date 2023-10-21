package al.nya.reflect.wrapper.wraps.wrapper.gui;


import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiMainMenu", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiMainMenu", targetMap = Maps.Srg1_12_2)
public class GuiMainMenu extends GuiScreen {
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiMainMenu", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiMainMenu", targetMap = Maps.Srg1_12_2)
    public static Class<?> GuiMainMenuClass;
    @WrapConstructor(targetMap = Maps.Srg1_8_9)
    public static Constructor<?> constructor_void;

    public GuiMainMenu() {
        super(ReflectUtil.construction(constructor_void));
    }

    public GuiMainMenu(Object obj) {
        super(obj);
    }
}
