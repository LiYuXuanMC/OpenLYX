package al.nya.reflect.wrapper.wraps.wrapper.gui;


import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiDisconnected", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiDisconnected", targetMap = Maps.Srg1_12_2)
public class GuiDisconnected extends GuiScreen {
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiDisconnected", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiDisconnected", targetMap = Maps.Srg1_12_2)
    public static Class<?> GuiDisconnectedClass;
    @WrapConstructor(targetMap = Maps.Srg1_8_9, signature = {GuiScreen.class, String.class, IChatComponent.class})
    public static Constructor<?> constructor_GuiScreen_String_String;

    public GuiDisconnected(GuiScreen guiScreen, String s, IChatComponent s1) {
        super(ReflectUtil.construction(constructor_GuiScreen_String_String, guiScreen.getWrapObject(), s, s1.getWrapObject()));
    }


    public GuiDisconnected(Object obj) {
        super(obj);
    }

}
