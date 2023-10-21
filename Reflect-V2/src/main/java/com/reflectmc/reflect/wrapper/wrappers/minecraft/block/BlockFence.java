package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockFence",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockFence",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockFence extends Block {
    @ClassInstance
    public static Class BlockFenceClass;

    public BlockFence(Object obj) {
        super(obj);
    }

    public static boolean isBlockFence(Block block) {
        return BlockFenceClass.isInstance(block.getWrappedObject());
    }
}
