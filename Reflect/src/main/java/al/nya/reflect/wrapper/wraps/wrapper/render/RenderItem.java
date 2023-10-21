package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.RenderItem", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.RenderItem", targetMap = Maps.Srg1_12_2)
public class RenderItem extends IWrapper {
    @WrapField(mcpName = "zLevel", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "zLevel", targetMap = Maps.Srg1_12_2)
    public static Field zLevel;
    @WrapMethod(mcpName = "renderItemIntoGUI", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "renderItemIntoGUI", targetMap = Maps.Srg1_12_2)
    public static Method renderItemIntoGUI;
    @WrapMethod(mcpName = "renderItemOverlayIntoGUI", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "renderItemOverlayIntoGUI", targetMap = Maps.Srg1_12_2)
    public static Method renderItemOverlayIntoGUI;
    public RenderItem(Object obj) {
        super(obj);
    }
    public float getZLevel() {
        return (float) getField(zLevel);
    }
    public void setZLevel(float f){
        setField(zLevel,f);
    }
    public void renderItemIntoGUI(ItemStack stack, int x, int y){
        invoke(renderItemIntoGUI,stack.getWrapObject(),x,y);
    }
    public void renderItemOverlayIntoGUI(FontRenderer fr, ItemStack stack, int xPosition, int yPosition, String text){
        invoke(renderItemOverlayIntoGUI,fr.getWrapObject(),stack.getWrapObject(),xPosition,yPosition,text);
    }
}
