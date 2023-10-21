package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockLiquid",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockLiquid",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockLiquid extends Block{
    @ClassInstance
    public static Class BlockLiquidClass;
    public BlockLiquid(Object obj) {
        super(obj);
    }
    public static boolean isBlockLiquid(Block block){
        return BlockLiquidClass.isInstance(block.getWrappedObject());
    }
}
