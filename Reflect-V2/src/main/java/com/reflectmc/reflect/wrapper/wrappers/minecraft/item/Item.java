package com.reflectmc.reflect.wrapper.wrappers.minecraft.item;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.block.Block;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.item.Item", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.item.Item", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Item extends WrapperBase {
    @WrapMethod(deobfName = "getUnlocalizedName", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "()Ljava/lang/String;")
    @WrapMethod(deobfName = "getUnlocalizedName", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = "()Ljava/lang/String;")
    public static Method getUnlocalizedName;
    @WrapField(deobfName = "maxDamage", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "maxDamage", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field maxDamage;
    @WrapMethod(deobfName = "getIdFromItem", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getIdFromItem;
    @WrapMethod(deobfName = "getItemFromBlock", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getItemFromBlock;

    public Item(Object obj) {
        super(obj);
    }

    public String getUnlocalizedName() {
        return (String) invokeMethod(getUnlocalizedName);
    }

    public int getMaxDamage() {
        return (int) getField(maxDamage);
    }

    public static int getIdFromItem(Item item) {
        return (int) invokeStaticMethod(getIdFromItem, item.getWrappedObject());
    }

    public static Item getItemFromBlock(Block block) {
        return new Item(invokeStaticMethod(getItemFromBlock, block.getWrappedObject()));
    }
}
