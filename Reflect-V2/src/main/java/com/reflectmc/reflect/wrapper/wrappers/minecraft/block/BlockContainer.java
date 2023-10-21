package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockContainer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockContainer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockContainer extends Block{
    @ClassInstance
    public static Class BlockContainerClass;
    public BlockContainer(Object obj) {
        super(obj);
    }
    public static boolean isBlockContainer(Block block){
        return BlockContainerClass.isInstance(block.getWrappedObject());
    }
}
