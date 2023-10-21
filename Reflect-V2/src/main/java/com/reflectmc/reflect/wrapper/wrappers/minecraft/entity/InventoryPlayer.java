package com.reflectmc.reflect.wrapper.wrappers.minecraft.entity;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.ItemStack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@WrapperClass(deobfName = "net.minecraft.entity.player.InventoryPlayer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.entity.player.InventoryPlayer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class InventoryPlayer extends WrapperBase {
    @WrapField(deobfName = "player",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "player",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field player;
    @WrapField(deobfName = "itemStack",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "itemStack",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field itemStack;
    @WrapMethod(deobfName = "getCurrentItem",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getCurrentItem",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getCurrentItem;
    @WrapField(deobfName = "mainInventory", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "mainInventory", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field mainInventory;
    @WrapField(deobfName = "currentItem", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "currentItem", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field currentItem;
    @WrapMethod(deobfName = "getStackInSlot", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getStackInSlot", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getStackInSlot;
    @WrapField(deobfName = "armorInventory", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "armorInventory", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field armorInventory;

    public InventoryPlayer(Object obj) {
        super(obj);
    }

    public EntityPlayer getPlayer() {
        return new EntityPlayer(getField(player));
    }

    public ItemStack getItemStack() {
        return new ItemStack(getField(itemStack));
    }
    public ItemStack getCurrentItem(){
        return new ItemStack(invokeMethod(getCurrentItem));
    }
    public ItemStack[] getMainInventory(){
        Object[] objects = (Object[]) getField(mainInventory);
        ItemStack[] itemStacks = new ItemStack[objects.length];
        int i = 0;
        for (Object object : objects) {
            itemStacks[i] = new ItemStack(object);
            i++;
        }
        return itemStacks;
    }
    public void setMainInventory(ItemStack[] itemStacks) {
        List<Object> objs = new ArrayList<>();
        for (ItemStack stack : itemStacks) {
            objs.add(stack.getWrappedObject());
        }
        setField(mainInventory, objs.toArray());
    }
    public ItemStack[] getArmorInventory() {
        Object[] objects = (Object[]) getField(armorInventory);
        ItemStack[] itemStacks = new ItemStack[objects.length];
        int i = 0;
        for (Object object : objects) {
            itemStacks[i] = new ItemStack(object);
            i++;
        }
        return itemStacks;
    }
    public void setArmorInventory(ItemStack[] itemStacks) {
        List<Object> objs = new ArrayList<>();
        for (ItemStack stack : itemStacks) {
            objs.add(stack.getWrappedObject());
        }
        setField(armorInventory, objs.toArray());
    }
    public int currentItem() {
        return (int) getField(currentItem);
    }
    public void currentItem(int i) {
        setField(currentItem, i);
    }
    public ItemStack getStackInSlot(int index) {
        return new ItemStack(invokeMethod(getStackInSlot, index));
    }
}
