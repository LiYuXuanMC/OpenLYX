package com.reflectmc.reflect.wrapper.wrappers.minecraft.item;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.block.Block;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.block.IBlockState;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.item.ItemStack",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.item.ItemStack",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ItemStack extends WrapperBase {
    @WrapField(deobfName = "item",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "item",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field item;
    @WrapMethod(deobfName = "getMaxItemUseDuration",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getMaxItemUseDuration",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getMaxItemUseDuration;
    @ClassInstance
    public static Class ItemStackClass;
    @WrapField(deobfName = "stackSize",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "stackSize",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field stackSize;
    @WrapMethod(deobfName = "getUnlocalizedName",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getUnlocalizedName",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getUnlocalizedName;
    @WrapMethod(deobfName = "getStrVsBlock",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getDestroySpeed",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getStrVsBlock;
    @WrapMethod(deobfName = "getDisplayName",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getDisplayName",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getDisplayName;
    @WrapField(deobfName = "itemDamage",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "itemDamage",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field itemDamage;
    public ItemStack(Object obj) {
        super(obj);
    }
    public Item getItem(){
        return new Item(getField(item));
    }
    public int getMaxItemUseDuration(){
        return (int) invokeMethod(getMaxItemUseDuration);
    }
    public int getStackSize(){
        return (int) getField(stackSize);
    }
    public String getUnlocalizedName(){
        return (String) invokeMethod(getUnlocalizedName);
    }
    public float getStrVsBlock(Block block){
        return (float) invokeMethod(getStrVsBlock,block.getWrappedObject());
    }
    public float getDestroySpeed(IBlockState block){
        return (float) invokeMethod(getStrVsBlock,block.getWrappedObject());
    }
    public String getDisplayName(){
        return (String) invokeMethod(getDisplayName);
    }
    public int getItemDamage(){
        return (int) getField(itemDamage);
    }
}
