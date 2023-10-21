package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockEnderChest",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockEnderChest",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockEnderChest extends Block {
    @ClassInstance
    public static Class BlockEnderChestClass;

    public BlockEnderChest(Object obj) {
        super(obj);
    }

    public static boolean isBlockEnderChest(Block block) {
        return BlockEnderChestClass.isInstance(block.getWrappedObject());
    }
}
