package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockChest",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockChest",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockChest extends Block {
    @ClassInstance
    public static Class BlockChestClass;

    public BlockChest(Object obj) {
        super(obj);
    }

    public static boolean isBlockChest(Block block) {
        return BlockChestClass.isInstance(block.getWrappedObject());
    }
}
