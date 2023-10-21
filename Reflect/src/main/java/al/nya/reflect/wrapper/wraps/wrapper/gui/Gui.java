package al.nya.reflect.wrapper.wraps.wrapper.gui;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.gui.Gui",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.gui.Gui",targetMap = Maps.Srg1_12_2)
public class Gui extends IWrapper {
    @WrapMethod(mcpName = "drawModalRectWithCustomSizedTexture",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "drawModalRectWithCustomSizedTexture",targetMap = Maps.Srg1_12_2)
    public static Method drawModalRectWithCustomSizedTexture;
    public Gui(Object obj) {
        super(obj);
    }
    public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight)
    {
        ReflectUtil.invoke(drawModalRectWithCustomSizedTexture,null,x,y,u,v,width,height,textureWidth,textureHeight);
    }
}
