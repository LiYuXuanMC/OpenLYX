package com.reflectmc.reflect.wrapper.wrappers.minecraft.item;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.block.Block;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.item.ItemBlock", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.item.ItemBlock", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ItemBlock extends Item {
    @ClassInstance
    public static Class ItemBlockClass;
    @WrapMethod(deobfName = "getBlock", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getBlock", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getBlock;
    @WrapMethod(deobfName = "canPlaceBlockOnSide", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "canPlaceBlockOnSide", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method canPlaceBlockOnSide;
    @WrapMethod(deobfName = "getItemFromBlock", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Method getItemFromBlock;

    public ItemBlock(Object obj) {
        super(obj);
    }

    public static boolean isItemBlock(Item item) {
        return ItemBlockClass.isInstance(item.getWrappedObject());
    }

    public Block getBlock() {
        return new Block(invokeMethod(getBlock));
    }

//    public boolean canPlaceBlockOnSide(World world, BlockPos blockPos, Enum<?> sideHit, EntityPlayer entityPlayer, ItemStack itemstack) {
//        return (boolean) invokeMethod(canPlaceBlockOnSide, world.getWrappedObject(), blockPos.getWrappedObject(), sideHit, entityPlayer.getWrapObject(), itemstack.getWrapObject());
//    }
}
