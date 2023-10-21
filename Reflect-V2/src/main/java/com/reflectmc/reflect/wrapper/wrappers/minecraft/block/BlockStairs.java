package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockStairs", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockStairs", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockStairs extends Block{
    @ClassInstance
    public static Class BlockStairsClass;
    public BlockStairs(Object obj) {
        super(obj);
    }
    public static boolean isBlockStairs(Block block){
        return BlockStairsClass.isInstance(block.getWrappedObject());
    }
}
