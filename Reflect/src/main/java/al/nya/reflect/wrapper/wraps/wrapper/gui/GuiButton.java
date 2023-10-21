package al.nya.reflect.wrapper.wraps.wrapper.gui;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.gui.GuiButton", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.gui.GuiButton", targetMap = Maps.Srg1_12_2)
public class GuiButton extends Gui {
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiButton", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.gui.GuiButton", targetMap = Maps.Srg1_12_2)
    public static Class GuiButtonClass;
    @WrapField(mcpName = "id", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "id", targetMap = Maps.Srg1_12_2)
    public static Field id;
    @WrapConstructor(targetMap = Maps.Srg1_8_9, signature = {int.class, int.class, int.class, int.class, int.class, String.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2, signature = {int.class, int.class, int.class, int.class, int.class, String.class})
    public static Constructor GuiButton_IIIII;

    public GuiButton(Object obj) {
        super(obj);
    }

    public GuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(ReflectUtil.construction(GuiButton_IIIII, buttonId, x, y, widthIn, heightIn, buttonText));
    }

    public int getId() {
        return (int) getField(id);
    }
}
