package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.block.Block;
import al.nya.reflect.wrapper.wraps.wrapper.block.IBlockState;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

@WrapperClass(mcpName = "net.minecraft.item.ItemStack",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemStack",targetMap = Maps.Srg1_12_2)
public class ItemStack extends IWrapper {
    @WrapField(mcpName = "item",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "item",targetMap = Maps.Srg1_12_2)
    public static Field item;
    @WrapMethod(mcpName = "getMaxItemUseDuration",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getMaxItemUseDuration",targetMap = Maps.Srg1_12_2)
    public static Method getMaxItemUseDuration;
    @WrapClass(mcpName = "net.minecraft.item.ItemStack",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemStack",targetMap = Maps.Srg1_12_2)
    public static Class ItemStackClass;
    @WrapField(mcpName = "stackSize",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "stackSize",targetMap = Maps.Srg1_12_2)
    public static Field stackSize;
    @WrapMethod(mcpName = "getUnlocalizedName",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getUnlocalizedName",targetMap = Maps.Srg1_12_2)
    public static Method getUnlocalizedName;
    @WrapMethod(mcpName = "getStrVsBlock",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getDestroySpeed",targetMap = Maps.Srg1_12_2)
    public static Method getStrVsBlock;
    @WrapMethod(mcpName = "getDisplayName",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getDisplayName",targetMap = Maps.Srg1_12_2)
    public static Method getDisplayName;
    @WrapField(mcpName = "itemDamage",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "itemDamage",targetMap = Maps.Srg1_12_2)
    public static Field itemDamage;
    public ItemStack(Object obj) {
        super(obj);
    }
    public Item getItem(){
        return new Item(getField(item));
    }
    public int getMaxItemUseDuration(){
        return (int) invoke(getMaxItemUseDuration);
    }
    public int getStackSize(){
        return (int) getField(stackSize);
    }
    public String getUnlocalizedName(){
        return (String) invoke(getUnlocalizedName);
    }
    public float getStrVsBlock(Block block){
        return (float) invoke(getStrVsBlock,block.getWrapObject());
    }
    public float getDestroySpeed(IBlockState block){
        return (float) invoke(getStrVsBlock,block.getWrapObject());
    }
    public String getDisplayName(){
        return (String) invoke(getDisplayName);
    }
    public int getItemDamage(){
        return (int) getField(itemDamage);
    }
}
