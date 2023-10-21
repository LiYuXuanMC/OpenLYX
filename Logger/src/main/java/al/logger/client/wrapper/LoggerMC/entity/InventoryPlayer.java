package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;
import al.logger.client.wrapper.LoggerMC.utils.NonNullList;
import al.logger.client.wrapper.environment.EnvironmentDetector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.entity.player.InventoryPlayer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.entity.player.InventoryPlayer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class InventoryPlayer extends IWrapper {
    @WrapField(mcpName = "player",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "player",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field player;
    @WrapField(mcpName = "itemStack",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "itemStack",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field itemStack;
    @WrapMethod(mcpName = "getCurrentItem",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getCurrentItem",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getCurrentItem;
    @WrapField(mcpName = "mainInventory", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "mainInventory", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field mainInventory;
    @WrapField(mcpName = "currentItem", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "currentItem", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field currentItem;
    @WrapMethod(mcpName = "getStackInSlot", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getStackInSlot", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getStackInSlot;
    @WrapField(mcpName = "armorInventory", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "armorInventory", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field armorInventory;
    @WrapMethod(mcpName = "getTotalArmorValue", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getTotalArmorValue", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getTotalArmorValue;

    public InventoryPlayer(Object obj) {
        super(obj);
    }

    public EntityPlayer getPlayer() {
        return new EntityPlayer(ReflectUtil.getField(player, getWrappedObject()));
    }

    public ItemStack getItemStack() {
        return new ItemStack(ReflectUtil.getField(itemStack, getWrappedObject()));
    }
    public ItemStack getCurrentItem(){
        return new ItemStack(invoke(getCurrentItem));
    }
    public int getTotalArmorValue(){
        return (int) invoke(getTotalArmorValue);
    }
    public ItemStack[] getMainInventory(){
        if (Wrapper.isTargetMap(new Environment[]{Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, EnvironmentDetector.getMinecraft())){
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
        if (Wrapper.isTargetMap(new Environment[]{Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, EnvironmentDetector.getMinecraft())){
            NonNullList list = new NonNullList();
        }
        List<Object> objs = new ArrayList<>();
        for (ItemStack stack : itemStacks) {
            objs.add(stack.getWrappedObject());
        }
        setField(mainInventory, objs.toArray());
    }

    public ItemStack[] getArmorInventory() {
        if (Wrapper.isTargetMap(new Environment[]{Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, EnvironmentDetector.getMinecraft())){
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
        return new ItemStack(invoke(getStackInSlot, index));
    }
}
