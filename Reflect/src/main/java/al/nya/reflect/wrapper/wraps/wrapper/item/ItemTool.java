package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.block.Block;
import al.nya.reflect.wrapper.wraps.wrapper.block.IBlockState;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.item.ItemTool",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemTool",targetMap = Maps.Srg1_12_2)
public class ItemTool extends Item{
    @WrapField(mcpName = "toolMaterial",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "toolMaterial",targetMap = Maps.Srg1_12_2)
    public static Field toolMaterial;
    @WrapClass(mcpName = "net.minecraft.item.ItemTool",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemTool",targetMap = Maps.Srg1_12_2)
    public static Class ItemToolClass;
    @WrapMethod(mcpName = "getStrVsBlock",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getDestroySpeed",targetMap = Maps.Srg1_12_2)
    public static Method getStrVsBlock;
    public ItemTool(Object obj) {
        super(obj);
    }
    public Enum getToolMaterial(){
        return (Enum) getField(toolMaterial);
    }
    public static boolean isItemTool(Item item){
        return ItemToolClass.isInstance(item.getWrapObject());
    }
    public float getStrVsBlock(ItemStack itemStack, Block block) {
        return (float) invoke(getStrVsBlock,itemStack.getWrapObject(),block.getWrapObject());
    }
    public float getDestroySpeed(ItemStack itemStack, IBlockState block) {
        return (float) invoke(getStrVsBlock,itemStack.getWrapObject(),block.getWrapObject());
    }
}
