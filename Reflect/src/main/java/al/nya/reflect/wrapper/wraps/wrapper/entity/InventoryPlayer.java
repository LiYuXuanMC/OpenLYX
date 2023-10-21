package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.utils.NonNullList;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.entity.player.InventoryPlayer",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.player.InventoryPlayer",targetMap = Maps.Srg1_12_2)
public class InventoryPlayer extends IWrapper {
    @WrapField(mcpName = "player",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "player",targetMap = Maps.Srg1_12_2)
    public static Field player;
    @WrapField(mcpName = "itemStack",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "itemStack",targetMap = Maps.Srg1_12_2)
    public static Field itemStack;
    @WrapMethod(mcpName = "getCurrentItem",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getCurrentItem",targetMap = Maps.Srg1_12_2)
    public static Method getCurrentItem;
    @WrapField(mcpName = "mainInventory", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "mainInventory", targetMap = Maps.Srg1_12_2)
    public static Field mainInventory;
    @WrapField(mcpName = "currentItem", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "currentItem", targetMap = Maps.Srg1_12_2)
    public static Field currentItem;
    @WrapMethod(mcpName = "getStackInSlot", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getStackInSlot", targetMap = Maps.Srg1_12_2)
    public static Method getStackInSlot;
    @WrapField(mcpName = "armorInventory", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "armorInventory", targetMap = Maps.Srg1_12_2)
    public static Field armorInventory;

    public InventoryPlayer(Object obj) {
        super(obj);
    }

    public EntityPlayer getPlayer() {
        return new EntityPlayer(ReflectUtil.getField(player, getWrapObject()));
    }

    public ItemStack getItemStack() {
        return new ItemStack(ReflectUtil.getField(itemStack, getWrapObject()));
    }
    public ItemStack getCurrentItem(){
        return new ItemStack(invoke(getCurrentItem));
    }
    public ItemStack[] getMainInventory(){
        if (Wrapper.env.equals(Maps.Srg1_12_2)){
            AbstractList list = (AbstractList) getField(mainInventory);
            ItemStack[] itemStacks = new ItemStack[list.size()];
            int i = 0;
            for (Object object : list) {
                itemStacks[i] = new ItemStack(object);
                i++;
            }
            return itemStacks;
        }
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
        if (Wrapper.env.equals(Maps.Srg1_12_2)) {
            NonNullList list = new NonNullList();
        }
        List<Object> objs = new ArrayList<>();
        for (ItemStack stack : itemStacks) {
            objs.add(stack.getWrapObject());
        }
        setField(mainInventory, objs.toArray());
    }

    public ItemStack[] getArmorInventory() {
        if (Wrapper.env.equals(Maps.Srg1_12_2)) {
            AbstractList list = (AbstractList) getField(armorInventory);
            ItemStack[] itemStacks = new ItemStack[list.size()];
            int i = 0;
            for (Object object : list) {
                itemStacks[i] = new ItemStack(object);
                i++;
            }
            return itemStacks;
        }
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
            objs.add(stack.getWrapObject());
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
        return new ItemStack(invoke(getStackInSlot, index));
    }
}
