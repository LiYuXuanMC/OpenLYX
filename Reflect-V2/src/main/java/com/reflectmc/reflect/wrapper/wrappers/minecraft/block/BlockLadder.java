package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.block.BlockLadder",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.BlockLadder",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class BlockLadder extends Block{
    @ClassInstance
    public static Class BlockLadderClass;
    public BlockLadder(Object obj) {
        super(obj);
    }
    public static boolean isBlockLaddeer(Block block){
        return BlockLadderClass.isInstance(block.getWrappedObject());
    }
}
